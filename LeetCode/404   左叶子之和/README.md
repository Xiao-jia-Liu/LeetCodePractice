##### 题目描述

计算给定二叉树的所有左叶子之和。

**示例：**

```
    3
   / \
  9  20
    /  \
   15   7

在这个二叉树中，有两个左叶子，分别是 9 和 15，所以返回 24
```





##### 递归解法

递归遍历树，在是左子树且是叶子节点时就加和



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
    var res = 0

    fun sumOfLeftLeaves(root: TreeNode?): Int {
        tranverseInternal(root, false)
        return res
    }

    fun tranverseInternal(root: TreeNode?,isLeft: Boolean) {
        if (root == null) {
            return
        }

        if (root.left == null && root.right == null && isLeft) {
            res += root.`val`
            return
        }

        tranverseInternal(root.left,true)
        tranverseInternal(root.right,false)
    }
}
```

