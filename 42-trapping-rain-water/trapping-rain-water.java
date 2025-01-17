class Solution {
    public int trap(int[] height) {
        int total = 0; 
        int l =0, r= height.length-1;
        int lmax = 0, rmax = height[r];

        while(l<r){
            if(height[l] <= height[r]){
                if(height[l] < lmax){
                    total += lmax-height[l];
                }else{
                    lmax = height[l];
                }
                l++;
            }else{
                if(height[r] < rmax){
                    total += rmax-height[r];
                }else{
                        rmax = height[r];
                      }
                r--;
            }
        }
                return total;
            }
        } 