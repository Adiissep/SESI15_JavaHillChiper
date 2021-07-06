
package java_hillchiper;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
/**
 *
 * @author ADVANG4C
 */
public class Enkripsi_hillchiper {
    static String[] abjad = {"A", "B", "C", "D", "E", "F", "G", "H",
                             "I", "J", "K", "L", "M", "N", "O", "P", "Q",
                             "R", "S", "T", "U", "V", "W", "X", "Y", "Z", 
                             "a", "b", "c", "d", "e", "f", "g", "h",
                             "i", "j", "k", "l", "m", "n", "o", "p", "q",
                             "r", "s", "t", "u", "v", "w", "x", "y", "z",
                             "`", "~", "!", "@", "#", "$", "%", "^", 
                             "&", "*", "(", ")", "_", "-", "+", "=", 
                             "{", "}", "[", "]", "|", "\\", ":", ";", 
                             "’", ",", ".", "?", "/", " ", ">", "<", "\n"};
    static int[] angka = {0, 1, 2, 3, 4,
                          5, 6, 7, 8, 9,
                          10, 11, 12, 13, 14,
                          15, 16, 17, 18, 19,
                          20, 21, 22, 23, 24, 25, 
                          26, 27, 28, 29, 30, 31,
                          32, 33, 34, 35, 36, 37,
                          38, 39, 40, 41, 42, 43, 44,
                          45, 46, 47, 48, 49, 50, 51, 
                          52, 53, 54, 55, 56, 57, 58, 
                          59, 60, 61, 62, 63, 64, 65, 
                          66, 67, 68, 69, 70, 71, 72, 
                          73, 74, 75, 76, 77, 78, 79, 
                          80, 81, 82, 83, 84, 85};
    static int modulo = 85;
    static String[]teks2karakter;
    static String hasilKonversi[][];
    static String hasilHitungKunci[][];
    static String totalHasilEnkrip= "";
    
    public int hitungJumlahHuruf(String txt){
        int jumlahhuruf = txt.length();
        System.out.println("Jumlah Plaintext : "+jumlahhuruf);
        
        return  jumlahhuruf;
    }
    public String hitungEnkripsi(String txt, int [][] kunci){
        System.out.println("Plaintext : '" + txt + "'");
        hitungJumlahHuruf(txt);
        pisahkanTeks(txt);
        AbjadKeAngka(teks2karakter);
        perhitunganKunci(hasilKonversi, kunci);
        AngkaKeAbjad(hasilHitungKunci);
        
        
        return totalHasilEnkrip;
    }
    static String pisahkanTeks(String text) {
        //System.out.println(">>> PEMISAH TIAP 2 HURUF <<<");
        String teksnya = text;
        if (teksnya.length() % 2 == 0) {
            teksnya = text;
        } else {
            teksnya = text + ".";
        }
        assert teksnya.length() % 2 == 0;
        teks2karakter = new String[teksnya.length() / 2];
        for (int index = 0; index < teks2karakter.length; index++) {
            teks2karakter[index] = teksnya.substring(index * 2, index * 2 + 2);
            //System.out.println(teks2karakter[index]);
        }
        return teksnya;
    }

    static String[][] AbjadKeAngka(String[] text) {
        hasilKonversi = new String[text.length][2];
        //System.out.println(">>> TRANSFORMASI HURUF KE ANGKA <<<");
        for (int i = 0; i < text.length; i++) {
            String char1 = text[i].substring(0, 1);
            String char2 = text[i].substring(1);

            for (int j = 0; j < abjad.length; j++) {
                if (char1.equals(abjad[j])) {
                    char1 = String.valueOf(angka[j]);
                }
                if (char2.equals(abjad[j])) {
                    char2 = String.valueOf(angka[j]);
                }
            }

            if (hasilKonversi[i][0] == null) {
                hasilKonversi[i][0] = char1;

                if (hasilKonversi[i][1] == null) {
                    hasilKonversi[i][1] = char2;

                }
            }
        }

        for (int n = 0; n < hasilKonversi.length; n++) {
            for (int p = 0; p < hasilKonversi[0].length; p++) {
                //System.out.print(hasilKonversi[n][p] + " ");
            }
            //System.out.println("");
        }

        return hasilKonversi;
    }

    static String[][] perhitunganKunci(String[][] angka, int[][] kunci) {
        int kunciK0B0 = kunci[0][0];
        int kunciK0B1 = kunci[0][1];
        int kunciK1B0 = kunci[1][0];
        int kunciK1B1 = kunci[1][1];
        hasilHitungKunci = new String[angka.length][2];
        //System.out.println(">>> HASIL PERKALIAN KUNCI <<<");
        for (int n = 0; n < angka.length; n++) {
            int konvert = Integer.parseInt(angka[n][0]);
            int konvert1 = Integer.parseInt(angka[n][1]);
            int hasil = (kunciK0B0 * konvert) + (kunciK0B1 * konvert1);
            int hasil1 = (kunciK1B0 * konvert) + (kunciK1B1 * konvert1);
            
            //System.out.println(hasil + " " + hasil1);
            
            hasil = hasil % modulo;
            hasil1 = hasil1 % modulo;
            
            
            //   System.out.println(hasil + " " + hasil1);

            if (hasilHitungKunci[n][0] == null) {
                hasilHitungKunci[n][0] = String.valueOf(hasil);
                if (hasilHitungKunci[n][1] == null) {
                    hasilHitungKunci[n][1] = String.valueOf(hasil1);
                }
            }
        }

        //System.out.println(">>> HASIL MODULO 84 <<<");
        
        for (int i = 0; i < hasilHitungKunci.length; i++) {
            for (int j = 0; j < hasilHitungKunci[0].length; j++) {
                //System.out.print(hasilHitungKunci[i][j] + " ");
            }
            //System.out.println("");
        }
        return hasilHitungKunci;
    }

