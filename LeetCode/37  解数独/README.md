##### 题目描述

编写一个程序，通过已填充的空格来解决数独问题。

一个数独的解法需**遵循如下规则**：

1. 数字 `1-9` 在每一行只能出现一次。
2. 数字 `1-9` 在每一列只能出现一次。
3. 数字 `1-9` 在每一个以粗实线分隔的 `3x3` 宫内只能出现一次。

空白格用 `'.'` 表示。

![img](http://upload.wikimedia.org/wikipedia/commons/thumb/f/ff/Sudoku-by-L2G-20050714.svg/250px-Sudoku-by-L2G-20050714.svg.png)

一个数独。

![img](http://upload.wikimedia.org/wikipedia/commons/thumb/3/31/Sudoku-by-L2G-20050714_solution.svg/250px-Sudoku-by-L2G-20050714_solution.svg.png)

答案被标成红色。

**Note:**

- 给定的数独序列只包含数字 `1-9` 和字符 `'.'` 。
- 你可以假设给定的数独只有唯一解。
- 给定数独永远是 `9x9` 形式的。

##### 初步解法

每个格子里，本质就是一个行列和本大格的与集合，然后在总的1-9的集合的差集。

```kotlin
class Solution {
    val allNodes = HashMap<Int,Node>()
    val allNumber = HashSet<Char>()

    fun solveSudoku(board: Array<CharArray>): Unit {
        for (item in board) {
                for (i in item) {
                    if (i != '.') {
                        allNumber.add(i)
                    }
                }
            }

            for (i in board.indices) {
                for (j in board[i].indices) {
                    if (board[i][j] == '.') {
                        allNodes[i * 10 + j] = getNodeInfo(board,i,j,allNumber)
                    }
                }
            }

            sudoku(board,allNodes,allNumber)
    }

    fun getNodeInfo(board: Array<CharArray>,i: Int,j: Int,allNumber: HashSet<Char>): Node {
        val temCol = HashSet<Char>()
        val temRow = HashSet<Char>()
        val temInner = HashSet<Char>()

        //col
        for (temI in 0 .. 8) {
            temCol.add(board[temI][j])
        }

        //row
        for (temJ in 0 .. 8) {
            temRow.add(board[i][temJ])
        }

        //inner
        val r = i / 3
        val c = j / 3

        for (temI in r * 3 until r * 3 + 3) {
            for (temJ in c * 3 until c * 3 + 3) {
                temInner.add(board[temI][temJ])
            }
        }

        /*val row = HashSet(allNumber)
        val col = HashSet(allNumber)
        val inner = HashSet(allNumber)
    */
    /*    row.removeAll(temRow)
        col.removeAll(temCol)
        inner.removeAll(temInner)*/

        return Node(temRow,temCol,temInner)
    }

        fun sudoku(board: Array<CharArray>, allNode: HashMap<Int,Node>, allNumber:HashSet<Char>) {
        var remainNode = allNode.size


        while (remainNode != 0) {
            for ((key, value) in allNode) {
                if (value.accept.size == 1) {
                    val i = key / 10
                    val j = key % 10

                    board[i][j] = value.accept.first()

                    //row update
                    for (temJ in 0 .. 8) {
                        if (temJ == j) {
                            continue
                        }

                        val n = allNode[i * 10 + temJ]

                        if (value.accept.isEmpty()) {
                            n?.updateAccept()
                            break
                        }

                        n?.row?.add(value.accept.first())
                        n?.updateAccept()
                    }

                    //col update
                    for (temI in 0 .. 8) {
                        if (temI == i) {
                            continue
                        }

                        val n = allNode[temI * 10 + j]

                        if (value.accept.isEmpty()) {
                            n?.updateAccept()
                            break
                        }

                        n?.col?.add(value.accept.first())
                        n?.updateAccept()
                    }

                    //inner update
                    val remainI = i / 3
                    val remainJ = j / 3

                    for (temI in remainI * 3 until remainI * 3 + 3) {
                        for (temJ in remainJ * 3 until remainJ * 3 + 3) {
                            if (temI == i && temJ == j) {
                                continue
                            }

                            val n = allNode[temI * 10 + temJ]

                            if (value.accept.isEmpty()) {
                                n?.updateAccept()
                                break
                            }

                            n?.inner?.add(value.accept.first())
                            n?.updateAccept()
                        }
                    }

                    //remove the current node
                    value.accept.clear()
                    remainNode--
                }
            }
        }


    }

    inner class Node(val row: HashSet<Char>,val col: HashSet<Char>,val inner: HashSet<Char>) {
        var accept: HashSet<Char> = HashSet(allNumber)

        init {
            updateAccept()
        }

        fun updateAccept() {
            accept.removeAll(row)
            accept.removeAll(col)
            accept.removeAll(inner)
        }
    }
}
```

但是存在问题，如果说每个都不满足能唯一确定一个数，则会一直循环，无法退出