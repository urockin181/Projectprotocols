package com.example.androidclient;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class MainActivity extends AppCompatActivity {
    Thread qqq = null;
    EditText txtIp;
    EditText txtPort;
    EditText txtMsg;
    Button txtBut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtIp = (EditText) findViewById(R.id.txtIp);
        txtPort = (EditText) findViewById(R.id.txtPort);
        txtMsg = (EditText) findViewById(R.id.txtMsg);
        txtBut = (Button) findViewById(R.id.txtBut);
        txtBut.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            Socket msg = new Socket(txtIp.getText().toString(),Integer.parseInt(txtPort.getText().toString()));
                            DataOutputStream ms = new DataOutputStream(msg.getOutputStream());
                            BufferedReader p = new BufferedReader(new InputStreamReader(msg.getInputStream()));
                            ms.write((txtMsg.getText().toString()+"\n").getBytes());
                            ms.flush();
                            String x ="";
                            x = p.readLine();
                            ms.write("close connection".getBytes());
                            ms.flush();
                            ms.close();
                            msg.close();
                        }
                        catch(NumberFormatException a){
                            a.printStackTrace();
                        }
                        catch(UnknownHostException a){
                            a.printStackTrace();
                        }
                        catch(IOException a){
                            a.printStackTrace();
                        }

                    }
                }).start();


            }
        });
    }
}