package com.headhonchos;

import org.apache.commons.lang3.tuple.Pair;

public class SalaryStat
{
  public static Pair<String, String> getSalaryRangeIndian(int min, int max)
  {
    int minModified = (int)(min * 0.75D);
    int maxModified = (int)(max * 1.05D);
    
    String finalMinSalary = Integer.toString(minModified);
    String finalMaxSalary = Integer.toString(maxModified);
    
    Pair<String, String> minMaxPair = Pair.of(finalMinSalary, finalMaxSalary);
    return minMaxPair;
  }
  
  public static Pair<String, String> getSalaryRangeNonIndian(int min, int max)
  {
    int minRelaxation = minRelaxation(min);
    int maxRelaxation = maxRelaxtation(max);
    
    int refinedMin = min - minRelaxation;
    int refinedMax = max + maxRelaxation;
    
    String finalMinSalary = Integer.toString(refinedMin);
    String finalMaxSalary = Integer.toString(refinedMax * 2);
    
    Pair<String, String> minMaxPair = Pair.of(finalMinSalary, finalMaxSalary);
    return minMaxPair;
  }
  
  private static int maxRelaxtation(int max)
  {
    int maxRelaxation = 0;
    if (max < 20) {
      maxRelaxation = 2;
    } else if ((max >= 20) && (max < 30)) {
      maxRelaxation = 2;
    } else if ((max >= 30) && (max < 50)) {
      maxRelaxation = 5;
    } else if ((max >= 50) && (max < 70)) {
      maxRelaxation = 5;
    } else if ((max >= 70) && (max < 90)) {
      maxRelaxation = 5;
    } else if (max >= 90) {
      maxRelaxation = 20;
    }
    return maxRelaxation;
  }
  
  private static int minRelaxation(int min)
  {
    int minRelaxation = 0;
    if ((min >= 10) && (min < 20)) {
      minRelaxation = 3;
    } else if ((min >= 20) && (min < 30)) {
      minRelaxation = 5;
    } else if ((min >= 30) && (min < 50)) {
      minRelaxation = 8;
    } else if ((min >= 50) && (min < 70)) {
      minRelaxation = 15;
    } else if ((min >= 70) && (min < 90)) {
      minRelaxation = 15;
    } else if (min >= 90) {
      minRelaxation = 20;
    }
    return minRelaxation;
  }
}


/* Location:              /home/yatish/.m2-headhonchos/RcmdStat/1.0/RcmdStat-1.0.jar!/com/headhonchos/SalaryStat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */