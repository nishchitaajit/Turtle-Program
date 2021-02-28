package com.sdsu.command;

/*
 * Class to represent move commands like: move 10
 */

import com.sdsu.Turtle.Turtle;

public class MoveCommand implements Command {

  private final int distance;
  private final Turtle turtle;
  private boolean isExecuted = false;

  public MoveCommand(Turtle t, int distance) {
    this.distance = distance;
    this.turtle = t;
  }

  @Override
  public Turtle execute() {
    turtle.move(distance);
    isExecuted = true;
    return turtle;
  }

  @Override
  public Turtle undo() {
    if (isExecuted) {
      turtle.move(-(distance));
      return turtle;
    } else {
      throw new RuntimeException("Execution of undo can't be completed.");
    }
  }
}