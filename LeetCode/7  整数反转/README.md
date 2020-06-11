##### 题目描述

给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。

**示例 1:**

```
输入: 123
输出: 321
```

 **示例 2:**

```
输入: -123
输出: -321
```

**示例 3:**

```
输入: 120
输出: 21
```

**注意:**

假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−2^31, 2^31 − 1]。请根据这个假设，如果反转后整数溢出那么就返回 0。



##### 题解

比较简单，直接转为char数组，首尾交换元素即可

```kotlin
class Solution {
    fun reverse(x: Int): Int {
        var s = x.toString()
        var a = s.toCharArray()

        for (i in a.indices) {
            var j = a.size - i - 1

            if (j <= i) {
                break
            }

            var c = a[i]
            a[i] = a[j]
            a[j] = c
        }

        try {
            if (a[a.size - 1] == '-') {
                return (String(a).substring(0,a.size - 1).toInt() * -1)
            } else {
                return (String(a).toInt())
            }
        } catch (ex : Exception) {
            return 0
        }
    }
}
```



