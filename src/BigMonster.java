public class BigMonster extends Monster {

    private String image = "\uD83D\uDC79";

    private static final String ROCK = "камень";
    private static final String SCISSORS = "ножницы";
    private static final String PAPER = "бумага";

    public BigMonster(int sizeBoard) {
        super(sizeBoard);
    }

    @Override
    public String getImage() {
        return image;
    }

    @Override
    public boolean taskMonster(int difficultGame) {
        System.out.println("\uD83D\uDC79 Чтобы победить большого монстра сыграй с ним в камень-ножницы-бумага! \uD83D\uDC79");
        System.out.println("Победи в двух из трех раундов!");

        int playerWins = 0;
        int monsterWins = 0;

        for (int round = 1; round <= 3; round++) {
            System.out.println("Раунд: " + round);

            String monsterChoice = getRandomChoice();
            String playerChoice = getPlayerChoice();

            System.out.println("\uD83D\uDC79 Монстр выбрал: " + monsterChoice);
            System.out.println("\uD83D\uDE3D Вы выбрали: " + playerChoice);

            String result = determineWinner(playerChoice, monsterChoice);

            if (result.equals("player")) {
                System.out.println("\uD83C\uDF89 Вы выиграли раунд!");
                playerWins++;
            } else if (result.equals("monster")) {
                System.out.println("\uD83D\uDE22 Увы, монстр выиграл раунд!");
                monsterWins++;
            } else {
                System.out.println("\uD83E\uDD14 Ничья в раунде!");
            }

            System.out.println("\uD83D\uDCA5 Счет: Игрок " + playerWins + " : " + monsterWins + " Монстр \uD83D\uDCA5");

            if (playerWins >= 2) {
                System.out.println("\uD83C\uDF89 \uD83C\uDF89 \uD83C\uDF89 Поздравляю, ты победил!!! \uD83C\uDF89 \uD83C\uDF89 \uD83C\uDF89");
                return true;
            }
            if (monsterWins >= 2) {
                System.out.println("\uD83D\uDE22 \uD83D\uDE22 \uD83D\uDE22 К сожалению, монстр победил. \uD83D\uDE22 \uD83D\uDE22 \uD83D\uDE22");
                return false;
            }
        }

        System.out.println("\uD83E\uDD14 Ничья по раундам! Назначаем решающий раунд. \uD83E\uDD14");

        String monsterChoice = getRandomChoice();
        String playerChoice = getPlayerChoice();

        System.out.println("\uD83D\uDC79 Монстр выбрал: " + monsterChoice);
        System.out.println("\uD83D\uDE3D Вы выбрали: " + playerChoice);

        String result = determineWinner(playerChoice, monsterChoice);

        if (result.equals("player")) {
            System.out.println("\uD83C\uDF89 \uD83C\uDF89 \uD83C\uDF89 Ты победил монстра! \uD83C\uDF89 \uD83C\uDF89 \uD83C\uDF89");
            return true;
        } else if (result.equals("monster")) {
            System.out.println("\uD83D\uDE22 \uD83D\uDE22 \uD83D\uDE22 Монстр победил, увы! \uD83D\uDE22 \uD83D\uDE22 \uD83D\uDE22");
            return false;
        } else {
            System.out.println("\uD83E\uDD14 Монстр устал от бесконечных ничьих и отпускает тебя. \uD83E\uDD14");
            return true;
        }
    }

    private String getRandomChoice() {
        String[] choices = {ROCK, SCISSORS, PAPER};
        return choices[(int)(Math.random() * 3)];
    }

    private String getPlayerChoice() {
        System.out.print("Ваш выбор (1 - камень, 2 - ножницы, 3 - бумага): ");

        while (true) {
            try {
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1: return ROCK;
                    case 2: return SCISSORS;
                    case 3: return PAPER;
                    default:
                        System.out.print("Неверный ввод! Введите 1, 2 или 3: ");
                }
            } catch (Exception e) {
                System.out.print("Ошибка! Введите число 1, 2 или 3: ");
                scanner.next();
            }
        }
    }

    private String determineWinner(String player, String monster) {
        if (player.equals(monster)) {
            return "tie";
        }

        if (player.equals(ROCK)) {
            return monster.equals(SCISSORS) ? "player" : "monster";
        } else if (player.equals(PAPER)) {
            return monster.equals(ROCK) ? "player" : "monster";
        } else if (player.equals(SCISSORS)) {
            return monster.equals(PAPER) ? "player" : "monster";
        }

        return "monster";
    }
}