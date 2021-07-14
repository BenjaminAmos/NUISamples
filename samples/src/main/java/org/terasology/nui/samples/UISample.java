package org.terasology.nui.samples;

import org.joml.Vector2i;
import org.terasology.nui.AbstractWidget;
import org.terasology.nui.Canvas;
import org.terasology.nui.UIWidget;

import java.util.Collections;
import java.util.Iterator;

public abstract class UISample extends AbstractWidget {
    protected UIWidget content;

    public abstract void init();
    public abstract String getTitle();
    public abstract String getExplanation();

    @Override
    public void onDraw(Canvas canvas) {
        canvas.drawWidget(content);
    }

    @Override
    public Vector2i getPreferredContentSize(Canvas canvas, Vector2i sizeHint) {
        return canvas.size();
    }

    @Override
    public Vector2i getMaxContentSize(Canvas canvas) {
        return canvas.size();
    }

    @Override
    public Iterator<UIWidget> iterator() {
        if (content == null) {
            return Collections.emptyIterator();
        }

        return Collections.singleton(content).iterator();
    }
}
