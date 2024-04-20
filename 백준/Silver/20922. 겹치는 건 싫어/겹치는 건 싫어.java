import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken());
    int K = Integer.parseInt(st.nextToken());
    int[] numbers = new int[N];
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) numbers[i] = Integer.parseInt(st.nextToken());

    int[] count = new int[100_001];
    int left = 0;
    int right = 0;
    int maxLength = 0;
    while (right < N) {
      int num = numbers[right];
      count[num]++;

      while (count[num] > K) {
        count[numbers[left]]--;
        left++;
      }

      if (right - left + 1 > maxLength) maxLength = right - left + 1;
      right++;
    }

    System.out.println(maxLength);
  }
}
