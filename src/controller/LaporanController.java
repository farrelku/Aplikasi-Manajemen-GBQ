package controller;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javax.swing.JTable;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import java.io.FileOutputStream;
import java.sql.ResultSet;

import model.LaporanModel;

public class LaporanController {

    LaporanModel model = new LaporanModel();

    /*
     =========================
     FILTER TABEL LAPORAN
     =========================
    */
    public void filterLaporan(
        JTable tabel,
        String bulan
    ) {

        DefaultTableModel tbl =
        new DefaultTableModel();

        tbl.addColumn("ID");
        tbl.addColumn("Nama");
        tbl.addColumn("NIM");
        tbl.addColumn("Bulan");
        tbl.addColumn("Jumlah");
        tbl.addColumn("Status");

        try {

            ResultSet rs =
            model.getDataLaporan(bulan);

            if(rs == null){

                JOptionPane.showMessageDialog(
                    null,
                    "Data laporan NULL"
                );

                return;
            }

            while(rs.next()) {

                tbl.addRow(new Object[]{

                    rs.getString("id"),
                    rs.getString("nama"),
                    rs.getString("nim"),
                    rs.getString("bulan"),
                    rs.getString("jumlah"),
                    rs.getString("status")

                });

            }

            tabel.setModel(tbl);

        } catch (Exception e) {

            e.printStackTrace();

            JOptionPane.showMessageDialog(
                null,
                e.getMessage()
            );

        }

    }

    /*
     =========================
     GENERATE PDF
     =========================
    */
    public void generatePDF(int bulan, int tahun) {

        try {

            Document document =
            new Document();

            PdfWriter.getInstance(
                document,
                new FileOutputStream(
                    "Laporan_" +
                    bulan + "_" +
                    tahun + ".pdf"
                )
            );

            document.open();

            document.add(
                new Paragraph(
                    "LAPORAN BULAN "
                    + bulan +
                    " TAHUN " +
                    tahun
                )
            );

            document.add(
                new Paragraph(" ")
            );

            /*
             ======================
             DATA PEMBAYARAN
             ======================
            */

            document.add(
                new Paragraph(
                    "DATA PEMBAYARAN"
                )
            );

            PdfPTable tabelBayar =
            new PdfPTable(6);

            tabelBayar.addCell("Nama");
            tabelBayar.addCell("NIM");
            tabelBayar.addCell("Bulan");
            tabelBayar.addCell("Jumlah");
            tabelBayar.addCell("Metode");
            tabelBayar.addCell("Status");

            ResultSet rsBayar =
            model.getLaporanPembayaran(
                bulan,
                tahun
            );

            if(rsBayar == null){

                JOptionPane.showMessageDialog(
                    null,
                    "ResultSet pembayaran NULL"
                );

                return;
            }

            while(rsBayar.next()) {

                tabelBayar.addCell(
                    rsBayar.getString("nama")
                );

                tabelBayar.addCell(
                    rsBayar.getString("nim")
                );

                tabelBayar.addCell(
                  rsBayar.getString("bulan")
                );

                tabelBayar.addCell(
                    rsBayar.getString("jumlah")
                );

                tabelBayar.addCell(
                    rsBayar.getString("metode")
                );

                tabelBayar.addCell(
                    rsBayar.getString("status")
                );
            }

            document.add(
                tabelBayar
            );

            /*
             ======================
             DATA ABSENSI
             ======================
            */

            document.add(
                new Paragraph(" ")
            );

            document.add(
                new Paragraph(
                    "DATA ABSENSI"
                )
            );

            PdfPTable tabelAbsen =
            new PdfPTable(4);

            tabelAbsen.addCell("Nama");
            tabelAbsen.addCell("NIM");
            tabelAbsen.addCell("Tanggal");
            tabelAbsen.addCell("Status");

            ResultSet rsAbsen =
            model.getLaporanAbsensi(
                bulan,
                tahun
            );

            if(rsAbsen == null){

                JOptionPane.showMessageDialog(
                    null,
                    "ResultSet absensi NULL"
                );

                return;
            }

            while(rsAbsen.next()) {

                tabelAbsen.addCell(
                    rsAbsen.getString("nama")
                );

                tabelAbsen.addCell(
                    rsAbsen.getString("nim")
                );

                tabelAbsen.addCell(
                    rsAbsen.getString("tanggal")
                );

                tabelAbsen.addCell(
                    rsAbsen.getString("status")
                );
            }

            document.add(
                tabelAbsen
            );

            document.close();

            JOptionPane.showMessageDialog(
                null,
                "PDF berhasil dibuat"
            );

        } catch (Exception e) {

            e.printStackTrace();

            JOptionPane.showMessageDialog(
                null,
                e.getMessage()
            );

        }
    }
}