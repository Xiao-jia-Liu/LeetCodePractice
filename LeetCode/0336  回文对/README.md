##### 题目描述

给定一组 **互不相同** 的单词， 找出所有**不同** 的索引对`(i, j)`，使得列表中的两个单词， `words[i] + words[j]` ，可拼接成回文串。

 

**示例 1：**

```
输入：["abcd","dcba","lls","s","sssll"]
输出：[[0,1],[1,0],[3,2],[2,4]] 
解释：可拼接成的回文串为 ["dcbaabcd","abcddcba","slls","llssssll"]
```

**示例 2：**

```
输入：["bat","tab","cat"]
输出：[[0,1],[1,0]] 
解释：可拼接成的回文串为 ["battab","tabbat"]
```





##### 暴力破解法

两两拼接，然后判断是不是回文串，是就将下标添加进链表中，否则继续

```kotlin
class Solution {
    fun palindromePairs(words: Array<String>): List<List<Int>> {
        val res = ArrayList<ArrayList<Int>>()
        val size = words.size

        for (i in 0 until size) {
            for (j in 0 until size) {
                if (i == j) {
                    continue
                }

                if (isPairs(words[i] + words[j])) {
                    res.add(ArrayList<Int>().apply {
                        add(i)
                        add(j)
                    })
                }
            }
        }

        return res
    }

    fun isPairs(str: String): Boolean {
        var right = str.length - 1
        var left = 0

        while (left <= right) {
            if (str[left] != str[right]) {
                return false
            }

            left ++
            right --
        }

        return true
    }
}
```





##### 枚举前缀和后缀

假设存在两个字符串 s 1和 s2，s1+s 2是一个回文串，记这两个字符串的长度分别为len 
1 和 len 2，我们分三种情况进行讨论：

1. len1=len 2，这种情况下 s1是 s2的翻转。
2. len1 > len2 ，这种情况下我们可以将 s1拆成左右两部分：t1和 t2 ，其中 t1是 s2的翻转，t2是一个回文串
3. len 1<len 2，这种情况下我们可以将 s2拆成左右两部分：t1和 t2，其中 t2是 s1的翻转，t1是一个回文串。这样，对于每一个字符串，我们令其为 s1和 s2中较长的那一个，然后找到可能和它构成回文串的字符串即可。

具体地说，我们枚举每一个字符串 k，令其为 s1和 s2中较长的那一个，那么 k可以被拆分为两部分，t1和 t2。当 t1是回文串时，符合情况 3，我们只需要查询给定的字符串序列中是否包含 t2的翻转。当 t2是回文串时，符合情况 2，我们只需要查询给定的字符串序列中是否包含 t1的翻转。
也就是说，我们要枚举字符串 kk 的每一个前缀和后缀，判断其是否为回文串。如果是回文串，我们就查询其剩余部分的翻转是否在给定的字符串序列中出现即可。

注意到空串也是回文串，所以我们可以将 k 拆解为 k+\varnothingk+∅ 或 \varnothing+k∅+k，这样我们就能将情况 1 也解释为特殊的情况 2 或情况 3

而要实现这些操作，我们只需要设计一个能够在一系列字符串中查询「某个字符串的子串的翻转」是否存在的数据结构，有两种实现方法：

我们可以使用字典树存储所有的字符串。在进行查询时，我们将待查询串的子串逆序地在字典树上进行遍历，即可判断其是否存在。

我们可以使用哈希表存储所有字符串的翻转串。在进行查询时，我们判断带查询串的子串是否在哈希表中出现，就等价于判断了其翻转是否存在。



```java
class Solution {
    List<String> wordsRev = new ArrayList<String>();
    Map<String, Integer> indices = new HashMap<String, Integer>();

    public List<List<Integer>> palindromePairs(String[] words) {
        int n = words.length;
        for (String word: words) {
            wordsRev.add(new StringBuffer(word).reverse().toString());
        }
        for (int i = 0; i < n; ++i) {
            indices.put(wordsRev.get(i), i);
        }

        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        for (int i = 0; i < n; i++) {
            String word = words[i];
            int m = words[i].length();
            if (m == 0) {
                continue;
            }
            for (int j = 0; j <= m; j++) {
                if (isPalindrome(word, j, m - 1)) {
                    int leftId = findWord(word, 0, j - 1);
                    if (leftId != -1 && leftId != i) {
                        ret.add(Arrays.asList(i, leftId));
                    }
                }
                if (j != 0 && isPalindrome(word, 0, j - 1)) {
                    int rightId = findWord(word, j, m - 1);
                    if (rightId != -1 && rightId != i) {
                        ret.add(Arrays.asList(rightId, i));
                    }
                }
            }
        }
        return ret;
    }

    public boolean isPalindrome(String s, int left, int right) {
        int len = right - left + 1;
        for (int i = 0; i < len / 2; i++) {
            if (s.charAt(left + i) != s.charAt(right - i)) {
                return false;
            }
        }
        return true;
    }

    public int findWord(String s, int left, int right) {
        return indices.getOrDefault(s.substring(left, right + 1), -1);
    }
}
```

