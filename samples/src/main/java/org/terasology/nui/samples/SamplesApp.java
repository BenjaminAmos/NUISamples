package org.terasology.nui.samples;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import org.joml.Vector2i;
import org.terasology.input.InputType;
import org.terasology.input.MouseInput;
import org.terasology.input.device.CharKeyboardAction;
import org.terasology.input.device.KeyboardDevice;
import org.terasology.input.device.MouseAction;
import org.terasology.input.device.MouseDevice;
import org.terasology.input.device.RawKeyboardAction;
import org.terasology.joml.geom.Rectanglei;
import org.terasology.nui.FocusManager;
import org.terasology.nui.FocusManagerImpl;
import org.terasology.nui.UITextureRegion;
import org.terasology.nui.UIWidget;
import org.terasology.nui.backends.libgdx.LibGDXCanvasRenderer;
import org.terasology.nui.backends.libgdx.LibGDXKeyboardDevice;
import org.terasology.nui.backends.libgdx.LibGDXMouseDevice;
import org.terasology.nui.canvas.CanvasImpl;
import org.terasology.nui.events.NUICharEvent;
import org.terasology.nui.events.NUIKeyEvent;
import org.terasology.nui.events.NUIMouseButtonEvent;
import org.terasology.nui.events.NUIMouseWheelEvent;
import org.terasology.nui.skin.UISkin;
import org.terasology.nui.widgets.UIButton;
import org.terasology.nui.widgets.UILabel;
import org.terasology.nui.widgets.UIText;

import java.util.List;
import java.util.Queue;

public class SamplesApp implements ApplicationListener {
    private LibGDXCanvasRenderer canvasRenderer;
    private FocusManager focusManager;
    private KeyboardDevice keyboardDevice;
    private MouseDevice mouseDevice;
    private UITextureRegion whiteTexture;
    private CanvasImpl canvas;
    private final UISample[] samples = {
            new BasicTextSample(),
            new BasicButtonSample(),
            new RelativeLayoutSample(),
            new MultiplePointersSample(),
            new TextInputSample()
    };
    private UIButton previousButton;
    private UIButton nextButton;
    private UILabel titleLabel;
    private int sampleNo = 0;
    private boolean simulateSecondPointer;
    private SimulatedPointer simulatedPointer;

    @Override
    public void create() {
        canvasRenderer = new LibGDXCanvasRenderer();
        focusManager = new FocusManagerImpl();
        keyboardDevice = new LibGDXKeyboardDevice();
        mouseDevice = new LibGDXMouseDevice();

        whiteTexture = Assets.getTexture("white.png");

        UISkin skin = DefaultSkins.getDefaultSkin();

        canvas = new CanvasImpl(canvasRenderer, focusManager, keyboardDevice, mouseDevice, whiteTexture, skin, 100);

        UIText.DEFAULT_CURSOR_TEXTURE = whiteTexture;

        // TODO: Re-enabled button click sounds when a better sound is found.
        //UIButton.DEFAULT_CLICK_SOUND = Assets.getSound("click.wav");

        previousButton = new UIButton("previousButton", "Previous");
        previousButton.subscribe(widget -> {
            if (sampleNo-- <= 0) {
                sampleNo = samples.length - 1;
            }
            updateTitleLabel();
        });

        nextButton = new UIButton("nextButton", "Next");
        nextButton.subscribe(widget -> {
            sampleNo = (sampleNo + 1) % samples.length;
            updateTitleLabel();
        });

        titleLabel = new UILabel("titleLabel");
        updateTitleLabel();

        for (UISample sample : samples) {
            sample.init();
        }

        simulatedPointer = new SimulatedPointer(new Vector2i(Gdx.graphics.getBackBufferWidth() / 2,
                Gdx.graphics.getBackBufferHeight() / 2),
                Assets.getTexture("cursor.png"));
    }

    @Override
    public void resize(int width, int height) {
        canvasRenderer.resize(width, height);
    }

