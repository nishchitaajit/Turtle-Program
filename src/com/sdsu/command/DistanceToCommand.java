package com.sdsu.command;

/*
 * This class interprets distanceTo command:
 * distance Point
 * It returns the distance of turtle from its current location to the newLocation.
 */

import com.sdsu.Turtle.Context;
import com.sdsu.Turtle.Point;
import com.sdsu.Turtle.Turtle;

public class DistanceToCommand implements Command {

  private final Point newLocation;
  private final Turtle turtle;
  private double result;
  private Context context;
  private boolean isExecuted = false;

  public DistanceToCommand(Turtle t, Point newLocation, Context context) {
    this.newLocation = newLocation;
    this.turtle = t;
    this.context = context;
  }

  @Override
  public Turtle execute() {
    result = turtle.distanceTo(newLocation);
    isExecuted = true;
    return turtle;
  }

  @Override
  public Turtle undo() {
    if (isExecuted) {
      context.putValue("distanceTo", result);
    }
    return null;
  }
}