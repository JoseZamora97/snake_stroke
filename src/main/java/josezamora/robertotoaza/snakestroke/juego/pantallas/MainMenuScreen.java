package josezamora.robertotoaza.snakestroke.juego.pantallas;


import java.util.List;

import josezamora.robertotoaza.snakestroke.implementacion.Pantalla;
import josezamora.robertotoaza.snakestroke.interfaces.Graficos;
import josezamora.robertotoaza.snakestroke.interfaces.Input.TouchEvent;
import josezamora.robertotoaza.snakestroke.interfaces.Juego;
import josezamora.robertotoaza.snakestroke.juego.clases.Assets;
import josezamora.robertotoaza.snakestroke.juego.clases.Configuraciones;

public class MainMenuScreen extends Pantalla {
    public MainMenuScreen(Juego juego){
        super(juego);
    }

    boolean play_pulsado = false;
    boolean ayuda_pulsado = false;

    @Override
    public void update(float deltaTime) {

        Graficos g = juego.getGraphics();

        List<TouchEvent> touchEvents = juego.getInput().getTouchEvents();

        juego.getInput().getKeyEvents();

        int len = touchEvents.size();

        for(int i = 0; i<len; i++){

            TouchEvent event = touchEvents.get(i);

            if(event.type == TouchEvent.TOUCH_UP){
                if(inBounds(event, 20*2, 20*2, 60*2, 44*2)){
                    Configuraciones.sonidoHabilitado = !Configuraciones.sonidoHabilitado;
                    if(Configuraciones.sonidoHabilitado) {
                        Assets.sonido_boton.play(1);
                        Assets.musica_de_fondo.play();
                    }
                    else{
                        if(Assets.musica_de_fondo.isPlaying())
                            Assets.musica_de_fondo.stop();
                    }
                }
                if(inBounds(event, 304*2, 190*2, 34*2, 45*2)){
                    juego.setScreen(new GameScreen(juego));
                    play_pulsado=!play_pulsado;
                    if(Configuraciones.sonidoHabilitado)
                        Assets.sonido_boton.play(1);
                    return;
                }
                if(inBounds(event, 566*2, 20*2, 55*2, 55*2)){
                    juego.setScreen(new HelpScreen(juego));
                    ayuda_pulsado=!ayuda_pulsado;
                    if(Configuraciones.sonidoHabilitado)
                        Assets.sonido_boton.play(1);
                    return;
                }
            }

            if(event.type == TouchEvent.TOUCH_DOWN){
                if(inBounds(event, 304*2, 190*2, 34*2, 45*2)){
                    play_pulsado=!play_pulsado;
                }
                if(inBounds(event, 566*2, 20*2, 55*2, 55*2)){
                    ayuda_pulsado=!ayuda_pulsado;
                }
            }
        }
    }

    public boolean inBounds(TouchEvent event , int x, int y, int width, int height){
        if(event.x > x && event.x < x+width-1 && event.y>y && event.y < y+height-1)
            return true;
        else
            return false;
    }


    @Override
    public void present(float deltaTime) {
        Graficos g = juego.getGraphics();

        g.drawPixmap(Assets.pantalla_main_menu, 0, 0);

        if(Configuraciones.sonidoHabilitado)
            g.drawPixmap(Assets.boton_config_sonido, 20*2, 20*2, 0, 44*2, 60*2, 44*2);
        else
            g.drawPixmap(Assets.boton_config_sonido, 20*2, 20*2, 0, 0, 60*2, 44*2);

        if(play_pulsado)
            g.drawPixmap(Assets.boton_play, 304*2,190*2, 0, 152, 107,152);
        else
            g.drawPixmap(Assets.boton_play, 304*2,190*2, 0, 0, 107,152);

        if(ayuda_pulsado)
            g.drawPixmap(Assets.boton_ayuda, 566*2, 20*2);
        else
            g.drawPixmap(Assets.boton_ayuda_pulsado, 566*2, 20*2);

    }

    @Override
    public void pause() {
        Configuraciones.save(juego.getFileIO());
        Assets.musica_de_fondo.stop();
    }

    @Override
    public void resume() {
        if(Configuraciones.sonidoHabilitado)
        Assets.musica_de_fondo.play();

    }

    @Override
    public void dispose() {

    }


}
