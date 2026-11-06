package Pertemuan2.Latihan;
import javax.swing.*;
import java.awt.*;

public class Latihan2 {    private static final int BATAS_STOK = 100000;
    private final JFrame frame = new JFrame("Contoh 2 - Validasi Input");
    private final JTextField namaField = new JTextField(24);
    private final JTextField stokField = new JTextField(24);

    private void tampilkan() {
        JPanel fieldPanel = new JPanel(new GridLayout(2, 2, 8, 8));
        fieldPanel.add(new JLabel("Nama barang:"));
        fieldPanel.add(namaField);
        fieldPanel.add(new JLabel("Stok :"));
        fieldPanel.add(stokField);

        JButton periksaButton = new JButton("Periksa Input");
        periksaButton.addActionListener(event -> periksaInput());

        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        panel.add(fieldPanel, BorderLayout.CENTER);
        panel.add(periksaButton, BorderLayout.SOUTH);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void periksaInput() {
        String nama = namaField.getText().trim();
        String teksStok = stokField.getText().trim();

        if (nama.isEmpty() || teksStok.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Nama dan Stok tidak boleh kosong!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int stok = Integer.parseInt(teksStok);

            if (stok < 0 || stok > BATAS_STOK) {
                JOptionPane.showMessageDialog(frame, "Stok harus antara 0 dan 100000!", "Error", JOptionPane.ERROR_MESSAGE);
                stokField.requestFocusInWindow();
                return;
            }

            JOptionPane.showMessageDialog(frame, "Data Valid!\nNama: " + nama + "\nStok: " + stok, "Berhasil", JOptionPane.INFORMATION_MESSAGE);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Stok harus berupa angka bulat!", "Error", JOptionPane.ERROR_MESSAGE);
            stokField.requestFocusInWindow();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Latihan2 ().tampilkan());
    }
}