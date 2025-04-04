<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String login = (String) session.getAttribute("login");
    if (login == null) {
        login = "wait";

    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Connexion</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f4f4f9;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            flex-direction: column;
        }

        h1 {
            color: #333;
            font-size: 2.5em;
            margin-bottom: 20px;
        }

        a {
            text-decoration: none;
            color: #007BFF;
            font-size: 1.2em;
            margin-bottom: 20px;
        }

        a:hover {
            text-decoration: underline;
        }

        form {
            background: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            width: 300px;
            text-align: center;
        }

        input[type="text"], input[type="password"] {
            width: 100%;
            padding: 10px;
            margin: 10px 0;
            border: 1px solid #ddd;
            border-radius: 4px;
            box-sizing: border-box;
        }

        input[type="submit"], button {
            width: 100%;
            padding: 10px;
            background-color: #007BFF;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 1em;
            margin: 5px 0;
        }

        input[type="submit"]:hover, button:hover {
            background-color: #0056b3;
            transform: scale(1.05);
            transition: transform 0.2s ease;
        }

        .button-container {
            display: flex;
            justify-content: space-between;
            width: 100%;
            margin-top: 10px;
        }

        .button-container button {
            width: 48%;
        }

        .button-container button.connexion {
            background-color: #28a745;
        }

        .button-container button.connexion:hover {
            background-color: #218838;
        }

        .button-container button.employee {
            background-color: #dc3545;
        }

        .button-container button.employee:hover {
            background-color: #c82333;
        }

        p.error {
            color: red;
            font-size: 0.9em;
        }
    </style>
</head>
<body>
<h1>Connexion</h1>

<!-- Formulaire de connexion -->
<form action="login" method="post" onsubmit="return validateForm()">
    <input type="text" name="username" placeholder="Username" required>
    <input type="password" name="password" placeholder="Password" required>

    <!-- Champ caché pour stocker la valeur du bouton cliqué -->
    <input type="hidden" name="userType" id="userType" value="">

    <%
        if (login.equals("no")) {
    %>
    <p class="error">Username ou mot de passe incorrect</p>
    <%
        }
    %>

    <!-- Boutons Connexion et Employee -->
    <div class="button-container">
        <button type="submit" onclick="setUserType('client')" class="connexion">Connexion</button>
        <button type="submit" onclick="setUserType('employee')" class="employee">Employee</button>
    </div>
</form>

<script>
    function validateForm() {
        const username = document.querySelector('input[name="username"]').value;
        const password = document.querySelector('input[name="password"]').value;

        if (username.trim() === "" || password.trim() === "") {
            alert("Veuillez remplir tous les champs.");
            return false;
        }
        return true;
    }

    function setUserType(userType) {
        document.getElementById('userType').value = userType;
        return validateForm();
    }
</script>
</body>
</html>