/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Transaksi;
import Koneksi.Koneksi;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
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
/**
 *
 * @author Hilman
 */
public class FrmTransMCRM extends javax.swing.JFrame {

    String header[]={"Kode RM","Bahan Baku","Satuan","Jml","Harga","Value In Batch"};
    Connection con;
    Statement st;
    Koneksi kon=new Koneksi();
    private DefaultTableModel tabMode;
    private int totalrm, baris,baris2;
    String query;
    private String a,b,c,d,e,s,qty;
    private Dimension dimensi = Toolkit.getDefaultToolkit().getScreenSize();
    
    int Nomor;
    String Kode;

    String sHasil,sHasilKembali;
    int iharga,ijumlah,isubtotal;
    int iGrantTotal,iBayar,itotal;
    /**
     * Creates new form FrmRenprod
     */
    public FrmTransMCRM() {
        initComponents();
        TidakAktif();
    }
    
        public String tgl(){
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    return sdf.format(new Date());
}
    
void Aktif(){
    txtJmlProduk.setEditable(true);
    txtJmlProduk.requestFocus();
    
    btnNew.setEnabled(false);
    btnRefresh.setEnabled(true);
}

void Clear(){
   txtJmlProduk.setText("");
   txtJmlMesin.setText("");
   txtJmlDL.setText("");
   txtNamaProduk.setText("");
   txtHargaDL.setText("");
   txtHargaMesin.setText("");
   txtSubtotalDL.setText("");
   txtSubtotalMesin.setText("");
   txtTotal.setText("");
   txtTotal1.setText("");
   cboKodeProduk.setSelectedItem("");
   cboKodeMesin.setSelectedItem("");
   cboKodeDL.setSelectedItem("");
   
   cboKodeProduk.setEnabled(true);
   btnSave.setEnabled(false);
}

void TidakAktif(){
    txtJmlProduk.setEditable(false);
    txtJmlMesin.setEditable(false);
    txtJmlDL.setEditable(false);
    btnSave.setEnabled(false);
    btnPrint.setEnabled(false);
    btnRefresh.setEnabled(false);
}

void TampilDataComboProduk(){
    try{
       con=kon.open();
       st=con.createStatement();
       query="SELECT * FROM tb_bom where proses='BULK'";
       ResultSet rs=st.executeQuery(query);
       while(rs.next()){
            cboKodeProduk.addItem(""+rs.getString("kd_produk"));
       }
            
       }catch(SQLException sqle){
           JOptionPane.showMessageDialog(null,"Failed show data! "+sqle);
            
    }
}

