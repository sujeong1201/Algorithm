import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for(int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int r1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            int r2 = Integer.parseInt(st.nextToken());

            double distance = Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));

            if(distance == 0 && r1 == r2) {  // 두 원 일치
                sb.append(-1 + "\n");
            } else if(distance > r1 + r2 || distance < Math.abs(r1 - r2)) {  // 두 원이 만나지 않음
                sb.append(0 + "\n");
            } else if(distance == r1 + r2 || distance == Math.abs(r1 - r2)) {  // 두 원이 한 점에서 만남
                sb.append(1 + "\n");
            } else {
                sb.append(2 + "\n");
            }
        }
            
        System.out.println(sb);
    }

}
