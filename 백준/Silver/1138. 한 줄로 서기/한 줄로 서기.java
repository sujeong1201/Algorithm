import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());
    int[] tallerThanMe = new int[N];
    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) tallerThanMe[i] = Integer.parseInt(st.nextToken());

    int[] result = solve(tallerThanMe, N);
    for (int i = 0; i < N; i++) System.out.print(result[i] + " ");
  }

  private static int[] solve(int[] tallerThanMe, int n) {
    int[] result = new int[n];
    for (int i = 0; i < n; i++) {
      int index = 0;
      int cnt = tallerThanMe[i];
      while (cnt > 0 || result[index] != 0) {
        if (result[index] == 0) cnt--;
        index++;
      }
      result[index] = i + 1;
    }

    return result;
  }
}
