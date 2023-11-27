package parcial1.spendify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PantallaIndex extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_index);

        // ------------ OnClickListener para botones ------------

        // Botón 'Gastos'
        Button botonGastos = findViewById(R.id.boton_gastos);
        botonGastos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirPantallaVerGastos();
            }
        });

        // Botón 'Ver Resumen Mensual'
        Button botonVerResumenMensual = findViewById(R.id.boton_ver_resumen_mensual);
        botonVerResumenMensual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirPantallaVerResumenMensual();
            }
        });
    }

     // ------------ Funciones para abrir pantallas ------------

    // PantallaVerGastos
    private void abrirPantallaVerGastos() {
        Intent intent = new Intent(this, PantallaVerGastos.class);
        startActivity(intent);
    }

    // PantallaVerResumenMensual
    private void abrirPantallaVerResumenMensual() {
        Intent intent = new Intent(this, PantallaVerResumenMensual.class);
        startActivity(intent);
    }

}