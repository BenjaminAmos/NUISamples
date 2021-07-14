package org.terasology.nui.samples;

import org.terasology.nui.widgets.UILabel;

public class BasicTextSample extends UISample {
    @Override
    public void init() {
        content = new UILabel("TeraNUI (Terasology's New UI Framework) is a canvas based UI framework.\n" +
                "\n" +
                "Its major features are:\n" +
                "\n" +
                "    Canvas providing primitive draw operations for UI, including \"drawing\" interaction regions.\n" +
                "    Skins providing display information, handled through the canvas operations.\n" +
                "    UIWidget system for encapsulating drawing logic into objects\n" +
                "    Skin asset providing the ability to define skins\n" +
                "    UI asset providing the ability to define widget layouts\n" +
                "    Data binding support\n");
    }

    @Override
    public String getTitle() {
        return "Basic Text";
    }

    @Override
    public String getExplanation() {
        return "";
    }
}
