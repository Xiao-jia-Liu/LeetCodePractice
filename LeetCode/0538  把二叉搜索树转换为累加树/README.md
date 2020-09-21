##### 题目描述



给定一个二叉搜索树（Binary Search Tree），把它转换成为累加树（Greater Tree)，使得每个节点的值是原来的节点值加上所有大于它的节点值之和。

 

**例如：**

```
输入: 原始二叉搜索树:
              5
            /   \
           2     13

输出: 转换为累加树:
             18
            /   \
          20     13
```





##### 递归

二叉搜索树的中序遍历是一个递增数列，那么反向中序遍历就是一个递减数列，反向递归的同时累加和，然后写入节点就是最终的结果

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
    var sum = 0

    fun convertBST(root: TreeNode?): TreeNode? {
        value(root)
        return root
    }

    fun value(root: TreeNode?) {
        if (root == null) {
            return
        }

        value(root!!.right)
        sum += root!!.`val`
        root!!.`val` = sum
        value(root!!.left)
    }
}
```

