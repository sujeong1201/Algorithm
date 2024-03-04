import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    static int N, K, L;
    static boolean appleMap[][];
    static int turn[][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        appleMap = new boolean[N + 1][N + 1];
        K = Integer.parseInt(br.readLine());
        for(int i = 0; i < K; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            appleMap[r][c] = true;
        }
        L = Integer.parseInt(br.readLine());
        turn = new int[L][2];
        for(int i = 0; i < L; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            turn[i][0] = Integer.parseInt(st.nextToken());
            turn[i][1] = st.nextToken().equals("L") ? 1 : 2;  // 왼쪽은 1, 오른쪽은 2
        }

        System.out.println(solve());
    }

    public static int solve() {
        int time = 0;
        // 뱀의 초기 방향 오른쪽
        // 0-오른쪽, 1-아래쪽, 2-왼쪽, 3-위쪽
        int direction = 0;
        int index = 0;  // 이동경로 인덱스

        Deque<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[] {1, 1});  // 뱀의 초기 위치

        outer:
        while(true) {
            time++;
            int[] nextHead = getNextHead(queue.peekFirst(), direction);
            if(nextHead[0] <= 0 || nextHead[0] > N || nextHead[1] <= 0 || nextHead[1] > N) break;

            for(int[] body : queue) {
                if(body[0] == nextHead[0] && body[1] == nextHead[1]) break outer;
            }

            queue.offerFirst(nextHead);
            if(!appleMap[nextHead[0]][nextHead[1]]) {
                queue.pollLast();
            } else {
                appleMap[nextHead[0]][nextHead[1]] = false;
            }

            if(index < L && turn[index][0] == time) {
                if(turn[index][1] == 1) direction = direction == 0 ? 3 : direction - 1;
                else direction = (direction + 1) % 4;
                index++;
            }
        }

        return time;
    }

    private static int[] getNextHead(int[] curHead, int direction) {
        if(direction == 0) {
            return new int[] {curHead[0], curHead[1] + 1};
        } else if(direction == 1) {
            return new int[] {curHead[0] + 1, curHead[1]};
        } else if(direction == 2) {
            return new int[] {curHead[0], curHead[1] - 1};
        } else {
            return new int[] {curHead[0] - 1, curHead[1]};
        }
    }
}
