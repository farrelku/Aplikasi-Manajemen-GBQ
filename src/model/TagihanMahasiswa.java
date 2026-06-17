package model;

public class TagihanMahasiswa {

    private String nama;
    private String nim;
    private String bulan;
    private double jumlah;
    private String status;

    public TagihanMahasiswa(
            String nama,
            String nim,
            String bulan,
             double jumlah,
            String status
    ) {

        this.nama = nama;
        this.nim = nim;
        this.bulan = bulan;
        this.jumlah = jumlah;
        this.status = status;
    }
    
    public double getJumlah() {
    return jumlah;
}

    public String getNama() {
        return nama;
    }

    public String getNim() {
        return nim;
    }

    public String getBulan() {
        return bulan;
    }

    public String getStatus() {
        return status;
    }
}