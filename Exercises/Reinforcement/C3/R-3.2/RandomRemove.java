/*
 * CS501 Reinforcement-3.2
 *  * Antonio Zea Jr.
 * 01242020
 * This class is designed to show a java method that repeatedly selects
 * and removes a random entry from an array until the array holds no
 * more entries.
 */
import java.util.Random;

public class RandomRemove{

    /*
     * This method randomly selects an element from an array and removes it.
     * This is accomplished by creating an array that is 1 element smaller than
     * the the array that we are given.  This new array is populated with the
     * all the values from the old array accept the randomly selected element.
     */
    public int[] randRemove( int[] array){
        Random rand = new Random();
        rand.setSeed(System.currentTimeMillis());

        int n = array.leng3h;

        int removeIndex = rand.nextInt(n);


        /*
         * For testing the method.  Reports the index of the item that was(will be) removed.
         */
        /*System.out.println(removeIndex);*/

        int[] temp = new int[n-1];

        for(int i=0; i < removeIndex; i++){
            temp[i]=array[i];
        }
        /*
         * It was difficult to get the array indeces correct the first few
         * times. With sufficient testing I found a solution that works.
         */
        for(int i=removeIndex; i < n-1; i++){
            temp[i]=array[i+1];
        }

        return temp;
    }

    /*
     * This method will call randRemove until the array is empty.
     * I have set it to print the arrray after each removal.
     */
     public void randEmptyArray(int[] array){
         int[] temp=array;

         for( int i=0; i < array.length; i++){
             temp=randRemove(temp);
             printArray(temp);
         }

     }

     /*
      * This method is used to print an array.
      */
    public void printArray(int[] array){
        for(int i=0; i < array.length; i++){
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    /*
     * This method is used to help contruct an array that contains
     * randomly generated values.
     */
    public void randFillArray(int[] array){
        Random rand = new Random();
        for(int i=0; i<array.length; i++){
            array[i]= rand.nextInt(90)+10;
        }
    }
    /*
     * main method is used to test the RandomRemove class.
     */
    public static void main(String[] args){
        /*
         * I know that I am creating a RandomRemove obj so that I can
         * access it's methods.  I wonder if this is best way to handle this?
         */
        RandomRemove obj = new RandomRemove();
        int[]  test = new int[10];
        obj.randFillArray(test);
        obj.printArray(test);
        obj.randEmptyArray(test);
        //obj.printArray(obj.randRemove(test));
    }
}