    public void removeTable() {
            try{
                for(int t=tabMode.getRowCount();t>0;t--){tabMode.removeRow(0);}
                
            }catch(Exception ex){System.out.println(ex);
                
            }
   
    }
    

void TampilDataComboMesin(){
    try{
       con=kon.open();
       st=con.createStatement();
       query="SELECT * FROM tb_mesin";
       ResultSet rs=st.executeQuery(query);
       while(rs.next()){
            cboKodeMesin.addItem(""+rs.getString("kd_mesin"));
       }
            
       }catch(SQLException sqle){
           JOptionPane.showMessageDialog(null,"Failed show data! "+sqle);
            
    }
}


void TampilDataComboPekerjaDL(){
    try{
       con=kon.open();
       st=con.createStatement();
       query="SELECT * FROM tb_pekerja where jenis_pekerja='DL PROSES'";
       ResultSet rs=st.executeQuery(query);
       while(rs.next()){
            cboKodeDL.addItem(""+rs.getString("kd_pekerja"));
       }
            
       }catch(SQLException sqle){
           JOptionPane.showMessageDialog(null,"Failed show data! "+sqle);
    }
}


//Simpan data
void save(){
     try{
     kon.QUERY("INSERT INTO tb_mc_rm (kd_mc_rm,tgl_cetak,kd_produk,jml_produk,kd_mesin,jml_mesin,kd_pekerja1,jml_pekerja1) VALUES ('"+txtMCRM.getText()+"','"+txtTanggal.getText()+"','"+cboKodeProduk.getSelectedItem()+"','"+txtJmlProduk.getText()+"','"+cboKodeMesin.getSelectedItem()+"','"+txtJmlMesin.getText()+"','"+cboKodeDL.getSelectedItem()+"','"+txtJmlDL.getText()+"')"," Disimpan!!");        
     //JOptionPane.showMessageDialog(rootPane,"INSERT INTO tb_mc_rm (kd_mc_rm,tgl_cetak,kd_produk,jml_produk,kd_mesin,jml_mesin,kd_pekerja1,jml_pekerja1) VALUES ('"+txtMCRM.getText()+"','"+txtTanggal.getText()+"','"+cboKodeProduk.getSelectedItem()+"','"+txtJmlProduk.getText()+"','"+cboKodeDL.getSelectedItem()+"','"+txtJmlDL.getText()+"')");  
    btnSave.setEnabled(false);
    btnPrint.setEnabled(true);
     }catch(Exception sqle){
    JOptionPane.showMessageDialog(rootPane,"Failed saved data! "+sqle.getMessage());
    }
}


private void tampilDataTabeldetail(){
        removeTable();
        try{
            con=kon.open();
            st=con.createStatement();
            query="SELECT * FROM tb_mc_rm_detail WHERE kd_mc_rm='"+ txtMCRM.getText() +"'";
            ResultSet rs=st.executeQuery(query);
            while(rs.next()){
                String a=rs.getString("kd_mc_rm");
                String b=rs.getString("kd_rm");
                String c=rs.getString("jml_rm");
                String d=rs.getString("subtotal");
                
                String data []={a,b,c,d};
                tabMode.addRow(data);
                }
            }catch(SQLException sqle){
                JOptionPane.showMessageDialog(null,"Failed show data!"+sqle);
        }
    }
    
    public void tampilbb(){
        try{
            con=kon.open();
            st=con.createStatement();
            query="SELECT a.kd_rm, a.nama_rm, a.satuan, b.qty, a.harga_rm, c.kd_produk FROM tb_master_rm as a, tb_bom_detail as b, tb_bom as c WHERE a.kd_rm = b.kd_rm and b.id_bom = c.id_bom and c.kd_produk='"+ cboKodeProduk.getSelectedItem()+"'";
            ResultSet rs=st.executeQuery(query);
            baris = 0;
            while(rs.next()){
                a=rs.getString("kd_rm");
                b=rs.getString("nama_rm");
                c=rs.getString("satuan");
                //ngambil data qty dari database
                d=rs.getString("qty");
                //ngambil data banyak produk
                //int asa = Integer.parseInt(txtJmlProduk.getText());
                double asa = Double.parseDouble(txtJmlProduk.getText());
                //ngaliin qty sama banyak
                double jmlh = (double) (Double.parseDouble(d)*asa);
                //convert nilai dari int ke string
                qty = String.valueOf(jmlh);
                e=rs.getString("harga_rm");
                int f= (int) (Double.parseDouble(qty)*Integer.parseInt(e));
                s = String.valueOf(f);
                totalrm = totalrm + Integer.parseInt(s);
                String data []={a,b,c,qty,e,s};
                String urlValue = "jdbc:mysql://localhost/appinv?user=root&password=";
                Connection conn=DriverManager.getConnection(urlValue);
                PreparedStatement pStatement = null;
                String skript = "INSERT INTO tb_mc_rm_detail (kd_mc_rm,kd_rm,jml_rm,subtotal) VALUES ('"+txtMCRM.getText()+"','"+ a +"','"+ qty +"','"+ s +"')";
                pStatement = conn.prepareStatement(skript);
                int intTambah= pStatement.executeUpdate();
                tabMode.addRow(data);
                baris = baris+1;
            }
            txtTotal.setText(String.valueOf(totalrm));
        }catch(SQLException sqle){
            JOptionPane.showMessageDialog(null,"Failed show data! "+sqle);
        }
    }
    
 
    private void TotalKeseluruhan(){
                        Integer DL = Integer.parseInt(txtSubtotalDL.getText());
                        Integer MES = Integer.parseInt(txtSubtotalMesin.getText());
                        Integer SUB = Integer.parseInt(txtTotal.getText());
                        Integer tot = DL + MES + SUB;
                        txtTotal1.setText(String.valueOf(tot));
    }

