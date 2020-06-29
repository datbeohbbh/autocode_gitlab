package com.efimchick.ifmo.collections.countwords;

import java.util.ArrayList;

public class Main {
    public static void main(String args[]){
        Words w = new Words();
        String a = "abcd,dsadas,dasd : dasd,  ,";
        ArrayList <String> li = new ArrayList<>();
        for(int i = 0;i < 10;++i){
            li.add(a);
        }
        String t = w.countWords(li);
        System.out.print(t);
    }
}
