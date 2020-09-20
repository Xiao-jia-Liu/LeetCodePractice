##### 题目描述

在未排序的数组中找到第 **k** 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。

**示例 1:**

```
输入: [3,2,1,5,6,4] 和 k = 2
输出: 5
```

**示例 2:**

```
输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
输出: 4
```

**说明:**

你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。





##### 题解：快排思想：快选

快排中，每次划分后，选定的元素就确定了正确的位置，则每次都随机选择一个元素，只划分，不进行排序，返回这个元素的下标，如果这个元素的下标等于数组长度 - k，则这个元素就是所要找的元素，当然也可以在划分时按从大到小的顺序划分，则此时返回的下标等于k时就是所要找的元素



```kotlin
class Solution {
    fun findKthLargest(nums: IntArray, k: Int): Int {
        return quickSelect(nums,0,nums.size - 1,nums.size - k)
    }

    fun quickSelect(nums: IntArray,l: Int,r: Int,index: Int): Int{
        val q = randomPartion(nums,l,r)

        return if (q == index) {
            nums[q]
        } else {
            if (q < index) quickSelect(nums,q + 1,r,index) else quickSelect(nums,l,q - 1,index)
        }
    }

    fun randomPartion(nums: IntArray,l: Int,r: Int): Int{
        val start = Random().nextInt(r - l + 1) + l
        swap(nums,start,r)
        return partion(nums,l,r)
    }

    fun swap(nums: IntArray,l: Int,r: Int) {
        val tem = nums[l]
        nums[l] = nums[r]
        nums[r] = tem
    }

    fun partion(nums: IntArray,l: Int,r: Int): Int {
        val target = nums[r]
        var i = l - 1

        for (j in l until r) {
            if (target >= nums[j]) {
                swap(nums,++i,j)
            }
        }

        swap(nums,++i,r)
        return i
    }
}
```





##### 堆排序思想：

