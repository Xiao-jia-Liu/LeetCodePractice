##### 题目描述：
我们把数组 A 中符合下列属性的任意连续子数组 B 称为 “山脉”：

B.length >= 3
存在 0 < i < B.length - 1 使得 B[0] < B[1] < ... B[i-1] < B[i] > B[i+1] > ... > B[B.length - 1]
（注意：B 可以是 A 的任意子数组，包括整个数组 A。）

给出一个整数数组 A，返回最长 “山脉” 的长度。

如果不含有 “山脉” 则返回 0。

```
输入：[2,1,4,7,3,2,5]
输出：5
解释：最长的 “山脉” 是 [1,4,7,3,2]，长度为 5。

输入：[2,2,2]
输出：0
解释：不含 “山脉”。
```


##### 解法思路：

######思路：

做这题之前，先做了941 有效的山脉数组。941是解这道题的基础。

拿到题，简单判断一下可能的情况。很自然的想法是找到两个低谷，即极小值点(他们之间必有极大值，参考罗尔中值定理)，

计算所有极小值点的长度取最大的。根据这个思路，似乎再考虑一下只有一个极小值点的情况就秒了这题。

然而罗尔中值定理前提是函数要闭区间连续，开区间可导。但是明显存在着区间内不可导的情况。

比如 [1 1 2 3 3 3 5 5]，可以找到两个"低谷"（非严格意义上的极小值），分别是index为1的元素1，和index为5的元素3。

但这个区间没有山脉。于是在原有的找低谷的框架下，要增加一个判断山脉是否有效的函数，也就是题目941。

######解法：
第一次遍历列表，找到所有k可能的低谷（A[i] <= A[i+1] && A[i] <= A[i-1]，但不同时取等）

和可能的山峰(A[i] >= A[i+1] && A[i] >= A[i-1])。

然后第二次遍历，把列表分为由低谷组成的子区间，判断子区间是否是有效的山脉数组，找最长的山脉。

注意细节，一是被低谷点划分出的区间要包括第一个低谷出现前的区间，和最后一个低谷出现后的区间。这两个区间要注意是不是山脉。

二是在只有一个极小值，或没有极小值点时，也是可能有山脉的，这是要辅助极大值进行判断。


python版本
```python
import numpy as np
class Solution(object):
    def validMountainArray(self,A):
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

    def longestMountain(self,A):

        n = len(A)
        extreme_min = []
        extreme_max = []
        for i in range(1,n-1):
            if A[i-1] == A[i] and A[i+1] == A[i]:
                continue
            if A[i-1] >= A[i] and A[i] <= A[i+1]:
                extreme_min.append(i)
            if A[i-1] <= A[i] and A[i] >= A[i+1]:
                extreme_max.append(i)
        min_num = len(extreme_min)
        max_num = len(extreme_max)

        if min_num == 0 and max_num == 0:
            return 0
        elif min_num == 0 and max_num == 1:
            num = extreme_max[0]
            if A[num-1] < A[num] and A[num] > A[num+1]:
                return n
            else:
                return 0
        elif max_num == 0:
            return 0
        else:
            extreme_min.insert(0,0)
            extreme_min.append(n-1)
            extreme_diff = []
            for j in range(min_num+1):
                mountain_list = A[extreme_min[j]:extreme_min[j+1]+1]
                if self.validMountainArray(mountain_list)==True:
                    extreme_diff.append(extreme_min[j+1]-extreme_min[j])

            if len(extreme_diff) == 0:
                return 0
            else:
                return max(extreme_diff)+1
```


