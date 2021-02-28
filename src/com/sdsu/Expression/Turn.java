package com.sdsu.Expression;

/*
 * This class represents turn expression : turn 90 | #variable
 * Interpreting this returns the degree value the turtle has to turn.
 */

import com.sdsu.Turtle.Context;
import com.sdsu.Visitor.Visitor;
import java.text.DecimalFormat;

public class Turn implements IExpression {

  private IExpression degrees;

  public Turn(IExpression degrees) {
    this.degrees = degrees;
  }

  @Override
  public Object interpret(Context context) {
    Object turnDegree = degrees.interpret(context);
    DecimalFormat decimalFormat = new DecimalFormat("##");
    String resultString = decimalFormat.format(turnDegree);
    int value = Integer.parseInt(resultString);
    context.getTurtle().turn(value);

    return turnDegree;
  }

  @Override
  public void accept(Visitor visitor) {
    visitor.visitTurn(this);
  }
}