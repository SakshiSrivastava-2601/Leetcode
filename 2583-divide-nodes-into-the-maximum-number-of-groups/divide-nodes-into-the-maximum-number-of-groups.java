class Solution {
    public int magnificentSets(int n, int[][] edges) {
        List<List<Integer>> adj = new ArrayList<>();
        n++;

        for(int i=0; i<n; i++){
            adj.add(new ArrayList<>());
        }
        for(int edge[]: edges){
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }
        if(!isBipartite(adj, n)){
            return -1;
        }
        int bfsDepth[] = new int[n];
        for(int i=1; i<n; i++){
            bfsDepth[i]=bfsDepthFinder(adj, i);
        }
        int visited[]= new int[n];
        int groupCount=0;
        for(int i=1; i<n; i++){
            if(visited[i]==0){
                groupCount += dfs(adj, visited, bfsDepth, i);
            }
        }
        return groupCount;
    }
    private int dfs(List<List<Integer>> adj, int visited[], int bfsDepth[], int node){
        visited[node]=1;
        int maxDepth = bfsDepth[node];

        for(int neighbor : adj.get(node)){
            if(visited[neighbor]==0){
                maxDepth = Math.max(maxDepth, dfs(adj, visited, bfsDepth, neighbor));
            }
        }
        return maxDepth;
    }
    private int bfsDepthFinder(List<List<Integer>> adj, int start){
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{start, 1});
        int visited[]=new int[adj.size()];
        visited[start]=1;
        int last[]=new int[2];

        while(!queue.isEmpty()){
            last = queue.poll();
            for(int neighbor : adj.get(last[0])){
                if(visited[neighbor]==0){
                    visited[neighbor]=1;
                    queue.add(new int[]{neighbor, last[1]+1});
                }
            }
        }
        return last[1];
    }
    private boolean isBipartite(List<List<Integer>> adj, int n){
        int color[]=new int[n];
        for(int i=0; i<n; i++){
            if(color[i]==0 && !bfsCheck(adj, color, i)){
                return false;
            }
        }
        return true;
    }
    private boolean bfsCheck(List<List<Integer>> adj, int color[], int node){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(node);
        color[node]=1;

        while(!queue.isEmpty()){
            int current = queue.poll();
            int nextColor = (color[current]==1)?2:1;

            for(int neighbor : adj.get(current)){
                if(color[neighbor]==0){
                    color[neighbor] = nextColor;
                    queue.add(neighbor);
                }else if(color[neighbor] != nextColor){
                    return false;
                }
            }
        }
        return true;
    }
}