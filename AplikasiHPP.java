
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AplikasiHPP extends JFrame {

    private JTextField txtBahanBaku, txtTenagaKerja, txtOverhead, txtJumlahProduksi, txtPersenLaba;
    private JTextArea txtHasil;

    public AplikasiHPP() {
        setTitle("Aplikasi Perhitungan HPP & Laba");
        setSize(400, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel utama
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Komponen input
        panel.add(new JLabel("Biaya Bahan Baku (Rp):"));
        txtBahanBaku = new JTextField();
        panel.add(txtBahanBaku);

        panel.add(new JLabel("Biaya Tenaga Kerja (Rp):"));
        txtTenagaKerja = new JTextField();
        panel.add(txtTenagaKerja);

        panel.add(new JLabel("Biaya Overhead (Rp):"));
        txtOverhead = new JTextField();
        panel.add(txtOverhead);

        panel.add(new JLabel("Jumlah Produksi:"));
        txtJumlahProduksi = new JTextField();
        panel.add(txtJumlahProduksi);

        panel.add(new JLabel("Persentase Laba (%):"));
        txtPersenLaba = new JTextField();
        panel.add(txtPersenLaba);

        JButton btnHitung = new JButton("Hitung");
        panel.add(btnHitung);

        JButton btnReset = new JButton("Reset");
        panel.add(btnReset);

        // Area hasil
        txtHasil = new JTextArea();
        txtHasil.setEditable(false);
        txtHasil.setBorder(BorderFactory.createTitledBorder("Hasil Perhitungan"));

        // Event tombol Hitung
        btnHitung.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double bahanBaku = Double.parseDouble(txtBahanBaku.getText());
                    double tenagaKerja = Double.parseDouble(txtTenagaKerja.getText());
                    double overhead = Double.parseDouble(txtOverhead.getText());
                    int jumlahProduksi = Integer.parseInt(txtJumlahProduksi.getText());
                    double persenLaba = Double.parseDouble(txtPersenLaba.getText());

                    double totalBiaya = bahanBaku + tenagaKerja + overhead;
                    double hpp = totalBiaya / jumlahProduksi;
                    double labaPerUnit = hpp * (persenLaba / 100);
                    double hargaJual = hpp + labaPerUnit;

                    txtHasil.setText(
                            "Total Biaya Produksi : Rp " + totalBiaya
                            + "\nHPP per Unit         : Rp " + hpp
                            + "\nHarga Jual per Unit : Rp " + hargaJual
                    );

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null,
                            "Input tidak valid! Harap isi dengan angka.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Event tombol Reset
        btnReset.addActionListener(e -> {
            txtBahanBaku.setText("");
            txtTenagaKerja.setText("");
            txtOverhead.setText("");
            txtJumlahProduksi.setText("");
            txtPersenLaba.setText("");
            txtHasil.setText("");
        });

        add(panel, BorderLayout.NORTH);
        add(txtHasil, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new AplikasiHPP().setVisible(true);
        });
    }
}
