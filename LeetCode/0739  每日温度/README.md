##### 题目描述

请根据每日 `气温` 列表，重新生成一个列表。对应位置的输出为：要想观测到更高的气温，至少需要等待的天数。如果气温在这之后都不会升高，请在该位置用 `0` 来代替。

例如，给定一个列表 `temperatures = [73, 74, 75, 71, 69, 72, 76, 73]`，你的输出应该是 `[1, 1, 4, 2, 1, 1, 0, 0]`。

**提示：**`气温` 列表长度的范围是 `[1, 30000]`。每个气温的值的均为华氏度，都是在 `[30, 100]` 范围内的整数。





##### 暴力破解法

对每个 Xi ，向后遍历，最近的大于它的值的距离就是观测到更高温的天数，到最后也没有则为 0 

```kotlin
class Solution {
    fun dailyTemperatures(T: IntArray): IntArray {
        var r = IntArray(T.size)

        for (i in T.indices) {
            for (j in i + 1 until T.size) {
                if (T[j] > T[i]) {
                    r[i] = j - i
                    break
                }
            }
        }

        return r
    }
}
```





时间复杂度 O(n^3)



##### 利用栈实现

```kotlin
class Solution {
    fun dailyTemperatures(T: IntArray): IntArray {
        var r = IntArray(T.size)
        var stack = Stack<Int>()

        for (i in T.indices) {
            if (i == 0) {
                stack.push(i)
                continue
            }

            while (!stack.empty() && T[i] > T[stack.peek()]) {
                r[stack.peek()] = i - stack.peek()
                stack.pop()
            }

            stack.push(i)
        }

        return r
    }
}
```

