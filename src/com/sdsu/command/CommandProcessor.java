package com.sdsu.command;

/*
 * This class performs undo-redo of commands by using two stacks.
 */

import java.util.Stack;

public class CommandProcessor {

  private static CommandProcessor instanceOf;
  private Stack<Command> undoStack;
  private Stack<Command> redoStack;

  private CommandProcessor() {
    undoStack = new Stack<>();
    redoStack = new Stack<>();
  }

  public static CommandProcessor getInstance() {
    if (instanceOf == null) {
      instanceOf = new CommandProcessor();
    }
    return instanceOf;
  }

  public void execute(Command command) {
    undoStack.push(command);
    command.execute();
  }

  public void undo() {
    if (undoStack.size() > 0) {
      Command command = undoStack.pop();
      redoStack.push(command);
      command.undo();
    }
  }

  public void redo() {
    if (redoStack.size() > 0) {
      Command command = redoStack.pop();
      undoStack.push(command);
      command.execute();
    }
  }
}