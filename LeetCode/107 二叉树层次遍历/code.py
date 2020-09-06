class Solution(object):
    def levelOrderBottom(self, root):
        """
        :type root: TreeNode
        :rtype: List[List[int]]
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
                result_list.append(temp_list)
        real_list = []
        n = len(result_list)
        for j in range(n):
            real_list.append(result_list[n-j-1])
        return real_list