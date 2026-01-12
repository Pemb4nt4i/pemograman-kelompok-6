
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class ManajemenStokLengkap extends JFrame {

    JTextField txtKode, txtNama, txtStok, txtHarga, txtCari;
    JTable table;
    DefaultTableModel model;

    Connection conn;

    public ManajemenStokLengkap() {
        setTitle("Manajemen Stok Barang");
        setSize(900, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        koneksiDatabase();

        // ===== HEADER =====
        JLabel judul = new JLabel("MANAJEMEN STOK BARANG", JLabel.CENTER);
        judul.setFont(new Font("Arial", Font.BOLD, 18));
        add(judul, BorderLayout.NORTH);

        // ===== FORM INPUT =====
        JPanel form = new JPanel(new GridLayout(4, 2, 10, 10));
        form.setBorder(BorderFactory.createTitledBorder("Input Barang"));

        txtKode = new JTextField();
        txtNama = new JTextField();
        txtStok = new JTextField();
        txtHarga = new JTextField();

        form.add(new JLabel("Kode"));
        form.add(txtKode);
        form.add(new JLabel("Nama"));
        form.add(txtNama);
        form.add(new JLabel("Stok"));
        form.add(txtStok);
        form.add(new JLabel("Harga"));
        form.add(txtHarga);

        // ===== BUTTON =====
        JPanel button = new JPanel(new GridLayout(1, 3, 10, 10));
        JButton btnTambah = new JButton("Tambah");
        JButton btnUpdate = new JButton("Update");
        JButton btnHapus = new JButton("Hapus");

        button.add(btnTambah);
        button.add(btnUpdate);
        button.add(btnHapus);

        // ===== SEARCH =====
        JPanel cariPanel = new JPanel(new BorderLayout());
        txtCari = new JTextField();
        cariPanel.setBorder(BorderFactory.createTitledBorder("Cari Barang"));
        cariPanel.add(txtCari);

        // ===== TABLE =====
        model = new DefaultTableModel(new String[]{"ID", "Kode", "Nama", "Stok", "Harga"}, 0);
        table = new JTable(model);
        JScrollPane scroll = new JScrollPane(table);

        loadData("");

        // ===== EVENT SEARCH =====
        txtCari.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent e) {
                loadData(txtCari.getText());
            }
        });

        // ===== EVENT BUTTON =====
        btnTambah.addActionListener(e -> tambah());
        btnUpdate.addActionListener(e -> update());
        btnHapus.addActionListener(e -> hapus());

        table.getSelectionModel().addListSelectionListener(e -> isiForm());

        JPanel kiri = new JPanel(new BorderLayout(10, 10));
        kiri.add(form, BorderLayout.NORTH);
        kiri.add(button, BorderLayout.CENTER);
        kiri.add(cariPanel, BorderLayout.SOUTH);

        add(kiri, BorderLayout.WEST);
        add(scroll, BorderLayout.CENTER);
    }

    private void koneksiDatabase() {
        try {
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost/stok_barang", "root", ""
            );
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Database tidak terhubung: " + e.getMessage());
        }
    }

    private void loadData(String cari) {
        model.setRowCount(0);
        try {
            String sql = "SELECT * FROM barang WHERE nama LIKE ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + cari + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("kode"),
                    rs.getString("nama"),
                    rs.getInt("stok"),
                    rs.getDouble("harga")
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal memuat data: " + e.getMessage());
        }
    }

    private void tambah() {
        try {
            String sql = "INSERT INTO barang (kode,nama,stok,harga) VALUES (?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, txtKode.getText());
            ps.setString(2, txtNama.getText());
            ps.setInt(3, Integer.parseInt(txtStok.getText()));
            ps.setDouble(4, Double.parseDouble(txtHarga.getText()));
            ps.executeUpdate();
            loadData("");
            reset();
        } catch (NumberFormatException | SQLException e) {
            JOptionPane.showMessageDialog(this, "Input salah: " + e.getMessage());
        }
    }

    private void update() {
        int row = table.getSelectedRow();
        if (row >= 0) {
            try {
                int id = Integer.parseInt(model.getValueAt(row, 0).toString());
                String sql = "UPDATE barang SET kode=?, nama=?, stok=?, harga=? WHERE id=?";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, txtKode.getText());
                ps.setString(2, txtNama.getText());
                ps.setInt(3, Integer.parseInt(txtStok.getText()));
                ps.setDouble(4, Double.parseDouble(txtHarga.getText()));
                ps.setInt(5, id);
                ps.executeUpdate();
                loadData("");
                reset();
            } catch (NumberFormatException | SQLException e) {
                JOptionPane.showMessageDialog(this, "Gagal update: " + e.getMessage());
            }
        }
    }

    private void hapus() {
        int row = table.getSelectedRow();
        if (row >= 0) {
            try {
                int id = Integer.parseInt(model.getValueAt(row, 0).toString());
                PreparedStatement ps = conn.prepareStatement(
                        "DELETE FROM barang WHERE id=?");
                ps.setInt(1, id);
                ps.executeUpdate();
                loadData("");
                reset();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Gagal hapus: " + e.getMessage());
            }
        }
    }

    private void isiForm() {
        int row = table.getSelectedRow();
        if (row >= 0) {
            txtKode.setText(model.getValueAt(row, 1).toString());
            txtNama.setText(model.getValueAt(row, 2).toString());
            txtStok.setText(model.getValueAt(row, 3).toString());
            txtHarga.setText(model.getValueAt(row, 4).toString());
        }
    }

    private void reset() {
        txtKode.setText("");
        txtNama.setText("");
        txtStok.setText("");
        txtHarga.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ManajemenStokLengkap().setVisible(true));
    }
}