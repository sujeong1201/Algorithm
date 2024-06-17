import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
  static int N;
  static long fact[];

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    N = Integer.parseInt(br.readLine());
    StringTokenizer st = new StringTokenizer(br.readLine());
    int problem = Integer.parseInt(st.nextToken());

    fact = new long[N + 1];
    fact[1] = 1L;
    for (int i = 2; i <= N; i++) fact[i] = fact[i - 1] * i;

    if (problem == 1) {
      long K = Long.parseLong(st.nextToken());
      int[] kthArr = new int[N];
      boolean[] selected = new boolean[N + 1];
      getKthArr(kthArr, selected, 1, K);

      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < N; i++) sb.append(kthArr[i] + " ");
      System.out.println(sb);
    } else {
      int[] arr = new int[N];
      for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());

      System.out.println(getNumOfArr(arr));
    }
  }

  private static long getNumOfArr(int[] target) {
    Queue<Integer> queue = new ArrayDeque<>();
    for (int i = 1; i <= N; i++) queue.offer(i);

    long numOfArr = 0;
    for (int i = 0; i < N - 1; i++) {
      int size = queue.size();
      int cnt = 0;
      boolean flag = false;

      for (int s = 0; s < size; s++) {
        int cur = queue.poll();
        if (flag) {
          queue.offer(cur);
        } else {
          if (cur == target[i]) {
            numOfArr += cnt * fact[N - i - 1];
            flag = true;
          } else {
            cnt++;
            queue.offer(cur);
          }
        }
      }
    }
    numOfArr += 1;

    return numOfArr;
  }

  private static void getKthArr(int[] arr, boolean[] selected, int cnt, long k) {
    if (cnt == N) {
      for (int i = 1; i <= N; i++) {
        if (!selected[i]) {
          arr[cnt - 1] = i;
          return;
        }
      }
    }

    long divide = (k - 1) / fact[N - cnt];

    int idx = 1;
    long num = 0L;
    while (true) {
      if (selected[idx]) {
        idx++;
        continue;
      }

      if (divide == num) {
        selected[idx] = true;
        arr[cnt - 1] = idx;
        break;
      } else {
        num++;
        idx++;
      }
    }

    getKthArr(arr, selected, cnt + 1, (k - 1) % fact[N - cnt] + 1);
  }
}
