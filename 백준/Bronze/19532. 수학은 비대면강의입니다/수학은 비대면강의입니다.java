import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        int c = sc.nextInt();
        int d = sc.nextInt();
        int e = sc.nextInt();
        int f = sc.nextInt();

        int x = -999;
        int y = -999;
        outer:
        for(; x <= 999; x++) {
            for(y = -999; y <= 999; y++) {
                int sum1 = a * x + b * y;
                int sum2 = d * x + e * y;
                
                if(sum1 == c && sum2 == f) break outer;
            }
        }

        System.out.println(x + " " + y);
    }
}
