/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.iaie;

import java.awt.Component;
import java.awt.Image;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author cr249
 */
public class janelaClientes extends javax.swing.JFrame {

    private AbstractTableModel tableModel;
    private repositorioClientes clientes;
    private Moloni mol;
    JLabel label;

    /**
     * Creates new form janelaClientes
     */
    public janelaClientes() throws IOException {
        initComponents();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.clientes = new repositorioClientes();
        this.mol= new Moloni();
        clientes.getClientesHttp();
        mol.autenticar();
        mol.obterClientes();
        clientes.verMoloni(mol.getMoloniClients());
        this.tableModel = criarModeloTabela();
        tableClientes.setModel(tableModel);
        tableClientes.setRowHeight(40);
        tableClientes.getColumnModel().getColumn(3).setCellRenderer(new Renderer());
    }
    
    class Renderer extends DefaultTableCellRenderer{
        ImageIcon icon = new ImageIcon("./src/main/java/com/mycompany/iaie/images.png");
        Image image = icon.getImage();
        Image resized = image.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);
        ImageIcon icon2 = new ImageIcon(resized);
        JLabel label = new JLabel("Moloni",icon2, JLabel.CENTER);
        
        @Override
        public Component getTableCellRendererComponent(JTable tableClientes, Object value, boolean isSelected, boolean hasFocus, int row, int column){
            if(clientes.getClientes().get(row).getMoloni()){
                                return label;
                        }else{
                            return null;
                        }
            
        }
    }

    private AbstractTableModel criarModeloTabela() {
        String[] nomeColunas = {"Name", "Customer_ID", "Address","Moloni"};
        return new AbstractTableModel() {
            @Override
            public String getColumnName(int column) {
                return nomeColunas[column];
            }

            @Override
            public int getColumnCount() {
                return nomeColunas.length;
            }

            @Override
            public int getRowCount() {
                return clientes.getClientes().size();
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {

                switch (columnIndex) {
                    case 0:
                        return clientes.getClientes().get(rowIndex).getName();
                    case 1:
                        return clientes.getClientes().get(rowIndex).getCustomer_id();
                    case 2:
                        return clientes.getClientes().get(rowIndex).getAdress();
                    case 3:
                            return (rowIndex*columnIndex);
                        
                    default:
                        return "";
                }
            }

        };
    }
      public void atualizar() throws IOException {    
          
       clientes.getClientesHttp();
       mol.obterClientes();
       clientes.verMoloni(mol.getMoloniClients());
       tableModel.fireTableDataChanged();
    }
    
    private void adicionarMoloni() throws IOException{
        int rowIndex = tableClientes.getSelectedRow();
           if (rowIndex == -1) return;
           String Name = (String) tableModel.getValueAt(rowIndex, 0);
           int id =  (int) tableModel.getValueAt(rowIndex, 1);
           String Customer_ID = Integer.toString(id);
           String Address = (String) tableModel.getValueAt(rowIndex, 2);
           mol.InserirCliente(Name, Customer_ID, Address);
           atualizar();
           JOptionPane.showMessageDialog(this, "Cliente adicionado com sucesso!!");
    }
    
     private void removerMoloni() throws IOException{
        int rowIndex = tableClientes.getSelectedRow();
           if (rowIndex == -1) return;
           if(!clientes.getClientes().get(rowIndex).getMoloni()){
               JOptionPane.showMessageDialog(this, "Cliente não existe no Moloni!!");
               return;
           }
           mol.delete_moloni(clientes.getClientes().get(rowIndex).getId_moloni());
           atualizar();
           JOptionPane.showMessageDialog(this, "Cliente Removido com sucesso!!");
    }
     
     private void editar(){
        int rowIndex = tableClientes.getSelectedRow();
        if (rowIndex == -1) return;
        if(!clientes.getClientes().get(rowIndex).getMoloni()){
               JOptionPane.showMessageDialog(this, "Cliente não existe no Moloni!!");
               return;
           }
        verDetalhes vd = new verDetalhes(clientes.getClientes().get(rowIndex),mol);
        vd.setVisible(true);
        dispose();
        
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableClientes = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        btnDetails = new javax.swing.JButton();
        btnRemove = new javax.swing.JButton();
        btnAtualizar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tableClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tableClientes);

        jButton1.setText("Add Moloni");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btnDetails.setText("Client Details");
        btnDetails.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDetailsActionPerformed(evt);
            }
        });

        btnRemove.setText("Remove Moloni");
        btnRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveActionPerformed(evt);
            }
        });

        btnAtualizar.setText("Atualizar");
        btnAtualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtualizarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 647, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(btnDetails, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAtualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnRemove, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                    .addComponent(btnDetails, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                    .addComponent(btnRemove, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                    .addComponent(btnAtualizar, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            adicionarMoloni();
        } catch (IOException ex) {
            Logger.getLogger(janelaClientes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnDetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDetailsActionPerformed
        editar();
        
    }//GEN-LAST:event_btnDetailsActionPerformed

    private void btnRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveActionPerformed
        try {
            removerMoloni();
        } catch (IOException ex) {
            System.out.println("Erro ao Remover");
        }
    }//GEN-LAST:event_btnRemoveActionPerformed

    private void btnAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtualizarActionPerformed
        try {
            atualizar();
        } catch (IOException ex) {
            System.out.println("Erro ao atualizar");
        }
    }//GEN-LAST:event_btnAtualizarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAtualizar;
    private javax.swing.JButton btnDetails;
    private javax.swing.JButton btnRemove;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableClientes;
    // End of variables declaration//GEN-END:variables
}
