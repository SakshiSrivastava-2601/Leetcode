class Solution {
    public int firstCompleteIndex(int[] arr, int[][] mat) {
        int rows = mat.length, cols = mat[0].length;
        Map<Integer, int[]> positionMap = new HashMap<>();
        int rowCount[] = new int[rows];
        int colCount[] = new int[cols];
        Arrays.fill(rowCount, cols);
        Arrays.fill(colCount, rows);
        for(int q = 0; q< rows; q++){
            for(int p=0; p<cols; p++){
                positionMap.put(mat[q][p], new int[]{q,p});
            }
        }
         for(int idx =0; idx<arr.length; idx++){
            int pos[] = positionMap.get(arr[idx]);
            if(--rowCount[pos[0]] == 0 || --colCount[pos[1]]== 0){
                return idx;
            }
         }
         return -1;
    }
}