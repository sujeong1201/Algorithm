import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static class Node {
        int r;
        int c;
        boolean wallBroken;

        public Node(int r, int c, boolean wallBroken) {
            this.r = r;
            this.c = c;
            this.wallBroken = wallBroken;
        }
    }

    static int N, M;
    static char map[][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        for(int i = 0; i < N; i++) map[i] = br.readLine().toCharArray();

        System.out.println(bfs());
    }

    private static int bfs() {
        Queue<Node> queue = new ArrayDeque<>();
        boolean[][] visitedTrue = new boolean[N][M];
        boolean[][] visitedFalse = new boolean[N][M];
        int[] dr = {0, 0, 1, -1};
        int[] dc = {1, -1, 0, 0};

        queue.offer(new Node(0, 0, false));
        visitedTrue[0][0] = true;
        visitedFalse[0][0] = true;

        int level = 1;
        while(!queue.isEmpty()) {
            int size = queue.size();

            for(int s = 0; s < size; s++) {
                Node cur = queue.poll();
                if(cur.r == N - 1 && cur.c == M - 1) return level;

                for(int d = 0; d < 4; d++) {
                    int nr = cur.r + dr[d];
                    int nc = cur.c + dc[d];
                    if(nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
                    if((cur.wallBroken && visitedTrue[nr][nc]) || (!cur.wallBroken && visitedFalse[nr][nc])) continue;

                    if(map[nr][nc] == '1' && !cur.wallBroken) {
                        queue.offer(new Node(nr, nc, true));
                        visitedFalse[nr][nc] = true;
                    } else if(map[nr][nc] == '0') {
                        queue.offer(new Node(nr, nc, cur.wallBroken));
                        if(cur.wallBroken) visitedTrue[nr][nc] = true;
                        else visitedFalse[nr][nc] = true;
                    }
                }
            }

            level++;
        }

        return -1;
    }
}
