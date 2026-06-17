package controller;

import koneksi.koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class PembayaranController {

    Connection con;

    public PembayaranController() {
        con = koneksi.getConnection();
    }

    public void tampilData(JTable table) {

        DefaultTableModel model = new DefaultTableModel();

        model.addColumn("ID");
        model.addColumn("Nama");
        model.addColumn("NIM");
        model.addColumn("Bulan");
        model.addColumn("Jumlah");
        model.addColumn("Metode");
        model.addColumn("Status");
        model.addColumn("Bukti Transfer");

        try {

            String sql = "SELECT * FROM pembayaran";

            PreparedStatement pst =
                    con.prepareStatement(sql);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                model.addRow(new Object[]{

                    rs.getString("id"),
                    rs.getString("nama"),
                    rs.getString("nim"),
                    rs.getString("bulan"),
                    rs.getString("jumlah"),
                    rs.getString("metode"),
                    rs.getString("status"),
                    rs.getString("bukti_transfer")
                });
            }

            table.setModel(model);

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }

    public void updateData(
            String id,
            String nama,
            String nim,
            String bulan,
            String jumlah,
            String metode,
            String status
    ) {

        try {

            String sql =
                    "UPDATE pembayaran SET "
                    + "nama=?, "
                    + "nim=?, "
                    + "bulan=?, "
                    + "jumlah=?, "
                    + "metode=?, "
                    + "status=? "
                    + "WHERE id=?";

            PreparedStatement pst =
                    con.prepareStatement(sql);

            pst.setString(1, nama);
            pst.setString(2, nim);
            pst.setString(3, bulan);
            pst.setString(4, jumlah);
            pst.setString(5, metode);
            pst.setString(6, status);
            pst.setString(7, id);

            pst.executeUpdate();
            
            if(status.equals("Lunas")) {

    String sqlHapus =
    "DELETE FROM tagihan WHERE nim=? AND bulan=?";

    PreparedStatement pstHapus =
    con.prepareStatement(sqlHapus);

    pstHapus.setString(1, nim);
    pstHapus.setString(2, bulan);

    pstHapus.executeUpdate();
}
            if(status.equals("Ditolak")) {
   // tidak melakukan apa-apa
}

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }

    public void hapusData(String id) {

        try {

            String sql =
                    "DELETE FROM pembayaran WHERE id=?";

            PreparedStatement pst =
                    con.prepareStatement(sql);

            pst.setString(1, id);

            pst.executeUpdate();
            
            

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }

    public void cariData(
            JTable table,
        String kategori,
        String keyword
) {

    DefaultTableModel model =
            new DefaultTableModel();

    model.addColumn("ID");
    model.addColumn("Nama");
    model.addColumn("NIM");
    model.addColumn("Bulan");
    model.addColumn("Jumlah");
    model.addColumn("Metode");
    model.addColumn("Status");
    model.addColumn("Bukti Transfer");

    try {

        // mapping dropdown ke nama kolom database
        if (kategori.equals("Nama")) {
            kategori = "nama";
        } else if (kategori.equals("NIM")) {
            kategori = "nim";
        }

        String sql =
                "SELECT * FROM pembayaran "
                + "WHERE " + kategori + " LIKE ?";

        PreparedStatement pst =
                con.prepareStatement(sql);

        pst.setString(1, "%" + keyword + "%");

        ResultSet rs = pst.executeQuery();

        while (rs.next()) {

            model.addRow(new Object[]{

                rs.getString("id"),
                rs.getString("nama"),
                rs.getString("nim"),
                rs.getString("bulan"),
                rs.getString("jumlah"),
                rs.getString("metode"),
                rs.getString("status"),
                rs.getString("bukti_transfer")
            });
        }

        table.setModel(model);

    } catch (Exception e) {

        System.out.println(e.getMessage());
    }
    }
}