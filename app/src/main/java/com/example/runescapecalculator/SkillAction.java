package com.example.runescapecalculator;

/**********************************************************************
 * Object class to hold action name, action experience given per
 * action done, and image for use in table in Calculator Activity.
 * @author Tristan James
 *********************************************************************/
public class SkillAction {

    /** holds data on action */
    private String name, image;
    private double exp;

    /******************************************************************
     * Constructor to hold data on skill
     * @param name name of action
     * @param exp experience given per use of action
     * @param image image to use with action
     *****************************************************************/
    public SkillAction(String name, float exp, String image){
        this.exp = exp;
        this.name = name;
        this.image = image;
    }

    /******************************************************************
     * Getter for name of action
     * @return name of action
     *****************************************************************/
    public String getName() {
        return name;
    }

    /******************************************************************
     * Getter for experience points given per action done
     * @return experience points given per action done
     *****************************************************************/
    public double getExp() {
        return exp;
    }

    /******************************************************************
     * Getter for image name for action
     * @return image name of action
     *****************************************************************/
    public String getImage() {
        return image;
    }
}
