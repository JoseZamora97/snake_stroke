package josezamora.robertotoaza.snakestroke.implementacion;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.view.Window;
import android.view.WindowManager;

import josezamora.robertotoaza.snakestroke.interfaces.Audio;
import josezamora.robertotoaza.snakestroke.interfaces.FileIO;
import josezamora.robertotoaza.snakestroke.interfaces.Graficos;
import josezamora.robertotoaza.snakestroke.interfaces.Input;
import josezamora.robertotoaza.snakestroke.interfaces.Juego;


public abstract class AndroidJuego extends Activity implements Juego {

    AndroidFastRenderView renderView;

    Graficos graficos;
    Audio audio;
    Input input;
    FileIO fileIO;
    Pantalla pantalla;
    WakeLock wakeLock;

    static String nick;
    static int puntos;

    @SuppressLint("InvalidWakeLockTag")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        nick = getIntent().getStringExtra("nick");

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        boolean isLandscape = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
        int frameBufferWidth = isLandscape ? 1280 : 720;
        int frameBufferHeight = isLandscape ? 720 : 1280;

        Bitmap frameBuffer = Bitmap.createBitmap(frameBufferWidth,frameBufferHeight, Bitmap.Config.RGB_565);

        float scaleX = (float) frameBufferWidth
                / getWindowManager().getDefaultDisplay().getWidth();
        float scaleY = (float) frameBufferHeight
                / getWindowManager().getDefaultDisplay().getHeight();

        renderView = new AndroidFastRenderView(this, frameBuffer);
        graficos = new AndroidGraficos(getAssets(), frameBuffer);
        fileIO = new AndroidFileIO(getAssets());
        audio = new AndroidAudio(this);
        input = new AndroidInput(this, renderView, scaleX, scaleY);
        setContentView(renderView);
        pantalla=getStartScreen();

        PowerManager powerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
        wakeLock = powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK,"GlGame");
    }

    @Override
    public void onPause(){
        super.onPause();

        wakeLock.release();
        renderView.pause();
        pantalla.pause();

        if(isFinishing())
            pantalla.dispose();

    }

    @Override
    protected void onResume() {
        super.onResume();
        wakeLock.acquire();
        pantalla.resume();
        renderView.resume();
    }

    @Override
    public Input getInput() {
        return input;
    }

    @Override
    public FileIO getFileIO(){
        return fileIO;
    }

    @Override
    public Graficos getGraphics(){
        return graficos;
    }

    @Override
    public Audio getAudio(){
        return audio;
    }

    @Override
    public Pantalla getCurrentScreen() {
        return pantalla;
    }

    @Override
    public void setScreen(Pantalla pantalla) {
        if(pantalla == null)
            throw new IllegalArgumentException("Pantalla no debe se null");

        this.pantalla.pause();
        this.pantalla.dispose();

        pantalla.resume();
        pantalla.update(0);

        this.pantalla = pantalla;
    }

    public String getNick(){
        return nick;
    }

    public void setPuntuaciones(int i){
        puntos = i;
    }

    public int getPuntuaciones(){
        return puntos;
    }

    public Context obtenContext(){
        return this.getApplicationContext();
    }
}
