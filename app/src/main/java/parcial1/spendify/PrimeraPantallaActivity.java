package parcial1.spendify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PrimeraPantallaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primera_pantalla);

        // Evento --> botón comenzar lleva a la pantalla de crear una cuenta
        Button botonComenzar = findViewById(R.id.boton_comenzar);

        botonComenzar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crear una intención para iniciar la actividad PantallaCrearCuenta
                Intent intent = new Intent(PrimeraPantallaActivity.this, PantallaCrearCuenta.class);

                // Iniciar la actividad PantallaCrearCuenta
                startActivity(intent);
            }
        });
    }
}