import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static final int totalNum = 9;
    static int[] heights, numbers;
    static boolean flag;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        heights = new int[totalNum];
        for(int i = 0; i < totalNum; i ++) heights[i] = Integer.parseInt(br.readLine());
        Arrays.sort(heights);

        numbers = new int[7];
        flag = false;
        comb(0, 0, 0);
    }

    private static void comb(int start, int cnt, int sum) {
        if(sum > 100) return;

        if(cnt == 7) {
            if(sum == 100) {
                for(int i = 0; i < 7; i++) System.out.println(numbers[i]);
                flag = true;
            }
            return;
        }

        for(int i = start; i < totalNum; i++) {
            numbers[cnt] = heights[i];
            comb(i + 1, cnt + 1, sum + heights[i]);
            if(flag) return;
        }
    }

}
