import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Random;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Main extends JFrame {

    // komponen utama gui
    CardLayout mainlayout = new CardLayout();  // layout untuk panel utama
    JPanel mainpanel = new JPanel(mainlayout); // panel utama dengan cardlayout
    JLabel stickdisp = new JLabel();           // label untuk menampilkan jumlah batang
    
    // variabel game state
    boolean playerturn;  // menunjukkan apakah giliran pemain
    int stick;           // jumlah batang tersisa
    int min;             // batas minimum jumlah batang awal
    int max;             // batas maksimum jumlah batang awal

    public static void main(String[] args) {
        new Main();  // untuk memulai
    }

    public Main() {
        // konfigurasi dasar Jframe
        setTitle("the last stick: ambatang's challenge");
        setSize(600, 550);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        add(mainpanel);  // menambahkan panel utama ke frame
        setVisible(true); // menampilkan frame

        // inisialisasi panel-panel yang berbeda
        menupanel();   // panel menu utama
        gamepanel();   // panel permainan
        storypanel();  // panel cerita/prolog
        carapanel();   // panel petunjuk cara bermain
    }

    private void menupanel() {
        // membuat panel menu utama dengan tombol-tombol
        JButton startbut = new JButton("start game");
        JButton carabut = new JButton("how to play the game");
        JButton storybut = new JButton("prologue");
        JButton exitbut = new JButton("exit the game");

        // panel utama dengan boxlayout vertikal
        JPanel panel = new JPanel();
        panel.setBackground(Color.BLACK);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // label judul game
        JLabel label = new JLabel("face the cunning king ambatang in a battle of wits and sticks...");
        label.setFont(new Font("cascadia code",Font.ROMAN_BASELINE,15));
        label.setForeground(Color.RED);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        // mengatur alignment tombol-tombol
        startbut.setAlignmentX(Component.CENTER_ALIGNMENT);
        carabut.setAlignmentX(Component.CENTER_ALIGNMENT);
        storybut.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitbut.setAlignmentX(Component.CENTER_ALIGNMENT);

        // menambahkan komponen ke panel
        panel.add(Box.createRigidArea(new Dimension(0, 30)));
        panel.add(label);
        panel.add(Box.createRigidArea(new Dimension(0, 30)));
        panel.add(startbut);
        panel.add(Box.createRigidArea(new Dimension(0, 30)));
        panel.add(carabut);
        panel.add(Box.createRigidArea(new Dimension(0, 30)));
        panel.add(storybut);
        panel.add(Box.createRigidArea(new Dimension(0, 30)));
        panel.add(exitbut);
        
        // menambahkan panel menu ke panel utama
        mainpanel.add(panel, "menu");

        // action listener untuk tombol-tombol
        startbut.addActionListener(e -> {
            startgame();  // memulai game baru
            mainlayout.show(mainpanel, "gamepanel");  // masuk ke gamepanel
        });
        storybut.addActionListener(e -> mainlayout.show(mainpanel, "storypanel"));
        carabut.addActionListener(e -> mainlayout.show(mainpanel, "carapanel"));
        exitbut.addActionListener(e -> System.exit(0));
    }

    private void carapanel() {
        // panel petunjuk cara bermain
        JTextArea t = new JTextArea("""
        1. akan digenerate secara acak angka sebanyak n
        2. akan dipilih secara random giliran bermain oleh komputer
        3. setiap pemain harus mengambil 1, 2, atau 3 batang pada setiap gilirannya
        4. pemain yang mengambil batang terakhir adalah pemain yang kalah
        """);
        // konfigurasi tampilan text
        t.setBackground(Color.BLACK);
        t.setForeground(Color.WHITE);
        t.setFont(new Font("cascadia code", Font.PLAIN, 15));
        t.setEditable(false);
        t.setLineWrap(true);
        t.setWrapStyleWord(true);

        // panel tengah untuk text area
        JPanel centerpanel = new JPanel();
        centerpanel.setBackground(Color.BLACK);
        centerpanel.setLayout(new BoxLayout(centerpanel, BoxLayout.Y_AXIS));
        centerpanel.add(Box.createVerticalGlue());
        t.setMaximumSize(new Dimension(500, 150));
        t.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerpanel.add(t);
        centerpanel.add(Box.createVerticalGlue());

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.BLACK);

        // tombol kembali
        JButton back = new JButton("back");
        back.addActionListener(e -> mainlayout.show(mainpanel, "menu"));

        panel.add(centerpanel, BorderLayout.CENTER);
        panel.add(back, BorderLayout.SOUTH);

        mainpanel.add(panel, "carapanel");
    }

    private void storypanel() {
        // panel cerita/prolog game
        JTextArea t = new JTextArea("Prolog:\n" + //
                        "\n" + //
                        "Di ujung sebuah negeri yang damai, terdapat sebuah desa kecil bernama Tumbak Raya.\n" + //
                        "\n" + //
                        "Selama bertahun-tahun, desa ini hidup tenteram, hingga langit berubah kelabu.\n" + //
                        "\n" + //
                        "Dari utara datanglah seorang tiran yang dikenal dengan nama Raja Ambatang—seorang penguasa sombong, ahli dalam permainan batang kuno yang diwariskan dari para leluhurnya.\n" + //
                        "\n" + //
                        "\"Barang siapa bisa mengalahkanku dalam permainan batang,\" katanya, \"akan kuberikan kebebasannya. Tapi siapa pun yang gagal... akan menjadi pelayanku seumur hidup.\"\n" + //
                        "\n" + //
                        "Tak satu pun dari penduduk desa berhasil menang. Satu per satu, mereka tunduk di bawah kakinya.\n" + //
                        "\n" + //
                        "Namun hari ini...\n" + //
                        "\n" + //
                        "Seorang penantang baru berdiri.\n" + //
                        "\n" + //
                        "Kamu.\n" + //
                        "\n" + //
                        "Dibekali keberanian, akal, dan harapan dari seluruh desa, kamu menerima tantangan Raja Ambatang.\n" + //
                        "\n" + //
                        "Jika kamu gagal, kegelapan akan menyelimuti Tumbak Raya selamanya.\n" + //
                        "\n" + //
                        "Tapi jika kamu berhasil...\n" + //
                        "\n" + //
                        "Maka desa akan bebas, dan nama kamu akan dikenang sepanjang zaman.\n" + //
                        "\n" + //
                        "⚔️ Selamat datang dalam Tantangan Raja Ambatang. Ambil batangmu, dan mulailah permainan.");
        // tampilan text
        t.setEditable(false);
        t.setBackground(Color.BLACK);
        t.setForeground(Color.WHITE);
        t.setFont(new Font("cascadia code",Font.ROMAN_BASELINE,15));

        JPanel panel = new JPanel();
        panel.setBackground(Color.BLACK);
        panel.setLayout(new BorderLayout());

        // tombol kembali
        JButton back = new JButton("back");
        back.addActionListener(e -> mainlayout.show(mainpanel, "menu"));

        panel.add(t, BorderLayout.CENTER);
        panel.add(back, BorderLayout.SOUTH);

        mainpanel.add(panel, "storypanel");
    }

    private void gamepanel() {
        // panel permainan utama
        stickdisp.setForeground(Color.WHITE);
        stickdisp.setFont(new Font("arial", Font.BOLD, 20));
        stickdisp.setAlignmentX(Component.CENTER_ALIGNMENT);

        // label judul permainan
        JLabel t = new JLabel();
        t.setBackground(Color.BLACK);
        t.setForeground(Color.RED);
        t.setText("bermain batang bersama ambatang >////<");
        t.setAlignmentX(Component.CENTER_ALIGNMENT);
        t.setFont(new Font("cascadia code",Font.ROMAN_BASELINE,20));

        // panel judul
        JPanel judul = new JPanel();
        judul.setBackground(Color.BLACK);
        judul.add(t);
        judul.setForeground(Color.RED);

        // panel tombol untuk mengambil batang (1-3)
        JPanel btnpanel = new JPanel();
        btnpanel.setBackground(Color.BLACK);
        for (int i = 1; i <= 3; i++) {
            int num = i;
            JButton btn = new JButton("ambil " + i);
            btn.addActionListener(e -> playermove(num));  // agar saat tombol diklik ada actionnya
            btnpanel.add(btn);
        }

        // tombol restart
        JButton restart = new JButton("restart?");
        restart.setAlignmentX(Component.CENTER_ALIGNMENT);
        restart.addActionListener(e -> mainlayout.show(mainpanel, "menu"));

        // panel dalam dengan layout vertikal
        JPanel innerpanel = new JPanel();
        innerpanel.setBackground(Color.BLACK);
        innerpanel.setLayout(new BoxLayout(innerpanel, BoxLayout.Y_AXIS));
        
        // menambahkan komponen ke panel
        innerpanel.add(Box.createRigidArea(new Dimension(0, 20)));
        innerpanel.add(judul);
        innerpanel.add(Box.createRigidArea(new Dimension(0, 20)));
        innerpanel.add(stickdisp);
        innerpanel.add(Box.createRigidArea(new Dimension(0, 20)));
        innerpanel.add(btnpanel);
        innerpanel.add(Box.createRigidArea(new Dimension(0, 20)));
        innerpanel.add(restart);

        // panel luar dengan layout border
        JPanel outerpanel = new JPanel(new BorderLayout());
        outerpanel.setBackground(Color.BLACK);
        outerpanel.add(innerpanel, BorderLayout.CENTER);

        mainpanel.add(outerpanel, "gamepanel");
    }

    private void stickdisp() {
        // memperbarui tampilan jumlah batang tersisa
        stickdisp.setText("batang tersisa: " + stick);
        stickdisp.setForeground(Color.WHITE);
    }

    private void startgame() {
        // memulai game baru
        min = 10;  // batas minimal batang 
        max = 30;  // batas maksimal batang 
        stick = new Random().nextInt(max - min + 1) + min;  // generate jumlah batang random
        stickdisp();  // mnampilkan batang

        // memilih secara acak siapa yang bermain pertama
        int pilih = new Random().nextInt(2);

        if (pilih == 1) {
            playerturn = true;
            JOptionPane.showMessageDialog(null, "you play first", "...", JOptionPane.DEFAULT_OPTION);
        } else {
            playerturn = false;
            JOptionPane.showMessageDialog(null, "computer play first", "...", JOptionPane.DEFAULT_OPTION);
            computermove(0);  // komputer mulai pertama
        }
    }

    private void playermove(int ambil) {
        // logika saat pemain mengambil batang
        if (!playerturn) return;  // bukan giliran pemain
        if (ambil < 1 || ambil > 3 || ambil > stick) return; 

        stick -= ambil;  // kurangi jumlah batang
        stickdisp();     // perbarui tampilan

        if (stick == 0) {
            // pemain ngambil batang terakhir = kalah
            JOptionPane.showMessageDialog(null, "ambatang menang! (cupu lu :p)");
            return;
        }

        playerturn = false;  // gantian komputer
        computermove(0);     // komputer main
    }

    private void computermove(int ambil) {
        if (stick > 4) {
            int sisamod = stick % 4;
            if (sisamod == 0) ambil = 3;
            else if (sisamod == 3) ambil = 2;
            else if (sisamod == 2) ambil = 1;
            else ambil = new Random().nextInt(3) + 1;  
        } else {
            ambil = stick - 1;
            if (ambil < 1) ambil = 1;
        }

        // info komputer ngambil sekian batang
        JOptionPane.showMessageDialog(null, "computer mengambil " + ambil + " batang.");
        stick -= ambil;
        stickdisp();

        if (stick == 0) {
            // komputer ngambil batang terakhir = pemain menang
            JOptionPane.showMessageDialog(null, "kamu menang!");
            return;
        }

        playerturn = true;  // gantian pemain
    }
}