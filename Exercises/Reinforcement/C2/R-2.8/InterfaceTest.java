interface A extends B{
    int test1();
}

interface B extends A{
    String test2();
}

class InterfaceTest implements A{
    int test1(){
        return 14;
    }

    String test2(){
        return "fourteen";
    }

    static void main(String args[]){
        InterfaceTest interf = new InterfaceTest();
        System.out.println(interf.test1());
    }
}
/*
 *    \InterfaceTest.java:1: error: cyclic inheritance involving A
 *    interface A extends B{
 *    ^
 *    1 error
 *    Can two interfaces mutually exnted each other?  Why or why not?
 *    As far as I can tell, the answer is no.  The issue stems from
 *    object A inherting from object B which inherits from object A.
 *    This constitutes a cyclic inheritance which is not allowed in
 *    Java.
 */
