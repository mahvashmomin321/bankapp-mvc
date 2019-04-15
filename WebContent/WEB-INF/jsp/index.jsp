<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!doctype html>
<html>
<head>
	<title>HBSC</title>
	<meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
  <link rel="stylesheet" href="css/style.css">
</head>
<body>
  <header class="navbar navbar-inverse navbar-fixed-top bs-docs-nav navc">
    <div class="navbar-header">
      <a class="navbar-brand" href="index.html">HBSC BANK</a>
    </div>
    <nav class="collapse navbar-collapse bs-navbar-collapse" role="navigation">
      <ul class="nav navbar-nav navbar-right">
        <li class="active">
          <a href="index.html">HOME</a>
        </li>
        <li>
          <a href="/bankApp/app/bank/home/new_acc">CREATE NEW ACCOUNT</a>
        </li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">ACTITVITY<b class="caret"></b></a>
          <ul class="dropdown-menu">
            <li><a href="/bankApp/app/bank/home/withdraw">WITHDRAW</a></li>
            <li><a href="/bankApp/app/bank/home/deposit">DEPOSIT</a></li>
            <li><a href="/bankApp/app/bank/home/fund-transfer">FUND TRANSFER</a></li>
            <li><a href="/bankApp/app/bank/home/check-balance">CHECK BALANCE</a></li>
            <li><a href="/bankApp/app/bank/home/show-details">SHOW DETAILS</a></li>
            <li><a href="/bankApp/app/bank/home/search-account">SEARCH ACCOUNT</a></li>
            <li><a href="/bankApp/app/bank/home/delete-acc">DELETE ACCOUNT</a></li>
            <li><a href="/bankApp/app/bank/home/update-acc">UPDATE ACCOUNT</a></li>
          </ul>
        </li>
        <li>
          <a href="#">ABOUT US</a>
        </li>
        <li>
          <a href="#">CONTACT US</a>
        </li>
      </ul>
    </nav>
  </header>
  <div class="banner">
    <h2 class="banner-header">WELCOME TO HSBC BANK</h2>
  </div>
</body>
</html>