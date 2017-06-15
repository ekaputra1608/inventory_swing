/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Menu;

import Koneksi.Koneksi;
import Master.*;
import Report.lap_bppb;
import Report.lap_pprm;
import Report.lap_mcb;
import Transaksi.*;
import Utility.FrmLogin;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
/**
 *
 * @author Hilman
 */
public class FrmMenuUtama extends javax.swing.JFrame {
String kd_user,nama,hak_akses;
    Connection con;
    Statement st;
    Koneksi kon=new Koneksi();
    private DefaultTableModel tabMode;
    String query;
    private String urlValue ="";

    JasperReport JasRep;
    JasperPrint JasPri;
    Map<String, Object> param = new HashMap<String, Object>();
    JasperDesign JasDes;

    private Dimension dimensi = Toolkit.getDefaultToolkit().getScreenSize();
    
    
    public FrmMenuUtama(String kd_user,String nama,String hak_akses) {
        initComponents();   
        this.kd_user=kd_user;
        this.nama=nama;
        this.hak_akses=hak_akses;
        
        if(hak_akses.equalsIgnoreCase("Warehouse")){
            jMenu5.setVisible(false);
            jMenu2.setVisible(false);
            }else if(hak_akses.equalsIgnoreCase("PPIC")){
                jMenu2.setVisible(false);
                jMenu3.setVisible(false);
            }else if(hak_akses.equalsIgnoreCase("Admin")){
                jMenu5.setVisible(true);
                jMenu2.setVisible(true);
                jMenu3.setVisible(true);
            }
            Date tgl = new Date();
            SimpleDateFormat tanggal = new SimpleDateFormat("dd-MM-yyyy");
            String dateString = tanggal.format(tgl);
            LabelTanggal.setText("Tanggal : "+ dateString);     
            Date waktu = new Date();
            SimpleDateFormat pukul = new SimpleDateFormat("HH:mm");
            String waktu2 = pukul.format(waktu);
            LabelWaktu.setText(" / "+ waktu2);
            LabelBawah.setText("Menu Utama");
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

        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        LabelTanggal = new javax.swing.JLabel();
        LabelWaktu = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jToolBar1 = new javax.swing.JToolBar();
        LabelBawah = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem15 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem13 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        jReportBPPB = new javax.swing.JMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();
        jReportRM = new javax.swing.JMenuItem();

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jLabel2.setText("jLabel2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(34, 108, 182));
        jPanel1.setToolTipText("");
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        LabelTanggal.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        LabelTanggal.setText("Date");
        jPanel1.add(LabelTanggal, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 30, -1, 20));

        LabelWaktu.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        LabelWaktu.setText("Time");
        jPanel1.add(LabelWaktu, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 30, -1, 20));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Splash.jpg"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 940, 630));

        jToolBar1.setRollover(true);

        LabelBawah.setText("note");
        jToolBar1.add(LabelBawah);

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Home.png"))); // NOI18N
        jMenu1.setText("File");
        jMenu1.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N

