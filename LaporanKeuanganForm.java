
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class LaporanKeuanganForm extends javax.swing.JFrame {

    DefaultTableModel model;
    double totalMasuk = 0;
    double totalKeluar = 0;

    public LaporanKeuanganForm() {
        initComponents();
        model = (DefaultTableModel) tabel.getModel();
    }

    private void initComponents() {

        JLabel lblTanggal = new JLabel("Tanggal");
        JLabel lblKeterangan = new JLabel("Keterangan");
        JLabel lblJenis = new JLabel("Jenis");
        JLabel lblJumlah = new JLabel("Jumlah");

        JTextField txtTanggal = new JTextField();
        JTextField txtKeterangan = new JTextField();
        JTextField txtJumlah = new JTextField();

        JComboBox<String> cbJenis = new JComboBox<>(new String[]{"Pemasukan", "Pengeluaran"});

        JButton btnTambah = new JButton("Tambah");

        tabel = new JTable();
        tabel.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{"Tanggal", "Keterangan", "Jenis", "Jumlah"}
        ));

        JLabel lblMasuk = new JLabel("Total Pemasukan: ");
        JLabel lblKeluar = new JLabel("Total Pengeluaran: ");
        JLabel lblSaldo = new JLabel("Saldo: ");

        lblTotalMasuk = new JLabel("0");
        lblTotalKeluar = new JLabel("0");
        lblTotalSaldo = new JLabel("0");

        JScrollPane scrollPane = new JScrollPane(tabel);

        btnTambah.addActionListener(e -> {
            String tanggal = txtTanggal.getText();
            String ket = txtKeterangan.getText();
            String jenis = cbJenis.getSelectedItem().toString();
            double jumlah = Double.parseDouble(txtJumlah.getText());

            model.addRow(new Object[]{tanggal, ket, jenis, jumlah});

            if (jenis.equals("Pemasukan")) {
                totalMasuk += jumlah;
            } else {
                totalKeluar += jumlah;
            }

            lblTotalMasuk.setText(String.valueOf(totalMasuk));
            lblTotalKeluar.setText(String.valueOf(totalKeluar));
            lblTotalSaldo.setText(String.valueOf(totalMasuk - totalKeluar));

            txtTanggal.setText("");
            txtKeterangan.setText("");
            txtJumlah.setText("");
        });

        setLayout(null);

        lblTanggal.setBounds(20, 20, 100, 25);
        txtTanggal.setBounds(120, 20, 150, 25);

        lblKeterangan.setBounds(20, 55, 100, 25);
        txtKeterangan.setBounds(120, 55, 150, 25);

        lblJenis.setBounds(20, 90, 100, 25);
        cbJenis.setBounds(120, 90, 150, 25);

        lblJumlah.setBounds(20, 125, 100, 25);
        txtJumlah.setBounds(120, 125, 150, 25);

        btnTambah.setBounds(120, 160, 150, 30);

        scrollPane.setBounds(300, 20, 450, 200);

        lblMasuk.setBounds(300, 240, 150, 25);
        lblTotalMasuk.setBounds(450, 240, 100, 25);

        lblKeluar.setBounds(300, 270, 150, 25);
        lblTotalKeluar.setBounds(450, 270, 100, 25);

        lblSaldo.setBounds(300, 300, 150, 25);
        lblTotalSaldo.setBounds(450, 300, 100, 25);

        add(lblTanggal);
        add(txtTanggal);
        add(lblKeterangan);
        add(txtKeterangan);
        add(lblJenis);
        add(cbJenis);
        add(lblJumlah);
        add(txtJumlah);
        add(btnTambah);
        add(scrollPane);
        add(lblMasuk);
        add(lblTotalMasuk);
        add(lblKeluar);
        add(lblTotalKeluar);
        add(lblSaldo);
        add(lblTotalSaldo);

        setTitle("Aplikasi Laporan Keuangan");
        setSize(800, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private JTable tabel;
    private JLabel lblTotalMasuk;
    private JLabel lblTotalKeluar;
    private JLabel lblTotalSaldo;

    public static void main(String[] args) {
        new LaporanKeuanganForm().setVisible(true);
    }
}
