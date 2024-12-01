import com.sun.jdi.DoubleValue;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class MyListTest {

    @Test
    public void contains(){
        MyList<Integer> lst = new MyList<>();
        for (int i = 0; i < 10; i++) {
            lst.add(i);
        }
        Integer val = 5;
        assertTrue(lst.contains(val));

        Collection<Integer> vals = Arrays.asList(2, 4, 1, 8);
        assertTrue(lst.containsAll(vals));
    }

    @Test
    public void ToArray(){
        MyList<Integer> lst = new MyList<>();
        ArrayList<Integer> arr = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            lst.add(i);
            arr.add(i);
        }

        assertEquals(arr.size(), lst.size());

        assertArrayEquals(arr.toArray(), lst.toArray());
        assertArrayEquals(arr.toArray(new Integer[0]), lst.toArray(new Integer[0]));
    }

    @Test
    public void add(){
        MyList<String> lst = new MyList<>();
        ArrayList<String> arr = new ArrayList<>();
        Collection<String> col1 = Arrays.asList("abc", "de", "f");
        Collection<String> col2 = Arrays.asList("ghi", "jk");
        arr.addAll(col1);
        lst.addAll(col1);
        assertArrayEquals(arr.toArray(), lst.toArray());
        lst.addAll(2, col2);
        arr.addAll(2, col2);
        assertArrayEquals(arr.toArray(), lst.toArray());
    }

    @Test
    public void remove(){
        MyList<Integer> lst = new MyList<>();
        ArrayList<Integer> arr = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            lst.add(i);
            arr.add(i);
        }
        Integer s = 5;
        Collection<Integer> vals = Arrays.asList(2, 4, 1, 9);

        lst.remove(s);
        arr.remove(s);
        for (int i = 0; i < lst.size(); i++) {
            assertEquals(lst.get(i), arr.get(i));
        }

        lst.removeAll(vals);
        arr.removeAll(vals);
        for (int i = 0; i < lst.size(); i++) {
            assertEquals(lst.get(i), arr.get(i));
        }

        lst.remove(3);
        arr.remove(3);
        for (int i = 0; i < lst.size(); i++) {
            assertEquals(lst.get(i), arr.get(i));
        }
    }


    @Test
    public void retain(){
        MyList<String> lst = new MyList<>();
        lst.add("1");
        lst.add("3");
        lst.add("5");
        lst.add("7");
        ArrayList<String> arr = new ArrayList<>();
        arr.add("1");
        arr.add("3");
        arr.add("5");
        arr.add("7");
        Collection<String> col = Arrays.asList("1", "3", "5");

        assertTrue(lst.retainAll(col));
        lst.set(2, "aa");
        arr.set(2, "aa");
        assertEquals(arr.indexOf("aa"), lst.indexOf("aa"));
        assertEquals(arr.get(2), lst.get(2));
    }

    @Test
    public void index(){
        MyList<Integer> lst = new MyList<>();
        ArrayList<Integer> arr = new ArrayList<>();
        Collection<Integer> col = Arrays.asList(1, 2, 3, 3, 2, 1, 2);
        lst.addAll(col);
        arr.addAll(col);
        assertEquals(lst.size()-1, lst.lastIndexOf(2));
        assertEquals(lst.lastIndexOf(2), arr.lastIndexOf(2));
    }

    @Test
    public void iterator(){
        MyList<Integer> lst = new MyList<>();
        ArrayList<Integer> arr = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            lst.add(i);
            arr.add(i);
        }

        MyList.myIterator iterator = (MyList.myIterator) lst.listIterator(0);
        ListIterator newIter = arr.listIterator(0);

        assertEquals(iterator.next(), newIter.next());
        assertEquals(iterator.next(), newIter.next());

        assertEquals(iterator.hasNext(), newIter.hasNext());
        assertEquals(iterator.hasPrevious(), newIter.hasPrevious());

        assertEquals(iterator.previous(), newIter.previous());
        assertEquals(iterator.previous(), newIter.previous());
    }

    @Test
    public void iteratorIndex(){
        MyList<Integer> lst = new MyList<>();
        lst.add(5);
        lst.add(6);
        lst.add(7);
        lst.add(8);

        MyList.myIterator intIter = (MyList.myIterator) lst.listIterator(1);
        assertEquals(5, intIter.previous());
        assertEquals(1, intIter.nextIndex());
        intIter.next();
        assertEquals(0, intIter.previousIndex());
        intIter.next();
        assertEquals(2, intIter.nextIndex());
    }

}