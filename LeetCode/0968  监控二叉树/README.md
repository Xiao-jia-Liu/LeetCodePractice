##### 题目描述

给定一个二叉树，我们在树的节点上安装摄像头。

节点上的每个摄影头都可以监视**其父对象、自身及其直接子对象。**

计算监控树的所有节点所需的最小摄像头数量。

 

**示例 1：**

![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/12/29/bst_cameras_01.png)

```
输入：[0,0,null,0,0]
输出：1
解释：如图所示，一台摄像头足以监控所有节点。
```

**示例 2：**

![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/12/29/bst_cameras_02.png)

```
输入：[0,0,null,0,null,0,null,null,0]
输出：2
解释：需要至少两个摄像头来监视树的所有节点。 上图显示了摄像头放置的有效位置之一。
```


**提示：**

1. 给定树的节点数的范围是 `[1, 1000]`。
2. 每个节点的值都是 0。

##### 动态规划

对于每个节点，有三种可能的值

a. 根节点放置监控，且覆盖整棵树所需要的摄像头数

b. 覆盖整棵树所需要的摄像头数，不管是否在根节点放置摄像头

c. 覆盖两棵子树的所需摄像头数，无论根节点是否放置摄像头



对于root节点，设左子树对应的状态为(la,lb,lc),右子树对应的状态为(ra,rb,rc),则可得到递推式：

a = lc + rc + 1  :因为，必须要在根节点放置一个摄像头，并且保证左子树和右子树必须被覆盖

b = min(a, min(la + rb,lb + ra))  : 覆盖真棵树所需要的摄像头数，有三种可能，根节点放置，且覆盖整棵树，a节点不放置，左子树根节点放置且保证左子树的两个子树全覆盖，右子树根节点放置且办证右子树的两个子树全覆盖

c = min(a,lb + lc) :  两个子树保证全覆盖，两中情况，根节点放置且保证全覆盖，根节点不放置，两个子树保证全覆盖



（以上分析来自LeetCode官方解析加个人理解：ps：尼玛，笔试遇到这种题直接GG）



```kotlin
/**
 * Example:
 * var ti = TreeNode(5)
 * var v = ti.`val`
 * Definition for a binary tree node.
 * class TreeNode(var `val`: Int) {
 *     var left: TreeNode? = null
 *     var right: TreeNode? = null
 * }
 */
class Solution {
    fun minCameraCover(root: TreeNode?): Int {
        return dfs(root)[1]
    }

    fun dfs(root: TreeNode?): IntArray {
        if (root == null) {
            return intArrayOf(Int.MAX_VALUE / 2, 0, 0)
        }
        
        val left = dfs(root!!.left)
        val right = dfs(root!!.right)
        val tem = IntArray(3){0}
        tem[0] = 1 + left[2] + right[2]
        tem[1] = Math.min(tem[0], Math.min(left[1] + right[0], left[0] + right[1]))
        tem[2] = Math.min(tem[0], left[1] + right[1])
        return tem
    }
}
```

