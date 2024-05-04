import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  static int N, K, kits[], answer;
  static int numbers[];
  static boolean selected[];

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    K = Integer.parseInt(st.nextToken());
    kits = new int[N];
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) kits[i] = Integer.parseInt(st.nextToken());

    answer = 0;
    numbers = new int[N];
    selected = new boolean[N];
    recur(0);

    System.out.println(answer);
  }

  private static void recur(int cnt) {
    if (cnt == N) {
      int weight = 500;
      for (int i = 0; i < N; i++) {
        weight += (kits[numbers[i]] - K);
        if (weight < 500) return;
      }
      answer++;
      return;
    }

    for (int i = 0; i < N; i++) {
      if (selected[i]) continue;

      numbers[cnt] = i;
      selected[i] = true;
      recur(cnt + 1);
      selected[i] = false;
    }
  }
}
