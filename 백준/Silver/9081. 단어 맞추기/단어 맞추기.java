import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < T; i++) {
            String word = br.readLine();
            String nextWord = getNextWord(word);
            sb.append(nextWord+"\n");
        }

        System.out.println(sb);
    }

    private static String getNextWord(String word) {
        char[] wordArr = word.toCharArray();

        int i = wordArr.length - 1;
        while(i > 0 && wordArr[i - 1] >= wordArr[i]) i--;
        if(i == 0) return word;

        int j = wordArr.length - 1;
        while(wordArr[i - 1] >= wordArr[j]) j--;
        swap(wordArr, i - 1, j);

        int k = wordArr.length - 1;
        while(i < k) swap(wordArr, i++, k--);

        return String.copyValueOf(wordArr);
    }

    private static void swap(char[] arr, int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
