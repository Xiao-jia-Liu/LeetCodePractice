##### 题目描述

给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和。

**说明:** 叶子节点是指没有子节点的节点。

**示例:** 
给定如下二叉树，以及目标和 `sum = 22`，

```
              5
             / \
            4   8
           /   / \
          11  13  4
         /  \      \
        7    2      1
```

返回 `true`, 因为存在目标和为 22 的根节点到叶子节点的路径 `5->4->11->2`。

##### 递归解法

递归遍历树，计算从根节点到叶子节点的路径总和，等于就返回。优点简单，缺点，函数调用开销大，所以利用栈或者队列来去除递归

```kotlin
/**
 * Example:
 * var ti = TreeNode(5)
 * var v = ti.`val`
 * Definition for a binary tree node.
 * class TreeNode(var `val`: Int) {
 *     var left: TreeNode? = null
 *     var right: TreeNode? = null
 * }
 */
class Solution {
    fun hasPathSum(root: TreeNode?, sum: Int): Boolean {
        return calCount(root,0,sum)
    }

    fun calCount(root: TreeNode?,sum: Int,target: Int): Boolean {
        if (root == null) {
            return false
        }

        if (root.`val` + sum == target && root.left == null && root.right == null) {
            return true
        }

        return calCount(root.left,root.`val` + sum,target) || calCount(root.right,root.`val` + sum,target)

    }
}
```



##### 用队列降低复杂度

将递归转换成队列或者栈，此处用队列。先构造一个结构，其中存储当前的节点以及当前节点到根节点的和

```kotlin
/**
 * Example:
 * var ti = TreeNode(5)
 * var v = ti.`val`
 * Definition for a binary tree node.
 * class TreeNode(var `val`: Int) {
 *     var left: TreeNode? = null
 *     var right: TreeNode? = null
 * }
 */
class Solution {
    fun hasPathSum(root: TreeNode?, sum: Int): Boolean {
        return calCount(root,sum)
    }

    fun calCount(root: TreeNode?,target: Int): Boolean {
        val queue = LinkedList<Node>()

        if (root == null) {
            return false
        }

        queue.push(Node(root,0))


        while (!queue.isEmpty()) {
            val cur = queue.pop() ?: continue

            if (cur.v.left == null && cur.v.right == null && cur.sum + cur.v.`val` == target) {
                return true
            }

            if (cur.v.left == null && cur.v.right == null) {
                continue
            }

            if (cur.v.left != null) {
                queue.push(Node(cur.v.left!!,cur.sum + cur.v.`val`))
            }

            if (cur.v.right != null) {
                queue.push(Node(cur.v.right!!,cur.sum + cur.v.`val`))
            }
        }

        return false
    }

    data class Node(val v: TreeNode,var sum: Int = 0)
}
```

