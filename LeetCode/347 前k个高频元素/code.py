from collections import Counter
import numpy as np
# 统计列表中元素出现的频

class Solution(object):
    def topKFrequent(self, nums, k):
        counter = Counter(nums)
        a = sorted(counter.items(), key=lambda x: x[1], reverse=True)
        result = np.asarray(a)
        x = result[0:k,0]
        return x.tolist()