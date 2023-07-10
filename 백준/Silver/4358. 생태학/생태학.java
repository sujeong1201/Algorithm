import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Map<String, Integer> trees = new TreeMap<>();

        int N = 0;
        String treeName = null;
        while((treeName = br.readLine()) != null) {
            N++;
            if(trees.containsKey(treeName)) {
                int cnt = trees.get(treeName);
                trees.put(treeName, cnt + 1);
            } else {
                trees.put(treeName, 1);
            }
        }

//        StringBuilder sb = new StringBuilder();
        for(String key : trees.keySet()) {
            double percentage = Math.round((double) trees.get(key) / N * 1_000_000) / 10000.0;
            System.out.printf("%s %.4f\n", key, percentage);
        }
//        System.out.println(sb.toString());
    }
}
