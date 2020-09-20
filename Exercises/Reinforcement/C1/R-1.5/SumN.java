import java.util.Random;

public class SumN{
    public static int sumTillN(int N){
        int sum=0;
        for(int i = 1; i<=N; i++){
            sum+=i;
        }
        return sum;
    }

    public static void main(String[] args){
        Random r = new Random();
        int N = r.nextInt(1000);

        System.out.println("The sum of all numbers until " + N +" is : " + SumN.sumTillN(N));
    }
}
