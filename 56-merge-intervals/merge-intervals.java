class Solution {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a,b)->Integer.compare(a[0],b[0]));
        List<int[]> result = new ArrayList<>();
        int currInterval[] = intervals[0];
        for(int i=1; i<intervals.length; i++){
            if(intervals[i][0]<=currInterval[1]){
                currInterval[1]=Math.max(intervals[i][1], currInterval[1]);
            }else{
                result.add(currInterval);
                currInterval = intervals[i];
            }
        }
        result.add(currInterval);
        return result.toArray(new int[result.size()][]);
    }
}