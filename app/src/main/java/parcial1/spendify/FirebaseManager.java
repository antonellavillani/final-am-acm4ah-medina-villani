package parcial1.spendify;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

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

    public interface AuthCallback {
        void onSuccess();

        void onFailure(String errorMessage);
    }
}