    static String AngkaKeAbjad(String[][] hasilHitungKunci) {

        String hasilEnkripsi = "";
        if (choice==1){
           System.out.println(">>> HASIL ENKRIPSI <<<");
        }else {
           System.out.println(">>> HASIL DESKRIPSI <<<");
        }
        totalHasilEnkrip = "";
        for (int i = 0; i < hasilHitungKunci.length; i++) {
            for (int j = 0; j < hasilHitungKunci[0].length; j++) {
                // System.out.print(hasilHitungKunci[i][j]+" ");
                for (int k = 0; k < angka.length; k++) {
                    if (hasilHitungKunci[i][j].equals(String.valueOf(angka[k]))) {
                        hasilEnkripsi = abjad[k];
                        totalHasilEnkrip = totalHasilEnkrip + hasilEnkripsi;
                    }
                }
            }
        }
        System.out.println(totalHasilEnkrip);
        return totalHasilEnkrip;
    }
    static int choice;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);
        Enkripsi_hillchiper hill=new Enkripsi_hillchiper();
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Menu:\n1) Enkripsi\n2) Deskripsi");
        System.out.println("Pilihan : ");choice = Integer.parseInt(in.readLine());
        if(choice==1){
        System.out.println("\nKUNCI MATRIK ORDO 2X2");
        System.out.println("Isikan Nilai Matrik A :");
        int m1 = scan.nextInt();
        System.out.println("Isikan Nilai Matrik B :");
        int m2 = scan.nextInt();
        System.out.println("Isikan Nilai Matrik C :");
        int m3 = scan.nextInt();
        System.out.println("Isikan Nilai Matrik D :");
        int m4 = scan.nextInt();
        if ((m1*m2)-(m3*m4)==1) {
            System.out.println("Matriks telah memenuhi syarat enkripsi HillChiper\n");
            int[][] kunci = {{m1, m3}, {m4, m2}};
            System.out.printf("Masukan Path/Directory file :");
            String path = scan.next();
            try {
            File f0 = new File(path);
            Scanner reader=new Scanner(f0);
            while(reader.hasNextLine()){
                String data = reader.nextLine();
                //proses enkripsi;
                hill.hitungEnkripsi(data, kunci);
            }
            reader.close();
            }catch(FileNotFoundException e){
            System.out.println("Kesalahan Akses File!");
            e.printStackTrace();
            }
            //simpan di enkripsi
             FileWriter writer=new FileWriter("C:\\Users\\ADVANG4C\\Documents\\NetBeansProjects\\Java_HillChiper\\src\\java_hillchiper\\enkripsi.txt");
             try{
             writer.write(hill.totalHasilEnkrip);
             }catch(IOException e){
             e.printStackTrace();
             }
             finally{
             writer.flush();
             writer.close();
             System.out.println("Data disimpan coba check file tersebut!\n");
             main(args);
             }
        } else {
            System.out.println("Tidak memenuhi syarat, Coba lagi dengan hasil determinan harus 1\n");
            main(args);
        }
        }
        if(choice==2){
        System.out.println("KUNCI MATRIK ORDO 2X2");
        System.out.println("Isikan Nilai Matrik A :");
        int m1 = scan.nextInt();
        System.out.println("Isikan Nilai Matrik B :");
        int m2 = scan.nextInt();
        System.out.println("Isikan Nilai Matrik C :");
        int m3 = scan.nextInt();
        System.out.println("Isikan Nilai Matrik D :");
        int m4 = scan.nextInt();
        if ((m1*m2)-(m3*m4)==1) {
            System.out.println("Matriks telah memenuhi syarat enkripsi HillChiper\n");
            int[][] kunci = {{m2, modulo-m3}, {modulo-m4, m1}};
            System.out.printf("Masukan Path :");
            String path2 = scan.next();
            try {
            File f0 = new File(path2);
            Scanner reader=new Scanner(f0);
            while(reader.hasNextLine()){
                String data = reader.nextLine();
                //proses enkripsi;
                hill.hitungEnkripsi(data, kunci);
            }
            reader.close();
            }catch(FileNotFoundException e){
            System.out.println("Kesalahan Akses File!");
            e.printStackTrace();
            }
            //simpan ke deskripsi
            FileWriter writer=new FileWriter("C:\\Users\\ADVANG4C\\Documents\\NetBeansProjects\\Java_HillChiper\\src\\java_hillchiper\\deskripsi.txt");
            try{
            writer.write(hill.totalHasilEnkrip);
            }catch(IOException e){
            e.printStackTrace();
            }
            finally{
            writer.flush();
            writer.close();
            System.out.println("Data disimpan coba check file tersebut!\n");
            main(args);
            }
        } else {
            System.out.println("Tidak memenuhi syarat, Coba lagi dengan hasil determinan harus 1\n");
            main(args);
        }
        }
       
    }
    
   
}
