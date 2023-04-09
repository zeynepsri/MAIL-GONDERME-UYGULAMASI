import java.io.*;
import java.util.Properties;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Main{
    static Scanner verial = new Scanner(System.in);
    public static void main(String[] args) throws Exception {
        Uye_bilgi_girisi kisi = new Uye_bilgi_girisi();
        System.out.println("MERHABA :D");
        System.out.println("E-Mail Uygulamasına Giriş Yaptınız.");
        kisi.kullanici_bilgileri(); //fonksiyon cagrılarak kullanicinin bilgileri alindi.
        int i;
        for(i=1;i<2;) {
            System.out.println("Yapmak İstediginiz islemi seciniz: \n 1)EliT Uye Ekleme \n 2)Genel Uye Ekleme \n 3)Mail Gönderme ");
            int islem = verial.nextInt(); //Kullanıcıdan islem alındı.
            switch (islem) {
                case 1:
                    kisi.bilgi_giris1();
                    break;
                case 2:
                    kisi.bilgi_giris2();
                    break;
                case 3:
                    System.out.println("---MAIL GONDERME---");
                    Mail.mail_gonder(); //Mail classından mail_gonder fonksiyonu cagırıldı.
                    break;
                default:
                    System.out.println("Dogru islem secimi yapamadınız."); //Kullanıcının dogru islem secememe ihtimaline karsılık default eklendi.
            }
            System.out.println("Devam etmek istiyor musunuz? 1=EVET 2=HAYIR ");
            int devam =verial.nextInt();
            if(devam== 1){
                i=1; //Kullanıcı devam etmek istiyorsa i=1 sayesinde for döngüsü sağlandı.
            }
            else if(devam==2){
                i=2; //Kullanıcı devam etmek istemiyorsa i=2 kullanılarak for döngüsünden cıkıldı.
            }
            else{
                System.out.println("Yanlış veri girdiniz.");
                i=2; //kullanıcının 1 veya 2 seceneklerini secmemesine karsılık for döngüsünden cikmak icin i=2 kullanıldı.
            }
        }
        System.out.println("Uygulamadan cıkıs yaptınız. Yeniden Bekleriz. :D"); //kullaniciya uygulamadan cıkıs yaptıgı ekranda gosterildi.
    }
}
class Uye_bilgi_girisi {
    static Scanner s_al=new Scanner(System.in); //sayi almak icin tanimlandi
    static Scanner t_al=new Scanner(System.in); //text almak icin tanimlandi
    static int i;
    static int L;
    static String kullaniciemail;
    static String kullanicisifre;

    //class icerisinde kullanabilmek icin degiskenler tanımlandı.
    String[] isim_genel;
    String[] soyisim_genel;

