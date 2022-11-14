package tools;

public class Vector2 {
    public double x;
    public double y;

    public Vector2() {
        this.x = 0;
        this.y = 0;
    }

    public Vector2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void print() {
        System.out.print("X: " + this.x + " Y: " + this.y + "\n");
    }

    public boolean equals(Vector2 other) {
        return (this.x == other.x && this.y == other.y);
    }

    public void normalize() {
        double len = (double) Math.sqrt((this.x * this.x) + (this.y * this.y));
        this.x /= len;
        this.y /= len;
    }

    public void multiply(double nr) {
        this.x *= nr;
        this.y *= nr;
    }

    public void add(Vector2 other) {
        this.x += other.x;
        this.y += other.y;
    }

    public boolean isNull() {
        if (this.x == 0 && this.y == 0)
            return true;
        return false;
    }

}