

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









递归kotlin版本

```kotlin
/**
 * Example:
 * var li = ListNode(5)
 * var v = li.`val`
 * Definition for singly-linked list.
 * class ListNode(var `val`: Int) {
 *     var next: ListNode? = null
 * }
 */
class Solution {
    var res: ListNode? = null
    fun reverseList(head: ListNode?): ListNode? {
        reverseListInternal(head, head?.next)

        return res
    }

    fun reverseListInternal(head: ListNode?, next: ListNode?) {
        if (next == null) {
            res = head
            return
        }

        reverseListInternal(head?.next, next?.next)
        next?.next = head
        head?.next = null
    }
}
```



迭代kotlin版本

```kotlin
/**
 * Example:
 * var li = ListNode(5)
 * var v = li.`val`
 * Definition for singly-linked list.
 * class ListNode(var `val`: Int) {
 *     var next: ListNode? = null
 * }
 */
class Solution {
    fun reverseList(head: ListNode?): ListNode? {
        var cur: ListNode? = head
        var pre: ListNode? = null

        while (cur != null) {
            val temNext = cur?.next

            cur?.next = pre
            pre = cur
            cur = temNext
        }

        return pre
    }
}
```

