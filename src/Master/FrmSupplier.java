/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Master;
import Koneksi.Koneksi;
import java.io.File;
import java.sql.*;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
/**
 *
 * @author Hilman
 */
public class FrmSupplier extends javax.swing.JFrame {
    String header[]={"Kode Supplier","Nama Supplier","No Telepon","Keterangan"};
    Connection con;
    Statement st;
    Koneksi kon=new Koneksi();
    private DefaultTableModel tabMode;
    String query;
    /**
     * Creates new form FrmUser
     */
    public FrmSupplier() {
        initComponents();
        TidakAktif();
        tampilDataTabel();
    }

void Aktif(){
   txtKodeSupplier.setEditable(true);
   txtNamaSupplier.setEditable(true);
   txtNoTlp.setEditable(true);
   txtKet.setEditable(true);
   txtKodeSupplier.requestFocus();
}

//continue
void TidakAktif(){
    txtKodeSupplier.setEditable(false);
    txtNamaSupplier.setEditable(false);
    txtNoTlp.setEditable(false);
    txtKet.setEditable(false);
    btnSave.setEnabled(false);
    btnUpdate.setEnabled(false);
    btnDelete.setEnabled(false);
}    
    
void New(){
    ClearText();
    Aktif();
    btnSave.setEnabled(true);
}

void save(){
     try{
     kon.QUERY("INSERT INTO tb_supplier (kd_supplier,nama_supplier,no_tlp,ket) VALUES ('"+txtKodeSupplier.getText()+"','"+txtNamaSupplier.getText()+"','"+txtNoTlp.getText()+"','"+txtKet.getText()+"')"," Saved!! ");        
        tampilDataTabel();
        ClearText();
    }catch(Exception sqle){
    JOptionPane.showMessageDialog(rootPane,"Failed saved data! "+sqle.getMessage());
    }
}

void Update(){
   try{       
        kon.QUERY("UPDATE tb_supplier SET nama_supplier='"+txtNamaSupplier.getText()+"',no_tlp='"+txtNoTlp.getText()+"',ket='"+txtKet.getText()+"' WHERE kd_supplier='"+txtKodeSupplier.getText()+"'"," Updated!!");
        tampilDataTabel();
        ClearText();
   }catch(Exception sqle){
            
   JOptionPane.showMessageDialog(rootPane,"Failed updated data! "+sqle.getMessage());
   }
}

void Delete(){
   try{
       kon.QUERY ("DELETE FROM tb_supplier WHERE kd_supplier='"+txtKodeSupplier.getText()+"' "," Deleted");
       tampilDataTabel();
       ClearText();
   }catch(Exception sqle){          
       JOptionPane.showMessageDialog(rootPane, "Failed deleted data! "+sqle.getMessage());
   }
}

void Search(){
   removeTable();            
   try{                
       con=kon.open();
       st=con.createStatement();
       query="SELECT * FROM tb_supplier WHERE "+cboKat.getSelectedItem()+" LIKE '%"+txtCari.getText()+"%' ";
       ResultSet rs=st.executeQuery(query);               
       while(rs.next()){                  
           String skd_sup =rs.getString("kd_supplier");
           String snama_sup =rs.getString("nama_supplier");
           String snotlp =rs.getString("no_tlp");
           String sket =rs.getString("ket");     
           txtKodeSupplier.setText(skd_sup);
           txtNamaSupplier.setText(snama_sup);
           txtNoTlp.setText(snotlp);
           txtKet.setText(sket);
                  
           String data []={skd_sup,snama_sup,snotlp,sket};
           tabMode.addRow(data);                   
        }
     }catch(SQLException sqle){
           JOptionPane.showMessageDialog(null, "Failed show data! "+sqle.getMessage());
     }
}

void ClearText(){
   txtKodeSupplier.setText("");
   txtNamaSupplier.setText("");
   txtNoTlp.setText("");
   txtKet.setText("");
   txtKodeSupplier.requestFocus();
   btnUpdate.setEnabled(false);
   btnDelete.setEnabled(false);
   btnSave.setEnabled(false);
}

void ShowDataTabelKeText(){
   txtKodeSupplier.setText(String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(),0)));
   txtNamaSupplier.setText(String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(),1)));
   txtNoTlp.setText(String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 2)));  
   txtKet.setText(String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 3)));  
   txtKodeSupplier.setEditable(false);
   txtNamaSupplier.setEditable(true);
   txtNoTlp.setEditable(true);
   txtKet.setEditable(true);
   txtNamaSupplier.requestFocus();
            
   btnUpdate.setEnabled(true);
   btnDelete.setEnabled(true);
}

