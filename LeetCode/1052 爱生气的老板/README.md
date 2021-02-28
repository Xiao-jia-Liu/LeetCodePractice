##### 题目描述

今天，书店老板有一家店打算试营业 `customers.length` 分钟。每分钟都有一些顾客（`customers[i]`）会进入书店，所有这些顾客都会在那一分钟结束后离开。

在某些时候，书店老板会生气。 如果书店老板在第 `i` 分钟生气，那么 `grumpy[i] = 1`，否则 `grumpy[i] = 0`。 当书店老板生气时，那一分钟的顾客就会不满意，不生气则他们是满意的。

书店老板知道一个秘密技巧，能抑制自己的情绪，可以让自己连续 `X` 分钟不生气，但却只能使用一次。

请你返回这一天营业下来，最多有多少客户能够感到满意的数量。
 

**示例：**

```
输入：customers = [1,0,1,2,1,1,7,5], grumpy = [0,1,0,1,0,1,0,1], X = 3
输出：16
解释：
书店老板在最后 3 分钟保持冷静。
感到满意的最大客户数量 = 1 + 1 + 1 + 1 + 7 + 5 = 16.
```

 

**提示：**

- `1 <= X <= customers.length == grumpy.length <= 20000`
- `0 <= customers[i] <= 1000`
- `0 <= grumpy[i] <= 1`



##### 分析：

使用动态规划，很容易得出，当前的结果是前一段时间不生气和当前时间不生气的最大值

```kotlin
class Solution {
    fun maxSatisfied(customers: IntArray, grumpy: IntArray, X: Int): Int {
        if (grumpy.size == 0) {
            return 0
        }

        for (i in grumpy.indices) {
            grumpy[i] = if (grumpy[i] == 0) 1 else 0
        }

        val sum = IntArray(customers.size) {0}
        for (i in grumpy.indices) {
            if (i == 0) {
                sum[i] = customers[i] * grumpy[i]
                continue
            }
            sum[i] = sum[i - 1] + customers[i] * grumpy[i]
        }
        val cSum = IntArray(customers.size + 1) { 0 }
        for (i in customers.indices) {
            if (i == 0) {
                cSum[i] = customers[i]
                continue
            }
            cSum[i] = cSum[i - 1] + customers[i]
        }
        var res = 0
        for (i in customers.indices) {
            res = Math.max(res + customers[i] * grumpy[i], if (i >= X) cSum[i] - cSum[i - X] + sum[i - X] else cSum[i])
        }
        return res
    }
}
```

