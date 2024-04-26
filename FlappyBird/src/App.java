import javax.swing.*;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // Membuat dan menampilkan StartForm
                StartForm startForm = new StartForm();
            }
        });
    }
}
