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

public class IndustryStat
{
  private static Map<String, List<String>> industryFungibilityMap = new HashMap();
  
  static
  {
    InputStream resourceAsStream = IndustryStat.class.getResourceAsStream("/if.tsv");
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(resourceAsStream));
    String line = null;
    try
    {
      while ((line = bufferedReader.readLine()) != null)
      {
        String[] tokens = line.split("\t");
        String industry = tokens[0];
        if (tokens.length == 1)
        {
          industryFungibilityMap.put(industry, new ArrayList());
        }
        else if (tokens.length == 2)
        {
          ArrayList<String> fungibilityList = new ArrayList(Arrays.asList(tokens[1].split(",")));
          industryFungibilityMap.put(industry, fungibilityList);
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
  
  public static List<String> getIndustryFungibility(String industryName)
  {
    List<String> industryFungibility = null;
    if (industryFungibilityMap.containsKey(industryName))
    {
      List<String> fungibility = (List)industryFungibilityMap.get(industryName);
      if ((fungibility.isEmpty()) || (fungibility == null)) {
        industryFungibility = new ArrayList(Arrays.asList(new String[] { "*" }));
      } else {
        industryFungibility = fungibility;
      }
    }
    else
    {
      throw new NoSuchElementException();
    }
    return industryFungibility;
  }
}


/* Location:              /home/yatish/.m2-headhonchos/RcmdStat/1.0/RcmdStat-1.0.jar!/com/headhonchos/IndustryStat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */