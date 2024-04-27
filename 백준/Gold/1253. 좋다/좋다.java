import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());
    int[] numbers = new int[N];
    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) numbers[i] = Integer.parseInt(st.nextToken());

    Arrays.sort(numbers);

    int good = 0;
    for (int i = 0; i < N; i++) {
      int left = 0;
      int right = N - 1;

      while (left < right) {
        if (left == i) {
          left++;
          continue;
        }
        if (right == i) {
          right--;
          continue;
        }

        int sum = numbers[left] + numbers[right];
        if (sum == numbers[i]) {
          good++;
          break;
        }

        if (sum < numbers[i]) {
          left++;
        } else {
          right--;
        }
      }
    }

    System.out.println(good);
  }
}
