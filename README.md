# AdproModul

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

Dengan unit testing, saya dapat lebih mudah memaksimalkan cakupan kode (code coverage). Saya juga merasa bahwa unit test yang saya buat sudah mencakup 100% kode, sehingga seharusnya tidak ada kesalahan ketika kode digunakan secara resmi.

Jika saya diminta untuk membuat suite pengujian fungsional tambahan guna memverifikasi jumlah item dalam daftar produk, lalu membuat kelas Java baru yang mirip dengan suite pengujian fungsional sebelumnya, maka kualitas kode justru akan menurun. Penyebab utamanya adalah duplikasi kode. Setiap kelas pengujian akan memiliki kode setup yang sama, sehingga jika ada perubahan (misalnya pada logika baseUrl), maka pembaruan harus dilakukan di banyak tempat. Hal ini meningkatkan beban pemeliharaan dan berisiko menimbulkan inkonsistensi di antara suite pengujian.

Oleh karena itu, solusi yang lebih baik adalah mengekstrak logika setup yang umum ke dalam kelas dasar (base class) untuk semua pengujian fungsional, sehingga lebih efisien dan mudah dikelola.
