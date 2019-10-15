package com.example.runescapecalculator;


import java.util.HashMap;

/**********************************************************************
 * Creates a Hashmap holding SkillActions, which holds SkillActions.
 * Instantiates the Hashmap with SkillActions for each of the ten
 * skills and the actions for each one. For use in displaying table in
 * Calculator Activity.
 * @author Tristan James
 *********************************************************************/
public class SkillActionItems extends HashMap<String, SkillActions> {
    /** SkillActions to get for table */
    private SkillActions fishing, woodcutting, cooking, mining, smithing, runecrafting, firemaking, crafting, prayer, magic;

    public SkillActionItems(){
        fishing = new SkillActions();
        woodcutting = new SkillActions();
        cooking = new SkillActions();
        mining = new SkillActions();
        smithing = new SkillActions();
        runecrafting = new SkillActions();
        firemaking = new SkillActions();
        crafting = new SkillActions();
        prayer = new SkillActions();
        magic = new SkillActions();

        //adding actions to SkillActions
        fishing.add(new SkillAction("Raw Swordfish", 100, "raw_swordfish"));
        fishing.add(new SkillAction("Raw Tuna", 80, "raw_tuna"));
        fishing.add(new SkillAction("Raw Trout", 50, "raw_trout"));

        woodcutting.add(new SkillAction("Yew Logs", 175, "yew_logs"));
        woodcutting.add(new SkillAction("Maple Logs", 100, "maple_logs"));
        woodcutting.add(new SkillAction("Oak Logs", 37.5f, "oak_logs"));

        cooking.add(new SkillAction("Chocolate Cake", 210, "chocolate_cake"));
        cooking.add(new SkillAction("Stew", 117, "stew"));
        cooking.add(new SkillAction("Trout", 70, "trout"));

        mining.add(new SkillAction("Runite Ore", 125, "runite_ore"));
        mining.add(new SkillAction("Mithril Ore", 80, "mithril_ore"));
        mining.add(new SkillAction("Iron Ore", 35, "iron_ore"));

        smithing.add(new SkillAction("Rune Platebody", 375, "rune_platebody"));
        smithing.add(new SkillAction("Steel Platebody", 187.5f, "steel_platebody"));
        smithing.add(new SkillAction("Iron Full Helm", 50, "iron_full_helm"));

        runecrafting.add(new SkillAction("Body Rune", 7.5f, "body_rune"));
        runecrafting.add(new SkillAction("Water Rune", 6, "water_rune"));
        runecrafting.add(new SkillAction("Air Rune", 5, "air_rune"));

        firemaking.add(new SkillAction("Yew Logs", 202.5f, "yew_logs"));
        firemaking.add(new SkillAction("Maple Logs", 135, "maple_logs"));
        firemaking.add(new SkillAction("Oak Logs", 60, "oak_logs"));

        crafting.add(new SkillAction("Diamond", 107.5f, "diamond"));
        crafting.add(new SkillAction("Emerald Necklace", 60, "emerald_necklace"));
        crafting.add(new SkillAction("Leather Chaps", 27, "leather_chaps"));

        prayer.add(new SkillAction("Big Bones", 15, "big_bones"));
        prayer.add(new SkillAction("Monkey Bones", 5, "monkey_bones"));
        prayer.add(new SkillAction("Bones", 4.5f, "bones"));

        magic.add(new SkillAction("High Level Alchemy", 65, "high_level_alchemy"));
        magic.add(new SkillAction("Varrock Teleport", 35, "varrock_teleport"));
        magic.add(new SkillAction("Bones to Bananas", 25, "bones_to_bananas"));

        //adding to hashmap
        put("fishing", fishing);
        put("woodcutting", woodcutting);
        put("cooking", cooking);
        put("mining", mining);
        put("smithing", smithing);
        put("runecrafting", runecrafting);
        put("firemaking", firemaking);
        put("crafting", crafting);
        put("prayer", prayer);
        put("magic", magic);
    }
}
