package me.crumbs.utils;

public class Vec2 {
    public int[] v;

    public Vec2(int x, int y) {
        v = new int[] {x, y};
    }

    public int x() { return v[0]; }
    public int y() { return v[1]; }

    public void setX(int x) { v[0] = x; }
    public void setY(int y) { v[1] = y; }
}
