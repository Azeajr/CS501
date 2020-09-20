import maps.*;
//import java.util.Random;

public class test{
    public static void main(String[] args){
        ProbeHashMap<Integer, Character> phm = new ProbeHashMap<>();
        //phm.createTable();

        //Random rand = new Random();

        System.out.println(phm + "\n");

        for(int i = 0; i < 30; i++){
            System.out.println(i);
            phm.put(i,(char)(i+65));

            System.out.println(phm);
            System.out.println();
        }

        System.out.println(phm);
    }
}
