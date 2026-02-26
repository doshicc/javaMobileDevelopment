import java.util.Random;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        Monster monster = new Monster();

        // creating variables
        int sizeBoard = 5, countMonster = sizeBoard * sizeBoard - sizeBoard - 1;
        String leftBlock = " | ", rightBlock = " |", wall = " + —— + —— + —— + —— + —— + ";
        String castle = "\uD83C\uDFF0";
        String[][] board = new String[sizeBoard][sizeBoard];

        Person person = new Person(sizeBoard);

        // fill the array with empty cells
        for (int y = 1; y <= sizeBoard; y++) {
            for (int x = 1; x <= sizeBoard; x++) {
                board[y - 1][x - 1] = "  ";
            }
        }

        // add monsters to random cells
        for (int i = 0; i <= countMonster; i++) {
            board[random.nextInt(sizeBoard - 1)][random.nextInt(sizeBoard)] = monster.getImage();
        }

        int castleY = 1;
        int castleX = 1 + random.nextInt(sizeBoard);

        System.out.println("Привет! Вы готовы начать игру? (ДА или НЕТ)");
        String answer = scanner.nextLine();

        // answer processing logic
        switch (answer){
            case "ДА":
                // choice of difficulty and number of lives
                System.out.println("Выбери сложность игры (от 1 до 5):");
                int difficultGame = scanner.nextInt();
                while (difficultGame < 1 || difficultGame > 5) {
                    System.out.println("Введите пожалуйста корректную сложность.");
                    difficultGame = scanner.nextInt();
                }
                System.out.println("Выбранная сложность: " + difficultGame);

                System.out.println("Сколько жизней будет у персонажа?");
                person.setLive(scanner.nextInt());
                System.out.println("Количество жизней: " + person.getLive() + "\n");

                // game loop
                while ((person.getLive() > 0) && !(castleX == person.getX() && castleY == person.getY())) {
                    // write down the coordinates of the person and the castle
                    board[castleY - 1][castleX - 1] = castle;
                    board[person.getY() - 1][person.getX() - 1] = person.getImage();

                    // field rendering
                    for (int y = 1; y <= sizeBoard; y++) {
                        System.out.println(wall);
                        for (int x = 1; x <= sizeBoard; x++) {
                            System.out.print(leftBlock);
                            System.out.print(board[y - 1][x - 1]);
                        }
                        System.out.println(rightBlock);
                    }
                    System.out.println(wall);

                    // logic for checking the correctness of a move
                    System.out.println("Введите куда будет ходить персонаж (ход возможен только по вертикали и" +
                            " горизонтали на одну клетку):" + "\nКоординаты персонажа: x: " + person.getX() + ", y: " +
                            person.getY());

                    int x = scanner.nextInt();
                    int y = scanner.nextInt();

                    while (person.isMoveCorrect(x, y)) {
                        System.out.println("Неккоректный ход. Передвигаться можно тольбко на одну клетку по горизонтали" +
                                " или вертикали. Введите новые координаты.");
                        x = scanner.nextInt();
                        y = scanner.nextInt();
                    }

                    // changing the coordinates of the character
                    System.out.println("Ваш ход: " + x + ", " + y);

                    // check which cell we are in
                    if (board[y - 1][x - 1].equals("  ")) {
                        board[person.getY() - 1][person.getX() - 1] = "  ";
                        person.move(x, y);
                        board[person.getY() - 1][person.getX() - 1] = person.getImage();
                        System.out.println("Ваш ход корректный! Вот новые координаты: " + person.getX() + ", " +
                                person.getY());

                    } else if (board[y - 1][x - 1].equals(castle)) {
                        System.out.println("Поздравляю!!! Вы проишли игру.");
                        break;

                    } else if (board[y - 1][x - 1].equals(monster.getImage())) {
                        System.out.println("Чтобы победить монстра необходимо решить задачу.");
                        if (Monster.taskMonster(difficultGame)) {
                            board[person.getY() - 1][person.getX() - 1] = "  ";
                            person.move(x, y);
                            board[person.getY() - 1][person.getX() - 1] = person.getImage();
                        } else {
                            person.subtractLive();
                            person.setStep();
                        }
                    }
                }
                break;

            case "НЕТ":
                System.out.println("Жаль, приходите еще.");
                break;
            default:
                System.out.println("Данные введены некорректно");
        }
    }
}

