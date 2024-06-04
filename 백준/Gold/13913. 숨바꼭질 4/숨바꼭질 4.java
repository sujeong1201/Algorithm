import java.io.*;
import java.util.*;

public class Main {
  static int time, visited[];

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken());
    int K = Integer.parseInt(st.nextToken());
    br.close();

    if (N == K) {
      System.out.println(0);
      System.out.println(N);
      System.exit(0);
    }

    bfs(N, K);

    Stack<Integer> stack = new Stack<>();
    int i = K;
    while (true) {
      if (i == -2) break;

      stack.push(i);
      i = visited[i];
    }

    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    bw.write(time + "\n");
    while (!stack.isEmpty()) bw.write(stack.pop() + " ");
    bw.flush();
    bw.close();
  }

  private static void bfs(int start, int target) {
    final int max = 200_000;
    Queue<Integer> queue = new ArrayDeque<>();
    visited = new int[max + 1];
    Arrays.fill(visited, -1);

    time = 0;
    queue.offer(start);
    visited[start] = -2;

    while (!queue.isEmpty()) {
      time++;
      int size = queue.size();

      for (int s = 0; s < size; s++) {
        int cur = queue.poll();

        for (int d = 0; d < 3; d++) {
          int nextPoint = getNext(cur, d);
          if (nextPoint < 0 || nextPoint > max || visited[nextPoint] != -1) continue;

          visited[nextPoint] = cur;
          if (nextPoint == target) return;

          queue.offer(nextPoint);
        }
      }
    }
  }

  private static int getNext(int point, int d) {
    if (d == 0) return point + 1;
    if (d == 1) return point - 1;
    if (d == 2) return point * 2;
    return -1;
  }
}
