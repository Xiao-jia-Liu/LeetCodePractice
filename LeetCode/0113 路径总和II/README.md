##### 题目描述
给定一个二叉树和一个目标和，找到所有从根节点到叶子节点路径总和等于给定目标和的路径。

说明: 叶子节点是指没有子节点的节点。

示例:
给定如下二叉树，以及目标和 sum = 22，
```
输入：

  5
 / \
4   8
/   / \
11  13  4
/  \    / \
7    2  5   1



输出：

[
   [5,4,11,2],
   [5,8,4,5]
]

```


##### 题解

二叉树递归加栈回溯



python版本

```python
import numpy as np
class Solution(object):
    def pathSum(self, root, sum):
        """
        :type root: TreeNode
        :type sum: int
        :rtype: List[List[int]]
        """
        resultList = [] 

        def Sum(root,sumList):
            if root == None:
                return 0
            sumList.append(root.val)
            if root.left == None and root.right == None:
                if np.array(sumList).sum() == sum:
                    resultList.append(list(np.array(sumList)))
                sumList.pop()
                return 0 
            Sum(root.left,sumList)
            Sum(root.right,sumList)
            sumList.pop()
            return 0
        Sum(root,[])
        return resultList
```



