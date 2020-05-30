class Solution {
    public int rob(int[] nums) {
        //表示Sn-2
        int Sn_2 = 0;

        //表示Sn-1
        int Sn_1 = 0;

        //边界条件判断，当只有0或1间的时候直接返回
        if (nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];

        //初始化Sn-2与Sn-1
        Sn_2 = 0;
        Sn_1 = nums[0];
        
        int sum = 0;

        //开始递推公式
        for (int i = 1; i < nums.length; i++) {
            sum = Math.max(Sn_2 + nums[i],Sn_1);
            Sn_2 = Sn_1;
            Sn_1 = sum;
        }

        return sum;
  
    }
}