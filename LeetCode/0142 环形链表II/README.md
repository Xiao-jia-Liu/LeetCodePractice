##### 题目描述

给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。

为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。

说明：不允许修改给定的链表。


**示例:**

```
输入：head = [3,2,0,-4], pos = 1
输出：tail connects to node index 1
解释：链表中有一个环，其尾部连接到第二个节点。
```

进阶：
你是否可以不用额外空间解决此题？

##### 题解

这题要比 0141 环形链表 难一点，因为要找到环的起始位置。

用快慢指针思路想了一下没想通，退一步用辅助列表比较简单。

循环时，第二次出现重复节点时就是环的入口节点。

python版本
```python
class Solution(object):
    def detectCycle(self, head):
        """
        :type head: ListNode
        :rtype: ListNode
        """
        temp = head
        temp_list = []
        while temp != None and temp not in temp_list:
            temp_list.append(temp)
            temp = temp.next
        if temp == None:
            return None
        else:
            return temp
```

复杂度 时间复杂度O(n) 空间复杂度O(n)

##### 快慢指针

利用一个快指针fast，一个慢指针slow，快指针每次都向前移动两个位置，慢指针移动一个位置，如果有环的话，肯定会在某个位置相遇。假设相遇点为n，设从头到n有a个节点，从n到相遇的位置有b个节点，从相遇位置继续向前到n有c个节点。则有关系式
$$
a + n(b + c) + b = 2(a + b)
$$
因为，可能快指针已经走了n圈，所以有个n(b+c),且相遇时，会额外有一段c，还有开头的a，而满指针就只有a和b，所以；
$$
a = (n - 1)(b + c) + c
$$
这个就表示，从开头走到n的位置的距离正好等于从相遇的位置走n - 1圈且 + c到n，所以，可以在用一个p指针，当fast和slow相遇时，p往前走，slow往前走，当两个相遇时，就是环的开始点

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
    fun detectCycle(head: ListNode?): ListNode? {
        if (head == null) {
            return null
        }

        var slow = head?.next
        var fast = head?.next?.next

        while (slow != null && fast != null) {
            if (slow == fast) {
                var p = head

                while (p != null && slow != null) {
                    if (p == slow) {
                        return p
                    }

                    p = p?.next
                    slow = slow?.next
                } 

            }

            slow = slow?.next
            fast = fast?.next?.next
        }

        return null
    }
}
```

