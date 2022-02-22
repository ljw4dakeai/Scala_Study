package chapter01;

public class Student_java {
    private String name;
    private Integer age;

    private static String school = "whpu";

    public Student_java(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public void info(){
        System.out.println("学生：" + this.name + "\t" + this.age + "\t" + Student_java.school);
    }


    //scala删除了static，static不够面对对象
    public static void main(String[] args) {
        Student_java zjh = new Student_java("zjh", 20);
        Student_java ljw = new Student_java("ljw", 20);
        zjh.info();
        ljw.info();
    }
}
