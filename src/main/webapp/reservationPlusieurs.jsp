<%@ page import="com.pfe.model.Client" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  Client client = (Client) session.getAttribute("client");
  if (client == null) {
    response.sendRedirect("index.jsp");
    return;
  }
  int nombre = (int) request.getAttribute("nombre");
  int id_hotel=(int) request.getAttribute("id_hotel");
%>
<!DOCTYPE html>
<html>
<head>
  <title>Réservation Plusieurs Chambres</title>
  <style>
    /* Styles globaux */
    :root {
      --primary-color: #2563eb;
      --danger-color: #dc2626;
      --success-color: #006600;
      --error-color: #cc0000;
      --background-color: #f5f5f5;
      --card-background: white;
      --shadow: 0 2px 4px rgba(0,0,0,0.1);
    }

    body {
      font-family: Arial, sans-serif;
      margin: 0;
      padding: 20px;
      background-color: var(--background-color);
      display: flex;
      justify-content: center;
      align-items: center;
      height: 100vh;
    }

    /* Animation d'apparition progressive */
    @keyframes fadeIn {
      from {
        opacity: 0;
        transform: translateY(20px);
      }
      to {
        opacity: 1;
        transform: translateY(0);
      }
    }

    /* Conteneur principal avec animation */
    .reservation-container {
      max-width: 600px;
      width: 100%;
      padding: 20px;
      background: var(--card-background);
      border-radius: 8px;
      box-shadow: var(--shadow);
      animation: fadeIn 0.8s ease-out;
    }

    /* Titre de la page */
    .page-title {
      text-align: center;
      color: #333;
      font-size: 2em;
      margin-bottom: 20px;
    }

    /* Styles pour les champs de formulaire */
    .form-group {
      margin-bottom: 15px;
    }

    .form-group label {
      display: block;
      margin-bottom: 5px;
      font-weight: bold;
      color: #333;
    }

    .form-group input {
      width: 100%;
      padding: 10px;
      border: 1px solid #ddd;
      border-radius: 4px;
      font-size: 1em;
    }

    /* Bouton de réservation */
    .reserve-button {
      width: 100%;
      padding: 10px;
      background: var(--primary-color);
      color: white;
      border: none;
      border-radius: 4px;
      font-size: 1em;
      cursor: pointer;
      transition: background-color 0.3s ease, transform 0.3s ease;
    }

    .reserve-button:hover {
      background: #1d4ed8;
      transform: scale(1.05);
    }

    /* Animation de pulsation pour le bouton */
    @keyframes pulse {
      0% {
        transform: scale(1);
      }
      50% {
        transform: scale(1.05);
      }
      100% {
        transform: scale(1);
      }
    }

    .reserve-button.pulse {
      animation: pulse 1.5s infinite;
    }
  </style>
</head>
<body>
<div class="reservation-container">
  <!-- Titre de la page -->
  <h1 class="page-title">Réservation de plusieurs chambres</h1>

  <!-- Formulaire de réservation -->
  <form action="ReservationPlus" method="POST">
    <%
      for (int i = 0; i < nombre; i++) {
    %>
    <div class="form-group">
      <label for="chambre<%=i%>">Saisir l'ID de la chambre <%=i + 1%></label>
      <input type="number" id="chambre<%=i%>" name="chambre<%=i%>" required>
    </div>
    <%
      }
    %>
    <input type="hidden" name="nombre_chambre" value="<%=nombre%>">
    <input type="hidden" name="id_hotel" value="<%=id_hotel%>" >

    <!-- Bouton de réservation -->
    <button type="submit" name="action" value="ReserverPlusieursChambre" class="reserve-button pulse">
      Réserver
    </button>
  </form>
</div>
</body>
</html>