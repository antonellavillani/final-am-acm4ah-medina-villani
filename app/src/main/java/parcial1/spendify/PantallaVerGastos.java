package parcial1.spendify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PantallaVerGastos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_ver_gastos);

        Button botonVolver = findViewById(R.id.boton_volver);

        botonVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                volverAPantallaIndex();
            }
        });
    }

    private void volverAPantallaIndex() {
        Intent intent = new Intent(this, PantallaIndex.class);
        startActivity(intent);
        finish(); // Cierra la actividad actual para que no quede en la pila de actividades
    }
}