package parcial1.spendify;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class PantallaVerResumenMensual extends AppCompatActivity {
    private ArrayList<Double> gastosMensuales; // lista de gastos mensuales

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_ver_resumen_mensual);


        // Inicializar lista de gastos mensuales
        gastosMensuales = new ArrayList<>();
        // Ejemplo
        gastosMensuales.add(100.0);
        gastosMensuales.add(150.0);

        // Calcula el total de gastos mensuales
        double totalGastosMensuales = calcularTotalGastosMensuales();

        // Muestra el total de gastos mensuales en el TextView correspondiente
        TextView totalGastosMensualesTextView = findViewById(R.id.total_gastos_mensuales);
        totalGastosMensualesTextView.setText(getString(R.string.formato_total_de_gastos_mensuales, totalGastosMensuales));


        // OnClickListener para el botón "Volver"
        Button botonVolver = findViewById(R.id.boton_volver);
        botonVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                volverAPantallaIndex();
            }
        });

        // Llama a obtenerImagenUrl al iniciar la aplicación
        obtenerImagenUrl(null);
    }

    public void obtenerImagenUrl(View v){
        // referencia del ImageView desde el archivo XML
        ImageView imagenResumenMensual = findViewById(R.id.imagen_ver_resumen_mensual);

        // URL de la imagen
        String url = "https://programacion.net/files/editor/bar-chart.png";

        // tarea asíncrona para cargar la imagen
        ImagenUrl imageUrl = new ImagenUrl(imagenResumenMensual);
        imageUrl.execute(url);
    }


    private double calcularTotalGastosMensuales() {
        double total = 0.0;
        for (Double gasto : gastosMensuales) {
            total += gasto;
        }
        return total;
    }

    private void volverAPantallaIndex() {
        Intent intent = new Intent(this, PantallaIndex.class);
        startActivity(intent);
        finish(); // Cierra la actividad actual para que no quede en la pila de actividades
    }
}
