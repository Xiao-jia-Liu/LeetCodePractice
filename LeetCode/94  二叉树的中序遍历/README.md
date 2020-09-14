##### 题目描述

给定一个二叉树，返回它的*中序* 遍历。

**示例:**

```
输入: [1,null,2,3]
   1
    \
     2
    /
   3

输出: [1,3,2]
```

**进阶:** 递归算法很简单，你可以通过迭代算法完成吗？





##### 递归算法

递归算法很简单，先访问左子树，访问当前节点，访问右子树

python版本
```python
class Solution(object):
    def inorderTraversal(self, root):

        result = []
        
        def inorder(root):
            if root == None:
                return 0
            if root.left != None and root.right != None:
                inorder(root.left)
                result.append(root.val)
                inorder(root.right)
            if root.left == None and root.right != None:
                result.append(root.val)
                inorder(root.right)
            if root.left != None and root.right == None:
                inorder(root.left)
                result.append(root.val)
            if root.left == None and root.right == None:
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
    	res.add(root.`val`)
    	traversalInternal(root.right)
    }
}
```





##### 迭代算法

用栈来实现，但是必须解决的问题是，左子树第一次进栈时，要有标志位标记，第二次遍历该节点时，判断左子树是否已经访问过，已经访问过则直接访问该节点，然后访问该几点的右子树



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
    fun inorderTraversal(root: TreeNode?): List<Int> {
        val res = ArrayList<Int>()
        val stack = ArrayList<Node>()

        if (root == null) {
            return res
        }

        stack.add(Node(root))

        while (!stack.isEmpty()) {
            val tem = stack[stack.lastIndex]
            stack.removeAt(stack.lastIndex)

            if (tem.`val`.left != null && !tem.hasAccessed) {
                stack.add(tem)
                tem.hasAccessed = true
                stack.add(Node(tem.`val`.left))
                continue
            }

            res.add(tem.`val`.`val`)

            if (tem.`val`.right != null) {
                stack.add(Node(tem.`val`.right))
                continue
            }
        }

        return res
    }

    class Node(val `val`: TreeNode){
        var hasAccessed = false
    }
}
```

