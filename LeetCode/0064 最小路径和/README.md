##### 题目描述：

给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。

说明：每次只能向下或者向右移动一步。

 

**示例：**

```
输入:
[
  [1,3,1],
  [1,5,1],
  [4,2,1]
]
输出: 7
解释: 因为路径 1→3→1→1→1 的总和最小。
```




##### 解法：动态规划
入门级动态规划，题目已经暗示得不能再明显了，但是只打败了5%的人我是没想通的，等有空了再研究研究。

```
:type grid: List[List[int]]
:rtype: int
```

```python
import numpy as np
class Solution(object):
    def minPathSum(self, grid):
        zero = np.array(grid)
        x = zero.shape[0]
        y = zero.shape[1]
        if x == 1 and y == 1:
            return zero[0,0]
        for i in range(1,x):
            zero[i,0] = zero[i-1,0] + zero[i,0]
        for j in range(1,y):
            zero[0,j] = zero[0,j-1] + zero[0,j]

        for i in range(1,x):
            for j in range(1,y):
                if zero[i-1,j] < zero[i,j-1]:
                    zero[i,j] = zero[i-1,j] + zero[i,j]
                else:
                    zero[i,j] = zero[i,j-1] + zero[i,j]
        return zero[x-1,y-1]
```







