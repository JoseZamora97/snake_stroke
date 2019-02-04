package josezamora.robertotoaza.snakestroke.juego.clases;

import java.util.ArrayList;
import java.util.List;

public class Snake {

    public static final int ARRIBA = 0;
    public static final int IZQUIERDA = 1;
    public static final int ABAJO = 2;
    public static final int DERECHA = 3;

    public static final int VELOCIDAD = 15;

    public List<Parte> partes = new ArrayList<>();
    public int direccion;

    public Snake(){
        direccion = DERECHA;
        partes.add(new Parte(600,300));
    }

    public void girarDerecha(){
        if(direccion == ARRIBA)
           direccion = DERECHA;
        else
        if(direccion == ABAJO)
            direccion = IZQUIERDA;
        else
        if(direccion == DERECHA)
            direccion = ABAJO;

        else
            direccion = ARRIBA;
    }
    public void girarIzquierda(){
        if(direccion == ARRIBA)
            direccion = IZQUIERDA;
        else
        if(direccion == ABAJO)
            direccion = DERECHA;
        else
        if(direccion == DERECHA)
            direccion = ARRIBA;
        else
            direccion = ABAJO;
    }
    public void comer() {
        Parte end = partes.get(partes.size()-1);
        partes.add(new Parte(end.x, end.y));
    }
    public void avance(){
        Parte barco = partes.get(0);

        int len = partes .size()-1;
        for(int i = len; i>0; i--){
            Parte antes = partes.get(i-1);
            Parte parte = partes.get(i);
            parte.x = antes.x;
            parte.y = antes.y;
        }

        if(direccion == ARRIBA)
            barco.y -= VELOCIDAD;
        if(direccion == IZQUIERDA)
            barco.x -= VELOCIDAD;
        if(direccion == ABAJO)
            barco.y += VELOCIDAD;
        if(direccion == DERECHA)
            barco.x += VELOCIDAD;

    }

    public boolean comprobarChoque (){
        int len = partes.size();
        Parte cabeza = partes.get(0);

        if(cabeza.x < 16)
            return true;
        if(cabeza.x>1264)
            return true;
        if(cabeza.y < 16)
            return true;
        if(cabeza.y > 704)
            return true;

        for(int i = 1; i<len; i++){
            Parte parte = partes.get(i);
            if(parte.x == cabeza.x && parte.y == cabeza.y)
                return true;
        }

        return false;
    }


}
