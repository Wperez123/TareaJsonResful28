package com.example.tareajsonresful28;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import WebService.Asynchtask;
import WebService.WebService;

public class MainActivity extends AppCompatActivity implements Asynchtask
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bundle bundle = this.getIntent().getExtras();
        Map<String, String> datos = new HashMap<String, String>();
        WebService ws= new WebService("https://api.fake-rest.refine.dev/users",
                datos,MainActivity.this, MainActivity.this);
        ws.execute("GET");
    }

    @Override
    public void processFinish(String result) throws JSONException
    {
        String lsperson = "";
        String id, nombre, apellido, correo;
        TextView txtSaludo = (TextView)findViewById(R.id.txtmostrar);
        JSONArray JSONlista = new JSONArray(result);
        for(int i=0; i< JSONlista.length();i++) {
            JSONObject user = JSONlista.getJSONObject(i);
            id = user.getString("id");
            nombre = user.getString("firstName");
            apellido = user.getString("lastName");
            correo = user.getString("email");

            lsperson=lsperson+"-----------------"+"\n"+"Identificador: " +id+"\n"+"Nombre:  " +nombre+"\n"+"Apellido:  "+apellido+"\n"
                    +"Correo:  "+correo+"\n";

        }
        txtSaludo.setText(lsperson);
    }
}