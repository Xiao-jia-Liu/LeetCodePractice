##### 题目描述

用两个栈实现一个队列。队列的声明如下，请实现它的两个函数 `appendTail` 和 `deleteHead` ，分别完成在队列尾部插入整数和在队列头部删除整数的功能。(若队列中没有元素，`deleteHead` 操作返回 -1 )

 

**示例 1：**

```
输入：
["CQueue","appendTail","deleteHead","deleteHead"]
[[],[3],[],[]]
输出：[null,null,3,-1]
```

**示例 2：**

```
输入：
["CQueue","deleteHead","appendTail","appendTail","deleteHead","deleteHead"]
[[],[],[5],[2],[],[]]
输出：[null,-1,null,null,5,2]
```

**提示：**

- `1 <= values <= 10000`
- `最多会对 appendTail、deleteHead 进行 10000 次调用`





##### 题解

题目都已经说了用两个栈，那没啥好想的了，两个栈，负负得正，倒两次 就是正的顺序



```kotlin
class CQueue() {
    private val inStack = Stack<Int>()
    private val outStack = Stack<Int>()

    fun appendTail(value: Int) {
        inStack.push(value)
    }

    fun deleteHead(): Int {
        if (!outStack.isEmpty()) {
            return outStack.pop()
        } else {
            if (inStack.isEmpty()) {
                return -1
            } else {
                while (!inStack.isEmpty()) {
                    outStack.push(inStack.pop())
                }

                return outStack.pop()
            }
        }
    }
}
```

