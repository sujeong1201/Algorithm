import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken());
    int taesooScore = Integer.parseInt(st.nextToken());
    int P = Integer.parseInt(st.nextToken());

    if (N == 0) {
      System.out.println(1);
      System.exit(0);
    }

    st = new StringTokenizer(br.readLine());
    int[] scores = new int[N];
    for (int i = 0; i < N; i++) scores[i] = Integer.parseInt(st.nextToken());

    int cnt = 0;
    int prev = -1;
    int rank = 0;
    for (int i = 0; i < N; i++) {
      if (scores[i] < taesooScore) {
        if (prev != taesooScore) rank = cnt + 1;
        break;
      }

      cnt++;
      if (prev != scores[i]) {
        rank = cnt;
        prev = scores[i];
      }
    }

    if (cnt == N && N < P && prev != taesooScore) rank = cnt + 1;
    System.out.println(cnt >= P ? -1 : rank);
  }
}
