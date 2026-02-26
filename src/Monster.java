import java.util.Random;
import java.util.Scanner;

public class Monster {
    private final String image = "\uD83D\uDC7B";
    public Monster() {

    }

    public String getImage() {
        return image;
    }

    // adding a method for generating tasks
    static boolean taskMonster(int difficultGame) {
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);

        int a = random.nextInt(100 * difficultGame);
        int b = random.nextInt(100 * difficultGame);
        int trueAnswer = a + b;

        System.out.println("Для победы над монстром решите пример: " + a + " + " + b + " = ?");
        int userAnswer = scanner.nextInt();

        if (userAnswer == trueAnswer) {
            System.out.println("Поздравляю! Вы победили монстра.");
            return true;
        } else {
            System.out.println("Увы, ваш ответ неправильный. Вы теряете одну жизнь.");
            return false;
        }
    }
}
