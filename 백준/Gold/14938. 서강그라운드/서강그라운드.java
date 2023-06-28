import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static class Point {
        int num;
        int length;

        public Point(int num, int length) {
            this.num = num;
            this.length = length;
        }
    }

    static int N, M, R;
    static int items[], adj[][];
    static int maxItem;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        items = new int[N + 1];
        adj = new int[N + 1][N + 1];

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) {
            items[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 1; i <= R; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            adj[from][to] = adj[to][from] = Integer.parseInt(st.nextToken());
        }

        maxItem = 0;
        for(int i = 1; i <= N; i++) {
            bfs(i);
        }

        System.out.println(maxItem);
    }

    private static void bfs(int start) {
        PriorityQueue<Point> queue = new PriorityQueue<>(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                return Integer.compare(o1.length, o2.length);
            }
        });
        boolean[] visited = new boolean[N + 1];
        queue.offer(new Point(start, 0));

        int item = 0;
        while(!queue.isEmpty()) {
            Point cur = queue.poll();
            if(visited[cur.num]) continue;
            visited[cur.num] = true;
            item += items[cur.num];

            for(int i = 1; i <= N; i++) {
                if(!visited[i] && adj[cur.num][i] > 0 && cur.length + adj[cur.num][i] <= M) {
                    queue.offer(new Point(i, cur.length + adj[cur.num][i]));
                }
            }
        }

        if(item > maxItem) maxItem = item;
    }
}
