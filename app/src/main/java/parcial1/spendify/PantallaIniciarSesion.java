package parcial1.spendify;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class PantallaIniciarSesion extends AppCompatActivity {

    private FirebaseManager firebaseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_iniciar_sesion);

        // Inicializar FirebaseManager
        firebaseManager = FirebaseManager.getInstance();

        // Enlace a elementos de la interfaz de usuario
        EditText editTextEmail = findViewById(R.id.EditText_ingresar_correo);
        EditText editTextPassword = findViewById(R.id.EditText_ingresar_contrasena);
        Button buttonContinuar = findViewById(R.id.boton_continuar);

        // Evento al hacer clic en el botón "Continuar"
        buttonContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editTextEmail.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();

                // Validar que el correo electrónico sea válido
                if (!email.contains("@")) {
                    Toast.makeText(PantallaIniciarSesion.this, "Correo electrónico inválido, debe contener un @", Toast.LENGTH_SHORT).show();
                } else {
                    // Iniciar sesión
                    iniciarSesion(email, password);
                }
            }
        });

        // Estilo de tiempo de ejecución: agrego una sombra al título "Inicia Sesión"
        TextView titulo = findViewById(R.id.titulo_inicia_sesion);
        titulo.setShadowLayer(15, 5, 5, Color.BLACK);
    }

    // Función para iniciar sesión
    private void iniciarSesion(String email, String password) {
        firebaseManager.getAuthInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Inicio de sesión exitoso, el usuario es dirigido a la siguiente pantalla
                        Toast.makeText(PantallaIniciarSesion.this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();
                        irAPantallaSiguiente(email);
                    } else {
                        // Si el inicio de sesión falla, muestra un mensaje al usuario.
                        Toast.makeText(PantallaIniciarSesion.this, "Error al iniciar sesión", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    // Función para redirigir a la siguiente pantalla
    private void irAPantallaSiguiente(String email) {
        Intent intent = new Intent(this, PantallaIngresarSueldo.class);
        intent.putExtra("correoUsuario", email);
        startActivity(intent);
        finish();
    }
}
