##### 题目描述



给定一个整数 *n*，求以 1 ... *n* 为节点组成的二叉搜索树有多少种？

**示例:**

```
输入: 3
输出: 5
解释:
给定 n = 3, 一共有 5 种不同结构的二叉搜索树:

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3
```



##### 动态规划

其实就是，给定一个二叉树的前序，有多少中中序遍历，然后又本质是栈的出入有多少种。很简单，当 n = 1时，就一种，当 n  >  1时，就是先拿出一个头，然后剩下的组合，即 左边 0 ，右边 n - 1,。。左边 1 ，右边 n - 2.所以递推公式就得出了



```kotlin
class Solution {
    fun numTrees(n: Int): Int {
        if (n <= 1) {
            return 1
        }

        val array = Array<Int>(n + 1){1}

        for (i in 2 .. n) {
            array[i] = 0

            for (j in 0 until i) {
                array[i] += (array[j] * array[i - j - 1])
            }
        }

        return array[n]
    }
}
```







##### 数学公式

Catalan数，直接公式求解，便于计算的Catalan数为：
$$
C_{0} = 1  \quad  C_{n+1} = \frac{2(2n + 1)}{n + 2}C_{n}
$$


```kotlin
class Solution {
    fun numTrees(n: Int): Int {
        var ret: Long = 1

        for (i in 0 until n) {
            ret = ret * (2 * (2 * i.toLong() + 1)) / (i.toLong() + 2)
        }

        return ret.toInt()
    }
}
```

