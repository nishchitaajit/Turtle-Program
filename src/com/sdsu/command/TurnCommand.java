package com.sdsu.command;

/*
 * Class to represent move commands like: turn 90
 */

import com.sdsu.Turtle.Turtle;

public class TurnCommand implements Command {

  private int degree;
  private Turtle turtle;
  private boolean isExecuted = false;

  public TurnCommand(Turtle t, int degree) {
    this.degree = degree;
    this.turtle = t;
  }

  @Override
  public Turtle execute() {
    turtle.turn(degree);
    isExecuted = true;
    return turtle;
  }

  @Override
  public Turtle undo() {
    if (isExecuted) {
      turtle.turn(-(degree));
      return turtle;
    } else {
      throw new RuntimeException("Execution of this command can't be completed.");
    }
  }
}