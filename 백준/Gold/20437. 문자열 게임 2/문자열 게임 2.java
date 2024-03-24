import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int T = Integer.parseInt(br.readLine());

    StringBuilder sb = new StringBuilder();
    for (int t = 0; t < T; t++) {
      String w = br.readLine();
      int k = Integer.parseInt(br.readLine());

      int[] result = solve(w, k);
      if (result[1] == -1) sb.append("-1\n");
      else sb.append(result[0] + " " + result[1] + "\n");
    }

    System.out.println(sb);
  }

  private static int[] solve(String w, int k) {
    if (k == 1) return new int[] {1, 1};

    int length = w.length();
    int[] sequenceCnt = new int[length];

    for (int i = 0; i < length; i++) {
      char ch = w.charAt(i);
      int cnt = 1;
      int j = i + 1;
      for (; j < length; j++) {
        if (w.charAt(j) == ch) cnt++;
        if (cnt == k) break;
      }

      if (cnt == k) sequenceCnt[i] = j - i + 1;
      else sequenceCnt[i] = -1;
    }

    int min = Integer.MAX_VALUE;
    int max = -1;
    for (int i = 0; i < length; i++) {
      if (sequenceCnt[i] != -1 && sequenceCnt[i] < min) min = sequenceCnt[i];
      if (sequenceCnt[i] > max) max = sequenceCnt[i];
    }

    return new int[] {min, max};
  }
}
