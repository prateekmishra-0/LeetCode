class Pair {
    
    int row;
    
    int column;
    
    Pair(int row , int column){
        
        this.row = row;
        
        this.column = column;
    }
}

class Region_Data {
    
    int walls_required_count;
    
    int uninfected_cells_count;
    
    List<Pair> infected_cells_list;
    
    Region_Data(){
        
        walls_required_count = 0;
        
        uninfected_cells_count = 0;
        
        infected_cells_list = new ArrayList<>();
    }
}

class Solution {
    
    int[][] four_directions_array = new int[][] {{-1 , 0} , {1 , 0} , {0 , -1} , {0 , 1}};
    
    public boolean is_within_bounds(int current_i , int current_j , int R , int C){
        
        return ((current_i >= 0) && (current_i < R) && (current_j >= 0) && (current_j < C));
    }
    
    public void depth_first_search(int current_i , int current_j , int R , int C , int[][] matrix , boolean[][] visited_matrix , boolean[][] uninfected_cells_visited_matrix , Region_Data region_data_object){
        
        visited_matrix[current_i][current_j] = true;
        
        region_data_object.infected_cells_list.add((new Pair(current_i , current_j)));
        
        for(int[] coordinates_array : four_directions_array){
            
            int next_i = (current_i + coordinates_array[0]);
            
            int next_j = (current_j + coordinates_array[1]);
            
            if((is_within_bounds(next_i , next_j , R , C)) && (matrix[next_i][next_j] != -999) && (!visited_matrix[next_i][next_j])){
                
                if(matrix[next_i][next_j] == 1){
                    depth_first_search(next_i , next_j , R , C , matrix , visited_matrix , uninfected_cells_visited_matrix , region_data_object);
                }else if(matrix[next_i][next_j] == 0){
                    
                    region_data_object.walls_required_count++;
                    
                    if(!uninfected_cells_visited_matrix[next_i][next_j]){
                        uninfected_cells_visited_matrix[next_i][next_j] = true;
                        region_data_object.uninfected_cells_count++;
                    }
                }
            }
        }
    }
    
    public int containVirus(int[][] matrix){
        
        int total_walls_required_count = 0;
        
        int R = matrix.length;
        
        int C = matrix[0].length;
        
        while(true){
            
            PriorityQueue<Region_Data> max_heap = new PriorityQueue<>((a , b) -> (b.uninfected_cells_count - a.uninfected_cells_count));
            
            boolean[][] visited_matrix = new boolean[R][C];
            
            for(int i = 0 ; (i < R) ; i++){
                
                for(int j = 0 ; (j < C) ; j++){
                    
                    if((!visited_matrix[i][j]) && (matrix[i][j] == 1)){
                        
                        Region_Data region_data_object = new Region_Data();
                        
                        depth_first_search(i , j , R , C , matrix , visited_matrix , (new boolean[R][C]) , region_data_object);
                        
                        if(region_data_object.uninfected_cells_count > 0){
                            max_heap.add(region_data_object);
                        }
                    }
                }
            }
            
            if(max_heap.isEmpty()){
                break;
            }
            
            Region_Data region_data_object_with_maximum_uninfected_cells = max_heap.poll();
            
            total_walls_required_count += region_data_object_with_maximum_uninfected_cells.walls_required_count;
            
            for(Pair pair_object : region_data_object_with_maximum_uninfected_cells.infected_cells_list){
                matrix[pair_object.row][pair_object.column] = -999;
            }
            
            while(!max_heap.isEmpty()){
                
                for(Pair pair_object : max_heap.poll().infected_cells_list){
                    
                    int current_i = pair_object.row;
                    
                    int current_j = pair_object.column;
                    
                    for(int[] coordinates_array : four_directions_array){
                        
                        int next_i = (current_i + coordinates_array[0]);
                        
                        int next_j = (current_j + coordinates_array[1]);
                        
                        if((is_within_bounds(next_i , next_j , R , C)) && (matrix[next_i][next_j] == 0)){
                            matrix[next_i][next_j] = 1;
                        }
                    }
                }
            }
        }
        
        return total_walls_required_count;
    }
}