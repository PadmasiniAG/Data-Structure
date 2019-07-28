/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import java.util.Iterator;


public class Permutation {
    public static void main(String[] args) {
        int k,i=0;
        RandomizedQueue<String> per = new RandomizedQueue<String>();
        k = Integer.parseInt(args[0]);
        while(!StdIn.isEmpty()) {
            if(i == k)
            {
                per.dequeue();
            }
            per.enqueue(StdIn.readString());
            i++;
        }
        for(i=0; i < k; i++){
            System.out.println(per.dequeue());
        }
    }
}
