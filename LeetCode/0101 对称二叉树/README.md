##### 题目描述：

给定一个二叉树，检查它是否是镜像对称的。

 

例如，二叉树 `[1,2,2,3,4,4,3]` 是对称的。

```
    1
   / \
  2   2
 / \ / \
3  4 4  3
```

 

但是下面这个 `[1,2,2,null,3,null,3]` 则不是镜像对称的:

```
    1
   / \
  2   2
   \   \
   3    3
```



##### 递归解法：

其实本质上就是一个节点的左节点值等于右节点且左节点堆成右节点。所以可以用两个指针p和q，一个向左节点前进，一个向右节点前进。

```kotlin
class Solution {
    fun isSymmetric(root: TreeNode?): Boolean {
        return check(root,root)
    }

    fun check(left: TreeNode?,right: TreeNode?): Boolean {
        if (left == null && right == null) {
            return true
        }

        if (left == null || right == null) {
            return false
        }

        return left?.`val` == right?.`val` && check(left?.left,right?.right) && check(left?.right,right?.left)
    }
}
```



##### 迭代解法：

利用队列将递归改为迭代

```kotlin
class Solution {
    fun isSymmetric(root: TreeNode?): Boolean {
        return check(root,root)
    }

    fun check(left: TreeNode?,right: TreeNode?): Boolean {
        var queue = LinkedList<TreeNode?>() as Queue<TreeNode?>
        queue.offer(left)
        queue.offer(right)

        while (!queue.isEmpty()) {
            var l = queue.poll()
            var r = queue.poll()

            if (l == null && r == null) {
                continue
            }

            if (l == null || r == null || l?.`val` != r?.`val`) {
                return false
            }

            queue.offer(l?.left)
            queue.offer(r?.right)
            queue.offer(l?.right)
            queue.offer(r?.left)
        }

        return true
    }
}
```

