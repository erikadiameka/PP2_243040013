package Pertemuan2.Latihan;

import javax.swing.*;
import java.awt.*;

public class LatihanP2 {
    private final JFrame frame = new JFrame("Latihan P2 - Form & Ringkasan");
    private final CardLayout cardLayout = new CardLayout();
    private final JPanel panelUtama = new JPanel(cardLayout);

    private final JTextField namaField = new JTextField(20);
    private final JTextField stokField = new JTextField(20);
    private final JTextArea ringkasanArea = new JTextArea(5, 20);

    public LatihanP2() {
        panelUtama.add(buatPanelForm(), "FORM");
        panelUtama.add(buatPanelRingkasan(), "RINGKASAN");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panelUtama);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private JPanel buatPanelForm() {
        JPanel formPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        formPanel.add(new JLabel("Nama Barang:"));
        formPanel.add(namaField);
        formPanel.add(new JLabel("Stok:"));
        formPanel.add(stokField);

        JButton btnTinjau = new JButton("Tinjau Ringkasan");
        btnTinjau.addActionListener(e -> prosesData());

        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        panel.add(formPanel, BorderLayout.CENTER);
        panel.add(btnTinjau, BorderLayout.SOUTH);
        return panel;
    }

    private JPanel buatPanelRingkasan() {
        ringkasanArea.setEditable(false);
        JButton btnKembali = new JButton("Kembali ke Form");
        
        btnKembali.addActionListener(e -> {
            cardLayout.show(panelUtama, "FORM");
            namaField.requestFocusInWindow();
        });

        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        panel.add(new JLabel("Ringkasan Data:"), BorderLayout.NORTH);
        panel.add(new JScrollPane(ringkasanArea), BorderLayout.CENTER);
        panel.add(btnKembali, BorderLayout.SOUTH);
        return panel;
    }

    private void prosesData() {
        String nama = namaField.getText().trim();
        String teksStok = stokField.getText().trim();

        if (nama.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Nama barang tidak boleh kosong!", "Validasi Gagal", JOptionPane.ERROR_MESSAGE);
            namaField.requestFocusInWindow();
            return;
        }

        try {
            int stok = Integer.parseInt(teksStok);
            if (stok < 0) {
                JOptionPane.showMessageDialog(frame, "Stok tidak boleh negatif!", "Validasi Gagal", JOptionPane.ERROR_MESSAGE);
                stokField.requestFocusInWindow();
                return;
            }

            String ringkasan = "Nama Barang: " + nama + "\nStok Tersedia: " + stok;
            ringkasanArea.setText(ringkasan);
            cardLayout.show(panelUtama, "RINGKASAN");

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Stok harus berupa angka bulat!", "Validasi Gagal", JOptionPane.ERROR_MESSAGE);
            stokField.requestFocusInWindow();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LatihanP2::new);
    }
}