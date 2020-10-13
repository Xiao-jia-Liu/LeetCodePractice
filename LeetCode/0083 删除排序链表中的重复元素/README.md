##### 题目描述：

给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。

**示例：**

```
输入: 1->1->2
输出: 1->2

输入: 1->1->2->3->3
输出: 1->2->3
```


##### 解法：
快慢指针。

快指针，找到和当前节点值一样的节点就前进一步，找到不一样的就停，快指针指向不一样的节点，慢指针指向快指针。

快指针继续向前，重复操作。

时间复杂度O(n)，空间复杂度O(1)

```python
class Solution(object):
    def deleteDuplicates(self, head):
        """
        :type head: ListNode
        :rtype: ListNode
        """
        flag = head
        while flag != None and flag.next != None:
            temp_flag = flag
            while temp_flag != None and temp_flag.val == flag.val:
                temp_flag = temp_flag.next
            flag.next = temp_flag
            flag = flag.next
        return head
```







```
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
    fun deleteDuplicates(head: ListNode?): ListNode? {
        if (head == null) {
            return head
        }
        
        var cur = head?.`val`
        var tem = head

        while (tem?.next != null) {
            if (cur == tem?.next?.`val`) {
                tem?.next = tem?.next?.next
                continue
            } else {
                cur = tem?.next?.`val`!!
            }

            tem = tem?.next
        }

        return head
    }
}
```

