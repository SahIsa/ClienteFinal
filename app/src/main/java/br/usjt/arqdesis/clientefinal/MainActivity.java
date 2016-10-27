package br.usjt.arqdesis.clientefinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static final String LISTA = "br.usjt.arqdesis.clientefinal.lista";
    EditText texto; //Instancia para receber texto
    ArrayList<Cliente> lista;
    ClienteRequester requester;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        texto = (EditText) findViewById(R.id.busca_cliente); //Instancia acesso para puxar da classe activity ( pega o texto )
    }
    public void buscarClientes(View view){
        requester = new ClienteRequester();
        intent = new Intent(this, ListarClientesActivity.class);
           new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        lista = requester.getClientes("http://192.168.56.1:8080/arqdesis_poetas/cliente");
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                intent.putExtra(LISTA,lista);
                                startActivity(intent);
                            }
                        });
                        intent.putExtra(LISTA, lista);
                        startActivity(intent);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();



    }
}
