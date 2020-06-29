package com.efimchick.ifmo.collections;

import java.util.*;

class PairStringList implements List <String>{
    private ArrayList <String> li = new ArrayList<>();

    @Override
    public boolean add(String e){
        li.add(e);
        return li.add(e);
    }

    @Override
    public void add(int index,String e){
        int ll = ((index & 1) == 1 ? index + 1 : index);
        li.add(ll,e);
        li.add(ll,e);
    }

    @Override
    public boolean addAll(Collection<? extends String> c){
        c.stream().forEach(cur -> add(cur));
        return true;
    }

    @Override
    public boolean addAll(int index,Collection<? extends String> c){
        int ll = ((index & 1) == 1 ? index + 1 : index);
        for(String s : c){
            add(ll,s);
            ll += 2;
        }
        return true;
    }

    @Override
    public void clear(){
        li.clear();
    }

    @Override
    public boolean contains(Object o){
        return true;
    }

    @Override
    public boolean containsAll(Collection <?> c){
        return true;
    }

    @Override
    public boolean equals(Object e){
        return true;
    }

    @Override
    public String get(int index){
        return li.get(index);
    }

    @Override
    public int hashCode(){
        return 0;
    }

    @Override
    public int indexOf(Object o){
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public boolean isEmpty(){
        return li.isEmpty();
    }

    @Override
    public Iterator<String> iterator(){
        return li.iterator();
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] ts) {
        return ts;
    }

    @Override
    public ListIterator<String> listIterator(){
        return null;
    }

    @Override
    public ListIterator<String> listIterator(int index){
        return null;
    }

    @Override
    public List<String> subList(int i, int i1) {
        return null;
    }

    @Override
    public String remove(int index){
        li.remove(index);
        return li.remove(index ^ 1);
    }

    @Override
    public boolean remove(Object o){
        li.remove(o);
        return li.remove(o);
    }

    @Override
    public boolean removeAll(Collection<?> c){
        return li.remove(c);
    }

    @Override
    public boolean retainAll(Collection<?> c){
        return li.retainAll(c);
    }

    @Override
    public String set(int index,String e){
        li.set(index,e);
        return li.set(index ^ 1,e);
    }

    @Override
    public int size(){
        return li.size();
    }

}
