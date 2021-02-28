package com.sdsu.Parse;

/*
 * This class divides the expression into tokens.
 * "move 10" = [ "move", "10" , "\n" ]
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Reader {
  public static List<String> tokenize(String line) {
    String removeComma = line.replaceAll(",", "");
    String[] lineTokens = removeComma.split(" ");
    List<String> expression = new ArrayList<>(Arrays.asList(lineTokens));

    // Removes empty tokens in the expression like spaces and tabs.
    expression.removeIf(""::equals);

    // Adds new line to check the tokens in expression.
    expression.add("\n");
    return expression;
  }
}