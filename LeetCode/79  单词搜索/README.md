##### 题目描述

给定一个二维网格和一个单词，找出该单词是否存在于网格中。

单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。

 

**示例:**

```
board =
[
  ['A','B','C','E'],
  ['S','F','C','S'],
  ['A','D','E','E']
]

给定 word = "ABCCED", 返回 true
给定 word = "SEE", 返回 true
给定 word = "ABCB", 返回 false
```

 

**提示：**

- `board` 和 `word` 中只包含大写和小写英文字母。
- `1 <= board.length <= 200`
- `1 <= board[i].length <= 200`
- `1 <= word.length <= 10^3`





##### 暴力破解法

先选择一个起点，起点的char 等于word的开头个char，则结果就等于上下左右的或运算，及当前相等，且下一个字母与当前点的上下左右某一个相等，由此递归计算

```kotlin
class Solution {
    fun exist(board: Array<CharArray>, word: String): Boolean {
        if (board.size <= 0) {
            return false
        }

        val x = board.size
        val y = board[0].size

        for (i in 0 until x) {
            for (j in 0 until y) {
                if (board[i][j] == word[0]) {
                    if (checkExist(board, i, j, word, 0)) {
                        return true
                    }
                }
            }
        }

        return false
    }

    fun checkExist(board: Array<CharArray>,x: Int,y: Int,word: String,index: Int): Boolean {
        if (x < 0 || y < 0 || x >= board.size || y >= board[x].size) {
            return false
        }

        if (board[x][y] != word[index]) {
            return false
        }

        if (index + 1 >= word.length) {
            return true
        }

        val up = if (x - 1 < 0) {
            false
        } else {
            val char = board[x][y]
            board[x][y] = ' '
            val tem = checkExist(board, x - 1, y, word, index + 1)
            board[x][y] = char
            tem
        }

        if (up) {
            return true
        }

        val right = if (y + 1 >= board[x].size) {
            false
        } else {
            val char = board[x][y]
            board[x][y] = ' '
            val tem = checkExist(board, x, y + 1, word, index + 1)
            board[x][y] = char
            tem
        }

        if (right) {
            return true
        }

        val left = if (y - 1 < 0) {
            false
        } else {
            val char = board[x][y]
            board[x][y] = ' '
            val tem = checkExist(board, x, y - 1, word, index + 1)
            board[x][y] = char
            tem
        }

        if (left) {
            return true
        }

        val down = if (x + 1 >= board.size) {
            false
        } else {
            val char = board[x][y]
            board[x][y] = ' '
            val tem = checkExist(board, x + 1, y, word, index + 1)
            board[x][y] = char
            tem
        }

        if (down) {
            return true
        }

        return false
    }
}
```