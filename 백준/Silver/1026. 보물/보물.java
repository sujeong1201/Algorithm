import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());
    PriorityQueue<Integer> arrA = new PriorityQueue<>();
    PriorityQueue<Integer> arrB = new PriorityQueue<>((i1, i2) -> i2 - i1);
    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) arrA.offer(Integer.parseInt(st.nextToken()));
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) arrB.offer(Integer.parseInt(st.nextToken()));

    int result = 0;
    for (int i = 0; i < N; i++) {
      int elA = arrA.poll();
      int elB = arrB.poll();
      result += elA * elB;
    }

    System.out.println(result);
  }
}
