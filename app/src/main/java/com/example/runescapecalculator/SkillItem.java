package com.example.runescapecalculator;

/**********************************************************************
 * Object class to hold the skill item, which holds the data for which
 * skill it is in the list of items.
 *********************************************************************/
public class SkillItem {
    /** String to hold skill and image*/
    private String skill, skillImage = null;

    /******************************************************************
     * Default constructor for the skillItem
     *****************************************************************/
    public SkillItem() { }

    /******************************************************************
     * Constructor that sets the skill String
     * @param skill skill to be set
     * @param skillImage skill Image to be set
     *****************************************************************/
    public SkillItem(String skill, String skillImage){
        this.skill = skill;
        this.skillImage = skillImage;
    }

    /******************************************************************
     * Getter for the skill
     * @return skill of item
     *****************************************************************/
    public String getSkill() {
        return skill;
    }

    /******************************************************************
     * Setter for the skill
     * @param skill skill to be set
     *****************************************************************/
    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getSkillImage() {
        return skillImage;
    }

    public void setSkillImage(String skillImage) {
        this.skillImage = skillImage;
    }
}
