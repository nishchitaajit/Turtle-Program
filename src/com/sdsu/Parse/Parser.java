package com.sdsu.Parse;

/*
 * This class parses the Turtle language with the following grammar :
 *
 * expression : move | turn | assignment | repeat | distanceTo | bearingTo
 * #variable = (0-9)+
 * move constant | #variable
 * turn constant | #variable
 * repeat constant | #variable + "end
 */

import com.sdsu.Expression.*;
import com.sdsu.Turtle.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public class Parser {

  private static final String NAME_REGEX = "[a-zA-Z]+";

  public static IExpression parseFile(List<String> expression, BufferedReader reader)
      throws IOException, ParseException {

    IExpression exp = null;
    String firstToken = expression.get(0).toLowerCase();

    // If first token is a constant
    String name = expression.get(0).substring(1);
    if (firstToken.startsWith("#")) {
      if (name.matches(NAME_REGEX)) {
        if ("=".equals(expression.get(1))) {

          // Next token should be a constant or a point
          if (expression.size() > 4) {

            // Parses commands like : #d = distanceTo #s
            if (expression.get(2).equals("distanceTo")) {
              String input = expression.get(2) + " " + expression.get(3);
              List<String> nextList = Reader.tokenize(input);

              exp = Parser.parseFile(nextList, null);
            }

            // Parses commands like : #a = bearingTo #s
            else if (expression.get(2).equals("bearingTo")) {
              String input = expression.get(2) + " " + expression.get(3);
              List<String> nextList = Reader.tokenize(input);

              exp = Parser.parseFile(nextList, null);
            }
            if (exp != null) {
              return new Assignment(name, exp);
            }
            double x = Double.parseDouble(expression.get(2));
            double y = Double.parseDouble(expression.get(3));
            Point point = new Point(x, y);
            String pointName = String.valueOf(name.charAt(1));
            return new Assignment(pointName, point);
          }
          Constant constant = constantExp(expression.get(2));
          return new Assignment(name, constant);
        }
      }
    }

    switch (firstToken) {
      case "move":
        return new Move(valueExp(expression.get(1)));
      case "turn":
        return new Turn(valueExp(expression.get(1)));
      case "repeat":
        IExpression numberOfRepeat = valueExp(expression.get(1));
        Repeat repeat = new Repeat(numberOfRepeat);
        // All the lines should be read till "end" is found
        String line;
        while ((line = reader.readLine()) != null && !"end".equals(line.toLowerCase())) {
          List<String> newToken = Reader.tokenize(line);
          repeat.addExpression(parseFile(newToken, reader));
        }
        return repeat;
      case "distanceto":
        return new DistanceTo(valueExp(expression.get(1)));
      case "bearingto":
        return new BearingTo(valueExp(expression.get(1)));
      default:
        throw new ParseException("Error parsing!");
    }
  }

  private static IExpression valueExp(String token) throws ParseException {
    IExpression terminalNode;

    // To check if it's a variable or a constant
    if (token.startsWith("#")) {
      terminalNode = variableExp(token);
    } else {
      terminalNode = constantExp(token);
    }
    return terminalNode;
  }

  private static Variable variableExp(String token) throws ParseException {
    String name = token.substring(1);

    if (name.matches(NAME_REGEX)) {
      return new Variable(name);
    } else {
      throw new ParseException("Invalid variable name!");
    }
  }

  private static Constant constantExp(String token) throws ParseException {
    try {
      return new Constant(Integer.parseInt(token));
    } catch (NumberFormatException e) {
      throw new ParseException("Not a valid number!", e);
    }
  }
}