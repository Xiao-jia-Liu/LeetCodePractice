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