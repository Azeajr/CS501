import java.util.Scanner;

public class ReverseLineOrder{
    public static void reverseLineOrder(){
        Scanner scan = new Scanner(System.in);
        String temp = "";

        while(scan.hasNext()){
            temp = scan.nextLine() + "\n" + temp;
        }

        System.out.println("\n\n" + temp);

    }

    public static void main(String[] args){
        ReverseLineOrder.reverseLineOrder();
    }


}
