package view;

import javax.swing.*;
import java.awt.*;
import report.ReportService;

public class LaporanPembayaranFrame extends JFrame {

    // Deklarasi komponen
    private JTextField txtTanggalAwal;
    private JTextField txtTanggalAkhir;
    private JButton btnCetakLaporan;
    private JButton btnTutup;

    public LaporanPembayaranFrame() {
        setTitle("Laporan Pembayaran");
        setSize(450, 220);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        initComponents();
        layoutComponents();
        setupEventListeners();
    }

    private void initComponents() {
        txtTanggalAwal  = new JTextField(15);
        txtTanggalAkhir = new JTextField(15);
        btnCetakLaporan = new JButton("Cetak Laporan");
        btnTutup        = new JButton("Tutup");
    }

    private void layoutComponents() {
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Judul
        JLabel lblTitle = new JLabel("LAPORAN PEMBAYARAN KURSUS", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 16));
        mainPanel.add(lblTitle, BorderLayout.NORTH);

        // Form tanggal (2 baris: label + field)
        JPanel formPanel = new JPanel(new GridLayout(2, 2, 8, 8));
        formPanel.add(new JLabel("Tanggal Awal (yyyy-MM-dd):"));
        formPanel.add(txtTanggalAwal);
        formPanel.add(new JLabel("Tanggal Akhir (yyyy-MM-dd):"));
        formPanel.add(txtTanggalAkhir);
        mainPanel.add(formPanel, BorderLayout.CENTER);

        // Panel tombol (DI SINI letak "Cetak Laporan")
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        buttonPanel.add(btnCetakLaporan);
        buttonPanel.add(btnTutup);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private void setupEventListeners() {
        btnCetakLaporan.addActionListener(e -> btnCetakLaporanActionPerformed());
        btnTutup.addActionListener(e -> this.dispose());
    }

    private void btnCetakLaporanActionPerformed() {
        String tglAwal  = txtTanggalAwal.getText().trim();
        String tglAkhir = txtTanggalAkhir.getText().trim();

        // 1. Cek kosong
        if (tglAwal.isEmpty() || tglAkhir.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Tanggal awal dan akhir tidak boleh kosong!",
                "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // 2. Cek format yyyy-MM-dd
        if (!tglAwal.matches("\\d{4}-\\d{2}-\\d{2}") || !tglAkhir.matches("\\d{4}-\\d{2}-\\d{2}")) {
            JOptionPane.showMessageDialog(this,
                "Format tanggal harus yyyy-MM-dd (contoh: 2026-11-01)",
                "Error Format", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // 3. Cek logika: awal tidak boleh setelah akhir
        if (tglAwal.compareTo(tglAkhir) > 0) {
            JOptionPane.showMessageDialog(this,
                "Tanggal awal tidak boleh setelah tanggal akhir!",
                "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // 4. Semua valid -> panggil service
        ReportService.cetakPembayaranPeriode(tglAwal, tglAkhir);
    }

    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        SwingUtilities.invokeLater(() -> new LaporanPembayaranFrame().setVisible(true));
    }
}