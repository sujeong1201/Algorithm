import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static String target;
    static int length;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        target = br.readLine();
        length = target.length();

        StringBuilder sb = new StringBuilder(s);
        recur(s);
        System.out.println(0);
    }

    private static void recur(String str) {
        if(str.length() == length) {
            if(target.equals(str.toString())) {
                System.out.println(1);
                System.exit(0);
            }

            return;
        }

        StringBuilder sb = new StringBuilder(str);
        sb.append('A');
        String nextStr = sb.toString();
        if(target.contains(nextStr) || target.contains(sb.reverse())) recur(nextStr);

        sb = new StringBuilder(str);
        sb.append('B');
        sb.reverse();
        nextStr = sb.toString();
        if(target.contains(nextStr) || target.contains(sb.reverse())) recur(nextStr);
    }
}
