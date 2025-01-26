class Solution {
    private int longestCycle = -1; // To store the length of the longest cycle
    private int maxPairsAndChains = 0; // To store the sum of lengths from chains and 2-cycles

    public int maximumInvitations(int[] favorite) {
        // Step 1: Prepare adjacency list
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        int n = favorite.length;
        for (int i = 0; i < n; i++)
            adj.add(new ArrayList<>());

        for (int i = 0; i < n; i++) {
            adj.get(i).add(favorite[i]);
        }

        // Step 2: Find the longest cycle
        int longestCycleLength = findLongestCycle(adj);

        // Step 3: Handle chains and 2-cycles
        int chainAndPairLength = handleChainsAndPairs(favorite);

        // Return the maximum of the longest cycle and the total from chains and 2-cycles
        return Math.max(longestCycleLength, chainAndPairLength);
    }
    
    // find the longest cycle in the given graph. 
    public int findLongestCycle(ArrayList<ArrayList<Integer>> adj) {
        int n = adj.size();
        boolean[] visited = new boolean[n];
        boolean[] currVisited = new boolean[n];
        int[] discoveryTime = new int[n];
        Arrays.fill(discoveryTime, -1);

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                cycle(i, visited, currVisited, discoveryTime, 0, adj);
            }
        }

        return longestCycle;
    }
    // dfs to capture the longest cycle size in a global variable. 
    private void cycle(int node, boolean[] visited, boolean[] currVisited, int[] discoveryTime, int time, ArrayList<ArrayList<Integer>> adj) {
        // Mark the current node as visited in both arrays and set its discovery time.
        visited[node] = true;
        currVisited[node] = true;
        discoveryTime[node] = time;

        // Explore all adjacent vertices of the current node.
        for (int neighbor : adj.get(node)) {
            if (!visited[neighbor]) {
                // If the neighbor is not visited, continue DFS.
                cycle(neighbor, visited, currVisited, discoveryTime, time + 1, adj);
            } else if (currVisited[neighbor]) {
                // If the neighbor is visited in the current path, a cycle is detected.
                int cycleLength = time - discoveryTime[neighbor] + 1; // Calculate cycle length
                longestCycle = Math.max(longestCycle, cycleLength);  // Update the longest cycle
            }
        }

        // Backtrack: Mark the current node as unvisited in the current traversal path.
        currVisited[node] = false;
    }


    private int handleChainsAndPairs(int[] favorite) {
        int n = favorite.length;
        int[] inDegree = new int[n];
        for (int i = 0; i < n; i++) {
            inDegree[favorite[i]]++;
        }

        // Find all nodes with in-degree 0 (start of chains)
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }

        // Process chains and reduce the graph
        int[] chainLengths = new int[n];
        while (!queue.isEmpty()) {
            int node = queue.poll();
            int fav = favorite[node];
            chainLengths[fav] = Math.max(chainLengths[fav], chainLengths[node] + 1);
            inDegree[fav]--;
            if (inDegree[fav] == 0) {
                queue.add(fav);
            }
        }

        // Handle 2-cycles and combine chains
        boolean[] visited = new boolean[n];
        int totalPairsAndChains = 0;

        for (int i = 0; i < n; i++) {
            if (favorite[favorite[i]] == i && i < favorite[i]) { // Found a 2-cycle
                int chain1 = chainLengths[i];
                int chain2 = chainLengths[favorite[i]];
                totalPairsAndChains += 2 + chain1 + chain2; // Pair plus chains
            }
        }

        return totalPairsAndChains;
    }
}