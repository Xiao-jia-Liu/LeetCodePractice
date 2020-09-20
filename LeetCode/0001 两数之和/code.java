class Solution {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer,Integer> h = new HashMap<>();
        for(int i = 0; i < nums.length; i++) {
            Integer j = h.get(target - nums[i]);
            if(j != null) {
                int[] ret = new int[2];
                if(j > i) {
                    ret[0] = i;
                    ret[1] = j;
                } else {
                    ret[0] = j;
                    ret[1] = i;
                }
                return ret;
            } else {
                h.put(nums[i],i);
            }
        }
        return null;
    }

}