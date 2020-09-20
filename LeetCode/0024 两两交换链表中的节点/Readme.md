##### 题目描述

给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。

你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。

 

**示例**

```
给定 1->2->3->4, 你应该返回 2->1->4->3.

```

**解题思路**

#####解法
一次插入，一次删除。具体内容看代码。



##### 代码实现Python

```Python

"""
:type head: ListNode
:rtype: ListNode
"""

class Solution(object):
    def swapPairs(self, head):
        if head == None or head.next ==None:
            return head
        temp = ListNode(head.val)
        temp.next = head.next.next
        head.next.next = temp
        head = head.next
        result_head = head.next.next
        result_flag = head.next
        while result_head !=None and result_head.next !=None:
            temp = ListNode(result_head.val)
            temp.next = result_head.next.next
            result_head.next.next = temp

            temp2 = result_flag.next
            result_flag.next = result_flag.next.next
            temp2.next = None

            result_flag=result_flag.next.next
            result_head=result_flag.next

        return head

```





kotlin代码

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
    fun swapPairs(head: ListNode?): ListNode? {
        if (head == null || head.next == null) {
            return head
        }
        
        var first: ListNode? = head
        var second: ListNode? = head.next
        var swap: ListNode? = head.next.next
        var last: ListNode? = null
        var res: ListNode? = null
        
        while (true) {
            if (last != null) {
                last.next = second
            }

            last = first
            second?.next = first
            first?.next = swap
            first = swap

            if (res == null) {
                res = second
            }
            
            if (first == null || first?.next == null) {
                break
            }
            
            second = first?.next
            swap = second?.next
        }
        
        
        return res
    }
}


```

