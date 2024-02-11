import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        ArrayList<char[][]> pictures = new ArrayList<>();
        for(int i = 0; i < N; i++) {
            char[][] curPic = new char[5][7];
            for(int j = 0; j < 5; j++) {
                curPic[j] = br.readLine().toCharArray();
            }

            pictures.add(curPic);
        }

        int minDiff = Integer.MAX_VALUE;
        int[] minDiffPics = new int[2];
        for(int i = 0; i < N; i++) {
            for(int j = i + 1; j < N; j++) {
                int diff = comparePicture(pictures.get(i), pictures.get(j));
                if(diff < minDiff) {
                    minDiff = diff;
                    minDiffPics = new int[] {i, j};
                }
            }
        }

        System.out.println((minDiffPics[0] + 1) + " " + (minDiffPics[1] + 1));
    }

    private static int comparePicture(char[][] pic1, char[][] pic2) {
        int cnt = 0;

        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 7; j++) {
                if(pic1[i][j] != pic2[i][j]) cnt++;
            }
        }

        return cnt;
    }
}
