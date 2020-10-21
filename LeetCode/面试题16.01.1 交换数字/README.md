##### 题目描述


编写一个函数，不用临时变量，直接交换`numbers = [a, b]`中`a`与`b`的值。

**示例：**

```
输入: numbers = [1,2]
输出: [2,1]
```

**提示：**

- `numbers.length == 2`





异或运算，没啥好说的

```kotlin
class Solution {
    fun swapNumbers(numbers: IntArray): IntArray {
        numbers[0] = numbers[0].xor(numbers[1])

        numbers[1] = numbers[1].xor(numbers[0])

        numbers[0] = numbers[1].xor(numbers[0])

        return numbers
    }
}
```

