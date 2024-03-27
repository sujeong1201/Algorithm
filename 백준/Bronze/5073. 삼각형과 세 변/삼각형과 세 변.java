import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringBuilder sb = new StringBuilder();
    while (true) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int[] length = new int[3];
      length[0] = Integer.parseInt(st.nextToken());
      length[1] = Integer.parseInt(st.nextToken());
      length[2] = Integer.parseInt(st.nextToken());
      if (length[0] == 0) break;

      Arrays.sort(length);
      if (length[2] >= (length[0] + length[1])) sb.append("Invalid\n");
      else if (length[0] == length[1] && length[1] == length[2] && length[0] == length[2])
        sb.append("Equilateral\n");
      else if (length[0] == length[1] || length[1] == length[2] || length[0] == length[2])
        sb.append("Isosceles\n");
      else sb.append("Scalene\n");
    }

    System.out.println(sb);
  }
}
