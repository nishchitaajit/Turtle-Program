package com.sdsu.command;

/*
 * This class interprets bearingTo command:
 * bearingTo Point
 * It returns the angle of the turtle from its current location to the newLocation.
 */

import com.sdsu.Turtle.*;

public class BearingToCommand implements Command {

  private Point newLocation;
  private Turtle turtle;
  private Context context;
  private boolean isExecuted = false;
  private double result;

  public BearingToCommand(Turtle t, Point newLocation, Context context) {
    this.newLocation = newLocation;
    this.turtle = t;
    this.context = context;
  }

  @Override
  public Turtle execute() {
    result = turtle.bearingTo(newLocation);
    isExecuted = true;
    return turtle;
  }

  @Override
  public Turtle undo() {
    if (isExecuted) {
      context.putValue("bearingTo", result);
    }
    return null;
  }
}