    @Override
    public void render() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.P)) {
            simulateSecondPointer = !simulateSecondPointer;
        }

        List<MouseAction> simulatedMouseActions = null;
        if (simulateSecondPointer) {
            simulatedMouseActions = simulatedPointer.update();
        }

        // Explicitly specify pointer to opt-in to multi-touch logic
        // Up to 20 pointers are supported by LibGDX on Android
        Vector2i[] positions = new Vector2i[20];
        for (int pointer = 0; pointer < 20; pointer++) {
            Vector2i position = mouseDevice.getPosition(pointer);
            if (simulateSecondPointer && pointer == 1) {
                position = simulatedPointer.getPosition();
            }

            if (!position.equals(0, 0)) {
                positions[pointer] = position;
            } else {
                // Set the pointer to an off-screen location, so it acts as if it were not present.
                positions[pointer] = new Vector2i(Integer.MAX_VALUE, Integer.MAX_VALUE);
            }
        }
        canvas.processMousePosition(positions);

        canvas.setGameTime(System.currentTimeMillis());

        UIWidget currentSample = samples[sampleNo];

        for (RawKeyboardAction action : keyboardDevice.getInputQueue()) {
            NUIKeyEvent event = new NUIKeyEvent(mouseDevice, keyboardDevice, action.getInput(), action.getState());

            if (focusManager.getFocus() != null) {
                focusManager.getFocus().onKeyEvent(event);
            }

            if (currentSample.onKeyEvent(event) || event.isConsumed()) {
                break;
            }
        }

        for (CharKeyboardAction action : keyboardDevice.getCharInputQueue()) {
            NUICharEvent event = new NUICharEvent(mouseDevice, keyboardDevice, action.getCharacter());

            if (focusManager.getFocus() != null) {
                focusManager.getFocus().onCharEvent(event);
            }

            if (currentSample.onCharEvent(event) || event.isConsumed()) {
                break;
            }
        }

        Queue<MouseAction> mouseActions = mouseDevice.getInputQueue();
        if (simulateSecondPointer) {
            mouseActions.addAll(simulatedMouseActions);
        }

        for (MouseAction action : mouseActions) {
            if (action.getInput().getType() == InputType.MOUSE_BUTTON) {
                if (action.getState().isDown()) {
                    canvas.processMouseClick((MouseInput) action.getInput(), action.getMousePosition(), action.getPointer());
                } else {
                    canvas.processMouseRelease((MouseInput) action.getInput(), action.getMousePosition(), action.getPointer());
                }

                NUIMouseButtonEvent event = new NUIMouseButtonEvent((MouseInput) action.getInput(), action.getState(), action.getMousePosition());

                if (focusManager.getFocus() != null) {
                    focusManager.getFocus().onMouseButtonEvent(event);
                }

                currentSample.onMouseButtonEvent(event);
                if (event.isConsumed()) {
                    break;
                }
            } else if (action.getInput().getType() == InputType.MOUSE_WHEEL) {
                canvas.processMouseWheel(action.getTurns(), action.getMousePosition());

                NUIMouseWheelEvent event = new NUIMouseWheelEvent(mouseDevice, keyboardDevice, action.getMousePosition(), action.getTurns());

                if (focusManager.getFocus() != null) {
                    focusManager.getFocus().onMouseWheelEvent(event);
                }

                currentSample.onMouseWheelEvent(event);
                if (event.isConsumed()) {
                    break;
                }
            }
        }

        currentSample.update(Gdx.graphics.getDeltaTime());

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT | GL20.GL_STENCIL_BUFFER_BIT);

        canvas.preRender();

        canvas.drawWidget(previousButton, new Rectanglei(0, 0, 128, 128));
        canvas.drawWidget(nextButton, new Rectanglei(Gdx.graphics.getBackBufferWidth() - 128, 0, Gdx.graphics.getBackBufferWidth(), 128));
        canvas.drawWidget(titleLabel, new Rectanglei(128, 0, Gdx.graphics.getBackBufferWidth() - 128, 128));
        canvas.drawWidget(currentSample, new Rectanglei(0, 128, Gdx.graphics.getBackBufferWidth(), Gdx.graphics.getBackBufferHeight()));

        if (simulateSecondPointer) {
            // Draw the simulator pointer last, so that it renders on top of everything.
            simulatedPointer.draw(canvas);
        }

        canvas.postRender();

        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
        }
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
        Assets.dispose();
    }

    private void updateTitleLabel() {
        UISample sample = samples[sampleNo];
        titleLabel.setText(sample.getTitle() + "\n\n" + sample.getExplanation());
    }
}
