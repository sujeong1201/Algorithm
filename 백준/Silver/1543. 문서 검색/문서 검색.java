import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String str = br.readLine();
    String target = br.readLine();

    int cnt = 0;
    int index = 0;
    for (; index < str.length(); ) {
      if (index + target.length() > str.length()) break;

      if (target.equals(str.substring(index, index + target.length()))) {
        cnt++;
        index += target.length();
      } else {
        index++;
      }
    }

    System.out.println(cnt);
  }
}
