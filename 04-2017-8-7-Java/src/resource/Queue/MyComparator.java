package resource.Queue;

import resource.List.Pet;

import java.util.*;

/**
 * Created by asus on 2017/8/8.
 */
class MyComparator implements Comparator {

    public static Map<String, Integer> heatTable = new HashMap<>();

    static {
        heatTable.put("小猫",new Integer(5));
        heatTable.put("小狗",new Integer(2));
        heatTable.put("鹦鹉",new Integer(3));
        heatTable.put("麻雀",new Integer(4));
    }
    /**
     * 实现Comparator接口，将1设置为最大值
     *
     * @param o1
     * @param o2
     * @return
     */
    @Override
    public int compare(Object o1, Object o2) {
        Pet pet1 = (Pet) o1;
        Pet pet2 = (Pet) o2;

        return heatTable.get(pet1.getName()) - heatTable.get(pet2.getName());
    }

    public static void main(String[] args) {
    }
}


