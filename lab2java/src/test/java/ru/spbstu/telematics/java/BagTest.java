package ru.spbstu.telematics.java;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class BagTest {

    @Test
    public void testGetCount() {
        Bag<String> bag = new Bag<>();
        bag.add("w");
        bag.add("a", 2);

        assertEquals(2, bag.getCount("a"));
    }

    @Test
    public void testAdd() {
        Bag<String> bag = new Bag<>();
        bag.add("w");
        bag.add("a");
        bag.add("a");

        List<String> str = new LinkedList<>();
        str.add("w");
        str.add("a");
        str.add("a");

        assertArrayEquals(bag.toArray(), str.toArray());
    }

    @Test
    public void testSize() {
        Bag<String> bag = new Bag<>();
        bag.add("w");
        bag.add("a", 2);

        List<String> str = new LinkedList<>();
        str.add("w");
        str.add("a");
        str.add("a");

        assertEquals(bag.size(), str.size());
    }

    @Test
    public void testAdd1() {
        Bag<String> bag = new Bag<>();
        bag.add("w");
        bag.add("a", 2);
        bag.add("c");

        List<String> str = new LinkedList<>();
        str.add("w");
        str.add("a");
        str.add("a");
        str.add("c");

        assertArrayEquals(bag.toArray(), str.toArray());
    }

    @Test
    public void testRemove() {
        Bag<String> bag = new Bag<>();
        bag.add("w");
        bag.add("a");
        bag.add("a");
        bag.add("c");

        List<String> str = new LinkedList<>();
        str.add("w");
        str.add("a");
        str.add("a");
        str.add("c");

        bag.remove("a");
        str.remove("a");
        str.remove("a");

        assertArrayEquals(bag.toArray(), str.toArray());
    }

    @Test
    public void testRemove1() {
        Bag<String> bag = new Bag<>();
        bag.add("w");
        bag.add("a");
        bag.add("a");
        bag.add("c");

        List<String> str = new LinkedList<>();
        str.add("w");
        str.add("a");
        str.add("a");
        str.add("c");

        bag.remove("a", 1);
        str.remove("a");

        assertArrayEquals(bag.toArray(), str.toArray());
    }

    @Test
    public void testUniqueSet() {
        HashSet<String> set = new HashSet<String>();
        set.add("w");
        set.add("a");
        set.add("a");
        set.add("c");

        Bag<String> bag = new Bag<>();
        bag.add("w");
        bag.add("a");
        bag.add("a");
        bag.add("c");

        assertEquals(set, bag.uniqueSet());
    }

    @Test
    public void testContainsAll() {
        LinkedList<String> str1 = new LinkedList<>();
        str1.add("a");
        str1.add("a");
        str1.add("c");

        Bag<String> bag = new Bag<>();
        bag.add("w");
        bag.add("a");
        bag.add("a");
        bag.add("c");

        List<String> str = new LinkedList<>();
        str.add("w");
        str.add("a");
        str.add("a");
        str.add("c");

        assertEquals(str.containsAll(str1), bag.containsAll(str1));
    }

    @Test
    public void testRemoveAll() {
        LinkedList<String> str1 = new LinkedList<>();
        str1.add("a");
        str1.add("a");
        str1.add("c");

        Bag<String> bag = new Bag<>();
        bag.add("w");
        bag.add("a");
        bag.add("a");
        bag.add("c");

        List<String> str = new LinkedList<>();
        str.add("w");
        str.add("a");
        str.add("a");
        str.add("c");

        assertEquals(str.removeAll(str1), bag.removeAll(str1));
    }

    @Test
    public void testRetainAll() {
        LinkedList<String> str1 = new LinkedList<>();
        str1.add("a");
        str1.add("a");
        str1.add("c");

        Bag<String> bag = new Bag<>();
        bag.add("w");
        bag.add("a");
        bag.add("a");
        bag.add("c");

        List<String> str = new LinkedList<>();
        str.add("w");
        str.add("a");
        str.add("a");
        str.add("c");

        assertEquals(str.retainAll(str1), bag.retainAll(str1));
    }

    @Test
    public void testIterator(){
        Bag<Integer> bag = new Bag<>();
        List<Integer> str = new LinkedList<>();
        for (int i = 0; i < 5; i++){
            bag.add(i);
            str.add(i);
        }

        Iterator<Integer> iter = bag.iterator();
        Iterator<Integer> iter1 = str.iterator();

        assertEquals(iter.hasNext(), iter1.hasNext());
        assertEquals(iter.next(), iter1.next());
        assertEquals(iter.hasNext(), iter1.hasNext());
        assertEquals(iter.next(), iter1.next());
        assertEquals(iter.hasNext(), iter1.hasNext());
        assertEquals(iter.next(), iter1.next());
        assertEquals(iter.hasNext(), iter1.hasNext());
        assertEquals(iter.next(), iter1.next());
        assertEquals(iter.hasNext(), false);
    }

    @Test
    public void testAddAll(){
        LinkedList<String> str1 = new LinkedList<>();
        str1.add("a");
        str1.add("a");
        str1.add("c");

        Bag<String> bag = new Bag<>();
        bag.add("w");
        bag.add("a");
        bag.add("a");
        bag.add("c");

        List<String> str = new LinkedList<>();
        str.add("w");
        str.add("a");
        str.add("a");
        str.add("c");

        str.addAll(str1);
        bag.addAll(str1);
        assertArrayEquals(str.toArray(), bag.toArray());
    }

    @Test
    public void testClear(){
        List<String> str = new LinkedList<>();
        str.add("w");
        str.add("a");
        str.add("a");
        str.add("c");

        Bag<String> bag = new Bag<>();
        bag.add("w");
        bag.add("a");
        bag.add("a");
        bag.add("c");

        bag.clear();
        str.clear();

        assertEquals(bag.isEmpty(), str.isEmpty());

    }

    @Test
    public void testContains(){
        Bag<Integer> bag = new Bag<>();
        List<Integer> str = new LinkedList<>();
        for (int i = 0; i < 5; i++){
            bag.add(i);
            str.add(i);
        }

        assertEquals(bag.contains(2), str.contains(2));
    }

    @Test
    public void testToArray(){
        Bag<Integer> bag = new Bag<>();
        List<Integer> str = new LinkedList<>();
        for (int i = 0; i < 5; i++){
            bag.add(i);
            str.add(i);
        }

        assertArrayEquals(bag.toArray(), str.toArray());
    }

    @Test
    public void testToArray1(){
        Bag<Integer> bag = new Bag<>();
        List<Integer> str = new LinkedList<>();
        for (int i = 0; i < 5; i++){
            bag.add(i);
            str.add(i);
        }

        assertArrayEquals(bag.toArray(new Integer[0]), str.toArray(new Integer[0]));
    }
}
