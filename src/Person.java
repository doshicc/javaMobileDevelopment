import java.util.Random;

public class Person {
    Random r = new Random();
    private int x, y;
    private String image = "\uD83D\uDE3D";
    private int life = 3;
    private int step = 0;

    public Person(int size) {
        y = size;
        x = r.nextInt(size);
    }

    // геттеры и сеттеры
    public int getX() {  return x; }
    public int getY() { return y; }
    public String getImage() { return image; }
    public int getLife() { return life; }
    public void setStep() { this.step++; }
    public void setLife(int life) { this.life = life; }

    void move(int x, int y) { // передвигаем персонажа
        this.x = x;
        this.y = y;
        step++;
        System.out.println("Это был ход под номером: " + step);
    }

    public boolean isMoveCorrect(int x, int y) { // проверяем корректность хода
        return Math.abs(x - this.x) + Math.abs(y - this.y) != 1;
    }

    void subtractLife() { // вычитаем жизнь
        if (life > 0) {
            life--;
        }
    }
}