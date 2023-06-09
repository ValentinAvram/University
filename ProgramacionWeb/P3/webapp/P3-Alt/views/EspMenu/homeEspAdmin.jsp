<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Menú de Espectaculos</title>
<link rel="stylesheet" href="../../css/homeEspAdmin.css">
</head>
<body>
<jsp:include page="../headerNavAdmin.jsp"></jsp:include>
    	
    <div class="cont">
	<div class="add">
	  <a href=./createEspAdmin.jsp><img class="foto" src="/P3-Alt/P3-Alt/img/add.png" alt="dar de alta espectaculo" style="width:100%"></a>
	  <div class="container">
	<p>Dar de alta</p>
	  </div>
	</div>
	<div class="mod">
	  <a href=../../../GetAllEsps><img class="foto" src="/P3-Alt/P3-Alt/img/modify.png" alt="modificar sesiones" style="width:100%"></a>
	  <div class="container">
	  <p>Modificar sesiones</p>
	  </div>
	</div>
	<div class="cancelEsp">
	  <a href=../../../GetAllToDelete><img class="foto" src="/P3-Alt/P3-Alt/img/trashcan.png" alt="cancelar espectaculo" style="width:100%"></a>
	<div class="container">
	  <p>Cancelar espect&aacuteculos</p>
	  </div>
	</div>
	<div class="addSes">
	  <a href=../../../GetMultTempServlet><img class="foto" src="/P3-Alt/P3-Alt/img/addses.png" alt="añadir sesiones" style="width:100%"></a>
	<div class="container">
	  <p>Añadir sesiones</p>
	  </div>
	</div>
	<div class="cancel">
	  <a href=../../../GetMultTempDeleteServlet><img class="foto" src="/P3-Alt/P3-Alt/img/cancelar.png" alt="cancelar sesiones" style="width:100%"></a>
	<div class="container">
	  <p>Cancelar sesiones</p>
	  </div>
	</div>
	</div>

</body>
</html>