package com.sdsu.command;

/*
 * Interface to represent commands in the abstract syntax tree.
 */

import com.sdsu.Turtle.Turtle;

public interface Command {

  Turtle execute(); // Redo is same as execute

  Turtle undo();
}