package org.terasology.nui.samples.screens;

import org.terasology.nui.Color;
import org.terasology.nui.HorizontalAlign;
import org.terasology.nui.VerticalAlign;
import org.terasology.nui.layouts.relative.HorizontalHint;
import org.terasology.nui.layouts.relative.RelativeLayout;
import org.terasology.nui.layouts.relative.VerticalHint;
import org.terasology.nui.samples.DefaultSkins;
import org.terasology.nui.samples.UISample;
import org.terasology.nui.skin.UISkinBuilder;
import org.terasology.nui.widgets.UIButton;

public class MultiplePointersSample extends UISample {
    @Override
    public void init() {
        setSkin(new UISkinBuilder()
                .setBaseSkin(DefaultSkins.getDefaultSkin())
                .setElementClass(UIButton.class)
                .setElementMode(UIButton.DOWN_MODE)
                .setTextColor((Color) Color.red)
                .build());

        RelativeLayout layout = new RelativeLayout();
        layout.addWidget(new UIButton("topLeftButton", "Top Left"),
                HorizontalHint.create()
                        .alignLeft()
                        .alignRight(HorizontalAlign.CENTER),
                VerticalHint.create()
                        .alignTop()
                        .alignBottom(VerticalAlign.MIDDLE)
        );
        layout.addWidget(new UIButton("topRightButton", "Top Right"),
                HorizontalHint.create()
                        .alignRight()
                        .alignLeft(HorizontalAlign.CENTER),
                VerticalHint.create()
                        .alignTop()
                        .alignBottom(VerticalAlign.MIDDLE)
        );
        layout.addWidget(new UIButton("bottomLeftButton", "Bottom Left"),
                HorizontalHint.create()
                        .alignLeft()
                        .alignRight(HorizontalAlign.CENTER),
                VerticalHint.create()
                        .alignBottom()
                        .alignTop(VerticalAlign.MIDDLE)
        );
        layout.addWidget(new UIButton("bottomRightButton", "Bottom Right"),
                HorizontalHint.create()
                        .alignRight()
                        .alignLeft(HorizontalAlign.CENTER),
                VerticalHint.create()
                        .alignBottom()
                        .alignTop(VerticalAlign.MIDDLE)
        );
        content = layout;
    }

    @Override
    public String getTitle() {
        return "Multiple Pointers";
    }

    @Override
    public String getExplanation() {
        return "For this demo, the button text will turn red when held.\n" +
                "Multiple pointers are also supported. Press 'p' to toggle a second on-screen pointer.\n" +
                "Secondary pointer controls:\n" +
                "Movement: Arrow Keys\n" +
                "Pointer Click: Hold and release Enter";
    }
}
