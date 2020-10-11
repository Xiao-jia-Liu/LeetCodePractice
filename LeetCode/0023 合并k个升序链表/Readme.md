##### 题目描述

给你一个链表数组，每个链表都已经按升序排列。

请你将所有链表合并到一个升序链表中，返回合并后的链表。

**示例**

```
输入：lists = [[1,4,5],[1,3,4],[2,6]]
输出：[1,1,2,3,4,4,5,6]
解释：链表数组如下：
[
  1->4->5,
  1->3->4,
  2->6
]
将它们合并到一个有序链表中得到。
1->1->2->3->4->4->5->6
```


**解题思路**

#####解法
在合并两个链表基础上，简单写两个实现首尾合并的循环。

##### 代码实现Python

```Python

"""
:type head: ListNode
:rtype: ListNode
"""

class Solution(object):
        """
        :type lists: List[ListNode]
        :rtype: ListNode
        """

    def mergeTwoLists(self, l1, l2):
        if l1==None and l2==None:
            return None
        elif l1!=None and l2==None:
            lresult = ListNode(l1.val)
            head = lresult
            l1 =l1.next    
        elif l1==None and l2!=None:
            lresult = ListNode(l2.val)
            head = lresult
            l2 =l2.next
        else:
            if l1.val >= l2.val:
                lresult = ListNode(l2.val)
                head = lresult
                l2 =l2.next
            else:
                lresult = ListNode(l1.val)
                head = lresult
                l1 =l1.next
        while (l1!=None and l2!=None):
            if l1.val >= l2.val:
                temp = ListNode(l2.val,None)
                lresult.next = temp
                l2 = l2.next
            else:
                temp = ListNode(l1.val,None)
                lresult.next = temp
                l1 = l1.next
            lresult = lresult.next
        if l1 == None:
            while l2!=None:
                temp = ListNode(l2.val,None)
                lresult.next = temp
                l2 = l2.next
                lresult = lresult.next
        if l2 == None:
            while l1!=None:
                temp = ListNode(l1.val,None)
                lresult.next = temp
                l1 = l1.next
                lresult = lresult.next
        return head

    def mergeKLists(self, lists):
        n = len(lists)
        #开始循环，直到合并结束
        if n == 0:
            return None
        while n > 1:
            mid = n/2
            temp_list = []
            #首尾两两合并
            for i in range(mid):
                node = self.mergeTwoLists(lists[i],lists[n-i-1])
                temp_list.append(node)
            if n % 2 == 1:
                temp_list.append(lists[mid])
            lists = temp_list
            n = len(lists)
        return lists[0]
```


