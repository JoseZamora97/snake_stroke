package josezamora.robertotoaza.snakestroke.interfaces;


import android.content.Context;

import josezamora.robertotoaza.snakestroke.implementacion.Pantalla;

public interface Juego {
    public Input getInput();
    public FileIO getFileIO();
    public Graficos getGraphics();
    public Audio getAudio();
    public void setScreen(Pantalla pantalla);
    public Pantalla getCurrentScreen();
    public Pantalla getStartScreen();

    public Context obtenContext();

    void setPuntuaciones(int i);
    int getPuntuaciones();

}
