<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Error</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-5">
        <div class="alert alert-danger" role="alert">
            <h4 class="alert-heading">An error occurred!</h4>
            <p><%= request.getAttribute("errorMessage") %></p>
            <hr>
            <p>If this issue persists, please contact support.</p>
            <a href="login" class="btn btn-primary">Back to Login</a>
        </div>
    </div>
</body>
</html>
