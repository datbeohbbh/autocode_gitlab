package com.efimchick.ifmo.streams.countwords;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.*;
import java.util.StringTokenizer;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static void main(String args[]){
        //String s = "ajdaskdn,8 aa,8,***,fasdf:fsadf   dadssd-----0101        dasdsadasdsa";
        String s = "1,2,3,for,4,5,       6,     ЦЦЦЦЦц      йййййй      7,8,ййййрооммсс:d;'[;ryr'''';'=23123123-34=3=-4dasdsad";
        //String[] ss = s.split("[,*-:\\d ]");
        String[] dasds = s.split("[\\Wfor\\W]");
        String[] ss = s.split("[^\\w\\d]");
        String k = s.replaceAll("[^\\w]","+");
        k = k.replaceAll("[+\\d]+"," ");
        ArrayList <String> li = new ArrayList<String>();
        ArrayList <String[]> x = new ArrayList<>();
        x.add(ss);
        Arrays.stream(ss).forEach(cur -> System.out.println(cur));
        /*IntStream is = s.chars();
        is.forEach(c -> System.out.println(Character.toChars(c)));*/

        //Stream<String> str = Pattern.compile(",").splitAsStream("a,b,c");
        //System.out.print(k);
       /* String aa = "a          b";
        String[] sss = aa.split("[\\s]+");
        int xxx = 1;
        System.out.println(xxx);
        */
        System.out.println("------------------------------------------------------");
        Words tmp = new Words();
        List <String> f = new ArrayList<>();
        f.add(s);
        String ans = tmp.countWords(f);
        System.out.println(ans);
    }
}



       /* ar = lines.stream().map(cur -> {
            cur = cur.replaceAll("[^а-яА-Яa-zA-Z]","+");
            cur = cur.replaceAll("[+\\d]+"," ");
            return cur;
        }).map(cur -> cur.split("[\\s]+")).collect(Collectors.toList());

        //forEach
        ar.stream().forEach(cur -> {
           Arrays.stream(cur).filter(iter -> !iter.isEmpty())
                             .map(iter -> iter.toLowerCase())
                             .forEach(iter -> lwa.add(iter));
        });
        */

        /*
        //forEach
        lwa.stream().forEach(cur -> {
            cnt.put(cur,(cnt.containsKey(cur) ? cnt.get(cur) : 0) + 1);
        });
         */
