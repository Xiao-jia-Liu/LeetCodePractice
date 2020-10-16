##### 题目描述：
给定一个按非递减顺序排序的整数数组 A，返回每个数字的平方组成的新数组，要求也按非递减顺序排序。
```
输入：[-4,-1,0,3,10]
输出：[0,1,9,16,100]

输入：[-7,-3,2,3,11]
输出：[4,9,9,49,121]
```


##### 解法思路：
因为已经有序，首先找到分界线，然后利用双指针归并排序。

python版本
```python
class Solution(object):
    def sortedSquares(self, A):
        """
        :type A: List[int]
        :rtype: List[int]
        """
        n = len(A)
        if n == 0:
            return A
        index = 0
        while index < n and A[index] < 0:
            A[index] = -A[index]
            index = index + 1
        
        result = []
        flag1 = index-1
        flag2 = index
        while flag1 > -1 and flag2 <n:
            if A[flag1] < A[flag2]:
                result.append(A[flag1]**2)
                flag1 = flag1 - 1 
            else:
                result.append(A[flag2]**2)
                flag2 = flag2 +1
        if flag2 == n:
            while flag1 > -1:
                result.append(A[flag1]**2)
                flag1 = flag1 - 1 
        if flag1 == -1:
            while flag2 < n:
                result.append(A[flag2]**2)
                flag2 = flag2 +1
        return result

```


