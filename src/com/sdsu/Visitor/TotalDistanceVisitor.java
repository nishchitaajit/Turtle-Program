package com.sdsu.Visitor;

/*
 * Visitor calculates the total distance travelled by the turtle.
 * As all commands don't move the turtle, only some commands are considered.
 */

import com.sdsu.Turtle.Context;
import com.sdsu.Expression.*;
import com.sdsu.Turtle.Point;

public class TotalDistanceVisitor implements Visitor {

  private double distance;
  private Context context;

  public TotalDistanceVisitor(Context context) {
    this.context = context;
  }

  public double getDistance() {
    return distance;
  }

  @Override
  public void visitAssignment(Assignment assignment) {
    assignment.interpret(context);
  }

  /* Only move command will result in constant value. */
  @Override
  public void visitConstant(Constant constant) {
    distance = distance + (int) constant.interpret(context);
  }

  /* Only move command will result in variable value. */
  @Override
  public void visitVariable(Variable variable) {
    distance = distance + (double) variable.interpret(context);
  }

  /* When the turtle moves, value of the move is visited and added to distance. */
  @Override
  public void visitMove(Move move) {
    move.getDistance().accept(this);
  }

  @Override
  public void visitTurn(Turn turn) {
    // Don't do anything.
  }

  @Override
  public void visitRepeat(Repeat repeat) {
    IExpression numberOfTimes = repeat.getNumberOfTimes();
    double numberOfRepeat = (int) numberOfTimes.interpret(context);

    for (int i=0; i<numberOfRepeat; i++) {
      for (IExpression expression: repeat.getExpression()) {
        expression.accept(this);
      }
    }
  }

  @Override
  public void visitDistanceTo(DistanceTo distanceTo) {
    // Don't do anything.
  }

  @Override
  public void visitBearingTo(BearingTo bearingTo) {
    // Don't do anything.
  }

  @Override
  public void visitPoint(Point point) {
    // Don't do anything
  }
}