package com.sdsu.Expression;

/*
 * This class interprets variables in expression : move #var
 * Interpreting this returns the value of the variable.
 */

import com.sdsu.Turtle.Context;
import com.sdsu.Visitor.Visitor;

public class Variable implements IExpression {

  private String name;

  public Variable(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  @Override
  public Object interpret(Context context) {
    return context.getValue(name);
  }

  @Override
  public void accept(Visitor visitor) {
    visitor.visitVariable(this);
  }
}