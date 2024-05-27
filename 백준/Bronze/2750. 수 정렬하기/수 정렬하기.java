import java.io.*;
import java.util.Arrays;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());
    int[] numbers = new int[N];
    for (int i = 0; i < N; i++) numbers[i] = Integer.parseInt(br.readLine());
    br.close();

    Arrays.sort(numbers);
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    for (int i = 0; i < N; i++) bw.write(numbers[i] + "\n");
    bw.flush();
    bw.close();
  }
}
