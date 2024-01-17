/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.iaie;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author cr249
 */

public class Moloni {
    private String access_token,refresh_token;
     private ArrayList<Cliente> Clientes;
    
    public Moloni(){
         Clientes=new ArrayList<>();
    }
    
    public ArrayList<Cliente> getMoloniClients(){
        return Clientes;
    }
    
    
    public void autenticar(){
        OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(5, TimeUnit.MINUTES) // connect timeout
            .writeTimeout(5, TimeUnit.MINUTES) // write timeout
            .readTimeout(5, TimeUnit.MINUTES) // read timeout
            .build();
        
        

Request request = new Request.Builder()
  .url("https://api.moloni.pt/v1/grant/?grant_type=password&client_id=iaieg4pl5&client_secret=626bdd7236c068d2fea2f28dc32fa2feb260dbee&username=a71354@alunos.uminho.pt&password=iaiepl5grupo4")
  .addHeader("Cookie", "PHPSESSID=59vub3gd420qu7t2ef5fj6miu0")
  .build();

        try {
            Response response = client.newCall(request).execute();
            String jsonData = response.body().string();
            JSONObject Jobject = new JSONObject(jsonData);
            access_token = Jobject.get("access_token").toString();
            refresh_token = Jobject.get("refresh_token").toString();
            
        } catch (IOException ex) {
            System.out.print(ex);
        }
    }
    
    public void refreshToken(){
       OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(5, TimeUnit.MINUTES) // connect timeout
            .writeTimeout(5, TimeUnit.MINUTES) // write timeout
            .readTimeout(5, TimeUnit.MINUTES) // read timeout
            .build();
       String url = "https://api.moloni.pt/v1/grant/?grant_type=refresh_token&client_id=iaieg4pl5&client_secret=626bdd7236c068d2fea2f28dc32fa2feb260dbee&refresh_token=" + refresh_token;
       
    Request request = new Request.Builder()
      .url(url)
      .addHeader("Cookie", "PHPSESSID=59vub3gd420qu7t2ef5fj6miu0")
      .build();
        try {
            Response response = client.newCall(request).execute();
            String jsonData = response.body().string();
            JSONObject Jobject = new JSONObject(jsonData);
            access_token = Jobject.get("access_token").toString();
            refresh_token = Jobject.get("refresh_token").toString();
        } catch (IOException ex) {
            Logger.getLogger(Moloni.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void delete_moloni(int id){
        refreshToken();
       OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(5, TimeUnit.MINUTES) // connect timeout
            .writeTimeout(5, TimeUnit.MINUTES) // write timeout
            .readTimeout(5, TimeUnit.MINUTES) // read timeout
            .build();
       String url = "https://api.moloni.pt/v1/customers/delete/?access_token=" + access_token;
       
     RequestBody body = new FormBody.Builder()
                .add("company_id", "5")
                .add("customer_id", Integer.toString(id))
                .build();
     
     Request request = new Request.Builder()
                .url(url)
                .post(body)
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .build();
    
        try {
            Response response = client.newCall(request).execute();
            
        } catch (IOException ex) {
            Logger.getLogger(Moloni.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public JSONObject getOne(int id){
        refreshToken();
       OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(5, TimeUnit.MINUTES) // connect timeout
            .writeTimeout(5, TimeUnit.MINUTES) // write timeout
            .readTimeout(5, TimeUnit.MINUTES) // read timeout
            .build();
       String url = "https://api.moloni.pt/v1/customers/getOne/?access_token=" + access_token;
       
     RequestBody body = new FormBody.Builder()
                .add("company_id", "5")
                .add("customer_id", Integer.toString(id))
                .build();
     
     Request request = new Request.Builder()
                .url(url)
                .post(body)
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .build();
    
        try {
            Response response = client.newCall(request).execute();
            String jsonData = response.body().string();
            JSONObject Jobject = new JSONObject(jsonData);
            return Jobject;
        } catch (IOException ex) {
            Logger.getLogger(Moloni.class.getName()).log(Level.SEVERE, null, ex);
        }
       return null;
    }
    
    
    public void InserirCliente(String nome, String id, String morada){
        refreshToken();
        OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(5, TimeUnit.MINUTES) // connect timeout
            .writeTimeout(5, TimeUnit.MINUTES) // write timeout
            .readTimeout(5, TimeUnit.MINUTES) // read timeout
            .build();
        
        //RequestBody body = RequestBody.create(mediaType, "number=88&name=Luis Correia&address=Pr Camilo Castelo Branco");
        RequestBody body = new FormBody.Builder()
                .add("company_id", "5")
                .add("number", id)
                .add("customer_id", id)
                .add("name", nome)
                .add("address", morada)
                .add("language_id", "1")
                .add("zip_code", "4700-209")
                .add("city", "Braga")
                .add("country_id", "1")
                .add("salesman_id", id)
                .add("maturity_date_id", "1554050")
                .add("payment_day", "1")
                .add("discount", "15")
                .add("credit_limit", "9999")
                .add("copies[0]document_type_id", "")
                .add("copies[0]copies", "")
                .add("payment_method_id", "1768105")
                .add("delivery_method_id", "")
                .add("field_notes", "")
                .add("email", "")
                .add("phone", "")
                .add("vat", "")
                .build();
        
        String url = "https://api.moloni.pt/v1/customers/insert/?access_token=" + access_token;
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .build();
        
        try {
            Response response = client.newCall(request).execute();;
        } catch (IOException ex) {
            Logger.getLogger(Moloni.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
      public void atualizarCliente(String nome, String id, String morada, String discount, String email, String phone, String vat) throws IOException{
        refreshToken();
        OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(5, TimeUnit.MINUTES) // connect timeout
            .writeTimeout(5, TimeUnit.MINUTES) // write timeout
            .readTimeout(5, TimeUnit.MINUTES) // read timeout
            .build();
        
        RequestBody body = new FormBody.Builder()
                .add("company_id", "5")
                .add("customer_id", id)
                .add("name", nome)
                .add("address", morada)
                .add("discount", discount)
                .add("email", email)
                .add("phone", phone)
                .add("vat", vat)
                .build();
        
        String url = "https://api.moloni.pt/v1/customers/update/?access_token=" + access_token;
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .build();
        
        
        Response response = client.newCall(request).execute();
    }
       
    
    public void obterClientes() throws IOException{
        Clientes.clear();
        refreshToken();
        OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(5, TimeUnit.MINUTES) // connect timeout
            .writeTimeout(5, TimeUnit.MINUTES) // write timeout
            .readTimeout(5, TimeUnit.MINUTES) // read timeout
            .build();
        RequestBody body = new FormBody.Builder()
                .add("company_id", "5")
                .build();
      String url = "https://api.moloni.pt/v1/customers/getAll/?access_token=" + access_token;
      
      Request request = new Request.Builder()
        .url(url)
        .post(body)
        .addHeader("Content-Type", "application/x-www-form-urlencoded")
        .addHeader("Cookie", "PHPSESSID=2r3ngerrstb7c7n04lucpgft93")
        .build();
      Response response = client.newCall(request).execute();
      String jsonData = response.body().string();
      JSONArray Jarray = new JSONArray(jsonData);
      for(int i=0;i<Jarray.length();i++){
                JSONObject obj = Jarray.getJSONObject(i);
                Cliente cliente;
                cliente = new Cliente(obj.getString("name"), obj.getInt("salesman_id"),obj.getString("address"), obj.getInt("customer_id"));
                Clientes.add(cliente);
            }
    }
}
