# eShop

# Navigation List
- [Module 1](#module-1)

## Module 1
# ---Reflection 1---
Dalam latihan pertama ini, saya menyadari beberapa hal penting yang perlu diperhatikan untuk menulis kode yang bersih dan menerapkan praktik pengkodean yang aman.

- Nama variabel dan fungsi yang bermakna
  Memberi nama yang jelas pada variabel dan fungsi membantu saya memahami tujuan masing-masing serta kapan dan di mana harus menggunakannya. Hal ini membuat kode lebih mudah dipahami dan lebih rapi.
- Mengimplementasikan fungsi dengan baik
  Sama seperti pemberian nama yang bermakna, implementasi fungsi yang tepat membantu saya memahami apa yang harus dilakukan oleh fungsi tersebut dan kapan harus menggunakannya. Bahkan jika hanya digunakan sekali, membungkus logika dalam fungsi tetap merupakan praktik yang baik agar kode lebih mudah dibaca dan dipahami.
- Menyederhanakan kode
  Selama latihan, saya menyadari bahwa beberapa bagian kode yang saya tulis terlalu rumit. Setelah menyederhanakannya, kode menjadi lebih mudah dipahami dan tidak terlalu membebani dengan kompleksitas yang tidak perlu.
  
Saya juga menemukan beberapa masalah dalam versi awal kode sebelum mengunggahnya ke GitHub, seperti kesalahan implementasi. Salah satu contohnya adalah saya lupa memperbarui model produk agar secara otomatis menghasilkan Product ID. Kesalahan kecil seperti ini mudah terlewat, tetapi juga mudah diperbaiki. Dari pengalaman ini, saya belajar bahwa selalu waspada terhadap potensi masalah saat mengembangkan fitur itu penting, bahkan sejak tahap perencanaan.

# ---Reflection 2---
Setelah menulis unit test, saya semakin memahami bahwa meskipun awalnya saya menganggap unit test tidak terlalu berguna dan lebih memakan waktu dibandingkan pengujian manual, ternyata ada situasi di mana unit testing menjadi jauh lebih efektif.

Untuk kode sederhana seperti yang pernah saya kerjakan pada matkul sebelumnya (misalnya dalam SDA), pengujian manual terasa lebih efisien karena ukuran kodenya yang relatif kecil. Namun, dalam proyek yang lebih besar seperti ini, unit testing justru menjadi lebih bermanfaat.

Dengan unit testing, saya dapat lebih mudah memaksimalkan cakupan kode (code coverage). Saya juga merasa bahwa unit test yang saya buat sudah mencakup 100% kode, sehingga seharusnya tidak ada kesalahan ketika kode digunakan secara resmi. Walaupun begitu, hasil dari 100% code coverage belum tentu menjadikan kode saya terhindar dari bug atau error. Hal ini disebabkan karena bisa saja ada error atau bug yang belum dicakup dalam unit testing.

Jika saya diminta untuk membuat suite pengujian fungsional tambahan guna memverifikasi jumlah item dalam daftar produk, lalu membuat kelas Java baru yang mirip dengan suite pengujian fungsional sebelumnya, maka kualitas kode justru akan menurun. Penyebab utamanya adalah duplikasi kode. Setiap kelas pengujian akan memiliki kode setup yang sama, sehingga jika ada perubahan (misalnya pada logika baseUrl), maka pembaruan harus dilakukan di banyak tempat. Hal ini meningkatkan beban pemeliharaan dan berisiko menimbulkan inkonsistensi di antara suite pengujian.

Oleh karena itu, solusi yang lebih baik adalah mengekstrak logika setup yang umum ke dalam kelas dasar (base class) untuk semua pengujian fungsional, sehingga lebih efisien dan mudah dikelola.

## Module 2
Aplikasi saya udah di-deploy di link ini: https://broad-stephana-lantry-glitch-58569552.koyeb.app/

Beberapa masalah code quality yang saya hadapi di modul kali ini macem-macem, misalnya:
1. Banyak baris kode yang cuma di-comment tapi belum dihapus.
2. Ada return redirection ke file di resources yang penamaannya kurang tepat, jadi rawan error atau bug.
3. Method setUp di unit test yang isinya kosong alias gak ada gunanya.

Cara saya ngatasinnya:
1. Pastikan baris kode yang cuma di-comment itu udah gak berfungsi lagi di versi push terbaru, terus dihapus dari program.
2. Misalnya, di controller editProductPage, ada return "editProduct", padahal file HTML yang dituju seharusnya "EditProduct". Ini bikin inkonsistensi. Solusinya simpel, yaitu memastikan semua return pakai format Pascal Case. Selain bikin inkonsistensi, penamaan yang salah juga bisa nyebabin error atau bug, apalagi kalau program dijalankan di Linux yang lebih strict dibanding Windows.
3. Buat masalah method setUp, sebetulnya ada banyak cara, tapi pilihan yang salah adalah menghapus method setUp karena saya langsung inisialisasi Product di setiap method test.

Menurut saya, workflow CI/CD yang saya implementasi udah sesuai sama definisi Continuous Integration dan Continuous Deployment. Lewat GitHub workflows (pakai GitHub Actions), program saya otomatis ngejalanin testing (dicek pake CI dan divalidasi sama SonarQube) dan di-deploy tiap kali ada push ke repository GitHub (dengan scorecard). Dari sisi CD, saya udah pakai PaaS dari Koyeb. Rangkaian task inilah yang ngebentuk workflow CI/CD saya.

Bonus (code coverage 100%):
![image](https://github.com/user-attachments/assets/faffc01c-1a4c-4fb3-b18d-8b58563f94c1)
