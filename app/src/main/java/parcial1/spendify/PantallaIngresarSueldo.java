package parcial1.spendify;

import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

public class PantallaIngresarSueldo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_ingresar_sueldo);

        // Obtener referencia al RadioGroup
        RadioGroup radioGroupOpciones = findViewById(R.id.radioGroupOpciones);

        // Configuración del listener para los cambios en el RadioGroup
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
            }
        });
    }
}
