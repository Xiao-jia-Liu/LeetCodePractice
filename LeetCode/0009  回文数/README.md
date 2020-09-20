##### 题目描述

判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。

**示例 1:**

```
输入: 121
输出: true
```

**示例 2:**

```
输入: -121
输出: false
解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
```

**示例 3:**

```
输入: 10
输出: false
解释: 从右向左读, 为 01 。因此它不是一个回文数。
```

**进阶:**

你能不将整数转为字符串来解决这个问题吗？



##### 简单解法

把int转为string，再转为char数组，利用两个指针，左指针等于右指针则相等，否则不是



##### 进阶解法

需要转为数组，本质上是需要首尾两个数字，不通过数组获取，还能直接通过数字获取，开头一个，就是除以一个10的这个数的最大长度 - 1 的乘方的数，结尾一个数，就是这个数的余数除以这个数的长度



```kotlin
class Solution {
    fun isPalindrome(x: Int): Boolean {
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false
        }

        var tem = x
        var revertNumber = 0

        while (revertNumber < tem) {
            revertNumber = revertNumber * 10 + tem % 10
            tem /= 10
        }

        return revertNumber == tem || tem == revertNumber / 10
    }
}
```

