import java.util.Random;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        // creating variables
        int sizeBoard = 5, countMonster = sizeBoard * sizeBoard - sizeBoard - 1;
        String leftBlock = " | ", rightBlock = " |", wall = " + —— + —— + —— + —— + —— + ";
        String castle = "\uD83C\uDFF0";
        String[][] board = new String[sizeBoard][sizeBoard];
        int count_monster = sizeBoard * sizeBoard - sizeBoard - 5;

        Person person = new Person(sizeBoard);
        Monster monster = new Monster(sizeBoard);
        BigMonster bigMonster = new BigMonster(sizeBoard);

        // fill the array with empty cells
        for (int y = 1; y <= sizeBoard; y++) {
            for (int x = 1; x <= sizeBoard; x++) {
                board[y - 1][x - 1] = "  ";
            }
        }

        // add monsters to random cells
        Monster[] arrMonster = new Monster[count_monster + 1];
        int count = 0;
        while (count < count_monster) {
            Monster test;
            int chance = random.nextInt(100);
            if (chance < 30) {
                test = new BigMonster(sizeBoard);
            } else {
                test = new Monster(sizeBoard);
            }
            if (board[test.getY()][test.getX()].equals("  ")) {
                board[test.getY()][test.getX()] = test.getImage();
                arrMonster[count] = test;
                count++;
            }
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
                person.setLife(scanner.nextInt());
                System.out.println("Количество жизней: " + person.getLife() + "\n");

                // game loop
                while ((person.getLife() > 0) && !(castleX == person.getX() && castleY == person.getY())) {
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
                        System.out.println("Поздравляю!!! Вы прошли игру.");
                        break;

                    } else {
                        Monster foundMonster = null;
                        for (int i = 0; i < arrMonster.length; i++) {
                            if (arrMonster[i] != null && arrMonster[i].getX() == x-1 && arrMonster[i].getY() == y-1) {
                                foundMonster = arrMonster[i];
                                break;
                            }
                        }

                        if (foundMonster != null) {
                            System.out.println("Чтобы победить монстра необходимо решить задачу.");
                            if (foundMonster.taskMonster(difficultGame)) {
                                board[person.getY() - 1][person.getX() - 1] = "  ";
                                person.move(x, y);
                                board[person.getY() - 1][person.getX() - 1] = person.getImage();
                                board[y - 1][x - 1] = "  ";
                            } else {
                                person.subtractLife();
                                person.setStep();
                            }
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

