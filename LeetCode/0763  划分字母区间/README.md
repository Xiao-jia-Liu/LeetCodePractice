##### 题目描述

字符串 `S` 由小写字母组成。我们要把这个字符串划分为尽可能多的片段，同一个字母只会出现在其中的一个片段。返回一个表示每个字符串片段的长度的列表。

 

**示例 1：**

```
输入：S = "ababcbacadefegdehijhklij"
输出：[9,7,8]
解释：
划分结果为 "ababcbaca", "defegde", "hijhklij"。
每个字母最多出现在一个片段中。
像 "ababcbacadefegde", "hijhklij" 的划分是错误的，因为划分的片段数较少。
```

 

**提示：**

- `S`的长度在`[1, 500]`之间。
- `S`只包含小写字母 `'a'` 到 `'z'` 。





增加一个额外数组，表示a-z的最后出现位置，然后从前往后遍历S，每出现一个字母，就获取它最后出现的位置，与当前保存的最大位置，取大者，就表示当前这个字串能到达的最短位置



```kotlin
class Solution {
    fun partitionLabels(S: String): List<Int> {
        val res = ArrayList<Int>()
        val maxIndex = IntArray(26) {0}

        val size = S.length

        for (i in 0 until size) {
            maxIndex[S[i] - 'a'] = i
        }

        var max = 0
        var lastIndex = 0

        for (i in 0 until size) {
            max = Math.max(max, maxIndex[S[i] - 'a'])

            if (i >= max) {
                res.add(i - lastIndex + 1)
                lastIndex = i + 1
            }
        }

        return res
    }
}
```

