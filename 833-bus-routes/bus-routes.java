class Solution {
    public int numBusesToDestination(int[][] routes, int source, int target) {
        if (source == target) return 0;

        int n = routes.length;
        Map<Integer, List<Integer>> stopToRoutes = new HashMap<>();

        // Map each stop to the routes (bus indices) that pass through it
        for (int i = 0; i < n; i++) {
            for (int stop : routes[i]) {
                stopToRoutes.computeIfAbsent(stop, x -> new ArrayList<>()).add(i);
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        boolean[] visitedRoutes = new boolean[n];
        Set<Integer> visitedStops = new HashSet<>();

        // Start BFS from all routes that include the source stop
        for (int route : stopToRoutes.getOrDefault(source, new ArrayList<>())) {
            queue.offer(route);
            visitedRoutes[route] = true;
        }

        int busesTaken = 1;

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                int routeIdx = queue.poll();

                for (int stop : routes[routeIdx]) {
                    if (stop == target) return busesTaken;

                    // Visit neighboring routes via this stop
                    for (int neighborRoute : stopToRoutes.getOrDefault(stop, new ArrayList<>())) {
                        if (!visitedRoutes[neighborRoute]) {
                            visitedRoutes[neighborRoute] = true;
                            queue.offer(neighborRoute);
                        }
                    }
                }
            }

            busesTaken++;
        }

        return -1; // Target is unreachable
    }
}