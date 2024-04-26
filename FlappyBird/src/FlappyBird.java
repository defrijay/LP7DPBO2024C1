import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class FlappyBird extends JPanel implements ActionListener, KeyListener {
    // Dimensi frame
    int frameWidth = 360;
    int frameHeight = 640;

    // Atribut untuk background dan gambar lainnya
    Image backgroundImage;
    Image birdImage;
    Image lowerPipeImage;
    Image upperPipeImage;

    // Atribut pemain
    int playerStartPosX = frameWidth / 8;
    int playerStartPosY = frameHeight / 2;
    int playerWidth = 34;
    int playerHeight = 24;
    Player player;

    // Atribut pipa
    int pipeStartPosX = frameWidth;
    int pipeStartPosY = 0;
    int pipeWidth = 64;
    int pipeHeight = 512;
    ArrayList<Pipe> pipes;

    // Timer untuk game loop dan penempatan pipa
    Timer gameLoop;
    Timer pipesCooldown;

    // Gravitasi
    int gravity = 1;

    // Label untuk skor
    JLabel scoreLabel;
    int score = 0;

    public FlappyBird() {
        setPreferredSize(new Dimension(frameWidth, frameHeight));
        setBackground(Color.blue);
        addKeyListener(this);

        // Memuat gambar dari file
        backgroundImage = new ImageIcon(getClass().getResource("assets/background.png")).getImage();
        birdImage = new ImageIcon(getClass().getResource("assets/bird.png")).getImage();
        lowerPipeImage = new ImageIcon(getClass().getResource("assets/lowerPipe.png")).getImage();
        upperPipeImage = new ImageIcon(getClass().getResource("assets/upperPipe.png")).getImage();

        // Inisialisasi pemain dan array pipa
        player = new Player(playerStartPosX, playerStartPosY, playerWidth, playerHeight, birdImage);
        pipes = new ArrayList<>();

        // Timer untuk game loop
        gameLoop = new Timer(1000/60, this);
        gameLoop.start();

        // Timer untuk penempatan pipa
        pipesCooldown = new Timer(1500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                placePipes();
            }
        });
        pipesCooldown.start();

        // Inisialisasi dan konfigurasi label skor
        scoreLabel = new JLabel("Score: " + score);
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 20));
        scoreLabel.setBounds(20, 20, 100, 30);
        add(scoreLabel);
    }

    // Metode paintComponent untuk menggambar komponen
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    // Metode untuk menggambar background, pemain, dan pipa
    public void draw(Graphics g) {
        g.drawImage(backgroundImage, 0, 0, frameWidth, frameHeight, null);
        g.drawImage(player.getImage(), player.getPosX(), player.getPosY(), player.getWidth(), player.getHeight(), null);

        for(int i = 0; i < pipes.size(); i++) {
            Pipe pipe = pipes.get(i);
            g.drawImage(pipe.getImage(), pipe.getPosX(), pipe.getPosY(), pipe.getWidth(), pipe.getHeight(), null);
        }
    }

    // Metode untuk menggerakkan pemain dan pipa
    public void move() {
        player.setVelocityY(player.getVelocityY() + gravity);
        player.setPosY(player.getPosY() + player.getVelocityY());
        player.setPosY(Math.max(player.getPosY(), 0));

        for(int i = 0; i < pipes.size(); i++) {
            Pipe pipe = pipes.get(i);
            pipe.setPosX(pipe.getPosX() + pipe.getVelocityX());
        }
    }

    // Metode untuk menempatkan pipa baru
    public void placePipes() {
        int randomPipePosY = (int) (pipeStartPosY - pipeHeight / 4 - Math.random() * (pipeHeight / 2));
        int openingSpace = frameHeight / 4;

        Pipe upperPipe = new Pipe(pipeStartPosX, randomPipePosY, pipeWidth, pipeHeight, upperPipeImage);
        pipes.add(upperPipe);

        Pipe lowerPipe = new Pipe(pipeStartPosX, randomPipePosY + pipeHeight + openingSpace, pipeWidth, pipeHeight, lowerPipeImage);
        pipes.add(lowerPipe);
    }

    // Metode untuk mendeteksi tabrakan pemain dengan pipa
    public boolean isColliding(Pipe pipe) {
        Rectangle playerRect = new Rectangle(player.getPosX(), player.getPosY(), player.getWidth(), player.getHeight());
        Rectangle pipeRect = new Rectangle(pipe.getPosX(), pipe.getPosY(), pipe.getWidth(), pipe.getHeight());

        return playerRect.intersects(pipeRect);
    }

    // Metode untuk mendeteksi pemain jatuh ke bawah batas bingkai JFrame
    public boolean isPlayerOutOfBounds() {
        return player.getPosY() + player.getHeight() >= frameHeight;
    }

    // Metode untuk memulai kembali permainan
    public void restartGame() {
        score = 0;
        scoreLabel.setText("Score: " + score);
        player.setPosY(playerStartPosY);
        pipes.clear();
        pipesCooldown.restart();
        gameLoop.start();
        requestFocus();
    }

    // Metode actionPerformed untuk menangani peristiwa
    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
        move();

        // Deteksi tabrakan dengan pipa atau jatuh ke bawah
        for (Pipe pipe : pipes) {
            if (isColliding(pipe) || isPlayerOutOfBounds()) {
                gameLoop.stop();
                pipesCooldown.stop();
                JOptionPane.showMessageDialog(this, "Yahh :( Game Over!\n Skor Kamu: " + score, "Game Over", JOptionPane.INFORMATION_MESSAGE);
                restartGame();
            }
        }

        // Deteksi pemain melewati pipa
        for (Pipe pipe : pipes) {
            if (pipe.getPosX() + pipe.getWidth() < playerStartPosX && !pipe.isPassed()) {
                pipe.setPassed(true);
                score++;
                scoreLabel.setText("Score: " + score);
            }
        }
    }

    // Metode keyPressed untuk menangani input keyboard
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            player.setVelocityY(-10);
        }

        if (e.getKeyCode() == KeyEvent.VK_R) {
            restartGame();
        }
    }

    // Implementasi metode keyTyped dan keyReleased
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}

    // Metode main untuk menjalankan aplikasi
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame("Flappy Bird");
                FlappyBird flappyBird = new FlappyBird();
                frame.add(flappyBird);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(360, 640);
                frame.setLocationRelativeTo(null);
                frame.setResizable(false);
                frame.setVisible(true);
            }
        });
    }
}
