package org.terasology.nui.samples;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import org.terasology.nui.UITextureRegion;
import org.terasology.nui.backends.libgdx.LibGDXTexture;

import java.util.HashMap;
import java.util.Map;

public final class Assets {
    private static final String ASSETS_PATH_PREFIX = "org/terasology/nui/samples/";
    private static final String TEXTURES_PATH = ASSETS_PATH_PREFIX + "textures/";
    private static final String AUDIO_PATH = ASSETS_PATH_PREFIX + "audio/";
    private static final Map<String, LibGDXTexture> textures = new HashMap<>();
    private static final Map<String, Sound> sounds = new HashMap<>();

    private Assets() {
    }

    public static UITextureRegion getTexture(String path) {
        if (textures.containsKey(path)) {
            return textures.get(path);
        }

        LibGDXTexture texture = new LibGDXTexture(new TextureRegion(new Texture(Gdx.files.classpath(TEXTURES_PATH + path))));
        textures.put(path, texture);
        return texture;
    }

    public static org.terasology.nui.asset.Sound getSound(String path) {
        if (sounds.containsKey(path)) {
            Sound sound = sounds.get(path);
            return sound::play;
        }

        Sound sound = Gdx.audio.newSound(Gdx.files.classpath(AUDIO_PATH + path));
        sounds.put(path, sound);
        return sound::play;
    }

    public static void dispose() {
        for (LibGDXTexture texture : textures.values()) {
            texture.getGdxTexture().getTexture().dispose();
        }

        for (Sound sound : sounds.values()) {
            sound.dispose();
        }
    }
}
