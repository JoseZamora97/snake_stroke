package josezamora.robertotoaza.snakestroke.interfaces;

public interface Musica {

    // Controlar el streaming
    public void play();
    public void stop();
    public void pause();

    /* Se ejecuta automáticamente cuando llegue al
       final del streaming */
    public void setLooping(boolean looping);

    // Configura el volumen: 0 = silencio y 1 = max.
    public void setVolume(float volume);

    /*Estado de la musica */
    public boolean isPlaying();
    public boolean isStopped();
    public boolean isLooping();

    /* Liberar los recursos */
    public void dispose();

}
