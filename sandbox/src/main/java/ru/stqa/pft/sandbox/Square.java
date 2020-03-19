package ru.stqa.pft.sandbox;

public class Square {
  public double l;
  public Square(double l){
    this.l = l;
  }

  public double square() {
    return this.l * this.l;
  }
}