import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static boolean relation[][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        relation = new boolean[N + 1][N + 1];

        int M = Integer.parseInt(st.nextToken());
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            relation[a][b] = true;
        }

        int answer = 0;
        for(int i = 1; i <= N; i++) {
            boolean[] visited = new boolean[N + 1];
            bfs(i, visited);
            bfsReverse(i, visited);
            if(knowOrder(visited)) answer++;
        }

        System.out.println(answer);
    }

    private static boolean knowOrder(boolean[] visited) {
        for(int i = 1; i <= N; i++) {
            if(!visited[i]) return false;
        }

        return true;
    }

    private static void bfs(int num, boolean[] visited) {
        visited[num] = true;

        for(int i = 1; i <= N; i++) {
            if(!visited[i] && relation[num][i]) bfs(i, visited);
        }
    }

    private static void bfsReverse(int num, boolean[] visited) {
        visited[num] = true;

        for(int i = 1; i <= N; i++) {
            if(!visited[i] && relation[i][num]) bfsReverse(i, visited);
        }
    }
}
