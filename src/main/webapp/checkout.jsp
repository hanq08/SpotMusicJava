<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SpotMusic</title>
</head>
<body>
<form action="music" method="post">
<fieldset>
<legend>Payment Details</legend>
<p>
<label>
  <input type="radio" name="optionsRadios" id="optionsRadios1" value="JHU Employee" >
  Visa
</label>
<label>
  <input type="radio" name="optionsRadios" id="optionsRadios1" value="JHU Employee" >
  Master Card
</label>
<label>
  <input type="radio" name="optionsRadios" id="optionsRadios1" value="JHU Employee" >
  Discover
</label>
</p>
<p>
<label>Credit Card Number </label><input type="text" required pattern="[0-9]{16}">
</p>
<p>
<label>Expiration Date </label>
    <select>
        <option value="01">January</option>
        <option value="02">February </option>
        <option value="03">March</option>
        <option value="04">April</option>
        <option value="05">May</option>
        <option value="06">June</option>
        <option value="07">July</option>
        <option value="08">August</option>
        <option value="09">September</option>
        <option value="10">October</option>
        <option value="11">November</option>
        <option value="12">December</option>
    </select>
    <select>
        <option value="18"> 2018</option>
        <option value="19"> 2019</option>
        <option value="20"> 2020</option>
        <option value="21"> 2021</option>
    </select>
</p>
    <button type="submit" name="action" value="process_order" class="margin_left">Checkout</button>
</fieldset>
</form>
</body>
</html>