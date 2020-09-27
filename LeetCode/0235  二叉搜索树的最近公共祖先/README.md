##### 题目描述

给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。

[百度百科](https://baike.baidu.com/item/最近公共祖先/8918834?fr=aladdin)中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（**一个节点也可以是它自己的祖先**）。”

例如，给定如下二叉搜索树: root = [6,2,8,0,4,7,9,null,null,3,5]

![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/12/14/binarysearchtree_improved.png)

 

**示例 1:**

```
输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
输出: 6 
解释: 节点 2 和节点 8 的最近公共祖先是 6。
```

**示例 2:**

```
输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
输出: 2
解释: 节点 2 和节点 4 的最近公共祖先是 2, 因为根据定义最近公共祖先节点可以为节点本身。
```

 

**说明:**

- 所有节点的值都是唯一的。
- p、q 为不同节点且均存在于给定的二叉搜索树中。





##### 暴力

比较简单，先找到一个节点的，且记录沿途的父节点，然后在从最近的父节点开始找是否有第二个节点，有则直接返回该父节点则ok



```kotlin
/**
 * Definition for a binary tree node.
 * class TreeNode(var `val`: Int = 0) {
 *     var left: TreeNode? = null
 *     var right: TreeNode? = null
 * }
 */

class Solution {
    val parent = ArrayList<TreeNode?>()
    fun lowestCommonAncestor(root: TreeNode?, p: TreeNode?, q: TreeNode?): TreeNode? {
        if (root == null) {
            return null
        }

        var next = root
        var pVal = p!!.`val`

        while (next != null && next.`val` != pVal) {
            parent.add(next)
            
            if (next.`val` < pVal) {
                next = next.right
            } else {
                next = next.left
            }
        }

        parent.add(p)

        val size = parent.size
        val qVal = q!!.`val`

        for (i in size - 1 downTo 0) {
            var cur = parent[i]

            while (cur != null) {
                if (cur!!.`val` == qVal) {
                    return parent[i]
                } else if (cur!!.`val` < qVal) {
                    cur = cur!!.right
                } else {
                    cur = cur!!.left
                }
            }
        }

        return null
    }
}
```

##### 一次遍历解法



同时遍历p和q，如果当前节点同时大于p与 q，则表示p和q都在当前节点的左子树，说明还不是共同祖先，则向左移，反之，如果同时小于p与q，则说明p，q在右子树，向右移，当一个在左一个在右时，就是公共祖先了

```kotlin
/**
 * Definition for a binary tree node.
 * class TreeNode(var `val`: Int = 0) {
 *     var left: TreeNode? = null
 *     var right: TreeNode? = null
 * }
 */

class Solution {
    fun lowestCommonAncestor(root: TreeNode?, p: TreeNode?, q: TreeNode?): TreeNode? {
        var tem = root

        while (true) {
            if (tem!!.`val` > p!!.`val` && tem!!.`val` > q!!.`val`) {
                tem = tem?.left
            } else if (tem!!.`val` < p!!.`val` && tem!!.`val` < q!!.`val`) {
                tem = tem?.right
            } else {
                break
            }
        }

        return tem
    }
}
```

