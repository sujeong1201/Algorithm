import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        /*
        K = 3이라고 하면
        분: 03, 13, 23, 33, 43, 53, 30, 31, 32, 34, 35, 36, 37, 38, 39
        초: 03, 13, 23, 33, 43, 53, 30, 31, 32, 34, 35, 36, 37, 38, 39
        시가 3이 아닐 경우, 한 시간에 60*15 + 60*15 - 15*15 = 1575
        시가 3일 경우, 60*60
        N = 5라고 하면
        1575 * 5 + 3600 = 11475
        =====고려해야할 조건이 많음..======

        완탐으로 돌릴 경우
        최대 24 * 60 * 60 = 86,400 -> 그냥 완탐 고!
         */
        int cnt = 0;
        for(int h = 0; h <= N; h++) {
            if(h % 10 == K || h / 10 == K) {
                cnt += 60 * 60;
                continue;
            }

            for(int m = 0; m < 60; m++) {
                if(m % 10 == K || m / 10 == K) {
                    cnt += 60;
                    continue;
                }

                for(int s = 0; s < 60; s++) {
                    if(s % 10 == K || s / 10 == K) cnt++;
                }
            }
        }

        System.out.println(cnt);
    }
}