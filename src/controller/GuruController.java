package controller;

import koneksi.koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class GuruController {

    Connection con = koneksi.getConnection();

    public void tampilMahasiswa(JTable tabel){

        DefaultTableModel tbl =
        new DefaultTableModel();

        tbl.addColumn("ID");
        tbl.addColumn("Nama");
        tbl.addColumn("NIM");

        try{

            String sql =
            "SELECT * FROM mahasiswa";

            PreparedStatement pst =
            con.prepareStatement(sql);

            ResultSet rs =
            pst.executeQuery();

            while(rs.next()){

                tbl.addRow(new Object[]{

                    rs.getInt("id"),
                    rs.getString("nama"),
                    rs.getString("nim")

                });
            }

            tabel.setModel(tbl);

        }catch(Exception e){

            e.printStackTrace();
        }
    }

    public void tampilAbsensi(JTable tabel){

        DefaultTableModel tbl =
        new DefaultTableModel();

        tbl.addColumn("ID");
        tbl.addColumn("Nama");
        tbl.addColumn("NIM");
        tbl.addColumn("Tanggal");
        tbl.addColumn("Status");

        try{

            String sql =
            "SELECT absensi.id,"
            + "mahasiswa.nama,"
            + "mahasiswa.nim,"
            + "absensi.tanggal,"
            + "absensi.status "
            + "FROM absensi "
            + "JOIN mahasiswa "
            + "ON absensi.mahasiswa_id = mahasiswa.id";

            PreparedStatement pst =
            con.prepareStatement(sql);

            ResultSet rs =
            pst.executeQuery();

            while(rs.next()){

                tbl.addRow(new Object[]{

                    rs.getInt("id"),
                    rs.getString("nama"),
                    rs.getString("nim"),
                    rs.getString("tanggal"),
                    rs.getString("status")

                });
            }

            tabel.setModel(tbl);

        }catch(Exception e){

            e.printStackTrace();
        }
    }

    public void tambahAbsensi(
            int mahasiswaId,
            String tanggal,
            String status
    ){

        try{

            String sql =
            "INSERT INTO absensi "
            + "(mahasiswa_id,tanggal,status)"
            + "VALUES(?,?,?)";

            PreparedStatement pst =
            con.prepareStatement(sql);

            pst.setInt(1, mahasiswaId);
            pst.setString(2, tanggal);
            pst.setString(3, status);

            pst.executeUpdate();

        }catch(Exception e){

            e.printStackTrace();
        }
    }

    public void editAbsensi(
            int id,
            String tanggal,
            String status
    ){

        try{

            String sql =
            "UPDATE absensi "
            + "SET tanggal=?,status=? "
            + "WHERE id=?";

            PreparedStatement pst =
            con.prepareStatement(sql);

            pst.setString(1, tanggal);
            pst.setString(2, status);
            pst.setInt(3, id);

            pst.executeUpdate();

        }catch(Exception e){

            e.printStackTrace();
        }
    }

    public void hapusAbsensi(int id){

        try{

            String sql =
            "DELETE FROM absensi "
            + "WHERE id=?";

            PreparedStatement pst =
            con.prepareStatement(sql);

            pst.setInt(1, id);

            pst.executeUpdate();

        }catch(Exception e){

            e.printStackTrace();
        }
    }

    public void cariMahasiswa(
            JTable tabel,
            String keyword
    ){

        DefaultTableModel tbl =
        new DefaultTableModel();

        tbl.addColumn("ID");
        tbl.addColumn("Nama");
        tbl.addColumn("NIM");

        try{

            String sql =
            "SELECT * FROM mahasiswa "
            + "WHERE nama LIKE ? "
            + "OR nim LIKE ?";

            PreparedStatement pst =
            con.prepareStatement(sql);

            pst.setString(1,"%"+keyword+"%");
            pst.setString(2,"%"+keyword+"%");

            ResultSet rs =
            pst.executeQuery();

            while(rs.next()){

                tbl.addRow(new Object[]{

                    rs.getInt("id"),
                    rs.getString("nama"),
                    rs.getString("nim")

                });
            }

            tabel.setModel(tbl);

        }catch(Exception e){

            e.printStackTrace();
        }
    }

    public void cariAbsensi(
        JTable tabel,
        String kategori,
        String keyword
){

    DefaultTableModel tbl =
    new DefaultTableModel();

    tbl.addColumn("ID");
    tbl.addColumn("Nama");
    tbl.addColumn("NIM");
    tbl.addColumn("Tanggal");
    tbl.addColumn("Status");

    try{

        String kolom = "";

        if(kategori.equals("Nama")){
            kolom = "mahasiswa.nama";
        }else{
            kolom = "mahasiswa.nim";
        }

        String sql =
        "SELECT absensi.id, "
        + "mahasiswa.nama, "
        + "mahasiswa.nim, "
        + "absensi.tanggal, "
        + "absensi.status "
        + "FROM absensi "
        + "JOIN mahasiswa "
        + "ON absensi.mahasiswa_id = mahasiswa.id "
        + "WHERE " + kolom + " LIKE ?";

        PreparedStatement pst =
        con.prepareStatement(sql);

        pst.setString(
            1,
            "%" + keyword + "%"
        );

        ResultSet rs =
        pst.executeQuery();

        while(rs.next()){

            tbl.addRow(new Object[]{

                rs.getInt("id"),
                rs.getString("nama"),
                rs.getString("nim"),
                rs.getString("tanggal"),
                rs.getString("status")

            });
        }

        tabel.setModel(tbl);

    }catch(Exception e){

        e.printStackTrace();

    }
}}