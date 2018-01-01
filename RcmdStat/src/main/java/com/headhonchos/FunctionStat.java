package com.headhonchos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public class FunctionStat
{
  private static Map<String, List<String>> functionFungibilityMap = new HashMap();
  
  static
  {
    InputStream resourceAsStream = FunctionStat.class.getResourceAsStream("/ff.tsv");
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(resourceAsStream));
    String line = null;
    try
    {
      while ((line = bufferedReader.readLine()) != null)
      {
        String[] tokens = line.split("\t");
        String function = tokens[0];
        if (tokens.length == 1)
        {
          functionFungibilityMap.put(function, new ArrayList());
        }
        else if (tokens.length == 2)
        {
          ArrayList<String> fungibilityList = new ArrayList(Arrays.asList(tokens[1].split(",")));
          functionFungibilityMap.put(function, fungibilityList);
        }
        else if (tokens.length != 3) {}
      }
      bufferedReader.close();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
  
  public static List<String> getfunctionFungibility(String functionName)
  {
    List<String> functionFungibility = null;
    if (functionFungibilityMap.containsKey(functionName))
    {
      List<String> fungibility = (List)functionFungibilityMap.get(functionName);
      if ((fungibility.isEmpty()) || (fungibility == null)) {
        functionFungibility = new ArrayList(Arrays.asList(new String[] { "*" }));
      } else {
        functionFungibility = fungibility;
      }
    }
    else
    {
      throw new NoSuchElementException();
    }
    return functionFungibility;
  }
}


/* Location:              /home/yatish/.m2-headhonchos/RcmdStat/1.0/RcmdStat-1.0.jar!/com/headhonchos/FunctionStat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */