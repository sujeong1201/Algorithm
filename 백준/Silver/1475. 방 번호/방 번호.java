import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String room = br.readLine();

    int[] numCnt = new int[10];
    for (int i = 0; i < room.length(); i++) {
      int curNum = room.charAt(i) - '0';
      numCnt[curNum]++;
    }

    int sixAndNine = numCnt[6] + numCnt[9];
    numCnt[6] = sixAndNine / 2;
    numCnt[9] = sixAndNine - numCnt[6];

    System.out.println(Arrays.stream(numCnt).max().getAsInt());
  }
}
