package parcial1.spendify;

import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PantallaIngresarSueldo extends AppCompatActivity {

    private Button botonSiguiente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_ingresar_sueldo);

        RadioGroup radioGroupOpciones = findViewById(R.id.radioGroupOpciones);
        botonSiguiente = findViewById(R.id.boton_siguiente);

        // Deshabilitar el botón "Siguiente" por defecto
        botonSiguiente.setEnabled(false);

        // Configurar el listener para los cambios en el RadioGroup
        radioGroupOpciones.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // Desmarcar todos los botones
                for (int i = 0; i < group.getChildCount(); i++) {
                    RadioButton radioButton = (RadioButton) group.getChildAt(i);
                    radioButton.setChecked(false);
                }

                // Marcar el botón seleccionado
                RadioButton selectedRadioButton = findViewById(checkedId);
                selectedRadioButton.setChecked(true);

                // Habilitar el botón "Siguiente"
                botonSiguiente.setEnabled(true);
            }
        });

        // Configurar el listener para el botón "Siguiente"
        botonSiguiente.setOnClickListener(v -> {
            // Verificar si se ha seleccionado alguna opción
            if (radioGroupOpciones.getCheckedRadioButtonId() == -1) {
                // Mostrar Toast si no se ha seleccionado ninguna opción
                Toast.makeText(PantallaIngresarSueldo.this, "Selecciona una opción", Toast.LENGTH_SHORT).show();
            } else {
                // Guardar la opción seleccionada para usar después
                int opcionSeleccionada = radioGroupOpciones.getCheckedRadioButtonId();
            }
        });
    }
}
