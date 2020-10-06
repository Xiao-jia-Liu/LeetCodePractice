##### 题目描述

给定一个包含 m x n 个元素的矩阵（m 行, n 列），请按照顺时针螺旋顺序，返回矩阵中的所有元素。

**示例:**

```
示例 1:
输入:
[
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]
输出: [1,2,3,6,9,8,7,4,5]

示例 2:
输入:
[
  [1, 2, 3, 4],
  [5, 6, 7, 8],
  [9,10,11,12]
]
输出: [1,2,3,4,8,12,11,10,9,5,6,7]
```

##### 题解

第一次见到这个题，是大二的数据结构oj，当时是n*n的方阵，我大概做了一个晚上。

今天把相当于把当时的思路复制了一遍，只是注意m*n后通过设置计数器来保证添加m*n个数后终止。

很简单但有点麻烦的题。

从外圈到内圈层层遍历，每一圈注意下标设置，以前我纯靠想，这次直接调试

节省不少时间。



python版本：

```python
import numpy as np
class Solution(object):
    def spiralOrder(self, matrix):
        if len(matrix) == 0:
            return []
        matrix_array = np.array(matrix)

        m = matrix_array.shape[1]
        n = matrix_array.shape[0]

        result = []
         
        for i in range(n,int(n/2),-1):
            c = n-i //圈数
            for j in range(c,m-c):  //向右遍历
                result.append(matrix[c][j])
                if len(result) == m*n:
                    return result
                
            for j in range(c,i-1):  //向下遍历
                result.append(matrix[j+1][m-c-1])
                if len(result) == m*n:
                    return result
                
            for j in range(m-c-1,c,-1):  //向左遍历
                result.append(matrix[i-1][j-1])
                if len(result) == m*n:
                    return result
                
            for j in range(i-1,c+1,-1):  //向上遍历
                result.append(matrix[j-1][c])
                if len(result) == m*n:
                    return result
        
        return result
```

