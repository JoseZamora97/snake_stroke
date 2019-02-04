package josezamora.robertotoaza.snakestroke.implementacion;

import android.graphics.Bitmap;

import josezamora.robertotoaza.snakestroke.interfaces.Graficos;
import josezamora.robertotoaza.snakestroke.interfaces.Pixmap;


public class AndroidPixmap implements Pixmap {

    Bitmap bitmap;
    Graficos.PixmapFormat format;

    public AndroidPixmap(Bitmap bitmap, Graficos.PixmapFormat format){
        this.bitmap = bitmap;
        this.format = format;
    }

    @Override
    public int getWidth() {
        return bitmap.getWidth();
    }

    @Override
    public int getHeigth() {
        return bitmap.getHeight();
    }

    @Override
    public Graficos.PixmapFormat getFormat() {
        return format;
    }

    @Override
    public void dispose() {
        bitmap.recycle();
    }
}
