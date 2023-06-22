import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long K = sc.nextLong();

        System.out.println(makeX(K));
    }

    private static int makeX(long off) {
        if(off == 1) return 0;
        if(off == 2) return 1;

        long log = log2(off);
        long next = 0;
        if((long)Math.pow(2, log - 1) < off && off <= (long)Math.pow(2, log - 2) * 3) {
            next = off - (long)Math.pow(2, log - 2);
        } else {
            next = off - (long)Math.pow(2, log - 2) * 3;
        }
        
        return makeX(next);
    }

    private static long log2(long num) {
        return (long) Math.ceil(Math.log10(num) / Math.log10(2));
    }
}
