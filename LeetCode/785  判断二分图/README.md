##### 题目描述

给定一个无向图`graph`，当这个图为二分图时返回`true`。

如果我们能将一个图的节点集合分割成两个独立的子集A和B，并使图中的每一条边的两个节点一个来自A集合，一个来自B集合，我们就将这个图称为二分图。

`graph`将会以邻接表方式给出，`graph[i]`表示图中与节点`i`相连的所有节点。每个节点都是一个在`0`到`graph.length-1`之间的整数。这图中没有自环和平行边： `graph[i]` 中不存在`i`，并且`graph[i]`中没有重复的值。

```
示例 1:
输入: [[1,3], [0,2], [1,3], [0,2]]
输出: true
解释: 
无向图如下:
0----1
|    |
|    |
3----2
我们可以将节点分成两组: {0, 2} 和 {1, 3}。
示例 2:
输入: [[1,2,3], [0,2], [0,1,3], [0,2]]
输出: false
解释: 
无向图如下:
0----1
| \  |
|  \ |
3----2
我们不能将节点分割成两个独立的子集。
```

**注意:**

- `graph` 的长度范围为 `[1, 100]`。
- `graph[i]` 中的元素的范围为 `[0, graph.length - 1]`。
- `graph[i]` 不会包含 `i` 或者有重复的值。
- 图是无向的: 如果`j` 在 `graph[i]`里边, 那么 `i` 也会在 `graph[j]`里边。



##### 题解

因为要二分图，则相邻的两个节点一定不在同一个集合中。所以，遍历图，先将起始点染色成红色，然后，与起始点直接相连的染色成绿色，与绿色点直接相连的染色成红色，如此交替，直到全部染色结束或者在某一次时，将要染色的点已经有了颜色且颜色与将要染色的色不同

##### 深度优先

```kotlin
class Solution {
    var res = true
    val UNCOLOR = 0
    val RED = 1
    val GREEN = 2

    fun isBipartite(graph: Array<IntArray>): Boolean {
        val color = IntArray(graph.size){0}

        for (i in 0 until graph.size) {
            if (color[i] == UNCOLOR) {
                dfs(graph,i,RED,color)
            }
        }

        return res
    }

    fun dfs(graph: Array<IntArray>,node: Int,c: Int,color: IntArray) {
        val curColor = color[node]
        val nextColor = if (curColor == RED) GREEN else RED

        for (neighbor in graph[node]) {
            if (color[neighbor] == UNCOLOR) {
                color[neighbor] = nextColor
                dfs(graph,neighbor,curColor,color)

                if (!res) {
                    return
                }
            } else {
                if (color[neighbor] != nextColor) {
                    res = false
                    return
                }
            }
        }
    }
}
```





##### 深度优先

深度优先一样的道理，只是，需要一个队列来保存当前节点的邻接节点，因为需要一次性就全部处理完。代码今天不想写了，哪天有心情想起了再加