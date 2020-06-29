package com.efimchick.ifmo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(String args[]){
        Collecting c = new Collecting();
        //System.out.println(c.sum(IntStream.iterate(1, i -> i + 1).limit(5)));
        List <String> li = new ArrayList<>();
        li.add("a");
        li.add("b");
        li.stream().collect(Collectors.toList());
    }
}
