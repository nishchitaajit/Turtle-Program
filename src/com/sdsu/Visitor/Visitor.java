package com.sdsu.Visitor;

/*
 * This interface visits the different Expressions.
 */

import com.sdsu.Expression.*;
import com.sdsu.Turtle.Point;

public interface Visitor {
  void visitAssignment(Assignment assignment);
  void visitConstant(Constant constant);
  void visitVariable(Variable variable);
  void visitMove(Move move);
  void visitTurn(Turn turn);
  void visitRepeat(Repeat repeat);
  void visitDistanceTo(DistanceTo distanceTo);
  void visitBearingTo(BearingTo bearingTo);
  void visitPoint(Point point);
}