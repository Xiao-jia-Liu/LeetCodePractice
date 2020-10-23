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







