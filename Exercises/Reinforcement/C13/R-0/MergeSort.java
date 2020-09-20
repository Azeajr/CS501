import java.util.Arrays;

public class MergeSort{
    public void merge(int S1[], int S2[], int arr[]){
        int n1 = S1.length;
        int n2 = S2.length;
        int i=0, j=0, k=0;

        while(i < n1 && j < n2){
            if(S1[i] <= S2[j]){
                arr[k] = S1[i];
                i++;
            }else{
                arr[k] = S2[j];
                j++;
            }
            k++;
        }

        while(i < n1){
            arr[k] = S1[i];
            i++;
            k++;
        }

        while(j < n2){
            arr[k] = S2[j];
            j++;
            k++;
        }
    }

    public void sort(int arr[]){
        int n = arr.length;
        if(n < 2){
            return;
        }
        int mid = n/2;

        int S1[] = Arrays.copyOfRange(arr, 0, mid);
        int S2[] = Arrays.copyOfRange(arr, mid, n);

        sort(S1);
        sort(S2);

        merge(S1, S2, arr);
    }

    public void printArray(int arr[]){
        for(int i = 0; i < arr.length; i++){
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }


    public static void main(String args[]){
        MergeSort sorter = new MergeSort();

        int arr[] = {85,24,63,45,17,31,96,50};

        sorter.printArray(arr);
        sorter.sort(arr);
        sorter.printArray(arr);


    }
}
