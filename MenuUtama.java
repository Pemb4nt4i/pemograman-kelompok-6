import javax.swing.*;
import java.awt.*;

public class MenuUtama extends JFrame {

    public MenuUtama() {
        setTitle("APLIKASI KASIR & STOK BARANG");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel judul = new JLabel("SISTEM KASIR & MANAJEMEN STOK", JLabel.CENTER);
        judul.setFont(new Font("Arial", Font.BOLD, 18));
        add(judul, BorderLayout.NORTH);

        JMenuBar menuBar = new JMenuBar();

        JMenu menuMaster = new JMenu("Master Data");
        JMenuItem menuStok = new JMenuItem("Manajemen Stok");

        JMenu menuTransaksi = new JMenu("Transaksi");
        JMenuItem menuKasir = new JMenuItem("Kasir");

        JMenu menuLaporan = new JMenu("Laporan");
        JMenuItem menuLaporanStok = new JMenuItem("Laporan Stok");

        JMenuItem menuKeluar = new JMenuItem("Keluar");

        menuMaster.add(menuStok);
        menuTransaksi.add(menuKasir);
        menuLaporan.add(menuLaporanStok);

        menuBar.add(menuMaster);
        menuBar.add(menuTransaksi);
        menuBar.add(menuLaporan);
        menuBar.add(menuKeluar);

        setJMenuBar(menuBar);

        // EVENT MENU
        menuStok.addActionListener(e -> new ManajemenStokLengkap().setVisible(true));
        menuKasir.addActionListener(e -> new AplikasiKasirGUI().setVisible(true));
        menuLaporanStok.addActionListener(e -> new LaporanKeuanganForm().setVisible(true));
        menuKeluar.addActionListener(e -> System.exit(0));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MenuUtama().setVisible(true));
    }
}
