package view;

import dao.PesertaDAO; // [DITAMBAH]
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.sql.SQLException; // [DITAMBAH]
import java.sql.SQLIntegrityConstraintViolationException; // [DITAMBAH]
import java.util.Locale;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import java.awt.GridLayout;
import model.Peserta; // [DITAMBAH]


public class FormPeserta extends JFrame {

    // [DIUBAH] warna disesuaikan dengan screenshot
    private final Color warnaLatar = new Color(51, 255, 51);
    private final Color warnaHeader = new Color(51, 255, 51);
    private final Color warnaProses = Color.WHITE;
    private final Color warnaReset = Color.WHITE;

    private JPanel panelUtama;
    private JPanel panelHeader;
    private JPanel panelForm;
    private JPanel panelJenis;
    private JPanel panelFasilitas;
    private JPanel panelTombol;
    private JPanel panelJenisKelamin; // [DITAMBAH]

    private JLabel lblJudul;
    private JLabel lblNIM; // [DITAMBAH]
    private JLabel lblNama;
    private JLabel lblJenisKelamin; // [DITAMBAH]
    private JLabel lblProgramStudi; // [DITAMBAH]
    private JLabel lblNomorHP; // [DITAMBAH]
    private JLabel lblEmail; // [DITAMBAH]
    private JLabel lblKodeAkses;
    private JLabel lblJenis;
    private JLabel lblKursus;
    private JLabel lblFasilitas;
    private JLabel lblRingkasan;

    private JTextField txtNIM; // [DITAMBAH]
    private JTextField txtNama;
    private JTextField txtProgramStudi; // [DITAMBAH]
    private JTextField txtNomorHP; // [DITAMBAH]
    private JTextField txtEmail; // [DITAMBAH]
    private JPasswordField txtKodeAkses;
    private JRadioButton rbLakiLaki; // [DITAMBAH]
    private JRadioButton rbPerempuan; // [DITAMBAH]
    private JRadioButton rbMahasiswa;
    private JRadioButton rbUmum;
    private ButtonGroup groupJenisKelamin; // [DITAMBAH]
    private ButtonGroup groupJenis;
    private JComboBox<String> cmbKursus;
    private JCheckBox cbModul;
    private JCheckBox cbSertifikat;
    private JCheckBox cbRekaman;
    private JTextArea txtRingkasan;
    private JScrollPane scrollRingkasan;
    private JButton btnProses;
    private JButton btnReset;

