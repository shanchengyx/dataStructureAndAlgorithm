package com.gyx.greedy;

import java.util.*;

/**
 * 贪心算法
 */
public class GreedyDemo {
    public static void main(String[] args) {
        //创建广播电台
        Map<String, Set<String>> broadcast = new HashMap<>();
        //设置每个电台能覆盖的区域，用集合表示，等下要放入broadcast
        Set<String> set1 = new HashSet<>();
        set1.add("北京");
        set1.add("上海");
        set1.add("天津");

        Set<String> set2 = new HashSet<>();
        set2.add("广州");
        set2.add("北京");
        set2.add("深圳");

        Set<String> set3 = new HashSet<>();
        set3.add("成都");
        set3.add("上海");
        set3.add("杭州");

        Set<String> set4 = new HashSet<>();
        set4.add("上海");
        set4.add("天津");

        Set<String> set5 = new HashSet<>();
        set5.add("杭州");
        set5.add("大连");

        //将set加入到broadcast
        broadcast.put("k1", set1);
        broadcast.put("k2", set2);
        broadcast.put("k3", set3);
        broadcast.put("k4", set4);
        broadcast.put("k5", set5);
        //创建一个集合，把所有地区放进去
        Set<String> allArea = new HashSet<>();
        String[] areaArray = {"北京", "上海", "天津", "广州", "深圳", "成都", "杭州", "大连"};
        for (int i = 0; i < areaArray.length; i++) {
            allArea.add(areaArray[i]);
        }

        List<String> result = greedy(broadcast, allArea);
        System.out.println(result.toString());
    }

    public static List<String> greedy(Map<String, Set<String>> broadcast, Set<String> allArea) {
        //select用于存放选择的电台
        List<String> select = new ArrayList<>();
        //定义一个临时集合，存放遍历过程中当前电台覆盖地区和剩余地区的交集
        Set<String> temp = new HashSet<>();
        //max用来记录此次遍历能覆盖的最大区域数
        int max = 0;
        //定义一个maxKey，保存遍历过程中覆盖剩余区域最多电台
        String maxKey = "";
        while (allArea.size() != 0) {//不等于0就说明还有电台需要覆盖，继续循环
            temp.clear();//清空临时集合
            max = 0;//初始化
            maxKey = null;//也是初始化
            for (String key : broadcast.keySet()) {//按电台的key遍历电台集合
                Set<String> area = broadcast.get(key);//该电台能够覆盖的区域
                temp.addAll(area);//将该电台能覆盖的区域放入临时集合
                temp.retainAll(allArea);//与allArea求交，得到该电台能覆盖的剩余区域数量
                if (temp.size() > max) {//如果该电台能覆盖的区域更大，就更新max和maxKey
                    max = temp.size();
                    maxKey = key;
                }
            }
            if (maxKey != null) {//如果maxKey不等于空，说明此次遍历找到了合适的电台
                select.add(maxKey);//加入已选择的电台数组
                allArea.removeAll(broadcast.get(maxKey));//将它能覆盖的区域从剩余区域中移出
            }
        }
        return select;
    }
}
