package com.sdsu.Expression;

import com.sdsu.Turtle.Context;
import com.sdsu.Visitor.Visitor;
import java.util.ArrayList;
import java.util.List;

/*
 * This class interprets the repeat expression :
 * repeat 4
 *      move 15
 *      turn 90
 * end
 * Interpreting this returns the total sum of the expressions that
 * is multiplied with number of iterations.
 */

public class Repeat implements IExpression {

  private IExpression numberOfTimesRepeat;
  private List<IExpression> expression = new ArrayList<>();

  public Repeat(IExpression numberOfTimes) {
    this.numberOfTimesRepeat = numberOfTimes;
  }

  public IExpression getNumberOfTimes() {
    return numberOfTimesRepeat;
  }

  public List<IExpression> getExpression() {
    return expression;
  }

  public void addExpression(IExpression add) {
    expression.add(add);
  }

  @Override
  public Object interpret(Context context) {
    int repeat = 0;
    Object numberOfTimes = numberOfTimesRepeat.interpret(context);
    double count = 0.0;
    if (numberOfTimes instanceof Number)
      count = ((Number) numberOfTimes).doubleValue();

    for (int i = 0; i < count; i++) {
      for (IExpression list : expression) {
        repeat = repeat + (int) list.interpret(context);
      }
    }
    return numberOfTimes;
  }

  @Override
  public void accept(Visitor visitor) {
    visitor.visitRepeat(this);
  }
}