##### 297.  二叉树的序列化与反序列化

序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。

请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。

**示例:** 

```
你可以将以下二叉树：

    1
   / \
  2   3
     / \
    4   5

序列化为 "[1,2,3,null,null,4,5]"
```





##### 递归暴力解法

```kotlin
/**
 * Definition for a binary tree node.
 * class TreeNode(var `val`: Int) {
 *     var left: TreeNode? = null
 *     var right: TreeNode? = null
 * }
 */

class Codec() {
	// Encodes a URL to a shortened URL.
    fun serialize(root: TreeNode?): String {
        if (root == null) {
            return "*"
        }

        var ret = StringBuilder()
        ret.append(root.`val`)
        ret.append("_")
        ret.append(serialize(root.left))
        ret.append("_")
        ret.append(serialize(root.right))
        ret.append("_")

        return ret.toString()
    }

    // Decodes your encoded data to tree.
    fun deserialize(data: String): TreeNode? {
        return deserialize(StringBuilder(data))
    }

    fun deserialize(data: StringBuilder): TreeNode? {
        var c = getNextToken(data)

        if (c == "*" || c == "") {
            return null
        }

        

        var root = TreeNode(c.toInt())
        root.left = deserialize(data)
        root.right = deserialize(data)

        //printRoot(root)
        return root
    }

    fun printRoot(root: TreeNode?) {
        if (root == null) {
            return
        }

        println(root.`val`)
        printRoot(root.left)
        printRoot(root.right)
    }

    fun getNextToken(data: StringBuilder): String {
        while(data.length > 0 && data[0] == '_') {
            data.delete(0,1)
        }

        if (data.length == 0) {
            return ""
        }

        var tems = data.split("_")
        var ret = tems[0]
        data.delete(0,(ret.length + 1))
        return ret
    }
}

/**
 * Your Codec object will be instantiated and called as such:
 * var obj = Codec()
 * var data = obj.encode(longUrl)
 * var ans = obj.decode(data)
 */
```





##### 用栈将降低递归复杂度

```kotlin
/**
 * Definition for a binary tree node.
 * class TreeNode(var `val`: Int) {
 *     var left: TreeNode? = null
 *     var right: TreeNode? = null
 * }
 */

class Codec() {
	// Encodes a URL to a shortened URL.
    fun serialize(root: TreeNode?): String {
        if (root == null) {
            return "*"
        }

        var ret = StringBuilder()
        /*ret.append(root.`val`)
        ret.append("_")
        ret.append(serialize(root.left))
        ret.append("_")
        ret.append(serialize(root.right))
        ret.append("_")*/

        var stack = Stack<TreeNode?>()
        stack.push(root)

        while (!stack.isEmpty()) {
            var node = stack.pop()
            if (node == null) {
                ret.append("*")
                ret.append("_")
                continue
            }

            stack.push(node.right)
            stack.push(node.left)

            ret.append(node.`val`)
            ret.append("_")
        }

        return ret.toString()
    }

    // Decodes your encoded data to tree.
    fun deserialize(data: String): TreeNode? {
        return deserialize(StringBuilder(data))
    }

    fun deserialize(data: StringBuilder): TreeNode? {
        var c = getNextToken(data)

        if (c == "" || c == "*") {
            return null
        }

        var root = TreeNode(c.toInt())

        var stack = Stack<Node?>()
        stack.push(Node(root,1))
        c = getNextToken(data)

        while (c != "") {
            var currentNode: TreeNode? = null
            var lastNode: Node? = null

            if (!stack.isEmpty()) {
                lastNode = stack.pop()
            }

            if (c == "*") {
                if (lastNode != null) {
                    if (lastNode.lr == 1) {
                        lastNode.lr = 2
                        stack.push(lastNode)
                    } else {
                        while (!stack.isEmpty()) {
                            var parentNode = stack.pop()
                            if (parentNode?.lr == 1) {
                                parentNode?.lr = 2
                                stack.push(parentNode)
                                break
                            }
                        }
                    }
                }
            } else {
                currentNode = TreeNode(c.toInt())

                if (lastNode != null) {
                    if (lastNode.lr == 1) {
                        lastNode.node.left = currentNode
                    } else {
                        lastNode.node.right = currentNode
                    }

                    stack.push(lastNode)
                }

                stack.push(Node(currentNode,1))
            }

            c = getNextToken(data)
        }

        /*if (c == "*" || c == "") {
            return null
        }



        var root = TreeNode(c.toInt())
        root.left = deserialize(data)
        root.right = deserialize(data)*/

        //printRoot(root)
        return root
    }

    data class Node(val node: TreeNode,var lr: Int) {

    }

    fun printRoot(root: TreeNode?) {
        if (root == null) {
            return
        }

        println(root.`val`)
        printRoot(root.left)
        printRoot(root.right)
    }

    fun getNextToken(data: StringBuilder): String {
        if (data.length == 0) {
            return ""
        }

        while (data[0] == '_') {
            data.delete(0,1)

            if (data.length == 0) {
                return ""
            }
        }

        var index = 0
        var ret = StringBuilder()

        while (index < data.length && data[index] != '_') {
            ret.append(data[index])
            index++
        }

        data.delete(0,index)

        return ret.toString()
    }
}

/**
 * Your Codec object will be instantiated and called as such:
 * var obj = Codec()
 * var data = obj.encode(longUrl)
 * var ans = obj.decode(data)
 */
```

