/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Transaksi;

import Koneksi.Koneksi;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
//library untuk cetak laporan
import java.io.File;
import java.sql.*;
import java.util.HashMap;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import net.sf.jasperreports.engine.JRException;

/**
 *
 * @author Hilman
 */
public class PPRM extends java.awt.Frame {
    String header[]={"Kode RM","Nama RM","Satuan","Jml Kebutuhan RM","Subtotal"};
    String header2[]={"Kode RM","Nama RM","Satuan","Jml Permintaan RM","Value PPIC"};
    
    Connection con;
    Statement st;
    Koneksi kon=new Koneksi();
    private DefaultTableModel tabMode,tabMode2;
    String query;
    
    private Dimension dimensi = Toolkit.getDefaultToolkit().getScreenSize();
    
    int Nomor;
    String Kode;

    String sHasil,sHasilKembali;
    int iharga,ijumlah,isubtotal;
    int iGrantTotal,iBayar,itotal;

    /**
     * Creates new form Test
     */
    public PPRM() {
        initComponents();
        TidakAktif();
        this.setLocation(dimensi.width/2 - this.getWidth()/2,dimensi.height/2 - this.getHeight()/2);
    }

      private void tampilDataComboMC(){
        try{
            con=kon.open();
            st=con.createStatement();
            query="SELECT * FROM tb_mc_rm";
            ResultSet rs=st.executeQuery(query);
            while(rs.next()){
                cboMCN.addItem(""+ rs.getString("kd_mc_rm"));               
            }
            
            }catch(SQLException sqle){
                JOptionPane.showMessageDialog(null,"Failed show data Kode MC!"+sqle);
            
        }
    }
      
 void Aktif(){
   
   txtNoPPRM.setText("");
   txtDate.setText("");
   cboMCN.setSelectedItem("");
    
   cboMCN.setEditable(true);
   
   tampilDataComboMC();
 }

void TidakAktif(){
   cboMCN.setEditable(false);
   bSave.setEnabled(false);
   bPrint.setEnabled(false);
   bAddNew.setEnabled(false);
}

void New(){
    Aktif();
    bSave.setEnabled(true);
    bAddNew.setEnabled(true);
    bPrint.setEnabled(true);
}
      
private void savepprm(){
     java.text.SimpleDateFormat fmt = new java.text.SimpleDateFormat("yyyy-MM-dd");
    String formattedDate1 = fmt.format(jDateChooser1.getDate());
    String formattedDate2 = fmt.format(jDateChooser2.getDate());
     try{
     kon.QUERY("INSERT INTO tb_pprm (no_dok_pprm,tgl_pprm,kd_mc_rm,delivery_awal,delivery_akhir) VALUES ('"+txtNoPPRM.getText()+"','"+txtDate.getText()+"','"+cboMCN.getSelectedItem()+"','"+formattedDate1+"','"+formattedDate2+"')"," Saved!!");

    }catch(Exception sqle){
    }
}
    
    private void savepprmdetail(){
     try{
         String kode=String.valueOf(jTable3.getValueAt(jTable3.getSelectedRow(),1));
         String qty=String.valueOf(jTable3.getValueAt(jTable3.getSelectedRow(),2));
         String subtot=String.valueOf(jTable3.getValueAt(jTable3.getSelectedRow(),3));

            String asa = "INSERT INTO tb_pprm_detail (no_dok_pprm,kd_rm,jml,subtotal) VALUES ('"+txtNoPPRM.getText()+"','"+ kode +"','"+ qty +"','"+ subtot +"')";
         //kon.QUERY("");        
          JOptionPane.showMessageDialog(null,asa);
         tampilDataTabelPPRMDetil();
    }catch(Exception sqle){
}
        
    }
    
    
    private void tampilDataTabelMCdetail(){
        removeTable();
        try{
            con=kon.open();
            st=con.createStatement();
            query="SELECT a.kd_rm, b.nama_rm, b.satuan, a.jml_rm, a.subtotal FROM tb_mc_rm_detail as a, tb_master_rm as b WHERE kd_mc_rm='"+ cboMCN.getSelectedItem() +"' and a.kd_rm=b.kd_rm";
            ResultSet rs=st.executeQuery(query);
            while(rs.next()){
                String a=rs.getString("kd_rm");
                String b=rs.getString("nama_rm");
                String c=rs.getString("satuan");
                String d=rs.getString("jml_rm");
                String e=rs.getString("subtotal");
                
                String data []={a,b,c,d,e};
                tabMode.addRow(data);
            }
            
            }catch(SQLException sqle){
                JOptionPane.showMessageDialog(null,"Failed show data MC detail!"+sqle);            
        }
    }
    