    private void SubTotalPekerjaDL(){
        int a,b,c;
        a = Integer.parseInt(txtHargaDL.getText());
        double z = Double.parseDouble(txtJmlDL.getText());
        c = (int) (a * z);
        txtSubtotalDL.setText(String.valueOf(c));
    }
    
    private void SubTotalMesin(){
        int a,b,c;
        a = Integer.parseInt(txtHargaMesin.getText());
        //b = Integer.parseInt(txtJumlah.getText());
        double z = Double.parseDouble(txtJmlMesin.getText());
        c = (int) (a * z);
        //c =a* b;
        txtSubtotalMesin.setText(String.valueOf(c));
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        cboKodeMesin = new javax.swing.JComboBox();
        jLabel16 = new javax.swing.JLabel();
        txtHargaMesin = new javax.swing.JTextField();
        txtJmlMesin = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        txtSubtotalMesin = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        cboKodeDL = new javax.swing.JComboBox();
        txtHargaDL = new javax.swing.JTextField();
        txtJmlDL = new javax.swing.JTextField();
        txtSubtotalDL = new javax.swing.JTextField();
        txtNamaMesin = new javax.swing.JTextField();
        txtNamaDL = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtNamaProduk = new javax.swing.JTextField();
        cboKodeProduk = new javax.swing.JComboBox();
        txtJmlProduk = new javax.swing.JTextField();
        txtTanggal = new javax.swing.JTextField();
        txtMCRM = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        txtTotal1 = new javax.swing.JTextField();
        txtKeseluruhanTotal = new javax.swing.JButton();
        btnRefresh = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnPrint = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        btnNew = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();

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
        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(204, 204, 204));

