import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());

    PriorityQueue<int[]> pq =
        new PriorityQueue<>(
            (arr1, arr2) -> {
              if (arr1[1] == arr2[1]) return arr1[0] - arr2[0];
              return arr1[1] - arr2[1];
            });
    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      pq.offer(new int[] {i, Integer.parseInt(st.nextToken())});
    }

    int[] answer = new int[N];
    for (int i = 0; i < N; i++) {
      answer[pq.poll()[0]] = i;
    }

    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    for (int i = 0; i < N; i++) bw.write(answer[i] + " ");
    bw.flush();
    bw.close();
  }
}
