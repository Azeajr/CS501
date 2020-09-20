import java.util.Comparator;
import java.util.Random;

public class UnsortedPriorityQueue<K,V> extends AbstractPriorityQueue<K,V>{
    private PositionalList<Entry<K,V>> list = new LinkedPositionalList<>();
    private Position<Entry<K,V>> minimum = null;

    public UnsortedPriorityQueue(){
        super();
    }

    public UnsortedPriorityQueue(Comparator<K> comp){
        super(comp);
    }

    private Position<Entry<K,V>> findMin(){
        Position<Entry<K,V>> small = list.first();
        for(Position<Entry<K,V>> walk : list.positions()){
            if(compare(walk.getElement(), small.getElement()) < 0){
                small = walk;
            }
        }
        return small;
    }
    /*
     * The min method for the UnsortedPriorityQueue class executes in O(n) time,
     * as analyzed in Table 9.2. Give a simple modification to the class so that
     * min runs in O(1) time. Explain any necessary modifications to other
     * methods of the class
     */

    /*
     * By creating a Position<Entry<K,V>> called minimum I can store the minimum
     * here and update via insert().  The issue will be that remove() will not
     * be supported in this implementation.  If we remove minimum from the list
     * then we would have to use findMin() to get the minimum value.  This
     * function will run in O(n).  So once again remove() cannot be supported.
     */
    public Entry<K,V> insert(K key, V value) throws IllegalArgumentException{
        checkKey(key);
        Entry<K,V> newest =  new PQEntry<>(key, value);
        list.addLast(newest);

        if(minimum == null){
            minimum = list.last();
        }else if(compare(minimum.getElement(), list.last().getElement()) < 0){
            minimum = list.last();
        }

        return newest;
    }

    public Entry<K,V> min(){
        if(list.isEmpty()){
            return null;
        }
        /*return findMin().getElement();*/
        return minimum.getElement();
    }

    public Entry<K,V> removeMin(){
        if(list.isEmpty()){
            return null;
        }
        return list.remove(findMin());
    }

    public int size(){
        return list.size();
    }

    public String toString(){
        StringBuilder sb = new StringBuilder("{");

        //Position<Entry<K,V>> first = list.first();
        for(Position<Entry<K,V>> pos : list.positions()){
            sb.append("(" + pos.getElement().getKey() + "," + pos.getElement().getValue() + ")");
        }
        sb.append("}");
        return sb.toString();
    }

    public static void main(String[] args){
        UnsortedPriorityQueue<Integer,Character> upq = new UnsortedPriorityQueue<>();
        Random rand = new Random();

        System.out.println(upq);

        for(int i = 0; i < 10; i++){
            upq.insert(rand.nextInt(i+1),(char)(i+65));
            System.out.println(upq);

        }
        Entry<Integer,Character> temp = upq.removeMin();
        System.out.println(temp.getKey() + "," + temp.getValue());

        System.out.println(upq);

        temp = upq.removeMin();
        System.out.println(temp.getKey() + "," + temp.getValue());

        System.out.println(upq);

        temp = upq.removeMin();
        System.out.println(temp.getKey() + "," + temp.getValue());

        System.out.println(upq);


    }
}
