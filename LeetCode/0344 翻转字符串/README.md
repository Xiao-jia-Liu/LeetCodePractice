##### 题目描述

编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 char[] 的形式给出。

不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。

你可以假设数组中的所有字符都是 ASCII 码表中的可打印字符。

**示例:**

```

输入：["h","e","l","l","o"]

输出：["o","l","l","e","h"]

```


##### 题解

循环到n/2长度，首尾两两交换，美其名曰双指针，稍微想一下就可以验证不用考虑n的奇偶性。



python版本

"""
:type s: List[str]
:rtype: None Do not return anything, modify s in-place instead.
"""
		
```python
class Solution(object):
    def reverseString(self, s):
        n = len(s)
        for i in range(int(n/2)):
            s[i],s[n-i-1] = s[n-i-1],s[i]
```




