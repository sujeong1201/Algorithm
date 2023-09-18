import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static boolean friend[][], selected[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        friend = new boolean[N + 1][N + 1];
        selected = new boolean[N + 1];

        for(int i = 0; i < M; i++) {
            String[] tmp = br.readLine().split(" ");
            int a = Integer.parseInt(tmp[0]);
            int b = Integer.parseInt(tmp[1]);
            friend[a][b] = friend[b][a] = true;
        }

        Queue<Integer> queue = new ArrayDeque<>();
        for(int i = 1; i <= N; i++) {
            if(friend[1][i]) {
                selected[i] = true;
                queue.offer(i);
            }
        }

        while(!queue.isEmpty()) {
            int cur = queue.poll();
            for(int i = 2; i <= N; i++) {
                if(!selected[i] && friend[cur][i]) {
                    selected[i] = true;
                }
            }
        }

        int answer = 0;
        for(int i = 2; i <= N; i++) if(selected[i]) answer++;
        System.out.println(answer);
    }
}
