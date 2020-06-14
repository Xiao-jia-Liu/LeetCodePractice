##### k题目描述

给你一个整数数组 `arr` 和一个目标值 `target` ，请你返回一个整数 `value` ，使得将数组中所有大于 `value` 的值变成 `value` 后，数组的和最接近 `target` （最接近表示两者之差的绝对值最小）。

如果有多种使得和最接近 `target` 的方案，请你返回这些整数中的最小值。

请注意，答案不一定是 `arr` 中的数字。

 

**示例 1：**

```
输入：arr = [4,9,3], target = 10
输出：3
解释：当选择 value 为 3 时，数组会变成 [3, 3, 3]，和为 9 ，这是最接近 target 的方案。
```

**示例 2：**

```
输入：arr = [2,3,5], target = 10
输出：5
```

**示例 3：**

```
输入：arr = [60864,25176,27249,21296,20204], target = 56803
输出：11361
```





##### 暴力破解法

从数组中最大的一个元素 start 开始 ，将遍历数组一遍，如果数组元素大于 start，则用start相加，直到得出最接近的值（用时3秒多竟然还能通过 \手动笑哭）

```kotlin
class Solution {
    fun findBestValue(arr: IntArray, target: Int): Int {
        var sortedArr = arr.sortedArray()

        var calSum = Int.MAX_VALUE
        var value = Int.MAX_VALUE


        for (i in sortedArr[sortedArr.size - 1] downTo  0) {
            var sum = 0

            for (j in sortedArr.indices) {
                sum += if (sortedArr[j] > i) {
                    i
                } else {
                    sortedArr[j]
                }
            }

            if (calSum >= Math.abs(sum - target)) {
                value = i
                calSum = Math.abs(sum - target)
            }
        }

        return value
    }
}
```





##### 二分查找法

由题可得，数组和是随着value的增大而递增的。所以，寻找一个中间数组和sum，该sum < target 且后一个值的sum > target，若过程中有直接等于的，那肯定就是最优解，直接返回。所以，可以利用二分法查找这样一个值

```kotlin
class Solution {
    fun findBestValue(arr: IntArray, target: Int): Int {
        var sortedArr = arr.sortedArray()

        var sums = IntArray(sortedArr.size + 1)

        for (i in 1 .. sortedArr.size) {
            sums[i] = sums[i - 1] + sortedArr[i - 1]
        }

        var high = sortedArr[sortedArr.size - 1]
        var low = 0


        while (low <= high) {
            var mid = (low + high) / 2
            var sum = getSum(sums,sortedArr,mid)

            when {
                sum < target -> {
                    low = mid + 1
                }

                sum == target -> {
                    println(mid)
                    return mid
                }

                else -> {
                    high = mid - 1
                }
            }
        }

        if (low > sortedArr[sortedArr.size - 1]) {
            low = sortedArr[sortedArr.size - 1]
        }

        if (getSum(sums,sortedArr,low) > target) {
            if (Math.abs(getSum(sums,sortedArr,low) - target) < Math.abs(getSum(sums,sortedArr,low - 1) - target)) {

            } else {
                low -= 1
            }
        } else {
            if (Math.abs(getSum(sums,sortedArr,low + 1) - target) < Math.abs(getSum(sums,sortedArr,low ) - target)) {
                low += 1
            } else {

            }
        }

        return low
    }


    fun getSum(sums:IntArray,sortedArr: IntArray,value: Int): Int {
        var l = 0
        var h = sortedArr.size - 1
        var sum = 0
        //二分法查找所有小于 i 的元素位置
        while (l <= h && l < sortedArr.size) {
            var mid = (l + h) / 2

            if (sortedArr[mid] <= value) {
                l = mid + 1
            } else {
                h = mid - 1
            }
        }

        var index = if (l >= sortedArr.size + 1) {
            sortedArr.size - 1
        } else {
            l
        }

        sum += sums[index]
        sum += ((sortedArr.size - index) * value)
        return sum
    }
}
```

