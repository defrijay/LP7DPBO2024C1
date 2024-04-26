# Nama : Defrizal Yahdiyan Risyad
# NIM : 2206131
# Kelas : Ilmu Komputer C1 2022


# Janji
Saya Defrizal Yahdiyan Risyad dengan NIM 2206131 mengerjakan Laporan Praktikum 7
dalam mata kuliah Desain Pemograman Berorientasi Objek untuk keberkahanNya 
maka saya tidak melakukan kecurangan seperti yang telah dispesifikasikan. Aamiin.

# Ringkasan
Flappy Bird Game adalah implementasi sederhana dari game Flappy Bird yang populer menggunakan Java Swing GUI. Permainan ini memungkinkan pemain untuk mengontrol seekor burung, menavigasi melalui serangkaian pipa tanpa memukulnya. Tujuannya adalah mencapai skor setinggi mungkin dengan melewati pipa sebanyak mungkin.

# Desain Program
1. **App.java**: Kelas utama yang bertanggung jawab untuk memulai aplikasi. Ini membuat dan menampilkan StartForm untuk memulai permainan.

2. **StartForm.java**: Formulir GUI yang ditampilkan di awal program. Ini berisi tombol "Mulai Game" yang, ketika ditekan, akan menutup sendiri dan membuka FlappyBird JFrame.

3. **FlappyBird.java**: Kelas utama yang mengelola game Flappy Bird. Ini memperluas JFrame untuk berfungsi sebagai wadah utama game. Kelas ini menangani logika permainan, termasuk menggambar latar belakang, pemain, dan pipa, mendeteksi tabrakan, mengatur skor, dan banyak lagi.

4. **Pipe.java**: Mewakili pipa-pipa di game Flappy Bird. Setiap pipa mempunyai posisi, ukuran, gambar, kecepatan, dan status yang menunjukkan apakah pipa tersebut telah dilewati oleh pemain atau belum.

5. **Player.java**: Mewakili pemain (burung) dalam game Flappy Bird. Pemain memiliki posisi, ukuran, gambar, dan kecepatan.

# Alur Program
1. Program dimulai dengan menjalankan `App.java`.
2. `App.java` membuat dan menampilkan `StartForm`.
3. Ketika tombol "Start Game" ditekan pada `StartForm`, form akan menutup sendiri dan membuka FlappyBird JFrame.
4. Di `FlappyBird.java`, permainan dimulai dengan inisialisasi objek pemain dan pipa, serta pengaturan timer.
5. Pemain dapat mengontrol burung menggunakan input keyboard (spasi untuk melompat, R untuk memulai kembali permainan).
6. Setiap kali pengatur waktu terpicu, permainan memperbarui posisi pemain dan pipa, dan memeriksa tabrakan antara pemain dan pipa.
7. Jika pemain bertabrakan dengan pipa atau jatuh di bawah batas JFrame, permainan berakhir dan skor ditampilkan.
8. Jika pemain berhasil melewati sebuah pipa, skornya bertambah.
9. Pemain dapat memulai kembali permainan dengan menekan tombol “Start Game” pada form awal atau menekan “R” setelah permainan berakhir.

