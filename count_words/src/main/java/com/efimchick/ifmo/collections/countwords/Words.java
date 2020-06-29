package com.efimchick.ifmo.collections.countwords;

import java.util.*;

public class Words {

    public String countWords(List<String> lines) {
        TreeMap <String,Integer> cnt = new TreeMap<String, Integer>();
        for(int i = 0;i < lines.size();++i){
            String cur = lines.get(i);
            StringTokenizer st = new StringTokenizer(cur," ");
            ArrayList <String> a = new ArrayList <String> ();
            while(st.hasMoreTokens()){
                String s = st.nextToken();
                ArrayList <String> li = new ArrayList<>();
                li = process(s);
                for(int j = 0;j < li.size();++j){
                    a.add(li.get(j));
                }
            }
            for(int j = 0;j < a.size();++j){
                int freqs = 0;
                if(!cnt.isEmpty()){
                    if(cnt.containsKey(a.get(j))){
                        freqs = cnt.get(a.get(j));
                        cnt.remove(a.get(j));
                    }
                }
                cnt.put(a.get(j),++freqs);
            }
        }
        ArrayList <Pair> ar = new ArrayList<>();
        for(Map.Entry <String,Integer> iter : cnt.entrySet()){
            if(iter.getKey().length() < 4 || iter.getValue() < 10){
                continue;
            }
            ar.add(new Pair(iter.getKey(),iter.getValue()));
        }
        Collections.sort(ar,new Cmp());
        String ans = "";
        for(int i = 0;i < ar.size();++i){
            String t = ar.get(i).getString() + " - ";
            t += Integer.toString(ar.get(i).getFreq());
            ans += t;
            if(i < ar.size() - 1){
                ans += "\n";
            }
        }
        return ans;
    }

    ArrayList <String> process(String s){
        ArrayList <String> ret = new ArrayList<>();
        for(int i = 0;i < s.length();++i){
            if(Character.isLetter(s.charAt(i))){
                int j = i;
                String cur = "";
                while(j < s.length() && Character.isLetter(s.charAt(j))){
                    cur += s.charAt(j);
                    ++j;
                }
                if(!cur.isEmpty()){
                    cur = cur.toLowerCase();
                    ret.add(cur);
                }
                i = j;
            }
        }
        return ret;
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
            if(z == 0){
                z = x.getString().compareTo(y.getString());
            }
            return z;
        }
    }
}
