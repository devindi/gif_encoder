package ru.studiomobile.tools.gif;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

public class Encoder {

    private int height;
    private int width;
    private int length;
    private int[] pixels;
    private Bitmap bitmap;
    private static final int COLOR = 256;
    private static final int QUALITY = 10;

    /**
     * @Description: encode image to gif
     *
     * @param images	you can change this to paths
     * @param delay	each image's delay in image
     * @param name	generate file in sd card
     *
     * @return void
     */
    public void encode(Context context, int[] images, int delay, String name) {
        bitmap = BitmapFactory.decodeResource(context.getResources(), images[0]);
        width = bitmap.getWidth();
        height = bitmap.getHeight();
        length = images.length;
        bitmap.recycle();
        Log.e("GIF", "ENCODE");
        int state =	init(name, width, height, COLOR, QUALITY, delay / 10);
        if (state != 0) {
            return;
        }
        pixels = new int[width * height];
        for (int i = 0; i < images.length; i++) {
            bitmap = BitmapFactory.decodeResource(context.getResources(), images[i]);
            Log.d("GIF", bitmap.toString());
            if (width < bitmap.getWidth() || height < bitmap.getHeight()) {
                bitmap = Bitmap.createScaledBitmap(bitmap, width, height, true);
            }
            bitmap.getPixels(pixels, 0, width, 0, 0, width, height);
            addFrame(pixels);
            bitmap.recycle();
        }
        close();
    }

    static {
        System.loadLibrary("gifflen");
    }


    /**
     * @Description:
     *
     * @param pixels	pixels of one frame of gif
     * @return
     *
     * @return int
     */
    public native int addFrame(int[] pixels);

    /**
     * @param name
     * @param width
     * @param height
     * @param color
     * @param quality
     * @param delay
     * @return 0-->ok -1--->OutOfMemoryError already thrown -2--->file not exist
     */
    public native int init(String name, int width, int height, int color, int quality, int delay);

    /**
     * @Description:
     *	when finish encode gif ,close the resource
     *
     * @return void
     */
    public native void close();
}