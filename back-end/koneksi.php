<?php
define('Host','localhost'); // Hostnya
define('Username','root');  // Username Database
define('Password','');      // Password
define('Database','mysqlandroid'); // Nama Database

$koneksi = mysqli_connect(Host,Username,Password,Database) or die ('Koneksi Gagal'); // Konek ke Database

?>