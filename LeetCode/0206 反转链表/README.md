##### 题目描述

反转一个单链表。

**示例:**

```
输入: 1->2->3->4->5->NULL
输出: 5->4->3->2->1->NULL
```

##### 题解

第一次遍历拿一个栈记录一下，第二次遍历生成新的链表。

python版本
```python
class Solution(object):
    def reverseList(self, head):
        """
        :type head: ListNode
        :rtype: ListNode
        """
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
```


