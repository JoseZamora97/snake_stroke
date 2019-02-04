package josezamora.robertotoaza.snakestroke.juego.pantallas;

import java.util.List;

import josezamora.robertotoaza.snakestroke.implementacion.Pantalla;
import josezamora.robertotoaza.snakestroke.interfaces.Graficos;
import josezamora.robertotoaza.snakestroke.interfaces.Input;
import josezamora.robertotoaza.snakestroke.interfaces.Juego;
import josezamora.robertotoaza.snakestroke.juego.clases.Assets;
import josezamora.robertotoaza.snakestroke.juego.clases.Configuraciones;

public class HelpScreen extends Pantalla {

    boolean play_pulsado = false;

    public HelpScreen(Juego juego) {
        super(juego);
    }

    @Override
    public void update(float deltaTime) {
        Graficos g = juego.getGraphics();

        List<Input.TouchEvent> touchEvents = juego.getInput().getTouchEvents();

        juego.getInput().getKeyEvents();

        int len = touchEvents.size();

        for(int i = 0; i<len; i++) {

            Input.TouchEvent event = touchEvents.get(i);

            if (event.type == Input.TouchEvent.TOUCH_UP) {
                if (inBounds(event, 586*2, 295*2, 60*2, 44*2)) {
                    play_pulsado = !play_pulsado;
                    juego.setScreen(new MainMenuScreen(juego));
                    if (Configuraciones.sonidoHabilitado)
                        Assets.sonido_boton.play(1);
                }
                if (event.type == Input.TouchEvent.TOUCH_DOWN) {
                    if (inBounds(event, 586*2, 295, 34*2, 45*2))
                        play_pulsado = !play_pulsado;
                }
            }
        }
    }

    public boolean inBounds(Input.TouchEvent event , int x, int y, int width, int height){
        if(event.x > x && event.x < x+width-1 && event.y>y && event.y < y+height-1)
            return true;
        else
            return false;
    }

    @Override
    public void present(float deltaTime) {
        Graficos g = juego.getGraphics();

        g.drawPixmap(Assets.pantalla_ayuda, 0, 0);

        if(play_pulsado)
            g.drawPixmap(Assets.boton_play, 586*2-50,295*2-90, 0, 152, 107,152);
        else
            g.drawPixmap(Assets.boton_play, 586*2-50,295*2-90, 0, 0, 107,152);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
