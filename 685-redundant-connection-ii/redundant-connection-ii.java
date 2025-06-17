class Solution {
    public int[] findRedundantDirectedConnection(int[][] edges) {
        
        int[]inDegree = new int[edges.length+1];        // Case 1: Check for double parents
        Arrays.fill(inDegree, -1);
        
        int bl1 = -1, bl2 = -1;
        
        for(int i=0; i<edges.length; i++){
            int u = edges[i][0];
            int v = edges[i][1];
            
            if(inDegree[v] == -1){
                inDegree[v] = i;
            } else {
                bl1 = i;                        // If double parents exist, we need to find which one of them is unecessary
                bl2 = inDegree[v];              // We store them both in vars and then run DSU on both, ignoring
                                                // one edge at a time and check for correct removal
                break;
            }
        }
        
         // DSU Implementation
        
        int[]parent = new int[edges.length+1];
        int[]rank = new int[edges.length+1];
        
        for(int i=0; i<parent.length; i++) parent[i] = i;
        
        for(int i=0; i<edges.length; i++){
            if(i == bl1) continue;
            
            int[]edge = edges[i];
            boolean bool = union(edge[0], edge[1], parent, rank);
            if(bool){
                if(bl1 == -1){                  // Case 3: Double parents dont exist. Cycle exists.
                    return edge;
                } else{
                    return edges[bl2];          // Case 2: Double parents and cycle both exist
                }
            }
        }
        
        return edges[bl1];
    }
    
    public int find(int x, int[]parent){
        if(x == parent[x]) return x;
        else return find(parent[x], parent);
    }
	
    public boolean union(int s1, int s2, int[]parent, int[]rank){
		int s1lead = find(s1, parent);        // Finding the parent nodes of both the nodes
        int s2lead = find(s2, parent);
        
        if(s1lead != s2lead){
            if(rank[s1lead] > rank[s2lead]){
                parent[s2lead] = s1lead;
            } else if(rank[s2lead] > rank[s1lead]){
                parent[s1lead] = s2lead;
            } else{
                parent[s2lead] = s1lead;
                rank[s1lead]++;
            }
            return false;       // No cycle due to this edge  
        } else return true;     // Indicates that the cycle exists
    }
}