import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
  static int F, S, G, U, D;

  public static void main(String[] args) throws IOException {
    StringTokenizer st =
        new StringTokenizer(new BufferedReader(new InputStreamReader(System.in)).readLine());
    F = Integer.parseInt(st.nextToken()); // 총 층수
    S = Integer.parseInt(st.nextToken()); // 현재 위치
    G = Integer.parseInt(st.nextToken()); // 타겟 위치
    U = Integer.parseInt(st.nextToken()); // 위
    D = Integer.parseInt(st.nextToken()); // 아래
    if (S == G) {
      System.out.println(0);
      System.exit(0);
    }
    if (S > G && D == 0 || S < G && U == 0) {
      System.out.println("use the stairs");
      System.exit(0);
    }

    int result = bfs();
    System.out.println(result == -1 ? "use the stairs" : result);
  }

  private static int bfs() {
    Queue<Integer> queue = new ArrayDeque<>();
    boolean[] visited = new boolean[F + 1];
    int[] dir = new int[] {U, D * -1};

    int level = 0;
    queue.offer(S);
    visited[S] = true;

    while (!queue.isEmpty()) {
      int size = queue.size();

      for (int s = 0; s < size; s++) {
        int cur = queue.poll();
        if (cur == G) {
          return level;
        }

        for (int d = 0; d < 2; d++) {
          int next = cur + dir[d];
          if (next <= 0 || next > F || visited[next]) continue;

          queue.offer(next);
          visited[next] = true;
        }
      }

      level++;
    }

    return -1;
  }
}
