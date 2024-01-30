import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {
    static Map<Integer, Integer> triangleNum;
    static int maxTriangle;
    
    public static <List> void main(String[] args) throws IOException {
        triangleNum = new HashMap<>();
        for(int n = 1; ; n++) {
            int num = n * (n + 1) / 2;
            triangleNum.put(n, num);
            if(num > 1000) break;
        }
        maxTriangle = triangleNum.size();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for(int t = 0; t < T; t++) {
            int target = Integer.parseInt(br.readLine());
            if(solve(target)) sb.append("1\n");
            else sb.append("0\n");
        }

        System.out.println(sb);
    }

    private static boolean solve(int target) {
        for(int i = 1; i <= maxTriangle; i++) {
            for(int j = 1; j <= maxTriangle; j++) {
                for(int k = 1; k <= maxTriangle; k++) {
                    int sum = triangleNum.get(i) + triangleNum.get(j) + triangleNum.get(k);
                    if(sum == target) return true;
                }
            }
        }

        return false;
    }
}
