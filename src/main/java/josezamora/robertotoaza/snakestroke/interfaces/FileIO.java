package josezamora.robertotoaza.snakestroke.interfaces;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface FileIO {
    public InputStream leerAsset(String nombre) throws IOException;
    public InputStream leerArchivo(String nombre) throws IOException;
    public OutputStream escribir(String nombre) throws IOException;
}
