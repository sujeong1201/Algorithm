import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken());
    int X = Integer.parseInt(st.nextToken());
    int[] visitors = new int[N];
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) visitors[i] = Integer.parseInt(st.nextToken());

    int left = 0, right = X - 1;
    int sum = 0;
    for (int i = left; i <= right; i++) sum += visitors[i];

    int max = sum;
    int cnt = 1;
    while (right < N - 1) {
      sum -= visitors[left++];
      sum += visitors[++right];

      if (sum > max) {
        max = sum;
        cnt = 1;
      } else if (sum == max) {
        cnt++;
      }
    }

    if (max == 0) System.out.println("SAD");
    else System.out.println(max + "\n" + cnt);
  }
}
