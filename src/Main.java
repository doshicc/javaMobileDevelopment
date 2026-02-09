import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int step = 0, personX, personY, personLive = 5, sizeBoard = 5;
        String monster = "\uD83D\uDC7B", person = "\uD83D\uDE3D";
        String gamingField = "+ —— + —— + —— +\n"
                + "|    |    |    |\n"
                + "+ —— + —— + —— +\n"
                + "|    | " + monster + " |    |\n"
                + "+ —— + —— + —— +\n"
                + "| " + person + " |    |    |\n"
                + "+ —— + —— + —— +";
        System.out.println("Привет! Ты готов начать игру? (ДА или НЕТ)");
        String answer = scanner.nextLine();
        System.out.println("Ваш ответ:\t" + answer);
        System.out.println("Количество жизней: " + personLive);
    }
}

