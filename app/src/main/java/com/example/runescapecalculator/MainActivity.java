package com.example.runescapecalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;


/**********************************************************************
 * The Main Activity for the Runescape Calculator shows the list of
 * skills to select from, and launches the Calculator Activity on
 * click.
 * Icons retrieved from https://github.com/runelite/runelite
 * @author Tristan James
 *********************************************************************/
public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    /** ArrayList to get skills from to put in list and constants */
    private SkillItems items;
    private static final String SKILL = "skill";
    private static final String SKILL_IMAGEVIEW = "skillImage";
    private Toolbar mainToolbar;

    /******************************************************************
     * Creates the activity and generates the list view of skills
     * @param savedInstanceState previous state of activity
     *****************************************************************/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        items = new SkillItems();

        //adding items to list
        items.add(new SkillItem("Fishing", "fishing"));
        items.add(new SkillItem("Smithing", "smithing"));
        items.add(new SkillItem("Mining", "mining"));
        items.add(new SkillItem("Prayer", "prayer"));
        items.add(new SkillItem("Magic", "magic"));
        items.add(new SkillItem("Crafting", "crafting"));
        items.add(new SkillItem("Woodcutting", "woodcutting"));
        items.add(new SkillItem("Firemaking", "firemaking"));
        items.add(new SkillItem("Cooking", "cooking"));
        items.add(new SkillItem("Runecrafting", "runecraft"));

        ArrayList<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();

        //adding items in list to be used in listview
        for (SkillItem item: items){
            HashMap<String, String> map = new HashMap<String, String>();
            map.put(SKILL, item.getSkill());
            map.put(SKILL_IMAGEVIEW, Integer.toString(getResources().getIdentifier(item.getSkillImage(), "drawable", getPackageName())));
            data.add(map);
        }

        //making adapter for listview and mounting it
        SimpleAdapter adapter = new SimpleAdapter(this,
                data,
                R.layout.list_view,
                new String[]{SKILL_IMAGEVIEW, SKILL},
                new int[]{R.id.skillImageView, R.id.skillTextView});
        ListView listView = (ListView)findViewById(R.id.skillListView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);

        mainToolbar = findViewById(R.id.mainToolbar);
        setSupportActionBar(mainToolbar);
    }

    /******************************************************************
     * Generates a Calculator Activity when a skill is selected from
     * the listview, and sends that skill to activity
     * @param adapterView view where adapter is used
     * @param view view clicked on
     * @param position position of item in listview
     * @param l row id of item clicked
     *****************************************************************/
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        SkillItem item = items.get(position);
        Intent intent = new Intent(this, CalculatorActivity.class);
        Bundle extras = new Bundle();
        extras.putString("skill", item.getSkill());
        extras.putString("skillImage", item.getSkillImage());
        intent.putExtras(extras);
        startActivity(intent);
    }
}
