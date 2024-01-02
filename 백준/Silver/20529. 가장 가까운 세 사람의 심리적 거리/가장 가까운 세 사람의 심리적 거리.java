import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for(int t = 0; t < T; t++) {
            int N = Integer.parseInt(br.readLine());
            String[] mbti = new String[N];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i = 0; i < N; i++) mbti[i] = st.nextToken();

            int min = Integer.MAX_VALUE;
            // 3개만 고르면 되니까 그냥 반복문으로 구현
            loop:
            for(int i = 0; i < N; i++) {
                for(int j = i + 1; j < N; j++) {
                    for(int k = j + 1; k < N; k++) {
                        int distance = calcDistance(mbti[i], mbti[j], mbti[k]);
                        min = Math.min(min, distance);
                        if(min == 0) break loop;
                    }
                }
            }

            sb.append(min + "\n");
        }

        System.out.println(sb);
    }

    private static int calcDistance(String mbti1, String mbti2, String mbti3) {
        return distance(mbti1, mbti2) + distance(mbti2, mbti3) + distance(mbti1, mbti3);
    }

    public static int distance(String mbti1, String mbti2) {
        int value = 0;

        if(mbti1.charAt(0) != mbti2.charAt(0)) value++;
        if(mbti1.charAt(1) != mbti2.charAt(1)) value++;
        if(mbti1.charAt(2) != mbti2.charAt(2)) value++;
        if(mbti1.charAt(3) != mbti2.charAt(3)) value++;

        return value;
    }
}