        jPanel1.setBackground(new java.awt.Color(0, 153, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable2.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
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
        jScrollPane2.setViewportView(jTable2);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 280, 1310, 260));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cboKodeMesin.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        cboKodeMesin.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "- Select -" }));
        cboKodeMesin.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboKodeMesinItemStateChanged(evt);
            }
        });
        jPanel4.add(cboKodeMesin, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 80, 158, -1));

        jLabel16.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel16.setText("Harga");
        jPanel4.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 60, -1, -1));

        txtHargaMesin.setEditable(false);
        txtHargaMesin.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        txtHargaMesin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtHargaMesinActionPerformed(evt);
            }
        });
        jPanel4.add(txtHargaMesin, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 80, 158, -1));

        txtJmlMesin.setEditable(false);
        txtJmlMesin.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        txtJmlMesin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtJmlMesinKeyReleased(evt);
            }
        });
        jPanel4.add(txtJmlMesin, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 80, 68, -1));

        jLabel15.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel15.setText("Jml");
        jPanel4.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 60, -1, -1));

        jLabel25.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel25.setText("Subtotal");
        jPanel4.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 60, -1, -1));

        txtSubtotalMesin.setEditable(false);
        txtSubtotalMesin.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jPanel4.add(txtSubtotalMesin, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 80, 140, -1));

        jLabel10.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        jLabel10.setText("Biaya Produksi");
        jPanel4.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 20, -1, -1));

        jLabel11.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel11.setText("Nama");
        jPanel4.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 60, -1, -1));

        jLabel28.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel28.setText("Kode");
        jPanel4.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 60, -1, -1));

        jLabel17.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel17.setText("DL");
        jPanel4.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, -1, -1));

        cboKodeDL.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        cboKodeDL.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "- Select -" }));
        cboKodeDL.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboKodeDLItemStateChanged(evt);
            }
        });
        jPanel4.add(cboKodeDL, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 110, 158, -1));

        txtHargaDL.setEditable(false);
        txtHargaDL.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jPanel4.add(txtHargaDL, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 110, 158, -1));

        txtJmlDL.setEditable(false);
        txtJmlDL.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        txtJmlDL.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtJmlDLKeyReleased(evt);
            }
        });
        jPanel4.add(txtJmlDL, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 110, 68, -1));

        txtSubtotalDL.setEditable(false);
        txtSubtotalDL.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jPanel4.add(txtSubtotalDL, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 110, 140, -1));

        txtNamaMesin.setEditable(false);
        txtNamaMesin.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jPanel4.add(txtNamaMesin, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 80, 178, -1));

        txtNamaDL.setEditable(false);
        txtNamaDL.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jPanel4.add(txtNamaDL, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 110, 178, -1));

        jLabel18.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel18.setText("Mesin");
        jPanel4.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, -1, -1));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 70, 880, 200));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel1.setText("Kode MC");
        jPanel5.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 23, -1, -1));

        jLabel2.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel2.setText("Tgl Cetak");
        jPanel5.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 54, -1, -1));

        jLabel8.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel8.setText("Jml Produk");
        jPanel5.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, -1, -1));

        jLabel3.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel3.setText("Kode Produk");
        jPanel5.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, -1, -1));

        txtNamaProduk.setEditable(false);
        txtNamaProduk.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jPanel5.add(txtNamaProduk, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 150, 300, -1));

        cboKodeProduk.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        cboKodeProduk.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "- Select -" }));
        cboKodeProduk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cboKodeProdukMouseClicked(evt);
            }
        });
        cboKodeProduk.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboKodeProdukItemStateChanged(evt);
            }
        });
        cboKodeProduk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboKodeProdukActionPerformed(evt);
            }
        });
        jPanel5.add(cboKodeProduk, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 120, 300, -1));

        txtJmlProduk.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        txtJmlProduk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtJmlProdukActionPerformed(evt);
            }
        });
        txtJmlProduk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtJmlProdukKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtJmlProdukKeyReleased(evt);
            }
        });
        jPanel5.add(txtJmlProduk, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 80, 80, -1));

        txtTanggal.setEditable(false);
        txtTanggal.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jPanel5.add(txtTanggal, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 50, 120, -1));

        txtMCRM.setEditable(false);
        txtMCRM.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jPanel5.add(txtMCRM, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 20, 120, -1));

        jLabel13.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jPanel5.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 80, 40, 20));

        jLabel27.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel27.setText("Nama Produk");
        jPanel5.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, -1, -1));

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 430, 200));

        jLabel7.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Total Biaya RM");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 560, -1, -1));

        txtTotal.setEditable(false);
        txtTotal.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jPanel1.add(txtTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 560, 190, -1));

        jLabel26.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("Total Keseluruhan");
        jPanel1.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 590, -1, -1));

        txtTotal1.setEditable(false);
        txtTotal1.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jPanel1.add(txtTotal1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 590, 190, -1));

        txtKeseluruhanTotal.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        txtKeseluruhanTotal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/finish.png"))); // NOI18N
        txtKeseluruhanTotal.setText("Hitung Total");
        txtKeseluruhanTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKeseluruhanTotalActionPerformed(evt);
            }
        });
        jPanel1.add(txtKeseluruhanTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 620, -1, -1));

        btnRefresh.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        btnRefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/reset.gif"))); // NOI18N
        btnRefresh.setText("Refresh");
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });
        jPanel1.add(btnRefresh, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 640, -1, -1));

        btnSave.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        btnSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/save.png"))); // NOI18N
        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });
        jPanel1.add(btnSave, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 640, -1, 30));

        btnPrint.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        btnPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/PRINT.PNG"))); // NOI18N
        btnPrint.setText("Print");
        btnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintActionPerformed(evt);
            }
        });
        jPanel1.add(btnPrint, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 640, -1, 30));

        btnExit.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        btnExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Close.png"))); // NOI18N
        btnExit.setText("Exit");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });
        jPanel1.add(btnExit, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 640, -1, -1));

        btnNew.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        btnNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/manilla-folder-new.png"))); // NOI18N
        btnNew.setText("New");
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });
        jPanel1.add(btnNew, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 640, -1, -1));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/BgInputMCRM.jpg"))); // NOI18N
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1360, 680));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cboKodeProdukItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboKodeProdukItemStateChanged
        try{
            con=kon.open();
            st=con.createStatement();
            query="SELECT nama_produk, satuan, kd_mesin, kd_pekerja1, jml_mesin, jml_pekerja1 FROM tb_bom WHERE kd_produk='"+ cboKodeProduk.getSelectedItem() +"'";
            ResultSet rs=st.executeQuery(query);  
            while(rs.next()){
                txtNamaProduk.setText(""+rs.getString("nama_produk"));
                jLabel13.setText(""+rs.getString("satuan"));
                cboKodeMesin.setSelectedItem(""+rs.getString("kd_mesin"));
                cboKodeDL.setSelectedItem(""+rs.getString("kd_pekerja1"));
                txtJmlMesin.setText(""+rs.getString("jml_mesin"));
                txtJmlDL.setText(""+rs.getString("jml_pekerja1"));
            }
        }catch(SQLException sqle){
            JOptionPane.showMessageDialog(null,"Failed show data! "+sqle);
        }
        SubTotalPekerjaDL();
        SubTotalMesin();
        cboKodeDL.setEnabled(false);
        cboKodeMesin.setEnabled(false);
    }//GEN-LAST:event_cboKodeProdukItemStateChanged

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        save();
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        Aktif();
        autoCode();
        txtTanggal.setText(tgl());
        TampilDataComboProduk();
        TampilDataComboMesin();
        TampilDataComboPekerjaDL();
    }//GEN-LAST:event_btnNewActionPerformed

    private void txtHargaMesinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHargaMesinActionPerformed

    }//GEN-LAST:event_txtHargaMesinActionPerformed

    private void cboKodeMesinItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboKodeMesinItemStateChanged
        try{
            con=kon.open();
            st=con.createStatement();
            query="SELECT * FROM tb_mesin WHERE kd_mesin='"+ cboKodeMesin.getSelectedItem() +"'";
            ResultSet rs=st.executeQuery(query);
            while(rs.next()){
                txtNamaMesin.setText(""+rs.getString("nama_mesin"));
                txtHargaMesin.setText(""+rs.getString("harga_satuan"));
            }
        }catch(SQLException sqle){
            JOptionPane.showMessageDialog(null,"Failed show data! "+sqle);
        }
    }//GEN-LAST:event_cboKodeMesinItemStateChanged

    private void cboKodeDLItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboKodeDLItemStateChanged
        try{
            con=kon.open();
            st=con.createStatement();
            query="SELECT * FROM tb_pekerja WHERE kd_pekerja='"+ cboKodeDL.getSelectedItem() +"'";
            ResultSet rs=st.executeQuery(query);
            while(rs.next()){
                txtNamaDL.setText(""+rs.getString("nama_pekerja"));
                txtHargaDL.setText(""+rs.getString("harga_satuan"));
            }
        }catch(SQLException sqle){
            JOptionPane.showMessageDialog(null,"Failed show data! "+sqle);
        }
    }//GEN-LAST:event_cboKodeDLItemStateChanged

    private void txtJmlDLKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtJmlDLKeyReleased
