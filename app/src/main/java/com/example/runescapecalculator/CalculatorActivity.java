package com.example.runescapecalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;

/**********************************************************************
 * Activity allows for users to enter the level they are currently
 * at and the level they would like to reach. This calculates the
 * amount of experience points needed at each of those levels,
 * and displays three actions the user can do based on the skill
 * they chose in the previous activity. It shows an image of the
 * action, name of the action, how much experience points each time
 * the action is done gives, and the amount of times they have to do
 * that action to reach their desired level.
 * Icons retrieved from https://github.com/runelite/runelite
 * @author Tristan James
 *********************************************************************/
public class CalculatorActivity extends AppCompatActivity implements TextView.OnEditorActionListener, View.OnClickListener {

    /** Views to edit and get data from */
    private EditText currentLevel, targetLevel;
    private TextView currentEXP, targetEXP, rowOneEXP, rowTwoEXP, rowThreeEXP, rowOneAction, rowTwoAction, rowThreeAction, rowOneQuantity, rowTwoQuantity, rowThreeQuantity;
    private ImageView rowOneImage, rowTwoImage, rowThreeImage;
    private Button calculateButton;
    private String skill, skillImage;
    private Toolbar calculatorToolbar;
    private SharedPreferences savedValues;

    /** for use in calculating action quantity and experience */
    private int cLevel, tLevel;
    private float cEXP, tEXP;
    private SkillActionItems items;


    /******************************************************************
     * Creates the activity, sets the view, sets the toolbar, and gets
     * the bundle from the previous activity containing the skill
     * @param savedInstanceState previous state of activity
     *****************************************************************/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        currentLevel = findViewById(R.id.currentLevel);
        targetLevel = findViewById(R.id.targetLevel);
        currentEXP = findViewById(R.id.currentEXP);
        targetEXP = findViewById(R.id.targetEXP);
        rowOneEXP = findViewById(R.id.rowOneEXP);
        rowTwoEXP = findViewById(R.id.rowTwoEXP);
        rowThreeEXP = findViewById(R.id.rowThreeEXP);
        rowOneAction = findViewById(R.id.rowOneAction);
        rowTwoAction = findViewById(R.id.rowTwoAction);
        rowThreeAction = findViewById(R.id.rowThreeAction);
        rowOneQuantity = findViewById(R.id.rowOneQuantity);
        rowTwoQuantity = findViewById(R.id.rowTwoQuantity);
        rowThreeQuantity = findViewById(R.id.rowThreeQuantity);
        rowOneImage = findViewById(R.id.rowOneImage);
        rowTwoImage = findViewById(R.id.rowTwoImage);
        rowThreeImage = findViewById(R.id.rowThreeImage);
        calculateButton = findViewById(R.id.calculateButton);
        savedValues = getSharedPreferences("savedValues", MODE_PRIVATE);

        currentLevel.setOnEditorActionListener(this);
        targetLevel.setOnEditorActionListener(this);
        calculateButton.setOnClickListener(this);

        Bundle extras = getIntent().getExtras();
        skill = extras.getString("skill");
        skillImage = extras.getString("skillImage");
        calculatorToolbar = (Toolbar)findViewById(R.id.calculatorToolbar);
        setSupportActionBar(calculatorToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("    " + skill);
        getSupportActionBar().setLogo(getResources().getIdentifier(skillImage, "drawable", getPackageName()));
        items = new SkillActionItems();
    }

    /******************************************************************
     * Click listener for the button that uses calculate helper method
     * @param view view clicked on
     *****************************************************************/
    @Override
    public void onClick(View view) {
        calculate();
    }

