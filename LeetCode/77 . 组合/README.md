##### 题目描述

给定两个整数 *n* 和 *k*，返回 1 ... *n* 中所有可能的 *k* 个数的组合。

**示例:**

```
输入: n = 4, k = 2
输出:
[
  [2,4],
  [3,4],
  [2,3],
  [1,2],
  [1,3],
  [1,4],
]
```





##### 递归 + 剪枝法

递归的思想：

```
backtracking() {
    if (终止条件) {
        存放结果;
    }

    for (选择：选择列表（可以想成树中节点孩子的数量）) {
        递归，处理节点;
        backtracking();
        回溯，撤销处理结果
    }
}
```

在遍历过程中，每次都是 for (int i = startIndex; i <= n; i++)  ，在startIndex  <  k - path.size时是完全没有意义的

来举一个例子，n = 4， k = 4的话，那么从2开始的遍历都没有意义了。

已经选择的元素个数：path.size();

要选择的元素个数 : k - path.size();

在集合n中开始选择的起始位置 : n - (k - path.size());

因为起始位置是从1开始的，而且代码里是n <= 起始位置，所以 集合n中开始选择的起始位置 : n - (k - path.size()) + 1;



所以最终代码是：

```kotlin
class Solution {
    val res = ArrayList<ArrayList<Int>>()
    val path = ArrayList<Int>()

    fun combine(n: Int, k: Int): List<List<Int>> {
        backTracking(n,k,1)
        return res
    }

    fun backTracking(n: Int,k: Int,start: Int) {
        if (k == path.size) {
            val tem = ArrayList<Int>(path)
            res.add(tem)
            return
        }

        for (i in start .. n - (k - path.size) + 1) {
            path.add(i)
            backTracking(n,k,i + 1)
            path.removeAt(path.lastIndex)
        }
    }
}
```

