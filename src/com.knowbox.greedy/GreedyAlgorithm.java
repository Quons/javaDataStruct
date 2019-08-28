import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class GreedyAlgorithm {
    //贪心算法
    public static void main(String[] args) {
        //创建广播电台，放入到Map中管理
        HashMap<String, HashSet<String>> broadcasts = new HashMap<>();
        //将各个电台放入到broadcasts
        HashSet<String> hashSet1 = new HashSet<>();
        hashSet1.add("北京");
        hashSet1.add("上海");
        hashSet1.add("天津");
        HashSet<String> hashSet2 = new HashSet<>();
        hashSet2.add("广州");
        hashSet2.add("北京");
        hashSet2.add("深圳");
        HashSet<String> hashSet3 = new HashSet<>();
        hashSet3.add("成都");
        hashSet3.add("上海");
        hashSet3.add("杭州");
        HashSet<String> hashSet4 = new HashSet<>();
        hashSet4.add("上海");
        hashSet4.add("天津");
        HashSet<String> hashSet5 = new HashSet<>();
        hashSet5.add("杭州");
        hashSet5.add("大连");
        broadcasts.put("K1", hashSet1);
        broadcasts.put("k2", hashSet2);
        broadcasts.put("k3", hashSet3);
        broadcasts.put("k4", hashSet4);
        broadcasts.put("k5", hashSet5);
        //创建all areas 存放所有的地区
        HashSet<String> allAreas = new HashSet<>();
        for (Map.Entry<String, HashSet<String>> entry : broadcasts.entrySet()) {
            allAreas.addAll(entry.getValue());
        }
        //创建ArrayList ，存放选择的电台集合
        ArrayList<String> selects = new ArrayList<>();
        //定义一个临时的集合，在遍历过程中，存放遍历过程中电台覆盖的地区和当前还没有覆盖地区的交集
        HashSet<String> tmpSet = new HashSet<>();
        //定义给maxKey，保存在一次遍历过程中，能够覆盖最大未覆盖的地区对应的电台的key
        //如果maxKey不为空，则会加入到selects中
        String maxKey = null;
        HashMap<String, HashSet<String>> tmpCasts = new HashMap<>();//用于存放最大交集
        while (allAreas.size() != 0) {//如果allAreas不为0，则表示还没有覆盖所有的地区
            //每进行一次while要将maxKey置空
            maxKey = null;
            tmpCasts.clear();
            //遍历broadcasts，取出对应的key
            for (String key : broadcasts.keySet()) {
                //每进行一个for，要清空tmpSet
                tmpSet.clear();
                //当前这个key能够覆盖的地区
                HashSet<String> areas = broadcasts.get(key);
                tmpSet.addAll(areas);
                //求出tmpSet 和allAreas集合的交集，交集会赋值给tmpSet
                tmpSet.retainAll(allAreas);
                tmpCasts.put(key, tmpSet);
                //如果当前这个集合包含的未覆盖地区的数量，比maxKey指向的集合地区还多
                //就需要重置maxKey
                if (tmpSet.size() > 0 && (maxKey == null || tmpSet.size() > tmpCasts.get(maxKey).size())) {//这个应该比maxKey指向的交集还要多！！有问题
                    maxKey = key;
                }
            }
            //maxKey!=null,就应该将maxKey 加入到selects
            if (maxKey != null) {
                selects.add(maxKey);
                //将maxKey指向的广播电台覆盖的地区，从allAreas去掉
                allAreas.removeAll(broadcasts.get(maxKey));
            }
        }
        System.out.println("得到的选择结果是" + selects.toString());
    }
}
