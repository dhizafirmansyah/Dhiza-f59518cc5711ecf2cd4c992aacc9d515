<?php
 
 require_once('koneksi.php');
 
	if($_SERVER['REQUEST_METHOD']=='POST'){
		
		//Mendapatkan Nilai Variable
		$username = $_POST['username'];
		$password = $_POST['password'];
		$email = $_POST['email'];

        $sqlcek = "SELECT * FROM user WHERE username = '$username' ";
        $r = mysqli_query($koneksi,$sqlcek);
	    //Memasukkan Hasil Kedalam Array
	    $result = array();
	    $row = mysqli_fetch_array($r);
		if ( $row['username']==$username)
		{
			echo "Username sudah digunakan";
			echo " Coba buat username yang lain";
		}
    	else 
    	{
     		
     		$sql = "INSERT INTO user (username,password,email) VALUES ('$username','$password','$email')";
		
			//Include koneksi.php
			require_once('koneksi.php');
		
			//Eksekusi Query database
			if(mysqli_query($koneksi,$sql)){

				echo 'Registrasi Berhasil';
			}else{

				echo 'Registrasi Gagal';
			}
    	}
		
		
		//Pembuatan Syntax SQL
		
		mysqli_close($koneksi);
	}
?>