    static String[] email_genel;
    String[] isim_elit;
    String[] soyisim_elit;
    static String[] email_elit;
    public void kullanici_bilgileri(){//kullanici bilgileri alindi.
        System.out.println("Email adresinizi giriniz:");
        kullaniciemail= t_al.nextLine();
        System.out.println("Google tarafından tanımlanmış 2adımlı uygulama şifrenizi giriniz:");
        kullanicisifre= t_al.nextLine();

    }
    public void bilgi_giris1() throws IOException { //kullanıcıdan isim,soyisim,email bilgileri alindi.
        File dosya_1 = new File("elit_uye");
        if(!dosya_1.exists()) { //dosya_1 adında dosya yoksa dosya olusturuldu. Var ise tekrar olusturulması engellendi.
            dosya_1.createNewFile();
        }
        FileWriter elit = new FileWriter (dosya_1, false);//Dosya yazma işlemleri için dosyayazıcısı oluşturur.
        BufferedWriter elitb = new BufferedWriter(elit);  /*Karakterleri önce ara belleğe yazar. Bellek dolunca
         ya da yazıcı kapatıldığında diske yazar.*/

        System.out.println("--- ELIT UYE EKLEME ---");
        System.out.println("Kaç kişi eklemek istiyorsunuz?");
        L= s_al.nextInt();
        isim_elit = new String[L]; //kullanicidan aldigimiz kisi sayısına göre isim dizisi oluşturur.
        soyisim_elit = new String[L]; //kullanicidan aldigimiz kisi sayısına göre soyisim dizisi oluşturur.
        email_elit = new String[L]; //kullanicidan aldigimiz kisi sayısına göre email dizisi oluşturur.
        elitb.write(" ISIM \t SOYISIM \t EMAIL \n ");
        for(i=0;i<L;i++) {
            System.out.println("İsim giriniz: ");
            isim_elit[i]= t_al.nextLine();
            System.out.println("Soyisim giriniz: ");
            soyisim_elit[i]= t_al.nextLine();
            System.out.println("Email giriniz: ");
            email_elit[i]= t_al.nextLine();
            elitb.write(isim_elit[i]); //elit_uye dosyasına isim yazdırıldı.
            elitb.write("\t \t");
            elitb.write(soyisim_elit[i]); //elit_uye dosyasına soyisim yazdırıldı.
            elitb.write("\t \t");
            elitb.write(email_elit[i]); //elit_uye dosyasına email yazdırıldı.
            elitb.write("\n"); //dosya icerisinde alt satıra gecildi.
        }
        elitb.close(); //dosya yazdırma islemi kapatıldı.
    }
    public void bilgi_giris2() throws IOException { //kullanıcıdan isim,soyisim,email bilgileri alindi.
        File dosya_2 = new File("genel_uye");
        if(!dosya_2.exists()){ //dosya_2 adında dosya yoksa dosya olusturuldu. Var ise tekrar olusturulması engellendi.
            dosya_2.createNewFile();
        }
        FileWriter genel = new FileWriter (dosya_2, false);//Dosya yazma işlemleri için dosyayazıcısı oluşturur.
        BufferedWriter genelb= new BufferedWriter(genel);  /*Karakterleri önce ara belleğe yazar. Bellek dolunca
         ya da yazıcı kapatıldığında diske yazar.*/
        System.out.println("---GENEL UYE EKLEME---");
        System.out.println("Kaç kişi eklemek istiyorsunuz?");
        L=s_al.nextInt();
        isim_genel=new String[L]; //kullanicidan aldigimiz kisi sayısına göre isim dizisi oluşturur.
        soyisim_genel=new String[L]; //kullanicidan aldigimiz kisi sayısına göre soyisim dizisi oluşturur.
        email_genel=new String[L]; //kullanicidan aldigimiz kisi sayısına göre email dizisi oluşturur.
        genelb.write("ISIM \t SOYISIM \t EMAIL \n ");
        for(i=0;i<L;i++){
            System.out.println("İsim giriniz:");
            isim_genel[i]= t_al.nextLine(); //kullanicidan aldigi isim verisini diziye atar.
            System.out.println("Soyisim giriniz:");
            soyisim_genel[i]= t_al.nextLine(); //kullanicidan aldigi soyisim verisini diziye atar.
            System.out.println("Email giriniz:");
            email_genel[i]= t_al.nextLine(); //kullanicidan aldigi email verisini diziye atar.
            genelb.write(isim_genel[i]); //elit_uye dosyasına isim yazdırıldı.
            genelb.write("\t \t");
            genelb.write(soyisim_genel[i]); //elit_uye dosyasına soyisim yazdırıldı.
            genelb.write("\t \t");
            genelb.write(email_genel[i]); //elit_uye dosyasına email yazdırıldı.
            genelb.write("\n"); //dosya icerisinde alt satıra gecildi.
        }
        genelb.close(); //dosya yazdırma islemi kapatıldı.
    }
}
class Mail extends Uye_bilgi_girisi{
    static Scanner sayial = new Scanner(System.in);
    static Scanner textal= new Scanner(System.in);
    public static void mail_gonder() throws Exception {
        Properties properties = new Properties();
        //gmail üzerinden mail yollamak icin gerekli SMTP Server bilgileri koda eklendi.
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        String gonderen = kullaniciemail;
        String sifre =kullanicisifre;
        String alici; //gönderilecek kisiye atamak icin alici tanımlaması yapildi.
        int secim;
        System.out.println("Alici: 1)Elit Üyeler 2)Genelüyeler 3)Tüm üyeler"); //kullancıdan kime gönderecegini secmesi istendi.
        secim=sayial.nextInt();
        System.out.println("Konu:");//kullanicidan mailin konusu istendi.
        String konu = textal.nextLine();
        System.out.println("Mail Icerigi: "); //kullanicidan mailin icerigi istendi.
        String text = textal.nextLine();
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(gonderen, sifre);
            }
        });
        switch(secim){ //kullanıcının sectigi secenege gore sectigi kisilere  email gönderildi.
            case 1:
                for(i=0;i<L;i++) {
                    alici = email_elit[i];
                    Message mail = mail_bilgileri(session, gonderen, alici, konu, text);
                    Transport.send(mail); //mail_bilgileri fonksiyonuna göre hazirlanmis maili gönderir.
                }
                break;
            case 2:
                for(i=0;i<L;i++) {
                    alici = email_genel[i];
                    Message mail = mail_bilgileri(session, gonderen, alici, konu, text);
                    Transport.send(mail); //mail_bilgileri fonksiyonuna göre hazirlanmis maili gönderir.
                }
                break;
            case 3:
                for(i=0;i<L;i++) {
                    alici = email_elit[i];
                    Message mail = mail_bilgileri(session, gonderen, alici, konu, text);
                    Transport.send(mail); //mail_bilgileri fonksiyonuna göre hazirlanmis maili gönderir.
                    alici = email_genel[i];
                    Message mail2 = mail_bilgileri(session, gonderen, alici, konu, text);
                    Transport.send(mail2); //mail_bilgileri fonksiyonuna göre hazirlanmis maili gönderir.
                }
                break;
        }

        System.out.println("Mail basarıyla gönderildi");
    }
    private static Message mail_bilgileri (Session session, String gonderen, String alici , String konu, String text){
        try { /*kullanicinin dogru veri girmemesine karsılık programin durmamasi ve program bitiminde
        hatanin ne oldugunu görmesi icin try-catch kullanildi. */
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(gonderen)); //maili gonderecek kisi tanimlandi.
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(alici));  //mailin gönderilecegi alici tanımlandi.
            message.setSubject(konu); //mailin konusu tanimlandi.
            message.setText(text); //mailin icerigi tanimlandi.
            return message;
        } catch (Exception ex) {
            Logger.getLogger(Mail.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}