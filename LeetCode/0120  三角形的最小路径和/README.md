##### 题目描述

给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。

**相邻的结点** 在这里指的是 `下标` 与 `上一层结点下标` 相同或者等于 `上一层结点下标 + 1` 的两个结点。

 

例如，给定三角形：

```
[
     [2],
    [3,4],
   [6,5,7],
  [4,1,8,3]
]
```

自顶向下的最小路径和为 `11`（即，**2** + **3** + **5** + **1** = 11）。

 

**说明：**

如果你可以只使用 *O*(*n*) 的额外空间（*n* 为三角





##### 递归实现

其实就是一个二叉树的最小路径问题，只需要添加一个函数，求数组的”左节点“和右节点就可以了

```kotlin
import java.util.*

fun main() {
    val triangle = arrayOf(arrayOf(2), arrayOf(3,4),arrayOf(6,5,7),arrayOf(4,1,8,3))

    var res = Int.MAX_VALUE

    println(calSum(triangle,0,0))




}


fun calSum(nums: Array<Array<Int>>,i: Int,j: Int): Int {
    if (nums.isEmpty()) {
        return 0
    }

    if (i == nums.size - 1) {
        return nums[i][j]
    }

    var cur = nums[i][j]

    val left = calSum(nums,i + 1,j)
    val right = calSum(nums,i + 1,j + 1)

    return if (left < right) {
        cur + left
    } else {
        cur + right
    }
}
```

缺点是重复计算很多次，越靠前的重复计算计算次数越多，回超出时间限制，所以，自然而然的想到用数组保存之前的结果，下次直接取来用就好了，不用每次都要重新计算



##### 动态规划

用f\[i]\[j] 来表示第 i 行第 j 列的计算值，则 f\[i]\[j] = min(f\[i - 1]\[j],f[i - 1]\[j - 1]) + triangle\[i]\[j],考虑边界条件 i = 0以及 j = size - 1时，迭代计算每个值，最后在 f\[size - 1]\[0 .. size - 1]中找出最小值就行了，而开始的条件是 f\[0]\[0] = triangle\[0]\[0]，实现比较简单，不在给出代码实现



##### 动态规划 + 空间优化

```kotlin
class Solution {
    fun minimumTotal(triangle: List<List<Int>>): Int {
        if (triangle.isEmpty()) {
            return 0
        }

        val dp = triangle[triangle.size - 1].toIntArray()

        for (i in triangle.size - 2 downTo 0) {
            for (j in 0 .. i) {
                dp[j] = Math.min(dp[j],dp[j + 1]) + triangle[i][j]
            }
        }

        return dp[0]
    }
}
```

