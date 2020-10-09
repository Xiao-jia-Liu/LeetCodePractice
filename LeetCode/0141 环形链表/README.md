##### 题目描述

给定一个链表，判断链表中是否有环。

如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。

如果链表中存在环，则返回 true 。 否则，返回 false 。

进阶：

你能用 O(1)（即，常量）内存解决此问题吗？


**示例:**

```
输入：head = [3,2,0,-4], pos = 1
输出：true
解释：链表中有一个环，其尾部连接到第二个节点。
```

##### 题解

此题是我的当年面试的第一轮电话面试题，我当时没答上空间为常数的解法，然后面试官告诉我要用快慢双指针。
下面的两个版本都是双指针解法。

python版本
```python
class Solution(object):
    def hasCycle(self, head):
        """
        :type head: ListNode
        :rtype: bool
        """

        if head == None or head.next == None:
            return False

        temphead1 = head
        temphead2 = head.next
        
        while temphead2 != None:
            if temphead2 == temphead1:
                return True
            else:
                if temphead2.next == None or temphead2.next.next == None:
                    return False
                temphead2 = temphead2.next.next
                temphead1 = temphead1.next
        return False
```

java版本
```java

public class Solution {
    public boolean hasCycle(ListNode head) {
        ListNode temp1=new ListNode(0);
        ListNode temp2=new ListNode(0);
        temp1=head;
        temp2=head;
        if (temp1==null || temp1.next==null)
        {
            return false;
        }
            
        while ( temp1.next  != null && temp2.next != null && temp2.next.next!= null)
        {
            temp2=temp2.next.next;
            temp1=temp1.next;
            if (temp1==temp2)
                return true;
        }
        return false;
		    }
}
```


