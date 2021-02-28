package com.sdsu.Expression;

import com.sdsu.Turtle.Context;
import com.sdsu.Visitor.Visitor;

/*
 * Interface that represents the nodes of the abstract syntax tree.
 */

public interface IExpression {

  Object interpret(Context context);

  void accept(Visitor visitor);
}