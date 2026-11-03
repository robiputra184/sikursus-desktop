package view;

import model.Peserta;
import dao.PesertaDAO;

import java.awt.*;
import java.awt.print.PrinterException;
import java.sql.SQLException;
import java.util.List;
import java.util.Locale;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

public class MasterData extends JFrame {

    // ================= WARNA (Sesuai Kode Asli) =================
    private final Color warnaBackground = new Color(0, 255, 0);       // Hijau terang
    private final Color warnaInput = Color.WHITE;                        // Putih untuk input
    private final Color warnaTombol = Color.WHITE;                       // Putih untuk tombol
    private final Color warnaText = Color.BLACK;                         // Hitam untuk text
    private final Color warnaBorder = new Color(0, 180, 0);             // Hijau gelap untuk border

    // ================= DATABASE =================
    private final PesertaDAO dao = new PesertaDAO();
    private int selectedId = -1;

    // ================= FORM =================
    private JTextField txtNim;
    private JTextField txtNama;
    private JComboBox<String> cmbGender;
    private JTextField txtProdi;
    private JTextField txtNoHp;
    private JTextField txtEmail;

    // ================= TABEL =================
    private JTextField txtCari;
    private JTable tblPeserta;
    private DefaultTableModel tableModel;

    // ================= CONSTRUCTOR =================
    public MasterData() {
        setTitle("Master Data Peserta - SIKURSUS Desktop");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(950, 700));
        setSize(1000, 750);
        setLocationRelativeTo(null);

