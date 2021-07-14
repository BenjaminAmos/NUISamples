package org.terasology.nui.samples;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import org.joml.Math;
import org.joml.Vector2i;
import org.terasology.input.ButtonState;
import org.terasology.input.MouseInput;
import org.terasology.input.device.MouseAction;
import org.terasology.joml.geom.Rectanglei;
import org.terasology.nui.Canvas;
import org.terasology.nui.Color;
import org.terasology.nui.ScaleMode;
import org.terasology.nui.UITextureRegion;

import java.util.ArrayList;
import java.util.List;

public class SimulatedPointer {
    private static final Vector2i SIMULATED_POINTER_SIZE = new Vector2i(16, 16);
    private static final float SIMULATED_POINTER_SPEED = 400;
    private final UITextureRegion cursorTexture;
    private Vector2i position;
    private List<MouseAction> mouseActions = new ArrayList<>();

    public SimulatedPointer(Vector2i position, UITextureRegion cursorTexture) {
        this.position = position;
        this.cursorTexture = cursorTexture;

        InputProcessor inputProcessor = Gdx.input.getInputProcessor();
        if (inputProcessor instanceof InputMultiplexer) {
            ((InputMultiplexer)inputProcessor).addProcessor(new PointerInputProcessor());
        } else {
            Gdx.input.setInputProcessor(new InputMultiplexer(new PointerInputProcessor(), inputProcessor));
        }
    }

    public List<MouseAction> update() {
        float xMotion = 0.0f;
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            xMotion -= Gdx.graphics.getDeltaTime() * SIMULATED_POINTER_SPEED;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            xMotion += Gdx.graphics.getDeltaTime() * SIMULATED_POINTER_SPEED;
        }

        float yMotion = 0.0f;
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            yMotion -= Gdx.graphics.getDeltaTime() * SIMULATED_POINTER_SPEED;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            yMotion += Gdx.graphics.getDeltaTime() * SIMULATED_POINTER_SPEED;
        }
        position.x = Math.clamp(0, Gdx.graphics.getBackBufferWidth(), position.x + (int)xMotion);
        position.y = Math.clamp(0, Gdx.graphics.getBackBufferHeight(), position.y + (int)yMotion);

        List<MouseAction> actions = new ArrayList<>(mouseActions);
        mouseActions.clear();
        return actions;
    }

    public void draw(Canvas canvas) {
        canvas.drawTextureRaw(cursorTexture,
                new Rectanglei(position.x,
                        position.y,
                        position.x + SIMULATED_POINTER_SIZE.x,
                        position.y + SIMULATED_POINTER_SIZE.y),
                Color.white, ScaleMode.STRETCH);
    }

    public Vector2i getPosition() {
        return position;
    }

    private class PointerInputProcessor implements InputProcessor {
        @Override
        public boolean keyDown(int keycode) {
            if (keycode == Input.Keys.ENTER) {
                mouseActions.add(new MouseAction(MouseInput.MOUSE_LEFT, ButtonState.DOWN, position, 1));
                return true;
            }
            return false;
        }

        @Override
        public boolean keyUp(int keycode) {
            if (keycode == Input.Keys.ENTER) {
                mouseActions.add(new MouseAction(MouseInput.MOUSE_LEFT, ButtonState.UP, position, 1));
                return true;
            }
            return false;
        }

        @Override
        public boolean keyTyped(char character) {
            return false;
        }

        @Override
        public boolean touchDown(int screenX, int screenY, int pointer, int button) {
            return false;
        }

        @Override
        public boolean touchUp(int screenX, int screenY, int pointer, int button) {
            return false;
        }

        @Override
        public boolean touchDragged(int screenX, int screenY, int pointer) {
            return false;
        }

        @Override
        public boolean mouseMoved(int screenX, int screenY) {
            return false;
        }

        @Override
        public boolean scrolled(int amount) {
            return false;
        }
    }
}
