import java.util.Random;
import java.util.function.Function;
import java.io.FileWriter;

public class ArrayAverage{
    public static double[] prefixAverage1(double[] x){
        int n = x.length;
        double[] a = new double[n];
        for(int j = 0; j < n; j++){
            double total = 0;
            for(int i = 0; i < j; i++){
                total += x[i];
            }
            a[j] = total / (j+1);
        }
        return a;
    }

    public static double[] prefixAverage2(double[] x){
        int n = x.length;
        double[] a = new double[n];
        double total = 0;
        for(int j = 0; j < n; j++){
            total += x[j];
            a[j] = total / (j+1);
        }
        return a;
    }

    private static double[] randomArray(int size){
        Random rand = new Random();
        double arr[] = new double[size];
        for(int i = 0; i < size; i++){
            arr[i] = rand.nextDouble();
        }
        return arr;
    }

    public static long timeAnalysis(Function<double[], double[]> prefixMethod, int n){
        long startTime, endTime, elapsed;

        double arr[] = ArrayAverage.randomArray(n);

        startTime = System.currentTimeMillis();
        prefixMethod.apply(arr);
        endTime = System.currentTimeMillis();
        elapsed = endTime - startTime;

        return elapsed;
    }

    public static void main(String[] args){
        int analysisSize = 10000000;

        try{
            FileWriter csv = new FileWriter("new.csv");
            csv.append("n");
            csv.append(",");
            csv.append("prefixAverage1");
            csv.append(",");
            csv.append("prefixAverage2");
            csv.append("\n");

            for(int i=10; i <= analysisSize; i*=10){
                csv.append(Integer.toString(i));
                csv.append(",");
                csv.append(Long.toString(ArrayAverage.timeAnalysis(ArrayAverage::prefixAverage1, i)));
                csv.append(",");
                csv.append(Long.toString(ArrayAverage.timeAnalysis(ArrayAverage::prefixAverage2, i)));
                csv.append("\n");
            }

            csv.flush();
            csv.close();
        }catch(Exception e){
            System.out.println(e);
            System.exit(0);
        }
        System.out.println("Analysis Complete.");
    }

}
