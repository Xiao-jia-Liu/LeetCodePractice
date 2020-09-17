##### 题目描述

在本问题中，有根树指满足以下条件的**有向**图。该树只有一个根节点，所有其他节点都是该根节点的后继。每一个节点只有一个父节点，除了根节点没有父节点。

输入一个有向图，该图由一个有着N个节点 (节点值不重复1, 2, ..., N) 的树及一条附加的边构成。附加的边的两个顶点包含在1到N中间，这条附加的边不属于树中已存在的边。

结果图是一个以`边`组成的二维数组。 每一个`边` 的元素是一对 `[u, v]`，用以表示**有向**图中连接顶点 `u` 和顶点 `v` 的边，其中 `u` 是 `v` 的一个父节点。

返回一条能删除的边，使得剩下的图是有N个节点的有根树。若有多个答案，返回最后出现在给定二维数组的答案。

**示例 1:**

```
输入: [[1,2], [1,3], [2,3]]
输出: [2,3]
解释: 给定的有向图如下:
  1
 / \
v   v
2-->3
```

**示例 2:**

```
输入: [[1,2], [2,3], [3,4], [4,1], [1,5]]
输出: [4,1]
解释: 给定的有向图如下:
5 <- 1 -> 2
     ^    |
     |    v
     4 <- 3
```

**注意:**

- 二维数组大小的在3到1000范围内。
- 二维数组中的每个整数在1到N之间，其中 N 是二维数组的大小。





##### 暴力破解法



每次都从后往前去除一个边，然后判断移除该边后的树是不是有根树，所以主要的难点是判断一个图是不是有根树。判断的方法为：先找出根节点，然后根据根节点广度优先遍历或者深度优先遍历，同时，在遍历的途中标记该节点，如果该节点已经有了父节点则标记，当下次遍历再次遍历到该节点为子节点时则表示不是有根树



kotlin版本为：

```kotlin
class Solution {
    val nodeSet = HashSet<Int>()

    fun findRedundantDirectedConnection(edges: Array<IntArray>): IntArray {
        val res = IntArray(2) {0}
        val size = edges.size

        val tem = edges.toList() as ArrayList<IntArray>

        for (index in size - 1 downTo 0) {
            val edge = tem.removeAt(index)
            if (isRootTree(tem)) {
                res[0] = edges[index][0]
                res[1] = edges[index][1]
                return res
            }
            tem.add(index, edge)
        }

        return res
    }

    fun isRootTree(edges: ArrayList<IntArray>): Boolean {
        val temNodeSet = HashSet<Int>()
        val temHasFarther = HashMap<Int,Boolean>()

        for (edge in edges) {
            temNodeSet.add(edge[0])
            temNodeSet.add(edge[1])
            temHasFarther[edge[0]] = false
            temHasFarther[edge[1]] = false
        }

        for (edge in edges) {
            //1 means is pointed to
            temNodeSet.remove(edge[1])
        }

        //if temNodeSet's size not equals one ,means there is no root
        //or more than one root,return false
        if (temNodeSet.size != 1) {
            return false
        }

        //we have found the root node,start find if it is a tree
        val root = temNodeSet.first()
        return recursion(edges, root, temHasFarther)
    }

    fun recursion(edges: ArrayList<IntArray>,root: Int,hasFarther: HashMap<Int,Boolean>): Boolean {
        val queue = ArrayList<Int>()
        queue.add(root)
        hasFarther[root] = true

        while (!queue.isEmpty()) {
            val root = queue.first()
            queue.removeAt(0)

            for (edge in edges) {
                if (edge[0] == root) {
                    queue.add(edge[1])

                    if (hasFarther[edge[1]] == true) {
                        return false
                    }

                    hasFarther[edge[1]] = true
                }
            }
        }

        return !hasFarther.values.contains(false)
    }
}
```





