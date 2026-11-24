package report;

import config.Database;
import java.io.InputStream;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

public class ReportService {
    public static void cetakPendaftaran(int idPendaftaran) {
        try (Connection conn = Database.getConnection();
             InputStream reportStream = ReportService.class
                .getResourceAsStream("/report/laporan_pendaftaran.jasper")) {

            if (reportStream == null) {
                throw new IllegalStateException(
                    "File laporan_pendaftaran.jasper tidak ditemukan");
            }

            Map<String, Object> params = new HashMap<>();
            params.put("P_ID_PENDAFTARAN", idPendaftaran);

            JasperPrint print = JasperFillManager.fillReport(
                reportStream, params, conn
            );

            if (print.getPages().isEmpty()) {
                JOptionPane.showMessageDialog(null, 
                    "Data laporan tidak ditemukan.");
                return;
            }

            JasperViewer.viewReport(print, false);

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, 
                "Gagal mencetak laporan: " + ex.getMessage());
        }
    }
    
        public static void tampilkanLaporanPeserta() {
        try (Connection conn = config.Database.getConnection();
             InputStream reportStream = ReportService.class.getResourceAsStream("/report/laporan_peserta.jasper")) {

            if (reportStream == null) {
                JOptionPane.showMessageDialog(null, "File laporan_peserta.jasper belum tersedia.\nPastikan file sudah di-compile dan ditaruh di src/main/resources/report/");
                return;
            }

            Map<String, Object> params = new HashMap<>();
            // Jika tidak ada parameter, biarkan HashMap kosong
            
            JasperPrint print = JasperFillManager.fillReport(reportStream, params, conn);

            if (print.getPages().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Data laporan tidak ditemukan.");
                return;
            }

            JasperViewer.viewReport(print, false);

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Gagal mencetak laporan: " + ex.getMessage());
        }
    }
        public static void cetakPembayaranPeriode(String tglAwalStr, String tglAkhirStr) {
    try (Connection conn = config.Database.getConnection();
         InputStream reportStream = ReportService.class.getResourceAsStream("/report/laporan_pembayaran.jasper")) {

        if (reportStream == null) {
            JOptionPane.showMessageDialog(null, "File laporan_pembayaran.jasper tidak ditemukan.");
            return;
        }

        // 1. Konversi String (yyyy-MM-dd) menjadi java.sql.Date
        java.sql.Date tglAwal = java.sql.Date.valueOf(tglAwalStr.trim());
        java.sql.Date tglAkhir = java.sql.Date.valueOf(tglAkhirStr.trim());

        // 2. Masukkan ke Map
        Map<String, Object> params = new HashMap<>();
        params.put("P_TANGGAL_AWAL", tglAwal);
        params.put("P_TANGGAL_AKHIR", tglAkhir);

        // 3. Fill Report
        JasperPrint print = JasperFillManager.fillReport(reportStream, params, conn);

        if (print.getPages().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Tidak ada data pembayaran pada rentang tanggal tersebut.");
            return;
        }

        // 4. Tampilkan
        JasperViewer.viewReport(print, false);

    } catch (IllegalArgumentException e) {
        JOptionPane.showMessageDialog(null, "Format tanggal salah! Gunakan format: yyyy-MM-dd (contoh: 2026-11-01)", "Error Format", JOptionPane.ERROR_MESSAGE);
    } catch (Exception ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(null, "Gagal mencetak laporan: " + ex.getMessage());
    }
}
}