import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken());
    String gameType = st.nextToken();
    int playerNum = gameType.equals("Y") ? 1 : (gameType.equals("F") ? 2 : 3);

    Set<String> playerSet = new HashSet<>();
    while (N-- > 0) {
      playerSet.add(br.readLine());
    }
    System.out.println(playerSet.size() / playerNum);
  }
}
