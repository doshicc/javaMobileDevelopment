import java.util.Random;

public class Person {
    Random r = new Random();
    private int x, y;
    private String image = "\uD83D\uDE3D";
    private int live = 3;
    private int step = 0;

    public Person(int size) {
        y = size;
        int n = r.nextInt(size);
        x = n == 0 ? 1 : n;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getImage() {
        return image;
    }

    public int getLive() {
        return live;
    }

    public void setStep() {
        this.step++;
    }

    public void setLive(int live) {
        this.live = live;
    }

    void move(int x, int y) {
        this.x = x;
        this.y = y;
        step++;
        System.out.println("Это был ход под номером: " + step);
    }
    public boolean isMoveCorrect(int x, int y) {
        return Math.abs(x - this.x) + Math.abs(y - this.y) != 1;
    }
    void subtractLive() {
        if (live < 0)
            live = 0;
        else
            live--;
    }
}
