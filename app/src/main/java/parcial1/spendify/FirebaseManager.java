package parcial1.spendify;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.HashMap;
import java.util.Map;

public class FirebaseManager {
    private static FirebaseManager instance;
    private final FirebaseFirestore firestore;
    private final FirebaseAuth mAuth;
    private GastoActual gastoActual;
    private ListenerRegistration gastoListener;

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
        gasto.put("tipoGasto", tipoGasto);
        gasto.put("monto", monto);

        // Agregar el documento a la colección de gastos con el tipo de gasto como ID
        gastosCollection.document(tipoGasto).set(gasto);
    }

    // Método eliminar gasto
    public Task<Void> eliminarGasto(String userEmail, String tipoGasto) {
        // Obtener referencia a la colección de gastos para el usuario
        CollectionReference gastosCollection = firestore.collection("usuarios").document(userEmail).collection("gastos");

        // Eliminar el documento correspondiente al tipo de gasto
        return gastosCollection.document(tipoGasto).delete();
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

    // Métodos para acceder y modificar el gasto actual
    public GastoActual getGastoActual() {
        return gastoActual;
    }

    public void setGastoActual(String tipoGasto, String monto) {
        this.gastoActual = new GastoActual(tipoGasto, monto);
    }

    // Método para configurar el listener del gasto actual
    public ListenerRegistration configurarListenerGastoActual(String userEmail, EventListener<DocumentSnapshot> listener) {
        // Obtener referencia al documento del usuario actual
        DocumentReference usuarioDocument = firestore.collection("usuarios").document(userEmail);

        // Obtener referencia al documento de gasto actual dentro de la colección "gastoActual"
        DocumentReference gastoActualRef = usuarioDocument.collection("gastoActual").document("gastoActual");

        // Configurar el listener para cambios en el documento de gasto actual
        return gastoActualRef.addSnapshotListener(listener);
    }

    // ------------------------------- PantallaVerGastos -------------------------------

    // Clase para representar el gasto actual
    public static class GastoActual {
        private String tipoGasto;
        private String monto;

        public GastoActual(String tipoGasto, String monto) {
            this.tipoGasto = tipoGasto;
            this.monto = monto;
        }

        public String getTipoGasto() {
            return tipoGasto;
        }

        public String getMonto() {
            return monto;
        }
    }

    public interface AuthCallback {
        void onSuccess();

        void onFailure(String errorMessage);
    }
}