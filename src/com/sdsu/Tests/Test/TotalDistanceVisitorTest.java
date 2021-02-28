package com.sdsu.Tests.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.sdsu.Turtle.Context;
import com.sdsu.Expression.IExpression;
import com.sdsu.Parse.ParseException;
import com.sdsu.Program;
import com.sdsu.Visitor.TotalDistanceVisitor;
import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * Tests the TotalDistanceVisitor class.
 **/

class TotalDistanceVisitorTest {

  @Test
  void distanceVisitor() throws IOException, ParseException {
    Program program = new Program("src/com/sdsu/Tests/Input/BasicCommands.txt");
    Context context = program.getContext();

    TotalDistanceVisitor visitor = new TotalDistanceVisitor(context);
    List<IExpression> expression = program.getExpressions();

    for (IExpression list: expression)
      list.accept(visitor);

    assertEquals(65, visitor.getDistance());
  }

  @Test
  void distanceVisitor2() throws IOException, ParseException {
    Program program = new Program("src/com/sdsu/Tests/Input/AssignCalculate.txt");
    Context context = program.getContext();

    TotalDistanceVisitor visitor = new TotalDistanceVisitor(context);
    List<IExpression> expression = program.getExpressions();

    for (IExpression list: expression)
      list.accept(visitor);

    assertEquals(64, visitor.getDistance());
  }
}