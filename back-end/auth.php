<?php 
 

	if($_SERVER['REQUEST_METHOD']=='POST'){

	//Mendapatkan Nilai Dari Variable ID Pegawai yang ingin ditampilkan
	$username = $_POST['username'];
	$password = $_POST['password'];
	$login_state = $_POST['login_state'];
	$login_time = $_POST['login_time'];
	
	//Koneksi Ke Database
	require_once('koneksi.php');
	
	//Membuat Sql Query Berdasarkan Username
	$sql = "SELECT * FROM user WHERE username = '$username' ";
	
	//Mendapatkan Hasil 
	$r = mysqli_query($koneksi,$sql);
	
	//Memasukkan Hasil Kedalam Array
	$result = array();
	$row = mysqli_fetch_array($r);
	
	if ( $row['password']==$password)
	{
		echo "Benar";
	}
    else 
    {
     	echo "Salah";
    }

	$sql2=	$sql = "INSERT INTO user (login_state,login_time) VALUES ('$login_state','$login_state')";
	$r = mysqli_query($koneksi,$sql2);

	mysqli_close($koneksi);

}
?>