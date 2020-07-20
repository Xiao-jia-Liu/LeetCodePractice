##### 题目描述

给定一个已按照***升序排列\*** 的有序数组，找到两个数使得它们相加之和等于目标数。

函数应该返回这两个下标值 index1 和 index2，其中 index1 必须小于 index2*。*

**说明:**

- 返回的下标值（index1 和 index2）不是从零开始的。
- 你可以假设每个输入只对应唯一的答案，而且你不可以重复使用相同的元素。

**示例:**

```
输入: numbers = [2, 7, 11, 15], target = 9
输出: [1,2]
解释: 2 与 7 之和等于目标数 9 。因此 index1 = 1, index2 = 2 。
```





##### 哈希表

和之前的解法一样，利用一个哈希表，找对应的值



##### 双指针法

由于是有序的数组，则两个数之和，利用一个双指针，如果两个指针之和小于target，则说明左边值较小，且不会出现左值和右值的前边值和为target的情况，因为此时已经小于了，右值的左边的值肯定小于等于右值。反之亦然

```kotlin
class Solution {
    fun twoSum(numbers: IntArray, target: Int): IntArray {
        var left = 0
        var right = numbers.size - 1

        while (left < right) {
            if (numbers[left] + numbers[right] < target) {
                left++
            } else if (numbers[left] + numbers[right] == target) {
                return intArrayOf(left + 1,right + 1)
            } else {
                right--
            }
        }

        return intArrayOf()
    }
}
```

