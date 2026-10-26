package Pertemuan1.Latihan;
import javax.swing.*;

public class Latihan1 {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Latihan1();
        });
    }

     public Latihan1() {
        JFrame frame = new JFrame("Latihan 1");
        JLabel label = new JLabel("Hello, World!", SwingConstants.CENTER);
        frame.add(label); // <--- TAMBAHKAN BARIS INI
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setVisible(true);
    } 
}
