##### 题目描述

给你两个二进制字符串，返回它们的和（用二进制表示）。

输入为 **非空** 字符串且只包含数字 `1` 和 `0`。

 

**示例 1:**

```
输入: a = "11", b = "1"
输出: "100"
```

**示例 2:**

```
输入: a = "1010", b = "1011"
输出: "10101"
```

 

**提示：**

- 每个字符串仅由字符 `'0'` 或 `'1'` 组成。
- `1 <= a.length, b.length <= 10^4`
- 字符串如果不是 `"0"` ，就都不含前导零。



##### 一个因缺思厅的解法

直接当成向电路一样，各种开关，触发哪一个就走那个分支，不搞什么幺蛾子个数判断，虽然会简介很多，但是这样也很有趣



```kotlin
class Solution {
    fun addBinary(a: String, b: String): String {
        var res: StringBuilder = StringBuilder()
        var ia = a.length - 1
        var ib = b.length - 1
        var carray = '0'

        while (ia >= 0 && ib >=0) {
            var ca = a[ia]
            var cb = b[ib]

            when("$ca$cb$carray") {
                "000" -> {
                    res.append('0')
                }

                "001" -> {
                    res.append('1')
                    carray = '0'
                }

                "010" -> {
                    res.append('1')
                }

                "011" -> {
                    res.append('0')
                    carray = '1'
                }

                "100" -> {
                    res.append('1')
                }

                "101" -> {
                    res.append('0')
                    carray = '1'
                }

                "110" -> {
                    res.append(0)
                    carray = '1'
                }

                "111" -> {
                    res.append(1)
                    carray = '1'
                }
            }

            ia--
            ib--
        }

        while (ia >= 0) {
            if (carray == '1') {
                when ("$carray${a[ia]}") {
                    "11" -> {
                        res.append('0')
                    }

                    "10" -> {
                        res.append('1')
                        carray = '0'
                    }
                }
            } else {
                res.append(a[ia])
            }

            ia--
        }

        while (ib >= 0) {
            if (carray == '1') {
                when ("$carray${b[ib]}") {
                    "11" -> {
                        res.append('0')
                    }

                    "10" -> {
                        res.append('1')
                        carray = '0'
                    }
                }
            } else {
                res.append(b[ib])
            }

            ib--
        }

        if (carray == '1') {
            res.append('1')
        }

        return res.reverse().toString()
    }
}
```

