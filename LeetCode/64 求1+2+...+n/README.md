##### 题目描述：

求 `1+2+...+n` ，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。

 

**示例 1：**

```
输入: n = 3
输出: 6
```

**示例 2：**

```
输入: n = 9
输出: 45
```

 

**限制：**

- `1 <= n <= 10000`





##### 解法一：递归解法 

从格式上很容易看出用递归来解决，问题是怎么确定递归的出口：利用逻辑运算符 && 和 || 来进行判断。因为当 && 运算时，当前一个为false，后面就不在执行 ，|| 反之，前面为true，后面就不再执行

```java
class Solution {
    public int sumNums(int n) {
        int sum = n;
        boolean tem = (sum == 0) || ((sum += sumNums(sum - 1)) == 0);
        return sum;
    }
}
```



##### 解法二：（各种修的同头皮发麻的解法）

```java
class Solution {
    int[] test=new int[]{0};
    public int sumNums(int n) {
        try{
            return test[n];
        }catch(Exception e){
            return n+sumNums(n-1);
        }
    }
}
```

```c++
class Solution {
public:
    int sumNums(int n) {
        bool arr[n][n+1];
        return sizeof(arr)>>1;
    }
};
```



