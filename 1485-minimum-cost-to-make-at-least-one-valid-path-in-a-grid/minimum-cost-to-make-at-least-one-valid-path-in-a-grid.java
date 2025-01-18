class Solution {
    public int minCost(int[][] grid) {
        int m=grid.length;
        int n=grid[0].length;

        int[][] minCost=new int[m][n];
        for(int i=0; i<m; i++){
            Arrays.fill(minCost[i], Integer.MAX_VALUE);
        }

        Deque<int[]> dq=new ArrayDeque<>();
        dq.offerFirst(new int[]{0,0});
        minCost[0][0]=0;
        
        int[][] direction={ {0,1}, {0,-1}, {1,0}, {-1,0} };

        while(!dq.isEmpty()){
            int[] arr=dq.pollFirst();
            int r=arr[0];
            int c=arr[1];

            
            for(int i=0; i<4; i++){
                int nr=r+direction[i][0];
                int nc=c+direction[i][1];
                int cost=(grid[r][c]!=(i+1))?1:0;

                if( nr>=0 && nr<m && nc>=0 && nc<n && minCost[r][c]+cost<minCost[nr][nc]){
                    minCost[nr][nc]=minCost[r][c]+cost;

                    if(cost==1){
                        dq.offerLast(new int[]{nr,nc});
                    }else{
                        dq.offerFirst(new int[]{nr,nc});
                    }                   
                }
            }
        }

        return minCost[m-1][n-1];
    }
}