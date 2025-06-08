package code_block;

public class Outer {
    public String name = "liren";
    protected int age = 20;
    private String phone = "156";

    class Inner {
        private String name = "yt";

        public String getName() {
            return name;
        }
    }

    public static void main(String[] args) {
        Inner in = new Outer().new Inner();
        System.out.println(in.getName());
        System.out.println(in.name);
        System.out.println(new Outer().new Inner().name);
    }
}
