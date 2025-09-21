package lambda;

public class t1 {
    public static void main(String[] args) {
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("liren");
            }
        };
        r1.run();

        Runnable r2 = () -> {
            System.out.println("liren2");
        };
        r2.run();
    }
}
