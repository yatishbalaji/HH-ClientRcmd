package com.headhonchos;

import org.apache.commons.lang3.tuple.Pair;

public class ExperienceStat
{
  public static Pair<String, String> getExpRange(int min, int max)
  {
    int minRelaxation = getMinRelaxation(min);
    int maxRelaxation = getMaxRelaxation(max);
    
    int refinedMin = min - minRelaxation;
    int refinedMax = max + maxRelaxation;
    
    String finalMin = Integer.toString(refinedMin);
    String finalMax;
    if (refinedMin <= 20) {
      finalMax = Integer.toString(refinedMax);
    } else {
      finalMax = "*";
    }
    Pair<String, String> minMaxPair = Pair.of(finalMin, finalMax);
    return minMaxPair;
  }
  
  private static int getMaxRelaxation(int max)
  {
    int maxRelaxation = 0;
    if (max < 5) {
      maxRelaxation = 1;
    } else if ((max >= 5) && (max < 15)) {
      maxRelaxation = 2;
    } else if ((max >= 15) && (max < 30)) {
      maxRelaxation = 2;
    } else if ((max >= 30) && (max < 40)) {
      maxRelaxation = 3;
    } else if ((max >= 40) && (max < 60)) {
      maxRelaxation = 2;
    }
    return maxRelaxation;
  }
  
  private static int getMinRelaxation(int min)
  {
    int minRelaxation = 0;
    if ((min >= 1) && (min < 5)) {
      minRelaxation = 0;
    } else if ((min >= 5) && (min < 15)) {
      minRelaxation = 1;
    } else if ((min >= 15) && (min < 30)) {
      minRelaxation = 3;
    } else if ((min >= 30) && (min < 40)) {
      minRelaxation = 4;
    } else if (min >= 40) {
      minRelaxation = 3;
    }
    return minRelaxation;
  }
}


/* Location:              /home/yatish/.m2-headhonchos/RcmdStat/1.0/RcmdStat-1.0.jar!/com/headhonchos/ExperienceStat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */