##### 题目描述
给定一个包含 0 和 1 的二维网格地图，其中 1 表示陆地 0 表示水域。

网格中的格子水平和垂直方向相连（对角线方向不相连）。整个网格被水完全包围，但其中恰好有一个岛屿（或者说，一个或多个表示陆地的格子相连组成的岛屿）。

岛屿中没有“湖”（“湖” 指水域在岛屿内部且不和岛屿周围的水相连）。格子是边长为 1 的正方形。网格为长方形，且宽度和高度均不超过 100 。计算这个岛屿的周长。


**示例：**

```
输入:
[[0,1,0,0],
 [1,1,1,0],
 [0,1,0,0],
 [1,1,0,0]]

输出: 16

解释: 它的周长是下面图片中的 16 个黄色的边：
```

##### brute force解法
初始化计数器为0。扫描每个元素，如果元素为0，直接跳过；如果元素为1，计数器加四，

然后判断元素的上下左右是否为1，只要有一个方向为1计数器就减一。计数器最后的结果就是答案。

```python
import numpy as np
class Solution(object):
    def islandPerimeter(self, grid):
        """
        :type grid: List[List[int]]
        :rtype: int
        """
        array = np.array(grid)
        x = array.shape[0]
        y = array.shape[1]
        count = 0
        for i in range(x):
            for j in range(y):
                if array[i][j] == 1:
                    count = count + 4
                else:
                    continue
                #搜索上邻居
                if i-1>=0:
                    if array[i-1][j] == 1:
                        count = count -1
                #搜索下邻居
                if i+1 < x:
                    if array[i+1][j] == 1:
                        count = count -1
                #搜索左邻居
                if j-1>=0:
                    if array[i][j-1] == 1:
                        count = count -1 
                #搜索右邻居
                if j+1 <y:
                    if array[i][j+1] == 1:
                        count = count -1
        return count
```

