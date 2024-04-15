import java.io.*;
import java.util.StringTokenizer;

public class Main {
  static int N, maximum[];
  static String title[];

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());

    maximum = new int[N];
    title = new String[N];
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      title[i] = st.nextToken();
      maximum[i] = Integer.parseInt(st.nextToken());
    }

    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    for (int i = 0; i < M; i++) {
      bw.write(binarySearch(Integer.parseInt(br.readLine())) + "\n");
    }

    bw.flush();
    bw.close();
  }

  private static String binarySearch(int power) {
    int start = 0, end = N - 1;

    while (start < end) {
      int middle = (start + end) / 2;

      if (power > maximum[middle]) {
        start = middle + 1;
      } else {
        end = middle;
      }
    }

    return title[start];
  }
}
