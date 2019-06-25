public class FirstClass {
    public static void main(String[] args) {
        String str = "Java,Java,hello,world";
        String newStr = str.replaceAll("Java", "尚硅谷");
        System.out.println(newStr);
    }

    private int myFun() {
        return 12;
    }
}