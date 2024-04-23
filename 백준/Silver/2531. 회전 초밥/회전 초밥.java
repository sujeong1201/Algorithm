import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken());
    int d = Integer.parseInt(st.nextToken());
    int k = Integer.parseInt(st.nextToken());
    int c = Integer.parseInt(st.nextToken());

    int[] sushi = new int[2 * N];
    int[] counts = new int[d + 1];
    for (int i = 0; i < N; i++) {
      sushi[i] = sushi[N + i] = Integer.parseInt(br.readLine());
    }

    int maxType = 0;
    counts[c]++;
    for (int i = 0; i < k; i++) {
      counts[sushi[i]]++;
    }
    for (int i = 1; i <= d; i++) {
      if (counts[i] > 0) maxType++;
    }

    for (int i = 1; i < N; i++) {
      counts[sushi[i - 1]]--;
      counts[sushi[k + i - 1]]++;

      int curType = 0;
      for (int j = 1; j <= d; j++) {
        if (counts[j] > 0) curType++;
      }
      if (curType > maxType) maxType = curType;
    }

    System.out.println(maxType);
  }
}
