##### 题目描述

给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。

**示例**

```
给定一个链表: 1->2->3->4->5, 和 n = 2.

当删除了倒数第二个节点后，链表变为 1->2->3->5.

```

**说明**

```
给定的n保证是有效的
```

**进阶**

```
你能尝试用一趟扫描实现吗？
```


**解题思路**

#####解法

初级版解法：第一次遍历计数，第二次遍历找到要删除节点删掉。注意如果是删除头结点的情况。

进阶版解法：快慢指针，慢指针始终差快指针n的距离。然后删掉。

##### 代码实现Python 初级版

```Python

"""
:type head: ListNode
:rtype: ListNode
"""

class Solution(object):
    def removeNthFromEnd(self, head, n):
        """
        :type head: ListNode
        :type n: int
        :rtype: ListNode
        """
        if head == None or n == 0:
            return head

        flag_fast = head
        flag_slow = head
        index = 0
        while flag_fast.next != None:
            flag_fast = flag_fast.next
            index = index + 1
        for i in range(index-n):
            flag_slow = flag_slow.next
        
        if index + 1 == n:
            head = head.next
            return head

        flag_slow.next = flag_slow.next.next
        return head
```

##### 代码实现Python 进阶版

```Python

"""
:type head: ListNode
:rtype: ListNode
"""

class Solution(object):
    def removeNthFromEnd(self, head, n):
		if head == None or n == 0:
            return head

        flag_fast = head
        flag_slow = head
        index = 0
        while flag_fast.next != None:
            if index >= n:
                flag_slow = flag_slow.next
            flag_fast = flag_fast.next
            index = index + 1
        
        if index + 1 == n:
           head = head.next
           return head

        flag_slow.next = flag_slow.next.next
        return head

```





快慢指针kotlin版

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
    fun removeNthFromEnd(head: ListNode?, n: Int): ListNode? {
        var after = head
        var before = head
        var par = head

        repeat(n) {
            before = before?.next
        }

        while (before != null) {
            if (before?.next == null) {
                par = after
            }

            before = before?.next
            after = after?.next

            
        }

        if (par == after) {
            return head?.next
        }

        par?.next = after?.next
        return head
    }
}
```

