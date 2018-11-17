<?php 

require "connection.php";

$user_name = $_GET["user_name"];
$user_password = $_GET["user_password"];

$sql = "SELECT name FROM login_info WHERE user_name = '$user_name' AND user_password = '$user_password'";

$result = mysqli_query($con,$sql);

if (!mysqli_num_rows($result)>0) {

    $status = "failed";
    echo json_encode(array("response"=>$status));
}
else {
    
    $row = mysqli_fetch_assoc($result);
    $name = $row['name'];
    $status = "ok";
    echo json_encode(array("response"=>$status, "name"=>$name));
}

mysqli_close($con);

?>