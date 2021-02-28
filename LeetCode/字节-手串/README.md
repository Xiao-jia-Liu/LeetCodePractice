##### 题目描述：

作为一个手串艺人，有金主向你订购了一条包含n个杂色串珠的手串——每个串珠要么无色，要么涂了若干种颜色。为了使手串的色彩看起来不那么单调，金主要求，手串上的任意一种颜色（不包含无色），在任意连续的m个串珠里至多出现一次（注意这里手串是一个环形）。手串上的颜色一共有c种。现在按顺时针序告诉你n个串珠的手串上，每个串珠用所包含的颜色分别有哪些。请你判断该手串上有多少种颜色不符合要求。即询问有多少种颜色在任意连续m个串珠中出现了至少两次。



##### 分析：

使用HashSet统计错误的颜色。使用一个数组统计当前区间中的所有颜色值，索引为颜色 - 1，值为已经出现的次数，当一个颜色的值 > 2,则说明这个颜色是错误的，加入HashSet中，最后输出HashSet的大小。写代码时使用的是HashMap，写分析时才发现可以用HashSet



```java
import java.util.*;
 
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int c = sc.nextInt();
        int[] count = new int[c];
        ArrayList<Integer> head = new ArrayList<>();
        ArrayList<ArrayList<Integer>> queue = new ArrayList<>();
        HashMap<Integer,Integer> res = new HashMap<>();
        for (int i = 0; i <= n; i++) {
            if (i >= m) {
                ArrayList<Integer> last = queue.get(0);
                queue.remove(0);
                Iterator<Integer> it = last.iterator();
                while (it.hasNext()) {
                    int num = it.next();
                    if (count[num - 1] >= 1) {
                        count[num - 1]--;
                    }
                }
                if (i == n) {
                    break;
                }
            }
 
            ArrayList<Integer> cur = new ArrayList<Integer>();
            int curColorSize = sc.nextInt();
            for (int j = 0; j < curColorSize; j++) {
                int currentColor = sc.nextInt();
                if (i == 0) {
                    head.add(currentColor);
                }
                cur.add(currentColor);
                count[currentColor - 1]++;
                if (count[currentColor - 1] > 1) {
                    res.put(currentColor, 1);
                }
            }
            queue.add(cur);
        }
        Iterator<Integer> it = head.iterator();
        while (it.hasNext()) {
            int color = it.next();
            count[color - 1] = count[color - 1] + 1;
            if (count[color - 1] > 1) {
                res.put(color, 1);
            }
        }
        System.out.println(res.size());
    }
}
```

