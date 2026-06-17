package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import koneksi.koneksi;

public class MahasiswaAdminController {

    public void updateMahasiswa(
            int userId,
            String nama,
            String email,
            String nim
    ) {

        try {

            Connection con = koneksi.getConnection();

            // update login
            String query1 =
            "UPDATE logingbq SET username=?, email=? WHERE user_id=?";

            PreparedStatement pst1 =
            con.prepareStatement(query1);

            pst1.setString(1, nama);
            pst1.setString(2, email);
            pst1.setInt(3, userId);

            pst1.executeUpdate();

            // update mahasiswa
            String query2 =
            "UPDATE mahasiswa SET nim=? WHERE user_id=?";

            PreparedStatement pst2 =
            con.prepareStatement(query2);

            pst2.setString(1, nim);
            pst2.setInt(2, userId);

            pst2.executeUpdate();

        } catch(Exception e) {

            System.out.println(e.getMessage());

        }

    }

}