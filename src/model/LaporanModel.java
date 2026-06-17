package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import koneksi.koneksi;

public class LaporanModel {

    Connection con =
    koneksi.getConnection();

    PreparedStatement pst;

    /*
     =========================
     FILTER DATA LAPORAN
     =========================
    */
    public ResultSet getDataLaporan(
        String bulan
    ) {

        ResultSet rs = null;

        try {

            String sql;

            if(bulan.equals("Semua")) {

                sql =
                "SELECT * FROM pembayaran";

                pst =
                con.prepareStatement(sql);

            } else {

                sql =
                "SELECT * FROM pembayaran "
                + "WHERE bulan = ?";

                pst =
                con.prepareStatement(sql);

                pst.setString(1, bulan);

            }

            rs = pst.executeQuery();

        } catch(Exception e) {

            e.printStackTrace();

        }

        return rs;
    }

    /*
     =========================
     LAPORAN PEMBAYARAN PDF
     =========================
    */
    public ResultSet getLaporanPembayaran(
        int bulan,
        int tahun
    ) {

           ResultSet rs = null;

    try {

        String sql =
        "SELECT * FROM pembayaran";

        pst =
        con.prepareStatement(sql);

        rs = pst.executeQuery();

    } catch(Exception e) {

        e.printStackTrace();

    }

    return rs;
    }
    /*
     =========================
     LAPORAN ABSENSI PDF
     =========================
    */
    public ResultSet getLaporanAbsensi(
        int bulan,
        int tahun
    ) {

        ResultSet rs = null;

        try {

            String sql =
            "SELECT m.nama, m.nim, a.tanggal, a.status "
+ "FROM absensi a "
+ "LEFT JOIN mahasiswa m "
+ "ON a.mahasiswa_id = m.id";

            pst =
            con.prepareStatement(sql);

       

            rs =
            pst.executeQuery();

        } catch(Exception e) {

            e.printStackTrace();

        }

        return rs;
    }
}