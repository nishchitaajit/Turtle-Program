package com.sdsu.Expression;

/*
 * This class interprets the move expressions : move 15 | #variable
 * It returns the value of the distance moved.
 */

import com.sdsu.Turtle.Context;
import com.sdsu.Visitor.Visitor;
import java.text.DecimalFormat;

public class Move implements IExpression {

  private IExpression distance;

  public Move(IExpression distance) {
    this.distance = distance;
  }

  public IExpression getDistance() {
    return distance;
  }

  @Override
  public Object interpret(Context context) {
    Object moveValue = distance.interpret(context);
    DecimalFormat decimalFormat = new DecimalFormat("##");
    String resultString = decimalFormat.format(moveValue);
    int value = Integer.parseInt(resultString);
    context.getTurtle().move(value);

    return moveValue;
  }

  @Override
  public void accept(Visitor visitor) {
    visitor.visitMove(this);
  }
}