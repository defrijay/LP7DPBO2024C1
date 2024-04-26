import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartForm extends JFrame {
    public StartForm() {
        // Set judul form
        setTitle("Flappy Bird");
        // Set ukuran form
        setSize(800, 480);
        // Letakkan form di tengah layar
        setLocationRelativeTo(null);
        // Tetapkan operasi penutupan form
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Buat tombol "Start Game"
        JButton startButton = new JButton("Start Game");
        // Tambahkan action listener untuk tombol "Start Game"
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Tutup form saat tombol ditekan
                dispose();
                // Buka JFrame game FlappyBird
                startGame();
            }
        });

        // Buat panel untuk menampung tombol
        JPanel panel = new JPanel();
        panel.add(startButton);
        // Tambahkan panel ke dalam form
        add(panel);

        // Tampilkan form
        setVisible(true);
    }

    // Metode untuk membuka JFrame game FlappyBird
    private void startGame() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // Buat JFrame game FlappyBird
                JFrame frame = new JFrame("Flappy Bird");
                // Buat objek FlappyBird
                FlappyBird flappyBird = new FlappyBird();
                // Tambahkan objek FlappyBird ke dalam JFrame
                frame.add(flappyBird);
                // Tetapkan operasi penutupan untuk JFrame
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                // Tetapkan ukuran JFrame
                frame.setSize(360, 640);
                // Letakkan JFrame di tengah layar
                frame.setLocationRelativeTo(null);
                // Tetapkan agar ukuran JFrame tidak dapat diubah
                frame.setResizable(false);
                // Tampilkan JFrame
                frame.setVisible(true);
            }
        });
    }
}
