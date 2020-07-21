##### 题目描述

给定一个整数 *n*，生成所有由 1 ... *n* 为节点所组成的 **二叉搜索树** 。

 

**示例：**

```
输入：3
输出：
[
  [1,null,3,2],
  [3,2,null,1],
  [3,1,null,null,2],
  [2,1,3],
  [1,null,2,null,3]
]
解释：
以上的输出对应以下 5 种不同结构的二叉搜索树：

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3
```

 

**提示：**

- `0 <= n <= 8`



##### 递归法

二叉搜索树的性质就是所有左子树小于等于根节点，所有右子树大于等于根节点，所以，选定一个节点后，可以将当前序列分为两半，例如，当n等于3时，节点是1 2 3 ，则当选择2作为根节点时，将1 2 3分为1 3 ，然后1就是一棵树。所以，可以先选择一个节点，然后，递归的求解剩余的小的序列，且从返回的左子树链表和右子树链表中，两两组合为当前节点的左右节点，最后就是一个完整的树。而递归结束的条件可以这么想，当start == end时，说明只有一个节点，那就是添加到链表中，返回，如果start < end ，那就是没有节点，就返回null。

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
    fun generateTrees(n: Int): List<TreeNode?> {
        if (n == 0) {
            return ArrayList<TreeNode?>()
        }

        return generateTree(1,n)

    }

    fun generateTree(start: Int,end: Int): List<TreeNode?> {
        val list = ArrayList<TreeNode?>()
        
        if (start > end) {
            list.add(null) 
            return list
        }
        
        for (i in start .. end) {
            val left = generateTree(start, i - 1)
            val right = generateTree(i + 1,end)
            
            for (l in left) {
                for (r in right) {
                    val curNode = TreeNode(i,l,r)
                    list.add(curNode)
                }
            }
        }
        
        return list
    }
}
```

