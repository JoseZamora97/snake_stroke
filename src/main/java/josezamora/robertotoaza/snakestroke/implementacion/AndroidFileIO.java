package josezamora.robertotoaza.snakestroke.implementacion;

import android.content.res.AssetManager;
import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import josezamora.robertotoaza.snakestroke.interfaces.FileIO;

public class AndroidFileIO implements FileIO {

    AssetManager assetManager;
    String path;

    public AndroidFileIO(AssetManager assetManager){
        this.assetManager = assetManager;
        this.path = Environment.getExternalStorageDirectory()
                .getAbsolutePath()+ File.separator;
    }

    @Override
    public InputStream leerAsset(String nombre) throws IOException {
        return assetManager.open(nombre);
    }

    @Override
    public InputStream leerArchivo(String nombre) throws IOException {
        return new FileInputStream(path+nombre);
    }

    @Override
    public OutputStream escribir(String nombre) throws IOException {
        return new FileOutputStream(path+nombre);
    }
}
