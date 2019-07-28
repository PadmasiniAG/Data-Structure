import java.util.Iterator;
import java.util.NoSuchElementException;
public class Deque<Item> implements Iterable<Item> {

    private Node First,Last;
    private int sizequeue = 0;
    private class Node {
        Item item;
        Node left;
        Node right;
    }
    // construct an empty deque
    public Deque() {
        First = null;
        Last = null;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return (First == null);
    }

    // return the number of items on the deque
    public int size() {
        return sizequeue;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if(item == null) throw new IllegalArgumentException("No Item provided to add");
        Node oldFirst = First;
        First = new Node();
        First.item = item;
        First.right = oldFirst;
        First.left = null;
        sizequeue++;
        if(Last == null) // first element
        {
            Last = First;
        } else {
            oldFirst.left = First;
        }
    }

    // add the item to the back
    public void addLast(Item item) {
        if(item == null) throw new IllegalArgumentException("No Item provided to add");
        Node oldLast = Last;
        Last = new Node();
        Last.item = item;
        Last.right = null;
        Last.left = oldLast;
        sizequeue++;
        if(oldLast == null) // first element
        {
            First = Last;
        } else {
            oldLast.right = Last;
        }
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if(First == null) throw new NoSuchElementException("No Item provided to remove");
        Item itemval = First.item;
        Node oldFirst = First;
        First = oldFirst.right;
        if(Last == oldFirst) // only element
        {
            Last = null;
        }
        else First.left = null;
        oldFirst.right = null;
        oldFirst.left = null;
        sizequeue--;
        return itemval;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if(Last == null) throw new NoSuchElementException("No Item provided to remove");
        Item itemval = Last.item;
        Node oldLast = Last;
        Last = oldLast.left;
        if(Last != null) Last.right = null;
        else // only element
        {
            First = null;
        }
        oldLast.right = null;
        oldLast.left = null;
        sizequeue--;
        return itemval;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new dequeIterator();
    }

    private class dequeIterator implements Iterator<Item> {
        private Node current = First;
        public boolean hasNext(){
            return (current != null);
        }
        public Item next(){
            if(current == null) throw new NoSuchElementException("No element is left");
            Item item = current.item;
            current = current.right;
            return item;
        }
        public void remove(){
            throw new UnsupportedOperationException("Not supported");
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<String> deque = new Deque<String>();

        // We can add elements to the queue in various ways
        deque.addLast("Element 1"); // add to tail
        System.out.println("added ");
        deque.addFirst("Element 2");
        System.out.println("added ");
        deque.addLast("Element 3");
        System.out.println("added ");
        deque.addFirst("Element 4"); //add to head
        System.out.println("added ");
        deque.addLast("Element 5");
        System.out.println("added ");
        deque.addFirst("Element 6");
        System.out.println("added ");
        deque.addLast("Element 7");
        System.out.println("added ");

        // Iterate through the queue elements.
        System.out.println("Standard Iterator");
        Iterator<String> iterator = deque.iterator();
        while (iterator.hasNext())
            System.out.println("\t" + iterator.next());

        // We can remove the first / last element.
        deque.removeFirst();
        deque.removeLast();
        System.out.println("Size "+ deque.size());
        System.out.println("isEmpty  " + deque.isEmpty());
    }

}
