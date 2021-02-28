package com.sdsu.Turtle;

/*
 * This class performs the basic turtle language operations.
 */

import java.text.DecimalFormat;

public class Turtle {

  private float directionDegrees = 0;
  private Point location = new Point();
  private double distanceTo = 0.0;
  private double bearingTo = 0.0;

  public Turtle() {}

  public void move(int distance) {
    double radians = directionDegrees * Math.PI / 180;
    double deltaX = Math.cos(radians) * distance;
    double deltaY = Math.sin(radians) * distance;
    location.update(deltaX, deltaY);
  }

  public void turn(int degrees) {
    directionDegrees += degrees;

    //Rollover if direction is over 360 degrees
    if (directionDegrees >= 360) {
      directionDegrees %= 360;
    }
  }

  public float direction() {
    return directionDegrees;
  }

  public Point location() {
    return location;
  }

  private DecimalFormat decimalFormat = new DecimalFormat("##.##");

  public double distanceTo(Point newPoint) {
    double pointX = newPoint.getX() - location.getX();
    double pointY = newPoint.getY() - location.getY();
    double result = Math.sqrt((pointX * pointX) + (pointY * pointY));
    String resultString = decimalFormat.format(result);
    distanceTo = Double.parseDouble(resultString);
    return distanceTo;
  }

  public double getDistanceTo() { return distanceTo; }

  public double bearingTo(Point newPoint) {
    double pointX = newPoint.getY() - location.getY();
    double pointY = newPoint.getX() - location.getX();
    double result = Math.toDegrees(Math.atan(pointX / pointY));
    String resultString = decimalFormat.format(result);
    bearingTo = Double.parseDouble(resultString);
    return bearingTo;
  }

  public double getBearingTo() { return bearingTo; }
}