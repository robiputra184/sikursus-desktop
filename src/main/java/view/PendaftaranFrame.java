package view;

import config.Database;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import report.ReportService;

public class PendaftaranFrame extends JFrame {
    
    // Deklarasi komponen
    private JTable tblPendaftaran;
    private DefaultTableModel tableModel;
    private JButton btnCetakTerpilih;
    private JButton btnTutup;
    
    public PendaftaranFrame() {
        // Setup frame
        setTitle("Laporan Pendaftaran");
        setSize(700, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Inisialisasi komponen
        initComponents();
        layoutComponents();
        setupEventListeners();
        
        // Load data
        loadDataPendaftaran();
    }
    
    private void initComponents() {
        // Buat table model dengan kolom
        tableModel = new DefaultTableModel(
            new Object[]{"ID", "NIM", "Nama Peserta", "Kelas", "Tanggal Daftar", "Status"},
            0
        );
        
        // Buat JTable
        tblPendaftaran = new JTable(tableModel);
        tblPendaftaran.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblPendaftaran.getTableHeader().setReorderingAllowed(false);
        
        // Buat tombol
        btnCetakTerpilih = new JButton("Cetak Terpilih");
        btnTutup = new JButton("Tutup");
    }
    
    private void layoutComponents() {
        // Main panel dengan BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Title label
        JLabel lblTitle = new JLabel("LAPORAN PENDAFTARAN KURSUS", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 16));
        mainPanel.add(lblTitle, BorderLayout.NORTH);
        
        // Scroll pane untuk table
        JScrollPane scrollPane = new JScrollPane(tblPendaftaran);
        scrollPane.setPreferredSize(new Dimension(650, 300));
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        
        // Panel tombol
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        buttonPanel.add(btnCetakTerpilih);
        buttonPanel.add(btnTutup);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        // Tambahkan main panel ke frame
        add(mainPanel);
    }
    
    private void setupEventListeners() {
        // Event tombol cetak
        btnCetakTerpilih.addActionListener(e -> btnCetakTerpilihActionPerformed());
        
        // Event tombol tutup
        btnTutup.addActionListener(e -> this.dispose());
    }
    
    private void loadDataPendaftaran() {
        tableModel.setRowCount(0);
        
        try (Connection conn = Database.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(
                 "SELECT p.id_pendaftaran, ps.nim, ps.nama, " +
                 "kls.nama_kelas, p.tanggal_daftar, p.status " +
                 "FROM pendaftaran p " +
                 "INNER JOIN peserta ps ON p.id_peserta = ps.id_peserta " +
                 "INNER JOIN kelas kls ON p.id_kelas = kls.id_kelas " +
                 "ORDER BY p.id_pendaftaran"
             )) {
            
            while (rs.next()) {
                tableModel.addRow(new Object[]{
                    rs.getInt("id_pendaftaran"),
                    rs.getString("nim"),
                    rs.getString("nama"),
                    rs.getString("nama_kelas"),
                    rs.getDate("tanggal_daftar"),
                    rs.getString("status")
                });
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, 
                "Gagal memuat data: " + ex.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
    
    private void btnCetakTerpilihActionPerformed() {
        int row = tblPendaftaran.getSelectedRow();
        
        if (row == -1) {
            JOptionPane.showMessageDialog(this, 
                "Pilih satu data pendaftaran terlebih dahulu.",
                "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int idPendaftaran = Integer.parseInt(
            tblPendaftaran.getValueAt(row, 0).toString()
        );
        
        System.out.println("ID terpilih: " + idPendaftaran);
        
        // Panggil report service
        ReportService.cetakPendaftaran(idPendaftaran);
    }
    
    public static void main(String args[]) {
        // Set look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Jalankan di Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            new PendaftaranFrame().setVisible(true);
        });
    }
}