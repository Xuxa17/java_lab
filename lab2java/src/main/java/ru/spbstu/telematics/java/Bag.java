package ru.spbstu.telematics.java;

import java.util.*;

public class Bag<T> implements Iterable<T>{
    @Override
    public Iterator<T> iterator() {
        return new BagIterator();
    }

    static class Node<T>{
        T obj;
        Node<T> next;

        Node(T element){
            obj = element;
            next = null;
        }
    }

    private Node<T> first;
    private int n;

    public Bag(){
        n = 0;
        first = null;
    }

    public int getCount(T new_obj){
        int cnt = 0;
        Node<T> current = first;
        for (int i = 0; i < n; i++){
            if (current.obj == new_obj){
                cnt++;
            }
            current = current.next;
        }
        return cnt;
    }

    public boolean add(T new_obj){
        boolean res = false;
        if (getCount(new_obj) == 0){
            res = true;
        }

//        Node<T> current = first;
//        first = new Node<>(new_obj);
//        first.next = current;
        Node<T> new_el = new Node<>(new_obj);
        if (first == null){
            first = new_el;
        }
        else {
            Node<T> current = first;
            while (current.next != null){
                current = current.next;
            }
            current.next = new_el;
            //new_el.next = null;
        }
        n++;
        return res;
    }

    public int size(){
        return n;
    }

    public boolean add(T new_obj, int cnt){
        boolean res = false;
        if (getCount(new_obj) == 0){
            res = true;
        }

        for (int i = 0; i < cnt; i++) {
            Node<T> current = first;
            while (current.next != null){
                current = current.next;
            }
//            first = new Node<>(new_obj);
//            first.next = current;
            Node<T> new_el = new Node<>(new_obj);
            if (first == null){
                first = new_el;
            }
            else {
                current.next = new_el;
                //new_el.next = null;
            }
            n++;
        }
        return res;
    }


    public boolean remove(T obj){
        boolean res = false;

        if (first == null){
            return res;
        }

        if (first.obj == obj && n > 1){
            res = true;
            n--;
            first = first.next;
        }

        Node<T> current = first;
        while (current.next != null){
            if (current.next.obj == obj){
                res = true;
                current.next = current.next.next;
                n--;
            }
            else current = current.next;
        }

        if (n == 1 && first.obj == obj){
            n = 0;
            first = null;
        }

        return res;
    }

    public void print(){
        Node<T> current = first;
        for (int i = 0; i < n; i++){
            System.out.print(current.obj);
            current = current.next;
        }
    }


    public boolean remove(T obj, int cnt){
        boolean res = false;

        if (first == null){
            return res;
        }

        if (first.obj == obj){
            res = true;
            n--;
            cnt--;
            first = first.next;
        }

        Node<T> current = first;
        while (current.next != null && cnt > 0){
            if (current.next.obj == obj){
                res = true;
                current.next = current.next.next;
                n--;
                cnt--;
            }
            else current = current.next;
        }

        if (n == 1 && first.obj == obj && cnt > 0){
            n = 0;
            first = null;
        }

        return res;
    }


    public HashSet<T> uniqueSet(){
        HashSet<T> res = new HashSet<T>();
        Node<T> current = first;
        for (int i = 0; i < n; i++){
            res.add(current.obj);
            current = current.next;
        }
        return res;
    }


    public void getUniqueSet(){
        HashSet<T> res = this.uniqueSet();
        for (T r : res){
            System.out.print(r);
        }
    }


    public boolean containsAll(Collection<T> c){
        if (c == null) {
            throw new NullPointerException("Collection must not be null");
        }

        if (c.size() > n) return false;

        for (var elem : c){
            int cnt = 0;
            for (var elem1 : c){
                if (elem == elem1){
                    cnt++;
                }
            }
            if (this.getCount(elem) < cnt)
                return false;
        }
        return true;
    }


    public boolean removeAll(Collection<T> c){
        if (c == null) {
            throw new NullPointerException("Collection must not be null");
        }

        if (c.size() > n) return false;

        if (!this.containsAll(c)) return false;
        for (var elem : c){
            this.remove(elem, 1);
        }
        return true;
    }


    public boolean retainAll(Collection<T> c){
        if (c == null) {
            throw new NullPointerException("Collection must not be null");
        }

        boolean flag = false;
        Node<T> current = first;
        for (int i = 0; i < n; i++){
            if (c.contains(current.obj)) current = current.next;
            else{
                this.remove(current.obj);
                flag = true;
            }
        }

        for (T elem : c){
            int cnt = 0;
            for (T elem1 : c){
                if (elem == elem1) cnt++;
            }
            if (this.getCount(elem) > cnt) this.remove(elem, (this.getCount(elem)-cnt));
            flag = true;
        }
        return flag;
    }


    class BagIterator implements Iterator<T> {
        private Node<T> current = first;

        @Override
        public boolean hasNext() {
            return current.next != null;
        }

        @Override
        public T next() {
            if (!hasNext())
                throw new NoSuchElementException();
            Node<T> item = current;
            current = current.next;
            return item.obj;
        }
    }

    public void addAll(Collection<T> c){
        for (T elem : c){
            this.add(elem);
        }
    }

    public void clear(){
        first = null;
        n = 0;
    }

    public boolean contains(T o){
        return (this.getCount(o) != 0);
    }

    public boolean isEmpty(){
        return n == 0;
    }


    public Object[] toArray() {
        Object[] arr = new Object[n];
        int i = 0;
        Node<T> cur = first;
        while (cur != null){
            arr[i++] = cur.obj;
            cur = cur.next;
        }
        return arr;
    }

    public <T1> T1[] toArray(T1[] a) {
        if (a.length < n){
            a = (T1[]) Arrays.copyOf(a, this.size());
        }
        int i = 0;
        Node<T> cur = first;
        while (cur != null){
            a[i++] = (T1) cur.obj;
            cur = cur.next;
        }
        if (a.length > this.size()){
            a[this.size()] = null;
        }
        return a;
    }
}


