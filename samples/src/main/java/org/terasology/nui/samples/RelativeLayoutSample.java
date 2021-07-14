package org.terasology.nui.samples;

import org.terasology.nui.HorizontalAlign;
import org.terasology.nui.layouts.relative.HorizontalHint;
import org.terasology.nui.layouts.relative.RelativeLayout;
import org.terasology.nui.layouts.relative.VerticalHint;
import org.terasology.nui.widgets.UIButton;
import org.terasology.nui.widgets.UILabel;

public class RelativeLayoutSample extends UISample {
    @Override
    public void init() {
        UILabel label = new UILabel("label");
        label.setText("Sample App");

        UIButton button = new UIButton("button");
        button.setText("Click me!");
        button.subscribe(widget -> button.setText("Clicked!"));

        RelativeLayout layout = new RelativeLayout();
        layout.addWidget(label,
                HorizontalHint.create()
                        .alignRightRelativeTo("button", HorizontalAlign.LEFT),
                VerticalHint.create()
                        .center());
        layout.addWidget(button,
                HorizontalHint.create()
                        .alignLeft(HorizontalAlign.CENTER),
                VerticalHint.create()
                        .center());
        content = layout;
    }

    @Override
    public String getTitle() {
        return "Relative Layout";
    }

    @Override
    public String getExplanation() {
        return "";
    }
}
