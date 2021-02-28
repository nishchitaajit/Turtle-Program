package com.sdsu.Expression;

/*
 * This class interprets assignment expressions: #var = 15,
 * It returns the value of the constant.
 */

import com.sdsu.Turtle.Context;
import com.sdsu.Visitor.Visitor;

public class Assignment implements IExpression {

  private String name;
  private IExpression value;

  public Assignment(String name, IExpression value) {
    this.name = name;
    this.value = value;
  }

  public String getName() {
    return name;
  }

  @Override
  public Object interpret(Context context) {
    Object objectValue = value.interpret(context);
    context.putValue(name, objectValue);
    return objectValue;
  }

  @Override
  public void accept(Visitor visitor) {
    visitor.visitAssignment(this);
  }
}