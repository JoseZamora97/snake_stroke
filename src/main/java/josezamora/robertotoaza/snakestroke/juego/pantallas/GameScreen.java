package josezamora.robertotoaza.snakestroke.juego.pantallas;


import java.util.List;

import josezamora.robertotoaza.snakestroke.implementacion.Pantalla;
import josezamora.robertotoaza.snakestroke.interfaces.Graficos;
import josezamora.robertotoaza.snakestroke.interfaces.Input;
import josezamora.robertotoaza.snakestroke.interfaces.Juego;
import josezamora.robertotoaza.snakestroke.juego.clases.Assets;
import josezamora.robertotoaza.snakestroke.juego.clases.Comida;
import josezamora.robertotoaza.snakestroke.juego.clases.Configuraciones;
import josezamora.robertotoaza.snakestroke.juego.clases.Obstaculo;
import josezamora.robertotoaza.snakestroke.juego.clases.Parte;
import josezamora.robertotoaza.snakestroke.juego.clases.Snake;
import josezamora.robertotoaza.snakestroke.juego.clases.World;


public class GameScreen extends Pantalla {


    GameScreen(Juego juego){
        super(juego);
        mundo = new World(juego);
        juego.setPuntuaciones(1);
    }

    enum EstadoJuego { Ready, Running, Paused, Ended }

    private EstadoJuego estado = EstadoJuego.Ready;
    private World mundo;
    private String puntuacion;

    @Override
    public void update(float deltaTime) {
        List<Input.TouchEvent> touchEvents = juego.getInput().getTouchEvents();

        if(estado == EstadoJuego.Ready)
            updateReady(touchEvents);
        if(estado == EstadoJuego.Running)
            updateRunning(touchEvents, deltaTime);
        if(estado == EstadoJuego.Paused)
            updatePaused(touchEvents);
        if(estado == EstadoJuego.Ended)
            updateGameOver(touchEvents);
    }

    boolean cerrar_pulsado;

    private void updateReady(List<Input.TouchEvent> touchEvents) {
        if (touchEvents.size()>0) {
            estado = EstadoJuego.Running;
            if(Configuraciones.sonidoHabilitado)
            Assets.musica_de_fondo.play();
        }
    }
    private void updateRunning(List<Input.TouchEvent> touchEvents, float deltaTime) {
        int len = touchEvents.size();
        for(int i = 0; i < len; i++){

            Input.TouchEvent event = touchEvents.get(i);
            if(event.type == Input.TouchEvent.TOUCH_DOWN){

                if(inBounds(event, 0, 0, 639, 720)){    // LA MITAD IZQUIERDA DE LA PANTALLA
                    mundo.snake.girarIzquierda();
                    if(Configuraciones.sonidoHabilitado)
                        Assets.sonido_boton.play(1);
                    return;
                }

                if(inBounds(event, 640, 0, 640, 720)){    // LA MITAD DERECHA DE LA PANTALLA
                    mundo.snake.girarDerecha();
                    if(Configuraciones.sonidoHabilitado)
                        Assets.sonido_boton.play(1);
                    return;
                }
            }
        }

        mundo.update(deltaTime);

        if(mundo.finalJuego){
            puntuacion = Integer.toString(mundo.puntuacion);
            if(Configuraciones.sonidoHabilitado)
                Assets.sonido_derrota.play(1);
            estado = EstadoJuego.Ended;
        }
    }
    private void updatePaused(List<Input.TouchEvent> touchEvents){
        // vacio, no hay pausas.
    }


    private void updateGameOver(List<Input.TouchEvent> touchEvents){
        if(Assets.musica_de_fondo.isPlaying())
            Assets.musica_de_fondo.stop();

        int len = touchEvents.size();
        for(int i = 0; i<len; i++){

            Input.TouchEvent event = touchEvents.get(i);

            if(event.type == Input.TouchEvent.TOUCH_UP) {
                if (inBounds(event, 566 * 2, 20 * 2, 50 * 2, 50 * 2)) {
                    cerrar_pulsado = !cerrar_pulsado;
                    juego.setScreen(new MainMenuScreen(juego));
                    if (Configuraciones.sonidoHabilitado) {
                        Assets.sonido_boton.play(1);
                    }

                    return;
                }

                if (inBounds(event, 566 * 2, 20 * 2 + 110, 55 * 2, 55 * 2)) {
                    juego.setScreen(new GameScreen(juego));
                    if (Configuraciones.sonidoHabilitado) {
                        Assets.sonido_boton.play(1);
                    }
                    return;
                }
            }

            if(event.type == Input.TouchEvent.TOUCH_DOWN) {
                if (inBounds(event, 566 * 2, 20 * 2, 55 * 2, 55 * 2)) {
                    cerrar_pulsado = !cerrar_pulsado;
                }
            }
        }
    }

    private boolean inBounds(Input.TouchEvent event, int x, int y, int width, int height){
        return event.x > x && event.x < x + width - 1 && event.y > y && event.y < y + height - 1;
    }

