##### 题目描述

给定一个**无重复元素**的数组 `candidates` 和一个目标数 `target` ，找出 `candidates` 中所有可以使数字和为 `target` 的组合。

`candidates` 中的数字可以无限制重复被选取。

**说明：**

- 所有数字（包括 `target`）都是正整数。
- 解集不能包含重复的组合。 

**示例 1：**

```
输入：candidates = [2,3,6,7], target = 7,
所求解集为：
[
  [7],
  [2,2,3]
]
```

**示例 2：**

```
输入：candidates = [2,3,5], target = 8,
所求解集为：
[
  [2,2,2,2],
  [2,3,3],
  [3,5]
]
```

 

**提示：**

- `1 <= candidates.length <= 30`
- `1 <= candidates[i] <= 200`
- `candidate` 中的每个元素都是独一无二的。
- `1 <= target <= 500`





##### 递归法

很简单的思路，我先从数组中选择一个数出来，然后target 减去这个数，结果为target1，很显然，又是一个子问题，那当target1 == 0时，就说明此时的减数的路径正好满足，当 < 0时，说明已经超过，不满足，当小于时，继续减，对数组中每个元素都进行组合递归，组合递归的循环式为：

```
for (item in 集合) {
	添加到路径中
	递归
	从路径中删除
}
```

所以，转换为代码也是很简单的：

```kotlin
class Solution {
    val res = ArrayList<ArrayList<Int>>()
    val path = ArrayList<Int>()

    fun combinationSum(candidates: IntArray, target: Int): List<List<Int>> {
        checkCandidates(candidates, target, 0)
        return res
    }

    fun checkCandidates(can: IntArray,target: Int,index: Int) {
        if (target < 0) {
            return
        }

        if (target == 0) {
            val tem = ArrayList<Int>(path)
            res.add(tem)
        }

        for (i in index until can.size) {
            path.add(can[i])
            checkCandidates(can, target - can[i], i)
            path.removeAt(path.lastIndex)
        }
    }
}
```

