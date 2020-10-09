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









