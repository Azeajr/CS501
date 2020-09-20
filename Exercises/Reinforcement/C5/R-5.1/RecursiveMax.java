/*
 * Describe a recusive algorithm for finding the maximum
 * element in an array, A, of n elements.  What is your
 * running time and space usage?
 10	        0	   0
 100	    0	   0
 1000	    0	   0
 10000	    1	   1
 100000	    1	   2
 1000000	11	   26
 10000000	110	   257
 100000000	1047
 1000000000	10785

 * Although my function runs in half the time as version I found online, they
 * are both still running O(2^n).  The limit I found was recursion depth. While
 * my function has O(log n) recursive depth the function I found online has O(n)
 * recursive depth.
 */
import java.util.Random;

public class RecursiveMax{
    public static int recMax(int[] arr, int low, int high){
        if(low == high-1){
            if(arr[low] > arr[high]){
                return arr[low];
            }else{
                return arr[high];
            }
        }else{
            int mid = (low+high)/2;

            int front = recMax(arr,low,mid);
            int rear = recMax(arr,mid,high);
            if(front > rear){
                return front;
            }else{
                return rear;
            }
        }
    }

    public static int findMaxRec(int A[], int n)
    {
      // if size = 0 means whole array
      // has been traversed
      if(n == 1)
        return A[0];

        return Math.max(A[n-1], findMaxRec(A, n-1));
    }

    private static int[] randomArray(int size){
        Random rand = new Random();
        int arr[] = new int[size];
        for(int i = 0; i < size; i++){
            arr[i] = rand.nextInt(size);
        }
        return arr;
    }

    public static long timeAnalysis(int n){
        long startTime, endTime, elapsed;

        int arr[] = RecursiveMax.randomArray(n);

        startTime = System.currentTimeMillis();
        RecursiveMax.recMax(arr,0,arr.length-1);
        //RecursiveMax.findMaxRec(arr,arr.length);
        endTime = System.currentTimeMillis();
        elapsed = endTime - startTime;

        return elapsed;
    }

    public static void main(String[] args){
        for(int i = 10000000; i <= 1000000000; i*=10){
            System.out.println(i + "\t" + timeAnalysis(i));
        }



    }
}
