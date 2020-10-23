##### 题目描述

给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的子串的长度。

**示例**

```
输入: "(()"
输出: 2
解释: 最长有效括号子串为 "()"

输入: ")()())"
输出: 4
解释: 最长有效括号子串为 "()()"
```


**解题思路**

#####解法
括号题必然可以用栈，大二数据结构课老师当例题讲的。但是没这个题难。

最开始的思路，也是最自然的思路是：先把")"丢入栈中，来一个左括号，栈就pop一次进行匹配。

通过一个计数器记录长度，该继续加还是置零通过判断括号类型进行。

问题在于如果是')'，那么它的下一步是该计数还是置零，是很难判断的。')'可以一致在栈里待到最后再匹配。

所以解法核心在于区分有效括号串，特别是连续几个有效串的时候。

前面还是一样的，先把")"丢入栈中，来一个左括号，栈就pop一次进行匹配。

然后维护第二个栈，这个栈储存括号的索引，当括号匹配时，索引也随着第一个栈元素的pop而pop，

最后，第二个栈里面剩下的都是未匹配括号的索引。换言之这些索引把原始的列表分为了几个不同的有效括号串。

通过计算索引之间的间隔，就能得到最长的有效括号子串的长度。计算的时候要注意首尾的情况。

时间复杂度O(n)，空间复杂度O(n)。




##### 代码实现Python

```Python

class Solution(object):
    def longestValidParentheses(self, s):
        """
        :type s: str
        :rtype: int
        """

        stack = []
        n = len(s)
        
        index_list = []
        for j in range(n):
            temp = s[n-j-1]
            if temp == ')':
                index_list.append(j)
                stack.append(temp)

            elif temp == '(' and len(stack) > 0 and stack[-1] == ')':
                stack.pop()
                index_list.pop()

            elif temp == '(' and len(stack) == 0:
                index_list.append(j)
        
        if len(index_list) == 0:
            return n
        
        else:
            result = []
            for i in range(len(index_list)-1):
                temp = index_list[i+1]-index_list[i]
                result.append(temp)
            result.append(index_list[0])
            result.append(n-index_list[-1])
            max_ = max(result)
            if max_%2 == 1:
                max_ = max_-1
            return max_
```


