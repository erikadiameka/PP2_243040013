package Pertemuan1.Tugas;

import javax.swing.*;
import java.awt.*;

public class FormPendaftaran {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FormPendaftaran().tampilkanGUI());
    }

    private void tampilkanGUI() {
        JFrame frame = new JFrame("Form Pendaftaran Kegiatan");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panelUtama = new JPanel(new BorderLayout(10, 10));
        panelUtama.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JPanel panelIdentitas = new JPanel(new GridBagLayout());
        panelIdentitas.setBorder(BorderFactory.createTitledBorder("Identitas Peserta"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); 
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0;
        panelIdentitas.add(new JLabel("Nama Lengkap:"), gbc);
        gbc.gridx = 1; gbc.gridy = 0; gbc.weightx = 1.0;
        panelIdentitas.add(new JTextField(20), gbc);

        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0;
        panelIdentitas.add(new JLabel("NIM:"), gbc);
        gbc.gridx = 1; gbc.gridy = 1; gbc.weightx = 1.0;
        panelIdentitas.add(new JTextField(20), gbc);

        gbc.gridx = 0; gbc.gridy = 2; gbc.weightx = 0;
        panelIdentitas.add(new JLabel("Jurusan:"), gbc);
        gbc.gridx = 1; gbc.gridy = 2; gbc.weightx = 1.0;
        String[] jurusan = {"Teknik Informatika", "Sistem Informasi", "Teknik Komputer"};
        panelIdentitas.add(new JComboBox<>(jurusan), gbc);

        JPanel panelKegiatan = new JPanel();
        panelKegiatan.setLayout(new BoxLayout(panelKegiatan, BoxLayout.Y_AXIS));
        panelKegiatan.setBorder(BorderFactory.createTitledBorder("Pilihan Kegiatan"));

        JPanel panelRadio = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JRadioButton rbMahasiswa = new JRadioButton("Mahasiswa");
        JRadioButton rbDosen = new JRadioButton("Dosen");
        JRadioButton rbUmum = new JRadioButton("Umum");

        ButtonGroup bgTipe = new ButtonGroup();
        bgTipe.add(rbMahasiswa);
        bgTipe.add(rbDosen);
        bgTipe.add(rbUmum);
        rbMahasiswa.setSelected(true); 

        panelRadio.add(new JLabel("Tipe Peserta: "));
        panelRadio.add(rbMahasiswa);
        panelRadio.add(rbDosen);
        panelRadio.add(rbUmum);

        JPanel panelCheckbox = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelCheckbox.add(new JLabel("Workshop yang diikuti: "));
        panelCheckbox.add(new JCheckBox("Java Desktop"));
        panelCheckbox.add(new JCheckBox("Web Dev"));
        panelCheckbox.add(new JCheckBox("AI Basic"));

        panelKegiatan.add(panelRadio);
        panelKegiatan.add(panelCheckbox);

        JPanel panelBawah = new JPanel(new BorderLayout(5, 5));
        panelBawah.setBorder(BorderFactory.createTitledBorder("Catatan Tambahan"));
        
        JTextArea areaCatatan = new JTextArea(4, 20);
        areaCatatan.setLineWrap(true);
        JScrollPane scrollCatatan = new JScrollPane(areaCatatan);
        panelBawah.add(scrollCatatan, BorderLayout.CENTER);

        JButton btnDaftar = new JButton("Daftar Sekarang");
        JPanel panelTombol = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelTombol.add(btnDaftar);
        panelBawah.add(panelTombol, BorderLayout.SOUTH);

        panelUtama.add(panelIdentitas, BorderLayout.NORTH);
        panelUtama.add(panelKegiatan, BorderLayout.CENTER);
        panelUtama.add(panelBawah, BorderLayout.SOUTH);

        frame.add(panelUtama);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}