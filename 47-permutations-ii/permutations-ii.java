class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        boolean used[] = new boolean[nums.length];
        backtrack(nums, used, new ArrayList<>(), res);
        return res;
    }
    private void backtrack(int nums[], boolean used[], List<Integer> curr, List<List<Integer>> res){
        if(curr.size()==nums.length){
            res.add(new ArrayList<>(curr));
            return;
        }
        for(int i=0; i<nums.length; i++){
            if(used[i] || (i>0 && nums[i] == nums[i-1] && !used[i-1])){
            continue;
        }
        used[i] = true;
        curr.add(nums[i]);
        backtrack(nums, used, curr, res);
        used[i] = false;
        curr.remove(curr.size()-1);
    }
}
}