package org.terasology.nui.samples.screens;

import org.terasology.nui.samples.UISample;
import org.terasology.nui.widgets.UIText;

public class TextInputSample extends UISample {
    @Override
    public void init() {
        UIText textInput = new UIText();
        textInput.setMultiline(true);
        textInput.setText("Type something here...");
        content = textInput;
    }

    @Override
    public String getTitle() {
        return "Text Input";
    }

    @Override
    public String getExplanation() {
        return "Try clicking on one of the text areas and type something.";
    }
}
