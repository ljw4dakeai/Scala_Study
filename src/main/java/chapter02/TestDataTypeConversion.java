package chapter02;
//自动类型转换
// byte -> short -> int -> Long -> float -> double
//char -> int
public class TestDataTypeConversion {
    public static void main(String[] args) {
        byte b = 10;
        test(b);
    }

    public static void test(byte b){
        System.out.println("bbbb");
    }

    public static void test(int i) {
        System.out.println("iiii");
    }
    public static void test(char c) {
        System.out.println("cccc");
    }

    public static void test(short s) {
        System.out.println("ssss ");
    }
}