    private void tampilDataTabelPPRMDetil(){
        removeTable2();
        try{
            con=kon.open();
            st=con.createStatement();
            query="SELECT a.kd_rm, b.nama_rm, b.satuan, a.jml_rm, a.subtotal FROM tb_mc_rm_detail as a, tb_master_rm as b WHERE kd_mc_rm='"+ cboMCN.getSelectedItem() +"' and a.kd_rm=b.kd_rm";
            ResultSet rs=st.executeQuery(query);
            while(rs.next()){
                //String a= txtNoPPRM.getText();
                String a=rs.getString("kd_rm");
                String b=rs.getString("nama_rm");
                String c=rs.getString("satuan");
                String d=rs.getString("jml_rm");
                String e=rs.getString("subtotal");
                
                String urlValue = "jdbc:mysql://localhost/appinv?user=root&password=";
                Connection conn=DriverManager.getConnection(urlValue);
                PreparedStatement pStatement = null;
                String skript = "INSERT INTO tb_pprm_detail (no_dok_pprm,kd_rm,jml,subtotal) VALUES ('"+txtNoPPRM.getText()+"','"+ a +"','"+ d +"','"+ e +"')";
                pStatement = conn.prepareStatement(skript);
                int intTambah= pStatement.executeUpdate();
            
            
                String data []={a,b,c,d,e};
                tabMode2.addRow(data);
            }
            
            }catch(SQLException sqle){
                JOptionPane.showMessageDialog(null,"Failed show data PPRM detail!"+sqle);
            
        }
    }
    
    public void removeTable() {
            try{
                for(int t=tabMode.getRowCount();t>0;t--){tabMode.removeRow(0);}
                
            }catch(Exception ex){System.out.println(ex);
                
            }
   
    }
    
     public void removeTable2() {
            try{
                for(int t=tabMode2.getRowCount();t>0;t--){tabMode2.removeRow(0);}
                
            }catch(Exception ex){System.out.println(ex);
                
            }
   
    }
    
 public String tgl(){
SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
return sdf.format(new Date());
}
     
