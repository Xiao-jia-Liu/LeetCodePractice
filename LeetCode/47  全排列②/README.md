##### 题目描述

给定一个可包含重复数字的序列，返回所有不重复的全排列。

**示例:**

```
输入: [1,1,2]
输出:
[
  [1,1,2],
  [1,2,1],
  [2,1,1]
]
```





##### 递归解法 + 剪枝

kotlin版本

```kotlin
class Solution {
    val res = ArrayList<ArrayList<Int>>()
    val path = ArrayList<Int>()

    fun permuteUnique(nums: IntArray): List<List<Int>> {
        if (nums.size == 0) {
            return res
        }

        nums.sort()

        val used = BooleanArray(nums.size) {false}

        recursion(nums,nums.size,0,used)

        return res
    }

    fun recursion(nums: IntArray,end: Int, deep: Int,used: BooleanArray) {
        if (end == deep) {
            val tem = ArrayList<Int>(path)
            res.add(tem)
            return
        }

        for (i in 0 until end) {
            if (used[i]) {
                continue
            }

            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                continue
            }

            path.add(nums[i])
            used[i] = true
            recursion(nums,end,deep + 1,used)
            used[i] = false
            path.removeAt(path.lastIndex)
        }
    }
}

```

