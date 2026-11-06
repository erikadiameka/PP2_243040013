package Pertemuan2.Latihan; 

import javax.swing.*;
import java.awt.*;

public class Latihan1 {
    
    private int jumlahKlik = 0;

    private void tampilkan() {
        JFrame frame = new JFrame("Contoh Modul 1");
        JLabel pesan = new JLabel("Tekan tombol ini.");
        JButton tombol = new JButton("Klik Saya");

        tombol.addActionListener(event -> {
            jumlahKlik++;
            pesan.setText("Saya sudah mengklik sebanyak: " + jumlahKlik);
        });

     JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.add(pesan, BorderLayout.CENTER);
        panel.add(tombol, BorderLayout.SOUTH);
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

     public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Latihan1().tampilkan();
        });
    }
}