package josezamora.robertotoaza.snakestroke.implementacion;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;

import java.io.IOException;

import josezamora.robertotoaza.snakestroke.interfaces.Audio;
import josezamora.robertotoaza.snakestroke.interfaces.Musica;
import josezamora.robertotoaza.snakestroke.interfaces.Sonido;

public class AndroidAudio implements Audio {

    private AssetManager assetManager;
    private SoundPool soundPool;

    public AndroidAudio (Activity activity){
        activity.setVolumeControlStream(AudioManager.STREAM_MUSIC);
        this.assetManager = activity.getAssets();
        this.soundPool = new SoundPool(20,AudioManager.STREAM_MUSIC, 0);
    }

    @Override
    public Musica nuevaMusica(String nombre) {
        try {
            AssetFileDescriptor assetFileDescriptor = assetManager.openFd(nombre);
            return new AndroidMusica(assetFileDescriptor);
        } catch (IOException e){
            throw new RuntimeException("No se ha podido cargar '"+nombre+"'");
        }
    }

    @Override
    public Sonido nuevoSonido(String nombre) {
        try {
            AssetFileDescriptor assetFileDescriptor = assetManager.openFd(nombre);
            int soundId = soundPool.load(assetFileDescriptor, 0);
            return new AndroidSonido(soundPool, soundId);
        } catch (IOException e) {
            throw new RuntimeException("No se ha podido cargar '"+nombre+"'");
        }
    }
}
