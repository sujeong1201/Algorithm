import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[] inputArr = new String[N];
        Map<String, Integer> extMap = new HashMap<>();

        for(int i = 0; i < N; i++) {
            String[] fileSplit = br.readLine().split("\\.");

            int cnt = 0;
            if(extMap.containsKey(fileSplit[1])) {
                cnt = extMap.get(fileSplit[1]);
            }
            extMap.put(fileSplit[1], cnt + 1);
        }

        List<String> extList = new ArrayList<>(extMap.keySet());
        Collections.sort(extList);
        StringBuilder sb = new StringBuilder();
        for(String ext : extList) {
            sb.append(ext + " " + extMap.get(ext) + "\n");
        }

        System.out.println(sb);
    }
}
