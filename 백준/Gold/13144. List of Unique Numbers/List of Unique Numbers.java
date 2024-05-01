import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());
    int[] numbers = new int[N];
    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) numbers[i] = Integer.parseInt(st.nextToken());

    boolean[] notUnique = new boolean[100_001];
    int left = 0;
    int right = 0;
    notUnique[numbers[0]] = true;
    long answer = 1;
    while (right < N - 1) {
      if (notUnique[numbers[right + 1]]) {
        notUnique[numbers[left]] = false;
        if (left == right) {
          right++;
          notUnique[numbers[right]] = true;
        }
        left++;
        answer += (right - left + 1);
      } else {
        right++;
        notUnique[numbers[right]] = true;
        answer++;
      }
    }
    while (left < N - 1) {
      left++;
      answer += (right - left + 1);
    }

    System.out.println(answer);
  }
}
