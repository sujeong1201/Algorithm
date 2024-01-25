import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static Map<Character, Character[]> tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        tree = new HashMap<>();
        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            Character node = st.nextToken().charAt(0);
            Character[] child = new Character[] {st.nextToken().charAt(0), st.nextToken().charAt(0)};
            tree.put(node, child);
        }

        System.out.println(preorder('A'));
        System.out.println(inorder('A'));
        System.out.println(postorder('A'));
    }

    private static String preorder(Character node) {
        String result = "";

        result += node;  // 루트
        if(tree.get(node)[0] != '.') result += preorder(tree.get(node)[0]);  // 왼쪽 자식
        if(tree.get(node)[1] != '.') result += preorder(tree.get(node)[1]);  // 오른쪽 자식

        return result;
    }

    private static String inorder(Character node) {
        String result = "";

        if(tree.get(node)[0] != '.') result += inorder(tree.get(node)[0]);  // 왼쪽 자식
        result += node;  // 루트
        if(tree.get(node)[1] != '.') result += inorder(tree.get(node)[1]);  // 오른쪽 자식

        return result;
    }

    private static String postorder(Character node) {
        String result = "";

        if(tree.get(node)[0] != '.') result += postorder(tree.get(node)[0]);  // 왼쪽 자식
        if(tree.get(node)[1] != '.') result += postorder(tree.get(node)[1]);  // 오른쪽 자식
        result += node;  // 루트

        return result;
    }
}
