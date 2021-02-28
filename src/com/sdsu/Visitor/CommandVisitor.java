package com.sdsu.Visitor;

/*
 * This class creates a list of commands to be executed by
 * visiting all the lines present in the input text file.
 */

import com.sdsu.Turtle.Context;
import com.sdsu.Expression.*;
import com.sdsu.Turtle.Point;
import com.sdsu.command.*;
import java.util.ArrayList;
import java.util.List;

public class CommandVisitor implements Visitor {

  private Context context;
  private List<Command> commandList = new ArrayList<>();

  public CommandVisitor(Context context) {
    this.context = context;
  }

  @Override
  public void visitAssignment(Assignment assignment) {
    Command assignExp = new AssignCommand(assignment.getName(), assignment.interpret(context),
        context);
    commandList.add(assignExp);
  }

  @Override
  public void visitConstant(Constant constant) {
    // Don't do anything.
  }

  @Override
  public void visitVariable(Variable variable) {
    // Don't do anything.
  }

  @Override
  public void visitMove(Move move) {
    Command moveExp = new MoveCommand(context.getTurtle(), (Integer) move.interpret(context));
    commandList.add(moveExp);
  }

  @Override
  public void visitTurn(Turn turn) {
    Command turnExp = new TurnCommand(context.getTurtle(), (int) turn.interpret(context));
    commandList.add(turnExp);
  }

  /*
   * Visits all the expressions inside repeat.
   */
  @Override
  public void visitRepeat(Repeat repeat) {
    int numberOfRepeat = (int) repeat.getNumberOfTimes().interpret(context);

    for (int i = 0; i < numberOfRepeat; i++) {
      for (IExpression token : repeat.getExpression()) {
        token.accept(this);
      }
    }
  }

  @Override
  public void visitDistanceTo(DistanceTo distanceTo) {
    Point point = (Point) distanceTo.getNewPoint().interpret(context);
    Command distanceExp = new DistanceToCommand(context.getTurtle(), point, context);
    commandList.add(distanceExp);
  }

  @Override
  public void visitBearingTo(BearingTo bearingTo) {
    Point point = (Point) bearingTo.getNewPoint().interpret(context);
    Command bearingExp = new BearingToCommand(context.getTurtle(), point, context);
    commandList.add(bearingExp);
  }

  @Override
  public void visitPoint(Point point) {
    // Don't do anything
  }

  public List<Command> getCommandList() {
    return commandList;
  }
}