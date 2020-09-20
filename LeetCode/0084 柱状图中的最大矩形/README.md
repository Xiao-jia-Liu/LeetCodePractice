##### 题目描述：

给定 *n* 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。

求在该柱状图中，能够勾勒出来的矩形的最大面积。

 

![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/10/12/histogram.png)

以上是柱状图的示例，其中每个柱子的宽度为 1，给定的高度为 `[2,1,5,6,2,3]`。

 

![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/10/12/histogram_area.png)

图中阴影部分为所能勾勒出的最大矩形面积，其面积为 `10` 个单位。

 

**示例:**

```
输入: [2,1,5,6,2,3]
输出: 10
```





##### 思考：暴力破解法1



枚举所有可能，计算他的面积，利用一个变量保存临时最大者，最后该变量就是最大面积

枚举部分代码主要为：（枚举宽度）

```kotlin
class Solution {
    fun largestRectangleArea(heights: IntArray): Int {
        var count = 0

        for (i in heights.indices) {
            var minHeight = 0x7fffffff
            for (j in i downTo 0 ) {
                count = kotlin.math.max(count,kotlin.math.min(minHeight,heights[j]) * (i - j + 1))
            }
        }

        return count
    }
}
```

枚举高度：

```
class Solution {
    fun largestRectangleArea(heights: IntArray): Int {
        var count = 0

        for (i in htights.indices) {
        	var height = heights[i]
        	
        	var left = i
        	var right = i
        	
        	while (left - 1 >= 0 && heights[left - 1] >= height) {
        		left--
        	}
        	
        	whle (right + 1 < heights.size && heights[right + 1] >= heithg) {
        		right++
        	}
        	
        	count = kotlin.math.max(count,(right - left + 1) * height)
        }

        return count
    }
}
```





##### 解法：栈

分析：在枚举高度的暴力解法中，当我们确定了一个柱子 i ，会向左和向右寻找它的左边界和有边界，在寻找左边界时，判断标准是向左移动最先小于柱子 i 的高度的柱子。这时带来的重复操作是当有一列递增的柱子时，每个柱子都要比较一遍左边柱子以找到左边界。但是，完全可以用容器把它存起来，当是递增的柱子连续出现时，保存起来，需要使用时，只需要找最开始的个柱子就可以了。在此处，又需要最后放入的柱子应该先拿出来，因为是要最先出现的。所以此处用栈来存储。当 heights[j0] < heights[j1]时，就把j1入栈，因为此时就是连续出现递增的柱子。当h[j] >= h[i], j 出栈，因为说明 j 肯定不是 i 的左边界，因为此时 j 左边还有小于 j 的值，除非此时栈空，则左边界为起点。同时，当 j 出栈时，说明 j 的右边界就是 i ，因为 h[i] < h[j] 。

```
class Solution {
    fun largestRectangleArea(heights: IntArray): Int {
        var count = 0
        val stack = Stack<Int>()
        val left = IntArray(heights.size)
        val right = IntArray(heights.size) {_ -> heights.size}

        for (i in heights.indices) {
            val h = heights[i]

            while (!stack.empty() && heights[stack.peek()] >= h) {
                right[stack.peek()] = i
                stack.pop()
            }

            left[i] = if (stack.empty()) -1 else stack.peek()
            stack.push(i)
        }

        for (i in left.indices) {
            count = kotlin.math.max(count,heights[i] * (right[i] - left[i] - 1))
        }

        return count
    }
}
```







