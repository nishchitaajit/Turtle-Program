package com.sdsu.Expression;

/*
 * This class interprets the constant value. Used as terminal nodes.
 * It returns constant value.
 */

import com.sdsu.Turtle.Context;
import com.sdsu.Visitor.Visitor;

public class Constant implements IExpression {

  private int value;

  public Constant(int value) {
    this.value = value;
  }

  @Override
  public Object interpret(Context context) {
    return value;
  }

  @Override
  public void accept(Visitor visitor) {
    visitor.visitConstant(this);
  }
}