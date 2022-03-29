import java.util.Random;

public class Test {

    static Random rnd = new Random();

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            System.out.println(rnd.nextInt(10 + 1 -5) + 5);
        }

    }
}
