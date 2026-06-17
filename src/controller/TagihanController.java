package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JTable;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import koneksi.koneksi;

public class TagihanController {

    Connection con = koneksi.getConnection();
    PreparedStatement pst;
    ResultSet rs;

    public void tampilData(JTable tabel) {

        DefaultTableModel model = new DefaultTableModel();


        model.addColumn("Nama");
        model.addColumn("NIM");
        model.addColumn("Bulan");
        model.addColumn("Jumlah");
        model.addColumn("Status");

        tabel.setModel(model);

        try {

            String sql = "SELECT * FROM tagihan";

            pst = con.prepareStatement(sql);

            rs = pst.executeQuery();

            while (rs.next()) {

                model.addRow(new Object[]{

          
                    rs.getString("nama"),
                    rs.getString("nim"),
                    rs.getString("bulan"),
                    rs.getString("jumlah"),
                    rs.getString("status")

                });

            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e.getMessage());

        }

    }

    public void tambahTagihan(
            String nama,
            String nim,
            String bulan,
            String jumlah,
            String status
    ) {

        try {

            String sql = "INSERT INTO tagihan "
                    + "(nama,nim,bulan,jumlah,status)"
                    + " VALUES(?,?,?,?,?)";

            pst = con.prepareStatement(sql);

            pst.setString(1, nama);
            pst.setString(2, nim);
            pst.setString(3, bulan);
            pst.setString(4, jumlah);
            pst.setString(5, status);

            pst.executeUpdate();

            JOptionPane.showMessageDialog(
                    null,
                    "Data berhasil ditambah"
            );

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    null,
                    e.getMessage()
            );

        }

    }

    public void updateTagihan(
            int id,
            String nama,
            String nim,
            String bulan,
            String jumlah,
            String status
    ) {

        try {

            String sql = "UPDATE tagihan SET "
                    + "nama=?, "
                    + "nim=?, "
                    + "bulan=?, "
                    + "jumlah=?, "
                    + "status=? "
                    + "WHERE idTagihan=?";

            pst = con.prepareStatement(sql);

            pst.setString(1, nama);
            pst.setString(2, nim);
            pst.setString(3, bulan);
            pst.setString(4, jumlah);
            pst.setString(5, status);
            pst.setInt(6, id);

            pst.executeUpdate();

            JOptionPane.showMessageDialog(
                    null,
                    "Data berhasil diupdate"
            );

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    null,
                    e.getMessage()
            );

        }

    }

    public void hapusTagihan(int id) {

        try {

            String sql =
                    "DELETE FROM tagihan WHERE idTagihan=?";

            pst = con.prepareStatement(sql);

            pst.setInt(1, id);

            pst.executeUpdate();

            JOptionPane.showMessageDialog(
                    null,
                    "Data berhasil dihapus"
            );

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    null,
                    e.getMessage()
            );

        }

    }
}