        jMenuItem1.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Shutdown.png"))); // NOI18N
        jMenuItem1.setText("Logout");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem15.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        jMenuItem15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Close.png"))); // NOI18N
        jMenuItem15.setText("Exit");
        jMenuItem15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem15ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem15);

        jMenuBar1.add(jMenu1);

        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Main_New_Project.png"))); // NOI18N
        jMenu2.setText("Master");
        jMenu2.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N

        jMenuItem5.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        jMenuItem5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/icon_add_24px.png"))); // NOI18N
        jMenuItem5.setText("Data Bill Of Material");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem5);

        jMenuItem4.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        jMenuItem4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/icon_add_24px.png"))); // NOI18N
        jMenuItem4.setText("Data Packaging Material");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem4);

        jMenuItem3.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/icon_add_24px.png"))); // NOI18N
        jMenuItem3.setText("Data Raw Material");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem3);

        jMenuItem2.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/icon_add_24px.png"))); // NOI18N
        jMenuItem2.setText("Data Supplier");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);

        jMenuItem6.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        jMenuItem6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/icon_add_24px.png"))); // NOI18N
        jMenuItem6.setText("Data User");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem6);

        jMenuBar1.add(jMenu2);

        jMenu3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Garage_garbage_trash_recycle_bin_delete.png"))); // NOI18N
        jMenu3.setText("Warehouse");
        jMenu3.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N

        jMenuItem7.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        jMenuItem7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/goods_warehouse_products_shipping-24.png"))); // NOI18N
        jMenuItem7.setText("BPPB");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem7);

        jMenuBar1.add(jMenu3);

        jMenu5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/gtk-edit.png"))); // NOI18N
        jMenu5.setText("PPIC");
        jMenu5.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N

        jMenuItem13.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        jMenuItem13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/trans.png"))); // NOI18N
        jMenuItem13.setText("Master Composition");
        jMenuItem13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem13ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem13);

        jMenuItem8.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        jMenuItem8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Filetype-RAW-icon.png"))); // NOI18N
        jMenuItem8.setText("PPRM");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem8);

        jMenuBar1.add(jMenu5);

        jMenu6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/custom-reports.png"))); // NOI18N
        jMenu6.setText("Report");
        jMenu6.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N

        jReportBPPB.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        jReportBPPB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/reports.png"))); // NOI18N
        jReportBPPB.setText("Transaksi BPPB");
        jReportBPPB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jReportBPPBActionPerformed(evt);
            }
        });
        jMenu6.add(jReportBPPB);

        jMenuItem11.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        jMenuItem11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/reports.png"))); // NOI18N
        jMenuItem11.setText("Transaksi MCB");
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem11);

        jReportRM.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        jReportRM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/reports.png"))); // NOI18N
        jReportRM.setText("Transaksi PPRM");
        jReportRM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jReportRMActionPerformed(evt);
            }
        });
        jMenu6.add(jReportRM);

        jMenuBar1.add(jMenu6);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 930, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jReportBPPBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jReportBPPBActionPerformed
       lap_bppb bppb = new lap_bppb();
       bppb.setVisible(true); 
    }//GEN-LAST:event_jReportBPPBActionPerformed

    private void jReportRMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jReportRMActionPerformed
       lap_pprm pprm = new lap_pprm();
       pprm.setVisible(true);
    }//GEN-LAST:event_jReportRMActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        PPRM pprm = new PPRM();
        pprm.setVisible(true);
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        FrmBPPB bppb = new FrmBPPB();
        bppb.setVisible(true);
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem13ActionPerformed
       FrmMCB mcb = new FrmMCB();
       mcb.setVisible(true);  
    }//GEN-LAST:event_jMenuItem13ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
    FrmRM rm = new FrmRM();
    rm.setVisible(true);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
    FrmPM pm = new FrmPM();
    pm.setVisible(true);    
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem15ActionPerformed
    dispose();
    }//GEN-LAST:event_jMenuItem15ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
    int clossing;
        clossing = JOptionPane.showConfirmDialog(this, "Apa anda yakin ingin Logout?",
                "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (clossing==0){
            FrmLogin lg = new FrmLogin();
            lg.setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        int clossing;
        clossing = JOptionPane.showConfirmDialog(this, "Apakah anda yakin, mau keluar dari aplikasi ini?",
                "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (clossing==0){
            this.dispose();
        }
    }//GEN-LAST:event_formWindowClosing

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
    FrmUser user = new FrmUser();
    user.setVisible(true);
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
    lap_mcb mcb = new lap_mcb();
    mcb.setVisible(true);
    }//GEN-LAST:event_jMenuItem11ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
    FrmSupplier sup = new FrmSupplier();
    sup.setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
    FrmBulk bulk = new FrmBulk();
    bulk.setVisible(true);        
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LabelBawah;
    private javax.swing.JLabel LabelTanggal;
    private javax.swing.JLabel LabelWaktu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem15;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JMenuItem jReportBPPB;
    private javax.swing.JMenuItem jReportRM;
    private javax.swing.JToolBar jToolBar1;
    // End of variables declaration//GEN-END:variables
}