    @Override
    public void present(float deltaTime) {
        Graficos g = juego.getGraphics();

        if(mundo.puntuacion<6)
            g.drawPixmap(Assets.fondo_juego_1, 0,0);
        else
        if(mundo.puntuacion < 12)
            g.drawPixmap(Assets.fondo_juego_2, 0,0);
        else
        if(mundo.puntuacion < 18)
            g.drawPixmap(Assets.fondo_juego_3, 0,0);
        else
        if(mundo.puntuacion < 24)
            g.drawPixmap(Assets.fondo_juego_4, 0,0);
        else
        if(mundo.puntuacion < 30)
            g.drawPixmap(Assets.fondo_juego_5, 0,0);
        else
        if(mundo.puntuacion < 36)
            g.drawPixmap(Assets.fondo_juego_6, 0,0);
        else
            g.drawPixmap(Assets.fondo_juego_6, 0,0);

        if(estado == EstadoJuego.Ready)
            drawReadyUI();
        if(estado == EstadoJuego.Running)
            drawRunningUI();
        if(estado == EstadoJuego.Paused)
            drawPausedUI();
        if(estado == EstadoJuego.Ended)
            drawGameOverUI();
    }

    private void drawReadyUI() {
        Graficos g = juego.getGraphics();
        g.drawPixmap(Assets.pantalla_loading, 0 , 0);
    }
    private void drawRunningUI() {
        drawWorld(mundo);
    }
    private void drawPausedUI(){
        Graficos g = juego.getGraphics();
    }
    private void drawGameOverUI(){

        Graficos g = juego.getGraphics();
        g.drawPixmap(Assets.pantalla_game_over, 0, 0);

        drawText(g,puntuacion, 600, 450 );

        if(cerrar_pulsado)
            g.drawPixmap(Assets.boton_cerrar, 566 * 2, 20 * 2, 0, 100, 95,100);
        else
            g.drawPixmap(Assets.boton_cerrar, 566 * 2, 20 * 2, 0, 100, 95,100);

        g.drawPixmap(Assets.boton_reiniciar, 566 * 2, 20 * 2 + 110);
    }

    private void drawWorld(World mundo) {
        Graficos g = juego.getGraphics();
        Snake snake = mundo.snake;
        Comida comida = mundo.comida;

        if(mundo.puntuacion>=30 && mundo.puntuacion<36)
            g.drawPixmap(Assets.pieza_borde_blanco, comida.x, comida.y);

        else
            g.drawPixmap(Assets.pieza_blanca_1, comida.x, comida.y);

        int len = snake.partes.size();
        for(int i = 0; i < len ; i++)
        {
            Parte part = snake.partes.get(i);
            if(mundo.puntuacion>=30 && mundo.puntuacion<36)
                g.drawPixmap(Assets.pieza_blanca_full,part.x, part.y);

            else
                g.drawPixmap(Assets.pieza_negra, part.x, part.y);

        }

        List<Obstaculo> obs = mundo.obstaculoList;

        int l= obs.size();
        for(int j = 0; j<l; j++){

            Obstaculo o = obs.get(j);

            int lenO = o.partes.size();
            for(int i = 0; i < lenO ; i++)
            {
                Parte part = o.partes.get(i);
                if(mundo.puntuacion>=30 && mundo.puntuacion<36)
                    g.drawPixmap(Assets.pieza_blanca_full, part.x, part.y);

                else
                    g.drawPixmap(Assets.pieza_negra, part.x, part.y);
            }
        }
    }

    private void drawText(Graficos g, String line, int x, int y){

        int len = line.length();
        for(int i = 0; i<len ; i++){
            switch (line.charAt(i)){
                case '0':
                    g.drawPixmap(Assets.numeros, x, y, 0, 0, 45, 43 );
                    break;
                case '1':
                    g.drawPixmap(Assets.numeros, x, y, 35, 0, 45, 43 );
                    break;
                case '2':
                    g.drawPixmap(Assets.numeros, x, y, 70, 0, 45, 43 );
                    break;
                case '3':
                    g.drawPixmap(Assets.numeros, x, y, 120, 0, 45, 43 );
                    break;
                case '4':
                    g.drawPixmap(Assets.numeros, x, y, 150, 0, 45, 43 );
                    break;
                case '5':
                    g.drawPixmap(Assets.numeros, x, y, 196, 0, 45, 43 );
                    break;
                case '6':
                    g.drawPixmap(Assets.numeros, x, y, 240, 0, 45, 43 );
                    break;
                case '7':
                    g.drawPixmap(Assets.numeros, x, y, 280, 0, 45, 43 );
                    break;
                case '8':
                    g.drawPixmap(Assets.numeros, x, y, 320, 0, 45, 43 );
                    break;
                case '9':
                    g.drawPixmap(Assets.numeros, x, y, 360, 0, 40, 43);
                    break;
                case '.':
                    g.drawPixmap(Assets.numeros, x, y, 0, 0, 45, 43 );
                    break;
            }

            x+=45;
        }
    }

    @Override
    public void pause() {
        if(estado == EstadoJuego.Running)
            estado = EstadoJuego.Ended;
        if(mundo.finalJuego){
            Configuraciones.addScore(mundo.puntuacion);
            Configuraciones.save(juego.getFileIO());

        }
        Assets.musica_de_fondo.stop();
    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }

}