    public FormPeserta() {
        initComponents();

        groupJenisKelamin.add(rbLakiLaki); // [DITAMBAH]
        groupJenisKelamin.add(rbPerempuan); // [DITAMBAH]
        groupJenis.add(rbMahasiswa);
        groupJenis.add(rbUmum);

        txtRingkasan.setEditable(false);
        txtRingkasan.setLineWrap(false);
        getRootPane().setDefaultButton(btnProses);

        setMinimumSize(new Dimension(860, 600));
        // [DIUBAH] ukuran menyesuaikan area layar agar tidak terpotong taskbar
        java.awt.Rectangle layar = java.awt.GraphicsEnvironment
                .getLocalGraphicsEnvironment().getMaximumWindowBounds();
        setSize(new Dimension(Math.min(920, layar.width), Math.min(700, layar.height)));
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        panelUtama = new JPanel(new BorderLayout());
        panelHeader = new JPanel(new BorderLayout());
        panelForm = new JPanel(new GridBagLayout());
        panelJenisKelamin = new JPanel(new FlowLayout(FlowLayout.LEFT, 14, 4)); // [DITAMBAH] [DIUBAH] vgap 4 agar sejajar label
        panelJenis = new JPanel(new FlowLayout(FlowLayout.LEFT, 14, 4)); // [DIUBAH] vgap 4 agar sejajar label
        panelFasilitas = new JPanel(new GridLayout(1, 3, 10, 0));
        panelTombol = new JPanel(new FlowLayout(FlowLayout.CENTER, 44, 0));

        lblJudul = new JLabel("Form Peserta", SwingConstants.CENTER);
        lblNIM = new JLabel("NIM"); // [DITAMBAH]
        lblNama = new JLabel("Nama Peserta");
        lblJenisKelamin = new JLabel("Jenis Kelamin"); // [DITAMBAH]
        lblProgramStudi = new JLabel("Program Studi"); // [DITAMBAH]
        lblNomorHP = new JLabel("Nomor HP"); // [DITAMBAH]
        lblEmail = new JLabel("Email"); // [DITAMBAH]
        lblKodeAkses = new JLabel("Kode Akses");
        lblJenis = new JLabel("Jenis Peserta");
        lblKursus = new JLabel("Pilih Kursus");
        lblFasilitas = new JLabel("Fasilitas Tambahan");
        lblRingkasan = new JLabel("Ringkasan");

        txtNIM = new JTextField(); // [DITAMBAH]
        txtNama = new JTextField();
        txtProgramStudi = new JTextField(); // [DITAMBAH]
        txtNomorHP = new JTextField(); // [DITAMBAH]
        txtEmail = new JTextField(); // [DITAMBAH]
        txtKodeAkses = new JPasswordField();
        rbLakiLaki = new JRadioButton("Laki-laki"); // [DITAMBAH]
        rbPerempuan = new JRadioButton("Perempuan"); // [DITAMBAH]
        groupJenisKelamin = new ButtonGroup(); // [DITAMBAH]
        rbMahasiswa = new JRadioButton("Mahasiswa");
        rbUmum = new JRadioButton("Umum");
        groupJenis = new ButtonGroup();

        cmbKursus = new JComboBox<String>(new String[] {
            "-- Pilih Kursus --",
            "Java Desktop",
            "Data Science",
            "Web Programming",
            "UI/UX Design"
        });

        cbModul = new JCheckBox("Modul Cetak (+Rp50.000)");
        cbSertifikat = new JCheckBox("Sertifikat (+Rp75.000)");
        cbRekaman = new JCheckBox("Akses Rekaman (+Rp40.000)");

        txtRingkasan = new JTextArea(11, 20);
        scrollRingkasan = new JScrollPane(txtRingkasan);

        btnProses = new JButton("Proses");
        btnReset = new JButton("Reset");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("SIKURSUS Desktop - Form Peserta");

        panelUtama.setBackground(warnaLatar);
        panelHeader.setBackground(warnaHeader);
        panelForm.setBackground(warnaLatar);
        panelJenisKelamin.setBackground(warnaLatar); // [DITAMBAH]
        panelJenis.setBackground(warnaLatar);
        panelFasilitas.setBackground(warnaLatar);
        panelTombol.setBackground(warnaLatar);

        lblJudul.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblJudul.setForeground(Color.BLACK);
        lblJudul.setPreferredSize(new Dimension(100, 56)); // [DIUBAH]
        panelHeader.add(lblJudul, BorderLayout.CENTER);

        rbLakiLaki.setBackground(warnaLatar); // [DITAMBAH]
        rbPerempuan.setBackground(warnaLatar); // [DITAMBAH]
        rbMahasiswa.setBackground(warnaLatar);
        rbUmum.setBackground(warnaLatar);
        cbModul.setBackground(warnaLatar);
        cbSertifikat.setBackground(warnaLatar);
        cbRekaman.setBackground(warnaLatar);

        panelJenisKelamin.add(rbLakiLaki); // [DITAMBAH]
        panelJenisKelamin.add(rbPerempuan); // [DITAMBAH]

        panelJenis.add(rbMahasiswa);
        panelJenis.add(rbUmum);

        panelFasilitas.add(cbModul);
        panelFasilitas.add(cbSertifikat);
        panelFasilitas.add(cbRekaman);

        btnProses.setBackground(warnaProses);
        btnProses.setForeground(Color.BLACK);
        btnProses.setFocusPainted(false);
        btnProses.setPreferredSize(new Dimension(160, 42)); // [DIUBAH]
        btnProses.addActionListener(evt -> btnProsesActionPerformed());

        btnReset.setBackground(warnaReset);
        btnReset.setForeground(Color.BLACK);
        btnReset.setFocusPainted(false);
        btnReset.setPreferredSize(new Dimension(160, 42)); // [DIUBAH]
        btnReset.addActionListener(evt -> btnResetActionPerformed());

        panelTombol.add(btnProses);
        panelTombol.add(btnReset);

        txtNIM.setPreferredSize(new Dimension(420, 30)); // [DITAMBAH]
        txtNama.setPreferredSize(new Dimension(420, 30));
        txtProgramStudi.setPreferredSize(new Dimension(420, 30)); // [DITAMBAH]
        txtNomorHP.setPreferredSize(new Dimension(420, 30)); // [DITAMBAH]
        txtEmail.setPreferredSize(new Dimension(420, 30)); // [DITAMBAH]
        txtKodeAkses.setPreferredSize(new Dimension(420, 30));
        cmbKursus.setPreferredSize(new Dimension(420, 30));
        txtKodeAkses.setToolTipText("Kode akses demo praktikum: PBO2026");

        // [DIUBAH] semua baris form dibuat setinggi 30 px agar sejajar dengan label
        panelJenisKelamin.setPreferredSize(new Dimension(420, 30));
        panelJenis.setPreferredSize(new Dimension(420, 30));
        panelFasilitas.setPreferredSize(new Dimension(600, 30));

        // [DIUBAH] font seragam untuk semua label dan input
        Font fontForm = new Font("Segoe UI", Font.PLAIN, 13);
        java.awt.Component[] semuaKomponen = {
            lblNIM, lblNama, lblJenisKelamin, lblProgramStudi, lblNomorHP, lblEmail,
            lblKodeAkses, lblJenis, lblKursus, lblFasilitas, lblRingkasan,
            txtNIM, txtNama, txtProgramStudi, txtNomorHP, txtEmail, txtKodeAkses,
            rbLakiLaki, rbPerempuan, rbMahasiswa, rbUmum, cmbKursus,
            cbModul, cbSertifikat, cbRekaman, btnProses, btnReset
        };
        for (java.awt.Component k : semuaKomponen) {
            k.setFont(fontForm);
        }

        txtRingkasan.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 13));
        txtRingkasan.setBackground(Color.WHITE);
        scrollRingkasan.setPreferredSize(new Dimension(520, 130)); // [DIUBAH]
        scrollRingkasan.setMinimumSize(new Dimension(200, 60));

        panelForm.setBorder(BorderFactory.createEmptyBorder(12, 24, 16, 24));

        addRow(0, lblNIM, txtNIM, 0.0); // [DITAMBAH]
        addRow(1, lblNama, txtNama, 0.0);
        addRow(2, lblJenisKelamin, panelJenisKelamin, 0.0); // [DITAMBAH]
        addRow(3, lblProgramStudi, txtProgramStudi, 0.0); // [DITAMBAH]
        addRow(4, lblNomorHP, txtNomorHP, 0.0); // [DITAMBAH]
        addRow(5, lblEmail, txtEmail, 0.0); // [DITAMBAH]
        addRow(6, lblKodeAkses, txtKodeAkses, 0.0);
        addRow(7, lblJenis, panelJenis, 0.0);
        addRow(8, lblKursus, cmbKursus, 0.0);
        addRow(9, lblFasilitas, panelFasilitas, 0.0);
        addRow(10, lblRingkasan, scrollRingkasan, 1.0);

        GridBagConstraints tombol = new GridBagConstraints();
        tombol.gridx = 0;
        tombol.gridy = 11; // [DIUBAH] dari 6 ke 11
        tombol.gridwidth = 2; // [DIUBAH] di tengah form
        tombol.weightx = 1.0;
        tombol.fill = GridBagConstraints.NONE;
        tombol.anchor = GridBagConstraints.CENTER;
        tombol.insets = new Insets(12, 0, 0, 0);
        panelForm.add(panelTombol, tombol);

        panelUtama.add(panelHeader, BorderLayout.NORTH);
        panelUtama.add(panelForm, BorderLayout.CENTER);
        setContentPane(panelUtama);
    }

    private void addRow(int row, JLabel label, java.awt.Component component, double weightY) {
        GridBagConstraints cLabel = new GridBagConstraints();
        cLabel.gridx = 0;
        cLabel.gridy = row;
        cLabel.anchor = weightY > 0 ? GridBagConstraints.NORTHEAST : GridBagConstraints.EAST; // [DIUBAH]
        cLabel.insets = new Insets(weightY > 0 ? 6 : 0, 0, 7, 16); // [DIUBAH]
        cLabel.ipadx = 18;
        panelForm.add(label, cLabel);

        GridBagConstraints cField = new GridBagConstraints();
        cField.gridx = 1;
        cField.gridy = row;
        cField.weightx = 1.0;
        cField.weighty = weightY;
        cField.fill = weightY > 0 ? GridBagConstraints.BOTH : GridBagConstraints.HORIZONTAL;
        cField.anchor = weightY > 0 ? GridBagConstraints.NORTHWEST : GridBagConstraints.WEST; // [DIUBAH]
        cField.insets = new Insets(0, 0, 7, 0); // [DIUBAH]
        panelForm.add(component, cField);
    }

    private String getJenisPeserta() {
        if (rbMahasiswa.isSelected()) {
            return "Mahasiswa";
        }
        if (rbUmum.isSelected()) {
            return "Umum";
        }
        return "";
    }

    private String getJenisKelamin() { // [DITAMBAH]
        if (rbLakiLaki.isSelected()) {
            return "Laki-laki";
        }
        if (rbPerempuan.isSelected()) {
            return "Perempuan";
        }
        return "";
    }

    private double hitungBiayaDasar(String kursus) {
        switch (kursus) {
            case "Java Desktop":
                return 500_000;
            case "Data Science":
                return 650_000;
            case "Web Programming":
                return 450_000;
            case "UI/UX Design":
                return 400_000;
            default:
                return 0;
        }
    }

    private double hitungBiayaFasilitas() {
        double total = 0;
        if (cbModul.isSelected()) {
            total += 50_000;
        }
        if (cbSertifikat.isSelected()) {
            total += 75_000;
        }
        if (cbRekaman.isSelected()) {
            total += 40_000;
        }
        return total;
    }

    private String buatRingkasanFasilitas() {
        String[] fasilitasDipilih = new String[3];
        int jumlah = 0;

        if (cbModul.isSelected()) {
            fasilitasDipilih[jumlah] = "Modul Cetak";
            jumlah++;
        }
        if (cbSertifikat.isSelected()) {
            fasilitasDipilih[jumlah] = "Sertifikat";
            jumlah++;
        }
        if (cbRekaman.isSelected()) {
            fasilitasDipilih[jumlah] = "Akses Rekaman";
            jumlah++;
        }

        if (jumlah == 0) {
            return "- Tidak ada fasilitas tambahan\n";
        }

        StringBuilder hasil = new StringBuilder();
        for (int i = 0; i < jumlah; i++) {
            hasil.append("- ").append(fasilitasDipilih[i]).append("\n");
        }
        return hasil.toString();
    }

    private boolean validasiInput() {
        String nim = txtNIM.getText().trim(); // [DITAMBAH]
        String nama = txtNama.getText().trim();
        String kode = new String(txtKodeAkses.getPassword());

        if (nim.isEmpty()) { // [DITAMBAH]
            JOptionPane.showMessageDialog(this,
                    "NIM wajib diisi.",
                    "Validasi",
                    JOptionPane.WARNING_MESSAGE);
            txtNIM.requestFocus();
            return false;
        }

        if (nama.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Nama peserta wajib diisi.",
                    "Validasi",
                    JOptionPane.WARNING_MESSAGE);
            txtNama.requestFocus();
            return false;
        }

        if (!rbLakiLaki.isSelected() && !rbPerempuan.isSelected()) { // [DITAMBAH]
            JOptionPane.showMessageDialog(this,
                    "Pilih jenis kelamin.",
                    "Validasi",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        }

        if (txtProgramStudi.getText().trim().isEmpty()) { // [DITAMBAH] wajib, sesuai Peserta.setProdi()
            JOptionPane.showMessageDialog(this,
                    "Program studi wajib diisi.",
                    "Validasi",
                    JOptionPane.WARNING_MESSAGE);
            txtProgramStudi.requestFocus();
            return false;
        }

        if (!kode.equals("PBO2026")) {
            JOptionPane.showMessageDialog(this,
                    "Kode akses demo salah.",
                    "Validasi",
                    JOptionPane.WARNING_MESSAGE);
            txtKodeAkses.requestFocus();
            return false;
        }

        if (!rbMahasiswa.isSelected() && !rbUmum.isSelected()) {
            JOptionPane.showMessageDialog(this,
                    "Pilih jenis peserta.",
                    "Validasi",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        }

        if (cmbKursus.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this,
                    "Pilih kursus terlebih dahulu.",
                    "Validasi",
                    JOptionPane.WARNING_MESSAGE);
            cmbKursus.requestFocus();
            return false;
        }

        return true;
    }

    private void btnProsesActionPerformed() {
        if (!validasiInput()) {
            return;
        }

        String nim = txtNIM.getText().trim(); // [DITAMBAH]
        String nama = txtNama.getText().trim();
        String jenisKelamin = getJenisKelamin(); // [DITAMBAH]
        String programStudi = txtProgramStudi.getText().trim(); // [DITAMBAH]
        String nomorHP = txtNomorHP.getText().trim(); // [DITAMBAH]
        String email = txtEmail.getText().trim(); // [DITAMBAH]
        String jenis = getJenisPeserta();
        String kursus = cmbKursus.getSelectedItem().toString();

        double biayaDasar = hitungBiayaDasar(kursus);
        double diskon = jenis.equals("Mahasiswa") ? biayaDasar * 0.10 : 0;
        double biayaFasilitas = hitungBiayaFasilitas();
        double total = biayaDasar - diskon + biayaFasilitas;

        String fasilitas = buatRingkasanFasilitas();

        String ringkasan =
                "PENDAFTARAN KURSUS\n"
                + "================================\n"
                + "NIM             : " + nim + "\n" // [DITAMBAH]
                + "Nama            : " + nama + "\n"
                + "Jenis Kelamin   : " + jenisKelamin + "\n" // [DITAMBAH]
                + "Program Studi   : " + programStudi + "\n" // [DITAMBAH]
                + "Nomor HP        : " + nomorHP + "\n" // [DITAMBAH]
                + "Email           : " + email + "\n" // [DITAMBAH]
                + "Jenis Peserta   : " + jenis + "\n"
                + "Kursus          : " + kursus + "\n"
                + "Biaya Dasar     : " + formatRupiah(biayaDasar) + "\n"
                + "Diskon          : " + formatRupiah(diskon) + "\n"
                + "Fasilitas:\n" + fasilitas
                + "Biaya Fasilitas : " + formatRupiah(biayaFasilitas) + "\n"
                + "================================\n"
                + "TOTAL           : " + formatRupiah(total);

        // [DITAMBAH] Simpan data peserta ke database (urutan sesuai constructor Peserta)
        PesertaDAO dao = new PesertaDAO();

        try {
            // [DIUBAH] kolom jenis_kelamin di database berisi kode "L" / "P"
            String kodeJenisKelamin = jenisKelamin.equals("Laki-laki") ? "L" : "P";
            Peserta peserta = new Peserta(0, nama, nomorHP, nim, kodeJenisKelamin, programStudi, email);
            boolean berhasil = dao.insert(peserta);
            if (berhasil) {
                txtRingkasan.setText(ringkasan);
                txtRingkasan.setCaretPosition(0);
                JOptionPane.showMessageDialog(this,
                        "Pendaftaran berhasil diproses.\nData peserta berhasil disimpan.",
                        "Berhasil",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this,
                        "Data tidak tersimpan.",
                        "Informasi",
                        JOptionPane.WARNING_MESSAGE);
            }
        } catch (IllegalArgumentException e) {
            // Validasi di class model/Orang, misalnya field wajib yang kosong
            JOptionPane.showMessageDialog(this,
                    e.getMessage(),
                    "Validasi",
                    JOptionPane.WARNING_MESSAGE);
        } catch (SQLIntegrityConstraintViolationException e) {
            JOptionPane.showMessageDialog(this,
                    "NIM " + nim + " sudah terdaftar.",
                    "Validasi",
                    JOptionPane.WARNING_MESSAGE);
            txtNIM.requestFocus();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,
                    "Gagal menyimpan data:\n" + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void btnResetActionPerformed() {
        int jawab = JOptionPane.showConfirmDialog(
                this,
                "Kosongkan seluruh input?",
                "Konfirmasi Reset",
                JOptionPane.YES_NO_OPTION);

        if (jawab == JOptionPane.YES_OPTION) {
            resetForm();
        }
    }

    private void resetForm() {
        txtNIM.setText(""); // [DITAMBAH]
        txtNama.setText("");
        groupJenisKelamin.clearSelection(); // [DITAMBAH]
        txtProgramStudi.setText(""); // [DITAMBAH]
        txtNomorHP.setText(""); // [DITAMBAH]
        txtEmail.setText(""); // [DITAMBAH]
        txtKodeAkses.setText("");
        groupJenis.clearSelection();
        cmbKursus.setSelectedIndex(0);
        cbModul.setSelected(false);
        cbSertifikat.setSelected(false);
        cbRekaman.setSelected(false);
        txtRingkasan.setText("");
        txtNIM.requestFocus(); // [DIUBAH]
    }

    private String formatRupiah(double nilai) {
        String angka = String.format(Locale.US, "%,.0f", nilai).replace(',', '.');
        return "Rp" + angka;
    }

    public static void main(String[] args) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(FormPeserta.class.getName())
                    .log(java.util.logging.Level.WARNING, "Nimbus tidak dapat diaktifkan", ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FormPeserta().setVisible(true);
            }
        });
    }
}