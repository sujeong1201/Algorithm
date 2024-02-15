import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        Deque<int[]> deque = new ArrayDeque<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) {
            deque.offer(new int[] {i, Integer.parseInt(st.nextToken())});
        }

        StringBuilder sb = new StringBuilder();
        int num = 1;
        for(int cnt = 0; cnt < N; cnt++) {
            int[] cur = null;

            if(num > 0) {
                while (--num != 0) {
                    deque.offerLast(deque.pollFirst());
                }
                cur = deque.pollFirst();
            } else if(num < 0) {
                while(++num != 0) {
                    deque.offerFirst(deque.pollLast());
                }
                cur = deque.pollLast();
            }

            sb.append(cur[0] + " ");
            num = cur[1];
        }

        System.out.println(sb);
    }
}
