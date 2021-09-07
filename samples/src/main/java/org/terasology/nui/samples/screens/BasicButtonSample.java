package org.terasology.nui.samples.screens;

import org.terasology.nui.samples.UISample;
import org.terasology.nui.widgets.UIButton;

public class BasicButtonSample extends UISample {
    private int clickCount = 0;

    @Override
    public void init() {
        UIButton button = new UIButton("button", "Click me!");
        button.subscribe(widget -> button.setText("You've clicked the button " + ++clickCount + " time(s)!"));
        content = button;
    }

    @Override
    public String getTitle() {
        return "Basic Button";
    }

    @Override
    public String getExplanation() {
        return "";
    }
}
