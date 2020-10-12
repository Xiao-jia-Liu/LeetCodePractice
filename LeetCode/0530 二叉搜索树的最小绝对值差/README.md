##### 题目描述

给你一棵所有节点为非负值的二叉搜索树，请你计算树中任意两节点的差的绝对值的最小值。

 

**示例：**

```
输入：

   1
    \
     3
    /
   2

输出：
1

解释：
最小绝对差为 1，其中 2 和 1 的差的绝对值为 1（或者 2 和 3）。
```

 

**提示：**

- 树中至少有 2 个节点。
- 本题与 783 https://leetcode-cn.com/problems/minimum-distance-between-bst-nodes/ 相同





##### 中序遍历

二叉搜索树中序遍历是一个有序的升序数组，而且，最小值之差一定出现在两个相邻的元素间，所以，只需要中序遍历，然后取中序遍历时的两元素的最小差值就行

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
    var res = Int.MAX_VALUE
    var pre = -1

    fun getMinimumDifference(root: TreeNode?): Int {
        recursion(root)
        return res
    }

    fun recursion(root: TreeNode?) {
        if (root == null) {
            return
        }

        recursion(root?.left)
        
        if (pre == -1) {
            pre = root?.`val`
        } else {
            res = Math.min(res, root?.`val` - pre)
            pre = root?.`val`
        }

        recursion(root?.right)
    }
}
```

