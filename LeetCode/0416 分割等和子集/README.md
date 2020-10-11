##### 题目描述

给定一个**只包含正整数**的**非空**数组。是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。

**注意:**

1. 每个数组中的元素不会超过 100
2. 数组的大小不会超过 200

**示例 1:**

```
输入: [1, 5, 11, 5]

输出: true

解释: 数组可以分割成 [1, 5, 5] 和 [11].
```

 

**示例 2:**

```
输入: [1, 2, 3, 5]

输出: false

解释: 数组不能分割成两个元素和相等的子集.
```





##### 暴力破解

先遍历一次，算出总数组的和n，n为奇数，则直接返回false，题目就变成了在数组中找是否存在组合，使得组合的和为 n/2，此时就很容易想到用递归，因为在长度为 m的数组中中是否存在组合，就是在长度为m - 1的数组中找是否存在组合

```kotlin
class Solution {
    fun canPartition(nums: IntArray): Boolean {
        var sum = 0

        for (num in nums) {
            sum += num
        }

        if (sum %2 != 0) {
            return false
        }

        return recursion(nums, 0, sum / 2)
    }

    fun recursion(nums: IntArray, start: Int, target: Int):Boolean {
        for (i in start until nums.size) {
            if (nums[i] == target) {
                return true
            }
        }

        for (i in start until nums.size) {
            if (recursion(nums, i + 1, target - nums[i])) {
                return true
            }
        }

        return false
    }
}
```

能解决问题，但是时间复杂度高，直接没法ac



##### 动态规划（来自LeetCode官方解析）

#### 方法一：动态规划

**思路与算法**

这道题可以换一种表述：给定一个只包含正整数的非空数组 nums[0]，判断是否可以从数组中选出一些数字，使得这些数字的和等于整个数组的元素和的一半。因此这个问题可以转换成「0-1 背包问题」。这道题与传统的「0−1 背包问题」的区别在于，传统的「0−1 背包问题」要求选取的物品的重量之和**不能超过**背包的总容量，这道题则要求选取的数字的和**恰好等于**整个数组的元素和的一半。类似于传统的「0−1 背包问题」，可以使用动态规划求解。

在使用动态规划求解之前，首先需要进行以下判断。

- 根据数组的长度 n 判断数组是否可以被划分。如果 n<2，则不可能将数组分割成元素和相等的两个子集，因此直接返回 false。
- 计算整个数组的元素和 sum以及最大元素 maxNum。如果 sum是奇数，则不可能将数组分割成元素和相等的两个子集，因此直接返回false。如果 *sum* 是偶数，则令 *target*=*sum / 2*，需要判断是否可以从数组中选出一些数字，使得这些数字的和等于 *target*。如果 *maxNum*>*target*，则除了 *maxNum* 以外的所有元素之和一定小于 *target*，因此不可能将数组分割成元素和相等的两个子集，直接返回 false。

创建二维数组dp*，包含 n* 行 *target*+1 列，其中 dp\[i][j] 表示从数组的 [0,i][0,*i*] 下标范围内选取若干个正整数（可以是 0 个），是否存在一种选取方案使得被选取的正整数的和等于j。初始时，dp 中的全部元素都是 false。

在定义状态之后，需要考虑边界情况。以下两种情况都属于边界情况。

- 如果不选取任何正整数，则被选取的正整数等于 0。因此对于所有 0 <= i <*n*都有 dp\[i][0] = true
- 当 i==0时，只有一个正整数nums\[0] 可以被选取，因此 dp\[0][nums[0]] = true

对于 i>0 且 j>0 的情况，如何确定 dp\[i][j]的值？需要分别考虑以下两种情况。

- 如果 j ≥nums\[i]，则对于当前的数字 nums\[i]，可以选取也可以不选取，两种情况只要有一个为 true，就有 dp\[i][j]=true。
  - 如果不选取nums\[i]，则 dp\[i][j] = dp\[i-1][j]
  - 如果选取选取nums\[i] , 则dp\[i][j] = dp\[i-1][j - nums\[i]]
- 如果 j < nums\[i]，则在选取的数字的和等于 j 的情况下无法选取当前的数字 nums\[i]，因此有dp\[i][j] = dp\[i - 1][j]

状态转移方程如下：
$$
dp[i][j] = \begin{cases}
dp[i-1][j] | dp[i-1][j - nums[i]], &j >= nums[i]
\\
dp[i-1][j], &j< nums[i]
\end{cases}
$$
最终得到dp\[n - 1][target] 即为答案。



```kotlin
class Solution {
    fun canPartition(nums: IntArray): Boolean {
        if (nums.size <= 1) {
            return false
        }

        var sum = 0

        for (num in nums) {
            sum += num
        }

        if (sum % 2 == 1) {
            return false
        }

        val target = sum / 2
        nums.sort()

        if (nums[nums.lastIndex] > target) {
            return false
        }

        val dp = Array<Array<Boolean>>(nums.size) {Array<Boolean> (target + 1) {false}}
        
        for(i in nums.indices) {
            for (j in 0 .. target) {
                if (i == 0) {
                    dp[0][nums[0]] = true
                    break
                }

                if (j >= nums[i]) {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i]]
                } else {
                    dp[i][j] = dp[i - 1][j]
                }
            }
        }

        return dp[nums.size - 1][target]

    }
}
```

空间压缩，注意到每次的状态值都只与上次的值有关，横向坐标永远是dp[i - 1]，即上一次的值中的某列值有关,那么，可以把横向坐标去掉，只用一个一维的数组，上一次的值其实就是上一层循环已经运算出来的结果，且需要注意的是第二层的循环我们需要从大到小计算，因为如果我们从小到大更新 dp 值，那么在计算 dp\[j]值的时候，dp\[j - nums\[i]] 已经是被更新过的状态，不再是上一行的 dp 值。

```kotlin
class Solution {
    fun canPartition(nums: IntArray): Boolean {
        if (nums.size <= 1) {
            return false
        }

        var sum = 0

        for (num in nums) {
            sum += num
        }

        if (sum % 2 == 1) {
            return false
        }

        val target = sum / 2
        nums.sort()

        if (nums[nums.lastIndex] > target) {
            return false
        }

        val dp = Array<Boolean>(target + 1) {false}
        dp[0] = true

        for (i in nums.indices) {
            for (j in target downTo nums[i]) {
                dp[j] = dp[j] || dp[j - nums[i]]
            }
        }

        return dp[target]
    }
}
```

