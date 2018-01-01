package com.headhonchos;

import com.headhonchos.InputReader.SkillsWeights;
import com.headhonchos.util.Lttt2SkillsTypes;
import com.headhonchos.util.TuningFactors;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.collections.ListUtils;

public class SkillsStat
{
  private static final DecimalFormat THREE_DECIMAL_DIGITS_FORMATTER = new DecimalFormat("#.###");
  private static int all_results_js_frequency = 152345;
  
  public static Map<String, Double> getWeightedSkills(List<String> skillsList, Lttt2SkillsTypes skillType, Map<String, Integer> skillsFrequencyMap)
  {
    Map<String, Double> weightedSkills = new HashMap();
    for (String skill : skillsList) {
      if (skillsFrequencyMap.containsKey(skill))
      {
        double frequencyWeight = getFrequencyWeight(((Integer)skillsFrequencyMap.get(skill)).intValue(), all_results_js_frequency, TuningFactors.KEYWORD_TUNING_FACTOR);
        double occurrenceWeight = getOccurrenceWeight(skillType, TuningFactors.CORE_SKILLS_POWER);
        double overallWeight = frequencyWeight * occurrenceWeight;
        weightedSkills.put(skill, Double.valueOf(overallWeight));
      }
    }
    return weightedSkills;
  }
  
  public static double getFrequencyWeight(int skillFrequency, int totalFrequency, TuningFactors tuningFactorType)
  {
    double skillWeight = SkillsWeights.getTuningFactor(tuningFactorType) * Math.log(totalFrequency / (skillFrequency + 1));
    return skillWeight;
  }
  
  private static double getOccurrenceWeight(Lttt2SkillsTypes skillType, TuningFactors tuningFactorType)
  {
    double v = SkillsWeights.getSkillWeight(skillType) * SkillsWeights.getTuningFactor(tuningFactorType);
    return v;
  }
  
  public static Map<String, Double> getRepetationWeightedSkills(List<String> jtSkills, List<String> jdSkills, List<String> jkwSkills)
  {
    double coreSkillPower = SkillsWeights.getTuningFactor(TuningFactors.CORE_SKILLS_POWER);
    Map<String, Double> weightedSkills = new HashMap();
    
    List<String> jt_jd_jkw_skill = ListUtils.intersection(ListUtils.intersection(jtSkills, jdSkills), jkwSkills);
    for (String skill : jt_jd_jkw_skill)
    {
      double weight = SkillsWeights.getTuningFactor(TuningFactors.JT_JD_KW) * coreSkillPower;
      weightedSkills.put(skill, new Double(THREE_DECIMAL_DIGITS_FORMATTER.format(weight)));
    }
    List<String> jt_jd_skills = ListUtils.subtract(ListUtils.intersection(jtSkills, jdSkills), jt_jd_jkw_skill);
    for (String skill : jt_jd_skills)
    {
      double weight = SkillsWeights.getTuningFactor(TuningFactors.JT_JD) * coreSkillPower;
      weightedSkills.put(skill, new Double(THREE_DECIMAL_DIGITS_FORMATTER.format(weight)));
    }
    List<String> jd_jkw_skills = ListUtils.subtract(ListUtils.intersection(jdSkills, jkwSkills), jt_jd_jkw_skill);
    for (String skill : jd_jkw_skills)
    {
      double weight = SkillsWeights.getTuningFactor(TuningFactors.JD_KW) * coreSkillPower;
      weightedSkills.put(skill, new Double(THREE_DECIMAL_DIGITS_FORMATTER.format(weight)));
    }
    List<String> jt_jkw_skills = ListUtils.subtract(ListUtils.intersection(jtSkills, jkwSkills), jt_jd_jkw_skill);
    for (String skill : jt_jkw_skills)
    {
      double weight = SkillsWeights.getTuningFactor(TuningFactors.JT_KW) * coreSkillPower;
      weightedSkills.put(skill, new Double(THREE_DECIMAL_DIGITS_FORMATTER.format(weight)));
    }
    return weightedSkills;
  }
}


/* Location:              /home/yatish/.m2-headhonchos/RcmdStat/1.0/RcmdStat-1.0.jar!/com/headhonchos/SkillsStat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */