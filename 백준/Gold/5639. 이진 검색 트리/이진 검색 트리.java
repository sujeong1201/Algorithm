import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static StringBuilder sb;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Integer> preOrder = new ArrayList<>();
        while(sc.hasNext()) {
            preOrder.add(sc.nextInt());
        }

        sb = new StringBuilder();
        makePostOrder(preOrder);
        System.out.println(sb);
    }

    private static void makePostOrder(List<Integer> preOrder) {
        if(preOrder.size() == 0) return;

        int root = preOrder.get(0);
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        int index = 1;
        for( ; index < preOrder.size(); index++) {
            if(preOrder.get(index) < root) left.add(preOrder.get(index));
            else break;
        }
        for(; index < preOrder.size(); index++) {
            right.add(preOrder.get(index));
        }

        makePostOrder(left);
        makePostOrder(right);
        sb.append(root + "\n");
    }
}
