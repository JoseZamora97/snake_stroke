package josezamora.robertotoaza.snakestroke.juego.clases;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Obstaculo {

    public int x, y;
    public int tipo;
    public static final int VERTICAL = 0;
    public static final int HORIZONTAL = 1;

    public List<Parte> partes = new ArrayList<>();

    public Obstaculo(int x, int y, int tipo){
        this.x = x;
        this.y = y;
        this.tipo = tipo;

        for( int i = 0; i< new Random().nextInt(5)+1; i++){
            if(tipo == HORIZONTAL){
                partes.add(new Parte(x, y));
                partes.add(new Parte(x+21, y));
                partes.add(new Parte(x+21*i, y));
            }
            if(tipo == VERTICAL){
                partes.add(new Parte(x, y));
                partes.add(new Parte(x, y+21));
                partes.add(new Parte(x, y+21*i));
            }
        }
    }
}
