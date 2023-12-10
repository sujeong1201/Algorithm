import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        // 각 자리에서 가정 많이 나오는 문자로 구성
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        char[][] dnas = new char[N][];
        for(int i = 0; i < N; i++) {
            dnas[i] = br.readLine().toCharArray();
        }

        char[] answerStr = new char[M];
        int answerDistance = 0;
        char[] dnaAlphabet = {'A', 'C', 'G', 'T'};
        for(int i = 0; i < M; i++) {
            Map<Character, Integer> counts = new HashMap<>();
            for(int a = 0; a < 4; a++) {
                counts.put(dnaAlphabet[a], 0);
            }

            for(int j = 0; j < N; j++) {
                int cnt = counts.get(dnas[j][i]);
                counts.put(dnas[j][i], cnt + 1);
            }

            int maxCnt = 0;
            for(int a = 0; a < 4; a++) {
                if(counts.get(dnaAlphabet[a]) > maxCnt) {
                    maxCnt = counts.get(dnaAlphabet[a]);
                    answerStr[i] = dnaAlphabet[a];
                }
            }
            answerDistance += (N - maxCnt);
        }

        for(int i = 0; i < M; i++) System.out.print(answerStr[i]);
        System.out.println();
        System.out.println(answerDistance);
    }
}
