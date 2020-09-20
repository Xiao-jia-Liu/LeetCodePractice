##### 题目描述

给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。

说明：解集不能包含重复的子集。

**示例:**

```
输入: nums = [1,2,3]
输出:
[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
]

```

##### 复用组合的代码，对子集的模1,2···n各取组合，然后罗列出来。

所以最终代码是：

```python
from itertools import combinations
class Solution(object):
    def subsets(self, nums):
        """
        :type nums: List[int]
        :rtype: List[List[int]]
        """

        def combine(temp_list, n):
            '''根据n获得列表中的所有可能组合（n个元素为一组）'''
            temp_list2 = []
            for c in combinations(temp_list, n):
                temp_list2.append(list(c))
            return temp_list2

        end_list = []
        for i in range(len(nums)+1):
            end_list.extend(combine(nums, i))
        return end_list
```



kotlin版本

```kotlin
class Solution {
    val res = ArrayList<ArrayList<Int>>()
    val path = ArrayList<Int>()

    fun subsets(nums: IntArray): List<List<Int>> {
        val used = BooleanArray(nums.size) {false}
        recursion(nums, 0, used)
        res.add(ArrayList<Int>())

        return res
    }

    fun recursion(nums: IntArray,start: Int,used: BooleanArray) {
        if (start != 0) {
            res.add(ArrayList<Int>(path))
        }

        for (i in start until nums.size) {
            if (used[i]) {
                continue
            }

            used[i] = true
            path.add(nums[i])
            recursion(nums, i + 1, used)
            path.removeAt(path.lastIndex)
            used[i] = false
        }
    }
}

```

