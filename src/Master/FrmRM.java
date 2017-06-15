/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Master;
import Koneksi.Koneksi;
import java.awt.Dimension;
import java.awt.Toolkit;
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
public class FrmRM extends javax.swing.JFrame {
    String header[]={"Kode RM","Nama RM","Satuan","Jenis","Harga RM", "Stock"};
    Connection con;
    Statement st;
    Koneksi kon=new Koneksi();
    private DefaultTableModel tabMode;
    String query;
    private String KD;
    private Dimension dimensi = Toolkit.getDefaultToolkit().getScreenSize();
    /**
     * Creates new form FrmRM
     */
    public FrmRM() {
        initComponents();     
        TidakAktif();
        tampilDataTabel();
        this.setLocationRelativeTo(rootPane);
    }

       //kode untuk properties komponen
void Aktif(){
   txtNamaRM.setEditable(true);
   txtHarga.setEditable(true);
   txtStock.setEditable(true);
   cboJenis.setEnabled(true);
   cboJenis.showPopup(); 
}

//continue
void TidakAktif(){
    txtNamaRM.setEditable(false);
    cboJenis.setEditable(false);
    txtHarga.setEditable(false);
    txtStock.setEditable(false);
    btnSave.setEnabled(false);
    cboJenis.setEnabled(false);
    
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
     kon.QUERY("INSERT INTO tb_master_rm (kd_rm, nama_rm, satuan, jns_rm, harga_rm, stock) VALUES ('"+txtKodeRM.getText()+"','"+txtNamaRM.getText()+"','"+txtSatuan.getText()+"','"+cboJenis.getSelectedItem()+"','"+txtHarga.getText()+"','"+txtStock.getText()+"')"," Disimpan!");        
        tampilDataTabel();
        ClearText();
    }catch(Exception sqle){
    JOptionPane.showMessageDialog(rootPane,"Failed saved data! "+sqle.getMessage());
    }
}


//kode untuk ubah data
void Update(){
   try{        
        kon.QUERY("UPDATE tb_master_rm SET nama_rm='"+txtNamaRM.getText()+"',satuan='"+txtSatuan.getText()+"',jns_rm='"+cboJenis.getSelectedItem()+"',harga_rm='"+txtHarga.getText()+"',stock='"+txtStock.getText()+"' WHERE kd_rm='"+txtKodeRM.getText()+"'"," diperbaharui!");
        System.out.println("UPDATE tb_master_rm SET nama_rm='"+txtNamaRM.getText()+"',satuan='"+txtSatuan.getText()+"',jns_rm='"+cboJenis.getSelectedItem()+"',harga_rm='"+txtHarga.getText()+"',stock='"+txtStock.getText()+"' WHERE kd_rm='"+txtKodeRM.getText()+"'");        
        tampilDataTabel();
        ClearText();
   }catch(Exception sqle){         
       JOptionPane.showMessageDialog(rootPane,"Gagal memperbaharui data! "+sqle.getMessage());
   }
}


//kode untuk hapus data
void Delete(){
   try{
       kon.QUERY ("DELETE FROM tb_master_rm WHERE kd_rm='"+txtKodeRM.getText()+"' "," Dihapus!");
       tampilDataTabel();
       ClearText();
   }catch(Exception sqle){          
       JOptionPane.showMessageDialog(rootPane, "Gagal menghapus data! "+sqle.getMessage());
   }
}

//kode untuk cari data
void Search(){
   removeTable();            
   try{                
       con=kon.open();
       st=con.createStatement();
       query="SELECT * FROM tb_master_rm WHERE "+cboKat.getSelectedItem()+" LIKE '%"+txtCari.getText()+"%' ";
       ResultSet rs=st.executeQuery(query);               
       while(rs.next()){                  
           String skd_rm =rs.getString("kd_rm");
           String snama_rm =rs.getString("nama_rm");
           String ssatuan =rs.getString("satuan");
           String sjns_rm =rs.getString("jns_rm");
           String sharga_rm =rs.getString("harga_rm");
           String sstock =rs.getString("stock");
           txtKodeRM.setText(skd_rm);
           txtNamaRM.setText(snama_rm);
           txtSatuan.setText(ssatuan);
           cboJenis.setSelectedItem(sjns_rm);
           txtHarga.setText(sharga_rm);
           txtStock.setText(sstock);
                  
           String data []={skd_rm,snama_rm,ssatuan,sjns_rm,sharga_rm,sstock};
           tabMode.addRow(data);                   
        }
     }catch(SQLException sqle){
           JOptionPane.showMessageDialog(null, " Gagal Menampilkan Data!"+sqle.getMessage());
     }
}


//kode untuk membersihkan text
void ClearText(){
   txtKodeRM.setText("");
   txtNamaRM.setText("");
   cboJenis.setSelectedItem("");
   txtHarga.setText("");
   txtStock.setText("");
   txtNamaRM.requestFocus();
   btnUpdate.setEnabled(false);
   btnDelete.setEnabled(false);
   btnSave.setEnabled(false);
}


