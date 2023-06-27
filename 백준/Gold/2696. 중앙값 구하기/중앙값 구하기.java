import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());

        for(int t = 0; t < T; t++) {
            int M = Integer.parseInt(br.readLine());
            sb.append((M / 2 + 1) + "\n");

            findMedian(M);
        }

        System.out.println(sb);
    }

    private static void findMedian(int M) throws IOException {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        Queue<Integer> queue = new ArrayDeque<>();

        StringTokenizer st = null;
        int cnt = 0; // 10개씩 출력하기 위한 카운트
        for(int i = 1; i <= M; i++) {
            if(i % 10 == 1) st = new StringTokenizer(br.readLine());

            pq.offer(Integer.parseInt(st.nextToken()));
            // 짝수번째 수 : 큐에 넣기만함
            if(i % 2 == 0) continue;

            // 홀수번째 수 : 큐에 넣고 중앙값 구하기
            for(int j = 1; j <= (i / 2 + 1); j++) {
                if(cnt == 10) {
                    sb.append("\n");
                    cnt = 0;
                }

                int cur = pq.poll();
                if(j == (i / 2 + 1)) {
                    sb.append(cur + " ");
                    cnt++;
                }

                queue.offer(cur);
            }

            while(!queue.isEmpty()) {
                pq.offer(queue.poll());
            }
        }

        sb.append("\n");
    }
}
