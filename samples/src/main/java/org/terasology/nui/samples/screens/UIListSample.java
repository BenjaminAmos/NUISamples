package org.terasology.nui.samples.screens;

import org.terasology.nui.samples.UISample;
import org.terasology.nui.widgets.UIList;

import java.util.Arrays;

public class UIListSample extends UISample {
    @Override
    public void init() {
        UIList<String> list = new UIList<String>();
        list.setList(Arrays.asList("Test", "Test 2", "Test 3", "Test 4"));
        content = list;
    }

    @Override
    public String getTitle() {
        return "UI List";
    }

    @Override
    public String getExplanation() {
        return "";
    }
}
