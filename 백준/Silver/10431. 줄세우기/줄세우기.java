import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int T = Integer.parseInt(br.readLine());

    StringBuilder sb = new StringBuilder();
    for (int t = 1; t <= T; t++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      st.nextToken(); // 테케 번호
      PriorityQueue<int[]> pq = new PriorityQueue<>((arr1, arr2) -> arr1[1] - arr2[1]);
      for (int i = 0; i < 20; i++) {
        int time = Integer.parseInt(st.nextToken());
        pq.offer(new int[] {i, time});
      }

      boolean[] check = new boolean[20];
      int cnt = 0;
      while (!pq.isEmpty()) {
        int[] cur = pq.poll();
        for (int i = cur[0] + 1; i < 20; i++) {
          if (check[i]) cnt++;
        }
        check[cur[0]] = true;
      }

      sb.append(t + " " + cnt + "\n");
    }

    System.out.println(sb);
  }
}
