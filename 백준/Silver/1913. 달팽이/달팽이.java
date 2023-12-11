import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N, X;
    static int map[][], position[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        X = Integer.parseInt(br.readLine());
        map = new int[N][N];
        position = new int[2];

        snake();

        StringBuilder sb = new StringBuilder();
        for(int r = 0; r < N; r++) {
            for(int c = 0; c < N; c++) sb.append(map[r][c] + " ");
            sb.append("\n");
        }
        sb.append(position[0] + " " + position[1]);
        System.out.println(sb.toString());
    }

    static void snake() {
        int[] dr = {-1, 0, 1, 0};
        int[] dc = {0, 1, 0, -1};
        int num = 1;
        int r = N / 2, c = N / 2;

        if(num == X) position = new int[] {r + 1, c + 1};
        map[r][c] = num++;

        int dir = 0;
        int cnt = 2;
        outer:
        while(true) {
            for(int i = 0; i < cnt / 2; i++) {
                r += dr[dir];
                c += dc[dir];
                if(r < 0 || r >= N || c < 0 || c >= N) break;

                if(num == X) position = new int[] {r + 1, c + 1};
                map[r][c] = num++;
            }

            if(num > N * N) break;
            cnt++;
            dir = (dir + 1) % 4;
        }
    }
}
