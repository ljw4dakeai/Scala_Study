package chapter06;

public class TestDynamicBuild {
    public static void main(String[] args) {
        worker worker = new worker();
        System.out.println(worker.name);
        worker.hellow();
        worker.hi();

        System.out.println("==============");

        //多态
        Personer personer = new worker();
        System.out.println(personer.name); //属性静态绑定，方法动态绑定

        personer.hellow();
    }
}

class Personer {
    String name = "person";

    public void hellow() {
        System.out.println("hellow person");
    }
}

class worker extends Personer {
    String name = "work";
    public void hellow() {
        System.out.println("hellow work");
    }

    public void hi() {
        System.out.println("hi work");
    }
}