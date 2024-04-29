import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken());
    int S = Integer.parseInt(st.nextToken());
    int[] numbers = new int[N];
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) numbers[i] = Integer.parseInt(st.nextToken());

    int minLength = N + 1;
    int left = 0;
    int right = 0;
    int sum = numbers[0];
    while (true) {
      if (sum >= S && (right - left + 1) < minLength) minLength = right - left + 1;

      if (sum > S) {
        sum -= numbers[left];
        left++;
      } else {
        right++;
        if (right >= N) break;
        sum += numbers[right];
      }
    }

    System.out.println(minLength == N + 1 ? 0 : minLength);
  }
}
