package tools;

public class Vector2 {
    public double x;
    public double y;

    /**
     * Empty constructor
     */
    public Vector2() {
        this.x = 0;
        this.y = 0;
    }

    /**
     * Contstructor for setting x and y
     * 
     * @param x
     * @param y
     */
    public Vector2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Print x and y for debug
     */
    public void print() {
        System.out.print("X: " + this.x + " Y: " + this.y + "\n");
    }

    /**
     * @param other
     * @return true if two vectors are the same
     */
    public boolean equals(Vector2 other) {
        return (this.x == other.x && this.y == other.y);
    }

    /**
     * Normalizes vector
     */
    public void normalize() {
        double len = (double) Math.sqrt((this.x * this.x) + (this.y * this.y));
        this.x /= len;
        this.y /= len;
    }

    /**
     * Multipies vector by a value
     * 
     * @param nr
     */
    public void multiply(double nr) {
        this.x *= nr;
        this.y *= nr;
    }

    /**
     * Adds two vectors
     * 
     * @param other
     */
    public void add(Vector2 other) {
        this.x += other.x;
        this.y += other.y;
    }

    /**
     * @return true if vector is zero (both values are 0)
     */
    public boolean isNull() {
        if (this.x == 0 && this.y == 0)
            return true;
        return false;
    }

}