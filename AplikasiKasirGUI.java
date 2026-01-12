
import java.awt.*;
import java.text.NumberFormat;
import java.util.Locale;
import javax.swing.*;

public class AplikasiKasirGUI extends JFrame {

    JTextField txtNama, txtHarga, txtJumlah, txtBayar;
    JTextArea areaStruk;
    JLabel lblTotal;
    double total = 0;

    NumberFormat rupiah = NumberFormat.getCurrencyInstance(Locale.forLanguageTag("id-ID"));

    public AplikasiKasirGUI() {
        setTitle("Aplikasi Kasir Profesional");
        setSize(550, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // ===== PANEL HEADER =====
        JLabel judul = new JLabel("APLIKASI KASIR PROFESIONAL", JLabel.CENTER);
        judul.setFont(new Font("Arial", Font.BOLD, 18));
        judul.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(judul, BorderLayout.NORTH);

        // ===== PANEL INPUT =====
        JPanel panelInput = new JPanel(new GridLayout(4, 2, 10, 10));
        panelInput.setBorder(BorderFactory.createTitledBorder("Input Barang"));

        txtNama = new JTextField();
        txtHarga = new JTextField();
        txtJumlah = new JTextField();

        panelInput.add(new JLabel("Nama Barang"));
        panelInput.add(txtNama);
        panelInput.add(new JLabel("Harga (Rp)"));
        panelInput.add(txtHarga);
        panelInput.add(new JLabel("Jumlah"));
        panelInput.add(txtJumlah);

        JButton btnTambah = new JButton("Tambah Barang");
        panelInput.add(btnTambah);

        lblTotal = new JLabel("Total : Rp 0");
        lblTotal.setFont(new Font("Arial", Font.BOLD, 14));
        panelInput.add(lblTotal);

        // ===== STRUK =====
        areaStruk = new JTextArea();
        areaStruk.setEditable(false);
        areaStruk.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scroll = new JScrollPane(areaStruk);
        scroll.setBorder(BorderFactory.createTitledBorder("Struk Belanja"));

        // ===== PANEL PEMBAYARAN =====
        JPanel panelBayar = new JPanel(new GridLayout(2, 2, 10, 10));
        panelBayar.setBorder(BorderFactory.createTitledBorder("Pembayaran"));

        txtBayar = new JTextField();

        JButton btnBayar = new JButton("Proses Pembayaran");
        JButton btnReset = new JButton("Reset Transaksi");

        panelBayar.add(new JLabel("Uang Bayar"));
        panelBayar.add(txtBayar);
        panelBayar.add(btnBayar);
        panelBayar.add(btnReset);

        // ===== EVENT TAMBAH BARANG =====
        btnTambah.addActionListener(e -> {
            try {
                String nama = txtNama.getText();
                double harga = Double.parseDouble(txtHarga.getText());
                int jumlah = Integer.parseInt(txtJumlah.getText());

                double subtotal = harga * jumlah;
                total += subtotal;

                areaStruk.append(
                        nama + " x" + jumlah
                        + " = " + rupiah.format(subtotal) + "\n");

                lblTotal.setText("Total : " + rupiah.format(total));

                txtNama.setText("");
                txtHarga.setText("");
                txtJumlah.setText("");
                txtNama.requestFocus();

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this,
                        "Input tidak valid!",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        // ===== EVENT PEMBAYARAN =====
        btnBayar.addActionListener(e -> {
            try {
                double bayar = Double.parseDouble(txtBayar.getText());
                double kembalian = bayar - total;

                if (kembalian < 0) {
                    JOptionPane.showMessageDialog(this,
                            "Uang tidak cukup!",
                            "Peringatan",
                            JOptionPane.WARNING_MESSAGE);
                } else {
                    areaStruk.append("\n---------------------------\n");
                    areaStruk.append("TOTAL     : " + rupiah.format(total) + "\n");
                    areaStruk.append("BAYAR     : " + rupiah.format(bayar) + "\n");
                    areaStruk.append("KEMBALIAN : " + rupiah.format(kembalian) + "\n");
                    areaStruk.append("---------------------------\n");
                    areaStruk.append("Terima kasih 😊\n");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this,
                        "Masukkan nominal bayar!",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        // ===== EVENT RESET =====
        btnReset.addActionListener(e -> {
            areaStruk.setText("");
            txtBayar.setText("");
            lblTotal.setText("Total : Rp 0");
            total = 0;
        });

        // ===== LAYOUT =====
        JPanel tengah = new JPanel(new BorderLayout(10, 10));
        tengah.add(panelInput, BorderLayout.NORTH);
        tengah.add(scroll, BorderLayout.CENTER);
        tengah.add(panelBayar, BorderLayout.SOUTH);

        add(tengah, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new AplikasiKasirGUI().setVisible(true);
        });
    }
}
