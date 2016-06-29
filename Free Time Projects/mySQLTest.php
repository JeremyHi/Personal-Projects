<?php

$mysql_hostname = "localhost";
$mysql_username = "root";
$mysql_password = " Winston ";
$mysql_database = "users_test";
$db = mysql_connect ($mysql_hostname, $mysql_username, $mysql_password) or die(mysql_error());
echo "Connected to Database. ";


mysql_select_db($mysql_database, $db) or die("Couldn't find database");
echo "Selected Database. ";


$submit = $_POST['submit'];			//"submit" button is clicked
$firstName = 'John';				//$_POST['firstName'];
$lastName = 'Cena';					//$_POST['lastName'];
$email = 'test@gmail.com';			//$_POST['email'];
$password = '1234';					//$_POST['password'];
$encpassword = 	md5($password);		//md5($_POST['password']);

// In order to reset id field to 0
// ALTER TABLE users AUTO_INCREMENT = 0;


//ONCE page is created check if submit has been clicked
// if ($submit) {
	$insert= mysql_query("INSERT INTO `users` (`id`, `fistName`, `lastName`, `email`, `password`) VALUES (NULL, '$firstName', '$lastName', '$email', '$encpassword')") or die(mysql_error());
	echo "Submitted. ";
//}

?>