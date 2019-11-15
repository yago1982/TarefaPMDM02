package com.ymourino.pmdm02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    public static final String TIPO_ACCESO = "com.ymourino.pmdm02.TIPO_ACCESO";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*
         * Se oculta la barra de título en la pantalla de login. Ya existe una etiqueta que muestra
         * el nombre de la aplicación.
         */
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_login);
    }


    public void login(View view) {
        /*
         * Se obtienen el nombre de usuario y la contraseña introducidos.
         */
        EditText edit_username = findViewById(R.id.edit_username);
        EditText edit_password = findViewById(R.id.edit_password);
        String username = edit_username.getText().toString();
        String password = edit_password.getText().toString();

        /*
         * Con el RadioGroup será sencillo obtener la opción seleccionada para poder comprobarla.
         */
        RadioGroup tipoLogin = findViewById(R.id.group_tipoLogin);

        /*
         * Se comprueba qué opción está seleccionada, y se comprueba si los datos de acceso son
         * los correctos para dicha opción.
         */
        if (tipoLogin.getCheckedRadioButtonId() == R.id.radio_cliente) {
            if (!username.equals("cliente1") || !password.equals("abc123.")) {
                showToastMsg("DATOS DE ACCESO INCORRECTOS");
            } else {
                /*
                 * Si los datos son correctos para el acceso cliente, se inicia la actividad
                 * principal indicando el tipo de acceso.
                 */
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra(TIPO_ACCESO, "CLIENTE");
                startActivity(intent);
            }
        } else if (tipoLogin.getCheckedRadioButtonId() == R.id.radio_admin) {
            if (!username.equals("admin") || !password.equals("abc123.")) {
                showToastMsg("DATOS DE ACCESO INCORRECTOS");
            } else {
                /*
                 * Si los datos son correctos para el acceso administrador, se inicia la actividad
                 * principal indicando el tipo de acceso.
                 */
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra(TIPO_ACCESO, "ADMIN");
                startActivity(intent);
            }
        } else {
            /*
             * A este punto no se debería llegar, ya que en principio no hay forma de que queden
             * sin seleccionar las dos opciones existentes.
             */
            showToastMsg("ERROR CON EL ACCESO ELEGIDO");
        }
    }


    private void showToastMsg(String msg) {
        Toast toast = Toast.makeText(this, msg, Toast.LENGTH_LONG);
        toast.show();
    }
}
