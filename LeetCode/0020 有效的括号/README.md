##### 题目描述

给定一个只包括 `'('`，`')'`，`'{'`，`'}'`，`'['`，`']'` 的字符串 `s` ，判断字符串是否有效。

有效字符串需满足：

1. 左括号必须用相同类型的右括号闭合。
2. 左括号必须以正确的顺序闭合。

 

**示例 1：**

```
输入：s = "()"
输出：true
```

**示例 2：**

```
输入：s = "()[]{}"
输出：true
```

**示例 3：**

```
输入：s = "(]"
输出：false
```

**示例 4：**

```
输入：s = "([)]"
输出：false
```

**示例 5：**

```
输入：s = "{[]}"
输出：true
```

 

**提示：**

- `1 <= s.length <= 104`
- `s` 仅由括号 `'()[]{}'` 组成





```kotlin
class Solution {
    fun isValid(s: String): Boolean {
        if (s.length == 0) {
            return true
        }
        var index = 0
        val stack = LinkedList<Char> ()
        stack.addLast(s[index])
        index++
        while (index < s.length) {
            val c = s[index]
            if (stack.isEmpty()) {
                stack.add(c)
            } else {
                when(stack.last + "" + c) {
                    "()" -> {
                        stack.removeLast()
                    }
                    "[]" -> {
                        stack.removeLast()
                    }
                    "{}" -> {
                        stack.removeLast()
                    }
                    else -> {
                        stack.add(c)
                    }
                }
            }
            index++
        }
        return stack.isEmpty()
    }
}
```

