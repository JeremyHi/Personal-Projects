<?php

$host= 'localhost';
$user= 'root';
$pass= ' Winston ';
$db= 'users_test';

$con= mysqli_connect($host, $user, $pass, $db);
if($con)
	echo "Successfully connected";

$query= mysqli_query("CREATE TABLE users (id INTEGER PRIMARY KEY, firstName TEXT, lastName TEXT, email TEXT, password TEXT)");

$sql= "INSERT INTO users VALUES (1, "John", "Cena", "test@gmail.com", "1234");"

// INSERT INTO users VALUES (1, "John", "Cena", "test@gmail.com");
// INSERT INTO users VALUES (2, "Jim", "Smith", "fake@gmail.com");
// INSERT INTO users VALUES (3, "Mike", "Rashid", "mRashid@gmail.com");
// INSERT INTO users VALUES (4, "Joe", "Rogan", "jrogan@gmail.com");

?>