package com.sdsu.Tests.Test;

import static java.lang.Float.NaN;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.sdsu.Turtle.Point;
import com.sdsu.Turtle.Turtle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TurtleTest {
  private Turtle turtle;

  @BeforeEach
  void setUp() { turtle = new Turtle(); }

  @Test
  void move() {
    turtle.move(10);
    Point expectedLocation = new Point(10.0, 0.0);
    assertEquals(expectedLocation.getX(), turtle.location().getX());

    assertEquals(expectedLocation.getY(), turtle.location().getY());
  }

  @Test
  void turn() {
    turtle.turn(90);
    assertEquals(90, turtle.direction());

    turtle.turn(-30);
    assertEquals(60, turtle.direction());

    turtle.turn(75);
    assertEquals(135, turtle.direction());
  }

  @Test
  void direction() {
    assertEquals(0, turtle.direction());

    turtle.turn(-270);
    assertEquals(-270, turtle.direction());
  }

  @Test
  void location() {
    Point expectedLocation = new Point(0.0, 0.0);
    assertEquals(expectedLocation.getX(), turtle.location().getX());
    assertEquals(expectedLocation.getY(), turtle.location().getY());

    turtle.move(10);
    turtle.turn(90);
    turtle.move(20);
    turtle.turn(-60);
    turtle.move(15);

    Point expectedLocation1 = new Point(23.0, 28.0);
    assertEquals(expectedLocation1.getX(), turtle.location().getX());
    assertEquals(expectedLocation1.getY(), turtle.location().getY());
  }

  @Test
  void distanceTo() {
    Point calculateDistance = new Point(0.0,0.0);
    assertEquals(0,turtle.distanceTo(calculateDistance));

    Point calculateDistance1 = new Point(20.0,30.0);
    assertEquals(36.06,turtle.distanceTo(calculateDistance1));

    turtle.move(2);
    turtle.turn(90);
    turtle.move(3);
    Point calculateDistance3 = new Point(5.0,7.0);
    assertEquals(5.0,turtle.distanceTo(calculateDistance3));
  }

  @Test
  void bearingTo() {
    Point calculateDegrees = new Point(0.0, 0.0);
    assertEquals(NaN, turtle.bearingTo(calculateDegrees)); //Since 0 divided by 0 is NaN.
    turtle.move(3);
    turtle.turn(90);
    turtle.move(4);
    assertEquals(53.13, turtle.bearingTo(calculateDegrees));
  }
}