/*        int a,b,c;
        a = Integer.parseInt(txtHargaDL.getText());
        //b = Integer.parseInt(txtJumlah.getText());
        double z = Double.parseDouble(txtJmlDL.getText());
        c = (int) (a * z);
        //c =a* b;
        txtSubtotalDL.setText(String.valueOf(c));
        */
    }//GEN-LAST:event_txtJmlDLKeyReleased

    private void txtJmlMesinKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtJmlMesinKeyReleased
/*        int a,b,c;
        a = Integer.parseInt(txtHargaMesin.getText());
        double z = Double.parseDouble(txtJmlMesin.getText());
        c = (int) (a * z);
        txtSubtotalMesin.setText(String.valueOf(c));  
*/        
    }//GEN-LAST:event_txtJmlMesinKeyReleased

    private void txtJmlProdukKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtJmlProdukKeyReleased

    }//GEN-LAST:event_txtJmlProdukKeyReleased

    private void txtJmlProdukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtJmlProdukActionPerformed

    }//GEN-LAST:event_txtJmlProdukActionPerformed

    private void txtJmlProdukKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtJmlProdukKeyPressed

    }//GEN-LAST:event_txtJmlProdukKeyPressed

    private void txtKeseluruhanTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKeseluruhanTotalActionPerformed
        TotalKeseluruhan();        
        btnSave.setEnabled(true);
        cboKodeProduk.setEnabled(false);
        txtJmlProduk.setEnabled(false);
    }//GEN-LAST:event_txtKeseluruhanTotalActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        dispose();
        FrmTransMCRM rm = new FrmTransMCRM();
        rm.setVisible(true);
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintActionPerformed
        String kdpsn = txtMCRM.getText().toString();
        tampilMCRM("./report/rpt_mc_rm.jasper", kdpsn);
    }//GEN-LAST:event_btnPrintActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_btnExitActionPerformed

    private void cboKodeProdukMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboKodeProdukMouseClicked

    }//GEN-LAST:event_cboKodeProdukMouseClicked

    private void cboKodeProdukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboKodeProdukActionPerformed
        if(!"".equals(txtJmlProduk.getText())){
        totalrm = 0;
        removeTable();
        tampilbb();
        }else{
            JOptionPane.showMessageDialog(null,"Jumlah Produk Tidak Boleh Kosong! ");
            cboKodeProduk.setSelectedIndex(0);
            txtNamaProduk.setText("");
            jLabel13.setText("");
            txtJmlProduk.requestFocus();
        }  
    }//GEN-LAST:event_cboKodeProdukActionPerformed

