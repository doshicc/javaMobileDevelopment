import java.util.Random;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        // creating variables
        int step = 0, personX = 1, personY = 3, sizeBoard = 5;
        int countMonster = sizeBoard * sizeBoard - sizeBoard - 1;
        String leftBlock = " | ";
        String rightBlock = " |";
        String wall = " + —— + —— + —— + —— + —— + ";
        String monster = "\uD83D\uDC7B";
        String person = "\uD83D\uDE3D";
        String castle = "\uD83C\uDFF0";
        String[][] board = new String[sizeBoard][sizeBoard];

        // fill the array with empty cells
        for (int y = 1; y <= sizeBoard; y++) {
            for (int x = 1; x <= sizeBoard; x++) {
                board[y - 1][x - 1] = "  ";
            }
        }

        // add monsters to random cells
        for (int i = 0; i <= countMonster; i++) {
            board[random.nextInt(sizeBoard - 1)][random.nextInt(sizeBoard)] = monster;
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
                System.out.println("Выбранная сложность: " + difficultGame);

                System.out.println("Сколько жизней будет у персонажа?");
                int personLive = scanner.nextInt();
                System.out.println("Количество жизней: " + personLive + "\n");

                // game loop
                while ((personLive > 0) && !(castleX == personX && castleY == personY)) {
                    // write down the coordinates of the person and the castle
                    board[castleY - 1][castleX - 1] = castle;
                    board[personY - 1][personX - 1] = person;

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

                    // logic fot checking the correctness of a move
                    System.out.println("Введите куда будет ходить персонаж (ход возможен только по вертикали и" +
                            " горизонтали на одну клетку):" + "\nКоординаты персонажа: x: " + personX + ", y: " +
                            personY);

                    int x = scanner.nextInt();
                    int y = scanner.nextInt();

                    while (Math.abs(x - personX) + Math.abs(y - personY) != 1) {
                        System.out.println("Неккоректный ход. Передвигаться можно тольбко на одну клетку по горизонтали" +
                                " или вертикали. Введите новые координаты.");
                        x = scanner.nextInt();
                        y = scanner.nextInt();

                    }

                    System.out.println(x + ", " + y);
                    board[personY - 1][personX - 1] = "  ";
                    personX = x;
                    personY = y;
                    step++;

                    if (board[personY - 1][personX - 1].equals("  ")) {
                        board[personY - 1][personX - 1] = person;
                        System.out.println("Ваш ход корректный! Вот новые координаты: " + personX + ", " + personY +
                                "\nЭто бы ход под номером " + step);
                    } else if (board[personY - 1][personX - 1].equals(castle)) {
                        System.out.println("Поздравляю!!! Вы проишли игру.");
                        break;
                    } else if (board[personY - 1][personX - 1].equals(monster)) {
                        personLive -= 1;
                        System.out.println("Будьте внимательны! Вы потеряли одну жизнь.");
                        continue;
                    }

                        break;
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

