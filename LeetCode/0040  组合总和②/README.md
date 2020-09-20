##### 题目描述

给定一个数组 `candidates` 和一个目标数 `target` ，找出 `candidates` 中所有可以使数字和为 `target` 的组合。

`candidates` 中的每个数字在每个组合中只能使用一次。

**说明：**

- 所有数字（包括目标数）都是正整数。
- 解集不能包含重复的组合。 

**示例 1:**

```
输入: candidates = [10,1,2,7,6,1,5], target = 8,
所求解集为:
[
  [1, 7],
  [1, 2, 5],
  [2, 6],
  [1, 1, 6]
]
```

**示例 2:**

```
输入: candidates = [2,5,2,1,2], target = 5,
所求解集为:
[
  [1,2,2],
  [5]
]
```



##### 递归 + 剪枝

和之前的组合总和差不多的，只是，每个元素只能选择一次，那很简单，每次递归时，下标 +1 就解决了，因为+1就表示集合中元素少了这个元素。但是麻烦的点是，由于元素有重复的了，所以会导致有重复的结果。解决方法：

1. 记录路径时使用有序数组或者堆，这样在插入时判断是否已经有该元素，没有则插入

2. 剪枝

   结果重复的原因是因为在遍历时，重复的元素重复与能匹配的元素重复计算了，所以本质就是要出去重复元素的影响，方法就是，先对数组排序，排序之后，重复的元素都拍在一起，递归时，判断如果是兄弟节点，且与上一个节点相同，则不往路径里面添加该元素，直接跳过该元素

   ```kotlin
   class Solution {
       lateinit var can: IntArray
       val res = ArrayList<ArrayList<Int>>()
       val path = ArrayList<Int>()
       
       fun combinationSum2(candidates: IntArray, target: Int): List<List<Int>> {
           can = candidates.sortedArray()
           checkCandidates(target, 0)
           return res
       }
   
       fun checkCandidates(target: Int,index: Int) {
           if (target == 0) {
           res.add(ArrayList<Int>(path))
           }
   
           for (i in index until can.size) {
               //小于0，剪枝，且由于是有序数组，后续的所有元素肯定都小于0
               if (target - can[i] < 0) {
                   break
               }
   
               //兄弟节点相等剪枝
               //i > index 表示是兄弟节点，因为下次递归时，index会 + 1，i 从index开始，所以，
               //子节点的话，i 是等于index的
               if (i > index && can[i] == can[i - 1]) {
                   continue
               }
   
               path.add(can[i])
   
               checkCandidates(target - can[i], i + 1)
               path.removeAt(path.lastIndex)
           }
       }
   }
   ```

   