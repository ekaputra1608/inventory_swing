/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Master;
import Koneksi.Koneksi;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Hilman
 */
public class FrmUser extends javax.swing.JFrame {
String header[]={"Kode User","Username","Password","Hak Akses"};
    Connection con;
    Statement st;
    Koneksi kon=new Koneksi();
    private DefaultTableModel tabMode;
    String query;
    private Dimension dimensi = Toolkit.getDefaultToolkit().getScreenSize();
    /**
     * Creates new form FrmUser
     */
    public FrmUser() {
        initComponents();
        
        TidakAktif();
        tampilDataTabel();
        this.setLocationRelativeTo(rootPane);
    }

    //kode untuk properties komponen
void Aktif(){
   txtKodeUser.setEditable(true);
   txtNamaUser.setEditable(true);
   txtPassword.setEditable(true);
   cboHak.setEditable(true);
   txtKodeUser.requestFocus();
}

//continue
void TidakAktif(){
    txtKodeUser.setEditable(false);
    txtNamaUser.setEditable(false);
    txtPassword.setEditable(false);
    cboHak.setEditable(false);
    btnSave.setEnabled(false);
    btnUpdate.setEnabled(false);
    btnDelete.setEnabled(false);
}

//continue
void New(){
    Aktif();
    btnSave.setEnabled(true);
}


//Simpan data
void save(){
     try{
     kon.QUERY("INSERT INTO tb_user (kd_user,nama_user,password,hak_akses) VALUES ('"+txtKodeUser.getText()+"','"+txtNamaUser.getText()+"','"+txtPassword.getText()+"','"+cboHak.getSelectedItem()+"')"," Disimpan");        
        tampilDataTabel();
        ClearText();
    }catch(Exception sqle){
    JOptionPane.showMessageDialog(rootPane,"Failed saved data! "+sqle.getMessage());
    }
}


//kode untuk ubah data
void Update(){
   try{
                
        kon.QUERY("UPDATE tb_user SET nama_user='"+txtNamaUser.getText()+"',password='"+txtPassword.getText()+"',hak_akses='"+cboHak.getSelectedItem()+"' WHERE kd_user='"+txtKodeUser.getText()+"' "," Diperbaharui");
        System.out.println("UPDATE tb_user SET nama_user='"+txtNamaUser.getText()+"',password='"+txtPassword.getText()+"',hak_akses='"+cboHak.getSelectedItem()+"' WHERE kd_user='"+txtKodeUser.getText()+"'");        
        tampilDataTabel();
        ClearText();
   }catch(Exception sqle){
            
   JOptionPane.showMessageDialog(rootPane,"Failed updated data! "+sqle.getMessage());
   }
}


//kode untuk hapus data
void Delete(){
   try{
       kon.QUERY ("DELETE FROM tb_user WHERE kd_user='"+txtKodeUser.getText()+"' "," Dihapus");
       tampilDataTabel();
       ClearText();
   }catch(Exception sqle){          
       JOptionPane.showMessageDialog(rootPane, "Failed deleted data! "+sqle.getMessage());
   }
}


//kode untuk cari data
void Search(){
   removeTable();            
   try{                
       con=kon.open();
       st=con.createStatement();
       query="SELECT * FROM tb_user WHERE "+cboKat.getSelectedItem()+" LIKE '%"+txtCari.getText()+"%' ";
       ResultSet rs=st.executeQuery(query);               
       while(rs.next()){                  
           String skd_user =rs.getString("kd_user");
           String snama_user =rs.getString("nama_user");
           String spassword =rs.getString("password");
           String shak =rs.getString("hak_akses");     
           txtKodeUser.setText(skd_user);
           txtNamaUser.setText(snama_user);
           txtPassword.setText(spassword);
           cboHak.setSelectedItem(shak);
                  
           String data []={skd_user,snama_user,spassword,shak};
           tabMode.addRow(data);                   
        }
     }catch(SQLException sqle){
           JOptionPane.showMessageDialog(null, "Failed show data! "+sqle.getMessage());
     }
}


//kode untuk membersihkan text
void ClearText(){
   txtKodeUser.setText("");
   txtNamaUser.setText("");
   txtPassword.setText("");
   cboHak.setSelectedItem("");
   txtKodeUser.requestFocus();
   btnUpdate.setEnabled(false);
   btnDelete.setEnabled(false);
   btnSave.setEnabled(false);
}


//kode untuk menampilkan data dari tabel frame ke text
void ShowDataTabelKeText(){
   txtKodeUser.setText(String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(),0)));
   txtNamaUser.setText(String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(),1)));
   txtPassword.setText(String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(),2)));
   cboHak.setSelectedItem(String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(),3)));
     
   txtKodeUser.setEditable(false);
   txtNamaUser.setEditable(true);
   txtPassword.setEditable(true);
   cboHak.setEditable(true);
   txtNamaUser.requestFocus();
            
   btnUpdate.setEnabled(true);
   btnDelete.setEnabled(true);
}


    
    
    private void tampilDataTabel(){
removeTable();
try{
       con=kon.open();
       st=con.createStatement();
       query="SELECT * FROM tb_user";
       ResultSet rs=st.executeQuery(query);
       while(rs.next()){
            String a=rs.getString("kd_user");
            String b=rs.getString("nama_user");
            String c=rs.getString("password");
            String d=rs.getString("hak_akses");
                
            String data []={a,b,c,d};
            tabMode.addRow(data);
       }
            
       }catch(SQLException sqle){
           JOptionPane.showMessageDialog(null,"Failed show data! "+sqle);
            
    }
}
//continue
public void removeTable() {
   try{
       for(int t=tabMode.getRowCount();t>0;t--){tabMode.removeRow(0);}            
   }
   catch(Exception ex){System.out.println(ex);}
}



    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnSave = new javax.swing.JButton();
        txtPassword = new javax.swing.JPasswordField();
        btnUpdate = new javax.swing.JButton();
        btnNew = new javax.swing.JButton();
        btnSearch = new javax.swing.JButton();
        txtCari = new javax.swing.JTextField();
        btnClear = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        btnDelete = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        btnRefresh = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        cboKat = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        txtKodeUser = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        txtNamaUser = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        cboHak = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 102, 102));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnSave.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        btnSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/save.png"))); // NOI18N
        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });
        jPanel1.add(btnSave, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 280, -1, 30));

        txtPassword.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jPanel1.add(txtPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 190, 220, -1));

        btnUpdate.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        btnUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/edit2.png"))); // NOI18N
        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });
        jPanel1.add(btnUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 280, -1, 30));

        btnNew.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        btnNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/document-new.png"))); // NOI18N
        btnNew.setText("New");
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });
        jPanel1.add(btnNew, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 280, -1, -1));

        btnSearch.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        btnSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/old-zoom-original.png"))); // NOI18N
        btnSearch.setText("Search");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });
        jPanel1.add(btnSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 560, -1, -1));

        txtCari.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jPanel1.add(txtCari, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 560, 250, 30));

        btnClear.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        btnClear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/edit-clear.png"))); // NOI18N
        btnClear.setText("Clear");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });
        jPanel1.add(btnClear, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 280, -1, -1));

        jLabel2.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel2.setText("Kode User");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 110, -1, -1));

        btnDelete.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/delete.png"))); // NOI18N
        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        jPanel1.add(btnDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 280, -1, -1));

        jLabel3.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel3.setText("Nama User");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 150, -1, -1));

        btnRefresh.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        btnRefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/gnome_session_reboot.png"))); // NOI18N
        btnRefresh.setText("Refresh");
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });
        jPanel1.add(btnRefresh, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 560, -1, -1));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        tabMode=new DefaultTableModel(null,header);
        jTable1.setModel(tabMode);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 350, 750, 180));

        jLabel4.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel4.setText("Password");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 190, -1, -1));

        cboKat.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        cboKat.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "kd_user", "nama_user", "password", "hak_akses" }));
        jPanel1.add(cboKat, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 560, -1, 30));

        jLabel5.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel5.setText("Hak Akses");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 230, -1, -1));

        txtKodeUser.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jPanel1.add(txtKodeUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 110, 220, -1));

        jButton7.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Close.png"))); // NOI18N
        jButton7.setText("Exit");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 280, 90, -1));

        txtNamaUser.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jPanel1.add(txtNamaUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 150, 220, -1));

        jLabel6.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel6.setText("Cari Berdasarkan");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 570, -1, -1));

        cboHak.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        cboHak.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "ADMIN", "WAREHOUSE", "PPIC" }));
        jPanel1.add(cboHak, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 230, 80, -1));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/BgInputUser.jpg"))); // NOI18N
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(-15, -17, 900, 660));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 871, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 635, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
New();        // TODO add your handling code here:
    }//GEN-LAST:event_btnNewActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
save();        // TODO add your handling code here:
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
Update();       // TODO add your handling code here:
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
Delete();        // TODO add your handling code here:
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
ClearText();        // TODO add your handling code here:
    }//GEN-LAST:event_btnClearActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
this.dispose();      // TODO add your handling code here:
    }//GEN-LAST:event_jButton7ActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
Search();        // TODO add your handling code here:
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed

        ClearText();
        tampilDataTabel();        // TODO add your handling code here:
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
ShowDataTabelKeText();
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new FrmUser().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox cboHak;
    private javax.swing.JComboBox cboKat;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtCari;
    private javax.swing.JTextField txtKodeUser;
    private javax.swing.JTextField txtNamaUser;
    private javax.swing.JPasswordField txtPassword;
    // End of variables declaration//GEN-END:variables
}
