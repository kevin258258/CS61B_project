import java.util.List;
import java.util.ArrayList; // import the ArrayList class

public class LinkedListDeque61B<T> implements Deque61B<T> {
    private int size;
    private Node sentinal;

    public class Node {
        Node next;
        Node prev;
        T item;
        public Node(Node next, Node prev, T item) {
            this.next = next;
            this.prev = prev;
            this.item = item;
        }
    }
    public LinkedListDeque61B() {
        size = 0;
        sentinal = new Node(null, null, null);
        sentinal.next = sentinal;
        sentinal.prev = sentinal;
    }



    @Override
    public void addFirst(T x) {
        size++;
        Node First = new Node(sentinal.next, sentinal, x);
        sentinal.next.prev = First;
        sentinal.next = First;
    }

    @Override
    public void addLast(T x) {
        size++;
        Node Last = new Node(sentinal, sentinal.prev, x);
        sentinal.prev.next = Last;
        sentinal.prev = Last;
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<T>();
        if (size == 0) {
            return null;
        }
        Node p = sentinal.next;
        while (p != sentinal) {
            returnList.add(p.item);
            p = p.next;
        }
        return returnList;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T returnValue = sentinal.next.item;
        sentinal.next.next.prev = sentinal;
        sentinal.next = sentinal.next.next;
        size--;
        return returnValue;

    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        T returnValue = sentinal.prev.item;
        sentinal.prev = sentinal.prev.prev;
        sentinal.prev.next = sentinal;
        size--;
        return returnValue;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        else {
            Node p = sentinal.next;
            if (index == 0) {
                return p.item;
            }
            else {
                p = p.next;
                return get(index - 1);
            }


        }

    }

    @Override
    public T getRecursive(int index) {
        return getRecursive(index,sentinal.next);
    }

    private T getRecursive(int index, Node p){
        if(index < 0 || index >= this.size ){
            return null;
        }
        if(index == 0){
            return p.item;
        }else{
            return getRecursive(index-1,p.next);
        }
    }


}
