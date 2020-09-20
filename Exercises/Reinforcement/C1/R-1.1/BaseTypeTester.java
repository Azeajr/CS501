import java.util.Scanner;

public class BaseTypeTester{
    /*
     * Package-private access level.Other classes in the same package can have
     * access but not any classes or subclasses from other packages.
     */
    void inputAllBaseTypes(){
        Scanner scan = new Scanner(System.in);
        Object obj;
        System.out.println("Enter a boolean value.");
        obj=scan.nextBoolean();
        System.out.println(obj);

        System.out.println("Enter a character value.");
        obj=scan.next().charAt(0);
        System.out.println(obj +"\n");

        System.out.println("Enter a byte value.");
        obj=scan.nextByte();
        System.out.println(obj);

        System.out.println("Enter a short value.");
        obj=scan.nextShort();
        System.out.println(obj);

        System.out.println("Enter a int value.");
        obj=scan.nextInt();
        System.out.println(obj);

        System.out.println("Enter a long value.");
        obj=scan.nextLong();
        System.out.println(obj);

        System.out.println("Enter a float value.");
        obj=scan.nextFloat();
        System.out.println(obj);

        System.out.println("Enter a double value.");
        System.out.println(scan.nextDouble());
    }

    public static void main(String[] args){
        System.out.println("Here we go.");
        BaseTypeTester b = new BaseTypeTester();
        b.inputAllBaseTypes();
    }
}
