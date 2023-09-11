package com.wang.game.map;

public class Route {
    private int x;
    private int y;

    public Route(){}
    public Route(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Route{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
