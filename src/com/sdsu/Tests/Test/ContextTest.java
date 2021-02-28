package com.sdsu.Tests.Test;

/*
 * Test to validate that the map is getting populating with objects.
 */

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.sdsu.Turtle.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ContextTest {

  private String DISTANCE = "#distance";
  private String POINT = "#point";
  private Point newPoint = new Point(3, 4);
  private Context context;

  @BeforeEach
  void setUp() {
    context = new Context(new Turtle());
    context.putValue(DISTANCE, 10);
    context.putValue(POINT, newPoint);
  }

  @Test
  void getValue() {
    assertEquals(10, context.getValue(DISTANCE));
    assertEquals(newPoint, context.getValue(POINT));
  }
}