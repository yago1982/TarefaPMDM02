package com.ymourino.pmdm02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

/**
 * La clase implementa la interfaz AdapterView.OnItemSelectedListener para poder reaccionar correctamente
 * a las selecciones que haga el usuario en el spinner de categorías.
 */
public class HacerPedidoActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    public static final String CATEGORIA = "com.ymourino.pmdm02.CATEGORIA";
    public static final String PRODUCTO = "com.ymourino.pmdm02.PRODUCTO";
    public static final String CANTIDAD = "com.ymourino.pmdm02.CANTIDAD";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hacer_pedido);

        /*
         * Establecemos el listener del spinner categorias, de modo que se puedan modificar los productos
         * según la categoría seleccionada.
         */
        Spinner categorias = findViewById(R.id.spinner_categorias);
        categorias.setOnItemSelectedListener(this);

        /*
         * Inicializamos el spinner de las cantidades.
         */
        Spinner spinner_cantidades = findViewById(R.id.spinner_cantidades);
        Integer[] array_cantidades = new Integer[] {1, 2, 3, 4, 5, };
        ArrayAdapter<Integer> cantidades = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, array_cantidades);
        spinner_cantidades.setAdapter(cantidades);
    }


    /**
     * Cuando se seleccione una categoría, el spinner de productos cambiará para mostrar los productos
     * de la categoría seleccionada.
     * @param parent
     * @param view
     * @param pos
     * @param id
     */
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        /*
         * Se obtiene la cadena de la categoría seleccionada.
         */
        String categoria = parent.getItemAtPosition(pos).toString();
        Spinner spinner_productos = findViewById(R.id.spinner_productos);
        ArrayAdapter<CharSequence> productos;

        if (categoria.equals("Informática")) {
            productos = ArrayAdapter.createFromResource(this, R.array.productos_informatica, android.R.layout.simple_spinner_item);
            productos.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner_productos.setAdapter(productos);
        } else if (categoria.equals("Electrónica")) {
            productos = ArrayAdapter.createFromResource(this, R.array.productos_electronica, android.R.layout.simple_spinner_item);
            productos.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner_productos.setAdapter(productos);
        } else if (categoria.equals("Móviles")) {
            productos = ArrayAdapter.createFromResource(this, R.array.productos_moviles, android.R.layout.simple_spinner_item);
            productos.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner_productos.setAdapter(productos);
        }
    }

    public void onNothingSelected(AdapterView<?> parent) {}

    /**
     * Se obtienen los datos del pedido y se pasan a la actividad de envío del pedido.
     * @param view
     */
    public void siguiente(View view) {
        Intent intent = new Intent(this, EnviarPedidoActivity.class);
        Spinner categorias = findViewById(R.id.spinner_categorias);
        Spinner productos = findViewById(R.id.spinner_productos);
        Spinner cantidades = findViewById(R.id.spinner_cantidades);
        String categoria = categorias.getSelectedItem().toString();
        String producto = productos.getSelectedItem().toString();
        Integer cantidad = (Integer) cantidades.getSelectedItem();

        intent.putExtra(CATEGORIA, categoria);
        intent.putExtra(PRODUCTO, producto);
        intent.putExtra(CANTIDAD, cantidad);

        startActivity(intent);

        /*
         * Finalizamos la actividad actual para que, cuando terminemos también la actividad que
         * se acaba de iniciar, se regrese automáticamente a la página principal.
         *
         * Por el momento es la única forma de conseguir ese comportamiento que me ha funcionado
         * de forma fiable.
         */
        finish();
    }
}
