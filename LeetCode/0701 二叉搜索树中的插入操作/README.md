##### 题目描述

给定二叉搜索树（BST）的根节点和要插入树中的值，将值插入二叉搜索树。 返回插入后二叉搜索树的根节点。 输入数据保证，新值和原始二叉搜索树中的任意节点值都不同。

注意，可能存在多种有效的插入方式，只要树在插入后仍保持为二叉搜索树即可。 你可以返回任意有效的结果。

**示例:**

```
例如：

给定二叉搜索树:

        4
       / \
      2   7
     / \
    1   3

和 插入的值: 5

你可以返回这个二叉搜索树:

         4
       /   \
      2     7
     / \   /
    1   3 5


```


##### 题解
递归遍历，如果比根节点大就走右子树，如果比根节点小就走左子树，子树根节点为空的时候插入val值。


python版本

```python
class Solution(object):
    def insertIntoBST(self, root, val):
        x = TreeNode(val)
        if root == None:
            root =x
            return root
        if val > root.val:
            if root.right != None:
                self.insertIntoBST(root.right, val)
            else:
                root.right = x
        else:
            if root.left != None:
                self.insertIntoBST(root.left, val)
            else:
                root.left = x
        return root
```



