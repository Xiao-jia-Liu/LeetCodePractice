##### 题目描述

给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。

说明:

初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。
你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。


**示例:**
```
输入:
nums1 = [1,2,3,0,0,0], m = 3
nums2 = [2,5,6],       n = 3

输出: [1,2,2,3,5,6]

```


##### 题解
合并排序思路，把前几天提交的找中位数代码copy了一下。比较简单。

python版本

```python
class Solution(object):
    def merge(self, nums1, m, nums2, n):
        """
        :type nums1: List[int]
        :type m: int
        :type nums2: List[int]
        :type n: int
        :rtype: None Do not return anything, modify nums1 in-place instead.
        """

        n,m = m, n
        result = []
        flag1 = 0
        flag2 = 0
        
        while(flag1 < n and flag2 < m):
            if nums1[flag1] >= nums2[flag2]:
                result.append(nums2[flag2])
                flag2 = flag2 + 1
            else:
                result.append(nums1[flag1])
                flag1 = flag1 + 1
        if flag1 == n:
            for j in range(flag2,m):
                result.append(nums2[j])
        if flag2 == m:
            for k in range(flag1,n):
                result.append(nums1[k])
        for i in range(len(result)):
            nums1[i] = result[i]
```





利用两个指针，一个指向m的尾部，一个指向n的尾部，每次都选择m和n的大的一个，同时，再用一个index指针，从往前填充nums1数组，空间复杂度 O(1),时间复杂度 O(m+n)



```kotlin
class Solution {
    fun merge(nums1: IntArray, m: Int, nums2: IntArray, n: Int): Unit {
        var index = m + n - 1
        var m = m - 1
        var n = n - 1

        while (m >= 0 && n >= 0) {
            if (nums1[m] >= nums2[n]) {
                nums1[index] = nums1[m]
                m = m -1
                index = index - 1
            } else {
                nums1[index] = nums2[n]
                n = n -1
                index = index - 1
            }
        }

        if (m >= 0) {
            while (m >= 0) {
                nums1[index] = nums1[m]
                m = m -1
                index = index - 1
            }
        }

        if (n >= 0) {
            while (n >= 0) {
                nums1[index] = nums2[n]
                n = n -1
                index = index - 1
            }
        }
    }
}
```







