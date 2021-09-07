package org.terasology.nui.samples;

import org.terasology.nui.Color;
import org.terasology.nui.skin.UISkinBuilder;
import org.terasology.nui.widgets.UIButton;

public class ButtonHoldBugSample extends UISample {
    private static final float DISABLE_TOGGLE_INTERVAL = 5f;
    private UIButton button;
    private float timer;

    @Override
    public void init() {
        setSkin(new UISkinBuilder()
                .setBaseSkin(DefaultSkins.getDefaultSkin())
                .setElementClass(UIButton.class)
                .setElementMode(UIButton.DOWN_MODE)
                .setTextColor((Color) Color.red)
                .build());

        button = new UIButton("button", "Press this button");
        content = button;

        timer = DISABLE_TOGGLE_INTERVAL;
    }

    @Override
    public void update(float delta) {
        super.update(delta);

        timer -= delta;
        if (timer <= 0) {
            button.setEnabled(!button.isEnabled());
            timer = DISABLE_TOGGLE_INTERVAL;
        }
    }

    @Override
    public String getTitle() {
        return "Button Hold Bug";
    }

    @Override
    public String getExplanation() {
        return "Hold the button until it is disabled. Then release the button.";
    }
}
