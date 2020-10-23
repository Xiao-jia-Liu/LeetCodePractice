##### 题目描述：

请判断一个链表是否为回文链表。

**示例：**

```
输入: 1->2
输出: false

输入: 1->2->2->1
输出: true
```

进阶：

你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？


##### 解法：
从链表长度一半开始，用一个栈改变后一半顺序，然后和前一半做比较，不一样就不是，一样就是。

O(1)解法大概是，翻转后一半链表，然后比较，翻转链表能O(1)，这个题就能O(1)，没意思。

```python
class Solution(object):
    def isPalindrome(self, head):
        stack = []
        flag = head
        count = 0
        while flag != None:
            stack.append(flag.val)
            count = count + 1
            flag = flag.next
        new_flag = head
        for i in range(count/2):
            temp1 = stack.pop()
            temp2 = new_flag.val
            if temp1 != temp2:
                return False
            new_flag = new_flag.next
        return True
```



从链表一半，反转后一半或者前一半，然后比较，不等就是不是

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
    fun isPalindrome(head: ListNode?): Boolean {
        if (head?.next == null) {
            return true
        }

        var pre = head?.next
        var index = 1
        var half: ListNode? = head

        while (pre != null) {
            index++

            if (pre?.`val` == head?.`val`) {
                break
            }

            pre = pre?.next
        }

        if (pre == null) {
            return false
        }

        if (index % 2 == 1) {
            val mid = index / 2
            repeat(mid) {
                half = half?.next
            }

            val node = ListNode(half?.`val`!!)
            val tem = half
            node?.next = half?.next
            half = node
            tem?.next = null
        } else {
            val mid = index / 2
            repeat(mid - 1) {
                half = half?.next
            }

            val tem = half
            half = half?.next
            tem?.next = null
        }

        var reverse = reverseList(half)
        var cur = head
// print(reverse)
// println("----------")
// print(cur)
        while (cur != null && reverse != null) {
            if (cur?.`val` != reverse?.`val`) {
                return false
            }

            cur = cur?.next
            reverse = reverse?.next
        }

        return cur == null && reverse == null
    }

fun print(head: ListNode?) {
    var cur = head

    while (cur != null) {
        println(cur?.`val`)
        cur = cur?.next
    }
}

    fun reverseList(root: ListNode?): ListNode? {
        var head = root
        var after:ListNode? = null

        while (head != null) {
            val tem = head?.next
            head?.next = after
            after = head
            head = tem
        }

        return after
    }
}
```



