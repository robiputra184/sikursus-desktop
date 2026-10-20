package latihan; // package

public class LatihanBidangDatar { // class
    public static void main(String[] args){ // main method
        // --- 1. PERSEGI PANJANG ---
        String nama = "Persegi Panjang"; // variabel
        double panjang = 10;
        double lebar = 5;
        double luas = panjang * lebar;
        double keliling = 2 * (panjang + lebar);

        // output
        System.out.println("Nama       : " + nama);
        System.out.println("Panjang    : " + panjang);
        System.out.println("Lebar      : " + lebar);
        System.out.println("Luas       : " + luas);
        System.out.println("Keliling   : " + keliling);

         // --- 2. LINGKARAN ---
        String namaLingkaran = "Lingkaran";
        double r = 7; // jari-jari lingkaran
        double phi = 3.14;
        
        double luasLingkaran = phi * r * r;
        double kelilingLingkaran = 2 * phi * r;
        
        // output Lingkaran
        System.out.println("Nama : " + namaLingkaran);
        System.out.println("Jari-jari : " + r);
        System.out.println("Luas : " + luasLingkaran);
        System.out.println("Keliling : " + kelilingLingkaran);
    }
}
