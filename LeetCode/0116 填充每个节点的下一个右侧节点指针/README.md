##### 题目描述

给定一个**完美二叉树**，其所有叶子节点都在同一层，每个父节点都有两个子节点。二叉树定义如下：

```
struct Node {
  int val;
  Node *left;
  Node *right;
  Node *next;
}
```

填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 `NULL`。

初始状态下，所有 next 指针都被设置为 `NULL`。

 

**示例：**

![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2019/02/15/116_sample.png)



##### 层次遍历



层次遍历时，右边的节点就是右侧节点

```kotlin
/**
 * Definition for a Node.
 * class Node(var `val`: Int) {
 *     var left: Node? = null
 *     var right: Node? = null
 *     var next: Node? = null
 * }
 */

class Solution {
    fun connect(root: Node?): Node? {
        if (root == null) {
            return root
        }

        val queue = ArrayList<Node>()
        queue.add(root!!)
        var index = 1
        var layer = 1

        while (!queue.isEmpty()) {
            val current = queue.first()
            queue.removeAt(0)

            val left = current.left

            if (left != null) {
                queue.add(left)
                queue.add(current.right!!)
            }



            if ((index + 1) == (2 * layer)) {
                index++
                layer *= 2
                continue
            } else {
                index++
                current.next = queue.first()
            }
        }

        return root
    }
}
```

##### 层次指针

只有两种next指针，一种是指向同一个父节点的右子树，一种是跨越了父节点的左节点，但是如果知道了上一层的所有节点，那么，右子树的右侧指针其实就是当前节点的父节点的右侧节点的左子树



```kotlin
/**
 * Definition for a Node.
 * class Node(var `val`: Int) {
 *     var left: Node? = null
 *     var right: Node? = null
 *     var next: Node? = null
 * }
 */

class Solution {
    fun connect(root: Node?): Node? {
        if (root == null) {
            return root
        }

        var up = root
        var down = root?.left

        while (down != null) {
            var currentUp = up
            var currentDown = down
            var needRight: Node? = null

            while (currentUp != null) {
                currentUp?.left?.next = currentUp?.right
                needRight?.next = currentUp?.left
                needRight = currentUp?.right 
                currentUp = currentUp?.next
            }

            up = down
            down = down?.left
        }

        return root
    }
}
```

