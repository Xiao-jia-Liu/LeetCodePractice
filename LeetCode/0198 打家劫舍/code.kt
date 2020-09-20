class Solution {
    fun rob(nums: IntArray): Int {
        //表示Sn-2
        var Sn_2 = 0

        //表示Sn-1
        var Sn_1 = 0

        //边界条件判断，当只有0或1间的时候直接返回
        if (nums.size == 0) return 0
        if (nums.size == 1) return nums[0]

        //初始化Sn-2与Sn-1
        Sn_2 = 0
        Sn_1 = nums[0]
        
        var sum = 0

        //开始递推公式
        for (i in 1 until nums.size) {
            sum = Math.max(Sn_2 + nums[i],Sn_1)
            Sn_2 = Sn_1
            Sn_1 = sum
        }

        return sum
    }
}