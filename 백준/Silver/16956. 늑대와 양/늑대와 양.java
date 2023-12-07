import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        char[][] map = new char[R][C];

        for(int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();
        }

        int answer = 1;

        int[][] dir = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
        outer:
        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                if(map[i][j] == 'W') {
                    for(int d = 0; d < 4; d++) {
                        int nr = i + dir[d][0];
                        int nc = j + dir[d][1];
                        if(nr < 0 || nr >= R || nc < 0 || nc >= C) continue;

                        if(map[nr][nc] == 'S') {
                            answer = 0;
                            break outer;
                        }
                        if(map[nr][nc] == '.') map[nr][nc] = 'D';
                    }
                }
            }
        }

        System.out.println(answer);
        if(answer == 1) {
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < R; i++) {
                for(int j = 0; j < C; j++) {
                    sb.append(map[i][j]);
                }
                sb.append("\n");
            }

            System.out.println(sb.toString());
        }
    }
}