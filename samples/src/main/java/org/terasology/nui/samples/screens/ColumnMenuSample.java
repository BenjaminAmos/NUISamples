package org.terasology.nui.samples.screens;

import com.badlogic.gdx.Gdx;
import org.joml.Vector2i;
import org.terasology.nui.UITextureRegion;
import org.terasology.nui.layouts.ColumnLayout;
import org.terasology.nui.layouts.RowLayout;
import org.terasology.nui.layouts.RowLayoutHint;
import org.terasology.nui.samples.Assets;
import org.terasology.nui.samples.UISample;
import org.terasology.nui.widgets.UIButton;
import org.terasology.nui.widgets.UIImage;
import org.terasology.nui.widgets.UISpace;

public class ColumnMenuSample extends UISample {
    @Override
    public void init() {
        RowLayout rowLayout = new RowLayout();
        rowLayout.addWidget(new UISpace(new Vector2i(1, 1)), new RowLayoutHint().setRelativeWidth(0.2f));

        ColumnLayout columnLayout = new ColumnLayout();
        UITextureRegion banner = Assets.getTexture("banner.png");
        columnLayout.addWidget(new UIImage("banner", banner));

        UIButton button0 = new UIButton("button0", "640x480");
        button0.subscribe(x -> setWindowSize(640, 480));
        columnLayout.addWidget(button0);

        UIButton button1 = new UIButton("button1", "800x600");
        button1.subscribe(x -> setWindowSize(800, 600));
        columnLayout.addWidget(button1);

        UIButton button2 = new UIButton("button2", "1024x768");
        button2.subscribe(x -> setWindowSize(1024, 768));
        columnLayout.addWidget(button2);

        UIButton button3 = new UIButton("button3", "1280x720");
        button3.subscribe(x -> setWindowSize(1280, 720));
        columnLayout.addWidget(button3);

        UIButton button4 = new UIButton("button4", "1920x1080");
        button4.subscribe(x -> setWindowSize(1920, 1080));
        columnLayout.addWidget(button4);

        columnLayout.setVerticalSpacing(8);

        rowLayout.addWidget(columnLayout, new RowLayoutHint().setRelativeWidth(0.6f));

        rowLayout.addWidget(new UISpace(new Vector2i(1, 1)), new RowLayoutHint().setRelativeWidth(0.2f));
        content = rowLayout;
    }

    @Override
    public String getTitle() {
        return "Column Menu";
    }

    @Override
    public String getExplanation() {
        return "";
    }

    private void setWindowSize(int width, int height) {
        Gdx.graphics.setWindowedMode(width, height);
        Gdx.app.getApplicationListener().resize(width, height);
    }
}
