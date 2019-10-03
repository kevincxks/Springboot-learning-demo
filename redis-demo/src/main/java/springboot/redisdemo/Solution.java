package springboot.redisdemo;

import javax.swing.tree.TreeNode;
import java.util.*;
import java.util.stream.Collectors;

class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        Map<Integer,Integer> map = new HashMap<>();

        for (int i = 0; i < nums1.length; i++) {
            if(map.containsKey(nums1[i])){
                map.put(nums1[i],map.get(nums1[i])+1);
            }else{
                map.put(nums1[i],1);
            }
        }
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums2.length; i++) {
            if(map.containsKey(nums2[i])){
                list.add(nums2[i]);
                if(map.get(nums2[i])-1==0)
                    map.remove(nums2[i]);
                else
                    map.put(nums2[i],map.get(nums2[i])-1);
            }
        }
        return list.stream().mapToInt(Integer::intValue).toArray();
    }
}