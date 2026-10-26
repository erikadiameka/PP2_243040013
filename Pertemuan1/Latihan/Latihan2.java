package Pertemuan1.Latihan; 

import java.awt.*;
import javax.swing.*;

public class Latihan2 {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Border + Grid Sederhana");

            frame.add(new JLabel("Judul Atas (NORTH)", JLabel.CENTER), BorderLayout.NORTH);

            JPanel panelGrid = new JPanel(new GridLayout(2, 2, 5, 5));
            panelGrid.add(new JButton("Tombol 1"));
            panelGrid.add(new JButton("Tombol 2"));
            panelGrid.add(new JButton("Tombol 3"));
            panelGrid.add(new JButton("Tombol 4"));

            frame.add(panelGrid, BorderLayout.CENTER);

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        }); 
    } 
} 