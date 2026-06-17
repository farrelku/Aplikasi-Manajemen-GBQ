package model;

public class GuruModel {

    private int id;
    private String nama;
    private String nim;
    private String tanggal;
    private String status;

    public GuruModel() {
    }

    public GuruModel(
            int id,
            String nama,
            String nim,
            String tanggal,
            String status
    ) {
        this.id = id;
        this.nama = nama;
        this.nim = nim;
        this.tanggal = tanggal;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public String getNim() {
        return nim;
    }

    public String getTanggal() {
        return tanggal;
    }

    public String getStatus() {
        return status;
    }
}