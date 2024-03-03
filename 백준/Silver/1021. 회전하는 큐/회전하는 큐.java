import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] sequence = new int[M];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < M; i++) sequence[i] = Integer.parseInt(st.nextToken());

        System.out.println(solve(N, M, sequence));
    }

    private static int solve(int N, int M, int[] sequence) {
        Deque<Integer> deque = new ArrayDeque<>();
        for(int i = 1; i <= N; i++) deque.offer(i);

        int answer = 0;
        for(int i = 0; i < M; i++) {
            if (deque.peekFirst() == sequence[i]) {
                deque.poll();
            } else {
                // 왼쪽 시프트
                int leftShift = 0;
                while (true) {
                    int next = deque.pollFirst();
                    if (next == sequence[i]) break;
                    deque.offerLast(next);
                    leftShift++;
                }
                // 원상복구
                deque.offerFirst(sequence[i]);
                int cnt = leftShift;
                while (cnt-- > 0) {
                    deque.offerFirst(deque.pollLast());
                }

                // 오른쪽 시프트
                int rightShift = 0;
                while (true) {
                    int next = deque.pollLast();
                    if (next == sequence[i]) {
                        rightShift++;
                        break;
                    }
                    deque.offerFirst(next);
                    rightShift++;
                }

                answer += Math.min(leftShift, rightShift);
            }
        }

        return answer;
    }
}

