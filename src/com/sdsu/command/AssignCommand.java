package com.sdsu.command;

/*
 * This class represents assignment commands like : #Ps = 3, 4   #variable = 10
 */

import com.sdsu.Turtle.Context;
import com.sdsu.Turtle.Turtle;

public class AssignCommand implements Command {
  private String name;
  private Object value;
  private Context context;
  private Object previousValue;

  public AssignCommand(String name, Object value, Context context) {
    this.name = name;
    this.value = value;
    this.context = context;
  }

  @Override
  public Turtle execute() {
    previousValue = context.getValue(name);
    context.putValue(name, value);
    return null;
  }

  @Override
  public Turtle undo() {
    if (previousValue == null) {
      context.removeValue(name);
    } else {
      context.putValue(name, previousValue);
    }
    return null;
  }
}