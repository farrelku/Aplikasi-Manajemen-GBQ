package controller;

import koneksi.koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.TagihanMahasiswa;

public class TagihanMahasiswaController {

    Connection con = koneksi.getConnection();

    public ArrayList<TagihanMahasiswa> getData(
            String nim,
            String statusFilter
    ) {

        ArrayList<TagihanMahasiswa> list =
                new ArrayList<>();

        try {

            String sql =
                    "SELECT * FROM tagihan WHERE nim=?";

            if (!statusFilter.equals("Semua")) {

                sql += " AND status=?";
            }

            PreparedStatement pst =
                    con.prepareStatement(sql);

            pst.setString(1, nim);

            if (!statusFilter.equals("Semua")) {

                pst.setString(2, statusFilter);
            }

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                list.add(
                        new TagihanMahasiswa(
                                rs.getString("nama"),
                                rs.getString("nim"),
                                rs.getString("bulan"),
                                rs.getDouble("jumlah"),
                                rs.getString("status")
                          
                        )
                );
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return list;
    }

 public void uploadBukti(
        String nim,
        String bulan,
        String pathFile
) {

    try {

        String cari =
"SELECT * FROM tagihan WHERE nim=? AND bulan=?";

PreparedStatement pstCari =
con.prepareStatement(cari);

pstCari.setString(1, nim);
pstCari.setString(2, bulan);

ResultSet rs =
pstCari.executeQuery();

if(rs.next()) {

    System.out.println("DATA DITEMUKAN");

    String nama = rs.getString("nama");
    double jumlah = rs.getDouble("jumlah");

    String sql =
    "INSERT INTO pembayaran "
    + "(nama,nim,bulan,jumlah,metode,status,bukti_transfer) "
    + "VALUES(?,?,?,?,?,?,?)";

    PreparedStatement pst =
    con.prepareStatement(sql);

    pst.setString(1, nama);
    pst.setString(2, nim);
    pst.setString(3, bulan);
    pst.setDouble(4, jumlah);
    pst.setString(5, "Transfer");
    pst.setString(6, "Menunggu");
    pst.setString(7, pathFile);

    int hasil = pst.executeUpdate();

    System.out.println("INSERT = " + hasil);

} else {

    System.out.println("DATA TIDAK DITEMUKAN");
}{
        }

    } catch(Exception e) {

        e.printStackTrace();

    }
}
}