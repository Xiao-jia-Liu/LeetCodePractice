##### 题目描述：

给定一个整数数组 `nums` 和一个目标值 `target`，请你在该数组中找出和为目标值的那 **两个** 整数，并返回他们的数组下标。

你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。

 

**示例:**

```
给定 nums = [2, 7, 11, 15], target = 9

因为 nums[0] + nums[1] = 2 + 7 = 9
所以返回 [0, 1]
```





##### 暴力解法：

两重循环，每个数字和第二重循环中的每个数字进行配对，计算和，和为targe则返回将数字下标返回，时间复杂度O（n2）

python版本
```python
class Solution(object):
    def twoSum(self, nums, target):
        for i in range(0,len(nums)):
            for j in range(i+1,len(nums)):
                if nums[i]+nums[j]==target:
                    return [i,j]
```

##### 解法：

增加一个辅助哈希表，key为出现过的数字，value为下标。顺序读取数组，读取到一个数，就在哈希表中寻找是否有该target - 当前数字的key，有就表示两个数字加起来和为target，比对下标，小的下标在前返回。

```java
class Solution {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer,Integer> h = new HashMap<>();
        for(int i = 0; i < nums.length; i++) {
            Integer j = h.get(target - nums[i]);
            if(j != null) {
                int[] ret = new int[2];
                if(j > i) {
                    ret[0] = i;
                    ret[1] = j;
                } else {
                    ret[0] = j;
                    ret[1] = i;
                }
                return ret;
            } else {
                h.put(nums[i],i);
            }
        }
        return null;
    }

}
```

时间复杂度O(n),空间复杂度O(n)

```C++
//C++
    class SolutionByHash {
public:
    vector<int> twoSum(vector<int>& nums, int target) {
        unordered_map<int, int> temp;
        for (int i = 0; i < nums.size(); i++)
        {
            if (temp.find(target - nums[i]) != temp.end())//查找哈希表中是否存在符合的
            {
            return { temp[target - nums[i]],i };  //如果有返回下标
            }
            else {
                temp[nums[i]] = i;  // key为数组元素 ，value为数组下标i
            }
        }
        return {}; //不存在 返回空
    }
};
```

时间复杂度O(n),空间复杂度O(n)