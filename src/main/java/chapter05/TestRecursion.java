package chapter05;

public class TestRecursion {
    public static void main(String[] args) {
        System.out.println(factorial(5));
        System.out.println(fact(5));
    }

    public static int factorial(int n){
        int res = 1;
        for (int i = 1; i <= n; i++ ){
            res *= i;
        }
        return res;
    }

    //digui
    public static int fact(int n){
        if (n == 0) return 1;
        return fact(n - 1) * n;

    }

}