     void ClearText(){
            txtNoPPRM.setText("");
            txtDate.setText("");
            cboMCN.setSelectedItem("- Select -");
       }

     
     public static void tampilPPRM(String fileReport, String ket) {
		HashMap param = new HashMap();
		param.put("pprm", ket);
		try{

                         Connection kon=DriverManager.getConnection("jdbc:mysql://localhost:3306/appinv","root","");
                
                    File laporanFile = new File(fileReport);
                    JasperReport jasperReport = (JasperReport) JRLoader.loadObject(laporanFile.getPath());
                    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, param, kon);
                    JasperViewer.viewReport(jasperPrint, false);
                    JasperViewer.setDefaultLookAndFeelDecorated(true);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Laporan tidak dapat dicetak!", "Cetak data", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
              } 
	}
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtNoPPRM = new javax.swing.JTextField();
        txtDate = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        cboMCN = new javax.swing.JComboBox();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        bAddNew = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        bNew = new javax.swing.JButton();
        bSave = new javax.swing.JButton();
        bPrint = new javax.swing.JButton();
        bExit = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
        });

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel2.setText("Kode PPRM");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, -1, -1));

        jLabel3.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel3.setText("Tgl Dok");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, -1, -1));

        jLabel4.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel4.setText("MC Number");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, -1, -1));

        jLabel5.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel5.setText("Tgl Delivery");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, -1, -1));

        txtNoPPRM.setEditable(false);
        txtNoPPRM.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jPanel2.add(txtNoPPRM, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 80, 170, -1));

        txtDate.setEditable(false);
        txtDate.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jPanel2.add(txtDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 120, 170, -1));

        jLabel10.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel10.setText("s/d");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 200, -1, -1));

        cboMCN.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        cboMCN.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "- Choose -" }));
        cboMCN.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboMCNItemStateChanged(evt);
            }
        });
        cboMCN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboMCNActionPerformed(evt);
            }
        });
        jPanel2.add(cboMCN, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 160, 170, -1));

        jDateChooser1.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jPanel2.add(jDateChooser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 200, 140, -1));

        jDateChooser2.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jPanel2.add(jDateChooser2, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 200, 126, -1));

        jTable3.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jTable3.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable3.setModel(tabMode);
        jScrollPane3.setViewportView(jTable3);

        jPanel2.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, 470, 102));

        bAddNew.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        bAddNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/input.png"))); // NOI18N
        bAddNew.setText("Move Data");
        bAddNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAddNewActionPerformed(evt);
            }
        });
        jPanel2.add(bAddNew, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 360, 130, -1));

        jTable2.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
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
        tabMode2=new DefaultTableModel(null,header2);
        jTable2.setModel(tabMode2);
        jScrollPane2.setViewportView(jTable2);

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 410, 470, 110));

        bNew.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        bNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/document-new.png"))); // NOI18N
        bNew.setText("New");
        bNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bNewActionPerformed(evt);
            }
        });
        jPanel2.add(bNew, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 540, 90, -1));

        bSave.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        bSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/save.png"))); // NOI18N
        bSave.setText("Save");
        bSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSaveActionPerformed(evt);
            }
        });
        jPanel2.add(bSave, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 540, 90, 30));

        bPrint.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        bPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Printer.png"))); // NOI18N
        bPrint.setText("Print");
        bPrint.setToolTipText("");
        bPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bPrintActionPerformed(evt);
            }
        });
        jPanel2.add(bPrint, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 540, 90, -1));

        bExit.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        bExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Close.png"))); // NOI18N
        bExit.setText("Exit");
        bExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bExitActionPerformed(evt);
            }
        });
        jPanel2.add(bExit, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 540, 90, 30));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/BgInputPPRM.jpg"))); // NOI18N
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 600));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Exit the Application
     */
    private void exitForm(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_exitForm
        System.exit(0);
    }//GEN-LAST:event_exitForm

    private void bNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bNewActionPerformed
        // TODO add your handling code here:
        New();
        autoCode();
        txtDate.setText(tgl());
    }//GEN-LAST:event_bNewActionPerformed

    private void cboMCNItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboMCNItemStateChanged
        try {
            con = kon.open();
            st = con.createStatement();
            query = "SELECT * FROM tb_mc_rm "
                    + " WHERE kd_mc_rm='" + cboMCN.getSelectedItem() + "'";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
            }
            tampilDataTabelMCdetail();

        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Data Gagal Masuk Tabel" + sqle);

        }
    }//GEN-LAST:event_cboMCNItemStateChanged

    private void cboMCNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboMCNActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboMCNActionPerformed

    private void bExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bExitActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_bExitActionPerformed

    private void bSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSaveActionPerformed
        // TODO add your handling code here:
        savepprm();
        removeTable();
        removeTable2();
    }//GEN-LAST:event_bSaveActionPerformed

    private void bPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bPrintActionPerformed
        String kdpsn = txtNoPPRM.getText().toString();
        tampilPPRM("./report/rpt_pprm.jasper", kdpsn);
    }//GEN-LAST:event_bPrintActionPerformed

    private void bAddNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAddNewActionPerformed
        try {
            con = kon.open();
            st = con.createStatement();
            query = "SELECT * FROM tb_mc_rm "
                    + " WHERE kd_mc_rm='" + cboMCN.getSelectedItem() + "'";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
            }
            tampilDataTabelPPRMDetil();

        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Data Gagal Masuk Tabel" + sqle);

        }
    }//GEN-LAST:event_bAddNewActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new PPRM().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bAddNew;
    private javax.swing.JButton bExit;
    private javax.swing.JButton bNew;
    private javax.swing.JButton bPrint;
    private javax.swing.JButton bSave;
    private javax.swing.JComboBox cboMCN;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTextField txtDate;
    private javax.swing.JTextField txtNoPPRM;
    // End of variables declaration//GEN-END:variables
public void autoCode(){
try {
con=kon.open();
st=con.createStatement();
String query = "SELECT no_dok_pprm FROM tb_pprm Order By no_dok_pprm Desc";
ResultSet rs = st.executeQuery(query);
if(rs.next()) {
Kode=rs.getString("no_dok_pprm");
Kode=Kode.substring(4,7);
Nomor=Integer.parseInt(Kode);
Nomor=Nomor+1;
if(Nomor < 10){
Kode="PRM-00"+Nomor; }
else if(Nomor < 100){
Kode="PRM-0"+Nomor;}
else if(Nomor < 1000){
Kode="PRM-"+Nomor;}
else if(Nomor < 10000){
Kode="PRM"+Nomor;}
else if(Nomor < 100000){
Kode="PR"+Nomor;}
else if(Nomor < 1000000){
Kode="P"+Nomor;}
else {
Kode="PRM"+Nomor;}
txtNoPPRM.setText (Kode);
}else {
txtNoPPRM.setText ("PRM-001");
}
}
catch(SQLException e) {JOptionPane.showMessageDialog(null, "Not connected");}
}
}
