##### 题目描述

你的朋友正在使用键盘输入他的名字 `name`。偶尔，在键入字符 `c` 时，按键可能会被*长按*，而字符可能被输入 1 次或多次。

你将会检查键盘输入的字符 `typed`。如果它对应的可能是你的朋友的名字（其中一些字符可能被长按），那么就返回 `True`。

 

**示例 1：**

```
输入：name = "alex", typed = "aaleex"
输出：true
解释：'alex' 中的 'a' 和 'e' 被长按。
```

**示例 2：**

```
输入：name = "saeed", typed = "ssaaedd"
输出：false
解释：'e' 一定需要被键入两次，但在 typed 的输出中不是这样。
```

**示例 3：**

```
输入：name = "leelee", typed = "lleeelee"
输出：true
```

**示例 4：**

```
输入：name = "laiden", typed = "laiden"
输出：true
解释：长按名字中的字符并不是必要的。
```

 

**提示：**

1. `name.length <= 1000`
2. `typed.length <= 1000`
3. `name` 和 `typed` 的字符都是小写字母。





利用两个指针，一个指向name，一个指向typed，同时向前遍历，且在name与typed不等时就返回false，在相等时，且name[i] == name[i-1]时，继续向前走，name[i] != name[i-1]时，就对type进行遍历，将指向typed的指针指向下一个不等的位置

```kotlin
class Solution {
    fun isLongPressedName(name: String, typed: String): Boolean {
        var n = 0
        var t = 0
        val nameSize = name.length
        val typedSize = typed.length

        while (n < nameSize) {
            if (t >= typedSize || name[n] != typed[t]) {
                return false
            }

            if (n == nameSize - 1 || (n < nameSize - 1 && name[n] != name[n + 1])) {
                while (t < typedSize - 1 && typed[t] == typed[t + 1] ) {
                    t++
                }

                t++
                n++
            } else {
                n++
                t++
            }
        }

        return t == typedSize
    }
}
```

更好的写法：以指向typed的指针为基础

```java
class Solution {
    public boolean isLongPressedName(String name, String typed) {
        int i = 0, j = 0;
        while (j < typed.length()) {
            if (i < name.length() && name.charAt(i) == typed.charAt(j)) {
                i++;
                j++;
            } else if (j > 0 && typed.charAt(j) == typed.charAt(j - 1)) {
                j++;
            } else {
                return false;
            }
        }
        return i == name.length();
    }
}
```

