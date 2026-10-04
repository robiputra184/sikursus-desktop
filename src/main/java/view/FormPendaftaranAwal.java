package view;

import javax.swing.JOptionPane;
import model.Kursus;

public class FormPendaftaranAwal extends javax.swing.JFrame {

    public FormPendaftaranAwal() {
        initComponents();
        getRootPane().setDefaultButton(btnProses);

        setMinimumSize(new java.awt.Dimension(700, 520));

        setSize(new java.awt.Dimension(900, 560));
        setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtKodeKursus = new javax.swing.JTextField();
        cmbKursus = new javax.swing.JComboBox<>();
        cmbLevel = new javax.swing.JComboBox<>();
        txtBiaya = new javax.swing.JTextField();
        txtDiskon = new javax.swing.JTextField();
        btnProses = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SIKURSUS Desktop - Pendaftaran Kursus");
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel1.setBackground(new java.awt.Color(51, 255, 51));

        jLabel2.setText("Kode Kursus");

        jLabel5.setText("Nama Kursus");

        jLabel6.setText("Level");

        jLabel4.setText("Biaya Kursus");

        jLabel3.setText("Diskon (%)");

        txtKodeKursus.setText("JAVA-BSC");
        txtKodeKursus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKodeKursusActionPerformed(evt);
            }
        });

        cmbKursus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Java Desktop Fundamental", "Data Science Fundamental", "UI/UX Fundamental" }));

        cmbLevel.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "BASIC", "INTERMEDIATE", "ADVANCED" }));

        btnProses.setText("Proses");
        btnProses.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProsesActionPerformed(evt);
            }
        });

        btnReset.setText("Reset");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setText("Form Pendaftaran Khusus");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(98, 98, 98)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtBiaya)
                    .addComponent(txtDiskon)
                    .addComponent(txtKodeKursus, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbKursus, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmbLevel, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(117, 117, 117))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(237, 237, 237)
                .addComponent(btnProses, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addGap(275, 275, 275))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(jLabel7)
                .addGap(41, 41, 41)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtKodeKursus, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbKursus, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbLevel, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBiaya, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDiskon, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnProses, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(jPanel1, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnProsesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProsesActionPerformed
        String kode = txtKodeKursus.getText().trim();
        String nama = cmbKursus.getSelectedItem().toString();
        String level = cmbLevel.getSelectedItem().toString();
        String strBiaya = txtBiaya.getText().trim();
        String strDiskon = txtDiskon.getText().trim();

        if (kode.isEmpty() || strBiaya.isEmpty() || strDiskon.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Kode kursus, biaya, dan diskon wajib diisi.",
                    "Validasi Input",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            double biaya = Double.parseDouble(strBiaya);
            double diskon = Double.parseDouble(strDiskon);

            if (biaya < 0) {
                JOptionPane.showMessageDialog(this,
                        "Biaya tidak boleh bernilai negatif.",
                        "Validasi Input",
                        JOptionPane.WARNING_MESSAGE);
                txtBiaya.requestFocus();
                return;
            }

            if (diskon < 0 || diskon > 100) {
                JOptionPane.showMessageDialog(this,
                        "Diskon harus berada pada rentang 0 sampai 100.",
                        "Validasi Input",
                        JOptionPane.WARNING_MESSAGE);
                txtDiskon.requestFocus();
                return;
            }

            Kursus kursus = new Kursus(kode, nama, level, biaya);
            double total = kursus.hitungBiayaSetelahDiskon(diskon);

            String pesan = "Kursus: " + nama
                    + "\nKode: " + kode
                    + "\nLevel: " + level
                    + "\nBiaya awal: Rp" + String.format("%,.0f", biaya)
                    + "\nDiskon: " + diskon + "%"
                    + "\nBiaya akhir: Rp" + String.format("%,.0f", total);

            JOptionPane.showMessageDialog(this,
                    pesan,
                    "Hasil Pendaftaran Kursus",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Biaya dan diskon harus berupa angka.\nContoh biaya: 500000 | diskon: 10",
                    "Input Tidak Valid",
                    JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnProsesActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        txtKodeKursus.setText("");
        cmbKursus.setSelectedIndex(0);
        cmbLevel.setSelectedIndex(0);
        txtBiaya.setText("");
        txtDiskon.setText("");
        txtKodeKursus.requestFocus();
    }//GEN-LAST:event_btnResetActionPerformed

    private void txtKodeKursusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKodeKursusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtKodeKursusActionPerformed

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormPendaftaranAwal.class.getName())
                    .log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormPendaftaranAwal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnProses;
    private javax.swing.JButton btnReset;
    private javax.swing.JComboBox<String> cmbKursus;
    private javax.swing.JComboBox<String> cmbLevel;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtBiaya;
    private javax.swing.JTextField txtDiskon;
    private javax.swing.JTextField txtKodeKursus;
    // End of variables declaration//GEN-END:variables
}
