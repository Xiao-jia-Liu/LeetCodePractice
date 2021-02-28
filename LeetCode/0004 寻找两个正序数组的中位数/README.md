##### 题目描述：

给定两个大小为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的中位数。

进阶：你能设计一个时间复杂度为 O(log (m+n)) 的算法解决此问题吗？


**示例:**

```

输入：nums1 = [1,3], nums2 = [2]
输出：2.00000
解释：合并数组 = [1,2,3] ，中位数 2

```

##### O（m+n）解法：

参考合并排序思路，增加一个辅助列表，一次循环遍历，得到排序好的数组，从中截取中位数。

python版本
```python
class Solution(object):
    def findMedianSortedArrays(self, nums1, nums2):
        """
        :type nums1: List[int]
        :type nums2: List[int]
        :rtype: float
        """

        n = len(nums1)
        m = len(nums2)
        result = []
        flag1 = 0
        flag2 = 0
 
        while(flag1 < n and flag2 < m):
            if nums1[flag1] >= nums2[flag2]:
                result.append(float(nums2[flag2]))
                flag2 = flag2 + 1
            else:
                result.append(float(nums1[flag1]))
                flag1 = flag1 + 1
        if flag1 == n:
            for j in range(flag2,m):
                result.append(float(nums2[j]))
        if flag2 == m:
            for k in range(flag1,n):
                result.append(float(nums1[k]))
        mid = int((m+n)/2)
        if (m+n)%2 == 1:
            return result[mid]
        else:
            return (result[mid]+result[mid-1])/2
        
```

##### O（log(m+n)）解法：





```kotlin
class Solution {
    fun findMedianSortedArrays(nums1: IntArray, nums2: IntArray): Double {
        val totalLen = nums1.size + nums2.size
        val isObb = (totalLen % 2) != 0
        val mid = if (isObb) totalLen / 2 else totalLen / 2 - 1
        var index1 = 0
        var index2 = 0
        var currentIndex = 0
        while (index1 < nums1.size || index2 < nums2.size) {
            if ((if (index1 < nums1.size) nums1[index1] else Int.MAX_VALUE)
            <= 
            (if (index2 < nums2.size) nums2[index2] else Int.MAX_VALUE)) {
                if (currentIndex == mid) {
                    var res = nums1[index1].toDouble()
                    if (!isObb) {
                        if ((index1 + 1) < nums1.size && nums1[index1 + 1] <= (if (index2 < nums2.size) nums2[index2] else Int.MAX_VALUE)) {
                            res = (res + nums1[index1 + 1]) / 2
                        } else {
                            res = (res + nums2[index2]) / 2
                        }
                    }
                    return res
                }
                currentIndex++
                index1++
            } else {
                if (currentIndex == mid) {
                    var res = nums2[index2].toDouble()
                    if (!isObb) {
                        if ((index2 + 1) < nums2.size && (if (index1 < nums1.size) nums1[index1] else Int.MAX_VALUE) >= nums2[index2 + 1]) {
                            res = (res + nums2[index2 + 1]) / 2
                        } else {
                            res = (res + nums1[index1]) / 2
                        }
                    }
                    return res
                }
                currentIndex++
                index2++
            }
        }
        return 0.0
    }
}
```

