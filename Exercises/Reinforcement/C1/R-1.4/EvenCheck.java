import java.util.Random;

public class EvenCheck{
    boolean isEven(int i){
        if(i%2==0){
            return true;
        }else{
            return false;
        }
    }

    public static void main(String[] args){
        EvenCheck eCheck = new EvenCheck();
        Random r = new Random();

        int n =r.nextInt(100);
        if(eCheck.isEven(n)){
            System.out.print(n + " is a even number.");
        }else{
            System.out.print(n + " is a odd number.");
        }

    }
}
