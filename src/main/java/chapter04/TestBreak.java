package chapter04;

public class TestBreak {
    public static void main(String[] args) {
        try{
            for (int i = 0; i <= 5; i++ ){
                if (i == 3){
                    throw new RuntimeException();
                }
                System.out.println("i" + i);
            }
        }catch (Exception e){

        }
        System.out.println("循环外部代码！");
    }
}
