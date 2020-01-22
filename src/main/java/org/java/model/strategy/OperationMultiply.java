package org.java.model.strategy;

public class OperationMultiply implements Strategy{
   @Override
   public int doOperation(int num1, int num2) {
      return num1 * num2;
   }

   @Override
   public int getType() {
      return 1;
   }
}