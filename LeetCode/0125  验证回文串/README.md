##### 题目描述

给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。

**说明：**本题中，我们将空字符串定义为有效的回文串。

注释：回文串就是正向读与反向读一样的字符串

**示例 1:**

```
输入: "A man, a plan, a canal: Panama"
输出: true
```

**示例 2:**

```
输入: "race a car"
输出: false
```





##### 解法：

利用两个指针，一个从后往前，一个从前往后

```kotlin
class Solution {
    fun isPalindrome(s: String): Boolean {
        var data = s.toLowerCase()

        if (data.trim().isEmpty() || data.length == 1) {
            return true
        }

        var left = 0
        var right = s.length - 1

        while (true) {
            while (left < data.length - 1 && data[left] !in 'a'..'z' && data[left] !in '0'..'9') {
                left++
            }

            while (right > 0 && data[right] !in 'a'..'z' && data[right] !in '0'..'9') {
                right--
            }

            if (left >= right) {
                return true
            }

            if (data[left] != data[right]) {
                return false
            }

            left++
            right--
        }
    }
}
```

