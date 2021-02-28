##### 题目描述：

为了不断优化推荐效果，今日头条每天要存储和处理海量数据。假设有这样一种场景：我们对用户按照它们的注册时间先后来标号，对于一类文章，每个用户都有不同的喜好值，我们会想知道某一段时间内注册的用户（标号相连的一批用户）中，有多少用户对这类文章喜好值为k。因为一些特殊的原因，不会出现一个查询的用户区间完全覆盖另一个查询的用户区间(不存在L1<=L2<=R2<=R1)。



##### 分析：

使用一个HashMap保存每个喜好的用户的链表，从 0 开始遍历用户喜好，链表升序排列。便利推荐，用K从HashMap中取出链表，遍历聊表，选出位于L 、R闭区间的数量

```java
import java.util.ArrayList;
import java.lang.Integer;
import java.util.List;
import java.util.Scanner;
import java.util.Arrays;
import java.util.HashMap;
import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            int f = scanner.nextInt();
            if (map.containsKey(f)) {
                map.get(f).add(i);
            } else {
                ArrayList<Integer> list = new ArrayList<>();
                list.add(i);
                map.put(f, list);
            }
        }
        int p = scanner.nextInt();
        for (int i = 0; i < p; i++) {
            int l = scanner.nextInt();
            int r = scanner.nextInt();
            int k = scanner.nextInt();
            int sum = 0;
            ArrayList<Integer> list = map.get(k);
            if (list != null) {
                Iterator<Integer> it = list.iterator();
                while (it.hasNext()) {
                    int num = it.next();
                    if (l <= num && num <= r) {
                        sum++;
                    }
                }
            }
            System.out.println(sum);
        }
    }
}
```

