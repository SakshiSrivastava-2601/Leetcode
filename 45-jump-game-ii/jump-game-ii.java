class Solution {
    public int jump(int[] nums) {
        int n = 0, f =  0, jumps = 0;
        while(f<nums.length-1){
            int farthest = 0;
            for(int i=n; i<= f; i++){
                farthest = Math.max(farthest, i+nums[i]);
            }
            n = f+1;
            f = farthest;
            jumps++;
        }
        return jumps;
    }
}