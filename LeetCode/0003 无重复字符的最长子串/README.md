##### 题目描述：

给定一个字符串，请你找出其中不含有重复字符的 **最长子串** 的长度。

 

**示例 1:**

```
输入: s = "abcabcbb"
输出: 3 
解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
```

**示例 2:**

```
输入: s = "bbbbb"
输出: 1
解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
```

**示例 3:**

```
输入: s = "pwwkew"
输出: 3
解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
```

**示例 4:**

```
输入: s = ""
输出: 0
```

 

**提示：**

- `0 <= s.length <= 5 * 104`
- `s` 由英文字母、数字、符号和空格组成



##### 分析：

双指针法：无重复的最长字串就是在一个区间内的最多有多少个字符数，使用一个指针，指向当前处理的字符，如果当前字符已经存在，移动左指针，表现为移除第一个元素，否则将右指针指向字符加入集合，返回集合元素最大者

```kotlin
class Solution {
    fun lengthOfLongestSubstring(s: String): Int {
        var right = 0
        var left = 0
        var max = 0
        val duplication = ArrayList<Char>()
        while (right < s.length) {
            if (duplication.contains(s[right])) {
                left++
                duplication.removeAt(0)
            } else {
                duplication.add(s[right])
                right++
            }
            max = Math.max(max, duplication.size)
        }
        return max
    }
}
```

