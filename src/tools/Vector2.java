package tools;

public class Vector2 {
    public float x;
    public float y;

    public Vector2() {
        this.x = 0.0f;
        this.y = 0.0f;
    }

    public Vector2(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public boolean equals(Vector2 other) {
        return (this.x == other.x && this.y == other.y);
    }

    public void normalize() {
        float len = (float) Math.sqrt((this.x * this.x) + (this.y * this.y));
        this.x /= len;
        this.y /= len;
    }

    public void multiply(int nr) {
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