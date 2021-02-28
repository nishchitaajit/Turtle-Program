package com.sdsu.Turtle;

/*
 * This class has the context required by commands to interpret it,
 * along with methods to access the context data.
 */

import java.util.HashMap;
import java.util.Map;

public class Context {

  private Map<String, Object> map = new HashMap<>();
  private Turtle turtle;

  public Context() {
  }

  public Context(Turtle turtle) {
    this.turtle = turtle;
  }

  public void putValue(String key, Object value) {
    map.put(key, value);
  }

  public Object getValue(String key) {
    return map.get(key);
  }

  public Turtle getTurtle() {
    return turtle;
  }

  public void removeValue(String name) {
    map.remove(name);
  }
}