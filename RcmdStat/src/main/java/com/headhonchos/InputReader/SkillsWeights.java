package com.headhonchos.InputReader;

import com.headhonchos.util.Lttt2SkillsTypes;
import com.headhonchos.util.TuningFactors;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class SkillsWeights
{
  static Map<String, Double> map = new HashMap();
  
  static
  {
    InputStream resourceAsStream = SkillsWeights.class.getResourceAsStream("/skillsWeights.tsv");
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(resourceAsStream));
    String line = null;
    try
    {
      while ((line = bufferedReader.readLine()) != null)
      {
        String[] tokens = line.split("=");
        String type = tokens[0];
        Double weight = Double.valueOf(Double.parseDouble(tokens[1].trim()));
        map.put(type, weight);
      }
      bufferedReader.close();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
  
  public static double getSkillWeight(Lttt2SkillsTypes type)
  {
    double typeWeight;
    switch (type)
    {
    case JOB_TITLE: 
      typeWeight = ((Double)map.get("JOB_TITLE_POWER")).doubleValue();
      break;
    case JOB_DESCRIPTION: 
      typeWeight = ((Double)map.get("JOB_DESCRIPTION_POWER")).doubleValue();
      break;
    case JOB_KEYWORDS: 
      typeWeight = ((Double)map.get("KEYWORDS_POWER")).doubleValue();
      break;
    case ESSENTIAL: 
      typeWeight = ((Double)map.get("ESSENTIAL_SKILLS_POWER")).doubleValue();
      break;
    case OTHER_DETAILS: 
      typeWeight = ((Double)map.get("OTHER_DETAILS_POWER")).doubleValue();
      break;
    case DESIGNATION: 
      typeWeight = ((Double)map.get("DESIGNATION_POWER")).doubleValue();
      break;
    case FUNCTIONAL_AREA: 
      typeWeight = ((Double)map.get("MASTER_FUNCTIONAL_AREA_POWER")).doubleValue();
      break;
    case MASTER_INDUSTRY: 
      typeWeight = ((Double)map.get("MASTER_INDUSTRY_POWER")).doubleValue();
      break;
    default: 
      typeWeight = 0.0D;
    }
    return typeWeight;
  }
  
  public static double getTuningFactor(TuningFactors type)
  {
    double tuningFactor;
    switch (type)
    {
    case CORE_SKILLS_POWER: 
      tuningFactor = ((Double)map.get("JOB_TITLE_POWER")).doubleValue();
      break;
    case KEYWORD_TUNING_FACTOR: 
      tuningFactor = ((Double)map.get("JOB_DESCRIPTION_POWER")).doubleValue();
      break;
    case JT_JD: 
      tuningFactor = ((Double)map.get("KEYWORDS_POWER")).doubleValue();
      break;
    case JD_KW: 
      tuningFactor = ((Double)map.get("ESSENTIAL_SKILLS_POWER")).doubleValue();
      break;
    case JT_JD_KW: 
      tuningFactor = ((Double)map.get("OTHER_DETAILS_POWER")).doubleValue();
      break;
    default: 
      tuningFactor = 0.0D;
    }
    return tuningFactor;
  }
}


/* Location:              /home/yatish/.m2-headhonchos/RcmdStat/1.0/RcmdStat-1.0.jar!/com/headhonchos/InputReader/SkillsWeights.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */