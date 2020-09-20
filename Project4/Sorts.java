/*
 * CS501 Project #4
 * Antonio Zea Jr.
 * 04302020
 *
 * Implement insertion, selection, merge and quick sorts. Perform a series of
 * benchmarking tests to see which one is faster. Your tests should include
 * sequences that are "random" as well as "almost" sorted.
 *
 */
import java.util.Random;
import java.util.Arrays;

public class Sorts{
    /*
     * This method uses the insertion sort algorithm to sort an array of
     * integers.
     */
    public void insertionSort(int[] list){
        int n = list.length;
        int key, position;

        for(int index = 1; index < n; index++){
            key = list[index];
            position = index;

            while(position > 0 && key < list[position - 1]){
                list[position] = list[position - 1];
                position--;
            }
            list[position] = key;
        }
    }
    /*
     * This method uses the selection sort algorithm to sort an array of
     * integers.
     */
    public void selectionSort(int[] list){
        int n = list.length;
        int min, temp;

        for(int index = 0; index < n; index++){
            min = index;
            for(int position = index + 1; position < n; position++){
                if(list[position] < list[min]){
                    min = position;
                }
            }
            temp = list[min];
            list[min] = list[index];
            list[index] = temp;
        }
    }
    /*
     * This method uses the merge sort algorithm to sort an array of integers
     * recursively.
     */
    public void mergeSort(int[] list){
        int n = list.length;
        if(n < 2){
            return;
        }

        int mid = n / 2;

        int[] s1 = Arrays.copyOfRange(list, 0, mid);
        int[] s2 = Arrays.copyOfRange(list, mid, n);

        mergeSort(s1);
        mergeSort(s2);

        int n1 = s1.length;
        int n2 = s2.length;

        int i=0, j=0, k=0;

        while(i < n1 && j < n2){
            if(s1[i] <= s2[j]){
                list[k] = s1[i];
                i++;
            }else{
                list[k] = s2[j];
                j++;
            }
            k++;
        }

        while(i < n1){
            list[k] = s1[i];
            i++;
            k++;
        }

        while(j < n2){
            list[k] = s2[j];
            j++;
            k++;
        }
    }
    /*
     * This method uses the quick sort algorithm to sort an array of integers
     * recursively.
     */
    public void quickSort(int list[], int low, int high){
        if(low < high){

            int pivot = list[high];
            int i = low - 1;
            for(int j = low; j < high; j++){
                if(list[j] < pivot){
                    i++;
                    int temp = list[i];
                    list[i] = list[j];
                    list[j] = temp;
                }
            }
            int temp = list[i+1];
            list[i+1] = list[high];
            list[high] = temp;

            int pi = i + 1;

            quickSort(list, low, pi - 1);
            quickSort(list, pi + 1, high);
        }
    }

    public static void main(String[] args){
        Sorts sorter = new Sorts();
        /*
         * Used to created the random values in our arrays.
         */
        Random rand = new Random();
        /*
         * These arrays will contain copies of the same array so that each
         * algorithm works on a duplicate of the conrtructed array arr[].
         */
        int[] arr, insert,select, merge, quick;
        /*
         * Timing variables used to store how much time each method takes to execute.
         */
        long startTime=0, endTime=0, elapsedTime=0;
        /*
         * This variable dictates the size of the largest trial.
         */
        int MaxN = 100000;
        /*
         * This for loop creates a trial of a specific size.  The size of the
         * trial is controlled by i.
         */
        for(int i = 10; i <= MaxN; i *= 10){
            arr = new int[i];
            /*
             * Populates arr[] with random integers between 10 and 99 inclusive.
             */
            for(int j = 0; j < arr.length; j++){
                arr[j] = rand.nextInt(90)+10;
            }
            /*
             * Makes exact exact duplicates of arr[] and stores them in
             * respective arrays.
             */
            insert = arr.clone();
            select = arr.clone();
            merge = arr.clone();
            quick =  arr.clone();

            /*
             * Tests Insertion sort on an array with all random values. Prints
             * the size of array test and how long it took to execute the
             * method.
             */
            startTime = System.nanoTime();
            sorter.insertionSort(insert);
            endTime = System.nanoTime();
            elapsedTime = endTime - startTime;
            System.out.println("Insertion sort of " + i + " Random Elements Elapsed Time: " + elapsedTime);
            /*
             * Tests Selection sort on an array with all random values. Prints
             * the size of array test and how long it took to execute the
             * method.
             */
            startTime = System.nanoTime();
            sorter.selectionSort(select);
            endTime = System.nanoTime();
            elapsedTime = endTime - startTime;
            System.out.println("Selection sort of " + i + " Random Elements Elapsed Time: " + elapsedTime);
            /*
             * Tests Merge sort on an array with all random values. Prints the
             * size of array test and how long it took to execute the method.
             */
            startTime = System.nanoTime();
            sorter.mergeSort(merge);
            endTime = System.nanoTime();
            elapsedTime = endTime - startTime;
            System.out.println("Merge sort of " + i + " Random Elements Elapsed Time: " + elapsedTime);
            /*
             * Tests Quick sort on an array with all random values. Prints the
             * size of array test and how long it took to execute the method.
             */
            startTime = System.nanoTime();
            sorter.quickSort(quick,0,quick.length-1);
            endTime = System.nanoTime();
            elapsedTime = endTime - startTime;
            System.out.println("Quick sort of " + i + " Random Elements Elapsed Time: " + elapsedTime + "\n\n");
        }

        /*
         * This for loop creates a trial of a specific size.  The size of the
         * trial is controlled by i.  This time the arrays will contain mostly
         * sorted arrays.  Approximately 90% sorted.
         */
        for(int i = 10; i <= MaxN; i *= 10){
            arr = new int[i];
            /*
             * Populates arr[] with sorted integers 90% of the time.  Otherwise,
             * it uses a random integer between 10 and 99 inclusve.
             */
            for(int j = 0; j < arr.length; j++){
                if(rand.nextInt(10)%10 == 1){
                    arr[j] = rand.nextInt(90)+10;
                }else{
                    arr[j] = j;
                }
            }
            /*
             * Makes exact exact duplicates of arr[] and stores them in
             * respective arrays.
             */
            insert = arr.clone();
            select = arr.clone();
            merge = arr.clone();
            quick =  arr.clone();
            /*
             * Tests Insertion sort on an array with some (10%) random values.
             * Prints the size of array test and how long it took to execute the
             * method.
             */
            startTime = System.nanoTime();
            sorter.insertionSort(insert);
            endTime = System.nanoTime();
            elapsedTime = endTime - startTime;
            System.out.println("Insertion sort of " + i + " Almost Sorted Elements Elapsed Time: " + elapsedTime);
            /*
             * Tests Selection sort on an array with some (10%) random values.
             * Prints the size of array test and how long it took to execute the
             * method.
             */
            startTime = System.nanoTime();
            sorter.selectionSort(select);
            endTime = System.nanoTime();
            elapsedTime = endTime - startTime;
            System.out.println("Selection sort of " + i + " Almost Sorted Elements Elapsed Time: " + elapsedTime);
            /*
             * Tests Merge sort on an array with with some (10%) random values. Prints the
             * size of array test and how long it took to execute the method.
             */
            startTime = System.nanoTime();
            sorter.mergeSort(merge);
            endTime = System.nanoTime();
            elapsedTime = endTime - startTime;
            System.out.println("Merge sort of " + i + " Almost Sorted Elements Elapsed Time: " + elapsedTime);
            /*
             * Tests Quick sort on an array with some (10%) random values. Prints the
             * size of array test and how long it took to execute the method.
             */
            startTime = System.nanoTime();
            sorter.quickSort(quick,0,quick.length-1);
            endTime = System.nanoTime();
            elapsedTime = endTime - startTime;
            System.out.println("Quick sort of " + i + " Almost Sorted Elements Elapsed Time: " + elapsedTime + "\n\n");
        }
    }
}
