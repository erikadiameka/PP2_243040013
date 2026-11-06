package Pertemuan2.Tugas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Tugas2 {
    // Deklarasi Komponen Global
    private final JFrame frame = new JFrame("Tugas 2 - Pendaftaran Kegiatan");
    private final CardLayout cardLayout = new CardLayout();
    private final JPanel panelUtama = new JPanel(cardLayout);

    // Komponen Form
    private final JTextField namaField = new JTextField(20);
    private final JTextField emailField = new JTextField(20);
    private final JTextField umurField = new JTextField(20);
    private final JRadioButton rbMahasiswa = new JRadioButton("Mahasiswa");
    private final JRadioButton rbUmum = new JRadioButton("Umum");
    private final ButtonGroup bgPeserta = new ButtonGroup();
    private final JLabel pesanError = new JLabel(" ");
    
    // Komponen Ringkasan
    private final JTextArea areaRingkasan = new JTextArea(6, 30);
    
    // Status untuk konfirmasi keluar
    private boolean isDataSaved = false;

    public Tugas2() {
        // Tambahkan halaman ke CardLayout
        panelUtama.add(buatPanelForm(), "FORM");
        panelUtama.add(buatPanelRingkasan(), "RINGKASAN");
        panelUtama.add(buatPanelBantuan(), "BANTUAN");

        // Konfigurasi Frame
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.add(panelUtama);
        frame.pack();
        frame.setLocationRelativeTo(null);

        // Event Listener Konfirmasi Keluar via Tombol X
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                konfirmasiKeluar();
            }
        });

        frame.setVisible(true);
    }

    private JPanel buatPanelForm() {
        JPanel formPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        formPanel.add(new JLabel("Nama Lengkap:"));
        formPanel.add(namaField);
        formPanel.add(new JLabel("Email:"));
        formPanel.add(emailField);
        formPanel.add(new JLabel("Umur (15-100):"));
        formPanel.add(umurField);

        JPanel radioPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        bgPeserta.add(rbMahasiswa);
        bgPeserta.add(rbUmum);
        radioPanel.add(new JLabel("Tipe Peserta: "));
        radioPanel.add(rbMahasiswa);
        radioPanel.add(rbUmum);

        JButton btnSimpan = new JButton("Simpan & Tampilkan");
        JButton btnReset = new JButton("Reset");
        JButton btnBantuan = new JButton("Bantuan");
        JButton btnKeluar = new JButton("Keluar");

        JPanel tombolPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        tombolPanel.add(btnSimpan);
        tombolPanel.add(btnReset);
        tombolPanel.add(btnBantuan);
        tombolPanel.add(btnKeluar);

        // Event Listener
        btnSimpan.addActionListener(e -> prosesSimpan());
        btnReset.addActionListener(e -> resetForm());
        btnBantuan.addActionListener(e -> cardLayout.show(panelUtama, "BANTUAN"));
        btnKeluar.addActionListener(e -> konfirmasiKeluar());

        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        panel.add(formPanel, BorderLayout.NORTH);
        panel.add(radioPanel, BorderLayout.CENTER);
        
        JPanel bawahPanel = new JPanel(new BorderLayout());
        bawahPanel.add(pesanError, BorderLayout.NORTH);
        bawahPanel.add(tombolPanel, BorderLayout.SOUTH);
        
        panel.add(bawahPanel, BorderLayout.SOUTH);
        return panel;
    }

    private JPanel buatPanelRingkasan() {
        areaRingkasan.setEditable(false);
        JButton btnKembali = new JButton("Kembali ke Form");
        JButton btnKeluar = new JButton("Keluar");

        btnKembali.addActionListener(e -> cardLayout.show(panelUtama, "FORM"));
        btnKeluar.addActionListener(e -> konfirmasiKeluar());

        JPanel tombolPanel = new JPanel();
        tombolPanel.add(btnKembali);
        tombolPanel.add(btnKeluar);

        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        panel.add(new JLabel("Ringkasan Pendaftaran"), BorderLayout.NORTH);
        panel.add(new JScrollPane(areaRingkasan), BorderLayout.CENTER);
        panel.add(tombolPanel, BorderLayout.SOUTH);
        return panel;
    }

    private JPanel buatPanelBantuan() {
        JTextArea teksBantuan = new JTextArea(
            "PANDUAN PENDAFTARAN:\n\n" +
            "1. Isi Nama Lengkap Anda.\n" +
            "2. Isi Email dengan format yang benar (contoh@email.com).\n" +
            "3. Isi Umur antara 15 sampai 100 tahun.\n" +
            "4. Pilih Tipe Peserta.\n" +
            "5. Klik 'Simpan & Tampilkan' untuk melihat ringkasan.\n\n" +
            "Tekan 'Kembali' untuk kembali ke form."
        );
        teksBantuan.setEditable(false);
        teksBantuan.setLineWrap(true);
        teksBantuan.setWrapStyleWord(true);

        JButton btnKembali = new JButton("Kembali ke Form");
        btnKembali.addActionListener(e -> cardLayout.show(panelUtama, "FORM"));

        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        panel.add(new JScrollPane(teksBantuan), BorderLayout.CENTER);
        panel.add(btnKembali, BorderLayout.SOUTH);
        return panel;
    }

    private void prosesSimpan() {
        String nama = namaField.getText().trim();
        String email = emailField.getText().trim();
        String umurStr = umurField.getText().trim();

        if (nama.isEmpty()) {
            tampilkanError("Nama wajib diisi!", namaField);
            return;
        }

        if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
            tampilkanError("Format email tidak wajar! (contoh: nama@email.com)", emailField);
            return;
        }

        int umur;
        try {
            umur = Integer.parseInt(umurStr);
            if (umur < 15 || umur > 100) {
                tampilkanError("Umur harus antara 15 dan 100 tahun!", umurField);
                return;
            }
        } catch (NumberFormatException e) {
            tampilkanError("Umur harus berupa angka!", umurField);
            return;
        }

        if (!rbMahasiswa.isSelected() && !rbUmum.isSelected()) {
            pesanError.setText("Pilih tipe peserta terlebih dahulu!");
            pesanError.setForeground(Color.RED);
            return;
        }

        String tipe = rbMahasiswa.isSelected() ? "Mahasiswa" : "Umum";
        String ringkasan = "PENDAFTARAN BERHASIL\n\n" +
                           "Nama: " + nama + "\n" +
                           "Email: " + email + "\n" +
                           "Umur: " + umur + " tahun\n" +
                           "Tipe Peserta: " + tipe;

        areaRingkasan.setText(ringkasan);
        pesanError.setText(" ");
        isDataSaved = true;
        cardLayout.show(panelUtama, "RINGKASAN");
    }

    private void resetForm() {
        namaField.setText("");
        emailField.setText("");
        umurField.setText("");
        bgPeserta.clearSelection();
        pesanError.setText(" ");
        isDataSaved = false;
        namaField.requestFocusInWindow();
    }

    private void tampilkanError(String pesan, JTextField field) {
        pesanError.setText(pesan);
        pesanError.setForeground(Color.RED);
        field.requestFocusInWindow();
    }

    private void konfirmasiKeluar() {
        if (isDataSaved || (namaField.getText().trim().isEmpty() && emailField.getText().trim().isEmpty())) {
            System.exit(0);
        }

        int pilihan = JOptionPane.showConfirmDialog(
            frame,
            "Data pendaftaran belum disimpan. Yakin ingin keluar?",
            "Konfirmasi Keluar",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE
        );

        if (pilihan == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Tugas2::new);
    }
}