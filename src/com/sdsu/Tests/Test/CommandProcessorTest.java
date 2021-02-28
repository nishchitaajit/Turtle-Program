package com.sdsu.Tests.Test;

/*
 * Tests to validate undo-redo operations.
 */

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.sdsu.Turtle.*;
import com.sdsu.command.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CommandProcessorTest {

  private Turtle turtle;
  private List<Command> commandList;
  private Context context;
  private Point point;

  @BeforeEach
  void setUp() {
    turtle = new Turtle();
    commandList = new ArrayList<>();
    context = new Context();
    point = new Point(3, 4);

    commandList.add(new MoveCommand(turtle, 10));
    commandList.add(new TurnCommand(turtle, 90));
    commandList.add(new TurnCommand(turtle, -90));
    commandList.add(new AssignCommand("var", 10, context));
    commandList.add(new AssignCommand("s", point, context));
    commandList.add(new DistanceToCommand(turtle, point, context));
    commandList.add(new BearingToCommand(turtle, point, context));
  }

  @Test
  void execute() {
    CommandProcessor.getInstance().execute(commandList.get(0));
    assertEquals(10, turtle.location().getX());
    assertEquals(0, turtle.location().getY());
    assertEquals(0, turtle.direction());

    CommandProcessor.getInstance().execute(commandList.get(1));
    assertEquals(10, turtle.location().getX());
    assertEquals(0, turtle.location().getY());
    assertEquals(90, turtle.direction());

    CommandProcessor.getInstance().execute(commandList.get(2));
    assertEquals(10, turtle.location().getX());
    assertEquals(0, turtle.location().getY());
    assertEquals(0, turtle.direction());

    CommandProcessor.getInstance().execute(commandList.get(3));
    assertEquals(10, context.getValue("var"));

    CommandProcessor.getInstance().execute(commandList.get(4));
    assertEquals(point, context.getValue("s"));

    CommandProcessor.getInstance().execute(commandList.get(5));
    assertEquals(8.06, turtle.getDistanceTo());

    CommandProcessor.getInstance().execute(commandList.get(6));
    assertEquals(-29.74, turtle.getBearingTo());
  }

  @Test
  void undo() {

    for (Command command : commandList)
      CommandProcessor.getInstance().execute(command);

    CommandProcessor.getInstance().undo();
    CommandProcessor.getInstance().undo();

    // Adding another command to to check value of #var = distanceTo #s.
    commandList.add(new AssignCommand("var", context.getValue("distanceTo"), context));
    CommandProcessor.getInstance().execute(commandList.get(7));
    assertEquals(8.06, context.getValue("var"));

    // #var gets back the value 10 after undo.
    CommandProcessor.getInstance().undo();
    assertEquals(10, context.getValue("var"));

    CommandProcessor.getInstance().undo();
    CommandProcessor.getInstance().undo();
    assertEquals(10, turtle.location().getX());
    assertEquals(0, turtle.location().getY());
    assertEquals(0, turtle.direction());

    CommandProcessor.getInstance().undo();
    assertEquals(10, turtle.location().getX());
    assertEquals(0, turtle.location().getY());
    assertEquals(90, turtle.direction());

    CommandProcessor.getInstance().undo();
    assertEquals(10, turtle.location().getX());
    assertEquals(0, turtle.location().getY());
    assertEquals(0, turtle.direction());
  }

  @Test
  void redo() {
    for (Command command : commandList)
      CommandProcessor.getInstance().execute(command);

    CommandProcessor.getInstance().undo();
    CommandProcessor.getInstance().undo();
    CommandProcessor.getInstance().undo();
    CommandProcessor.getInstance().redo();
    assertEquals(10, turtle.location().getX());
    assertEquals(0, turtle.direction());
    assertEquals(point, context.getValue("s"));

    CommandProcessor.getInstance().undo();
    CommandProcessor.getInstance().undo();
    CommandProcessor.getInstance().undo();
    CommandProcessor.getInstance().undo();
    CommandProcessor.getInstance().redo();
    assertEquals(10, turtle.location().getX());
    assertEquals(90, turtle.direction());
  }
}