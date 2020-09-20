import java.util.Scanner;

public class SimpleCalc{
    public static <A extends Number> T add(T x, T y){
        if(x == null || y == null){
            return null;
        }

        if(x instanceof Double){
            return (T) new Double(x.doubleValue() + y.doubleValue());
        } else if(x instanceof Integer){
            return (T) new Integer(x.intValue() + y.intValue());
        }
    }

    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);

        System.out.println("Welcome to Simple Calc");
        System.out.println("Start by entering a number: ");

        Object first, second;

        if(scan.hasNextInt()){
            first = scan.nextInt();
        }else if(scan.hasNextDouble()){
            first = scan.nextDouble();
        }

        System.out.println("Enter a second number: ");

        if(scan.hasNextInt()){
            second = scan.nextInt();
        }else if(scan.hasNextDouble()){
            second = scan.nextDouble();
        }


        System.out.println("Enter operation(+,-,*,/): ");

        if(scan.next().charAt(0) == '+'){
            System.out.println(SimpleCalc.add(first,second));
        }


    }
}
