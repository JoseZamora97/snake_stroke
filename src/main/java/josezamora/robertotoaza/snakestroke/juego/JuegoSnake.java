package josezamora.robertotoaza.snakestroke.juego;


import josezamora.robertotoaza.snakestroke.implementacion.AndroidJuego;
import josezamora.robertotoaza.snakestroke.implementacion.Pantalla;
import josezamora.robertotoaza.snakestroke.juego.pantallas.LoadingScreen;

public class JuegoSnake extends AndroidJuego {

    @Override
    public Pantalla getStartScreen() {
        return new LoadingScreen(this);
    }
}
