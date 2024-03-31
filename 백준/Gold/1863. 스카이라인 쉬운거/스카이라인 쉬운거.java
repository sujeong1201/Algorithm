import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());

    int cnt = 0;
    Stack<Integer> stack = new Stack<>();
    for (int i = 0; i < N; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int x = Integer.parseInt(st.nextToken());
      int y = Integer.parseInt(st.nextToken());

      if (y == 0) {
        while (!stack.isEmpty()) {
          stack.pop();
          cnt++;
        }
      } else if (stack.isEmpty() || stack.peek() < y) {
        stack.push(y);
      } else if (stack.peek() > y) {
        while (!stack.isEmpty() && stack.peek() > y) {
          stack.pop();
          cnt++;
        }
        if (stack.isEmpty() || stack.peek() != y) stack.push(y);
      }
    }

    while (!stack.isEmpty()) {
      stack.pop();
      cnt++;
    }

    System.out.println(cnt);
  }
}
