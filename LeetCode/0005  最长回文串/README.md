##### 题目描述

给你一个字符串 `s`，找到 `s` 中最长的回文子串。

 

**示例 1：**

```
输入：s = "babad"
输出："bab"
解释："aba" 同样是符合题意的答案。
```

**示例 2：**

```
输入：s = "cbbd"
输出："bb"
```

**示例 3：**

```
输入：s = "a"
输出："a"
```

**示例 4：**

```
输入：s = "ac"
输出："a"
```

 

**提示：**

- `1 <= s.length <= 1000`
- `s` 仅由数字和英文字母（大写和/或小写）组成





##### 马拉车算法

先使用一个不可用的字符，在每个字符中间插入，使之变成好处理的字符串

```kotlin
class Solution {
    fun longestPalindrome(s: String): String {
        if (s.length == 1 || (s.length == 2 && s[0] == s[1])) {
            return s
        }
        val sb = StringBuilder("#")
        for (c in s) {
            sb.append(c)
            sb.append("#")
        }
        val newS = sb.toString()
        val length = newS.length
        val dp = IntArray(length) {0}
        var i = 1
        var center = 0
        var maxRight = 0
        var maxLen = 0
        var startIndex = 0
        while (i < length) {
            if (i >= maxRight) {
                var left = i - 1
                var right = i + 1
                var sum = 0
                while (left >= 0 && right < length) {
                    if (newS[left] != newS[right]) {
                        break
                    } else {
                        sum++
                        left--
                        right++
                    }
                }
                dp[i] = sum
                maxRight = i + sum
                center = i
                if (sum > maxLen) {
                    maxLen = sum
                    startIndex = i
                }
            } else {
                if (maxRight - i == dp[2 * center - i]) {
                    var sum = dp[2 * center - i]
                    var left = i - sum - 1
                    var right = i + sum + 1
                    while (left >= 0 && right < length) {
                        if (newS[left] != newS[right]) {
                            break
                        } else {
                            sum++
                            left--
                            right++
                        }
                    }
                    dp[i] = sum
                    if (sum > maxLen) {
                        maxLen = sum
                        startIndex = i
                    }
                } else {
                    dp[i] = Math.min(maxRight - i, dp[2 * center - i])
                }
            }
            i++
        }
        val index = startIndex / 2
        val len = maxLen / 2
        return s.substring(index - len, index - len + maxLen)
    }
}

```