//kode untuk menampilkan data dari tabel frame ke text
void ShowDataTabelKeText(){
   cboJenis.setSelectedItem(String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(),3)));
   txtKodeRM.setText(String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(),0)));
   txtNamaRM.setText(String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(),1)));
   txtSatuan.setText(String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(),2)));
   txtHarga.setText(String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(),4)));
   txtStock.setText(String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(),5)));
     
   txtNamaRM.setEditable(true);
   cboJenis.setEditable(false);
   txtHarga.setEditable(true);
   txtStock.setEditable(true);
   txtNamaRM.requestFocus();
            
   btnUpdate.setEnabled(true);
   btnDelete.setEnabled(true);
}

    
    
private void tampilDataTabel(){
removeTable();
try{
       con=kon.open();
       st=con.createStatement();
       query="SELECT * FROM tb_master_rm";
       ResultSet rs=st.executeQuery(query);
       while(rs.next()){
            String a=rs.getString("kd_rm");
            String b=rs.getString("nama_rm");
            String c=rs.getString("satuan");
            String d=rs.getString("jns_rm");
            String e=rs.getString("harga_rm");
            String f=rs.getString("stock");
            
            String data []={a,b,c,d,e,f};
            tabMode.addRow(data);
       }
            
       }catch(SQLException sqle){
           JOptionPane.showMessageDialog(null," Gagal Menampilkan Data!"+sqle);
            
    }
}
    
