public class homeWork1 {
    public static void main1(String[] args) {
        int n = 100;
        for (int j = 100; j<=200 ;j++) {
            int i;
            for (i = 2;i <= Math.sqrt(n);i++) {
                if(n%i == 0) {
                    System.out.println("不是素数："+j);
                    break;
                }
            }
            if(i > Math.sqrt(n)) {
                System.out.println(j + "是素数");
            }
        }
    }

    public static String func1(int n) {
        if(n == 0)
            return "";

        int tmp = n % 10;
        n /= 10;
        return func1(n) + String.valueOf(tmp);
    }

    public static void main(String[] args) {
        System.out.println(func1(1234));
    }
}
