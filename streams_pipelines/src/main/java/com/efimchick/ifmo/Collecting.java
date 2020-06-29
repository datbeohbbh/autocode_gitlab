package com.efimchick.ifmo;

import com.efimchick.ifmo.util.CourseResult;
import com.efimchick.ifmo.util.Person;

import java.text.DecimalFormat;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Collecting{

    private final double exps = 1e-6;
    private final double open[] = {90 + exps,83,75,68,60,0};
    private final double close[] = {100,90,83 - exps,75 - exps,68 - exps,60 - exps};
    private final String[] marks = {"A","B","C","D","E","F"};

    public int sum(IntStream cur){
        return cur.sum();
    }

    public int production(IntStream cur){
        int prod = Arrays.stream(cur.toArray()).reduce(1,(x,y) -> x * y);
        return prod;
    }

    public int oddSum(IntStream cur){
        int sum = cur.filter(val -> ((val % 2) + 2) % 2 == 1).sum();
        return sum;
    }

    public TreeMap<Integer,Integer> sumByRemainder(int d, IntStream cur){
        TreeMap <Integer,Integer> cnt = new TreeMap<>();
        Arrays.stream(cur.toArray()).forEach(val -> {
            cnt.put(val % d,(cnt.containsKey(val % d) ? cnt.get(val % d) + val : val));
        });
        return cnt;
    }

    List <String> getTaskName(List <CourseResult> li){
        ArrayList <String> task_name = new ArrayList<>();
        li.stream().map(iter -> {
            Set <String> ks = iter.getTaskResults().keySet();
            return ks.stream().collect(Collectors.toList());
        }).forEach(task_name::addAll);
        return task_name.stream().distinct().collect(Collectors.toList());
    }

    public Map <Person,Double> totalScores(Stream<CourseResult> cur){
        List <CourseResult> li = cur.collect(Collectors.toList());
        List <String> task = getTaskName(li);
        Map <Person,Double> tot= li.stream().map(iter -> {
            Map <String,Integer> m = iter.getTaskResults();
            double ret = task.stream()
                             .map(c -> (double)(m.containsKey(c) ? m.get(c) : 0.0))
                             .reduce(0.0,(x,y) -> x + y);
            return new Pair(iter.getPerson(),ret / (double) task.size());
        }).collect(Collectors.toMap(Pair <Person,Double>::getFirst,Pair <Person,Double>::getSecond));;
        return tot;
    }

    public double averageTotalScore(Stream <CourseResult> cur){
        Map <Person,Double> tot = totalScores(cur);
        Set <Person> st = tot.keySet();
        double ret = st.stream().map(iter -> tot.get(iter)).reduce(0.0,(x,y) -> x + y);
        return ret / tot.size();
    }

    public Map <String,Double> averageScoresPerTask(Stream <CourseResult> cur){
        TreeMap <String,Double> ret = new TreeMap<>();
        int d = cur.map(iter -> iter.getTaskResults())
           .map(iter -> {
               Set <String> st = iter.keySet();
               st.stream().forEach(c -> {
                   Integer add = iter.get(c);
                   ret.put(c,(ret.containsKey(c) ? ret.get(c) + (double)add : add));
               });
               return 1;
           }).reduce(0,(x,y) -> x + y);
        Set <String> st = ret.keySet();
        return st.stream().map(iter -> {
            Double x = ret.get(iter);
            return new Pair <String,Double> (iter,x / (double)d);
        }).collect(Collectors.toMap(Pair <String,Double>::getFirst,Pair <String,Double>::getSecond));
    }

    private String getMarks(double averScore){
        return IntStream.range(0,6)
                .filter(i -> Double.compare(averScore,open[i]) >= 0
                        && Double.compare(averScore,close[i]) <= 0)
                .mapToObj(i -> marks[i]).collect(Collectors.joining());
    }

    public Map <Person,String> defineMarks(Stream <CourseResult> cur){
        List <CourseResult> li = cur.collect(Collectors.toList());
        List <String> task = getTaskName(li);
        return li.stream().map(iter -> {
            Map <String,Integer> m = iter.getTaskResults();
            double score = task.stream()
                               .map(c -> (double)(m.containsKey(c) ? m.get(c) : 0))
                               .reduce(0.0,(x,y) -> x + y);
            score /= (double)task.size();
            String rank = getMarks(score);
            return new Pair <Person,String> (iter.getPerson(),rank);
        }).collect(Collectors.toMap(Pair<Person,String>::getFirst,Pair <Person,String>::getSecond));
    }

    public String easiestTask(Stream <CourseResult> cur){
        Map <String,Double> task = averageScoresPerTask(cur);
        Set <String> st = task.keySet();
        List <Pair <Double,String> > li = st.stream().map(iter -> new Pair<Double,String>(task.get(iter),iter))
                   .sorted(new Cmp()).collect(Collectors.toList());
        return li.get(li.size() - 1).getSecond();
    }

    public Collector <CourseResult,?,String> printableStringCollector(){
        return new CollectorImpl();
    }

//-----------------------------
    public static String formatTable(ArrayList < ArrayList <String> > rows) {
        int[] maxLengths = new int[rows.get(0).size()];
        for (List<String> row : rows) {
            for (int i = 0; i < row.size(); i++) {
                maxLengths[i] = Math.max(maxLengths[i], row.get(i).length());
            }
        }
        StringBuilder formatBuilder = new StringBuilder();
        for (int i = 0;i <  maxLengths.length;++i) {
            int maxLength = maxLengths[i];
            if(i == 0){
                formatBuilder.append("%-").append(maxLength + 1).append("s");
                formatBuilder.append("|");
            } else {
                formatBuilder.append("%").append(maxLength + 1).append("s");
                formatBuilder.append(" |");
            }
        }
        String format = formatBuilder.toString();

        return rows.stream().map(iter -> String.format(format, iter.toArray(new String[0])))
                            .collect(Collectors.joining("\n"));
    }
//-----------------------------
    private class CollectorImpl implements Collector <CourseResult, ArrayList,String>{

        @Override
        public Supplier<ArrayList> supplier() {
            //System.out.println("here 1!");
            return ArrayList ::new;
        }

        @Override
        public BiConsumer<ArrayList, CourseResult> accumulator() {
            //System.out.println("here 2!");
            return ArrayList::add;
        }

        @Override
        public BinaryOperator<ArrayList> combiner() {
            return null;
        }

        @Override
        public Function<ArrayList, String> finisher() {
            return (arr) -> {
                Collections.sort(arr,(a,b) -> {
                    CourseResult x = (CourseResult)a;
                    CourseResult y = (CourseResult)b;
                    String lastname_x = x.getPerson().getLastName();
                    String lastname_y = y.getPerson().getLastName();
                    return lastname_x.compareTo(lastname_y);
                });
                List <CourseResult> li = new ArrayList<>();
                for(int i = 0;i < arr.size();++i) {
                    CourseResult obj = (CourseResult) arr.get(i);
                    li.add(obj);
                }
                List <String> task_name = getTaskName(li).stream()
                                                         .sorted()
                                                         .collect(Collectors.toList());
                ArrayList < ArrayList <String> > table = new ArrayList<>();
                DecimalFormat df = new DecimalFormat("#0.00");
                table.add(new ArrayList<>());
                table.get(0).add("Student");
                table.get(0).addAll(task_name);
                table.get(0).add("Total");
                table.get(0).add("Mark");
                int numberOfTask = task_name.size();
                for(int i = 0;i < arr.size();++i){
                    table.add(new ArrayList<>());
                    int j = table.size() - 1;
                    CourseResult cur = (CourseResult)arr.get(i);
                    String person = cur.getPerson().getLastName()
                                    + " "
                                    + cur.getPerson().getFirstName();
                    table.get(j).add(person);
                    Map <String,Integer> m = cur.getTaskResults();
                    table.get(j).addAll(task_name.stream()
                                      .map(iter -> (m.containsKey(iter) ? m.get(iter) : 0))
                                      .map(iter -> Integer.toString(iter))
                                      .collect(Collectors.toList()));
                    double tot = task_name.stream()
                                          .map(iter -> (double)(m.containsKey(iter) ? m.get(iter) : 0))
                                          .reduce(0.0,(x,y) -> x + y);
                    tot /= numberOfTask;
                    String mark = getMarks(tot);
                    table.get(j).add(df.format(tot));
                    table.get(j).add(mark);
                }
                table.add(new ArrayList<>());
                table.get(table.size() - 1).add("Average");
                double sumAverage = 0.0;
                for(String t : task_name){
                    double curAverage = 0.0;
                    for(int i = 0;i < arr.size();++i){
                        CourseResult cur = (CourseResult)arr.get(i);
                        curAverage += (cur.getTaskResults().containsKey(t) ?
                                       cur.getTaskResults().get(t) : 0);
                    }
                    curAverage /= (double) arr.size();
                    sumAverage += curAverage;
                    table.get(table.size() - 1).add(df.format(curAverage));
                }
                sumAverage /= (double)task_name.size();
                table.get(table.size() - 1).add(df.format(sumAverage));
                table.get(table.size() - 1).add(getMarks(sumAverage));
                return formatTable(table);
            };
        }

        @Override
        public Set<Characteristics> characteristics() {
            return EnumSet.of(Characteristics.CONCURRENT);
        }

    }

    private class Pair <T,U> {
        private T first;
        private U second;
        public Pair(T first,U second){
            this.first = first;
            this.second = second;
        }
        public T getFirst(){
            return first;
        }
        public U getSecond(){
            return second;
        }
    }

    private class Cmp implements Comparator < Pair <Double,String> > {
        @Override
        public int compare(Pair<Double, String> t1, Pair<Double, String> t2) {
            return Double.compare(t1.getFirst(),t2.getFirst());
        }
    }
}
