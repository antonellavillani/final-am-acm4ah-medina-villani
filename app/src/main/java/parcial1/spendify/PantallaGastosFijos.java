package parcial1.spendify;

import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PantallaGastosFijos extends AppCompatActivity {

    private TableLayout tableLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_gastos_fijos);

        // Obtener referencia a la tabla
        tableLayout = findViewById(R.id.tableLayout);

        // Ejemplo de agregar filas a la tabla
        agregarFilaTabla("Alquiler", "$1000");
        agregarFilaTabla("Electricidad", "$150");
    }

    // Función para agregar una fila a la tabla
    private void agregarFilaTabla(String tipoGasto, String monto) {
        // Crear una nueva fila
        TableRow newRow = new TableRow(this);

        // Configurar layout parameters para las celdas
        TableRow.LayoutParams params = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 0, 0, 16); // Agregar margen inferior entre filas

        // Crear celdas y agregarlas a la fila
        TextView cellTipoGasto = new TextView(this);
        cellTipoGasto.setText(tipoGasto);
        cellTipoGasto.setTextColor(getResources().getColor(R.color.lima));
        cellTipoGasto.setTextSize(TypedValue.COMPLEX_UNIT_SP, getResources().getDimension(R.dimen.fuente12));
        cellTipoGasto.setLayoutParams(params);

        TextView cellMonto = new TextView(this);
        cellMonto.setText(monto);
        cellMonto.setTextColor(getResources().getColor(R.color.lima));
        cellMonto.setTextSize(TypedValue.COMPLEX_UNIT_SP, getResources().getDimension(R.dimen.fuente12));
        cellMonto.setLayoutParams(params);

        Button cellModificar = new Button(this);
        cellModificar.setText("Modificar");
        // Configurar onClickListener para el botón "Modificar"
        cellModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lógica para modificar
            }
        });

        Button cellEliminar = new Button(this);
        cellEliminar.setText("Eliminar");
        // Configurar onClickListener para el botón "Eliminar"
        cellEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lógica para eliminar
            }
        });

        // Agregar celdas a la fila
        newRow.addView(cellTipoGasto);
        newRow.addView(cellMonto);
        newRow.addView(cellModificar);
        newRow.addView(cellEliminar);

        // Agregar fila a la tabla
        tableLayout.addView(newRow);

        // Cambiar visibilidad de la tabla a VISIBLE después de agregar una fila
        tableLayout.setVisibility(View.VISIBLE);
    }
}

