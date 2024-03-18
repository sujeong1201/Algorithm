import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, maxBroken;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int[][] eggs = new int[N][2];
        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            eggs[i][0] = Integer.parseInt(st.nextToken());
            eggs[i][1] = Integer.parseInt(st.nextToken());
        }

        maxBroken = 0;
        recur(0, eggs);
        System.out.println(maxBroken);
    }

    private static void recur(int cnt, int[][] eggs) {
        if(cnt == N) {
            int broken = 0;
            for(int i = 0; i < N; i++) {
                if(eggs[i][0] <= 0) broken++;
            }
            if(broken > maxBroken) maxBroken = broken;

            return;
        }

        if(eggs[cnt][0] <= 0) {
            recur(cnt + 1, eggs);
        } else {
            boolean flag = false;
            for (int i = 0; i < N; i++) {
                if(i == cnt || eggs[i][0] <= 0) continue;

                flag = true;
                eggs[cnt][0] -= eggs[i][1];
                eggs[i][0] -= eggs[cnt][1];
                recur(cnt + 1, eggs);

                eggs[cnt][0] += eggs[i][1];
                eggs[i][0] += eggs[cnt][1];
            }

            if(!flag) recur(cnt + 1, eggs);
        }
    }
}
