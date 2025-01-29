class Solution {
    private boolean dfs(int node, int parent, boolean visited[], List<List<Integer>> adj){
        visited[node]=true;

        for(int neighbor: adj.get(node)){
            if(!visited[neighbor]){
                if(dfs(neighbor, node, visited, adj)) return true;
            }else if(neighbor != parent){
                System.out.println(neighbor + " "+ parent);
                return true;
            }
        }
        return false;
    }
    public int[] findRedundantConnection(int[][] edges) {
        int m = edges.length;
        List<List<Integer>> adj = new ArrayList<>();
        for(int i=0; i<=m; i++)
        adj.add(new ArrayList<>());
        boolean visited[]=new boolean[m+1];

        for(int edge[]: edges){
            int u = edge[0], v = edge[1];

            Arrays.fill(visited, false);
            adj.get(u).add(v);
            adj.get(v).add(u);

            if(dfs(u, -1, visited, adj)){
                return new int[]{u, v};
            }
        }
        return new int[0];
    }
}