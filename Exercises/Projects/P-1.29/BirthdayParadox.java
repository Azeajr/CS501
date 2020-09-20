import java.util.Random;

public class BirthdayParadox{

    static void selectionSort(int arr1[], int arr2[], int arr3[]){
        int n=arr1.length;
        int min,temp;

        for(int index=0; index<n; index++){
            min=index;
            for(int position=index+1; position<n;position++){
                if(arr1[position]<arr1[min]){
                    min=position;
                }
            }

            temp=arr1[min];
            arr1[min]=arr1[index];
            arr1[index]=temp;

            temp=arr2[min];
            arr2[min]=arr2[index];
            arr2[index]=temp;

            temp=arr3[min];
            arr3[min]=arr3[index];
            arr3[index]=temp;


        }
    }

    public static void main(String[] args){
        Random rand = new Random();
        int n=100;

        int month[], day[], year[];
        month= new int[n];
        day= new int[n];
        year= new int[n];

        for(int i=0; i<n; i++){
            //year[i] = rand.nextInt() & Integer.MAX_VALUE
            year[i] = rand.nextInt(101)+1919;

            month[i] = rand.nextInt(12)+1;
            if(month[i]==2 && ((year[i]%4==0  && year[i]%100!=0) || (year[i]%4==0  && year[i]%100==0 && year[i]%400==0) )){
                day[i]=rand.nextInt(29)+1;
            }else if(month[i]==2){
                day[i]=rand.nextInt(28)+1;
            }else if(month[i]==1 || month[i]==3  ||
                     month[i]==5 || month[i]==7  ||
                     month[i]==8 || month[i]==10 || month[i]==12){
                day[i]=rand.nextInt(31)+1;
            }else if(month[i]==2 || month[i]==4 ||
                     month[i]==6 || month[i]==9 || month[i]==11){
                day[i]=rand.nextInt(30)+1;
            }
        }



        BirthdayParadox.selectionSort(day,month,year);
        BirthdayParadox.selectionSort(month,day,year);

        for(int i=0;i<n;i++){
            System.out.println("BDay: "+month[i]+"/"+day[i]+"/"+year[i]);
        }

        System.out.println();

        for(int i=1; i<n;i++){
            if(month[i-1]==month[i] && day[i-1]==day[i]){
                System.out.println("Mathcing birthdays: " + month[i-1]+"/"+day[i-1]+"/"+year[i-1] + " & " + month[i]+"/"+day[i]+"/"+year[i]);
            }
        }

        /*for(int i=0;i<n;i++){
            System.out.println("BDay: "+month[i]+"/"+day[i]+"/"+year[i]);
        }*/



    }
}
