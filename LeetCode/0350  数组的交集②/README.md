##### 题目描述

给定两个数组，编写一个函数来计算它们的交集。

 

**示例 1：**

```
输入：nums1 = [1,2,2,1], nums2 = [2,2]
输出：[2,2]
```

**示例 2:**

```
输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
输出：[4,9]
```

 

**说明：**

- 输出结果中每个元素出现的次数，应与元素在两个数组中出现次数的最小值一致。
- 我们可以不考虑输出结果的顺序。

***\*进阶\**：**

- 如果给定的数组已经排好序呢？你将如何优化你的算法？
- 如果 *nums1* 的大小比 *nums2* 小很多，哪种方法更优？
- 如果 *nums2* 的元素存储在磁盘上，磁盘内存是有限的，并且你不能一次加载所有的元素到内存中，你该怎么办？





##### 暴力破解法

遍历数组 1 ，对每个元素都在数组2中查找，如果数组2中存在这个元素，则结果集中就添加这个元素，否则不添加。最后，由于可能存在多个元素的情况，还要在遍历一次集合数组，在数组1中和数组2中寻找最小值，如果最小值不为1，则需要添加这个元素的最小个数 - 1.时间复杂度 m * n

进阶：

如果指定数组已经排好顺序，则用二分法就可以将查找复杂度将为 m * logn

如果数组1 比数组2 小很多，则遍历数组1，在数组2中二分查找

利用随机存储直接对文件进行操作



```kotlin
class Solution {
    fun intersect(nums1: IntArray, nums2: IntArray): IntArray {
        val res = LinkedList<Int>()

        for (num1 in nums1) {
            for (num2 in nums2) {
                if (num1 == num2) {
                    if (res.contains(num1)) {
                        break
                    }

                    res.add(num1)
                    break
                }
            }
        }

        val resSize = res.size

        for (r in 0 until resSize) {
            var count1 = 0
            var count2 = 0

            for (n in nums1) {
                if (res[r] == n) {
                    count1 ++
                }
            }

            for (n in nums2) {
                if (res[r] == n) {
                    count2 ++
                }
            }


            val min = Math.min(count1,count2)

            if (min != 1) {
                for (i in 0 until min - 1) {
                    res.add(res[r])
                }
            }
        }

        return res.toIntArray()
    }
}
```





##### 哈希表法

遍历数组1，key为数组元素，value为数组1中出现的次数，再遍历数组2，如果哈希表中有该key，则放入结果数组里，且value - 1，很简单，不再给出代码实现