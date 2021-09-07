package org.terasology.nui.samples;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import org.terasology.nui.Border;
import org.terasology.nui.Color;
import org.terasology.nui.HorizontalAlign;
import org.terasology.nui.VerticalAlign;
import org.terasology.nui.backends.libgdx.LibGDXFont;
import org.terasology.nui.skin.UISkin;
import org.terasology.nui.skin.UISkinBuilder;
import org.terasology.nui.widgets.UIButton;
import org.terasology.nui.widgets.UIList;
import org.terasology.nui.widgets.UIText;

public final class DefaultSkins {
    private static UISkin defaultSkin = null;

    private DefaultSkins() {
    }

    public static UISkin getDefaultSkin() {
        if (defaultSkin == null) {
            defaultSkin = new UISkinBuilder()
                    .setFont(new LibGDXFont(new BitmapFont()))
                    .setTextColor((Color) Color.white)
                    .setTextShadowColor((Color) Color.black)
                    .setTextHorizontalAlignment(HorizontalAlign.CENTER)
                    .setTextVerticalAlignment(VerticalAlign.MIDDLE)

                    .setElementClass(UIButton.class)
                        .setElementMode(UIButton.DEFAULT_MODE)
                            .setBackground(Assets.getTexture("button/button.png"))
                        .setElementMode(UIButton.HOVER_MODE)
                            .setBackground(Assets.getTexture("button/buttonHover.png"))
                            .setTextColor((Color) Color.yellow)
                        .setElementMode(UIButton.DOWN_MODE)
                            .setBackground(Assets.getTexture("button/buttonDown.png"))
                        .setElementMode(UIButton.DISABLED_MODE)
                            .setBackground(Assets.getTexture("button/buttonDisabled.png"))
                            .setTextColor((Color) Color.grey)

                    .setElementClass(UIList.class)
                        .setElementPart("item")
                            .setElementMode(UIButton.DEFAULT_MODE)
                                .setBackground(Assets.getTexture("button/button.png"))
                            .setElementMode(UIButton.HOVER_MODE)
                                .setBackground(Assets.getTexture("button/buttonHover.png"))
                                .setTextColor((Color) Color.yellow)
                            .setElementMode(UIButton.DOWN_MODE)
                                .setBackground(Assets.getTexture("button/buttonDown.png"))
                            .setElementMode(UIButton.DISABLED_MODE)
                                .setBackground(Assets.getTexture("button/buttonDisabled.png"))
                                .setTextColor((Color) Color.grey)

                    .setElementClass(UIText.class)
                        .setBackground(Assets.getTexture("box.png"))
                        .setBackgroundBorder(new Border(1, 1, 1, 1))
                        .setTextHorizontalAlignment(HorizontalAlign.LEFT)
                        .setTextVerticalAlignment(VerticalAlign.TOP)
                    .build();
        }

        return defaultSkin;
    }
}
