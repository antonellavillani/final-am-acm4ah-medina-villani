package parcial1.spendify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class PantallaVerConsejos extends AppCompatActivity {

    private ScrollView scrollView;
    private LinearLayout linearLayout;
    private int contadorTextos = 1;
    private String[] textos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_ver_consejos);

        scrollView = findViewById(R.id.scrollView);
        linearLayout = findViewById(R.id.linearLayout);
        textos = getResources().getStringArray(R.array.textos);

        // OnClickListener para el botón "Volver"
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

    public void agregarTexto(View view) {
        if (contadorTextos <= textos.length) {
            // Crea un nuevo TextView con el texto correspondiente
            TextView textView = new TextView(this);
            textView.setText(textos[contadorTextos - 1]);

            // Agrega el TextView al LinearLayout
            linearLayout.addView(textView);

            // Desplaza el ScrollView hasta el final para mostrar el nuevo texto
            scrollView.fullScroll(View.FOCUS_DOWN);

            // Incrementa el contador para el próximo click
            contadorTextos++;
//            // Verificar si se ha alcanzado el final del array
//            if (contadorTextos > textos.length) {
//                // Desactivar el botón después de agregar todos los textos
//                Button botonAgregarConsejo = findViewById(R.id.boton_agregar_consejo);
//                botonAgregarConsejo.setEnabled(false);
//            }
        } else {
            // Mostrar un mensaje de error cuando no hay más textos
            Toast.makeText(this, "No hay más textos para agregar", Toast.LENGTH_SHORT).show();
        }
    }

}