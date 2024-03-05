import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n, m, p[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        p = new int[n + 1];
        makeSet();
        
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int op = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if(op == 0) {
                union(a, b);
            } else {
                if(find(a) == find(b)) sb.append("YES\n");
                else sb.append("NO\n");
            }
        }

        System.out.println(sb);
    }

    private static void union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);

        if(aRoot != bRoot) p[bRoot] = aRoot;
    }

    private static int find(int a) {
        if(p[a] == a) return a;

        return p[a] = find(p[a]);
    }

    private static void makeSet() {
        for(int i = 0; i <= n; i++) {
            p[i] = i;
        }
    }
}
