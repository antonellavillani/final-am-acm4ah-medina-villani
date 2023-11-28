package parcial1.spendify;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseUser;

import java.util.HashMap;
import java.util.Map;

public class PantallaIngresarSueldo extends AppCompatActivity {

    private Button botonSiguiente;
    private EditText editTextSueldo;
    private FirebaseManager firebaseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_ingresar_sueldo);

        firebaseManager = FirebaseManager.getInstance();

        editTextSueldo = findViewById(R.id.EditText_ingresar_sueldo);
        botonSiguiente = findViewById(R.id.boton_siguiente);

        // Deshabilitar el botón "Siguiente" por defecto
        botonSiguiente.setEnabled(false);

        // Configuración del listener para cambios en el EditText
        editTextSueldo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {
                // No se necesita implementar
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                // No se necesita implementar
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // Habilitar el botón "Siguiente" si el EditText no está vacío
                botonSiguiente.setEnabled(!editable.toString().isEmpty());
            }
        });

        // Configurar el listener para el botón "Siguiente"
        botonSiguiente.setOnClickListener(v -> {
            // Verificar si el EditText tiene un valor
            if (editTextSueldo.getText().toString().isEmpty()) {
                // Mostrar Toast si el EditText está vacío
                Toast.makeText(PantallaIngresarSueldo.this, "Ingresa tu sueldo", Toast.LENGTH_SHORT).show();
            } else {
                // Obtener el sueldo ingresado por el usuario
                double sueldo = Double.parseDouble(editTextSueldo.getText().toString());

                // Obtener el usuario actualmente autenticado utilizando FirebaseManager
                FirebaseUser currentUser = FirebaseManager.getInstance().getCurrentUser();

                // Verificar si el usuario está autenticado
                if (currentUser != null) {
                    // Obtener el correo electrónico del usuario
                    String userEmail = currentUser.getEmail();

                    // Guardar el sueldo en Firestore con el correo electrónico como userId
                    Map<String, Object> usuario = new HashMap<>();
                    usuario.put("sueldo", sueldo);

                    // Agregar el documento a la colección "usuarios" con el correo electrónico como ID
                    firebaseManager.getFirestoreInstance().collection("usuarios").document(userEmail).set(usuario);

                    // Ir a PantallaIngresarGastosFijos
                    Intent intent = new Intent(PantallaIngresarSueldo.this, PantallaGastosFijos.class);
                    startActivity(intent);
                } else {
                    // El usuario no está autenticado
                    Toast.makeText(PantallaIngresarSueldo.this, "Error: Usuario no autenticado", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}