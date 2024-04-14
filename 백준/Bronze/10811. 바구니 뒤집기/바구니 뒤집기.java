import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());

    Queue<Integer> queue = new ArrayDeque<>();
    Stack<Integer> stack = new Stack<>();
    for (int i = 1; i <= N; i++) queue.offer(i);

    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int start = Integer.parseInt(st.nextToken());
      int end = Integer.parseInt(st.nextToken());

      for (int j = 1; j < start; j++) {
        queue.offer(queue.poll());
      }

      for (int j = start; j <= end; j++) {
        stack.push(queue.poll());
      }
      for (int j = start; j <= end; j++) {
        queue.offer(stack.pop());
      }

      for (int j = end + 1; j <= N; j++) {
        queue.offer(queue.poll());
      }
    }

    StringBuilder sb = new StringBuilder();
    for (int i = 1; i <= N; i++) sb.append(queue.poll() + " ");
    System.out.println(sb);
  }
}
