package com.sdsu.Tests.Test;

/*
 * Tests to validate Parser and Reader classes.
 */

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.sdsu.Expression.*;
import com.sdsu.Parse.*;
import com.sdsu.Turtle.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ParserTest {
  private Context context;

  @BeforeEach
  void setUp() { context = new Context(new Turtle()); }

  @Test
  void constantTest() {
    Constant constant = new Constant(10);
    assertEquals(10, constant.interpret(context));
  }

  @Test
  void variableTest() {
    Variable variable = new Variable("variable");
    context.putValue("variable",10.0);

    assertEquals("variable", variable.getName());
    assertEquals(10.0, variable.interpret(context));
  }

  @Test
  void tokenAssignment() {
    List<String> expression = Reader.tokenize("#variable = 20");

    assertEquals(4, expression.size()); // Reader appends "\n" so asserting size as 4
    assertEquals("#variable", expression.get(0));
    assertEquals("=", expression.get(1));
    assertEquals("20", expression.get(2));
    assertEquals("\n", expression.get(3));
  }

  @Test
  void parseAssignment() throws IOException, ParseException {
    List<String> tokens = Reader.tokenize("#variable = 20");

    IExpression expression = Parser.parseFile(tokens, null);
    assertTrue(expression instanceof Assignment);
    Assignment assignment = (Assignment) expression;

    assertEquals("variable", assignment.getName());
    assertEquals(20, assignment.interpret(context));
  }

  @Test
  void tokenMoveConstant() {
    List<String> expression = Reader.tokenize("move 15");

    assertEquals(3, expression.size());
    assertEquals("move", expression.get(0));
    assertEquals(String.valueOf(15), expression.get(1));
    assertEquals("\n", expression.get(2));
  }

  @Test
  void parseMoveConstant() throws IOException, ParseException {
    List<String> token = Reader.tokenize("move 15");
    IExpression expression = Parser.parseFile(token, null);
    Move move = (Move) expression;

    assertEquals(15, move.interpret(context));
  }

  @Test
  void tokenMoveVariable() {
    List<String> expression = Reader.tokenize("move #variable");

    assertEquals(3, expression.size());
    assertEquals("move", expression.get(0));
    assertEquals("#variable", expression.get(1));
    assertEquals("\n", expression.get(2));
  }

  @Test
  void parseMoveVariable() throws IOException, ParseException {
    context.putValue("variable", 15);
    List<String> token = Reader.tokenize("move #variable");
    IExpression expression = Parser.parseFile(token, null);
    Move move = (Move) expression;

    assertEquals(15, move.interpret(context));
  }

  @Test
  void tokenDistanceTo() {
    List<String> expression = Reader.tokenize("distanceTo 2 3");

    assertEquals(4, expression.size());
    assertEquals("distanceTo", expression.get(0));
    assertEquals(String.valueOf(2), expression.get(1));
    assertEquals(String.valueOf(3), expression.get(2));
    assertEquals("\n", expression.get(3));
  }

  @Test
  void parseDistanceTo() throws ParseException, IOException {
    Point newPoint = new Point(3,4);
    context.putValue("s", newPoint);
    List<String> token = Reader.tokenize("distanceTo #s");
    IExpression expression = Parser.parseFile(token, null);
    DistanceTo distanceTo = (DistanceTo) expression;

//    assertEquals(2, distanceTo.interpret(context).getX());
//    assertEquals(3, distanceTo.interpret(context).getY());
    assertEquals(5.0, distanceTo.interpret(context));
  }

  @Test
  void tokenBearingTo() {
    List<String> expression = Reader.tokenize("bearingTo 3 4");

    assertEquals(4, expression.size());
    assertEquals("bearingTo", expression.get(0));
    assertEquals(String.valueOf(3), expression.get(1));
    assertEquals(String.valueOf(4), expression.get(2));
    assertEquals("\n", expression.get(3));
  }

  @Test
  void parseBearingTo() throws ParseException, IOException {
    Point newPoint = new Point(3,4);
    context.putValue("s", newPoint);
    List<String> token = Reader.tokenize("bearingTo #s");
    IExpression expression = Parser.parseFile(token, null);
    BearingTo bearingTo = (BearingTo) expression;

    assertEquals(53.13, bearingTo.interpret(context));
  }

  @Test
  void tokenTurnConstant() {
    List<String> expression = Reader.tokenize("turn 90");

    assertEquals(3, expression.size());
    assertEquals("turn", expression.get(0));
    assertEquals(String.valueOf(90), expression.get(1));
    assertEquals("\n", expression.get(2));
  }

  @Test
  void parseTurnConstantTest() throws IOException, ParseException {
    List<String> token = Reader.tokenize("turn 90");
    IExpression expression = Parser.parseFile(token, null);
    Turn turn = (Turn) expression;

    assertEquals(90, turn.interpret(context));
  }

  @Test
  void tokenTurnVariable() {
    List<String> expression = Reader.tokenize("turn #variable");

    assertEquals(3, expression.size());
    assertEquals("turn", expression.get(0));
    assertEquals("#variable", expression.get(1));
    assertEquals("\n", expression.get(2));
  }

  @Test
  void parseTurnVariable() throws IOException, ParseException {
    context.putValue("variable", 90);
    List<String> token = Reader.tokenize("turn #variable");
    IExpression expression = Parser.parseFile(token, null);
    Turn turn = (Turn) expression;

    assertEquals(90, turn.interpret(context));
  }

  @Test
  void tokenRepeatConstant() {
    List<String> expression = Reader.tokenize("repeat 2");

    assertEquals(3, expression.size());
    assertEquals("repeat", expression.get(0));
    assertEquals(String.valueOf(2), expression.get(1));
    assertEquals("\n", expression.get(2));
  }

  @Test
  void parseRepeatConstant() throws IOException, ParseException {
    String token = "move 10 \n"
        + "turn 90 \n"
        + "end";
    BufferedReader readLine = new BufferedReader(new StringReader(token));
    List<String> tokenList = Reader.tokenize("repeat 4");
    IExpression expression = Parser.parseFile(tokenList, readLine);
    assertTrue(expression instanceof Repeat);

    Repeat repeat = (Repeat) expression;
    Object numberOfTimes = repeat.interpret(context);
    assertEquals(4, numberOfTimes);
  }

  @Test
  void tokenRepeatVariable() {
    List<String> expression = Reader.tokenize("repeat #variable");

    assertEquals(3, expression.size());
    assertEquals("repeat", expression.get(0));
    assertEquals("#variable", expression.get(1));
    assertEquals("\n", expression.get(2));
  }

  @Test
  void parseRepeatVariable() throws IOException, ParseException {
    context.putValue("variable", 4);
    String token = "move 10 \n"
        + "turn 90 \n"
        + "end";
    BufferedReader readLine = new BufferedReader(new StringReader(token));
    List<String> tokenList = Reader.tokenize("repeat #variable");
    IExpression expression = Parser.parseFile(tokenList, readLine);
    assertTrue(expression instanceof Repeat);

    Repeat repeat = (Repeat) expression;
    Object numberOfTimes = repeat.interpret(context);
    assertEquals(4, numberOfTimes);
    assertEquals(4, repeat.interpret(context));
  }
}