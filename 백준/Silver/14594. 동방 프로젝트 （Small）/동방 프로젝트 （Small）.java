import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        boolean[] noWall = new boolean[N + 1];  // 오른쪽 벽이 있는지 여부

        for(int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            while(x < y) {
                noWall[x] = true;
                x++;
            }
        }

        int cnt = 0;
        for(int i = 1; i <= N; i++) {
            if(!noWall[i]) cnt++;
        }

        System.out.println(cnt);
    }
}
