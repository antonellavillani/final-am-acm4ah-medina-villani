package parcial1.spendify;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class ImagenUrl extends AsyncTask<String, Void, Bitmap>{

    private ImageView imageView;

    ImagenUrl(ImageView imageView){
        this.imageView = imageView;
    }

    @Override
    protected Bitmap doInBackground(String... strings) {
        Log.i("Testing", String.valueOf(strings));
        Bitmap bitmap = null;

        try {
            Object content = new URL(strings[0]).getContent();
            InputStream inputStream = (InputStream) content;
            bitmap = BitmapFactory.decodeStream(inputStream);
        } catch (IOException e) {
            Log.i("testing", "Hubo un error");
        }

        return bitmap;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        // Configura la imagen en el ImageView
        if (bitmap != null) {
            imageView.setImageBitmap(bitmap);
        }
    }

}
