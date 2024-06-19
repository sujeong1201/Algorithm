import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());

    int[] remainCnt = new int[n + 1];
    Arrays.fill(remainCnt, -1);
    if (n >= 2) remainCnt[2] = 1;
    if (n >= 4) remainCnt[4] = 2;
    if (n >= 5) remainCnt[5] = 1;
    for (int i = 6; i <= n; i++) {
      if (remainCnt[i - 2] == -1 && remainCnt[i - 5] == -1) {
        continue;
      } else if (remainCnt[i - 2] == -1) {
        remainCnt[i] = remainCnt[i - 5] + 1;
      } else if (remainCnt[i - 5] == -1) {
        remainCnt[i] = remainCnt[i - 2] + 1;
      } else {
        remainCnt[i] = Math.min(remainCnt[i - 2], remainCnt[i - 5]) + 1;
      }
    }

    System.out.println(remainCnt[n]);
  }
}
