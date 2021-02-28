package com.sdsu.Expression;

import com.sdsu.Turtle.Context;
import com.sdsu.Turtle.Point;
import com.sdsu.Visitor.Visitor;

/*
 * This class interprets the distanceTo expressions : distanceTo #Ps.
 * It returns the value of the distance between the turtle's
 * current location and the given location.
 */

public class DistanceTo implements IExpression {

  private IExpression newPoint;

  public DistanceTo(IExpression newPoint) {
    this.newPoint = newPoint;
  }

  public IExpression getNewPoint() {
    return newPoint;
  }

  @Override
  public Object interpret(Context context) {
    Point point = (Point) newPoint.interpret(context);
    double result = Math.round(context.getTurtle().distanceTo(point));
    int moveDistance = (int) result;
    context.putValue("distanceTo", moveDistance);
    return result;
  }

  @Override
  public void accept(Visitor visitor) {
    visitor.visitDistanceTo(this);
  }
}