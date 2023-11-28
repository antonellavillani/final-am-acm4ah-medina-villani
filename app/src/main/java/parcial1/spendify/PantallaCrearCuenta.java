package parcial1.spendify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class PantallaCrearCuenta extends AppCompatActivity {

    private FirebaseManager firebaseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_crear_cuenta);

        // Inicializar FirebaseManager
        firebaseManager = FirebaseManager.getInstance();

        // Enlace a elementos de la interfaz de usuario
        EditText editTextEmail = findViewById(R.id.texto_ingresar_correo);
        EditText editTextPassword = findViewById(R.id.EditText_ingresar_nueva_contrasena);
        Button buttonRegister = findViewById(R.id.boton_registrar);

        // Evento al hacer clic en el botón de registro
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editTextEmail.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();

                // Validar que el correo electrónico sea válido
                if (!email.contains("@")) {
                    Toast.makeText(PantallaCrearCuenta.this, "Correo electrónico inválido, debe contener un @", Toast.LENGTH_SHORT).show();
                } else {
                    // Registrar al usuario
                    registrarUsuario(email, password);
                }
            }
        });

        // Estilo de tiempo de ejecución: agrego una sombra al título "Crear cuenta"
        TextView titulo = findViewById(R.id.titulo_crear_una_cuenta);
        titulo.setShadowLayer(15, 5, 5, Color.BLACK);
    }

    // Función para registrar al usuario
    private void registrarUsuario(String email, String password) {
        firebaseManager.registrarUsuario(email, password, new FirebaseManager.AuthCallback() {
            @Override
            public void onSuccess() {
                // Registro exitoso, el usuario es dirigido a PantallaIngresarSueldo
                Toast.makeText(PantallaCrearCuenta.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                // Redirigir al usuario a PantallaIngresarSueldo
                irAPantallaIngresarSueldo();
            }

            @Override
            public void onFailure(String errorMessage) {
                // Si el registro falla, muestra un mensaje al usuario.
                Toast.makeText(PantallaCrearCuenta.this, "Error al registrar el usuario: " + errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Función para redirigir a PantallaIngresarSueldo
    private void irAPantallaIngresarSueldo() {
        Intent intent = new Intent(this, PantallaIngresarSueldo.class);
        startActivity(intent);
        // Asegúrate de cerrar esta actividad si no deseas que el usuario pueda volver a ella con el botón "Atrás"
        finish();
    }

    // Evento -> link que lleva a la pantalla "Iniciar Sesión"
    public void irAPantallaIniciarSesion(View view) {
        Intent intent = new Intent(this, PantallaIniciarSesion.class);

        // Iniciar la actividad PantallaIniciarSesion
        startActivity(intent);
    }
}