private void tampilDataTabel(){
removeTable();
try{
       con=kon.open();
       st=con.createStatement();
       query="SELECT * FROM tb_supplier";
       ResultSet rs=st.executeQuery(query);
       while(rs.next()){
            String a=rs.getString("kd_supplier");
            String b=rs.getString("nama_supplier");
            String c=rs.getString("no_tlp");
            String d=rs.getString("ket");
                
            String data []={a,b,c,d};
            tabMode.addRow(data);
       }
            
       }catch(SQLException sqle){
           JOptionPane.showMessageDialog(null,"Failed show data! "+sqle);
            
    }
}

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
        txtNoTlp = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtKet = new javax.swing.JTextArea();
        txtKodeSupplier = new javax.swing.JTextField();
        txtNamaSupplier = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        btnPrint = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnNew = new javax.swing.JButton();
        btnRefresh = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        cboKat = new javax.swing.JComboBox();
        btnSearch = new javax.swing.JButton();
        txtCari = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtNoTlp.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        txtNoTlp.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtNoTlp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNoTlpActionPerformed(evt);
            }
        });
        jPanel1.add(txtNoTlp, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 200, 161, -1));

        txtKet.setColumns(20);
        txtKet.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        txtKet.setRows(5);
        jScrollPane2.setViewportView(txtKet);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 240, -1, -1));

        txtKodeSupplier.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jPanel1.add(txtKodeSupplier, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 120, 111, -1));

        txtNamaSupplier.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        txtNamaSupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNamaSupplierActionPerformed(evt);
            }
        });
        jPanel1.add(txtNamaSupplier, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 160, 161, -1));

        jLabel4.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel4.setText("No Telepon");
        jLabel4.setToolTipText("");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, -1, -1));

        jLabel5.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel5.setText("Keterangan");
        jLabel5.setToolTipText("");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, -1, -1));

        jLabel2.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel2.setText("Kode Supplier");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, -1, -1));

        jLabel3.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel3.setText("Nama Supplier");
        jLabel3.setToolTipText("");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, -1, -1));

        jTable1.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
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

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 390, 850, 122));

        btnUpdate.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        btnUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/edit-icon.png"))); // NOI18N
        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });
        jPanel1.add(btnUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 340, -1, -1));

        btnDelete.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/delete.png"))); // NOI18N
        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        jPanel1.add(btnDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 340, -1, -1));

        btnClear.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        btnClear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/edit-clear.png"))); // NOI18N
        btnClear.setText("Clear");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });
        jPanel1.add(btnClear, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 340, -1, -1));

        btnPrint.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        btnPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Printer2.png"))); // NOI18N
        btnPrint.setText("Print");
        btnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintActionPerformed(evt);
            }
        });
        jPanel1.add(btnPrint, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 340, -1, 30));

        btnExit.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        btnExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Close.png"))); // NOI18N
        btnExit.setText("Exit");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });
        jPanel1.add(btnExit, new org.netbeans.lib.awtextra.AbsoluteConstraints(773, 340, 90, -1));

        btnSave.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        btnSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/save.png"))); // NOI18N
        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });
        jPanel1.add(btnSave, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 340, -1, 30));

        btnNew.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        btnNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/document-new.png"))); // NOI18N
        btnNew.setText("New");
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });
        jPanel1.add(btnNew, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 340, -1, -1));

        btnRefresh.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        btnRefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/gnome_session_reboot.png"))); // NOI18N
        btnRefresh.setText("Refresh");
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });
        jPanel1.add(btnRefresh, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 530, -1, -1));

        jLabel6.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Cari Berdasarkan");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 530, -1, -1));

        cboKat.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        cboKat.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "kd_supplier", "nama_supplier", "no_tlp", "ket" }));
        jPanel1.add(cboKat, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 530, 150, 30));

        btnSearch.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        btnSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/old-zoom-original.png"))); // NOI18N
        btnSearch.setText("Search");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });
        jPanel1.add(btnSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 530, -1, -1));

        txtCari.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jPanel1.add(txtCari, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 530, 260, 30));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/BgInputSup.jpg"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 570, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNamaSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNamaSupplierActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNamaSupplierActionPerformed

    private void txtNoTlpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNoTlpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNoTlpActionPerformed

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
New();        // TODO add your handling code here:
    }//GEN-LAST:event_btnNewActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
save();        // TODO add your handling code here:
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
Update();        // TODO add your handling code here:
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
Delete();        // TODO add your handling code here:
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
ClearText();        // TODO add your handling code here:
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintActionPerformed

        try{
            String namaFile = "./report/rpt_sup.jasper";
            HashMap parameter = new HashMap();
            
            Connection   kon=DriverManager.getConnection("jdbc:mysql://localhost:3306/appinv","root","");
            File laporanFile = new File(namaFile);
            
            JasperReport jasperReport = (JasperReport)JRLoader.loadObject(laporanFile.getPath());
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameter, kon);
            JasperViewer.viewReport(jasperPrint, false);
            JasperViewer.setDefaultLookAndFeelDecorated(true);
            
        }catch (Exception ex){
            JOptionPane.showMessageDialog(null, "Laporan Tidak dapat dicetak!", "Cetak data", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btnPrintActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_btnExitActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
Search();        // TODO add your handling code here:
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
ClearText();
        tampilDataTabel();        // TODO add your handling code here:
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
ShowDataTabelKeText();        // TODO add your handling code here:
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
            java.util.logging.Logger.getLogger(FrmSupplier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmSupplier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmSupplier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmSupplier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new FrmSupplier().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnPrint;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox cboKat;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtCari;
    private javax.swing.JTextArea txtKet;
    private javax.swing.JTextField txtKodeSupplier;
    private javax.swing.JTextField txtNamaSupplier;
    private javax.swing.JTextField txtNoTlp;
    // End of variables declaration//GEN-END:variables
}
