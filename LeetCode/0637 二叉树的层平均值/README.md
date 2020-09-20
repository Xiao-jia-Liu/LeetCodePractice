##### 题目描述：

给定一个非空二叉树, 返回一个由每层节点平均值组成的数组。

例如：
给定二叉树 [3,9,20,null,null,15,7],

```
输入：
    3
   / \
  9  20
    /  \
   15   7
输出：[3, 14.5, 11]
解释：
第 0 层的平均值是 3 ,  第1层是 14.5 , 第2层是 11 。因此返回 [3, 14.5, 11] 。

```


##### 解法思路：
构造一个循环队列，遍历第i层时，从队列中获取到第i层的父节点，然后将其所有子节点加入队列，完成后从队列中删除父节点。不断重复此操作，直到队列为空。
上述步骤能完成层次遍历，还需要通过计数操作将每一层节点分离。通过两个标志，分别记录父节点数量n1和子节点数量n2。遍历父节点时，n2初始化为2*n1，若其左孩子为null，则n2减一，以此类推。每次父节点离开队列时，n1减一。当n1为0时，即所有父节点都已遍历完时，赋值n1为n2，n2重新置为0。即开始新的循环，将这一层的孩子节点作为下一层的父节点。
最后计算每一层的平均值。（实际是在上一次二叉树层次遍历上修改了一点点代码）

python版本
```python
import numpy as np
class Solution(object):
    def averageOfLevels(self, root):
        """
        :type root: TreeNode
        :rtype: List[float]
        """
        if root==None:
            return []
        root_list = []
        root_list.append(root)
        num_father = 1
        num_son = 0
        result_list = []
        while root_list !=[]:
            if num_son == 0:
                index = num_father * 2
                temp_list = [] 
            temp_list.append(root_list[0].val)
            temp_root = root_list[0]
            if temp_root.left!=None:
                root_list.append(temp_root.left)
            else:
                index = index -1
            if temp_root.right!=None:
                root_list.append(temp_root.right)
            else:
                index = index -1
            
            num_son = index
            root_list.pop(0)
            num_father = num_father - 1

            if num_father == 0:
                num_father = num_son
                num_son = 0
                result_list.append(np.array(temp_list).mean())

        return result_list   
```


