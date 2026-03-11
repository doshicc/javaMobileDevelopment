import java.util.Random;
import java.util.Scanner;


public class Main {
    // метод для отрисовки поля
    public static void renderBoard(String[][] board, int sizeBoard, String wall,
                                   String leftBlock, String rightBlock) {
        for (int y = 1; y <= sizeBoard; y++) {
            System.out.println(wall);
            for (int x = 1; x <= sizeBoard; x++) {
                System.out.print(leftBlock);
                System.out.print(board[y - 1][x - 1]);
            }
            System.out.println(rightBlock);
        }
        System.out.println(wall);
    }

    // метод для победы над монстром
    public static void handleVictory(String[][] board, Person person, int newX, int newY) {
        board[person.getY() - 1][person.getX() - 1] = "  ";
        person.move(newX, newY);
        board[person.getY() - 1][person.getX() - 1] = person.getImage();
        System.out.println("Вы победили монстра и заняли его клетку!");
    }

    // методя поражения
    public static void handleDefeat(Person person) {
        person.subtractLife();
        person.setStep();
        System.out.println("\uD83D\uDC94 Осталось жизней: " + person.getLife());
    }

    // метод для определения монстра
    public static Monster findMonster(Monster[] arrMonster, int x, int y) {
        for (int i = 0; i < arrMonster.length; i++) {
            if (arrMonster[i] != null &&
                    arrMonster[i].getX() == x - 1 &&
                    arrMonster[i].getY() == y - 1) {
                return arrMonster[i];
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        // создаем переменные
        int sizeBoard = 5, countMonster = sizeBoard * sizeBoard - sizeBoard - 1;
        String leftBlock = " | ", rightBlock = " |", wall = " + —— + —— + —— + —— + —— + ";
        String castle = "\uD83C\uDFF0";
        String[][] board = new String[sizeBoard][sizeBoard];
        int count_monster = sizeBoard * sizeBoard - sizeBoard - 5;

        Person person = new Person(sizeBoard);

        // заполняем пустотой ячейки поля
        for (int y = 1; y <= sizeBoard; y++) {
            for (int x = 1; x <= sizeBoard; x++) {
                board[y - 1][x - 1] = "  ";
            }
        }

        // добавляем монстров в рандомные ячейки массива монстра
        Monster[] arrMonster = new Monster[count_monster + 1];
        int count = 0;
        while (count < count_monster) {
            Monster test;
            int chance = random.nextInt(100);
            if (chance < 30) {  // с шансом 30 процентов помещаем большого монстра
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

        // обработка ответа
        switch (answer){
            case "ДА":
                // выбор сложности и количества жизней
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

                // цикл игры
                while ((person.getLife() > 0) && !(castleX == person.getX() && castleY == person.getY())) {
                    // записываем координаты персонажа и замка
                    board[castleY - 1][castleX - 1] = castle;
                    board[person.getY() - 1][person.getX() - 1] = person.getImage();

                    renderBoard(board, sizeBoard, wall, leftBlock, rightBlock);

                    // проверка корректности хода игрока
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

                    System.out.println("Ваш ход: " + x + ", " + y);

                    // проверяем в какой ячейке мы оказались
                    if (board[y - 1][x - 1].equals("  ")) { // если пустая ячейка, обновляем координаты и поле
                        board[person.getY() - 1][person.getX() - 1] = "  ";
                        person.move(x, y);
                        board[person.getY() - 1][person.getX() - 1] = person.getImage();
                        System.out.println("Ваш ход корректный! Вот новые координаты: " + person.getX() + ", " +
                                person.getY());

                    } else if (board[y - 1][x - 1].equals(castle)) { // если на замок, то конец игры
                        System.out.println("Поздравляю!!! Вы прошли игру.");
                        break;

                    } else { // если не пустая и не замок, то это точно монстр
                        Monster foundMonster = findMonster(arrMonster, x, y);
                        if (foundMonster != null) {
                            System.out.println("Чтобы победить монстра необходимо решить задачу.");
                            if (foundMonster.taskMonster(difficultGame)) {
                                handleVictory(board, person, x, y);
                            } else {
                                handleDefeat(person);
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

