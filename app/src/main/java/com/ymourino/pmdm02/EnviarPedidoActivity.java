package com.ymourino.pmdm02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EnviarPedidoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enviar_pedido);
    }


    /**
     * Al finalizar el pedido se mostrará un toast con la información del mismo y se termina la
     * actividad.
     * @param view
     */
    public void finalizarPedido(View view) {
        /*
         * Obtenemos los detalles del envío que ha recibido esta actividad al iniciarse.
         */
        Intent intent = getIntent();

        String categoria = intent.getStringExtra(HacerPedidoActivity.CATEGORIA);
        String producto = intent.getStringExtra(HacerPedidoActivity.PRODUCTO);
        Integer cantidad = intent.getIntExtra(HacerPedidoActivity.CANTIDAD, 1);

        String direccion = ((EditText) findViewById(R.id.edit_direccion)).getText().toString();
        String ciudad = ((EditText) findViewById(R.id.edit_ciudad)).getText().toString();
        String cp = ((EditText) findViewById(R.id.edit_cp)).getText().toString();

        /*
         * Mostramos el toast.
         */
        Toast toast = Toast.makeText(this,
                categoria + "\n"
                + producto + "\n"
                + cantidad + "\n\n"
                + direccion + "\n"
                + ciudad + "\n"
                + cp,
                Toast.LENGTH_LONG);
        toast.show();

        /*
         * Como la actividad que inició esta ya ha terminado, al terminar la actual se regresará a
         * la pantalla principal.
         */
        finish();
    }
}
