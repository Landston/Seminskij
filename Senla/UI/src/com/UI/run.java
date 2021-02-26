package com.UI;

import java.util.*;
import java.util.stream.Collectors;

public class Run {


    public static void main(String[] args) {
        //  MenuController menuController = MenuController.getInstance();

        //    menuController.run();


        String line = "Irina love tea, cake, apple";
        Map<Integer, Integer> map = new HashMap<>();
        List<String> list = new ArrayList<>();
        list.addAll(Arrays.asList(line.split(" ")));
        
        for (String item : list) {

            if (map.containsKey(item.length())) {
                Integer value = map.get(item.length())+1;

                map.replace(item.length(),value);
                // This way
            } else {
                // then we need to create key if it is new
                map.put(item.length(), 1);
            }


        }
        System.out.println(map);

    }
}
