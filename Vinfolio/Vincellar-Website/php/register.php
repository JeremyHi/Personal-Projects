<?php
    $mysql_hostname = "";
    $mysql_username = "";
    $mysql_password = "";
    $mysql_database = "";
    $db = mysql_connect ($mysql_hostname, $mysql_username, $mysql_password) or die(mysql_error());

    mysql_select_db($mysql_database, $db) or die("Couldn't find database");
?>