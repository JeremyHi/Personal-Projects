<!doctype html>
<html>

  <head>

    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->


    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="Images/vincellar-logo.jpg">
    <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" />
    <link rel="stylesheet" type="text/css" href="css/LoginPage.css" />

    <title>Vinfolio: Register for an Account</title>

    <?php
        include('php/register.php');

        $username = $_POST['username'];
        $firstName = $_POST['firstName'];
        $lastName = $_POST['lastName'];
        $email = $_POST['email'];
        $password = $_POST['password'];
        $passwordConfirm = $_POST['passwordConfirm'];
        $submit = $_POST['submit'];
        $encpassword = md5($password);

        if($submit) {
          if($username == true && strlen($username) < 64) {
            if($firstName == true) {
              if($lastName == true) {
                if($email == true && stripos($email, '@') == true) {
                  if($password == true && strlen($password) >= 6) {
                    if($passwordConfirm == true && $password == $passwordConfirm) {
                      if($_POST['accept'] == "on") {

                        
                        $insert= mysql_query("INSERT INTO VinCellar_Users_db VALUES ('', '$username', '$firstName','$lastName','$email','#password')")
                          or die("no work"); 




                      } else
                        echo '<script type="text/javascript">alert("Please accept the terms and conditions")</script>';
                    } else
                      echo '<script type="text/javascript">alert("Please confirm your password")</script>';
                  } else
                    echo '<script type="text/javascript">alert("Please enter a password at least 6 characcters long")</script>';
                } else
                  echo '<script type="text/javascript">alert("Please enter a valid email")</script>';
              } else
                echo '<script type="text/javascript">alert("Please enter a last name")</script>';
            } else
              echo '<script type="text/javascript">alert("Please enter a first name")</script>';
          } else
            echo '<script type="text/javascript">alert("Please enter a valid username less than or equal to 64 characters")</script>';
        };

    ?>
  </head>

  <body>
    <!-- Fixed navbar -->
    <nav class="navbar navbar-default navbar-fixed-top">
      <div class="container-fluid">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">Vincellar</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav">
            <li class="active"><a href="Homepage.html">Home</a></li>
            <li><a href="Overview.html">Overview</a></li>
            <li><a href="https://www.vinfolio.com/">Contact</a></li>  
          </ul>
          <form class="navbar-form navbar-right">
            <div class="form-group">
              <input type="text" placeholder="Email" class="form-control">
            </div>
            <div class="form-group">
              <input type="password" placeholder="Password" class="form-control">
            </div>
            <button type="submit" class="btn btn-success">Sign in</button>
          </form>
        </div>
        <!--/.nav-collapse -->
      </div>
    </nav>

    <div class="panel panel-default">
      <div class="panel-heading">
        <h3 class="panel-title">Register to Join the VinCellar Network</h3>
      </div>

      <div class="panel-body">
        <form class="form-horizontal" action="" method="post">
          <div class="form-group">
            <label for="name" class="col-sm-2 control-label">Username</label>
            <div class="col-sm-4">
              <input name="username" class="form-control" id="name" placeholder="How others see you">
            </div>
          </div>
          <div class="form-group">
            <label for="name" class="col-sm-2 control-label">First Name</label>
            <div class="col-sm-4">
              <input name="firstName" class="form-control" id="name" placeholder="First Name">
            </div>
          </div>
          <div class="form-group">
            <label for="name" class="col-sm-2 control-label">Last Name</label>
            <div class="col-sm-4">
              <input name="lastName" class="form-control" id="name" placeholder="Last Name">
            </div>
          </div>
          <div class="form-group">
            <label for="gmail" class="col-sm-2 control-label">Email</label>
              <div class="col-sm-4">
                <div class="input-group input-group-sm">
                  <span class="input-group-addon">example@mail.com</span>
                  <input name="email" class="form-control" placeholder="">
                </div>
              </div>
          </div>
          <div class="form-group">
            <label for="password" class="col-sm-2 control-label">Password</label>
            <div class="col-sm-4">
              <input name="password" class="form-control" id="pass" placeholder="**********">
            </div>
          </div>
          <div class="form-group">
            <label for="password" class="col-sm-2 control-label">Confirm your password
            </label>
            <div class="col-sm-4">
              <input name="passwordConfirm" class="form-control" id="confirmpass" placeholder="**********">
            </div>
          </div>
          <!-- <div class="form-group">
            <label for="State" class="col-sm-2 control-label">Country</label>
            <div class="col-sm-4">
              <select class="form-control">
                <option value="None"></option>
                <option value="US">United States</option>
                <option value="AF">Afghanistan</option>
                <option value="AX">Aland</option>
                <option value="AL">Albania</option>
                <option value="DZ">Algeria</option>
                <option value="AS">American Samoa</option>
                <option value="AD">Andorra</option>
                <option value="AO">Angola</option>
                <option value="AI">Anguilla</option>
                <option value="AQ">Antarctica</option>
                <option value="AG">Antigua and Barbuda</option>
                <option value="AR">Argentina</option>
                <option value="AM">Armenia</option>
                <option value="AW">Aruba</option>
                <option value="AU">Australia</option>
                <option value="AT">Austria</option>
                <option value="AZ">Azerbaijan</option>
                <option value="BS">Bahamas</option>
                <option value="BH">Bahrain</option>
                <option value="BD">Bangladesh</option>
                <option value="BB">Barbados</option>
                <option value="BY">Belarus</option>
                <option value="BE">Belgium</option>
                <option value="BZ">Belize</option>
                <option value="BJ">Benin</option>
                <option value="BM">Bermuda</option>
                <option value="BT">Bhutan</option>
                <option value="BO">Bolivia</option>
                <option value="BA">Bosnia and Herzegovina</option>
                <option value="BW">Botswana</option>
                <option value="BV">Bouvet Island</option>
                <option value="BR">Brazil</option>
                <option value="IO">British Indian Ocean Territory</option>
                <option value="BN">Brunei Darussalam</option>
                <option value="BG">Bulgaria</option>
                <option value="BF">Burkina Faso</option>
                <option value="BI">Burundi</option>
                <option value="KH">Cambodia</option>
                <option value="CM">Cameroon</option>
                <option value="CA">Canada</option>
                <option value="CV">Cape Verde</option>
                <option value="KY">Cayman Islands</option>
                <option value="CF">Central African Republic</option>
                <option value="TD">Chad</option>
                <option value="CL">Chile</option>
                <option value="CN">China</option>
                <option value="CX">Christmas Island</option>
                <option value="CC">Cocos (Keeling) Islands</option>
                <option value="CO">Colombia</option>
                <option value="KM">Comoros</option>
                <option value="CG">Congo (Brazzaville)</option>
                <option value="CD">Congo (Kinshasa)</option>
                <option value="CK">Cook Islands</option>
                <option value="CR">Costa Rica</option>
                <option value="CI">Cote d&#39;Ivoire</option>
                <option value="HR">Croatia</option>
                <option value="CU">Cuba</option>
                <option value="CY">Cyprus</option>
                <option value="CZ">Czech Republic</option>
                <option value="DK">Denmark</option>
                <option value="DJ">Djibouti</option>
                <option value="DM">Dominica</option>
                <option value="DO">Dominican Republic</option>
                <option value="EC">Ecuador</option>
                <option value="EG">Egypt</option>
                <option value="SV">El Salvador</option>
                <option value="GQ">Equatorial Guinea</option>
                <option value="ER">Eritrea</option>
                <option value="EE">Estonia</option>
                <option value="ET">Ethiopia</option>
                <option value="FK">Falkland Islands</option>
                <option value="FO">Faroe Islands</option>
                <option value="FJ">Fiji</option>
                <option value="FI">Finland</option>
                <option value="FR">France</option>
                <option value="GF">French Guiana</option>
                <option value="PF">French Polynesia</option>
                <option value="TF">French Southern Lands</option>
                <option value="GA">Gabon</option>
                <option value="GM">Gambia</option>
                <option value="GE">Georgia</option>
                <option value="DE">Germany</option>
                <option value="GH">Ghana</option>
                <option value="GI">Gibraltar</option>
                <option value="GR">Greece</option>
                <option value="GL">Greenland</option>
                <option value="GD">Grenada</option>
                <option value="GP">Guadeloupe</option>
                <option value="GU">Guam</option>
                <option value="GT">Guatemala</option>
                <option value="GN">Guinea</option>
                <option value="GW">Guinea-Bissau</option>
                <option value="GY">Guyana</option>
                <option value="HT">Haiti</option>
                <option value="HM">Heard and McDonald Islands</option>
                <option value="HN">Honduras</option>
                <option value="HK">Hong Kong</option>
                <option value="HU">Hungary</option>
                <option value="IS">Iceland</option>
                <option value="IN">India</option>
                <option value="ID">Indonesia</option>
                <option value="IR">Iran</option>
                <option value="IQ">Iraq</option>
                <option value="IE">Ireland</option>
                <option value="IL">Israel</option>
                <option value="IT">Italy</option>
                <option value="JM">Jamaica</option>
                <option value="JP">Japan</option>
                <option value="JO">Jordan</option>
                <option value="KZ">Kazakhstan</option>
                <option value="KE">Kenya</option>
                <option value="KI">Kiribati</option>
                <option value="KP">Korea, North</option>
                <option value="KR">Korea, South</option>
                <option value="KW">Kuwait</option>
                <option value="KG">Kyrgyzstan</option>
                <option value="LA">Laos</option>
                <option value="LV">Latvia</option>
                <option value="LB">Lebanon</option>
                <option value="LS">Lesotho</option>
                <option value="LR">Liberia</option>
                <option value="LY">Libya</option>
                <option value="LI">Liechtenstein</option>
                <option value="LT">Lithuania</option>
                <option value="LU">Luxembourg</option>
                <option value="MO">Macau</option>
                <option value="MK">Macedonia</option>
                <option value="MG">Madagascar</option>
                <option value="MW">Malawi</option>
                <option value="MY">Malaysia</option>
                <option value="MV">Maldives</option>
                <option value="ML">Mali</option>
                <option value="MT">Malta</option>
                <option value="MH">Marshall Islands</option>
                <option value="MQ">Martinique</option>
                <option value="MR">Mauritania</option>
                <option value="MU">Mauritius</option>
                <option value="YT">Mayotte</option>
                <option value="MX">Mexico</option>
                <option value="FM">Micronesia</option>
                <option value="MD">Moldova</option>
                <option value="MC">Monaco</option>
                <option value="MN">Mongolia</option>
                <option value="MS">Montserrat</option>
                <option value="MA">Morocco</option>
                <option value="MZ">Mozambique</option>
                <option value="MM">Myanmar</option>
                <option value="NA">Namibia</option>
                <option value="NR">Nauru</option>
                <option value="NP">Nepal</option>
                <option value="NL">Netherlands</option>
                <option value="AN">Netherlands Antilles</option>
                <option value="NC">New Caledonia</option>
                <option value="NZ">New Zealand</option>
                <option value="NI">Nicaragua</option>
                <option value="NE">Niger</option>
                <option value="NG">Nigeria</option>
                <option value="NU">Niue</option>
                <option value="NF">Norfolk Island</option>
                <option value="MP">Northern Mariana Islands</option>
                <option value="NO">Norway</option>
                <option value="OM">Oman</option>
                <option value="PK">Pakistan</option>
                <option value="PW">Palau</option>
                <option value="PS">Palestine</option>
                <option value="PA">Panama</option>
                <option value="PG">Papua New Guinea</option>
                <option value="PY">Paraguay</option>
                <option value="PE">Peru</option>
                <option value="PH">Philippines</option>
                <option value="PN">Pitcairn</option>
                <option value="PL">Poland</option>
                <option value="PT">Portugal</option>
                <option value="PR">Puerto Rico</option>
                <option value="QA">Qatar</option>
                <option value="RE">Reunion</option>
                <option value="RO">Romania</option>
                <option value="RU">Russian Federation</option>
                <option value="RW">Rwanda</option>
                <option value="SH">Saint Helena</option>
                <option value="KN">Saint Kitts and Nevis</option>
                <option value="LC">Saint Lucia</option>
                <option value="PM">Saint Pierre and Miquelon</option>
                <option value="VC">Saint Vincent and the Grenadines</option>
                <option value="WS">Samoa</option>
                <option value="SM">San Marino</option>
                <option value="ST">Sao Tome and Principe</option>
                <option value="SA">Saudi Arabia</option>
                <option value="SN">Senegal</option>
                <option value="CS">Serbia and Montenegro</option>
                <option value="SC">Seychelles</option>
                <option value="SL">Sierra Leone</option>
                <option value="SG">Singapore</option>
                <option value="SK">Slovakia</option>
                <option value="SI">Slovenia</option>
                <option value="SB">Solomon Islands</option>
                <option value="SO">Somalia</option>
                <option value="ZA">South Africa</option>
                <option value="GS">South Georgia and South Sandwich Islands</option>
                <option value="ES">Spain</option>
                <option value="LK">Sri Lanka</option>
                <option value="SD">Sudan</option>
                <option value="SR">Suriname</option>
                <option value="SJ">Svalbard and Jan Mayen Islands</option>
                <option value="SZ">Swaziland</option>
                <option value="SE">Sweden</option>
                <option value="CH">Switzerland</option>
                <option value="SY">Syria</option>
                <option value="TW">Taiwan</option>
                <option value="TJ">Tajikistan</option>
                <option value="TZ">Tanzania</option>
                <option value="TH">Thailand</option>
                <option value="TL">Timor-Leste</option>
                <option value="TG">Togo</option>
                <option value="TK">Tokelau</option>
                <option value="TO">Tonga</option>
                <option value="TT">Trinidad and Tobago</option>
                <option value="TN">Tunisia</option>
                <option value="TR">Turkey</option>
                <option value="TM">Turkmenistan</option>
                <option value="TC">Turks and Caicos Islands</option>
                <option value="TV">Tuvalu</option>
                <option value="UG">Uganda</option>
                <option value="UA">Ukraine</option>
                <option value="AE">United Arab Emirates</option>
                <option value="GB">United Kingdom</option>
                <option value="UM">United States Minor Outlying Islands</option>
                <option value="UY">Uruguay</option>
                <option value="UZ">Uzbekistan</option>
                <option value="VU">Vanuatu</option>
                <option value="VA">Vatican City</option>
                <option value="VE">Venezuela</option>
                <option value="VN">Viet Nam</option>
                <option value="VG">Virgin Islands, British</option>
                <option value="VI">Virgin Islands, U.S.</option>
                <option value="WF">Wallis and Futuna Islands</option>
                <option value="EH">Western Sahara</option>
                <option value="YE">Yemen</option>
                <option value="ZM">Zambia</option>
                <option value="ZW">Zimbabwe</option>
              </select>
            </div>
          </div> -->
          <div class="form-group">
            <div class="col-sm-offset-2 col-sm-4">
              <div class="checkbox">
                <label>
                  <input name="accept" type="checkbox" value="on">I accept the <a href="http://www.vinfolio.com/about/terms">terms</a> and confirm I am at least 21 years of age
                </label>
              </div>
            </div>
          </div>
          <div class="panel-footer" style="overflow:hidden;text-align:right;">
            <div class="form-group">
              <div class="col-sm-offset-2 col-sm-4">
                <input name="submit" type="submit" class="btn btn-success btn-sm">
              </div>
            </div>
          </div>
        </form>
      </div> <!-- End Panel Body -->
    </div>

    <div class="container-fluid">
      <div class="row">
        <div class="col-xs-12">
          <!-- Collect the nav links, forms, and other content for toggling -->
          <div class=navbar-header>
            <ul class="nav navbar-nav">
              <li><a href="https://www.vinfolio.com/about/company"><small>ABOUT US</small></a></li>
              <li><a href="https://www.vinfolio.com/about/privateClient"><small>PRIVATE CLIENT</small></a></li>
              <li><a href="https://www.vinfolio.com/about/inspectionGuidelines"><small>INSPECTION GUIDELINES</small></a></li>
              <li><a href="https://www.vinfolio.com/about/purchaseGuarantees"><small>PURCHASE GUARANTEES</small></a></li>
              <li><a href="https://www.vinfolio.com/about/privacy"><small>PRIVACY POLICY</small></a></li>
              <li><a href="https://www.vinfolio.com/about/terms"><small>TERMS</small></a></li>
              <li><a href="https://www.vinfolio.com/about/shippingAndDelivery"><small>SHIPPING</small></a></li>
            </ul>
          </div>

          <div class="navbar-header" style="width:50%">
            <div class="navbar-text">
              <h5>PAYMENT TYPES</h5>
              <p>
                <img alt="" src="https://www.thelucasgroup.com.au/assets/card-visa.png" />&nbsp;&nbsp;<img alt="" src="https://www.thelucasgroup.com.au/assets/card-mastercard.png" />&nbsp;&nbsp;<img alt="" src="https://www.thelucasgroup.com.au/assets/card-amex.png" />
              </p>
              <div class="AuthorizeNetSeal">
                <script type="text/javascript">
                  // <![CDATA[
                  var ANS_customer_id = "1bbc5685-894c-4903-8c31-2362914ace6d";
                  // ]]>
                </script>
              </div>
            </div>
          </div>
          <div class="navbar-header">
            <div class="navbar-text navbar-right">
              <h5>CONTACT</h5>
              <small>
                Corporate Office: 1890 Bryant St, Ste 208, San Francisco, CA 94110 800.969.1961<br/>
                Office Hours: Monday to Friday 9 a.m - 5 p.m. Pacific Time<br/><br/>
                Napa Warehouse: 644 Hanna Dr, Ste E American Canyon, CA 94503<br/>
                Warehouse Hours: Monday to Friday 8:30 a.m - 4:30 p.m. Pacific Time<br/>

                Email: service@vinfolio.com<br/>
              </small>
              <h5>Other Vinfolio Sites</h5>
              <a href="http://www.wineprices.com"><small>WINEPRICES</small></a> &nbsp;&nbsp;|&nbsp;&nbsp;

              <a href="https://www.vinfolio.com/do/winestore/home?b=hk"><small>HONG KONG</small></a>
            </div>
          </div>
          <!-- END navbar-header -->
        </div>
        <!-- END col-xs-12 -->
      </div>
      <!-- END row -->
    </div>

  </body>

</html>