package josezamora.robertotoaza.snakestroke.juego.clases;

import android.graphics.Rect;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import josezamora.robertotoaza.snakestroke.interfaces.Juego;

public class World {
    static final int MUNDO_ANCHO = 1260;
    static final int MUNDO_ALTO = 700;
    static final float TICK_INICIAL = 0.5f;
    static final float TICK_DECREMENTO = 0.05f;

    public Snake snake;
    public Comida comida;
    public boolean finalJuego = false;
    public int puntuacion = 0;
    boolean campos [][] = new boolean[MUNDO_ANCHO][MUNDO_ALTO];
    boolean camposObstaculos [][] = new boolean[MUNDO_ANCHO][MUNDO_ALTO];
    Random random = new Random();
    float tiempoTick = 0;
    static float tick = TICK_INICIAL;

    public List<Obstaculo> obstaculoList = new ArrayList<Obstaculo>();

    public World(Juego juego){
        snake = new Snake();
        colocarComida();
    }

    private void colocarComida(){
        for(int x=0; x<MUNDO_ANCHO; x++){
            for(int y =0 ;y < MUNDO_ALTO; y++){
                campos[x][y] = false;
            }
        }

        int len = snake.partes.size();
        for(int i =0; i <len; i++){
            Parte parte = snake.partes.get(i);
            campos[parte.x][parte.y] = true;
        }

        int botinX = random.nextInt(MUNDO_ANCHO-50)+50;
        int botinY = random.nextInt(MUNDO_ALTO-50)+50;

        while (campos[botinX][botinY]) {
            botinX += 1;
            if (botinX >= MUNDO_ANCHO) {
                botinX = 0;
                botinY += 1;
                if (botinY >= MUNDO_ALTO) {
                    botinY = 0;
                }
            }
        }

        camposObstaculos[botinX][botinY] = true;

        comida = new Comida(botinX, botinY);

        if(puntuacion % 2 == 0){
            if(puntuacion>0){
                int obsX = random.nextInt(MUNDO_ANCHO-26)+26;
                int obsY = random.nextInt(MUNDO_ALTO-26)+26;

                while(camposObstaculos[obsX][obsY]){
                    obsX += 1;
                    if (obsX >= MUNDO_ANCHO-20) {
                        obsX = 0;
                        obsY += 1;
                        if (obsY >= MUNDO_ALTO-20) {
                            obsY = 0;
                        }
                    }
                }

                obstaculoList.add(new Obstaculo(obsX, obsY, random.nextInt(2)));
                if(Configuraciones.sonidoHabilitado)
                    Assets.sonido_obstaculo.play(1);
            }
        }
    }

    public void update(float deltaTime){
        if(finalJuego)
            return;
        tiempoTick  += deltaTime;

        while(tiempoTick > tick){
            tiempoTick -= tick;
            snake.avance();
            if(snake.comprobarChoque()){
                finalJuego = true;
                return;
            }

            if(colisionaConPiezaNegra()){
                finalJuego = true;
                return;
            }

            if(colisionaConPiezaBlanca()){
                if(Configuraciones.sonidoHabilitado)
                    Assets.sonido_exposion.play(1);

                puntuacion ++;
                snake.comer();

                if(snake.partes.size() == MUNDO_ANCHO*MUNDO_ALTO){
                    finalJuego = true;
                    return;
                }else
                    colocarComida();

            }

            if(puntuacion % 10 == 0 && tick-TICK_DECREMENTO > 0){
                tick -= TICK_DECREMENTO;
            }
        }
    }

    private boolean colisionaConPiezaNegra() {
        Parte head = snake.partes.get(0);
        Rect rectA = new Rect(head.x, head.y, head.x+20, head.y+20);
        Rect rectB;

        for(Obstaculo o : obstaculoList){
            for(Parte p: o.partes){
                rectB = new Rect(p.x, p.y, p.x+20, p.y+20);
                if(rectA.intersect(rectB))
                    return true;
            }
        }
        return false;
    }

    private boolean colisionaConPiezaBlanca(){
        Parte head = snake.partes.get(0);
        Rect rectA = new Rect(head.x, head.y, head.x+20, head.y+20);
        Rect rectB = new Rect(comida.x, comida.y, comida.x+20, comida.y+20);

        return rectA.intersect(rectB);
    }

}
