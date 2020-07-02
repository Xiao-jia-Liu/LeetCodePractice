##### 题目描述

给定一个 *`n x n`* 矩阵，其中每行和每列元素均按升序排序，找到矩阵中第 `k` 小的元素。
请注意，它是排序后的第 `k` 小元素，而不是第 `k` 个不同的元素。

 

**示例：**

```
matrix = [
   [ 1,  5,  9],
   [10, 11, 13],
   [12, 13, 15]
],
k = 8,

返回 13。
```

 

**提示：**
你可以假设 k 的值永远是有效的，`1 ≤ k ≤ n2 `。





##### 二分法



由题目给出的性质可知，这个矩阵内的元素是从左上到右下递增的（假设矩阵左上角为 matrix[0][0]*m**a**t**r**i**x*[0][0]）。以下图为例：

![fig1](https://assets.leetcode-cn.com/solution-static/378/378_fig1.png)

我们知道整个二维数组中 matrix[0][0]*m**a**t**r**i**x*[0][0] 为最小值，matrix[n - 1][n - 1]*m**a**t**r**i**x*[*n*−1][*n*−1] 为最大值，现在我们将其分别记作 l*l* 和 r*r*。

可以发现一个性质：任取一个数 mid*m**i**d* 满足 l\leq mid \leq r*l*≤*m**i**d*≤*r*，那么矩阵中不大于 mid*m**i**d* 的数，肯定全部分布在矩阵的左上角。

例如下图，取 mid=8*m**i**d*=8：

![fig2](https://assets.leetcode-cn.com/solution-static/378/378_fig2.png)

我们可以看到，矩阵中大于 mid*m**i**d* 的数就和不大于 mid*m**i**d* 的数分别形成了两个板块，沿着一条锯齿线将这个矩形分开。其中左上角板块的大小即为矩阵中不大于 mid*m**i**d* 的数的数量。

读者也可以自己取一些 mid*m**i**d* 值，通过画图以加深理解。

我们只要沿着这条锯齿线走一遍即可计算出这两个板块的大小，也自然就统计出了这个矩阵中不大于 mid*m**i**d* 的数的个数了。

走法演示如下，依然取 mid=8*m**i**d*=8：

![fig3](https://assets.leetcode-cn.com/solution-static/378/378_fig3.png)

可以这样描述走法：

- 初始位置在 matrix[n - 1][0]*m**a**t**r**i**x*[*n*−1][0]（即左下角）；
- 设当前位置为 matrix[i][j]*m**a**t**r**i**x*[*i*][*j*]。若 matrix[i][j] \leq mid*m**a**t**r**i**x*[*i*][*j*]≤*m**i**d*，则将当前所在列的不大于 mid*m**i**d* 的数的数量（即 i + 1*i*+1）累加到答案中，并向右移动，否则向上移动；
- 不断移动直到走出格子为止。

我们发现这样的走法时间复杂度为 O(n)*O*(*n*)，即我们可以线性计算对于任意一个 mid*m**i**d*，矩阵中有多少数不大于它。这满足了二分查找的性质。

不妨假设答案为 x*x*，那么可以知道 l\leq x\leq r*l*≤*x*≤*r*，这样就确定了二分查找的上下界。

每次对于「猜测」的答案 mid*m**i**d*，计算矩阵中有多少数不大于 mid*m**i**d* ：

- 如果数量不少于 k*k*，那么说明最终答案 x*x* 不大于 mid*m**i**d*；
- 如果数量少于 k*k*，那么说明最终答案 x*x* 大于 mid*m**i**d*。

这样我们就可以计算出最终的结果 x*x* 了。

```kotlin
class Solution {
    fun kthSmallest(matrix: Array<IntArray>, k: Int): Int {
        var min = matrix[0][0]
        var max = matrix[matrix.size - 1][matrix.size - 1]


        while (min <=  max) {
            val mid = ((max - min) shr 1) + min
            
            if (findCount(matrix,mid) < k) {
                min = mid + 1
            } else {
                max = mid - 1
            }
        }  

        return min 
    }

    fun findCount(m: Array<IntArray>,value: Int): Int {
        if (m.isEmpty()) {
            return 0
        }

        var count = 0

        var col = 0
        var row = m.size - 1

        while (col < m.size && row >= 0) {
            if (m[row][col] <= value) {
                count += (row + 1)
                col++
            } else {
                row --
            }
        }

        return count
    }
}
```

