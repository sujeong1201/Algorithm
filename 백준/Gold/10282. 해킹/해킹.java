import java.io.*;
import java.util.*;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int T = Integer.parseInt(br.readLine());

    while (T-- > 0) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int n = Integer.parseInt(st.nextToken());
      int d = Integer.parseInt(st.nextToken());
      int c = Integer.parseInt(st.nextToken());

      List<int[]> adjList[] = new ArrayList[n + 1];
      for (int i = 1; i <= n; i++) adjList[i] = new ArrayList<>();
      while (d-- > 0) {
        st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());
        adjList[b].add(new int[] {a, s});
      }

      int[] result = dijkstra(n, c, adjList);
      bw.write(result[0] + " " + result[1] + "\n");
    }

    bw.flush();
    bw.close();
  }

  private static int[] dijkstra(int n, int c, List<int[]>[] adjList) {
    int[] time = new int[n + 1];
    boolean[] visited = new boolean[n + 1];
    PriorityQueue<int[]> pq = new PriorityQueue<>((arr1, arr2) -> arr1[1] - arr2[1]);

    Arrays.fill(time, Integer.MAX_VALUE);
    time[c] = 0;
    pq.offer(new int[] {c, 0});

    while (!pq.isEmpty()) {
      int[] cur = pq.poll();
      visited[cur[0]] = true;

      for (int[] computer : adjList[cur[0]]) {
        if (!visited[computer[0]] && time[computer[0]] > cur[1] + computer[1]) {
          time[computer[0]] = cur[1] + computer[1];
          pq.offer(new int[] {computer[0], time[computer[0]]});
        }
      }
    }

    int[] result = new int[2];
    for (int i = 1; i <= n; i++) {
      if (time[i] != Integer.MAX_VALUE) {
        result[0]++;
        if (time[i] > result[1]) result[1] = time[i];
      }
    }
    return result;
  }
}
