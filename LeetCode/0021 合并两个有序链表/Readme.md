##### 题目描述

将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。

**示例**

```
输入：1->2->4, 1->3->4
输出：1->1->2->3->4->4
```


**解题思路**

#####解法
四个月前写的，记不得了，应该跟合并两个升序数组差不多，归并排序思路。

只是链表这种数据结构稍微麻烦点。

##### 代码实现Python

```Python

"""
:type head: ListNode
:rtype: ListNode
"""

class Solution:
    def mergeTwoLists(self, l1: ListNode, l2: ListNode) -> ListNode:
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
```


