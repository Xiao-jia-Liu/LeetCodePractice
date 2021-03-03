##### 题目描述

给定一个仅包含数字 `2-9` 的字符串，返回所有它能表示的字母组合。答案可以按 **任意顺序** 返回。

给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。

![img](https://assets.leetcode-cn.com/aliyun-lc-upload/original_images/17_telephone_keypad.png)

 

**示例 1：**

```
输入：digits = "23"
输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
```

**示例 2：**

```
输入：digits = ""
输出：[]
```

**示例 3：**

```
输入：digits = "2"
输出：["a","b","c"]
```

 

**提示：**

- `0 <= digits.length <= 4`
- `digits[i]` 是范围 `['2', '9']` 的一个数字。



```kotlin
class Solution {
    fun letterCombinations(digits: String): List<String> {
        val len = digits.length
        if (len == 0) {
            return ArrayList<String> ()
        } else if (len == 1) {
            return getChar(digits[0])
        } else {
            val pre = letterCombinations(digits.substring(0, len - 1))
            val res = LinkedList<String> ()
            for (cur in getChar(digits[len - 1])) {
                for (last in pre) {
                    res.add(last + cur)
                } 
            }
            return res
        }
    }

    fun getChar(digit: Char): List<String> {
        return when(digit) {
                '2' -> {
                    listOf("a", "b", "c")
                }
                '3' -> {
                    listOf("d", "e", "f")
                }
                '4' -> {
                    listOf("g", "h", "i")
                }
                '5' -> {
                    listOf("j", "k", "l")
                }
                '6' -> {
                    listOf("m", "n", "o")
                }
                '7' -> {
                    listOf("p", "q", "r", "s")
                }
                '8' -> {
                    listOf("t", "u", "v")
                }
                '9' -> {
                    listOf("w", "x", "y", "z")
                }
                else -> {
                    LinkedList<String> ()
                }
        }
    }
}
```

