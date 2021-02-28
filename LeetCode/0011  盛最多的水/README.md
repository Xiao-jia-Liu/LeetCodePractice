##### 题目描述：

给你 `n` 个非负整数 `a1，a2，...，a``n`，每个数代表坐标中的一个点 `(i, ai)` 。在坐标内画 `n` 条垂直线，垂直线 `i` 的两个端点分别为 `(i, ai)` 和 `(i, 0)` 。找出其中的两条线，使得它们与 `x` 轴共同构成的容器可以容纳最多的水。

**说明：**你不能倾斜容器。

 

**示例 1：**

![img](https://aliyun-lc-upload.oss-cn-hangzhou.aliyuncs.com/aliyun-lc-upload/uploads/2018/07/25/question_11.jpg)

```
输入：[1,8,6,2,5,4,8,3,7]
输出：49 
解释：图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。
```

**示例 2：**

```
输入：height = [1,1]
输出：1
```

**示例 3：**

```
输入：height = [4,3,2,1,4]
输出：16
```

**示例 4：**

```
输入：height = [1,2,1]
输出：2
```

 

**提示：**

- `n = height.length`
- `2 <= n <= 3 * 104`
- `0 <= height[i] <= 3 * 104`





##### 分析：

双指针法，最大的容量一定出现在两个端之前，移动当前指向的两个端点中的小者。证明：

设左边高为 left ,右边高为right。再假如left <= right , 则当前面积为min(left, right) * w = left * w

,此时右移left ，新的面积为 min(left1, right) * (w - 1), 新面积可能增大。如果左移right ，则面积为 min(left, right1) * (w - 1)，有两种情况：right1 <= left ,此时新面积为right1 * (w - 1)，面积减少; right1 > left ，面积为left * (w - 1)，面积还是减少。所以移动小者，能带来面积的增大或减少，最后保存最大值即可。

```kotlin
class Solution {
    fun maxArea(height: IntArray): Int {
        var left = 0
        var right = height.size - 1
        var res = 0
        while (left != right) {
            val minValue = Math.min(height[left], height[right])
            res = Math.max(res, (right - left) * minValue)
            if (minValue == height[left]) {
                left++
            } else {
                right--
            }
        }
        return res
    }
}
```

