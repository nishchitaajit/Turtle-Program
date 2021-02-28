package com.sdsu;

/*
 * This class executes the turtle program code file.
 */

import com.sdsu.Expression.IExpression;
import com.sdsu.Parse.*;
import com.sdsu.Turtle.Context;
import com.sdsu.Turtle.Turtle;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/*
 * Generates a program from the source code.
 */
public class Program {

  private BufferedReader reader;
  private Turtle turtle = new Turtle();
  private Context context = new Context(turtle);
  private boolean isAlive = true;

  public Program(String fileName) throws IOException {
    File inputFile = new File(fileName);
    String absolutePath = inputFile.getAbsolutePath();
    reader = Files.newBufferedReader(Path.of(absolutePath));
  }

  public Context getContext() {
    return context;
  }

  public Turtle getTurtle() {
    return turtle;
  }

  public boolean isAlive() {
    return isAlive;
  }

  /*
   * This method implements the interpreter by interpreting every line of the code.
   */
  public void interpret() throws IOException, ParseException {
    String line = reader.readLine();
    if (line != null) {
      parseLine(line).interpret(context);
    } else {
      isAlive = false;
    }
  }

  /*
   * This method parses all the lines in the code and it returns a list of
   * expressions without executing them. It helps in visiting the code.
   */
  public List<IExpression> getExpressions() throws IOException, ParseException {

    List<IExpression> expressions = new ArrayList<>();
    String line;

    while ((line = reader.readLine()) != null) {
      expressions.add(parseLine(line));
    }

    return expressions;
  }

  private IExpression parseLine(String line) throws IOException, ParseException {
    try {
      return Parser.parseFile(Reader.tokenize(line), reader);
    } catch (ParseException e) {
      throw new ParseException("Cannot parse line - " + line, e);
    }
  }
}