import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static final int gearNum = 4, toothNum = 8, rightTooth = 2, leftTooth = 6;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[][] gears = new int[gearNum + 1][toothNum];
        for(int i = 1; i <= gearNum; i++) {
            String input = br.readLine();
            for(int j = 0; j < toothNum; j++) {
                gears[i][j] = input.charAt(j) - '0';
            }
        }

        boolean[] rotate = new boolean[gearNum];
        updateRotateArr(rotate, gears);

        int K = Integer.parseInt(br.readLine());
        for(int i = 0; i < K; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            int direction = Integer.parseInt(st.nextToken());

            doRotate(gears[num], direction);

            // 왼쪽 톱니
            int dir = direction;
            for(int n = num - 1; n >= 1; n--) {
                if(rotate[n]) {
                    dir = dir == 1 ? -1 : 1;
                    doRotate(gears[n], dir);
                } else {
                    break;
                }
            }

            // 오른쪽 톱니
            dir = direction;
            for(int n = num + 1; n <= gearNum; n++) {
                if(rotate[n - 1]) {
                    dir = dir == 1 ? -1 : 1;
                    doRotate(gears[n], dir);
                } else {
                    break;
                }
            }

            updateRotateArr(rotate, gears);
        }

        calculateScroe(gears);
    }

    private static void calculateScroe(int[][] gears) {
        int score = 0;
        if(gears[1][0] == 1) score += 1;
        if(gears[2][0] == 1) score += 2;
        if(gears[3][0] == 1) score += 4;
        if(gears[4][0] == 1) score += 8;

        System.out.println(score);
    }

    private static void updateRotateArr(boolean[] rotate, int[][] gears) {
        for(int i = 1; i < gearNum; i++) {
            if(gears[i][rightTooth] != gears[i + 1][leftTooth]) rotate[i] = true;
            else rotate[i] = false;
        }
    }

    private static void doRotate(int[] gear, int direction) {  // direction 1: 시계, -1: 반시계
        if(direction == 1) {
            int temp = gear[toothNum - 1];
            for(int i = toothNum - 1; i > 0; i--) {
                gear[i] = gear[i - 1];
            }
            gear[0] = temp;
        } else if(direction == -1) {
            int temp = gear[0];
            for(int i = 0; i < toothNum - 1; i++) {
                gear[i] = gear[i + 1];
            }
            gear[toothNum - 1] = temp;
        }
    }
}
