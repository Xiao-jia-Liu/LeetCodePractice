##### 题目描述

给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。

k 是一个正整数，它的值小于或等于链表的长度。

如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。

**示例**

```
给你这个链表：1->2->3->4->5

当 k = 2 时，应当返回: 2->1->4->3->5

当 k = 3 时，应当返回: 3->2->1->4->5
```

说明：

你的算法只能使用常数的额外空间。
你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。


**解题思路**

#####解法
首先要封装一个翻转链表的函数，这个正好有这么一道题 （0206 翻转链表），然后就很简单了，
将现在的链表划分成 n/k + 1 段，前n/k段调用翻转链表函数，然后再把所有段连接起来，代码
写得比较ugly，但是毕竟是人生第一道困难难度的题。




##### 代码实现Python

```Python

"""
:type head: ListNode
:rtype: ListNode
"""

class Solution(object):
    def reverseList(self, head):
        if head == None:
            return head
        NodeList = []
        Node = head
        while Node != None:
            NodeList.append(ListNode(Node.val))
            Node = Node.next

        rHead = NodeList.pop()
        Node = rHead
        while len(NodeList)!=0:
            tempNode = NodeList.pop()
            Node.next = tempNode
            Node = Node.next
        return rHead

    def reverseKGroup(self, head, k):
        temp = head 
        linklist = []
        while temp!= None :
            for i in range(k-1):
                temp = temp.next
                if temp  == None:
                    break
            if temp !=None:
                temphead = temp.next
                temp.next = None
                linklist.append(head)
                temp = temphead
                head = temp
        
        linklistNew = []
        for i in range(len(linklist)):
            linklistNew.append(self.reverseList(linklist[i]))
        linklistNew.append(head)

        for i in range(len(linklistNew)-1):
            temphead = linklistNew[i]
            for j in range(k-1):
                temphead = temphead.next
            temphead.next = linklistNew[i+1]
        return linklistNew[0]
```


