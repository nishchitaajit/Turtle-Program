package com.sdsu.Turtle;

/*
 * Point class to represent the point type values.
 */

import com.sdsu.Expression.IExpression;
import com.sdsu.Visitor.Visitor;

public class Point implements IExpression {

  private double x;
  private double y;

  public Point() {
    x = y = 0.0;
  }

  public Point(double x, double y) {
    this.x = x;
    this.y = y;
  }

  public double getX() {
    return x;
  }

  public double getY() {
    return y;
  }

  public void update(double deltaX, double deltaY) {
    this.x = Math.round(x + deltaX);
    this.y = Math.round(y + deltaY);
  }

  @Override
  public Object interpret(Context context) {
    return new Point(x,y);
  }

  @Override
  public void accept(Visitor visitor) {
    visitor.visitPoint(this);
  }
}