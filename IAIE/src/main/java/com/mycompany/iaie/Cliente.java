package com.mycompany.iaie;

public class Cliente {
    private String Name;
    private int Customer_id;
    private String Adress;
    private boolean moloni;
    private int id_moloni=0;

    
    
    
    
    public Cliente(){}
    
    public Cliente(String name, int Customer_id, String Adress){
        this.Name=name;
        this.Customer_id=Customer_id;
        this.Adress=Adress;
    }

    Cliente(String name, int Customer_id, String Adress, int id_moloni) {
        this.Name=name;
        this.Customer_id=Customer_id;
        this.Adress=Adress;
        this.id_moloni=id_moloni;
    }

    public int getId_moloni() {
        return id_moloni;
    }

    public void setId_moloni(int id_moloni) {
        this.id_moloni = id_moloni;
    }

    public String getName() {
        return Name;
    }

    public int getCustomer_id() {
        return Customer_id;
    }

    public String getAdress() {
        return Adress;
    }


    public void setName(String Name) {
        this.Name = Name;
    }

    public void setCustomer_id(int Customer_id) {
        this.Customer_id = Customer_id;
    }

    public void setAdress(String Adress) {
        this.Adress = Adress;
    }
    
    public boolean getMoloni() {
        return moloni;
    }

    public void setMoloni(boolean moloni) {
        this.moloni = moloni;
    }
    
}
