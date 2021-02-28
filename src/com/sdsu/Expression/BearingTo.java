package com.sdsu.Expression;

/*
 * This class interprets the bearingTo expressions : bearingTo #Ps.
 * It returns the value of the angle between the turtle's current location and the given location.
 */

import com.sdsu.Turtle.Context;
import com.sdsu.Turtle.Point;
import com.sdsu.Visitor.Visitor;

public class BearingTo implements IExpression {
  private IExpression newPoint;

  public BearingTo(IExpression newPoint) { this.newPoint = newPoint; }

  public IExpression getNewPoint() { return newPoint; }

  @Override
  public Object interpret(Context context) {
    Point point = (Point) newPoint.interpret(context);
    double result = context.getTurtle().bearingTo(point);
    context.putValue("bearingTo",result);
    return result;
  }

  @Override
  public void accept(Visitor visitor) {
    visitor.visitBearingTo(this);
  }
}