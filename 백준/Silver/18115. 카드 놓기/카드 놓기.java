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
        int[] skill = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) skill[i] = Integer.parseInt(st.nextToken());

        Deque<Integer> deque = new ArrayDeque<>();
        for(int i = 1; i <= N; i++) {
            if(skill[N - i] == 1) {
                deque.offerLast(i);
            } else if(skill[N - i] == 2) {
                int temp = deque.pollLast();
                deque.offerLast(i);
                deque.offerLast(temp);
            } else {
                deque.offerFirst(i);
            }
        }

        StringBuffer sb = new StringBuffer();
        while(!deque.isEmpty()) {
            sb.append(deque.pollLast() + " ");
        }
        System.out.println(sb);
    }
}
