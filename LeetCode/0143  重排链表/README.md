##### 题目描述

给定一个单链表 *L*：*L*0→*L*1→…→*L**n*-1→*L*n ，
将其重新排列后变为： *L*0→*L**n*→*L*1→*L**n*-1→*L*2→*L**n*-2→…

你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。

**示例 1:**

```
给定链表 1->2->3->4, 重新排列为 1->4->2->3.
```

**示例 2:**

```
给定链表 1->2->3->4->5, 重新排列为 1->5->2->4->3.
```





##### 迭代解法

先用双指针法找到链表的中点，将链表分割成两部分，然后，对后半部分进行反转，最后把两个链表拼接起来就是最终的答案

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
    fun reorderList(head: ListNode?): Unit {
        var slow = head
        var fast = head?.next
        var pre: ListNode? = null

        while (true) {
            fast = fast?.next?.next

            if (fast == null) {
                val tem = slow?.next
                slow?.next = null
                slow = tem
                break
            }

            slow = slow?.next
        }

        pre?.next = null
        val rev = reverseList(slow)
        unitList(head, rev)
    }

    fun reverseList(head: ListNode?): ListNode? {
        var pre: ListNode? = null
        var cur: ListNode? = head

        while(cur != null) {
            val temNext = cur?.next
            cur?.next = pre
            pre = cur
            cur = temNext
        }

        return pre
    }

    fun unitList(head1: ListNode?, head2: ListNode?) {
        var p1 = head1
        var p2 = head2
        var change = false

        while (p1 != null && p2 != null) {
            if (change) {
                val tem = p2
                p2 = p2?.next
                tem?.next = p1
            } else {
                val tem = p1
                p1 = p1?.next
                tem?.next = p2
            }

            change = !change
        }
    }
}
```

