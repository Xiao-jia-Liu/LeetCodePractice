##### 题目描述

给定一个二叉树，返回它的 后序 遍历。

**示例:**

```
输入: [1,null,2,3]  
   1
    \
     2
    /
   3 

输出: [3,2,1]
```

**进阶:** 递归算法很简单，你可以通过迭代算法完成吗？





##### 递归算法

递归算法很简单，先访问左子树，访问当前节点，访问右子树

python版本
```python
class Solution(object):
    def postorderTraversal(self, root):
        result = []
    
        def inorder(root):
            if root == None:
                return 0
            inorder(root.left)
            inorder(root.right)
            result.append(root.val)
            return 0
        inorder(root)
        return result
```

kotlin版本
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
    val res: List<Int> = List<Int>()
    
    fun inorderTraversal(root: TreeNode?): List<Int> {
		
    }
    
    fun traversalInternal(root: TreeNode?) {
    	if (root == null) {
    		return 
    	}
    	
    	traversalInternal(root.left)
    	traversalInternal(root.right)
		res.add(root.`val`)
    }
}
```




