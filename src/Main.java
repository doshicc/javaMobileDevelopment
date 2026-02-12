import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int step = 0, personX = 1, personY = 3, sizeBoard = 3;
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

        switch (answer){
            case "ДА":
                System.out.println("Выбери сложность игры(от 1 до 5):");
                int difficultGame = scanner.nextInt();
                System.out.println("Выбранная сложность:\t" + difficultGame);

                System.out.println("Сколько жизней будет у персонажа?");
                int personLive = scanner.nextInt();
                System.out.println(gamingField);
                System.out.println("Количество жизней:\t" + personLive + "\n");

                System.out.println("Введите куда будет ходить персонаж(ход возможен только по вертикали и горизонтали на одну клетку;" +
                        "\nКоординаты персонажа - (x: " + personX + ", y: " + personY + "))");

                int x = scanner.nextInt();
                int y = scanner.nextInt();

                System.out.println(x + ", " + y);
                if (x != personX && y != personY) {
                    System.out.println("Неккоректный ход");
                } else if (Math.abs(x - personX) == 1 || Math.abs(y - personY) == 1) {
                    personX = x;
                    personY = y;
                    step++;
                    System.out.println("Ход корректный; Новые координаты: " + personX + ", " + personY +
                            "\nХод номер: " + step);
                } else {
                    System.out.println("Координаты не изменены");
                }
            case "НЕТ":
                System.out.println("Жаль, приходите еще.");
                break;
            default:
                System.out.println("Данные введены некорректно");
        }
    }
}

