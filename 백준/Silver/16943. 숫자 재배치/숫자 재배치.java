import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int aAnswer, aArr[], aLength;
    static int B;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        String aStr = st.nextToken();
        B = Integer.parseInt(st.nextToken());

        aLength = aStr.length();
        aArr = new int[aLength];
        for(int i = 0; i < aLength; i++) aArr[i] = aStr.charAt(i) - '0';
        Arrays.sort(aArr);
        if(aArr[0] != 0) aAnswer = arrToInt(aArr);

        if(aLength > (int)(Math.log10(B) + 1) || aAnswer >= B) {
            System.out.println(-1);
            return;
        }
        
        nextPerm();
        if(aAnswer == 0) System.out.println(-1);
        else System.out.println(aAnswer);
    }

    private static void nextPerm() {
        do {
            if(aArr[0] == 0) continue;
            int num = arrToInt(aArr);
            if(num >= B) break;

            aAnswer = num;
        } while(np());
    }

    private static boolean np() {
        int i = aLength - 1;
        while(i > 0 && aArr[i - 1] >= aArr[i]) --i;
        if(i == 0) return false;

        int j = aLength - 1;
        while(aArr[i - 1] >= aArr[j]) --j;

        swap(aArr, i - 1, j);

        int k = aLength - 1;
        while(i < k) swap(aArr, i++, k--);

        return true;
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static int arrToInt(int[] arr) {
        int result = 0;
        for(int num : arr) {
            result *= 10;
            result += num;
        }
        
        return result;
    }
}