//continue
public void autoCode(){
    try {
        con=kon.open();
        st=con.createStatement();
        String query = "SELECT kd_RM FROM tb_master_rm where kd_rm like '%"+ KD +"%' Order By kd_rm Desc";
        ResultSet rs = st.executeQuery(query);
        if(rs.next()) {
            String Kode=rs.getString("kd_rm");
            Kode=Kode.substring(5,7);
            int Nomor=Integer.parseInt(Kode);
            Nomor=Nomor+1;
            if(Nomor < 10){
                Kode=KD+" 000"+Nomor; }
            else if(Nomor < 100){
                Kode=KD+" 00"+Nomor;}
            else if(Nomor < 1000){
                Kode=KD+" 0"+Nomor;}
            else if(Nomor < 10000){
                Kode=KD+" "+Nomor;}
            else{
                Kode="KD"+Nomor;}
            txtKodeRM.setText (Kode);
            }else{
                txtKodeRM.setText (KD+" 0001");
            }
    }
        catch(SQLException e) {JOptionPane.showMessageDialog(null, "Not connected!");
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

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        cboBulan = new javax.swing.JComboBox();
        jLabel11 = new javax.swing.JLabel();
        txtTahun = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtSaldoAwal = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtSaldoAkhir = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        cboJenis = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        txtKodeRM = new javax.swing.JTextField();
        txtNamaRM = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtSatuan = new javax.swing.JTextField();
        txtHarga = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtStock = new javax.swing.JTextField();
        btnNew = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        cboKat = new javax.swing.JComboBox();
        txtCari = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        btnRefresh = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();

        jLabel10.setText("Bulan");

        cboBulan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "JANUARI", "FEBRUARI", "MARET", "APRIL", "MEI", "JUNI", "JULI", "AGUSTUS", "SEPTEMBER", "OKTOBER", "NOVEMBER", "DESEMBER" }));

        jLabel11.setText("Tahun");

        jLabel12.setText("Saldo Awal");

        jLabel13.setText("Saldo Akhir Rekap");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12))
                        .addGap(52, 52, 52)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cboBulan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtTahun, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)
                                    .addComponent(txtSaldoAwal))
                                .addContainerGap(293, Short.MAX_VALUE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(18, 18, 18)
                        .addComponent(txtSaldoAkhir, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(340, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(cboBulan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtTahun, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSaldoAwal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtSaldoAkhir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(128, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel6.setText("Jenis Bahan");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, -1, -1));

        cboJenis.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        cboJenis.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "POWDER", "LIQUID", "WAX", "PEWARNA", "PERFUME", "VITAMIN", "EKSTRAK", "BAHAN NABATI" }));
        cboJenis.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboJenisItemStateChanged(evt);
            }
        });
        cboJenis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboJenisActionPerformed(evt);
            }
        });
        jPanel1.add(cboJenis, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 120, 190, -1));

        jLabel2.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel2.setText("Kode Raw Material");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, -1, 17));

        txtKodeRM.setEditable(false);
        txtKodeRM.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        txtKodeRM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKodeRMActionPerformed(evt);
            }
        });
        jPanel1.add(txtKodeRM, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 150, 338, 30));

        txtNamaRM.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jPanel1.add(txtNamaRM, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 191, 338, 30));

        jLabel3.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel3.setText("Nama Raw Material");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, -1, -1));

        jLabel5.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel5.setText("Satuan");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, -1, -1));

        txtSatuan.setEditable(false);
        txtSatuan.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jPanel1.add(txtSatuan, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 240, 58, -1));

        txtHarga.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jPanel1.add(txtHarga, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 280, 152, -1));

        jLabel9.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel9.setText("Harga Bahan");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 280, -1, -1));

        jLabel7.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel7.setText("Stock Barang");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 320, -1, -1));

        txtStock.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jPanel1.add(txtStock, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 320, 152, -1));

        btnNew.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        btnNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/document-new.png"))); // NOI18N
        btnNew.setText("New");
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });
        jPanel1.add(btnNew, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 370, -1, -1));

        btnSave.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        btnSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/save.png"))); // NOI18N
        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });
        jPanel1.add(btnSave, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 370, -1, 30));

        btnUpdate.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        btnUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/edit2.png"))); // NOI18N
        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });
        jPanel1.add(btnUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 370, -1, 30));

        btnDelete.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/delete.png"))); // NOI18N
        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        jPanel1.add(btnDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 370, -1, -1));

        btnExit.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        btnExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Close.png"))); // NOI18N
        btnExit.setText("Exit");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });
        jPanel1.add(btnExit, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 370, -1, -1));

        btnClear.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        btnClear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/edit-clear.png"))); // NOI18N
        btnClear.setText("Clear");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });
        jPanel1.add(btnClear, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 370, -1, -1));

        jButton1.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Printer.png"))); // NOI18N
        jButton1.setText("Print");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 600, -1, -1));

        jLabel15.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(102, 0, 51));
        jLabel15.setText("Cari Berdasarkan");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 610, -1, -1));

        cboKat.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        cboKat.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "kd_rm", "nama_rm", "satuan", "jns_rm", "harga_rm", "stock" }));
        cboKat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboKatActionPerformed(evt);
            }
        });
        jPanel1.add(cboKat, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 610, -1, -1));
        jPanel1.add(txtCari, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 610, 170, -1));

        btnSearch.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        btnSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/old-zoom-original.png"))); // NOI18N
        btnSearch.setText("Search");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });
        jPanel1.add(btnSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 600, -1, -1));

        btnRefresh.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        btnRefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/gnome_session_reboot.png"))); // NOI18N
        btnRefresh.setText("Refresh");
        btnRefresh.setToolTipText("");
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });
        jPanel1.add(btnRefresh, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 600, -1, -1));

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

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 430, 820, 160));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/BgInputRM.jpg"))); // NOI18N
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtKodeRMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKodeRMActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtKodeRMActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        ShowDataTabelKeText();       
    }//GEN-LAST:event_jTable1MouseClicked

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        New();
    }//GEN-LAST:event_btnNewActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        save();
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        Update();
        tampilDataTabel();
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        Delete();        
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        ClearText();        
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        this.dispose();        
    }//GEN-LAST:event_btnExitActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        Search();        // TODO add your handling code here:
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        ClearText();
        tampilDataTabel();        // TODO add your handling code here:
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void cboJenisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboJenisActionPerformed

    }//GEN-LAST:event_cboJenisActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
    this.dispose();
    }//GEN-LAST:event_formWindowClosing

    private void cboKatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboKatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboKatActionPerformed

    private void cboJenisItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboJenisItemStateChanged
     if(cboJenis.getSelectedItem()=="POWDER"){
            KD = "BA";
            txtSatuan.setText("KG");
        }else if(cboJenis.getSelectedItem()=="LIQUID"){
            KD = "LQ";
            txtSatuan.setText("L");
        }else if(cboJenis.getSelectedItem()=="WAX"){
            KD = "BT";
            txtSatuan.setText("KG");
        }else if(cboJenis.getSelectedItem()=="PEWARNA"){
            KD = "PE";
            txtSatuan.setText("L");
        }else if(cboJenis.getSelectedItem()=="PERFUME"){
            KD = "BP";
            txtSatuan.setText("KG");
        }else if(cboJenis.getSelectedItem()=="VITAMIN"){
            KD = "BZ";
            txtSatuan.setText("KG");
        }else if(cboJenis.getSelectedItem()=="EKSTRAK"){
            KD = "BM";
            txtSatuan.setText("KG");
        }else if(cboJenis.getSelectedItem()=="BAHAN NABATI"){
            KD = "BN";
            txtSatuan.setText("KG");
        }
        autoCode();
    }//GEN-LAST:event_cboJenisItemStateChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    try{
        String namaFile = "./report/rpt_rm.jasper";
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

    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(FrmRM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmRM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmRM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmRM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new FrmRM().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox cboBulan;
    private javax.swing.JComboBox cboJenis;
    private javax.swing.JComboBox cboKat;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtCari;
    private javax.swing.JTextField txtHarga;
    private javax.swing.JTextField txtKodeRM;
    private javax.swing.JTextField txtNamaRM;
    private javax.swing.JTextField txtSaldoAkhir;
    private javax.swing.JTextField txtSaldoAwal;
    private javax.swing.JTextField txtSatuan;
    private javax.swing.JTextField txtStock;
    private javax.swing.JTextField txtTahun;
    // End of variables declaration//GEN-END:variables
}
