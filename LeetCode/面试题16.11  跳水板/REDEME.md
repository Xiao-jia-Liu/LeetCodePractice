##### 题目描述

你正在使用一堆木板建造跳水板。有两种类型的木板，其中长度较短的木板长度为`shorter`，长度较长的木板长度为`longer`。你必须正好使用`k`块木板。编写一个方法，生成跳水板所有可能的长度。

返回的长度需要从小到大排列。

**示例：**

```
输入：
shorter = 1
longer = 2
k = 3
输出： {3,4,5,6}
```

**提示：**

- 0 < shorter <= longer
- 0 <= k <= 100000





##### 暴力破解法

能拼成的就在从 k 块短板都 0 块短板之间，所以，直接暴力破解完事



##### 直接添加

但是，很容易就发现其实组成的数组是一个等差数列，差为longer - shorter，所以，很简单，从 k * shorter 开始，直接填充数组，差值为前一个 + longer - shorter，时间复杂度 k

```kotlin
class Solution {
    fun divingBoard(shorter: Int, longer: Int, k: Int): IntArray {
        if (k == 0) {
            return IntArray(0)
        }

        if (shorter == longer) {
            return intArrayOf(k * shorter)
        }

        val ret = IntArray(k + 1) {k * shorter}

        for (i in 1 .. k) {
            ret[i] = ret[i - 1] + (longer - shorter)
        }

        return ret
    }
}
```

