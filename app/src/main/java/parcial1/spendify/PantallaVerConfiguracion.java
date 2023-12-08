package parcial1.spendify;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class PantallaVerConfiguracion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_ver_configuracion);

        // OnClickListener para el botón "Modificar Contraseña"
        Button botonModificarContrasena = findViewById(R.id.boton_modificar_contrasena);
        botonModificarContrasena.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarDialogoModificarContrasena();
            }
        });

        // OnClickListener para el botón "Volver"
        Button botonVolver = findViewById(R.id.boton_volver);
        botonVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                volverAPantallaIndex();
            }
        });
    }

    // ---------------------------------------------- Opción "Modificar contraseña" ----------------------------------------------

    // Método para mostrar el diálogo de opción Modificar contraseña
    private void mostrarDialogoModificarContrasena() {
        // Crear el diálogo
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_modificar_contrasena, null);
        builder.setView(dialogView);
        AlertDialog dialog = builder.create();

        // Obtener referencias a los elementos del diálogo
        EditText editTextContrasenaActual = dialogView.findViewById(R.id.editText_contrasena_actual);
        EditText editTextNuevaContrasena = dialogView.findViewById(R.id.editText_contrasena_nueva);
        Button botonAceptar = dialogView.findViewById(R.id.boton_aceptar);

        // Configurar el botón "Aceptar"
        botonAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickAceptar(v);
            }
        });

        // Mostrar el diálogo
        dialog.show();
    }

    // Método para manejar el botón "Aceptar"
    public void onClickAceptar(View view) {
        // Obtener las contraseñas ingresadas
        EditText editTextContrasenaActual = findViewById(R.id.editText_contrasena_actual);
        EditText editTextNuevaContrasena = findViewById(R.id.editText_contrasena_nueva);

        String contrasenaActual = editTextContrasenaActual.getText().toString();
        String nuevaContrasena = editTextNuevaContrasena.getText().toString();

        // Verificar la contraseña actual
        verificarContrasenaActual(contrasenaActual, nuevaContrasena);
    }


    // Método para verificar la contraseña actual antes de cambiarla
    private void verificarContrasenaActual(String contrasenaActual, String nuevaContrasena) {
        FirebaseManager.getInstance().verificarContrasenaActual(contrasenaActual, new FirebaseManager.AuthCallback() {
            @Override
            public void onSuccess() {
                // La contraseña actual es válida, proceder a cambiar la contraseña
                cambiarContrasena(nuevaContrasena);
            }

            @Override
            public void onFailure(String errorMessage) {
                // Mensaje de error si la contraseña actual no es válida
                Toast.makeText(PantallaVerConfiguracion.this, "Error: " + errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Método para cambiar la contraseña utilizando FirebaseManager
    private void cambiarContrasena(String nuevaContrasena) {
        FirebaseManager.getInstance().cambiarContrasena(nuevaContrasena, new FirebaseManager.AuthCallback() {
            @Override
            public void onSuccess() {
                // Mensaje indicando que la contraseña se cambió correctamente
                Toast.makeText(PantallaVerConfiguracion.this, "Contraseña cambiada con éxito", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(String errorMessage) {
                // Mensaje de error
                Toast.makeText(PantallaVerConfiguracion.this, "Error al cambiar la contraseña: " + errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }


    // Método para volver al Index
    private void volverAPantallaIndex() {
        Intent intent = new Intent(this, PantallaIndex.class);
        startActivity(intent);
        finish(); // Cierra la actividad actual para que no quede en la pila de actividades
    }
}