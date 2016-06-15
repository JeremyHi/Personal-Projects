<?php
    $mysql_hotsname = "localhost";
    $mysql_username = "root";
    $mysql_password = " Winston ";
    $mysql_database = "VinCellar_Users";
    $db = mysql_connect ($mysql_hotsname, $mysql_username, $mysql_password) or die("Something is broken :(");

    mysql_select_db($mysql_database, $db) or die("Couldn't find database");
?>