    /******************************************************************
     * Editor listener for editTexts, which launches the calculateEXP
     * helper method
     * @param textView view edited
     * @param i action done
     * @param keyEvent specific key pressed
     * @return false
     *****************************************************************/
    @Override
    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        calculateEXP();
        return false;
    }

    /******************************************************************
     * Generates the formula for the amount of exp needed per level,
     * and sets exp to 0 if not a valid level
     *****************************************************************/
    public void calculateEXP(){
        cLevel = Integer.parseInt(currentLevel.getText().toString());
        tLevel = Integer.parseInt(targetLevel.getText().toString());

        cEXP = 0;
        tEXP = 0;
        for (float lvl = 1; lvl < cLevel; lvl++){
            cEXP += Math.floor(lvl + 300 * Math.pow(2, lvl / 7));
        }
        for (float lvl = 1; lvl < tLevel; lvl++){
            tEXP += Math.floor(lvl + 300 * Math.pow(2, lvl / 7));
        }
        cEXP /= 4;
        tEXP /= 4;
        currentEXP.setText(Integer.toString((int) cEXP));
        targetEXP.setText(Integer.toString((int) tEXP));
        if(cLevel < 1 || cLevel > 99) {
            currentEXP.setText("0");
        }
        if (tLevel < 1 || tLevel > 99) {
            targetEXP.setText("0");
        }
    }

    /******************************************************************
     * Sets the table to have the correct icons, exp, action name, and
     * quantity, sets table to visible once set.
     *****************************************************************/
    public void calculate(){
        calculateEXP();
        String skillName = skill.toLowerCase();
        SkillActions tableSkill = items.get(skillName);
        SkillAction skillOne = tableSkill.get(0);
        SkillAction skillTwo = tableSkill.get(1);
        SkillAction skillThree = tableSkill.get(2);

        rowOneEXP.setText(Double.toString(skillOne.getExp()));
        rowTwoEXP.setText(Double.toString(skillTwo.getExp()));
        rowThreeEXP.setText(Double.toString(skillThree.getExp()));

        rowOneAction.setText(skillOne.getName());
        rowTwoAction.setText(skillTwo.getName());
        rowThreeAction.setText(skillThree.getName());

        rowOneImage.setImageResource(getResources().getIdentifier(skillOne.getImage(), "drawable", getPackageName()));
        rowTwoImage.setImageResource(getResources().getIdentifier(skillTwo.getImage(), "drawable", getPackageName()));
        rowThreeImage.setImageResource(getResources().getIdentifier(skillThree.getImage(), "drawable", getPackageName()));

        double expRequired = (double)(tEXP - cEXP);
        int skillOneQuantity = (int) Math.ceil(expRequired / skillOne.getExp());
        int skillTwoQuantity = (int) Math.ceil(expRequired / skillTwo.getExp());
        int skillThreeQuantity = (int) Math.ceil(expRequired / skillThree.getExp());

        rowOneQuantity.setText(Integer.toString(skillOneQuantity));
        rowTwoQuantity.setText(Integer.toString(skillTwoQuantity));
        rowThreeQuantity.setText(Integer.toString(skillThreeQuantity));

        TableLayout table = findViewById(R.id.expTableLayout);
        table.setVisibility(View.VISIBLE);
    }


    /******************************************************************
     * Resumes state of activty once paused, keeping numbers and table
     *****************************************************************/
    @Override
    protected void onResume() {
        super.onResume();
        cEXP = savedValues.getFloat("cEXP", cEXP);
        tEXP = savedValues.getFloat("tEXP", tEXP);
        cLevel = savedValues.getInt("cLevel", cLevel);
        tLevel = savedValues.getInt("tLevel", tLevel);
        currentLevel.setText(Integer.toString(cLevel));
        targetLevel.setText(Integer.toString(tLevel));
        calculateEXP();
        calculate();
    }

    /******************************************************************
     * Pauses state of activity, keeping numbers and table
     *****************************************************************/
    @Override
    protected void onPause() {
        SharedPreferences.Editor editor = savedValues.edit();
        editor.putFloat("cEXP", cEXP);
        editor.putFloat("tEXP", tEXP);
        editor.putInt("cLevel", cLevel);
        editor.putInt("tLevel", tLevel);
        editor.commit();
        super.onPause();
    }
}
