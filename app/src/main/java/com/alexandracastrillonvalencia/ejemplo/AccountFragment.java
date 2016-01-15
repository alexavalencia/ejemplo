package com.alexandracastrillonvalencia.ejemplo;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * A simple {@link Fragment} subclass.
 */
public class AccountFragment extends Fragment {
    private EditText nombre,apellido,correo,codigo,pass;
    private Button aceptar;
    ParseObject pelicula=new ParseObject("Pelicula");
    private android.support.v4.app.Fragment fragment;
    public AccountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView=inflater.inflate(R.layout.fragment_account, container, false);
        nombre=(EditText)rootView.findViewById(R.id.name);
        apellido=(EditText)rootView.findViewById(R.id.apellido);
        correo=(EditText)rootView.findViewById(R.id.correo);
        codigo=(EditText)rootView.findViewById(R.id.codigo);
        pass=(EditText)rootView.findViewById(R.id.pass);
        aceptar=(Button)rootView.findViewById(R.id.inicio);

        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(nombre.getText().toString()!="" ) {
                    if (apellido.getText().toString() != "") {
                        if (correo.getText().toString()!=""){
                            if (isEmailValid(correo.getText().toString())==true){
                                if(codigo.getText().toString()!=""){
                                    if (pass.getText().toString()!=""){
                                        ingresoDataBase();

                                    }else {
                                        Toast.makeText(getContext().getApplicationContext(),"The Emial field is empty",Toast.LENGTH_LONG).show();
                                    }
                                }else{
                                    Toast.makeText(getContext().getApplicationContext(),"The card code field is empty",Toast.LENGTH_LONG).show();
                                }

                            }else{
                                Toast.makeText(getContext().getApplicationContext(),"The Emial is invalid",Toast.LENGTH_LONG).show();
                            }

                        }else{
                            Toast.makeText(getContext().getApplicationContext(),"The Emial field is empty",Toast.LENGTH_LONG).show();
                        }
                    }else{
                        Toast.makeText(getContext().getApplicationContext(),"The Last Name field is empty",Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(getContext().getApplicationContext(),"The Name field is empty",Toast.LENGTH_LONG).show();
                }



            }
        });
        return rootView;



    }

public void ingresoDataBase(){
    final ParseQuery<ParseObject> query=ParseQuery.getQuery("Pelicula");
    query.whereEqualTo("codigo", codigo.getText().toString());
    query.getFirstInBackground(new GetCallback<ParseObject>() {
        @Override
        public void done(ParseObject object, ParseException e) {
            if (e == null) {
                pelicula = object;
                pelicula.put("nombre", nombre.getText().toString());
                pelicula.put("Apellido", apellido.getText().toString());
                pelicula.put("correo", correo.getText().toString());
                pelicula.put("contrasena", pass.getText().toString());
                pelicula.saveInBackground();
                nombre.setText("");
                apellido.setText("");
                codigo.setText("");
                pass.setText("");
                correo.setText("");
                Toast.makeText(getContext().getApplicationContext(), "You were registered successfully ", Toast.LENGTH_SHORT).show();
                

            } else {
                Toast.makeText(getContext().getApplicationContext(), "the card code is not found", Toast.LENGTH_SHORT).show();
                nombre.setText("");
                apellido.setText("");
                codigo.setText("");
                pass.setText("");
                correo.setText("");

            }
        }
    });
}
    public static boolean isEmailValid(String email) {
        String getText=email;

        String Expn =
                "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"

                        +"((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"

                        +"[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."

                        +"([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"

                        +"[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"

                        +"([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$";

        if (getText.matches(Expn) && getText.length() > 0)
        {
           return true;
        }

        else
        {
           return false;
        }
    }

}
