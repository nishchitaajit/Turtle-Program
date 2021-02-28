package com.sdsu.Tests.Test;

/*
 * Test to validate CommandVisitor class.
 * Checks if the CommandVisitor populates the list of commands as present in the input text file.
 */

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.sdsu.Turtle.Context;
import com.sdsu.Expression.IExpression;
import com.sdsu.Parse.ParseException;
import com.sdsu.Program;
import com.sdsu.Visitor.CommandVisitor;
import com.sdsu.command.*;
import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.Test;

class CommandVisitorTest {

  @Test
  void commandVisitorList() throws IOException, ParseException {
    Program program = new Program("src/com/sdsu/Tests/Input/CommandVisitor.txt");
    Context context = program.getContext();

    CommandVisitor visitor = new CommandVisitor(context);
    List<IExpression> expression = program.getExpressions();

    for (IExpression list : expression) {
      list.accept(visitor);
    }

    List<Command> commandList = visitor.getCommandList();
    assertNotNull(commandList);
    assertEquals(10, commandList.size());
    assertTrue(commandList.get(0) instanceof AssignCommand);
    assertTrue(commandList.get(1) instanceof MoveCommand);
    assertTrue(commandList.get(2) instanceof TurnCommand);
    assertTrue(commandList.get(3) instanceof MoveCommand);
    assertTrue(commandList.get(4) instanceof TurnCommand);
    assertTrue(commandList.get(5) instanceof AssignCommand);
    assertTrue(commandList.get(6) instanceof DistanceToCommand);
    assertTrue(commandList.get(7) instanceof BearingToCommand);
  }
}