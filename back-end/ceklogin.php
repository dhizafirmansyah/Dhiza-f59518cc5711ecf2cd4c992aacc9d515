<?php 
 

	if($_SERVER['REQUEST_METHOD']=='POST'){

	//Mendapatkan Nilai Dari Variable ID Pegawai yang ingin ditampilkan
	$username = $_POST['username'];
	
	
	//Koneksi Ke Database
	require_once('koneksi.php');
	
	//Membuat Sql Query Berdasarkan Username
	$sql = "SELECT * FROM user WHERE username = '$username' ";
	
	//Mendapatkan Hasil 
	$r = mysqli_query($koneksi,$sql);
	
	//Memasukkan Hasil Kedalam Array
	$result = array();
	$row = mysqli_fetch_array($r);
	
	if ( $row['login_state']=='1')
	{
		echo "Sudah Login Pukul  ";
		echo $row['login_time'];
	}
    else 
    {
     	echo "Belum Silahkan Login";
    }
	
	mysqli_close($koneksi);
}
?>