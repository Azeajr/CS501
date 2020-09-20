import linkedpositionallist.*;
import priorityqueue.*;

import java.util.Random;
import java.util.ArrayList;

public class PQSort{
    public static <E> void pqSort(PositionalList<E> S, PriorityQueue<E,?> P){
        int n = S.size();
        for(int j = 0; j < n; j++){
            E element = S.remove(S.first());
            P.insert(element, null);
        }

        for(int j = 0; j < n; j++){
            E element = P.removeMin().getKey();
            S.addLast(element);
        }
    }

    public static <E> void pqInPlaceSort(List<Object> S){
        int n = S.size();


    }



    public static void main(String[] args){
        Random rnd = new Random();

        PositionalList<Integer> pl_upq = new LinkedPositionalList<>();
        PositionalList<Integer> pl_spq = new LinkedPositionalList<>();
        PositionalList<Integer> pl_hpq = new LinkedPositionalList<>();

        PriorityQueue<Integer,?> upq =  new UnsortedPriorityQueue<>();
        PriorityQueue<Integer,?> spq = new SortedPriorityQueue<>();
        PriorityQueue<Integer,?> hpq = new HeapPriorityQueue<>(new ReverseComparator<Integer>());

        PQSort Sorter = new PQSort();

        int temp;

        for(int i = 0; i < 10; i++){
            temp = rnd.nextInt(90)+10;
            pl_upq.addFirst(temp);
            pl_spq.addFirst(temp);
            pl_hpq.addFirst(temp);
        }

        System.out.println("List: " + pl_upq);
        Sorter.pqSort(pl_upq,upq);
        System.out.println("List: " + pl_upq);

        //System.out.println("List: " + pl_spq);
        Sorter.pqSort(pl_spq,spq);
        System.out.println("List: " + pl_spq);

        //System.out.println("List: " + pl_hpq);
        Sorter.pqSort(pl_hpq,hpq);
        System.out.println("List: " + pl_hpq);



    }
}
