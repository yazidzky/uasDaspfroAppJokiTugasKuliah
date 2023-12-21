package controller;

import model.Pesanan;
import model.Pembayaran;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class JasaJokiMenu {
    private static List<Pesanan> daftarPesanan = new ArrayList<>();
    private static Pembayaran pembayaran;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        showMenu(scanner);
    }

    private static void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            System.out.println("Error clearing screen: " + e.getMessage());
        }
    }

    public static void showMenu(Scanner scanner) {
        List<String> jenisTugas = new ArrayList<>();
        List<Integer> hargaJasa = new ArrayList<>();
        List<Integer> jumlahPesan = new ArrayList<>();

        jenisTugas.add("Makalah  ");
        hargaJasa.add(150000);

        jenisTugas.add("Soal/lembar");
        hargaJasa.add(15000);

        jenisTugas.add("Skripsi");
        hargaJasa.add(1900000);

        int menu;

        do {
            clearScreen();
            System.out.println("+-------------------------------+");
            System.out.println("|  Layanan Jasa Joki Tugas     |");
            System.out.println("+-------------------------------+");
            System.out.println("| 1. Pesan Joki Tugas          |");
            System.out.println("| 2. Bayar                     |");
            System.out.println("| 3. Lihat Pesanan             |");
            System.out.println("| 0. Keluar                    |");
            System.out.println("+-------------------------------+");
            System.out.print("Pilihan Anda: ");
            menu = scanner.nextInt();

            switch (menu) {
                case 1:
                    pesanJoki(jenisTugas, hargaJasa, jumlahPesan, scanner);
                    break;
                case 2:
                    bayarUntukJoki(jenisTugas, hargaJasa, jumlahPesan, scanner);
                    
                    break;
                case 3:
                    lihatPesanan(jenisTugas, jumlahPesan);
                    break;
                case 0:
                    System.out.println("Terima kasih telah menggunakan Layanan Jasa Joki Tugas Kuliah kami.");
                    break;
                default:
                    System.out.println("Pilihan Tidak Valid.");
                    break;
            }
        } while (menu != 0);
    }

    public static void pesanJoki(List<String> jenisTugas, List<Integer> hargaJasa, List<Integer> jumlahPesan, Scanner scanner) {
        clearScreen();
        System.out.println("+-------------------------------+");
        System.out.println("|       Daftar Jenis Tugas      |");
        System.out.println("+-------------------------------+");
        for (int i = 0; i < jenisTugas.size(); i++) {
            System.out.println("| " + (i + 1) + ". " + jenisTugas.get(i) + " - Rp" + hargaJasa.get(i));
        }
        System.out.println("+-------------------------------+");

        System.out.print("Masukkan pilihan jenis tugas: ");
        int pilihanJenisTugas = scanner.nextInt();

        System.out.print("Masukkan jumlah tugas yang dipesan: ");
        int jumlahTugas = scanner.nextInt();

        if (pilihanJenisTugas >= 1 && pilihanJenisTugas <= jenisTugas.size()) {
            System.out.println("+-------------------------------+");
            System.out.println("| Anda telah memilih:           |");
            System.out.println("| " + jumlahTugas + " joki untuk " + jenisTugas.get(pilihanJenisTugas - 1)
                    + " - Rp" + hargaJasa.get(pilihanJenisTugas - 1) * jumlahTugas);
            System.out.println("+-------------------------------+");

            while (jumlahPesan.size() < jenisTugas.size()) {
                jumlahPesan.add(0);
            }

            jumlahPesan.set(pilihanJenisTugas - 1, jumlahPesan.get(pilihanJenisTugas - 1) + jumlahTugas);

            Pesanan pesanan = new Pesanan(jenisTugas.get(pilihanJenisTugas - 1), jumlahTugas);
            daftarPesanan.add(pesanan);

        } else {
            System.out.println("Pilihan Tidak Valid");
        }
    }

    public static void bayarUntukJoki(List<String> jenisTugas, List<Integer> hargaJasa, List<Integer> jumlahPesan, Scanner scanner) {
        clearScreen();
        System.out.println("+-------------------------------+");
        System.out.println("|        Daftar Pesanan         |");
        System.out.println("+-------------------------------+");

        int totalPembayaran = 0;
        for (int i = 0; i < jenisTugas.size(); i++) {
            if (jumlahPesan.get(i) > 0) {
                int hargaPembayaran = hargaJasa.get(i) * jumlahPesan.get(i);
                totalPembayaran += hargaPembayaran;
                System.out.println("| " + jenisTugas.get(i) + " (x" + jumlahPesan.get(i) + "): Rp" + hargaPembayaran);
            }
        }

        if (totalPembayaran > 0) {
            System.out.println("+-------------------------------+");
            System.out.println("| Total Pembayaran: Rp" + totalPembayaran);
            System.out.print("| Masukkan jumlah uang: ");
            int uangBayar = scanner.nextInt();

            pembayaran = new Pembayaran(totalPembayaran, uangBayar, uangBayar - totalPembayaran);

            prosesPembayaran(jenisTugas, hargaJasa, jumlahPesan, scanner);
        } else {
            System.out.println("Tidak ada pesanan yang telah diberikan.");
        }
    }

    private static void prosesPembayaran(List<String> jenisTugas, List<Integer> hargaJasa, List<Integer> jumlahPesan, Scanner scanner) {
        if (pembayaran != null) {
            int totalPembayaran = pembayaran.getTotalPembayaran();
            int uangBayar = pembayaran.getUangBayar();

            if (uangBayar >= totalPembayaran) {
                int kembalian = pembayaran.getKembalian();
                System.out.println("+-------------------------------+");
                System.out.println("|       Struk Pembayaran        |");
                System.out.println("+-------------------------------+");
                for (int i = 0; i < jenisTugas.size(); i++) {
                    if (jumlahPesan.get(i) > 0) {
                        System.out.println("| " + jenisTugas.get(i) + " (x" + jumlahPesan.get(i) + "): Rp" + hargaJasa.get(i) * jumlahPesan.get(i));
                    }
                }
                System.out.println("+-------------------------------+");
                System.out.println("| Total Pembayaran: Rp" + totalPembayaran);
                System.out.println("| Uang Bayar: Rp" + uangBayar);
                System.out.println("| Kembalian: Rp" + kembalian);
                System.out.println("+-------------------------------+");
                System.out.println("Terima kasih atas pembayaran Anda.");

                prosesPesanan(jenisTugas, jumlahPesan);

                for (Pesanan pesanan : daftarPesanan) {
                    pesanan.setSudahDibayar(true);  // Mengubah status pesanan menjadi sudah dibayar
                }

                System.out.print("Apakah Anda ingin memesan lagi? (1. Ya / 2. Tidak): ");
                int pesanLagi = scanner.nextInt();
                if (pesanLagi == 1) {
                    pembayaran = null;
                } else {
                    return;
                }
            } else {
                int sisaPembayaran = totalPembayaran - uangBayar;
                System.out.println("| Maaf, pembayaran Anda tidak mencukupi. Sisa pembayaran Anda adalah Rp" + sisaPembayaran + ".");

                System.out.print("| Apakah Anda ingin melanjutkan pembayaran? (1. Ya / 2. Tidak): ");
                int lanjutPembayaran = scanner.nextInt();

                if (lanjutPembayaran == 1) {
                    System.out.print("| Masukkan jumlah uang tambahan: ");
                    int uangTambahan = scanner.nextInt();
                    pembayaran.addUangTambahan(uangTambahan);

                    if (uangTambahan > 0) {
                        System.out.println("| Uang Tambahan: Rp" + uangTambahan);
                    }

                    prosesPembayaran(jenisTugas, hargaJasa, jumlahPesan, scanner);
                } else {
                    System.out.println("+-------------------------------+");
                    System.out.println("| Proses pembayaran gagal.       |");
                    System.out.println("+-------------------------------+");
                }
            }
        } else {
            System.out.println("Pembayaran belum diinisiasi.");
        }
    }

    private static void prosesPesanan(List<String> jenisTugas, List<Integer> jumlahPesan) {
        System.out.println("+-------------------------------+");
        System.out.println("|    Pesanan Sedang di Proses   |");
        System.out.println("+-------------------------------+");

        for (int i = 0; i < jenisTugas.size(); i++) {
            if (jumlahPesan.get(i) > 0) {
                String jenis = jenisTugas.get(i);
                int jumlah = jumlahPesan.get(i);

                int delay = getProcessingTime(jenis) * jumlah;
                System.out.println("| Memproses " + jumlah + " pesanan " + jenis + ". Waktu yang diperlukan: " + delay + " detik.");
                try {
                    Thread.sleep(delay * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("| Pesanan " + jenis + " selesai diproses.");
            }
        }

        System.out.println("+-------------------------------+");
        System.out.println("|   Semua Pesanan Selesai!     |");
        System.out.println("+-------------------------------+");
    }

    private static int getProcessingTime(String jenis) {
        switch (jenis.trim().toLowerCase()) {
            case "makalah":
                return 30;
            case "soal/lembar":
                return 20;
            case "skripsi":
                return 60;
            default:
                return 0;
        }
    }

    private static void lihatPesanan(List<String> jenisTugas, List<Integer> jumlahPesan) {
    clearScreen();
    System.out.println("+-------------------------------------+");
    System.out.println("|            Daftar Pesanan            |");
    System.out.println("+-------------------------------------+");

    if (daftarPesanan.isEmpty()) {
        System.out.println("Belum ada pesanan yang diberikan.");
    } else {
        for (Pesanan pesanan : daftarPesanan) {
            System.out.println("| " + pesanan.getJenisTugas() + " (x" + pesanan.getJumlah() + ")");
            if (pesanan.isSudahDibayar()) {
                System.out.println("|   Status: Sudah Dibayar");
            } else {
                System.out.println("|   Status: Belum Dibayar");
            }
            if (pesanan.isSudahDibayar()) {
                System.out.println("|   Estimasi waktu: " + getProcessingTime(pesanan.getJenisTugas()) * pesanan.getJumlah() + " detik");
            } else {
                System.out.println("|   Status Pemesanan: " + pesanan.getStatus());
            }
            System.out.println("+-------------------------------------+");
        }
    }

    System.out.println("Tekan Enter untuk kembali ke menu utama.");
    new Scanner(System.in).nextLine();
}
}