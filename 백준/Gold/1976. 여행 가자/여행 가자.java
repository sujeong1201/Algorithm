import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
  static int N, adjMatrix[][];

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    N = Integer.parseInt(br.readLine());
    int M = Integer.parseInt(br.readLine());
    adjMatrix = new int[N + 1][N + 1];
    for (int i = 1; i <= N; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      for (int j = 1; j <= N; j++) adjMatrix[i][j] = Integer.parseInt(st.nextToken());
    }
    int[] plan = new int[M];
    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < M; i++) plan[i] = Integer.parseInt(st.nextToken());

    // 각 도시를 시작점으로 해서 갈 수 있는 곳 다 표시
    for (int i = 1; i <= N; i++) {
      bfs(i);
    }

    for (int i = 0; i < M - 1; i++) {
      if (adjMatrix[plan[i]][plan[i + 1]] == 0) {
        System.out.println("NO");
        System.exit(0);
      }
    }

    System.out.println("YES");
  }

  public static void bfs(int start) {
    Queue<Integer> queue = new ArrayDeque<>();
    boolean[] visited = new boolean[N + 1];

    queue.offer(start);
    visited[start] = true;

    while (!queue.isEmpty()) {
      int cur = queue.poll();

      for (int i = 1; i <= N; i++) {
        if (adjMatrix[cur][i] == 1 && !visited[i]) {
          queue.offer(i);
          visited[i] = true;
        }
      }
    }

    for (int i = 1; i <= N; i++) {
      if (visited[i]) adjMatrix[start][i] = 1;
    }
  }
}
