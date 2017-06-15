/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Master;
import Transaksi.*;
import Koneksi.Koneksi;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.math.BigInteger;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Hilman
 */
public class FrmBulk extends javax.swing.JFrame {
    String header[]={"Kode RM","Nama RM","Satuan","Qty"};
    String header2[]={"Kode PM","Nama PM","Satuan","Qty"};
    Connection con;
    Statement st;
    Koneksi kon=new Koneksi();
    private DefaultTableModel tabMode, tabMode2;
    String query;
    
    int Nomor;
    String Kode;
    private Dimension dimensi = Toolkit.getDefaultToolkit().getScreenSize();
    
public FrmBulk() {
    initComponents();
    TidakAktif();
    this.setLocationRelativeTo(rootPane);
}
    
void Aktif(){   
   btnSave.setEnabled(true);
   btnSave1.setEnabled(true);
   btnAddItemPM.setEnabled(true);
   btnAddItemRM.setEnabled(true);
   btnCariPM.setEnabled(true);
   btnCariRM.setEnabled(true);
   btnCariMesin.setEnabled(true);
   btnCariPekerja.setEnabled(true);
   cboJenis.setEnabled(true);
   
   txtNamaProd.setEditable(true);
   txtJmlRM.setEditable(true);
   txtJmlPM.setEditable(true);
   txtJmlMesin.setEditable(true);
   txtJmlPekerja.setEditable(true);
   txtNoFormula.setEditable(true);
   txtKdProduk.setEditable(true);
   txtKdProduk.requestFocus();
}

void TidakAktif(){
   txtNamaProd.setEditable(false);
   txtJmlRM.setEditable(false);
   txtJmlPM.setEditable(false);
   txtJmlMesin.setEditable(false);
   txtJmlPekerja.setEditable(false);
   txtNoFormula.setEditable(false);
   txtKdProduk.setEditable(false);
   
   cboSat.setEnabled(false);
   cboJenis.setEnabled(false);
   
   btnSave.setEnabled(false);
   btnSave1.setEnabled(false);
   btnAddItemPM.setEnabled(false);
   btnAddItemRM.setEnabled(false);
   btnHapusRM.setEnabled(false);
   btnHapusPM.setEnabled(false);
   btnCariPM.setEnabled(false);
   btnCariRM.setEnabled(false);
   btnCariMesin.setEnabled(false);
   btnCariPekerja.setEnabled(false);
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
     try{
     kon.QUERY("INSERT INTO tb_bom (id_bom, kd_produk, nama_produk, satuan, jns_produk, proses, kd_mesin, kd_pekerja1, jml_mesin, jml_pekerja1) "
             + "VALUES ('"+txtNoFormula.getText()+"','"+txtKdProduk.getText()+"','"+txtNamaProd.getText()+"','"+cboSat.getSelectedItem()+"','"+cboJenis.getSelectedItem()+"','"+txtProses.getText()+"','"+txtKodeMesin.getText()+"','"+txtKodePekerja.getText()+"','"+txtJmlMesin.getText()+"','"+txtJmlPekerja.getText()+"')"," Saved!!");        
     ClearTextHeader();
    }catch(Exception sqle){
    JOptionPane.showMessageDialog(rootPane,"Failed saved data! "+sqle.getMessage());
    }
}

void save2(){
     try{
     kon.QUERY("INSERT INTO tb_produk (id_bom, kd_produk, nama_produk, satuan, jns_produk, proses, kd_mesin, kd_pekerja1, jml_mesin, jml_pekerja1, wadah)"
             + "VALUES ('"+txtNoFormula.getText()+"','"+txtKdProduk.getText()+"','"+txtNamaProd.getText()+"','"+cboSat.getSelectedItem()+"','"+cboJenis.getSelectedItem()+"','"+txtProses.getText()+"','"+txtKodeMesin.getText()+"','"+txtKodePekerja.getText()+"','"+txtJmlMesin.getText()+"','"+txtJmlPekerja.getText()+"','"+txtWadah.getText()+"')"," Saved!!");        
     ClearTextHeader();
    }catch(Exception sqle){
    JOptionPane.showMessageDialog(rootPane,"Failed saved data! "+sqle.getMessage());
    }
}

void ClearTextHeader(){
    txtKdProduk.setText("");
    txtNamaProd.setText("");
    txtKodeMesin.setText("");
    txtNamaMesin.setText("");
    txtKodePekerja.setText("");
    txtNamaPekerja.setText("");
    txtProses.setText("");
    txtJmlMesin.setText("");
    txtJmlPekerja.setText("");
    txtWadah.setText("");
    cboJenis.setSelectedIndex(0);
    cboSat.setSelectedIndex(0);
}

void savedetailRM(){
    try{
        con=kon.open();
        st=con.createStatement();
        query="SELECT * FROM tb_bom_detail WHERE id_bom = '"+txtNoFormula.getText()+"' and kd_rm like '%"+txtKodeRM.getText()+"%' ";
        ResultSet rs=st.executeQuery(query);               
        if(rs.last()){                  
            JOptionPane.showMessageDialog(rootPane, "Data RM sudah pernah diinput, periksa lagi penginputan anda!!!");  
            ClearTextRM();
        }else{
            kon.QUERY("INSERT INTO tb_bom_detail (id_bom, kd_rm, qty) "
                    + "VALUES ('"+txtNoFormula.getText()+"','"+txtKodeRM.getText().toString()+"','"+txtJmlRM.getText()+"')"," Saved!!");
            tampilDataTabelRM();
            ClearTextRM();
        }
    }catch(Exception sqle){
        JOptionPane.showMessageDialog(rootPane,"Failed saved data! "+sqle.getMessage());
    }
}

void ClearTextRM(){
    txtKodeRM.setText("");
    txtNamaRM.setText("");
    txtSatuanRM.setText("");
    txtJmlRM.setText("");
}

void savedetailPM(){
    try{
        con=kon.open();
        st=con.createStatement();
        query="SELECT * FROM tb_produk_detail WHERE id_bom = '"+txtNoFormula.getText()+"' and kd_pm like '%"+txtKodePM.getText()+"%' ";
        ResultSet rs=st.executeQuery(query);               
        if(rs.last()){                  
            JOptionPane.showMessageDialog(rootPane, "Data PM sudah pernah diinput, periksa lagi penginputan anda!!!");  
            ClearTextPM();
        }else{
            kon.QUERY("INSERT INTO tb_produk_detail (id_bom, kd_pm, qty) "
                    + "VALUES ('"+txtNoFormula.getText()+"','"+txtKodePM.getText()+"','"+txtJmlPM.getText()+"')"," Saved!!");        
            tampilDataTabelPM();
            ClearTextPM();
        }
    }catch(Exception sqle){
        JOptionPane.showMessageDialog(rootPane,"Failed saved data! "+sqle.getMessage());
    }
}

void ClearTextPM(){
    txtKodePM.setText("");
    txtNamaPM.setText("");
    txtSatuanPM.setText("");
    txtJmlPM.setText("");
}

private void tampilDataTabelRM(){
        removeTable();
        try{
            con=kon.open();
            st=con.createStatement();
            query="SELECT a.kd_rm, b.nama_rm, b.satuan, a.qty FROM tb_bom_detail as a, tb_master_rm as b WHERE id_bom='"+ txtNoFormula.getText() +"' and a.kd_rm = b.kd_rm";
            ResultSet rs=st.executeQuery(query);
            while(rs.next()){
                String a=rs.getString("kd_rm");
                String b=rs.getString("nama_rm");
                String c=rs.getString("satuan");
                String d=rs.getString("qty");
                
                String data []={a,b,c,d};
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
        query="SELECT a.kd_pm, b.nama_pm, b.satuan, a.qty FROM tb_produk_detail as a, tb_master_pm as b WHERE id_bom='"+ txtNoFormula.getText() +"' and a.kd_pm = b.kd_pm";
        ResultSet rs=st.executeQuery(query);
        while(rs.next()){
            String a=rs.getString("kd_pm");
            String b=rs.getString("nama_pm");
            String c=rs.getString("satuan");
            String d=rs.getString("qty");
            
            String data []={a,b,c,d};
            tabMode2.addRow(data);
        }
    }catch(SQLException sqle){
        JOptionPane.showMessageDialog(null,"Failed show data!"+sqle);
    }
}


public void autoCode(){
    try{    
        con=kon.open();
        st=con.createStatement();
        query="SELECT id_bom from tb_bom order by id_bom desc";
        ResultSet rs=st.executeQuery(query);
        if (rs.next()){
            int kode = Integer.parseInt(rs.getString("id_bom"))+1;
            txtNoFormula.setText(Integer.toString(kode));
        }else{
            int kode = 1;
            txtNoFormula.setText(Integer.toString(kode));
        }
        rs.close();
    }catch (Exception e){
        JOptionPane.showMessageDialog(null, e);
    }
}

public void autoCode1(){
    try{    
        con=kon.open();
        st=con.createStatement();
        query="SELECT id_bom from tb_produk order by id_bom desc";
        ResultSet rs=st.executeQuery(query);
        if (rs.next()){
            int kode = Integer.parseInt(rs.getString("id_bom"))+1;
            txtNoFormula.setText(Integer.toString(kode));
        }else{
            int kode = 1;
            txtNoFormula.setText(Integer.toString(kode));
        }
        rs.close();
    }catch (Exception e){
        JOptionPane.showMessageDialog(null, e);
    }
}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txtNamaProd = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtNoFormula = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtNamaMesin = new javax.swing.JTextField();
        txtKodeMesin = new javax.swing.JTextField();
        btnCariMesin = new javax.swing.JButton();
        jTabbedPane4 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtNamaRM = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtSatuanRM = new javax.swing.JTextField();
        txtJmlRM = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        btnAddItemRM = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        btnHapusRM = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        btnNew = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        txtKodeRM = new javax.swing.JTextField();
        btnCariRM = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtNamaPM = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        txtSatuanPM = new javax.swing.JTextField();
        txtJmlPM = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        btnAddItemPM = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnHapusPM = new javax.swing.JButton();
        btnExit1 = new javax.swing.JButton();
        btnNew1 = new javax.swing.JButton();
        btnSave1 = new javax.swing.JButton();
        btnReset1 = new javax.swing.JButton();
        txtKodePM = new javax.swing.JTextField();
        btnCariPM = new javax.swing.JButton();
        jLabel23 = new javax.swing.JLabel();
        txtWadah = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        cboJenis = new javax.swing.JComboBox();
        jLabel10 = new javax.swing.JLabel();
        txtJmlMesin = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtKodePekerja = new javax.swing.JTextField();
        txtNamaPekerja = new javax.swing.JTextField();
        btnCariPekerja = new javax.swing.JButton();
        txtJmlPekerja = new javax.swing.JTextField();
        cboSat = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        txtKdProduk = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        txtProses = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel2.setText("Kode Bulk/Produk");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 130, 30));

        jLabel3.setFont(new java.awt.Font("Trebuchet MS", 1, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("INPUT BILL OF MATERIAL");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 30, 430, 30));

        jLabel17.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel17.setText("Nama Bulk/Produk");
        jPanel3.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 120, 30));

        txtNamaProd.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jPanel3.add(txtNamaProd, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 180, 270, 30));

        jLabel9.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel9.setText("Proses");
        jPanel3.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, 90, 30));

        txtNoFormula.setEditable(false);
        txtNoFormula.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jPanel3.add(txtNoFormula, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 100, 70, 30));

        jLabel5.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel5.setText("Kode Mesin");
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 100, -1, 30));

        jLabel6.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel6.setText("Kode Pekerja");
        jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 220, -1, 30));

        txtNamaMesin.setEditable(false);
        txtNamaMesin.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jPanel3.add(txtNamaMesin, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 140, 280, 30));

        txtKodeMesin.setEditable(false);
        txtKodeMesin.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        txtKodeMesin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKodeMesinActionPerformed(evt);
            }
        });
        jPanel3.add(txtKodeMesin, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 100, 240, 30));

        btnCariMesin.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        btnCariMesin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/icon_search.gif"))); // NOI18N
        btnCariMesin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariMesinActionPerformed(evt);
            }
        });
        jPanel3.add(btnCariMesin, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 100, 29, -1));

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

        txtJmlRM.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jPanel1.add(txtJmlRM, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 130, 210, -1));

        jLabel16.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel16.setText("Jumlah /Kg");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, -1, -1));

        btnAddItemRM.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        btnAddItemRM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/addicon.jpg"))); // NOI18N
        btnAddItemRM.setText("Add Item");
        btnAddItemRM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddItemRMActionPerformed(evt);
            }
        });
        jPanel1.add(btnAddItemRM, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 180, 120, 30));

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

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 10, 610, 240));

        btnHapusRM.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        btnHapusRM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/edit_delete.png"))); // NOI18N
        btnHapusRM.setText("Delete");
        btnHapusRM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusRMActionPerformed(evt);
            }
        });
        jPanel1.add(btnHapusRM, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 220, 120, -1));

        btnExit.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        btnExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Close.png"))); // NOI18N
        btnExit.setText("Exit");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });
        jPanel1.add(btnExit, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 260, -1, 30));

        btnNew.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        btnNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/document-new.png"))); // NOI18N
        btnNew.setText("New");
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });
        jPanel1.add(btnNew, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 260, -1, -1));

        btnSave.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        btnSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/save.png"))); // NOI18N
        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });
        jPanel1.add(btnSave, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 260, -1, 30));

        btnReset.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        btnReset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/gnome_session_reboot.png"))); // NOI18N
        btnReset.setText("Reset");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });
        jPanel1.add(btnReset, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 260, -1, -1));

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

        jTabbedPane4.addTab("BULK", jPanel1);

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel11.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel11.setText("Nama PM");
        jPanel4.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, -1, -1));

        jLabel15.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(204, 0, 0));
        jLabel15.setText("Isi Jumlah Wadah/Pcs");
        jPanel4.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, -1));

        txtNamaPM.setEditable(false);
        txtNamaPM.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jPanel4.add(txtNamaPM, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 110, 210, -1));

        jLabel19.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel19.setText("Satuan");
        jPanel4.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, -1, -1));

        txtSatuanPM.setEditable(false);
        txtSatuanPM.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        txtSatuanPM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSatuanPMActionPerformed(evt);
            }
        });
        jPanel4.add(txtSatuanPM, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 150, 55, -1));

        txtJmlPM.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jPanel4.add(txtJmlPM, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 190, 210, -1));

        jLabel20.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel20.setText("Jumlah /Pcs");
        jPanel4.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, -1, -1));

        btnAddItemPM.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        btnAddItemPM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/addicon.jpg"))); // NOI18N
        btnAddItemPM.setText("Add Item");
        btnAddItemPM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddItemPMActionPerformed(evt);
            }
        });
        jPanel4.add(btnAddItemPM, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 220, 120, 30));

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

        jPanel4.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 10, 610, 240));

        btnHapusPM.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        btnHapusPM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/edit_delete.png"))); // NOI18N
        btnHapusPM.setText("Delete");
        btnHapusPM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusPMActionPerformed(evt);
            }
        });
        jPanel4.add(btnHapusPM, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 260, 120, -1));

        btnExit1.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        btnExit1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Close.png"))); // NOI18N
        btnExit1.setText("Exit");
        btnExit1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExit1ActionPerformed(evt);
            }
        });
        jPanel4.add(btnExit1, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 260, -1, 30));

        btnNew1.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        btnNew1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/document-new.png"))); // NOI18N
        btnNew1.setText("New");
        btnNew1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNew1ActionPerformed(evt);
            }
        });
        jPanel4.add(btnNew1, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 260, -1, -1));

        btnSave1.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        btnSave1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/save.png"))); // NOI18N
        btnSave1.setText("Save");
        btnSave1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSave1ActionPerformed(evt);
            }
        });
        jPanel4.add(btnSave1, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 260, -1, 30));

        btnReset1.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        btnReset1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/gnome_session_reboot.png"))); // NOI18N
        btnReset1.setText("Reset");
        btnReset1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReset1ActionPerformed(evt);
            }
        });
        jPanel4.add(btnReset1, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 260, -1, -1));

        txtKodePM.setEditable(false);
        txtKodePM.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jPanel4.add(txtKodePM, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 70, 170, -1));

        btnCariPM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/icon_search.gif"))); // NOI18N
        btnCariPM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariPMActionPerformed(evt);
            }
        });
        jPanel4.add(btnCariPM, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 70, 30, -1));

        jLabel23.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel23.setText("Kode PM");
        jPanel4.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, -1, -1));

        txtWadah.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jPanel4.add(txtWadah, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 30, 170, -1));

        jPanel2.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 410));

        jTabbedPane4.addTab("Finished Goods", jPanel2);

        jPanel3.add(jTabbedPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 330, 970, 340));
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 680));

        cboJenis.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        cboJenis.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-- Select --", "EYE SHADOW", "FRAGRANCE", "BODY LOTION", "BODY SOAP" }));
        jPanel3.add(cboJenis, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 260, 210, 30));

        jLabel10.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel10.setText("Nama Pekerja");
        jPanel3.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 260, -1, 30));

        txtJmlMesin.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        txtJmlMesin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtJmlMesinKeyTyped(evt);
            }
        });
        jPanel3.add(txtJmlMesin, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 180, 280, 30));

        jLabel7.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel7.setText("Jml Pekerja");
        jPanel3.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 300, -1, 30));

        jLabel12.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel12.setText("Nama Mesin");
        jPanel3.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 140, -1, 30));

        jLabel18.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel18.setText("Jml Mesin");
        jPanel3.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 180, -1, 30));

        txtKodePekerja.setEditable(false);
        txtKodePekerja.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        txtKodePekerja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKodePekerjaActionPerformed(evt);
            }
        });
        jPanel3.add(txtKodePekerja, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 220, 240, 30));

        txtNamaPekerja.setEditable(false);
        txtNamaPekerja.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jPanel3.add(txtNamaPekerja, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 260, 280, 30));

        btnCariPekerja.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        btnCariPekerja.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/icon_search.gif"))); // NOI18N
        btnCariPekerja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariPekerjaActionPerformed(evt);
            }
        });
        jPanel3.add(btnCariPekerja, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 220, 29, -1));

        txtJmlPekerja.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        txtJmlPekerja.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtJmlPekerjaKeyTyped(evt);
            }
        });
        jPanel3.add(txtJmlPekerja, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 300, 280, 30));

        cboSat.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        cboSat.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-- Select --", "KG", "PCS" }));
        jPanel3.add(cboSat, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 220, 100, 30));

        jLabel4.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel4.setText("Satuan");
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, -1, 30));

        txtKdProduk.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jPanel3.add(txtKdProduk, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 140, 270, 30));

        jLabel21.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel21.setText("No. Formula");
        jPanel3.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, -1, 30));

        jLabel22.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel22.setText("Jenis Produk");
        jPanel3.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 90, 30));

        txtProses.setEditable(false);
        txtProses.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jPanel3.add(txtProses, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 300, 70, 30));

        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/frmBulk.jpg"))); // NOI18N
        jPanel3.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 680));

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
        if (JOptionPane.showConfirmDialog(null, "Simpan Data?", "Konfirmasi",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
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
        autoCode();
        txtProses.setText("BULK");
        cboSat.setSelectedIndex(1);
        cboSat.setEnabled(false);
    }//GEN-LAST:event_btnNewActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        this.dispose();   
    }//GEN-LAST:event_btnExitActionPerformed

    private void btnCariMesinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariMesinActionPerformed
        carimesin cari = new carimesin(this, true);
        cari.setVisible(true);
        carimesinclass cr=cari.gettabledata();
        if(cr!=null){
            txtKodeMesin.setText(cr.getid());
            txtNamaMesin.setText(cr.getnama());
        }
    }//GEN-LAST:event_btnCariMesinActionPerformed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        txtKodeRM.setText(String.valueOf(jTable2.getValueAt(jTable2.getSelectedRow(),0)));
        txtNamaRM.setText(String.valueOf(jTable2.getValueAt(jTable2.getSelectedRow(),1)));
        txtSatuanRM.setText(String.valueOf(jTable2.getValueAt(jTable2.getSelectedRow(),2)));
        txtJmlRM.setText(String.valueOf(jTable2.getValueAt(jTable2.getSelectedRow(),3)));
            
        btnAddItemRM.setEnabled(false);
        btnHapusRM.setEnabled(true);
    }//GEN-LAST:event_jTable2MouseClicked

    private void btnHapusRMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusRMActionPerformed
        try{
            kon.QUERY ("DELETE FROM tb_bppb_rm WHERE kd_rm='"+txtKodeRM.getText()+"' "," deleted!");
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

    }//GEN-LAST:event_txtSatuanPMActionPerformed

    private void btnAddItemPMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddItemPMActionPerformed
        if (JOptionPane.showConfirmDialog(null, "Simpan Data?", "Konfirmasi",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
            savedetailPM();    
        }    
    }//GEN-LAST:event_btnAddItemPMActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        txtKodePM.setText(String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(),0)));
        txtNamaPM.setText(String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(),1)));
        txtSatuanPM.setText(String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(),2)));
        txtJmlPM.setText(String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(),3)));
            
        btnAddItemPM.setEnabled(false);
        btnHapusPM.setEnabled(true);
    }//GEN-LAST:event_jTable1MouseClicked

    private void btnHapusPMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusPMActionPerformed
        try{
            kon.QUERY ("DELETE FROM tb_bppb_pm WHERE kd_pm='"+txtKodePM.getText()+"' "," Deleted!");
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
        autoCode1();
        txtProses.setText("FG");
        cboSat.setSelectedIndex(2);
        cboSat.setEnabled(false);
    }//GEN-LAST:event_btnNew1ActionPerformed

    private void btnSave1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSave1ActionPerformed
        save2();
        removeTable();
        removeTable2();        // TODO add your handling code here:
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
        FrmBulk bulk = new FrmBulk();
        bulk.setVisible(true);
    }//GEN-LAST:event_btnReset1ActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        dispose();
        FrmBulk bulk = new FrmBulk();
        bulk.setVisible(true);
    }//GEN-LAST:event_btnResetActionPerformed

    private void jTabbedPane4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane4MouseClicked
   
    }//GEN-LAST:event_jTabbedPane4MouseClicked

    private void txtKodeMesinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKodeMesinActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtKodeMesinActionPerformed

    private void txtKodePekerjaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKodePekerjaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtKodePekerjaActionPerformed

    private void btnCariPekerjaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariPekerjaActionPerformed
        caripekerja cari = new caripekerja(this, true);
        cari.setVisible(true);
        caripekerjaclass cr=cari.gettabledata();
        if(cr!=null){
            txtKodePekerja.setText(cr.getid());
            txtNamaPekerja.setText(cr.getnama());
        }   
    }//GEN-LAST:event_btnCariPekerjaActionPerformed

    private void txtJmlMesinKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtJmlMesinKeyTyped
        char c = evt.getKeyChar();
        if (!Character.isDigit(c))
            evt.consume();
    }//GEN-LAST:event_txtJmlMesinKeyTyped

    private void txtJmlPekerjaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtJmlPekerjaKeyTyped
        char c = evt.getKeyChar();
        if (!Character.isDigit(c))
            evt.consume();
    }//GEN-LAST:event_txtJmlPekerjaKeyTyped

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
            java.util.logging.Logger.getLogger(FrmBulk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmBulk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmBulk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmBulk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new FrmBulk().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddItemPM;
    private javax.swing.JButton btnAddItemRM;
    private javax.swing.JButton btnCariMesin;
    private javax.swing.JButton btnCariPM;
    private javax.swing.JButton btnCariPekerja;
    private javax.swing.JButton btnCariRM;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnExit1;
    private javax.swing.JButton btnHapusPM;
    private javax.swing.JButton btnHapusRM;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnNew1;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnReset1;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnSave1;
    private javax.swing.JComboBox cboJenis;
    private javax.swing.JComboBox cboSat;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
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
    private javax.swing.JTextField txtJmlMesin;
    private javax.swing.JTextField txtJmlPM;
    private javax.swing.JTextField txtJmlPekerja;
    private javax.swing.JTextField txtJmlRM;
    private javax.swing.JTextField txtKdProduk;
    private javax.swing.JTextField txtKodeMesin;
    private javax.swing.JTextField txtKodePM;
    private javax.swing.JTextField txtKodePekerja;
    private javax.swing.JTextField txtKodeRM;
    private javax.swing.JTextField txtNamaMesin;
    private javax.swing.JTextField txtNamaPM;
    private javax.swing.JTextField txtNamaPekerja;
    private javax.swing.JTextField txtNamaProd;
    private javax.swing.JTextField txtNamaRM;
    private javax.swing.JTextField txtNoFormula;
    private javax.swing.JTextField txtProses;
    private javax.swing.JTextField txtSatuanPM;
    private javax.swing.JTextField txtSatuanRM;
    private javax.swing.JTextField txtWadah;
    // End of variables declaration//GEN-END:variables
}
