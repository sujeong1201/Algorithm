import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static class Node {
        int durability;
        boolean withRobot;

        public Node(int durability) {
            this.durability = durability;
            this.withRobot = false;
        }

        public void put() {
            this.durability--;
            this.withRobot = true;
        }

        public void remove() {
            this.withRobot = false;
        }

        @Override
        public String toString() {
            return durability + " " + withRobot;
        }
    }

    static int N, K;
    static Node conveyor[];
    static List<Integer> robots;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        conveyor = new Node[2 * N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < 2 * N; i++) {
            conveyor[i] = new Node(Integer.parseInt(st.nextToken()));
        }

        robots = new ArrayList<>();
        int step = 0;
        while(true) {
            step++;
            move();

            if(isFinish()) break;
        }

        System.out.println(step);
    }

    private static boolean isFinish() {
        int cnt = 0;
        for(Node node : conveyor) {
            if(node.durability == 0) cnt++;
        }

        if(cnt >= K) return true;
        else return false;
    }

    private static void move() {
        // 1단계: 벨트 회전
        rotateConveyor();
        // 2단계: 로봇 이동
        moveRobot();
        // 3단계: 로봇 올리기
        putRobot();
    }

    private static void putRobot() {
        if(conveyor[0].durability == 0) return;

        conveyor[0].put();
    }

    private static void moveRobot() {
        for(int i = N - 2; i >= 0; i--) {
            if(!conveyor[i].withRobot) continue;
            if(conveyor[i + 1].withRobot || conveyor[i + 1].durability == 0) continue;

            conveyor[i + 1].put();
            conveyor[i].remove();
        }

        if(conveyor[N - 1].withRobot) conveyor[N - 1].withRobot = false;
    }

    private static void rotateConveyor() {
        Node temp = conveyor[2 * N - 1];
        for(int i = 2 * N - 1; i > 0; i--) {
            conveyor[i] = conveyor[i - 1];
        }
        conveyor[0] = temp;

        if(conveyor[N - 1].withRobot) conveyor[N - 1].withRobot = false;
    }
}