public static void tampilMCRM(String fileReport, String ket) {
		HashMap param = new HashMap();
		param.put("mc_rm", ket);
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
        String query = "SELECT kd_mc_rm FROM tb_mc_rm Order By kd_mc_rm Desc";
        ResultSet rs = st.executeQuery(query);
        if(rs.next()) {
            Kode=rs.getString("kd_mc_rm");
            Kode=Kode.substring(5,8);
            Nomor=Integer.parseInt(Kode);
            Nomor=Nomor+1;
            if(Nomor < 10){
                Kode="MCRM-00"+Nomor; }
            else if(Nomor < 100){
                Kode="MCRM-0"+Nomor;}
            else if(Nomor < 1000){
                Kode="MCRM-"+Nomor;}
            else if(Nomor < 10000){
                Kode="MCRM"+Nomor;}
            else if(Nomor < 100000){
                Kode="MCR"+Nomor;}
            else if(Nomor < 1000000){
                Kode="MC"+Nomor;}
            else if(Nomor < 10000000){
                Kode="M"+Nomor;}
            else {
                Kode="MCRM"+Nomor;
            }
            txtMCRM.setText (Kode);
        }else {
            txtMCRM.setText ("MCRM-001");
        }
    }
    catch(SQLException e) {JOptionPane.showMessageDialog(null, "Not connected!");}
}
    
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
            java.util.logging.Logger.getLogger(FrmTransMCRM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmTransMCRM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmTransMCRM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmTransMCRM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new FrmTransMCRM().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnPrint;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnSave;
    private javax.swing.JComboBox cboKodeDL;
    private javax.swing.JComboBox cboKodeMesin;
    private javax.swing.JComboBox cboKodeProduk;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField txtHargaDL;
    private javax.swing.JTextField txtHargaMesin;
    private javax.swing.JTextField txtJmlDL;
    private javax.swing.JTextField txtJmlMesin;
    private javax.swing.JTextField txtJmlProduk;
    private javax.swing.JButton txtKeseluruhanTotal;
    private javax.swing.JTextField txtMCRM;
    private javax.swing.JTextField txtNamaDL;
    private javax.swing.JTextField txtNamaMesin;
    private javax.swing.JTextField txtNamaProduk;
    private javax.swing.JTextField txtSubtotalDL;
    private javax.swing.JTextField txtSubtotalMesin;
    private javax.swing.JTextField txtTanggal;
    private javax.swing.JTextField txtTotal;
    private javax.swing.JTextField txtTotal1;
    // End of variables declaration//GEN-END:variables
}
