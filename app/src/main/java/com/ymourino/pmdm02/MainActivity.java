package com.ymourino.pmdm02;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
         * Se obtiene el intent actual para tener acceso que se hayan pasado a esta actividad.
         */
        Intent intent = getIntent();
        String tipoAcceso = intent.getStringExtra(LoginActivity.TIPO_ACCESO);

        /*
         * Dependiendo del tipo de acceso se tendrá que modificar la interfaz mostrada al usuario.
         */
        if (tipoAcceso.equals("CLIENTE")) {
            /*
             * Se muestra el nombre completo del cliente.
             */
            ((TextView) findViewById(R.id.label_usuario)).setText("NomeA Apelido1A Apelido2A");
            /*
             * Se carga el avatar correcto para la densidad de pantalla del dispositivo.
             */
            ((ImageView) findViewById(R.id.image_avatarUsuario))
                    .setImageDrawable(getResources()
                            .getDrawableForDensity(R.drawable.cliente, getResources().getDisplayMetrics().densityDpi));
        } else if (tipoAcceso.equals("ADMIN")) {
            ((TextView) findViewById(R.id.label_usuario)).setText("NomeB Apelido1B Apelido2B");
            ((ImageView) findViewById(R.id.image_avatarUsuario))
                    .setImageDrawable(getResources()
                            .getDrawableForDensity(R.drawable.admin, getResources().getDisplayMetrics().densityDpi));

            /*
             * Siguiendo el enunciado de la tarea, el administrador no verá ningún botón.
             */
            findViewById(R.id.button_hacerPedido).setVisibility(View.INVISIBLE);
            findViewById(R.id.button_pedidosEnTramite).setVisibility(View.INVISIBLE);
            findViewById(R.id.button_comprasRealizadas).setVisibility(View.INVISIBLE);
            findViewById(R.id.button_salir).setVisibility(View.INVISIBLE);
        } else {
            ((TextView) findViewById(R.id.label_usuario)).setText("ERROR");
        }
    }


    /**
     * Se inicia la actividad para realizar un nuevo pedido.
     * @param view
     */
    public void hacerPedido(View view) {
        Intent intent = new Intent(this, HacerPedidoActivity.class);
        startActivity(intent);
    }

    /**
     * Se inicia la actividad para ver los pedidos en trámite.
     * @param view
     */
    public void pedidosEnTramite(View view) {
        Intent intent = new Intent(this, PedidosTramiteActivity.class);
        startActivity(intent);
    }

    /**
     * Se inicia la actividad para ver las compras realizadas.
     * @param view
     */
    public void comprasRealizadas(View view) {
        Intent intent = new Intent(this, ComprasRealizadasActivity.class);
        startActivity(intent);
    }

    /**
     * Se sale a la pantalla de login.
     * @param view
     */
    public void salir(View view) {
        /*
         * Como se ha definido que la actividad de login es la actividad padre de la actividad
         * principal, navegar "hacia arriba" nos lleva de nuevo al login.
         */
        NavUtils.navigateUpFromSameTask(this);
    }
}
