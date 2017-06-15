/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Report;

import Koneksi.Koneksi;
import com.mysql.jdbc.Statement;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;


/**
 *
 * @author Hilman
 */
public class lap_bppb extends javax.swing.JFrame {
        private String urlValue ="";
        private Connection Lconnection=null;
        private Connection connection=null;
        private JTable table = null;
        private JTable table2 = null;
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement pst = null;
        JasperReport JasRep;
        JasperPrint JasPri;
        Map<String, Object> param = new HashMap<String, Object>();
        JasperDesign JasDes;
        private Dimension dimensi = Toolkit.getDefaultToolkit().getScreenSize();

  
    public lap_bppb() {
        initComponents();
        tampilTabel();
        tampilTabel2();
        this.setLocationRelativeTo(rootPane);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane3 = new javax.swing.JTabbedPane();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        cmbCari = new javax.swing.JComboBox();
        txtCari = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnCetak = new javax.swing.JButton();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jDateChooser3 = new com.toedter.calendar.JDateChooser();
        jDateChooser4 = new com.toedter.calendar.JDateChooser();
        btnRM1 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        cboCariRM = new javax.swing.JComboBox();
        txtCariRM = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        btnRM2 = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel12 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTabbedPane1.setToolTipText("");
        jTabbedPane1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTabbedPane1.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("s/d");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 70, -1, -1));

        cmbCari.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        cmbCari.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "no_dok_bppb", "tgl_dok", "kd_supplier", "no_sj", "tgl_sj" }));
        cmbCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbCariActionPerformed(evt);
            }
        });
        jPanel1.add(cmbCari, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 240, 126, -1));

        txtCari.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        txtCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCariKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCariKeyTyped(evt);
            }
        });
        jPanel1.add(txtCari, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 240, 150, -1));

        jScrollPane1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jTable1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
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
        jScrollPane1.setViewportView(jTable1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, 680, 229));

        btnCetak.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        btnCetak.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Printer2.png"))); // NOI18N
        btnCetak.setText("Cetak");
        btnCetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCetakActionPerformed(evt);
            }
        });
        jPanel1.add(btnCetak, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 510, -1, 30));
        jPanel1.add(jDateChooser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 70, 150, -1));
        jPanel1.add(jDateChooser2, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 70, 150, -1));

        jLabel4.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Cari");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, -1, -1));

        jButton1.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Printer2.png"))); // NOI18N
        jButton1.setText("Cetak");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 120, -1, -1));

        jLabel2.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Cetak Laporan per periode");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 30, -1, -1));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 160, 730, 20));

        jLabel6.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Cetak Dokumen Harian");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 180, -1, -1));

        jLabel11.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Pilih Tanggal");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 70, -1, -1));

        jButton2.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Close.png"))); // NOI18N
        jButton2.setText("Close");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 510, -1, -1));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/BgRptBPPB1.jpg"))); // NOI18N
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 730, -1));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Packaging Material", jPanel2);

        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Cetak Laporan per periode");
        jPanel5.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 30, -1, -1));

        jLabel8.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("s/d");
        jPanel5.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 70, -1, -1));

        jDateChooser3.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jPanel5.add(jDateChooser3, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 70, 150, -1));

        jDateChooser4.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jPanel5.add(jDateChooser4, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 70, 150, -1));

        btnRM1.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        btnRM1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Printer2.png"))); // NOI18N
        btnRM1.setText("Cetak");
        btnRM1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRM1ActionPerformed(evt);
            }
        });
        jPanel5.add(btnRM1, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 120, -1, -1));

        jLabel9.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Cetak Dokumen Harian");
        jPanel5.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 180, -1, -1));

        jLabel10.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Cari");
        jPanel5.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, -1, -1));

        cboCariRM.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        cboCariRM.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "no_dok_bppb", "tgl_dok", "kd_supplier", "no_sj", "tgl_sj" }));
        jPanel5.add(cboCariRM, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 240, 120, -1));

        txtCariRM.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        txtCariRM.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCariRMKeyTyped(evt);
            }
        });
        jPanel5.add(txtCariRM, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 240, 150, -1));

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
        jScrollPane2.setViewportView(jTable2);

        jPanel5.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, 680, 230));

        btnRM2.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        btnRM2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Printer2.png"))); // NOI18N
        btnRM2.setText("Cetak");
        btnRM2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRM2ActionPerformed(evt);
            }
        });
        jPanel5.add(btnRM2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 510, -1, -1));
        jPanel5.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 160, 720, 20));

        jLabel12.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Pilih Tanggal");
        jPanel5.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 70, -1, -1));

        jButton3.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Close.png"))); // NOI18N
        jButton3.setText("Close");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 510, -1, -1));

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/BgRptBPPB1.jpg"))); // NOI18N
        jPanel5.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 730, -1));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Raw Material", jPanel3);

        jPanel4.add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 730, 610));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/BgRptBPPB.jpg"))); // NOI18N
        jPanel4.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 680));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCetakActionPerformed
        String usrId = table.getValueAt(table.getSelectedRow(), 0).toString();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            urlValue="jdbc:mysql://localhost/appinv?user=root&password=";
            Connection conn=
                    DriverManager.getConnection(urlValue);          
            File reprt = new File("report/rpt_bppb_pm.jrxml");
            JasDes = JRXmlLoader.load(reprt);
            Map<String, Object> params = new HashMap<String, Object>();
            param.put("bppb_pm",usrId);
            JasRep = JasperCompileManager.compileReport(JasDes);
            JasPri = JasperFillManager.fillReport(JasRep, param, conn);
            JasperViewer.viewReport(JasPri,false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_btnCetakActionPerformed

    private void cmbCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbCariActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbCariActionPerformed

    private void txtCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCariKeyPressed

    }//GEN-LAST:event_txtCariKeyPressed

    private void txtCariKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCariKeyTyped
        tampilTabel();     
    }//GEN-LAST:event_txtCariKeyTyped

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        java.text.SimpleDateFormat fmt = new java.text.SimpleDateFormat("yyyy-MM-dd");
        String formattedDate1 = fmt.format(jDateChooser1.getDate());
        String formattedDate2 = fmt.format(jDateChooser2.getDate());
 
        try {
            Class.forName("com.mysql.jdbc.Driver");
            urlValue="jdbc:mysql://localhost/appinv?user=root&password=";
            Connection conn=
                    DriverManager.getConnection(urlValue);          
            File reprt = new File("report/rpt_bppb_pm_universal.jrxml");
            JasDes = JRXmlLoader.load(reprt);
            Map<String, Object> params = new HashMap<String, Object>();
            param.put("tgl1",formattedDate1);
            param.put("tgl2",formattedDate2);
            JasRep = JasperCompileManager.compileReport(JasDes);
            JasPri = JasperFillManager.fillReport(JasRep, param, conn);
            JasperViewer.viewReport(JasPri,false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnRM1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRM1ActionPerformed
        java.text.SimpleDateFormat fmt = new java.text.SimpleDateFormat("yyyy-MM-dd");
        String formattedDate3 = fmt.format(jDateChooser3.getDate());
        String formattedDate4 = fmt.format(jDateChooser4.getDate());
     
        try {
            Class.forName("com.mysql.jdbc.Driver");
            urlValue="jdbc:mysql://localhost/appinv?user=root&password=";
            Connection conn=
                    DriverManager.getConnection(urlValue);          
            File reprt = new File("report/rpt_bppb_rm_universal.jrxml");
            JasDes = JRXmlLoader.load(reprt);
            Map<String, Object> params = new HashMap<String, Object>();
            param.put("tgl3",formattedDate3);
            param.put("tgl4",formattedDate4);
            JasRep = JasperCompileManager.compileReport(JasDes);
            JasPri = JasperFillManager.fillReport(JasRep, param, conn);
            JasperViewer.viewReport(JasPri,false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_btnRM1ActionPerformed

    private void txtCariRMKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCariRMKeyTyped
        tampilTabel2();
    }//GEN-LAST:event_txtCariRMKeyTyped

    private void btnRM2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRM2ActionPerformed
        String usrId1 = table2.getValueAt(table2.getSelectedRow(), 0).toString();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            urlValue="jdbc:mysql://localhost/appinv?user=root&password=";
            Connection conn=
                    DriverManager.getConnection(urlValue);          
            File reprt = new File("report/rpt_bppb_rm.jrxml");
            JasDes = JRXmlLoader.load(reprt);
            Map<String, Object> params = new HashMap<String, Object>();
            param.put("bppb_rm",usrId1);
            JasRep = JasperCompileManager.compileReport(JasDes);
            JasPri = JasperFillManager.fillReport(JasRep, param, conn);
            JasperViewer.viewReport(JasPri,false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }        
    }//GEN-LAST:event_btnRM2ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();    
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        this.dispose();        
    }//GEN-LAST:event_jButton3ActionPerformed
    //query menampilkan bppb semua
    //"select a.*,b.kd_pm,c.nama_pm,b.jml_pm_ter,b.no_po,b.no_ca from tb_bppb2 a inner join tb_bppb_pm b inner join tb_master_pm c on a.no_dok_bppb=b.no_dok_bppb and b.kd_pm=c.kd_pm where a.tgl_dok between '"+formattedDate1+"' and '"+formattedDate2+"'";
    
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
            java.util.logging.Logger.getLogger(lap_bppb.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(lap_bppb.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(lap_bppb.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(lap_bppb.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new lap_bppb().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCetak;
    private javax.swing.JButton btnRM1;
    private javax.swing.JButton btnRM2;
    private javax.swing.JComboBox cboCariRM;
    private javax.swing.JComboBox cmbCari;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private com.toedter.calendar.JDateChooser jDateChooser3;
    private com.toedter.calendar.JDateChooser jDateChooser4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
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
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField txtCari;
    private javax.swing.JTextField txtCariRM;
    // End of variables declaration//GEN-END:variables
private Object[][] getData(){
    Object[][] data1 = null;
    try {
        urlValue="jdbc:mysql://localhost/appinv?user=root&password=";
            connection=DriverManager.getConnection(urlValue);
        Statement st = (Statement) connection.createStatement();
        ResultSet as = st.executeQuery("Select * from tb_bppb2 where " + cmbCari.getSelectedItem() + " like '%"+ txtCari.getText() +"%'");
        as.last();
        int rowCount= as.getRow();
        as.beforeFirst();
        data1 = new Object[rowCount][5];
        int no=-1;
        while (as.next()){
                   no = no +1;
                   data1[no][0]=as.getString("no_dok_bppb");
                   data1[no][1]=as.getString("tgl_dok");
                   data1[no][2]=as.getString("kd_supplier");
                   data1[no][3]=as.getString("no_sj");
                   data1[no][4]=as.getString("tgl_sj");
            }
            st.close();
    }
    catch (SQLException e){
            System.out.println("koneksi gagal "+ e.toString());
    }
    return data1;
}


private void tampilTabel(){
    String[] columnNames={"No. Dok BPPB", "Tanggal Dokumen", "Kode Supplier", "No SJ", "Tgl SJ"};
    table = new JTable(getData(), columnNames);
    jScrollPane1.setViewportView(table);
}

private Object[][] getData2(){
    Object[][] data1 = null;
    try {
        urlValue="jdbc:mysql://localhost/appinv?user=root&password=";
            connection=DriverManager.getConnection(urlValue);
        Statement st = (Statement) connection.createStatement();
        ResultSet as = st.executeQuery("Select * from tb_bppb where " + cboCariRM.getSelectedItem() + " like '%"+ txtCariRM.getText() +"%'");
        as.last();
        int rowCount= as.getRow();
        as.beforeFirst();
        data1 = new Object[rowCount][5];
        int no=-1;
        while (as.next()){
                   no = no +1;
                   data1[no][0]=as.getString("no_dok_bppb");
                   data1[no][1]=as.getString("tgl_dok");
                   data1[no][2]=as.getString("kd_supplier");
                   data1[no][3]=as.getString("no_sj");
                   data1[no][4]=as.getString("tgl_sj");
            }
            st.close();
    }
    catch (SQLException e){
            System.out.println("koneksi gagal "+ e.toString());
    }
    return data1;
}

private void tampilTabel2(){
    String[] columnNames={"No. Dok BPPB", "Tanggal Dokumen", "Kode Supplier", "No SJ", "Tgl SJ"};
    table2 = new JTable(getData2(), columnNames);
    jScrollPane2.setViewportView(table2);
}

}
