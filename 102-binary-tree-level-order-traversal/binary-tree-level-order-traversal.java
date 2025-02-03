/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> all = new ArrayList<>();
        pre(root, 0, all);
        return all;
    }
    public static void pre(TreeNode root, int l, List<List<Integer>> all){
        if(root == null)
        return;
        if(all.size()==l){
            List<Integer> li = new ArrayList<>();
            li.add(root.val);
            all.add(li);
        }
        else
        all.get(l).add(root.val);
        pre(root.left, l+1, all);
        pre(root.right, l+1, all);
    }
}