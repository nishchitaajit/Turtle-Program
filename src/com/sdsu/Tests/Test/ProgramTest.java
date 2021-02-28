package com.sdsu.Tests.Test;

/*
 * This class represents command like : #variable = 10, #Ps = 2 3
 */

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.sdsu.Parse.ParseException;
import com.sdsu.Program;
import java.io.IOException;
import org.junit.jupiter.api.Test;

class ProgramTest {

  @Test
  void input1Test() throws IOException, ParseException {
    Program program = new Program("src/com/sdsu/Tests/Input/BasicCommands.txt");
    while (program.isAlive()) {
      program.interpret();
    }

    assertEquals(5, program.getTurtle().getDistanceTo());
    assertEquals(53.13, program.getTurtle().getBearingTo());
    assertEquals(15, program.getTurtle().location().getX());
    assertEquals(20, program.getTurtle().location().getY());
    assertEquals(180, program.getTurtle().direction());
  }

  @Test
  void input2Test() throws IOException, ParseException {
    Program program = new Program("src/com/sdsu/Tests/Input/Repeat.txt");
    while (program.isAlive()) {
      program.interpret();
    }

    assertEquals(0, program.getTurtle().location().getX());
    assertEquals(10, program.getTurtle().location().getY());
    assertEquals(270, program.getTurtle().direction());
  }

  @Test
  void input3Test() throws IOException, ParseException {
    Program program = new Program("src/com/sdsu/Tests/Input/Assignment.txt");
    while (program.isAlive()) {
      program.interpret();
    }

    assertEquals(5, program.getTurtle().location().getX());
    assertEquals(0, program.getTurtle().location().getY());
    assertEquals(45, program.getTurtle().direction());
  }
}