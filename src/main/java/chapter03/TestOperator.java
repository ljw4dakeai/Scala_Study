package chapter03;

import com.sun.org.apache.xpath.internal.operations.Bool;

public class TestOperator {
    public static void main(String[] args) {
        String s1 = "hello";
        String s2 = new String("hello");
        Boolean isEqual = s1 == s2;


        System.out.println(isEqual);

        Boolean Equal = s1.equals(s2);
        System.out.println(Equal);


        //赋值运算符
        byte b  = 10;
        b = (byte)(b + 1);
        b += 1;
        System.out.println(b);

        int x = 15;
        int y = x ++ ;
        System.out.println("x=" + x + "y=" + y );


        x = 15;
        y = ++ x;
        System.out.println("x=" + x + "y=" + y );


        x = 23;
        x = x ++; //temp(23) = x (23) ++  --> x ++ x= trmp
        System.out.println("x= "+ x);
    }
}
