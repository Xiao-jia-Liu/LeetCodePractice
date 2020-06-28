##### 题目描述

给定一个含有 **n** 个正整数的数组和一个正整数 **s ，**找出该数组中满足其和 **≥ s** 的长度最小的连续子数组，并返回其长度**。**如果不存在符合条件的连续子数组，返回 0。

 

**示例：**

```
输入：s = 7, nums = [2,3,1,2,4,3]
输出：2
解释：子数组 [4,3] 是该条件下的长度最小的连续子数组。
```

 

**进阶：**

- 如果你已经完成了 *O*(*n*) 时间复杂度的解法, 请尝试 *O*(*n* log *n*) 时间复杂度的解法。





##### 解法：双指针法

维护两个指针start与end，当从start到end的子数组和小于等于target的话，就移动end指针，因为小于表示此时和小于target，还不满足条件，如果从start到end的组数组和大于target，则表示已经满足基本条件，此时考虑收缩，但是end不能收缩，因为上一个数字相加时是小于target的，只能收缩start，所有start右移



```kotlin
class Solution {
    fun minSubArrayLen(s: Int, nums: IntArray): Int {
        var start = 0
        var end = -1
        var ans = Int.MAX_VALUE
        var sum = 0

        while (end < nums.size && start < nums.size) {
            if (sum < s) {
                end++
                
                if (end == nums.size) {
                    break
                }

                sum += nums[end]
            } else {
                ans = Math.min(ans,end - start + 1)
                sum -= nums[start]
                start++
            }
        }

        return if (ans == Int.MAX_VALUE) 0 else ans
    }
}
```

