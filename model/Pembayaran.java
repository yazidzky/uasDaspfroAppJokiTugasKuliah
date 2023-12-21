package model;

public class Pembayaran {
    private int total;
    private int uangBayar;
    private int kembalian;

    public Pembayaran(int total, int uangBayar, int kembalian) {
        this.total = total;
        this.uangBayar = uangBayar;
        this.kembalian = kembalian;
    }

    public int getTotalPembayaran() {
        return total;
    }

    public int getUangBayar() {
        return uangBayar;
    }

    public int getKembalian() {
        return kembalian;
    }

    public void addUangTambahan(int uangTambahan) {
        this.uangBayar += uangTambahan;
        this.kembalian = this.uangBayar - this.total;
    }
}
