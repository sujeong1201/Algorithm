import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());
    long[] solution = new long[N];
    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) solution[i] = Long.parseLong(st.nextToken());

    int left = 0, right = N - 1;
    long sum = Long.MAX_VALUE;
    int[] answer = new int[2];
    while (left < right) {
      long mix = solution[left] + solution[right];
      if (Math.abs(mix) < Math.abs(sum)) {
        sum = mix;
        answer[0] = left;
        answer[1] = right;
      }

      if (mix == 0) break;
      if (mix < 0) left++;
      else right--;
    }

    System.out.println(solution[answer[0]] + " " + solution[answer[1]]);
  }
}
