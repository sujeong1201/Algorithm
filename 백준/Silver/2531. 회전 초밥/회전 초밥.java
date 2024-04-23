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

    counts[c]++;
    for (int i = 0; i < k; i++) {
      counts[sushi[i]]++;
    }
    int curType = 0;
    for (int i = 1; i <= d; i++) {
      if (counts[i] > 0) curType++;
    }
    int maxType = curType;

    for (int i = 1; i < N; i++) {
      counts[sushi[i - 1]]--;
      if (counts[sushi[i - 1]] == 0) curType--;
      counts[sushi[k + i - 1]]++;
      if (counts[sushi[k + i - 1]] == 1) curType++;

      if (curType > maxType) maxType = curType;
    }

    System.out.println(maxType);
  }
}
