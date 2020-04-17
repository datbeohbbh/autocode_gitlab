package com.efimchick.ifmo.streams.countwords;

import java.util.*;
import java.util.stream.Collectors;

public class Words {

    private TreeMap <String,Integer> cnt = new TreeMap<>();
    private List <String> lwa = new ArrayList<>();
    private List <Pair> f = new ArrayList<>();
    private String ans = "";

    public String countWords(List<String> lines) {
        String str = "";
        str = lines.stream().map(cur -> {
            cur = cur.replaceAll("[^а-яА-Яa-zA-Z]","+");
            cur = cur.replaceAll("[+\\d]+"," ");
            return cur;
        }).collect(Collectors.joining(" "));

        List <String> li = new ArrayList<>();
        li = Arrays.asList(str.split("\\s+"));

        lwa = li.stream().filter(cur -> !cur.isEmpty())
                         .map(cur -> cur.toLowerCase())
                         .collect(Collectors.toList());

        lwa.stream().map(cur -> {
            cnt.put(cur,(cnt.containsKey(cur) ? cnt.get(cur) : 0) + 1);
            return new Pair(cur,cnt.get(cur));
        }).collect(Collectors.toList());

        Set <String> g = cnt.keySet();

        f = g.stream().filter(cur -> (cur.length() >= 4 && cnt.get(cur) >= 10))
                      .map(key -> new Pair(key,cnt.get(key)))
                      .collect(Collectors.toList());

        Collections.sort(f,new Cmp());

        ans = f.stream()
               .map(cur -> cur.getString() + " - " + Integer.toString(cur.getFreq()))
               .collect(Collectors.joining("\n"));

        return ans;
    }

    private class Pair{
        private String s;
        private int freq;

        public Pair(String s,int freq){
            this.s = s;
            this.freq = freq;
        }

        public String getString(){
            return s;
        }

        public int getFreq(){
            return freq;
        }
    }

    class Cmp implements Comparator <Pair> {
        public int compare(Pair x,Pair y){
            int z = -Integer.compare(x.getFreq(),y.getFreq());
            return (z == 0 ? x.getString().compareTo(y.getString()) : z);
        }
    }
}
