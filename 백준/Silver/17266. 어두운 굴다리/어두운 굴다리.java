import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  static int N, M, pos[], answer;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    N = Integer.parseInt(br.readLine());
    M = Integer.parseInt(br.readLine());
    pos = new int[M];
    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < M; i++) pos[i] = Integer.parseInt(st.nextToken());

    binarySearch();
    System.out.println(answer);
  }

  private static void binarySearch() {
    int min = 1;
    int max = N;

    outer:
    while (min < max) {
      int middle = (min + max) / 2;
      int right = 0;
      for (int p : pos) {
        if (p - middle > right) {
          min = middle + 1;
          continue outer;
        }

        right = p + middle;
      }

      if (right >= N) max = middle;
      else min = middle + 1;
    }

    answer = min;
  }
}
