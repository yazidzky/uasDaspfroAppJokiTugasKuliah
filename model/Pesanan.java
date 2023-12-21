package model;

public class Pesanan {
    private String jenisTugas;
    private int jumlah;
    private boolean sudahDibayar;
    private String status;

    public Pesanan(String jenisTugas, int jumlah) {
        this.jenisTugas = jenisTugas;
        this.jumlah = jumlah;
        this.sudahDibayar = false;
        this.status = "Menunggu Pembayaran";
    }

    public String getJenisTugas() {
        return jenisTugas;
    }

    public int getJumlah() {
        return jumlah;
    }

    public boolean isSudahDibayar() {
        return sudahDibayar;
    }

    public void setSudahDibayar(boolean sudahDibayar) {
        this.sudahDibayar = sudahDibayar;
        if (sudahDibayar) {
            this.status = "Sudah Dibayar";
        } else {
            this.status = "Menunggu Pembayaran";
        }
    }

    public String getStatus() {
        return status;
    }
}