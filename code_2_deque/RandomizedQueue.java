/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */
import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private final Deque<Item> dequeR;
    // construct an empty randomized queue
    public RandomizedQueue() {
        dequeR = new Deque<Item>();
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return(dequeR.isEmpty());
    }

    // return the number of items on the randomized queue
    public int size(){
        return dequeR.size();
    }

    // add the item
    public void enqueue(Item item) {
        int rand = StdRandom.uniform(0, 2); // returns 0 or 1
        if(rand == 0) {
            dequeR.addFirst(item);
        }
        else {
            dequeR.addLast(item);
        }
    }

    // remove and return a random item
    public Item dequeue(){
        int rand = StdRandom.uniform(0, 2); // returns 0 or 1
        if(rand == 0) {
            return dequeR.removeFirst();
        }
        else {
            return dequeR.removeLast();
        }
    }

    // return a random item (but do not remove it)
    public Item sample() {
        Item item;
        int rand;
        if (!dequeR.isEmpty()) {
            rand = StdRandom.uniform(0, 2); // returns 0 or 1
            if(rand == 0) {
                item = dequeR.removeFirst();
            }
            else {
                item = dequeR.removeLast();
            }
            rand = StdRandom.uniform(0, 2); // returns 0 or 1
            if(rand == 0) {
                dequeR.addFirst(item);
            }
            else {
                dequeR.addLast(item);
            }
            return item;
        }
        else throw new NoSuchElementException("No Item left");
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {  return new RanIterator();}

    private class RanIterator implements Iterator<Item> {
        private int index=0;
        Iterator<Item>  iteratorR = dequeR.iterator();
        private final Item[] x;
        private final int sizeran = dequeR.size();
        public RanIterator(){
            x = (Item[]) new Object[sizeran];
            for (int i=0; i < sizeran; i++) {
                x[i] = iteratorR.next();
            }
            StdRandom.shuffle(x);
        }
        public boolean hasNext(){
            return (index<sizeran);
        }
        public Item next(){
            if(this.hasNext())
            return x[index++];
            else throw new NoSuchElementException("No Item left");
        }
        public void remove(){
            throw new UnsupportedOperationException("operation not supported");
        }
    }
    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<String> Rdeque = new RandomizedQueue<String>();

        // We can add elements to the queue in various ways
        Rdeque.enqueue("Element 1"); // add to tail
        System.out.println("added ");
        Rdeque.enqueue("Element 2");
        System.out.println("added ");
        Rdeque.enqueue("Element 3");
        System.out.println("added ");
        Rdeque.enqueue("Element 4"); //add to head
        System.out.println("added ");
        Rdeque.enqueue("Element 5");
        System.out.println("added ");
        Rdeque.enqueue("Element 6");
        System.out.println("added ");
        Rdeque.enqueue("Element 7");
        System.out.println("added ");

        // Iterate through the queue elements.
        System.out.println("Standard Iterator");
        Iterator<String>  iterator = Rdeque.iterator();
        while (iterator.hasNext())
            System.out.println("\t" + iterator.next());

        // We can remove the first / last element.
        Rdeque.dequeue();
        Rdeque.dequeue();
        Rdeque.dequeue();
        System.out.println("Sample "+Rdeque.sample());
        System.out.println("Size "+ Rdeque.size());
        System.out.println("isEmpty  " + Rdeque.isEmpty());
    }

}
