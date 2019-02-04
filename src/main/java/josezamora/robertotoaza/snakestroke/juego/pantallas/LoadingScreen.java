package josezamora.robertotoaza.snakestroke.juego.pantallas;


import josezamora.robertotoaza.snakestroke.implementacion.Pantalla;
import josezamora.robertotoaza.snakestroke.interfaces.Graficos;
import josezamora.robertotoaza.snakestroke.interfaces.Juego;
import josezamora.robertotoaza.snakestroke.juego.clases.Assets;
import josezamora.robertotoaza.snakestroke.juego.clases.Configuraciones;

public class LoadingScreen extends Pantalla {

    public LoadingScreen(Juego juego){
        super(juego);
    }

    @Override
    public void update(float deltaTime){
        Graficos g = juego.getGraphics();

        // Botones
        Assets.boton_play = g.newPixmap("boton_play.png", Graficos.PixmapFormat.ARGB4444);
        Assets.boton_ayuda = g.newPixmap("boton_ayuda.png", Graficos.PixmapFormat.ARGB4444);
        Assets.boton_ayuda_pulsado = g.newPixmap("boton_ayuda_pulsado.png", Graficos.PixmapFormat.ARGB4444);
        Assets.boton_config_sonido = g.newPixmap("boton_config_sonido.png", Graficos.PixmapFormat.ARGB4444);
        Assets.boton_cerrar = g.newPixmap("boton_cerrar.png", Graficos.PixmapFormat.ARGB4444);
        Assets.boton_reiniciar = g.newPixmap("boton_reiniciar.png", Graficos.PixmapFormat.ARGB4444);

        // Actores
        Assets.pieza_blanca_1 = g.newPixmap("pieza_blanca_1.png", Graficos.PixmapFormat.ARGB4444);
        Assets.pieza_negra = g.newPixmap("pieza_negra.png", Graficos.PixmapFormat.ARGB4444);
        Assets.pieza_blanca_full = g.newPixmap("pieza_blanca_full.png", Graficos.PixmapFormat.ARGB4444);
        Assets.pieza_borde_blanco = g.newPixmap("pieza_borde_blanco.png", Graficos.PixmapFormat.ARGB4444);

        // Letras
        Assets.numeros = g.newPixmap("numeros.png", Graficos.PixmapFormat.ARGB4444);

        // fondo
        Assets.fondo_juego_1 = g.newPixmap("fondo_juego_1.png", Graficos.PixmapFormat.ARGB4444);
        Assets.fondo_juego_2 = g.newPixmap("fondo_juego_2.png", Graficos.PixmapFormat.ARGB4444);
        Assets.fondo_juego_3 = g.newPixmap("fondo_juego_3.png", Graficos.PixmapFormat.ARGB4444);
        Assets.fondo_juego_4 = g.newPixmap("fondo_juego_4.png", Graficos.PixmapFormat.ARGB4444);
        Assets.fondo_juego_5 = g.newPixmap("fondo_juego_5.png", Graficos.PixmapFormat.ARGB4444);
        Assets.fondo_juego_6 = g.newPixmap("fondo_juego_6.png", Graficos.PixmapFormat.ARGB4444);


        // Pantalla
        Assets.pantalla_ayuda = g.newPixmap("pantalla_ayuda.png", Graficos.PixmapFormat.ARGB4444);
        Assets.pantalla_game_over =  g.newPixmap("pantalla_game_over.png", Graficos.PixmapFormat.ARGB4444);
        Assets.pantalla_loading = g.newPixmap("pantalla_loading.png", Graficos.PixmapFormat.ARGB4444);
        Assets.pantalla_main_menu = g.newPixmap("pantalla_main_menu.png", Graficos.PixmapFormat.ARGB4444);

        // Sonido
        Assets.sonido_boton = juego.getAudio().nuevoSonido("pulsar.ogg");
        Assets.sonido_exposion = juego.getAudio().nuevoSonido("explosion.ogg");
        Assets.sonido_derrota = juego.getAudio().nuevoSonido("perder.ogg");
        Assets.sonido_obstaculo = juego.getAudio().nuevoSonido("sonido_obstaculo.ogg");

        // Musica
        Assets.musica_de_fondo = juego.getAudio().nuevaMusica("musica_de_fondo.ogg");

        Configuraciones.cargar(juego.getFileIO());
        juego.setScreen(new MainMenuScreen(juego));
    }

    @Override
    public void present(float deltaTime) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void dispose() {}

}
