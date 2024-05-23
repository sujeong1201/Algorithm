import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());
    int[] arr = new int[N];
    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());

    int[] dp = new int[N];
    List<Integer> lis[] = new ArrayList[N];

    for (int i = 0; i < N; i++) {
      dp[i] = 1;
      lis[i] = new ArrayList<>();
      lis[i].add(arr[i]);

      for (int j = 0; j < i; j++) {
        if (arr[j] < arr[i] && dp[j] + 1 > dp[i]) {
          dp[i] = dp[j] + 1;
          lis[i] = new ArrayList<>();
          for (Integer num : lis[j]) {
            lis[i].add(num);
          }
          lis[i].add(arr[i]);
        }
      }
    }

    int maxLength = 0;
    int maxIndex = -1;
    for (int i = 0; i < N; i++) {
      if (dp[i] > maxLength) {
        maxLength = dp[i];
        maxIndex = i;
      }
    }

    StringBuilder sb = new StringBuilder();
    sb.append(maxLength + "\n");
    for (Integer num : lis[maxIndex]) {
      sb.append(num + " ");
    }
    System.out.println(sb);
  }
}
