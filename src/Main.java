import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {

        final RedBlackTree tree = new RedBlackTree();

        System.out.println("RedBlack Tree Demo:...\n");

        int [] nums = {1, 3, 4, 11, 44, 5, 6, 7, 8, 13};

        for (int num : nums) {
            tree.add(num);
        }

        tree.printTree();

    }
}