import java.util.Random;
import maps.*;

/*
 * Reimplement the UnsortedTableMap class using the PositionalList class from
 * Section 7.3 rather than an ArrayList.
 */

public class test{
    public static void main(String[] args){
        UnsortedTableMap<Integer, Character> utm = new UnsortedTableMap<>();
        Random rand = new Random();

        System.out.println(utm);

        for(int i = 0; i < 10; i++){
            utm.put(i,(char)(i+65));
            System.out.println(utm);
        }

        System.out.println(utm);
    }
}
