package com.mycompany.iaie;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;




public class repositorioClientes {
    private ArrayList<Cliente> Clientes;
    
    public repositorioClientes(){
        Clientes=new ArrayList<>();
    }
    
    public ArrayList<Cliente> getClientes(){
        return Clientes;
    }
    
    public void verMoloni(ArrayList<Cliente> mol){
        for(int i=0;i<Clientes.size();i++){
            Cliente c = Clientes.get(i);
            for(int j=0;j<mol.size();j++){
                /*System.out.println(c.getCustomer_id());
                System.out.println(mol.get(j).getCustomer_id());
                System.out.println(c.getCustomer_id()==mol.get(j).getCustomer_id());
                System.out.println("////////////////////");*/
                if(c.getCustomer_id()==mol.get(j).getCustomer_id()){
                    c.setMoloni(true);
                    c.setId_moloni(mol.get(j).getId_moloni());
                    break;
                }else{
                    c.setMoloni(false);
                }
            }
        }
    }
    
    public void getClientesHttp() throws IOException{
        
        OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(5, TimeUnit.MINUTES) // connect timeout
            .writeTimeout(5, TimeUnit.MINUTES) // write timeout
            .readTimeout(5, TimeUnit.MINUTES) // read timeout
            .build();
        
        

Request request = new Request.Builder()
  .url("http://s12.sapucc.in.tum.de/sap/opu/odata/sap/MD_BUSINESSPARTNER_SRV/C_BusinessPartner?$format=json&$filter=substringof('LEARN-326',CreatedByUser)and substringof('FLCU01',Role)")
  .addHeader("sap-client", "313")
  .addHeader("Authorization", "Basic TEVBUk4tMzI2OnBvcnR1Z2Fs")
  .addHeader("Cookie", "SAP_SESSIONID_S12_313=93OAaPKyva3vPTwt4CkPu9f85JuCOhHtkohyz0TDKwY%3d; sap-usercontext=sap-client=313")
  .build();

        try {
            Response response = client.newCall(request).execute();
            String jsonData = response.body().string();
            System.out.println(jsonData);
            JSONObject Jobject = new JSONObject(jsonData);
            String ola = Jobject.get("d").toString();
            JSONObject Jobject2 = new JSONObject(ola);
            JSONArray Jarray = Jobject2.getJSONArray("results");
            ArrayList<Cliente> Clientes2 = new ArrayList<>();
            for(int i=0;i<Jarray.length();i++){
                JSONObject obj = Jarray.getJSONObject(i);
                Cliente cliente;
                cliente = new Cliente(obj.getString("FullName"), obj.getInt("BusinessPartner"),obj.getString("AddressLine1Text"));
                Clientes2.add(cliente);
            }
            Clientes=Clientes2;
        } catch (Exception ex) {
            System.out.print("Erro na Comunicação ao SAP! A tentar novamente");
            getClientesHttp();
            
        }
        
        
    }
    
}
