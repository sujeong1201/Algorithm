import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        // q를 (0, 0)으로
        char[] leftHandChar = {'q', 'w', 'e', 'r', 't',
                                'a', 's', 'd', 'f', 'g',
                                'z', 'x', 'c', 'v'};
        int[][] leftHandPos = {{0, 0}, {0, 1}, {0, 2}, {0, 3}, {0, 4},
                                {1, 0}, {1, 1}, {1, 2}, {1, 3}, {1, 4},
                                {2, 0}, {2, 1}, {2, 2}, {2, 3}};
        char[] rightHandChar = {'y', 'u', 'i', 'o', 'p',
                                'h', 'j', 'k', 'l',
                                'b', 'n', 'm'};
        int[][] rightHandPos = {{0, 5}, {0, 6}, {0, 7}, {0, 8}, {0,9},
                                {1, 5}, {1, 6}, {1, 7}, {1, 8},
                                {2, 4}, {2, 5}, {2, 6}};
        Map<Character, int[]> leftHandMap = new HashMap<>();
        for(int i = 0; i < leftHandChar.length; i++) leftHandMap.put(leftHandChar[i], leftHandPos[i]);
        Map<Character, int[]> rightHandMap = new HashMap<>();
        for(int i = 0; i < rightHandChar.length; i++) rightHandMap.put(rightHandChar[i], rightHandPos[i]);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        char curLeftHand = st.nextToken().charAt(0);
        char curRightHand = st.nextToken().charAt(0);
        String targetStr = br.readLine();

        int time = 0;
        for(int i = 0; i < targetStr.length(); i++) {
            char curTarget = targetStr.charAt(i);

            if(leftHandMap.containsKey(curTarget)) {
                int[] curLeftHandPos = leftHandMap.get(curLeftHand);
                int[] nextHandPos = leftHandMap.get(curTarget);
                time += getDistance(curLeftHandPos, nextHandPos) + 1;
                curLeftHand = curTarget;
            } else {
                int[] curRightHandPos = rightHandMap.get(curRightHand);
                int[] nextHandPos = rightHandMap.get(curTarget);
                time += getDistance(curRightHandPos, nextHandPos) + 1;
                curRightHand = curTarget;
            }
        }

        System.out.println(time);
    }

    private static int getDistance(int[] curHandPos, int[] nextHandPos) {
        return Math.abs(curHandPos[0] - nextHandPos[0]) +
                Math.abs(curHandPos[1] - nextHandPos[1]);
    }
}
