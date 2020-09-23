##### 题目描述：

给定两个二叉树，想象当你将它们中的一个覆盖到另一个上时，两个二叉树的一些节点便会重叠。

你需要将他们合并为一个新的二叉树。合并的规则是如果两个节点重叠，那么将他们的值相加作为节点合并后的新值，否则不为 NULL 的节点将直接作为新二叉树的节点。

```
输入: 
	Tree 1                     Tree 2                  
          1                         2                             
         / \                       / \                            
        3   2                     1   3                        
       /                           \   \                      
      5                             4   7                  
输出: 
合并后的树:
	     3
	    / \
	   4   5
	  / \   \ 
	 5   4   7

```

##### 解法思路：
二叉树基本操作，做一些子树判断下是否为空操作。一个空，另一个不为空就连接一下。二叉树遍历啥的就不提了，递归搞定。

python版本 2020.9.23
```python
class Solution(object):
    def mergeTrees(self, t1, t2):
        """
        :type t1: TreeNode
        :type t2: TreeNode
        :rtype: TreeNode
        """

        if t1==None and t2==None:
            return t1
        if t1!=None and t2==None:
            return t1
        if t1==None and t2!=None:
            return t2
        t1.val = t1.val+t2.val
        
        if t1.left == None and t2.left != None:
            t1.left = t2.left
            t2.left = None
        if t1.right == None and t2.right != None:
            t1.right = t2.right
            t2.right = None
        if t2.right == None and t1.right == None and t2.left == None and t1.left == None:
            return t1
        
        if t2.right == None and t1.right == None: 
            self.mergeTrees(t1.left, t2.left)
            return t1

        if t2.left == None and t1.left == None:
            self.mergeTrees(t1.right, t2.right)
            return t1        

        self.mergeTrees(t1.left, t2.left)
        self.mergeTrees(t1.right, t2.right)

        return t1
   
```

当我写完今天每日一题后，才发现这道题我已经ac了，并且写得罕见的优雅，是大二时写的。。

python版本 三年前
```python
class Solution(object):
    def mergeTrees(self, t1, t2):
        if t1 !=None and t2!=None:
            t1.val=t1.val+t2.val
            t1.left=self.mergeTrees(t1.left,t2.left)
            t1.right=self.mergeTrees(t1.right,t2.right)
            return t1
        elif t1==None and t2!=None:
            return t2
        elif t2==None and t1!=None: 
            return t1
        else:
            return None
```