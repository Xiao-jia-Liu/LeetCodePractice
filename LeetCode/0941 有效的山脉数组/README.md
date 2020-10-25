##### 题目描述：
给定一个整数数组 A，如果它是有效的山脉数组就返回 true，否则返回 false。

让我们回顾一下，如果 A 满足下述条件，那么它是一个山脉数组：

A.length >= 3
在 0 < i < A.length - 1 条件下，存在 i 使得：
A[0] < A[1] < ... A[i-1] < A[i]
A[i] > A[i+1] > ... > A[A.length - 1]

```
输入：[2,1]
输出：false

输入：[3,5,5]
输出：false

输入：[0,3,2,1]
输出：true
```


##### 解法思路：
首先找到极大值点，又根据山脉数组定义，极大值前的点是严格单调递增的，之后的点是严格单调递减的。

因此只需要根据极值点将数组分为两部分，分别检查两部分是否是严格单调递增（减）的。

python版本
```python
class Solution(object):
    def validMountainArray(self, A):
        """
        :type A: List[int]
        :rtype: bool
        """

        n = len(A)
        pole = 0
        for i in range(1,n-1):
            if A[i-1] < A[i] and A[i] > A[i+1]:
                pole = i
        if pole == 0:
            return False
        forward_list = A[0:pole+1]
        backward_list = A[pole+1:n]

        for i in range(len(forward_list)-1):
            if (forward_list[i+1] - forward_list[i])<=0:
                return False
        for i in range(len(backward_list)-1):
            if (backward_list[i+1] - backward_list[i])>=0:
                return False
        return True
```


