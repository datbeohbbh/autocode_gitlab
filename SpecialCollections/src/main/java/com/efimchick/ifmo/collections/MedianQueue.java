package com.efimchick.ifmo.collections;

import java.util.*;
import java.util.stream.Collectors;

class MedianQueue implements Queue <Integer> {

    Queue <Integer> que = new LinkedList<>();

    void toMedianQueue(){
        List <Integer> li = new ArrayList<>();
        li = que.stream().collect(Collectors.toList());
        Collections.sort(li);
        que.clear();
        while(!li.isEmpty()){
            int mid = (li.size() >> 1) - ((li.size() & 1) ^ 1);
            que.add(li.get(mid));
            List <Integer> nwList = new ArrayList<>();
            if(mid >= 1){
                nwList.addAll(li.subList(0,mid));
            }
            if(mid + 2 <= li.size()){
                nwList.addAll(li.subList(mid + 1,li.size()));
            }
            li.clear();;
            li.addAll(nwList);
        }
    }

    @Override
    public boolean add(Integer integer) {
        return que.add(integer);
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends Integer> collection) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        return false;
    }

    @Override
    public void clear() {
        que.clear();
    }

    @Override
    public boolean offer(Integer integer) {
        que.offer(integer);
        toMedianQueue();
        return true;
    }

    @Override
    public Integer remove() {
        return null;
    }

    @Override
    public Integer poll() {
        int x = que.poll();
        toMedianQueue();
        return x;
    }

    @Override
    public Integer element() {
        return null;
    }

    @Override
    public Integer peek() {
        return que.peek();
    }

    @Override
    public int size() {
        return que.size();
    }

    @Override
    public boolean isEmpty() {
        return que.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<Integer> iterator() {
        return que.iterator();
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] ts) {
        return null;
    }
}
