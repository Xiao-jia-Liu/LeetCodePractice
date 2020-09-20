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

##### 递归

很简单的递归就是，填完一个格子后，任然是一个数独，所以，只需要递归填完所有的格子就ok，一开始先找出需要填的格子的集合，然后作为参数传入递归函数，然后，对每个格子都从1 - 9填写，判断是否符合规则，规则就是横、竖、小格子里都不能出现重复的，直到最后需要填写的格子数为0则表示填写完毕



```kotlin
class Solution {
    fun solveSudoku(board: Array<CharArray>): Unit {
            val need = ArrayList<Int>()

            for (i in 0 .. 8) {
                for (j in 0 .. 8) {
                    if (board[i][j] == '.') {
                        need.add(i * 10 + j)
                    }
                }
            }

            val temB = Array<CharArray>(9){CharArray(9){'.'}}

            for (i in 0 .. 8) {
                for (j in 0 .. 8) {
                    temB[i][j] = board[i][j]
                }
            }

            dfs(temB,need,board)
    }

    fun dfs(board: Array<CharArray>,need: ArrayList<Int>,res: Array<CharArray>) {
        if (need.size == 0) {

            for (i in 0 ..8) {
                for (j in 0 .. 8) {
                    res[i][j] = board[i][j]
                }
            }
            return
        }

        for (number in 1 .. 9) {
            val tem = need.first()

            val i = tem / 10
            val j = tem % 10

            if (accept(board,i,j,number.toString()[0])) {
                need.removeAt(0)
                board[i][j] = number.toString()[0]
                dfs(board,need,res)
                board[i][j] = '.'
                need.add(0,tem)
            }


        }

    }

    fun accept(board: Array<CharArray>,i: Int,j: Int,number: Char): Boolean {
        for (temI in 0 .. 8) {
            if (board[temI][j] == number) {
                return false
            }
        }

        for (temJ in 0 .. 8) {
            if (board[i][temJ] == number) {
                return false
            }
        }


        val r = i / 3
        val c = j / 3

        for (temI in r * 3 .. r * 3 + 2) {
            for (temJ in c * 3 .. c * 3 + 2) {
                if (board[temI][temJ] == number) {
                    return false
                }
            }
        }

        return true
    }

}
```

优化方法后面再说