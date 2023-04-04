package com.example.teste_web_servise_ndroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.textclassifier.ConversationActions;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText id =findViewById(R.id.edCodigo);
        EditText nome =findViewById(R.id.edNome);
        EditText preco =findViewById(R.id.edPreco);
        EditText quantidade =findViewById(R.id.edQuantidade);
        Button Cadastrar=findViewById(R.id.btCadastrar);
        Button remover=findViewById(R.id.btCadastrar);
        Button atualizar=findViewById(R.id.btCadastrar);
        Button buscar=findViewById(R.id.btCadastrar);
        Button listar=findViewById(R.id.btCadastrar);
        RequestQueue requisisao= Volley.newRequestQueue(this);
        //resume ai porra ja to cansado so de ler pelamor de deus caraikkkkkkkk;
        String url ="http://10.0.2.2:5000/api/Produtos/";
        //String com a indicação da URL aonde o webservice será executado,
        //no caso ele estará na máquina local (localhost) porém, se utilizarmos
        //o nome localhost no aplicativo, o Android entenderá que deve direcionar
        //para o localhost do dispositivo (celular) e não para o computador
        //Para direcionar corretamente devemos utilizar o IP 10.0.2.2 que é
        //um IP especial configurado no Android para indicar o localhost do computador
        String urlWebservice = "http://10.0.2.2:5000/api/Produto";

        //Criar um objeto para fazer requisição ao webservice
        RequestQueue requisicao = Volley.newRequestQueue(MainActivity.this);

        Cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Antes de enviar os dados para o webservice devemos formatar
                //e criar um objeto em JSON com os dados do produto
                JSONObject dadosEnvio = new JSONObject();
                try {
                    //O método de cadastrar espera receber o ID, nome, preço, quantidade
                    dadosEnvio.put("id", id.getText().toString()); //Autoincremento do Banco de dados
                    dadosEnvio.put("nome", nome.getText().toString());
                    dadosEnvio.put("preco", Double.parseDouble(preco.getText().toString()));
                    dadosEnvio.put("quantidade", Integer.parseInt(quantidade.getText().toString()));
                } catch (JSONException jExc) {
                    jExc.printStackTrace();
                }

                //Configurar o envio (juntar URL + dados + método - POST) e
                //configurar o que irá acontecer se der tudo certo ou ocorrer erro
                JsonObjectRequest enviarPost = new JsonObjectRequest(Request.Method.POST, //Método de envio dos dados
                        urlWebservice, //Local do webservice, aonde irá conectar
                        dadosEnvio, //O que será enviado
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                //Verificar se o conteúdo retornado pelo webservice
                                //possui mesmo um objeto da classe Produto
                                if (response.has("nome")) {
                                    Toast.makeText(MainActivity.this,
                                            "Cadastrado", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this,
                                "Erro ao cadastrar", Toast.LENGTH_SHORT).show();
                        error.printStackTrace();
                    }
                }
                );
                //Pedir para executar a configuração acima
                requisicao.add(enviarPost);

            }
        });
    }
}