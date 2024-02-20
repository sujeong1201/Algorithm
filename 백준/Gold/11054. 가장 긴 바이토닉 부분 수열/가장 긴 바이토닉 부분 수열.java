import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());

        System.out.println(solve(N, arr));
    }

    private static int solve(int N, int[] arr) {
        int[] dpBigger = new int[N];
        int[] dpSmaller = new int[N];
        Arrays.fill(dpBigger, 1);
        Arrays.fill(dpSmaller, 1);

        for(int i = 1; i < N; i++) {
            for(int j = i - 1; j >= 0; j--) {
                if(arr[i] > arr[j]) {
                    int newValue = dpBigger[j] + 1;
                    if(newValue > dpBigger[i]) dpBigger[i] = newValue;
                }
            }

            for(int j = i - 1; j >= 0; j--) {
                if(arr[i] < arr[j]) {
                    int newValue = Math.max(dpBigger[j], dpSmaller[j]) + 1;
                    if(newValue > dpSmaller[i]) dpSmaller[i] = newValue;
                }
            }
        }

        int biggerMax = Arrays.stream(dpBigger).max().getAsInt();
        int smallerMax = Arrays.stream(dpSmaller).max().getAsInt();
        return Math.max(biggerMax, smallerMax);
    }
}
