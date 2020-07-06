##### 题目描述

一个机器人位于一个 *m x n* 网格的左上角 （起始点在下图中标记为“Start” ）。

机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。

现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？

![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/10/22/robot_maze.png)

网格中的障碍物和空位置分别用 `1` 和 `0` 来表示。

**说明：** *m* 和 *n* 的值均不超过 100。

**示例 1:**

```
输入:
[
  [0,0,0],
  [0,1,0],
  [0,0,0]
]
输出: 2
解释:
3x3 网格的正中间有一个障碍物。
从左上角到右下角一共有 2 条不同的路径：
1. 向右 -> 向右 -> 向下 -> 向下
2. 向下 -> 向下 -> 向右 -> 向右
```





##### 递归暴力解法

很容易发现，走一步之后，剩下的还是一个完全一样的子问题，而最终判断条件就是，如果最后能到达终点，那结果就 +1 。

```kotlin
class Solution {
    var count = 0

    fun uniquePathsWithObstacles(obstacleGrid: Array<IntArray>): Int {
        findPath(obstacleGrid, 0, 0)
        return count
    }

    fun findPath(grid: Array<IntArray>,x: Int,y: Int): Boolean {
        if (grid.size == 0 || x >= grid.size || y >= grid[0].size) {
            return true
        }

        if (x == grid.size - 1 && y == grid[0].size -1 && grid[x][y] != 1) {
            count++
            return true
        }

        if (grid[x][y] == 1) {
            return true
        }

        return findPath(grid,x + 1,y) && findPath(grid,x,y + 1)
    }
}
```

但是会超出时间限制

##### 利用队列来降级复杂度

别问，寻路问题问就是队列，将当前走了之后的下一步能走的位置放入队列，最后能到的位置，计数就 +1

```kotlin
class Solution {
    fun uniquePathsWithObstacles(obstacleGrid: Array<IntArray>): Int {
        val queue = LinkedList<Point>()
        var count = 0

        if (obstacleGrid.isEmpty() || obstacleGrid[0].isEmpty()) {
            return 0
        }

        queue.push(Point(obstacleGrid[0][0],0,0))

        while (!queue.isEmpty()) {
            val cur = queue.first
            queue.pop()

            if (cur.v == 1) {
                continue
            } else {

                //check
                if (cur.x + 1 == obstacleGrid.size && cur.y + 1 == obstacleGrid[0].size && obstacleGrid[cur.x][cur.y] != 1) {
                    count++
                    continue
                }


                //down
                if (cur.x + 1 >= obstacleGrid.size || obstacleGrid[cur.x + 1][cur.y] == 1) {

                } else {

                    queue.push(Point(obstacleGrid[cur.x + 1][cur.y], cur.x + 1, cur.y))
                }

                //right
                if (cur.y + 1 >= obstacleGrid[cur.x].size || obstacleGrid[cur.x][cur.y + 1] == 1) {

                } else {
                    queue.push(Point(obstacleGrid[cur.x][cur.y + 1], cur.x, cur.y + 1))
                }
            }

        }

        return count
    }
}

data class Point(val v: Int,val x: Int,val y: Int)
```

尼玛，写完提交才发现，超时了，一开始分析错了，一开始分析，每个元素最多入队列出队列一次，这是不对的，右和下，会同时添加一个元素，然后，前面的处理到等于此时的值时，又会再添加，时间复杂度不是O(mn)，是

O(2 ^ n ),肯定爆炸



##### 动态规划

后续补充，都0:38了