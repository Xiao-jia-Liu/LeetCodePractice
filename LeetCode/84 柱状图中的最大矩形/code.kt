class Solution {
    fun largestRectangleArea(heights: IntArray): Int {
        var count = 0
        val stack = Stack<Int>()
        val left = IntArray(heights.size)
        val right = IntArray(heights.size) {_ -> heights.size}

        for (i in heights.indices) {
            val h = heights[i]

            while (!stack.empty() && heights[stack.peek()] >= h) {
                right[stack.peek()] = i
                stack.pop()
            }

            left[i] = if (stack.empty()) -1 else stack.peek()
            stack.push(i)
        }

        for (i in left.indices) {
            count = kotlin.math.max(count,heights[i] * (right[i] - left[i] - 1))
        }

        return count
    }
}