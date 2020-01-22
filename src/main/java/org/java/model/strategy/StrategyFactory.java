package org.java.model.strategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StrategyFactory {

   private Map<Integer,Strategy> map;

   public StrategyFactory(){
      List<Strategy> list = new ArrayList<>(3);
      list.add(new OperationAdd());
      list.add(new OperationMultiply());
      list.add(new OperationSubstract());

      map = list.stream().collect(Collectors.toMap(Strategy::getType,
              strategy -> strategy));
   }

   public static class Holder {
      public static StrategyFactory instance = new StrategyFactory();
   }

   public static StrategyFactory getInstance() {
      return Holder.instance;
   }

   public Strategy get(Integer type) {
      return map.get(type);
   }
}