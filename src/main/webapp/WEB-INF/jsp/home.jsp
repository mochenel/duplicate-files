<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
	<title></title>
	<link rel="stylesheet" href="css/bootstrap.min.css">
	<link rel="stylesheet" href="css/font-awesome.min.css">
	<link rel="stylesheet" href="css/home.css">
	<script src="js/search.js"></script>
</head>
<body>

	<div class="container ">
		<h5 class="text-center mt-3"> Remove duplicate files from provided Directory</h5>	
			<div>		
					<form class="row row-cols-lg-auto g-3 align-items-center mt-4" method="POST" modelAttribute="path">
						<div class="col-6" id="val-path">
							<div class="mb-3">
							 
							  <input class="form-control" type="text" name="location" id="path" placeholder="Directory path">
							</div>
						</div>
						<div class="col-2">
							<div class="mb-2">
							  <input class="form-control btn btn-success" onclick="validatePath()" formaction="/files" type="submit" id="files" value="Files">
							</div>
						</div>
							
					</form>
					<p style="display: ${display};" class="alert alert-danger text-center"><em>Path couldn't be found, please provide the correct path</em></p>
			</div>		
			<div id="div-files" class="mt-5">
					

					
					<form class="row row-cols-lg-auto g-3 align-items-center mt-4" method="POST">
						<div class="col-3">
							<div class="mb-2">
							 <span><strong>Total Number Of Files : </strong></span>
							 <span id="size" >${size}</span>
							</div>
						</div>
						
							<div class="col-2">
								<div class="mb-2">
								<input class="form-control btn btn-danger" onclick="validateDelete()" type="submit" id="delete" value="Delete" formaction="/delete">
								</div>
							</div>
							<div class="col-7">
								<div class="mb-2">
								  <input class="form-control"  onkeyup="funFilter()" type="search" id="filter" aria-label="search" placeholder="Search File Name" >
								</div>
							</div>  
					</form>
					
				<table class="table mt-4 table table-hover">
				  <thead>
				    <tr>
				      <th scope="col">#</th>
				      <th scope="col">File Path</th>
				      <th scope="col">File Name</th>
				      <th scope="col">Size on disk</th>
				    </tr>
				  </thead>
				  <tbody>
				    <tr>
					  <c:forEach items = "${files}" var = "item">		
						<th scope="row">${item[0]}</th>
						<td>${item[1]}</td>
						<td>${item[2]}</td>
						<td>${item[3]}</td>
						</tr>
					 </c:forEach>
				    
				  </tbody>
				</table>
			</div>	
	
	</div>

</body>
</html>