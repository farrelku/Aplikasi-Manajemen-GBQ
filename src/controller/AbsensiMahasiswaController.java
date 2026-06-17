package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class AbsensiMahasiswaController {

    Connection con;

    public AbsensiMahasiswaController() {

        try {

            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/gbq",
                    "root",
                    "admin123"
            );

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e.getMessage());

        }

    }
    
    public void cariAbsensi(
        JTable tabel,
        String kategori,
        String keyword
) {

    DefaultTableModel model = new DefaultTableModel();

    model.addColumn("ID");
    model.addColumn("Nama");
    model.addColumn("NIM");
    model.addColumn("Tanggal");
    model.addColumn("Status");

    try {

        String kolom = "";

        if (kategori.equals("Nama")) {
            kolom = "u.username";
        } else if (kategori.equals("NIM")) {
            kolom = "m.nim";
        } else if (kategori.equals("Status")) {
            kolom = "a.status";
        }

        String query = "SELECT a.id, u.username, m.nim, a.tanggal, a.status "
                + "FROM absensi a "
                + "JOIN mahasiswa m ON a.mahasiswa_id = m.id "
                + "JOIN logingbq u ON u.user_id = m.user_id "
                + "WHERE " + kolom + " LIKE ?";

        PreparedStatement pst = con.prepareStatement(query);

        pst.setString(1, "%" + keyword + "%");

        ResultSet rs = pst.executeQuery();

        while (rs.next()) {

            model.addRow(new Object[]{
                rs.getInt("id"),
                rs.getString("username"),
                rs.getString("nim"),
                rs.getDate("tanggal"),
                rs.getString("status")
            });

        }

        tabel.setModel(model);

    } catch (Exception e) {

        JOptionPane.showMessageDialog(null, e.getMessage());

    }

}
    
    
// cari mahasiswa
public void cariMahasiswa(
        JTable tabel,
        String keyword
) {

    DefaultTableModel model = new DefaultTableModel();

    model.addColumn("ID");
    model.addColumn("Nama");
    model.addColumn("NIM");

    try {

        String query = "SELECT m.id, u.username, m.nim "
                + "FROM mahasiswa m "
                + "JOIN logingbq u ON u.user_id = m.user_id "
                + "WHERE u.username LIKE ? "
                + "OR m.nim LIKE ?";

        PreparedStatement pst = con.prepareStatement(query);

        pst.setString(1, "%" + keyword + "%");
        pst.setString(2, "%" + keyword + "%");

        ResultSet rs = pst.executeQuery();

        while (rs.next()) {

            model.addRow(new Object[]{
                rs.getInt("id"),
                rs.getString("username"),
                rs.getString("nim")
            });

        }

        tabel.setModel(model);

    } catch (Exception e) {

        JOptionPane.showMessageDialog(null, e.getMessage());

    }

}

    // tampil mahasiswa
    public void tampilMahasiswa(JTable tabel) {

        DefaultTableModel model = new DefaultTableModel();

        model.addColumn("ID");
        model.addColumn("Nama");
        model.addColumn("NIM");

        try {

            String query = "SELECT m.id, u.username, m.nim "
        + "FROM logingbq u "
        + "JOIN mahasiswa m ON u.user_id = m.user_id "
        + "WHERE u.role='Mahasiswa'";

            PreparedStatement pst = con.prepareStatement(query);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                model.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("username"),
                    rs.getString("nim")
                });

            }

            tabel.setModel(model);

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e.getMessage());

        }

    }

    // tampil absensi
    public void tampilAbsensi(JTable tabel) {

        DefaultTableModel model = new DefaultTableModel();

        model.addColumn("ID");
        model.addColumn("Nama");
        model.addColumn("NIM");
        model.addColumn("Tanggal");
        model.addColumn("Status");

        try {

            String query = "SELECT a.id, u.username, m.nim, a.tanggal, a.status "
        + "FROM absensi a "
        + "JOIN mahasiswa m ON a.mahasiswa_id = m.id "
        + "JOIN logingbq u ON u.user_id = m.user_id";

            PreparedStatement pst = con.prepareStatement(query);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                model.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("username"),
                    rs.getString("nim"),
                    rs.getDate("tanggal"),
                    rs.getString("status")
                });

            }

            tabel.setModel(model);

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e.getMessage());

        }

    }

    // tambah absensi
    public void tambahAbsensi(
             int mahasiswaId,
        String tanggal,
        String status
    ) {

        try {

           String query = "INSERT INTO absensi(mahasiswa_id,tanggal,status)"
                    + "VALUES(?,?,?)";

            PreparedStatement pst = con.prepareStatement(query);

            pst.setInt(1, mahasiswaId);
            pst.setString(2, tanggal);
            pst.setString(3, status);

            pst.executeUpdate();

            JOptionPane.showMessageDialog(null, "Absensi berhasil ditambah");

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e.getMessage());

        }

    }

    // hapus absensi
    public void hapusAbsensi(int absensiId) {

        try {

           String query = "DELETE FROM absensi WHERE id=?";

            PreparedStatement pst = con.prepareStatement(query);

            pst.setInt(1, absensiId);

            pst.executeUpdate();

            JOptionPane.showMessageDialog(null, "Absensi berhasil dihapus");

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e.getMessage());

        }

    }
    
    // edit absensi
public void editAbsensi(
        int absensiId,
        String tanggal,
        String status
) {

    try {

        String query = "UPDATE absensi "
                + "SET tanggal=?, status=? "
                + "WHERE id=?";

        PreparedStatement pst = con.prepareStatement(query);

        pst.setString(1, tanggal);
        pst.setString(2, status);
        pst.setInt(3, absensiId);

        pst.executeUpdate();

        JOptionPane.showMessageDialog(null, "Absensi berhasil diupdate");

    } catch (Exception e) {

        JOptionPane.showMessageDialog(null, e.getMessage());

    }

}

}