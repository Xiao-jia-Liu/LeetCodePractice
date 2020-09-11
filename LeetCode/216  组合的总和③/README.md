##### 题目描述

找出所有相加之和为 ***n*** 的 ***k\*** 个数的组合***。\***组合中只允许含有 1 - 9 的正整数，并且每种组合中不存在重复的数字。

**说明：**

- 所有数字都是正整数。
- 解集不能包含重复的组合。 

**示例 1:**

```
输入: k = 3, n = 7
输出: [[1,2,4]]
```

**示例 2:**

```
输入: k = 3, n = 9
输出: [[1,2,6], [1,3,5], [2,3,4]]
```







##### 递归 + 剪枝

和之前的类似，不能重复，那ok，每次递归都顺延一个，只能是k个，ok，设置一个深度，只有在深度达到这个值才取值，在1-9中选择，ok，直接先构造一个待选数 1-9的数组

```kotlin
class Solution {
    val path = ArrayList<Int>()
    val res = ArrayList<ArrayList<Int>>()

    fun combinationSum3(k: Int, n: Int): List<List<Int>> {
        val can = ArrayList<Int>()

        for (i in 1 .. 9) {
            can.add(i)
        }

        combinationSum(can, n, 0, k, 0)

        return res
    }


    fun combinationSum(can: ArrayList<Int>,target: Int,deep: Int,maxDeep: Int,start: Int) {
        if (deep > maxDeep || target < 0) {
            return
        }

        if (target == 0 && deep == maxDeep) {
            res.add(ArrayList<Int>(path))
        }

        for (i in start until can.size) {
            path.add(can[i])
            combinationSum(can,target - can[i],deep + 1, maxDeep, i + 1)
            path.removeAt(path.lastIndex)
        }
    }
}
```

