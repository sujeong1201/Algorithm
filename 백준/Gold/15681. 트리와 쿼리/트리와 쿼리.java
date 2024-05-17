import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
  static List<Integer> edges[];
  static int[] dp;
  static boolean[] visited;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken());
    int R = Integer.parseInt(st.nextToken());
    int Q = Integer.parseInt(st.nextToken());
    edges = new ArrayList[N + 1];
    for (int i = 1; i <= N; i++) edges[i] = new ArrayList<>();
    for (int i = 0; i < N - 1; i++) {
      st = new StringTokenizer(br.readLine());
      int u = Integer.parseInt(st.nextToken());
      int v = Integer.parseInt(st.nextToken());
      edges[u].add(v);
      edges[v].add(u);
    }

    dp = new int[N + 1];
    visited = new boolean[N + 1];
    dfs(R);

    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    for (int i = 0; i < Q; i++) {
      bw.write(dp[Integer.parseInt(br.readLine())] + "\n");
    }

    bw.flush();
    bw.close();
    br.close();
  }

  private static int dfs(int node) {
    visited[node] = true;

    for (Integer nextNode : edges[node]) {
      if (visited[nextNode]) continue;

      dp[node] += dfs(nextNode);
    }

    dp[node] += 1; // 자기 자신
    return dp[node];
  }
}
