/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Transaksi;
import Koneksi.Koneksi;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.math.BigInteger;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class FrmBPPB extends javax.swing.JFrame {
    String header[]={"Kode RM","Nama RM","Satuan","Jml Terima","No PO","No CA"};
    String header2[]={"Kode PM","Nama PM","Satuan","Jml Terima","No PO","No CA"};
    Connection con;
    Statement st;
    Koneksi kon=new Koneksi();
    private DefaultTableModel tabMode, tabMode2;
    String query;
    
    int Nomor;
    String Kode;
    private Dimension dimensi = Toolkit.getDefaultToolkit().getScreenSize();
    
public FrmBPPB() {
    initComponents();
    TidakAktif();
    this.setLocationRelativeTo(rootPane);
}

public String tgl(){
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    return sdf.format(new Date());
}
    
void Aktif(){   
   btnSave.setEnabled(true);
   btnSave1.setEnabled(true);
   btnAddItemPM.setEnabled(true);
   btnAddItemRM.setEnabled(true);
   btnPrint.setEnabled(true);
   btnPrint1.setEnabled(true);
   btnCariPM.setEnabled(true);
   btnCariRM.setEnabled(true);
   btnCariSup.setEnabled(true);
   
   txtNoSJ.setEditable(true);
   txtJmlTerRM.setEditable(true);
   txtJmlTerPM.setEditable(true);
   txtNoPORM.setEditable(true);
   txtNoPOPM.setEditable(true);
   txtNoCARM.setEditable(true);
   txtNoCAPM.setEditable(true);
   txtNoSJ.requestFocus();
   jDateChooser1.setEnabled(true);
}

void TidakAktif(){
   txtNoSJ.setEditable(false);
   txtJmlTerRM.setEditable(false);
   txtJmlTerPM.setEditable(false);
   txtNoPORM.setEditable(false);
   txtNoPOPM.setEditable(false);
   txtNoCARM.setEditable(false);
   txtNoCAPM.setEditable(false);
   
   btnSave.setEnabled(false);
   btnSave1.setEnabled(false);
   btnPrint.setEnabled(false);
   btnPrint1.setEnabled(false);
   btnAddItemPM.setEnabled(false);
   btnAddItemRM.setEnabled(false);
   btnHapusRM.setEnabled(false);
   btnHapusPM.setEnabled(false);
   btnCariPM.setEnabled(false);
   btnCariRM.setEnabled(false);
   btnCariSup.setEnabled(false);
   jDateChooser1.setEnabled(false);
}


public void removeTable() {
   try{
       for(int t=tabMode.getRowCount();t>0;t--){tabMode.removeRow(0);}            
   }
   catch(Exception ex){System.out.println(ex);}
}

public void removeTable2() {
    try{
        for(int t=tabMode2.getRowCount();t>0;t--){tabMode2.removeRow(0);}            
    }
    catch(Exception ex){System.out.println(ex);}
}

void save(){
    java.text.SimpleDateFormat fmt = new java.text.SimpleDateFormat("yyyy-MM-dd");
    String formattedDate1 = fmt.format(jDateChooser1.getDate());
    try{
        kon.QUERY("INSERT INTO tb_bppb (no_dok_bppb,tgl_dok,kd_supplier,no_sj,tgl_sj) VALUES ('"+txtNoBPPB.getText()+"','"+txtTglBPPB.getText()+"','"+txtKodeSupplier.getText()+"','"+txtNoSJ.getText()+"','"+formattedDate1+"')"," Saved!!");        
        ClearTextHeader();
    }catch(Exception sqle){
    JOptionPane.showMessageDialog(rootPane,"Failed saved data! "+sqle.getMessage());
    }
}

void save2(){
    java.text.SimpleDateFormat fmt = new java.text.SimpleDateFormat("yyyy-MM-dd");
    String formattedDate1 = fmt.format(jDateChooser1.getDate());
    try{
        kon.QUERY("INSERT INTO tb_bppb2 (no_dok_bppb,tgl_dok,kd_supplier,no_sj,tgl_sj) VALUES ('"+txtNoBPPB.getText()+"','"+txtTglBPPB.getText()+"','"+txtKodeSupplier.getText()+"','"+txtNoSJ.getText()+"','"+formattedDate1+"')"," Saved!!");        
        ClearTextHeader();
    }catch(Exception sqle){
    JOptionPane.showMessageDialog(rootPane,"Failed saved data! "+sqle.getMessage());
    }
}

void ClearTextHeader(){
    txtTglBPPB.setText("");
    txtNoSJ.setText("");
    txtKodeSupplier.setText("");
    txtNamaSup.setText("");
    jDateChooser1.setDate(null);
}

void savedetailRM(){
    try{
        con=kon.open();
        st=con.createStatement();
        query="SELECT * FROM tb_bppb_rm WHERE no_dok_bppb = '"+txtNoBPPB.getText()+"' and kd_rm like '%"+txtKodeRM.getText()+"%' ";
        ResultSet rs=st.executeQuery(query);               
        if(rs.last()){                  
            JOptionPane.showMessageDialog(rootPane, "Data RM sudah pernah diinput, periksa lagi penginputan anda!!!");  
            ClearTextRM();
        }else{
            kon.QUERY("INSERT INTO tb_bppb_rm (no_dok_bppb,kd_rm,jml_rm_ter,no_po,no_ca) VALUES ('"+txtNoBPPB.getText()+"','"+txtKodeRM.getText().toString()+"','"+txtJmlTerRM.getText()+"','"+txtNoPORM.getText()+"','"+txtNoCARM.getText()+"')"," Saved!!");
            String sq="update `tb_master_rm` set `stock`=`stock`+"+txtJmlTerRM.getText()+"  WHERE `kd_rm`='"+txtKodeRM.getText().toString()+"'";
            kon.QUERY2(sq);
            ClearTextRM();
            tampilDataTabelRM();
        }
    }catch(Exception sqle){
        JOptionPane.showMessageDialog(rootPane,"Failed saved data! "+sqle.getMessage());
    }
}

void ClearTextRM(){
    txtKodeRM.setText("");
    txtNamaRM.setText("");
    txtSatuanRM.setText("");
    txtJmlTerRM.setText("");
    txtNoPORM.setText("");
    txtNoCARM.setText("");
}

void savedetailPM(){
     try{
     con=kon.open();
       st=con.createStatement();
       query="SELECT * FROM tb_bppb_pm WHERE no_dok_bppb = '"+txtNoBPPB.getText()+"' and kd_pm like '%"+txtKodePM.getText()+"%' ";
       ResultSet rs=st.executeQuery(query);               
       if(rs.last()){                  
       JOptionPane.showMessageDialog(rootPane, "Data sudah pernah diinput, periksa lagi penginputan anda!!!");  
       ClearTextPM();
       }else{
           kon.QUERY("INSERT INTO tb_bppb_pm (no_dok_bppb,kd_pm,jml_pm_ter,no_po,no_ca) VALUES ('"+txtNoBPPB.getText()+"','"+txtKodePM.getText()+"','"+txtJmlTerPM.getText()+"','"+txtNoPOPM.getText()+"','"+txtNoCAPM.getText()+"')"," Saved!!");        
           String sq="update `tb_master_pm` set `stock`=`stock`+"+txtJmlTerPM.getText()+"  WHERE `kd_pm`='"+txtKodePM.getText().toString()+"'";
           kon.QUERY2(sq);
           ClearTextPM();
           tampilDataTabelPM();
       }
     }catch(Exception sqle){
         JOptionPane.showMessageDialog(rootPane,"Failed saved data! "+sqle.getMessage());
     }
}

void ClearTextPM(){
    txtKodePM.setText("");
    txtNamaPM.setText("");
    txtSatuanPM.setText("");
    txtJmlTerPM.setText("");
    txtNoPOPM.setText("");
    txtNoCAPM.setText("");
}

private void tampilDataTabelRM(){
    removeTable();
    try{
        con=kon.open();
        st=con.createStatement();
        query="SELECT a.kd_rm, b.nama_rm, b.satuan, a.jml_rm_ter, a.no_po, a.no_ca FROM tb_bppb_rm as a, tb_master_rm as b WHERE no_dok_bppb='"+ txtNoBPPB.getText() +"' and a.kd_rm = b.kd_rm";
        ResultSet rs=st.executeQuery(query);
        while(rs.next()){
            String a=rs.getString("kd_rm");
            String b=rs.getString("nama_rm");
            String c=rs.getString("satuan");
            String d=rs.getString("jml_rm_ter");
            String e=rs.getString("no_po");
            String f=rs.getString("no_ca");
                
            String data []={a,b,c,d,e,f};
            tabMode.addRow(data);
        }
    }catch(SQLException sqle){
        JOptionPane.showMessageDialog(null,"Failed show data!"+sqle);
    }
}

private void tampilDataTabelPM(){
    removeTable2();
    try{
        con=kon.open();
        st=con.createStatement();
        query="SELECT a.kd_pm, b.nama_pm, b.satuan, a.jml_pm_ter, a.no_po, a.no_ca FROM tb_bppb_pm as a, tb_master_pm as b WHERE no_dok_bppb='"+ txtNoBPPB.getText() +"' and a.kd_pm = b.kd_pm";
        ResultSet rs=st.executeQuery(query);
        while(rs.next()){
            String a=rs.getString("kd_pm");
            String b=rs.getString("nama_pm");
            String c=rs.getString("satuan");
            String d=rs.getString("jml_pm_ter");
            String e=rs.getString("no_po");
            String f=rs.getString("no_ca");
                
            String data []={a,b,c,d,e,f};
            tabMode2.addRow(data);
        }
    }catch(SQLException sqle){
        JOptionPane.showMessageDialog(null,"Failed show data!"+sqle);
    }
}

public static void tampilBPPBRM(String fileReport, String ket) {
    HashMap param = new HashMap();
    param.put("bppb_rm", ket);
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

public static void tampilBPPBPM(String fileReport, String ket) {
		HashMap param = new HashMap();
		param.put("bppb_pm", ket);
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

public void autoCode(){
    try {
        con=kon.open();
        st=con.createStatement();
        String query = "SELECT no_dok_bppb FROM tb_bppb Order By no_dok_bppb Desc";
        ResultSet rs = st.executeQuery(query);
        if(rs.next()) {
            Kode=rs.getString("no_dok_bppb");
            Kode=Kode.substring(5,8);
            Nomor=Integer.parseInt(Kode);
            Nomor=Nomor+1;
            if(Nomor < 10){
                Kode="BPPB-00"+Nomor;
            }
            else if(Nomor < 100){
                Kode="BPPB-0"+Nomor;
            }
            else if(Nomor < 1000){
                Kode="BPPB-"+Nomor;
            }
            else if(Nomor < 10000){
                Kode="BPPB"+Nomor;
            }
            else if(Nomor < 100000){
                Kode="BPP"+Nomor;
            }
            else if(Nomor < 1000000){
                Kode="BP"+Nomor;
            }
            else if(Nomor < 10000000){
                Kode="B"+Nomor;
            }
            else {
                Kode="BPPB"+Nomor;
            }
            txtNoBPPB.setText (Kode);
        }else{
            txtNoBPPB.setText ("BPPB-001");
        }
    }
    catch(SQLException e) {JOptionPane.showMessageDialog(null, "Not connected!");
    }
}

public void autoCode2(){
    try {
        con=kon.open();
        st=con.createStatement();
        String query = "SELECT no_dok_bppb FROM tb_bppb2 Order By no_dok_bppb Desc";
        ResultSet rs = st.executeQuery(query);
        if(rs.next()) {
            Kode=rs.getString("no_dok_bppb");
            Kode=Kode.substring(6,9);
            Nomor=Integer.parseInt(Kode);
            Nomor=Nomor+1;
        if(Nomor < 10){
            Kode="RR/PM/00"+Nomor; }
        else if(Nomor < 100){
            Kode="RR/PM/0"+Nomor;}
        else if(Nomor < 1000){
            Kode="RR/PM/"+Nomor;}
        else if(Nomor < 10000){
            Kode="RR/PM"+Nomor;}
        else if(Nomor < 100000){
            Kode="RR/P"+Nomor;}
        else if(Nomor < 1000000){
            Kode="RR/"+Nomor;}
        else if(Nomor < 10000000){
            Kode="RR"+Nomor;}
        else if(Nomor < 100000000){
            Kode="R"+Nomor;}
        else {
            Kode="RR/PM"+Nomor;}
            txtNoBPPB.setText (Kode);
        }else {
            txtNoBPPB.setText ("RR/PM/001");
        }
    }
    catch(SQLException e) {JOptionPane.showMessageDialog(null, "Not connected!");}
}


public void autoCode3(){
    try {
        con=kon.open();
        st=con.createStatement();
        String query = "SELECT no_dok_bppb FROM tb_bppb Order By no_dok_bppb Desc";
        ResultSet rs = st.executeQuery(query);
        if(rs.next()) {
            Kode=rs.getString("no_dok_bppb");
            Kode=Kode.substring(6,9);
            Nomor=Integer.parseInt(Kode);
            Nomor=Nomor+1;
            if(Nomor < 10){
                Kode="RR/RM/00"+Nomor; }
            else if(Nomor < 100){
                Kode="RR/RM/0"+Nomor;}
            else if(Nomor < 1000){
                Kode="RR/RM/"+Nomor;}
            else if(Nomor < 10000){
                Kode="RR/RM"+Nomor;}
            else if(Nomor < 100000){
                Kode="RR/R"+Nomor;}
            else if(Nomor < 1000000){
                Kode="RR/"+Nomor;}
            else if(Nomor < 10000000){
                Kode="RR"+Nomor;}
            else if(Nomor < 100000000){
                Kode="R"+Nomor;}
            else {
                Kode="RR/RM"+Nomor;}
                txtNoBPPB.setText (Kode);
            }else {
                txtNoBPPB.setText ("RR/RM/001");
            }
    }
    catch(SQLException e) {
        JOptionPane.showMessageDialog(null, "Not connected!");
    }
}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txtNoSJ = new javax.swing.JTextField();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel9 = new javax.swing.JLabel();
        txtNoBPPB = new javax.swing.JTextField();
        txtTglBPPB = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtNamaSup = new javax.swing.JTextField();
        txtKodeSupplier = new javax.swing.JTextField();
        btnCariSup = new javax.swing.JButton();
        jTabbedPane4 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtNamaRM = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtSatuanRM = new javax.swing.JTextField();
        txtJmlTerRM = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        btnAddItemRM = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        btnHapusRM = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        btnNew = new javax.swing.JButton();
        btnPrint = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        txtKodeRM = new javax.swing.JTextField();
        btnCariRM = new javax.swing.JButton();
        txtNoPORM = new javax.swing.JTextField();
        txtNoCARM = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtNamaPM = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        txtSatuanPM = new javax.swing.JTextField();
        txtJmlTerPM = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        btnAddItemPM = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel21 = new javax.swing.JLabel();
        btnHapusPM = new javax.swing.JButton();
        btnExit1 = new javax.swing.JButton();
        btnNew1 = new javax.swing.JButton();
        btnPrint1 = new javax.swing.JButton();
        btnSave1 = new javax.swing.JButton();
        btnReset1 = new javax.swing.JButton();
        txtKodePM = new javax.swing.JTextField();
        btnCariPM = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel();
        txtNoPOPM = new javax.swing.JTextField();
        txtNoCAPM = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel3.setBackground(new java.awt.Color(0, 0, 102));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel2.setText("No BPPB");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, 60, 30));

        jLabel3.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel3.setText("Tanggal BPPB");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 110, -1, -1));

        jLabel17.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel17.setText("No SJ");
        jPanel3.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, -1, -1));

        txtNoSJ.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jPanel3.add(txtNoSJ, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 150, 210, -1));

        jDateChooser1.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jPanel3.add(jDateChooser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 190, 210, -1));

        jLabel9.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel9.setText("Tanggal SJ");
        jPanel3.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 190, -1, -1));

        txtNoBPPB.setEditable(false);
        txtNoBPPB.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jPanel3.add(txtNoBPPB, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 110, 210, -1));

        txtTglBPPB.setEditable(false);
        txtTglBPPB.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jPanel3.add(txtTglBPPB, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 110, 140, 30));

        jLabel5.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel5.setText("Kode Pengirim");
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 150, -1, -1));

        jLabel6.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel6.setText("Nama Pengirim");
        jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 190, -1, -1));

        txtNamaSup.setEditable(false);
        txtNamaSup.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jPanel3.add(txtNamaSup, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 190, 280, 30));

        txtKodeSupplier.setEditable(false);
        txtKodeSupplier.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        txtKodeSupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKodeSupplierActionPerformed(evt);
            }
        });
        jPanel3.add(txtKodeSupplier, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 150, 240, 30));

        btnCariSup.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        btnCariSup.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/icon_search.gif"))); // NOI18N
        btnCariSup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariSupActionPerformed(evt);
            }
        });
        jPanel3.add(btnCariSup, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 150, 29, -1));

        jTabbedPane4.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        jTabbedPane4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane4MouseClicked(evt);
            }
        });

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel8.setText("Nama RM");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, -1));

        jLabel13.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel13.setText("Kode RM");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        txtNamaRM.setEditable(false);
        txtNamaRM.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jPanel1.add(txtNamaRM, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 50, 210, -1));

        jLabel14.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel14.setText("Satuan");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, -1, -1));

        txtSatuanRM.setEditable(false);
        txtSatuanRM.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        txtSatuanRM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSatuanRMActionPerformed(evt);
            }
        });
        jPanel1.add(txtSatuanRM, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 90, 55, -1));

        txtJmlTerRM.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jPanel1.add(txtJmlTerRM, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 130, 210, -1));

        jLabel16.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel16.setText("Jumlah Terima");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, -1, -1));

        btnAddItemRM.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        btnAddItemRM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/addicon.jpg"))); // NOI18N
        btnAddItemRM.setText("Add Item");
        btnAddItemRM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddItemRMActionPerformed(evt);
            }
        });
        jPanel1.add(btnAddItemRM, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 280, 120, 30));

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
        tabMode=new DefaultTableModel(null,header);
        jTable2.setModel(tabMode);
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 10, 610, 252));

        jLabel4.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel4.setText("No. CA");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, -1, -1));

        btnHapusRM.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        btnHapusRM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/edit_delete.png"))); // NOI18N
        btnHapusRM.setText("Delete");
        btnHapusRM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusRMActionPerformed(evt);
            }
        });
        jPanel1.add(btnHapusRM, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 320, 120, -1));

        btnExit.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        btnExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Close.png"))); // NOI18N
        btnExit.setText("Exit");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });
        jPanel1.add(btnExit, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 360, -1, 30));

        btnNew.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        btnNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/document-new.png"))); // NOI18N
        btnNew.setText("New");
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });
        jPanel1.add(btnNew, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 360, -1, -1));

        btnPrint.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        btnPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Printer.png"))); // NOI18N
        btnPrint.setText("Print");
        btnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintActionPerformed(evt);
            }
        });
        jPanel1.add(btnPrint, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 360, -1, -1));

        btnSave.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        btnSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/save.png"))); // NOI18N
        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });
        jPanel1.add(btnSave, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 360, -1, 30));

        btnReset.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        btnReset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/gnome_session_reboot.png"))); // NOI18N
        btnReset.setText("Reset");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });
        jPanel1.add(btnReset, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 360, -1, -1));

        txtKodeRM.setEditable(false);
        txtKodeRM.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jPanel1.add(txtKodeRM, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 10, 170, -1));

        btnCariRM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/icon_search.gif"))); // NOI18N
        btnCariRM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariRMActionPerformed(evt);
            }
        });
        jPanel1.add(btnCariRM, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 10, 30, -1));

        txtNoPORM.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jPanel1.add(txtNoPORM, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 170, 210, -1));

        txtNoCARM.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jPanel1.add(txtNoCARM, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 210, 210, -1));

        jLabel7.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel7.setText("No. PO");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, -1, -1));

        jTabbedPane4.addTab("Raw Material", jPanel1);

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel11.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel11.setText("Nama PM");
        jPanel4.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, -1));

        jLabel15.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel15.setText("Kode PM");
        jPanel4.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        txtNamaPM.setEditable(false);
        txtNamaPM.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jPanel4.add(txtNamaPM, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 50, 210, -1));

        jLabel19.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel19.setText("Satuan");
        jPanel4.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, -1, -1));

        txtSatuanPM.setEditable(false);
        txtSatuanPM.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        txtSatuanPM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSatuanPMActionPerformed(evt);
            }
        });
        jPanel4.add(txtSatuanPM, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 90, 55, -1));

        txtJmlTerPM.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jPanel4.add(txtJmlTerPM, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 130, 210, -1));

        jLabel20.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel20.setText("Jumlah Terima");
        jPanel4.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, -1, -1));

        btnAddItemPM.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        btnAddItemPM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/addicon.jpg"))); // NOI18N
        btnAddItemPM.setText("Add Item");
        btnAddItemPM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddItemPMActionPerformed(evt);
            }
        });
        jPanel4.add(btnAddItemPM, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 280, 120, 30));

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
        tabMode2=new DefaultTableModel(null,header2);
        jTable1.setModel(tabMode2);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(jTable1);

        jPanel4.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 10, 610, 252));

        jLabel21.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel21.setText("No. CA");
        jPanel4.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, -1, -1));

        btnHapusPM.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        btnHapusPM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/edit_delete.png"))); // NOI18N
        btnHapusPM.setText("Delete");
        btnHapusPM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusPMActionPerformed(evt);
            }
        });
        jPanel4.add(btnHapusPM, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 320, 120, -1));

        btnExit1.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        btnExit1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Close.png"))); // NOI18N
        btnExit1.setText("Exit");
        btnExit1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExit1ActionPerformed(evt);
            }
        });
        jPanel4.add(btnExit1, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 360, -1, 30));

        btnNew1.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        btnNew1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/document-new.png"))); // NOI18N
        btnNew1.setText("New");
        btnNew1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNew1ActionPerformed(evt);
            }
        });
        jPanel4.add(btnNew1, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 360, -1, -1));

        btnPrint1.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        btnPrint1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Printer.png"))); // NOI18N
        btnPrint1.setText("Print");
        btnPrint1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrint1ActionPerformed(evt);
            }
        });
        jPanel4.add(btnPrint1, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 360, -1, -1));

        btnSave1.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        btnSave1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/save.png"))); // NOI18N
        btnSave1.setText("Save");
        btnSave1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSave1ActionPerformed(evt);
            }
        });
        jPanel4.add(btnSave1, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 360, -1, 30));

        btnReset1.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        btnReset1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/gnome_session_reboot.png"))); // NOI18N
        btnReset1.setText("Reset");
        btnReset1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReset1ActionPerformed(evt);
            }
        });
        jPanel4.add(btnReset1, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 360, -1, -1));

        txtKodePM.setEditable(false);
        txtKodePM.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jPanel4.add(txtKodePM, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 10, 170, -1));

        btnCariPM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/icon_search.gif"))); // NOI18N
        btnCariPM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariPMActionPerformed(evt);
            }
        });
        jPanel4.add(btnCariPM, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 10, 30, -1));

        jLabel22.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel22.setText("No. PO");
        jPanel4.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, -1, -1));

        txtNoPOPM.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jPanel4.add(txtNoPOPM, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 170, 210, -1));

        txtNoCAPM.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jPanel4.add(txtNoCAPM, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 210, 210, -1));

        jPanel2.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 410));

        jTabbedPane4.addTab("Packaging Material", jPanel2);

        jPanel3.add(jTabbedPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 230, 970, 440));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/BgInputBPPB.jpg"))); // NOI18N
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 680));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtSatuanRMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSatuanRMActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSatuanRMActionPerformed

    private void btnAddItemRMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddItemRMActionPerformed
        if (JOptionPane.showConfirmDialog(null, "Simpan Data?", "Konfirmasi", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
            savedetailRM();    
        }
    }//GEN-LAST:event_btnAddItemRMActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        save();
        removeTable();
        removeTable2();
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        Aktif();
        autoCode3();
        txtTglBPPB.setText(tgl());
    }//GEN-LAST:event_btnNewActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        this.dispose();   
    }//GEN-LAST:event_btnExitActionPerformed

    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintActionPerformed
        String kdpsn = txtNoBPPB.getText().toString();
        tampilBPPBRM("./report/rpt_bppb_rm.jasper", kdpsn);    
    }//GEN-LAST:event_btnPrintActionPerformed

    private void btnCariSupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariSupActionPerformed
        carisupplier cari = new carisupplier(this, true);
        cari.setVisible(true);
        carisupplierclass cr=cari.gettabledata();
        if(cr!=null){
            txtKodeSupplier.setText(cr.getid());
            txtNamaSup.setText(cr.getnama());
        }
    }//GEN-LAST:event_btnCariSupActionPerformed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        txtKodeRM.setText(String.valueOf(jTable2.getValueAt(jTable2.getSelectedRow(),0)));
        txtNamaRM.setText(String.valueOf(jTable2.getValueAt(jTable2.getSelectedRow(),1)));
        txtSatuanRM.setText(String.valueOf(jTable2.getValueAt(jTable2.getSelectedRow(),2)));
        txtJmlTerRM.setText(String.valueOf(jTable2.getValueAt(jTable2.getSelectedRow(),3)));
        txtNoPORM.setText(String.valueOf(jTable2.getValueAt(jTable2.getSelectedRow(),4)));
        txtNoCARM.setText(String.valueOf(jTable2.getValueAt(jTable2.getSelectedRow(),5)));

        btnAddItemRM.setEnabled(false);
        btnHapusRM.setEnabled(true);
    }//GEN-LAST:event_jTable2MouseClicked

    private void btnHapusRMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusRMActionPerformed
        try{
            kon.QUERY ("DELETE FROM tb_bppb_rm WHERE kd_rm='"+txtKodeRM.getText()+"' "," deleted!");
            String sq="update `tb_master_rm` set `stock`=`stock`-"+txtJmlTerRM.getText()+"  WHERE `kd_rm`='"+txtKodeRM.getText().toString()+"'";
            kon.QUERY2(sq);
            tampilDataTabelRM();
        }catch(Exception sqle){          
            JOptionPane.showMessageDialog(rootPane, "Failed deleted data! "+sqle.getMessage());
        }    
        btnAddItemRM.setEnabled(true);
        btnHapusRM.setEnabled(false);
        ClearTextRM();
    }//GEN-LAST:event_btnHapusRMActionPerformed

    private void btnCariRMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariRMActionPerformed
        carirm cari = new carirm(this, true);
        cari.setVisible(true);
        carirmclass cr=cari.gettabledata();
        if(cr!=null){
            txtKodeRM.setText(cr.getid());
            txtNamaRM.setText(cr.getnama());
            txtSatuanRM.setText(cr.getsatuan());
        }
    }//GEN-LAST:event_btnCariRMActionPerformed

    private void txtSatuanPMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSatuanPMActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSatuanPMActionPerformed

    private void btnAddItemPMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddItemPMActionPerformed
        if (JOptionPane.showConfirmDialog(null, "Simpan Data?", "Konfirmasi",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
            savedetailPM();   
        }    
    }//GEN-LAST:event_btnAddItemPMActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        txtKodePM.setText(String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(),0)));
        txtNamaPM.setText(String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(),1)));
        txtSatuanPM.setText(String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(),2)));
        txtJmlTerPM.setText(String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(),3)));
        txtNoPOPM.setText(String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(),4)));
        txtNoCAPM.setText(String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(),4)));

        btnAddItemPM.setEnabled(false);
        btnHapusPM.setEnabled(true);
    }//GEN-LAST:event_jTable1MouseClicked

    private void btnHapusPMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusPMActionPerformed
        try{
            kon.QUERY ("DELETE FROM tb_bppb_pm WHERE kd_pm='"+txtKodePM.getText()+"' "," Deleted!");
            String sq="update `tb_master_pm` set `stock`=`stock`-"+txtJmlTerPM.getText()+"  WHERE `kd_pm`='"+txtKodePM.getText().toString()+"'";
            kon.QUERY2(sq);
            tampilDataTabelPM();
        }catch(Exception sqle){          
            JOptionPane.showMessageDialog(rootPane, "Failed deleted data! "+sqle.getMessage());
        }    
        btnAddItemPM.setEnabled(true);
        btnHapusPM.setEnabled(false);
        ClearTextPM();
    }//GEN-LAST:event_btnHapusPMActionPerformed

    private void btnExit1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExit1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnExit1ActionPerformed

    private void btnNew1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNew1ActionPerformed
        Aktif();
        autoCode2();
        txtTglBPPB.setText(tgl()); 
    }//GEN-LAST:event_btnNew1ActionPerformed

    private void btnPrint1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrint1ActionPerformed
        String kdpsn = txtNoBPPB.getText().toString();
        tampilBPPBPM("./report/rpt_bppb_pm.jasper", kdpsn);
    }//GEN-LAST:event_btnPrint1ActionPerformed

    private void btnSave1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSave1ActionPerformed
        save2();
        removeTable();
        removeTable2();        
    }//GEN-LAST:event_btnSave1ActionPerformed

    private void btnCariPMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariPMActionPerformed
        caripm cari = new caripm(this, true);
        cari.setVisible(true);
        caripmclass cr=cari.gettabledata();
        if(cr!=null){
            txtKodePM.setText(cr.getid());
            txtNamaPM.setText(cr.getnama());
            txtSatuanPM.setText(cr.getsatuan());
        }
    }//GEN-LAST:event_btnCariPMActionPerformed

    private void btnReset1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReset1ActionPerformed
        dispose();
        FrmBPPB bppb = new FrmBPPB();
        bppb.setVisible(true);
    }//GEN-LAST:event_btnReset1ActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        dispose();
        FrmBPPB bppb = new FrmBPPB();
        bppb.setVisible(true);
    }//GEN-LAST:event_btnResetActionPerformed

    private void jTabbedPane4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane4MouseClicked
   
    }//GEN-LAST:event_jTabbedPane4MouseClicked

    private void txtKodeSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKodeSupplierActionPerformed
  
    }//GEN-LAST:event_txtKodeSupplierActionPerformed

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
            java.util.logging.Logger.getLogger(FrmBPPB.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmBPPB.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmBPPB.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmBPPB.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new FrmBPPB().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddItemPM;
    private javax.swing.JButton btnAddItemRM;
    private javax.swing.JButton btnCariPM;
    private javax.swing.JButton btnCariRM;
    private javax.swing.JButton btnCariSup;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnExit1;
    private javax.swing.JButton btnHapusPM;
    private javax.swing.JButton btnHapusRM;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnNew1;
    private javax.swing.JButton btnPrint;
    private javax.swing.JButton btnPrint1;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnReset1;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnSave1;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane4;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField txtJmlTerPM;
    private javax.swing.JTextField txtJmlTerRM;
    private javax.swing.JTextField txtKodePM;
    private javax.swing.JTextField txtKodeRM;
    private javax.swing.JTextField txtKodeSupplier;
    private javax.swing.JTextField txtNamaPM;
    private javax.swing.JTextField txtNamaRM;
    private javax.swing.JTextField txtNamaSup;
    private javax.swing.JTextField txtNoBPPB;
    private javax.swing.JTextField txtNoCAPM;
    private javax.swing.JTextField txtNoCARM;
    private javax.swing.JTextField txtNoPOPM;
    private javax.swing.JTextField txtNoPORM;
    private javax.swing.JTextField txtNoSJ;
    private javax.swing.JTextField txtSatuanPM;
    private javax.swing.JTextField txtSatuanRM;
    private javax.swing.JTextField txtTglBPPB;
    // End of variables declaration//GEN-END:variables
}
