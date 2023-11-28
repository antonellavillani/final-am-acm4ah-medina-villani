package parcial1.spendify;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class FirebaseManager {
    private static FirebaseManager instance;
    private final FirebaseFirestore firestore;
    private final FirebaseAuth mAuth;

    private FirebaseManager() {
        // Constructor privado para evitar instanciación directa
        firestore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
    }

    public static synchronized FirebaseManager getInstance() {
        if (instance == null) {
            instance = new FirebaseManager();
        }
        return instance;
    }

    public FirebaseFirestore getFirestoreInstance() {
        return firestore;
    }

    public FirebaseAuth getAuthInstance() {
        return mAuth;
    }

    public void registrarUsuario(String email, String password, AuthCallback callback) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        callback.onSuccess();
                    } else {
                        callback.onFailure(task.getException().getMessage());
                    }
                });
    }

    public FirebaseUser getCurrentUser() {
        return mAuth.getCurrentUser();
    }

    public void agregarGasto(String userEmail, String tipoGasto, String monto) {
        // Obtener referencia a la colección de gastos para el usuario
        CollectionReference gastosCollection = firestore.collection("usuarios").document(userEmail).collection("gastos");

        // Crear un mapa con los datos del gasto
        Map<String, Object> gasto = new HashMap<>();
        gasto.put("monto", monto);

        // Agregar el documento a la colección de gastos con el tipo de gasto como ID
        gastosCollection.document(tipoGasto).set(gasto);
    }

    public void eliminarGasto(String userEmail, String tipoGasto) {
        // Obtener referencia a la colección de gastos para el usuario
        CollectionReference gastosCollection = firestore.collection("usuarios").document(userEmail).collection("gastos");

        // Eliminar el documento correspondiente al tipo de gasto
        gastosCollection.document(tipoGasto).delete();
    }

    public Task<Void> actualizarGasto(String userEmail, String tipoGastoActual, String nuevoTipoGasto, String nuevoMonto) {
        // Obtener referencia al documento del usuario y tipo de gasto actual
        DocumentReference gastoRef = firestore.collection("usuarios").document(userEmail).collection("gastos").document(tipoGastoActual);

        // Crear un mapa con los nuevos datos a actualizar
        Map<String, Object> nuevosDatos = new HashMap<>();
        nuevosDatos.put("tipoGasto", nuevoTipoGasto);
        nuevosDatos.put("monto", nuevoMonto);

        // Actualizar los datos en Firestore
        return gastoRef.update(nuevosDatos);
    }

    public interface AuthCallback {
        void onSuccess();

        void onFailure(String errorMessage);
    }
}