// Lab18av110.java
// This is the 110 point version.
// Bryce Wilson
// May 26, 2019
// APCS, Mr. Robinson

package ca.thenetworknerds.APCS;

import java.util.ArrayList;
import java.util.Arrays;

public class Lab18bv100 {
    public static void main(String[] args) {
        int[] jsaList1 = {101, 105, 115, 125, 145, 165, 175, 185, 195, 225, 235, 275, 305, 315, 325, 335, 345, 355, 375, 385};
        int[] jsaList2 = {110, 120, 130, 140, 150, 160, 170, 180, 190, 200, 210, 220, 230, 240, 250, 270, 280, 320, 350, 400};

        Array list1 = new Array(jsaList1, "List #1");
        Array list2 = new Array(jsaList2, "List #2");
        Array list3 = new Array("Merged List");

        list3.merge(list1, list2);

        list1.display();
        list2.display();
        list3.display();

    }

}


class Array {
    private ArrayList<Integer> list;
    private String listName;

    Array(String ln) {
        list = new ArrayList<>();
        listName = ln;
    }

    Array(int[] jsArray, String ln) {
        list = new ArrayList<>();
        listName = ln;
        Arrays.stream(jsArray).forEach(list::add);
    }

    public void display() {
        System.out.println("\n" + listName + ":\n");
        System.out.println(list + "\n");
    }

    public void merge(Array first, Array second) {
        ArrayList<Integer> firstList = new ArrayList<>(first.list);
        ArrayList<Integer> secondList = new ArrayList<>(second.list);
        while (!(firstList.isEmpty() || secondList.isEmpty())) {
            if (firstList.get(0) < secondList.get(0)) {
                list.add(firstList.remove(0));
            } else {
                list.add(secondList.remove(0));
            }
        }
        list.addAll(firstList);
        list.addAll(secondList);
    }


}
