import java.io.*;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());
    int[] numbers = new int[N];
    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) numbers[i] = Integer.parseInt(st.nextToken());

    boolean[][] isPalindrome = new boolean[N][N];
    for (int i = N - 1; i >= 0; i--) {
      isPalindrome[i][i] = true;
      for (int j = i + 1; j < N; j++) {
        if (j == i + 1 && numbers[i] == numbers[j]) isPalindrome[i][j] = true;
        if (j > i + 1 && isPalindrome[i + 1][j - 1] && numbers[i] == numbers[j]) {
          isPalindrome[i][j] = true;
        }
      }
    }

    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int M = Integer.parseInt(br.readLine());
    while (M-- > 0) {
      st = new StringTokenizer(br.readLine());
      int s = Integer.parseInt(st.nextToken()) - 1;
      int e = Integer.parseInt(st.nextToken()) - 1;

      if (isPalindrome[s][e]) bw.write("1\n");
      else bw.write("0\n");
    }

    bw.flush();
    bw.close();
    br.close();
  }
}