        initComponents();
        loadTable("");
    }

    // ================= INIT COMPONENTS =================
    private void initComponents() {
        JPanel panelUtama = new JPanel(new BorderLayout());
        panelUtama.setBackground(warnaBackground);

        // HEADER
        JLabel lblJudul = new JLabel("Master Data Peserta", SwingConstants.CENTER);
        lblJudul.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblJudul.setForeground(warnaText);
        lblJudul.setBorder(new EmptyBorder(10, 0, 10, 0));

        // CONTENT PANEL (Vertikal Layout)
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(warnaBackground);
        contentPanel.setBorder(new EmptyBorder(5, 15, 15, 15));

        JPanel formPanel = buildFormPanel();
        JPanel tablePanel = buildTablePanel();

        contentPanel.add(formPanel);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        contentPanel.add(tablePanel);

        panelUtama.add(lblJudul, BorderLayout.NORTH);
        panelUtama.add(contentPanel, BorderLayout.CENTER);

        setContentPane(panelUtama);
    }

    // ================= FORM PANEL =================
    private JPanel buildFormPanel() {
        JPanel panelForm = new JPanel(new GridBagLayout());
        panelForm.setBackground(warnaBackground);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(4, 6, 4, 6);

        // Inisialisasi komponen form
        txtNim = createField();
        txtNama = createField();
        
        cmbGender = new JComboBox<>(new String[]{
            "-- Pilih Jenis Kelamin --",
            "Laki-laki",
            "Perempuan"
        });
        styleCombo(cmbGender);

        txtProdi = createField();
        txtNoHp = createField();
        txtEmail = createField();

        // TOMBOL
        JButton btnSimpan = createButton("Simpan");
        JButton btnUbah = createButton("Ubah");
        JButton btnHapus = createButton("Hapus");
        JButton btnReset = createButton("Reset");
        JButton btnCetakData = createButton("Cetak Semua");

        btnSimpan.addActionListener(e -> simpanData());
        btnUbah.addActionListener(e -> ubahData());
        btnHapus.addActionListener(e -> hapusData());
        btnReset.addActionListener(e -> resetForm());
        btnCetakData.addActionListener(e -> cetakDataPeserta());

        // Panel Tombol
        JPanel panelTombol = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 4));
        panelTombol.setBackground(warnaBackground);
        panelTombol.add(btnSimpan);
        panelTombol.add(btnUbah);
        panelTombol.add(btnHapus);
        panelTombol.add(btnReset);
        panelTombol.add(btnCetakData);

        // Grid Input
        int row = 0;
        addFormField(panelForm, gbc, "NIM", txtNim, row++);
        addFormField(panelForm, gbc, "Nama Peserta", txtNama, row++);
        addFormField(panelForm, gbc, "Jenis Kelamin", cmbGender, row++);
        addFormField(panelForm, gbc, "Program Studi", txtProdi, row++);
        addFormField(panelForm, gbc, "Nomor HP", txtNoHp, row++);
        addFormField(panelForm, gbc, "Email", txtEmail, row++);

        // Baris Tombol
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;
        panelForm.add(panelTombol, gbc);

        return panelForm;
    }

    // ================= TABLE PANEL =================
    private JPanel buildTablePanel() {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBackground(warnaBackground);

        // Panel pencarian
        JPanel searchPanel = new JPanel(new BorderLayout(5, 0));
        searchPanel.setBackground(warnaBackground);

        JLabel lblCari = new JLabel("Pencarian: ");
        lblCari.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblCari.setForeground(warnaText);

        txtCari = new JTextField();
        txtCari.setBackground(warnaInput);
        txtCari.setForeground(warnaText);
        txtCari.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        txtCari.setBorder(new LineBorder(warnaBorder, 1));
        txtCari.setPreferredSize(new Dimension(200, 26));

        JButton btnCari = createButton("Cari");
        btnCari.setPreferredSize(new Dimension(80, 26));
        btnCari.addActionListener(e -> loadTable(txtCari.getText()));

        JPanel tombolPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        tombolPanel.setBackground(warnaBackground);
        tombolPanel.add(btnCari);

        searchPanel.add(lblCari, BorderLayout.WEST);
        searchPanel.add(txtCari, BorderLayout.CENTER);
        searchPanel.add(tombolPanel, BorderLayout.EAST);

        panel.add(searchPanel, BorderLayout.NORTH);

        // Tabel
        tableModel = new DefaultTableModel(
            new Object[]{"ID", "NIM", "Nama", "JK", "Prodi", "No. HP", "Email"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tblPeserta = new JTable(tableModel);
        tblPeserta.setRowHeight(25);
        tblPeserta.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        tblPeserta.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblPeserta.setAutoCreateRowSorter(true);
        tblPeserta.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 11));
        tblPeserta.getTableHeader().setBackground(warnaInput);
        tblPeserta.getTableHeader().setForeground(warnaText);
        tblPeserta.setGridColor(warnaBorder);
        tblPeserta.setSelectionBackground(new Color(200, 255, 200));
        tblPeserta.setSelectionForeground(warnaText);

        JScrollPane tableScroll = new JScrollPane(tblPeserta);
        tableScroll.setBorder(new LineBorder(warnaBorder, 1));
        tableScroll.setPreferredSize(new Dimension(800, 220));

        panel.add(tableScroll, BorderLayout.CENTER);

        // Event Pencarian Langsung
        txtCari.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { loadTable(txtCari.getText()); }
            public void removeUpdate(DocumentEvent e) { loadTable(txtCari.getText()); }
            public void changedUpdate(DocumentEvent e) { loadTable(txtCari.getText()); }
        });

        // Event Klik Tabel
        tblPeserta.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && tblPeserta.getSelectedRow() >= 0) {
                int row = tblPeserta.convertRowIndexToModel(tblPeserta.getSelectedRow());
                pilihDataTabel(row);
            }
        });

        return panel;
    }

    private void addFormField(JPanel panel, GridBagConstraints gbc, String labelText, JComponent component, int row) {
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = 1;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.EAST;

        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        label.setForeground(warnaText);
        panel.add(label, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(component, gbc);
    }

    private JTextField createField() {
        JTextField field = new JTextField();
        styleField(field);
        return field;
    }

    private void styleField(JTextField field) {
        field.setBackground(warnaInput);
        field.setForeground(warnaText);
        field.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        field.setPreferredSize(new Dimension(200, 26));
        field.setBorder(new LineBorder(warnaBorder, 1));
    }

    private void styleCombo(JComboBox<String> combo) {
        combo.setBackground(warnaInput);
        combo.setForeground(warnaText);
        combo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        combo.setPreferredSize(new Dimension(200, 26));
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(warnaTombol);
        button.setForeground(warnaText);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(105, 28));
        button.setBorder(new LineBorder(warnaBorder, 1));
        return button;
    }

    // ================= PILIH DATA TABEL =================
    private void pilihDataTabel(int row) {
        selectedId = Integer.parseInt(tableModel.getValueAt(row, 0).toString());
        txtNim.setText(valueAt(row, 1));
        txtNama.setText(valueAt(row, 2));

        String jk = valueAt(row, 3);
        if ("L".equalsIgnoreCase(jk) || "Laki-laki".equalsIgnoreCase(jk)) {
            cmbGender.setSelectedIndex(1);
        } else if ("P".equalsIgnoreCase(jk) || "Perempuan".equalsIgnoreCase(jk)) {
            cmbGender.setSelectedIndex(2);
        } else {
            cmbGender.setSelectedIndex(0);
        }

        txtProdi.setText(valueAt(row, 4));
        txtNoHp.setText(valueAt(row, 5));
        txtEmail.setText(valueAt(row, 6));
    }

    private String valueAt(int row, int col) {
        Object value = tableModel.getValueAt(row, col);
        return value == null ? "" : value.toString();
    }

    // ================= LOAD DATA DATABASE =================
    private void loadTable(String keyword) {
        if (tableModel == null) return;

        try {
            List<Peserta> data = dao.findAll();
            tableModel.setRowCount(0);

            String key = keyword == null ? "" : keyword.trim().toLowerCase(Locale.ROOT);

            for (Peserta p : data) {
                String jk = p.getJenisKelamin();
                String jkTampil = "L".equalsIgnoreCase(jk) ? "L" : ("P".equalsIgnoreCase(jk) ? "P" : jk);
                String gabung = p.getNim() + " " + p.getNama() + " " + p.getProdi();

                if (gabung.toLowerCase(Locale.ROOT).contains(key)) {
                    tableModel.addRow(new Object[]{
                        p.getId(), p.getNim(), p.getNama(), jkTampil,
                        p.getProdi(), p.getNoHp(), p.getEmail()
                    });
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Gagal memuat data:\n" + ex.getMessage(),
                "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // ================= SIMPAN DATA =================
    private void simpanData() {
        if (!validasiDataPeserta()) return;

        try {
            Peserta peserta = buatPeserta(0);
            if (dao.insert(peserta)) {
                JOptionPane.showMessageDialog(this, "Data berhasil disimpan!",
                    "Berhasil", JOptionPane.INFORMATION_MESSAGE);
                loadTable(txtCari.getText());
                kosongkanForm();
            }
        } catch (SQLException | IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, "Gagal menyimpan:\n" + ex.getMessage(),
                "Kesalahan", JOptionPane.ERROR_MESSAGE);
        }
    }

    // ================= UBAH DATA =================
    private void ubahData() {
        if (selectedId < 0) {
            JOptionPane.showMessageDialog(this, "Pilih data pada tabel terlebih dahulu.");
            return;
        }
        if (!validasiDataPeserta()) return;

        int jawab = JOptionPane.showConfirmDialog(this,
            "Yakin ingin memperbarui data ini?", "Konfirmasi Ubah",
            JOptionPane.YES_NO_OPTION);

        if (jawab != JOptionPane.YES_OPTION) return;

        try {
            Peserta peserta = buatPeserta(selectedId);
            if (dao.update(peserta)) {
                JOptionPane.showMessageDialog(this, "Data berhasil diperbarui!",
                    "Berhasil", JOptionPane.INFORMATION_MESSAGE);
                loadTable(txtCari.getText());
                kosongkanForm();
            } else {
                JOptionPane.showMessageDialog(this, "Data tidak ditemukan atau tidak berubah.");
            }
        } catch (SQLException | IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, "Gagal memperbarui:\n" + ex.getMessage(),
                "Kesalahan", JOptionPane.ERROR_MESSAGE);
        }
    }

    // ================= HAPUS DATA =================
    private void hapusData() {
        if (selectedId < 0) {
            JOptionPane.showMessageDialog(this, "Pilih data pada tabel terlebih dahulu.");
            return;
        }

        int jawab = JOptionPane.showConfirmDialog(this,
            "Yakin ingin menghapus data ini?", "Konfirmasi Hapus",
            JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

        if (jawab != JOptionPane.YES_OPTION) return;

        try {
            if (dao.delete(selectedId)) {
                JOptionPane.showMessageDialog(this, "Data berhasil dihapus!",
                    "Berhasil", JOptionPane.INFORMATION_MESSAGE);
                loadTable(txtCari.getText());
                kosongkanForm();
            } else {
                JOptionPane.showMessageDialog(this, "Data tidak ditemukan.");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Gagal menghapus:\n" + ex.getMessage(),
                "Kesalahan", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean validasiDataPeserta() {
        if (txtNim.getText().trim().isEmpty() || txtNama.getText().trim().isEmpty()
                || txtProdi.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "NIM, nama, dan program studi wajib diisi.");
            return false;
        }
        if (cmbGender.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Silakan pilih jenis kelamin.");
            return false;
        }
        return true;
    }

    private Peserta buatPeserta(int id) {
        String jk = cmbGender.getSelectedIndex() == 1 ? "L" : "P";
        return new Peserta(id, txtNama.getText().trim(), txtNoHp.getText().trim(),
            txtNim.getText().trim(), jk, txtProdi.getText().trim(), txtEmail.getText().trim());
    }

    private void resetForm() {
        int jawab = JOptionPane.showConfirmDialog(this,
            "Yakin ingin mengosongkan form?", "Konfirmasi Reset",
            JOptionPane.YES_NO_OPTION);

        if (jawab == JOptionPane.YES_OPTION) {
            kosongkanForm();
        }
    }

    private void kosongkanForm() {
        txtNim.setText("");
        txtNama.setText("");
        cmbGender.setSelectedIndex(0);
        txtProdi.setText("");
        txtNoHp.setText("");
        txtEmail.setText("");
        selectedId = -1;
        tblPeserta.clearSelection();
        txtNim.requestFocus();
    }

    private void cetakDataPeserta() {
        if (tableModel.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Tidak ada data untuk dicetak.");
            return;
        }
        try {
            boolean selesai = tblPeserta.print(
                JTable.PrintMode.FIT_WIDTH,
                new java.text.MessageFormat("DATA PESERTA SIKURSUS"),
                new java.text.MessageFormat("Halaman {0}")
            );
            if (selesai) JOptionPane.showMessageDialog(this, "Pencetakan selesai.");
        } catch (PrinterException ex) {
            JOptionPane.showMessageDialog(this, "Gagal mencetak:\n" + ex.getMessage(),
                "Kesalahan Cetak", JOptionPane.ERROR_MESSAGE);
        }
    }

    // ================= MAIN =================
    public static void main(String[] args) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(MasterData.class.getName())
                .log(java.util.logging.Level.WARNING, "Nimbus tidak dapat diaktifkan", ex);
        }

        EventQueue.invokeLater(() -> new MasterData().setVisible(true));
    }
}