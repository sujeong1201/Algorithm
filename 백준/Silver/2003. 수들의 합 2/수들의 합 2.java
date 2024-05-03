import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());
    int[] numbers = new int[N];
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) numbers[i] = Integer.parseInt(st.nextToken());

    int answer = 0;
    for (int i = 0; i < N; i++) {
      int sum = 0;
      for (int j = i; j < N; j++) {
        sum += numbers[j];
        if (sum == M) {
          answer++;
          break;
        }
        if (sum > M) {
          break;
        }
      }
    }

    System.out.println(answer);
  }
}
