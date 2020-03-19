package ru.stqa.pft.sandbox;

public class Point {
  public double x;
  public double y;

  public Point (double x, double y){
    this.x = x;
    this.y = y;
  }
  public double distance(Point p) {
    Square l1 = new Square(p.x-this.x);
    Square l2 = new Square(p.y-this.y);
    return Math.sqrt(l1.square() + l2.square());
  }

}
