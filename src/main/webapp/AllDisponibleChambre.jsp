<%@ page import="com.pfe.model.Hotel" %>
<%@ page import="com.pfe.model.Chambre" %>
<%@ page import="java.util.List" %>
<%@ page import="com.pfe.model.Client" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  Client client = (Client) session.getAttribute("client");
  if (client == null) {
    response.sendRedirect("index.jsp");
    return;
  }
  Hotel hotel = (Hotel) request.getAttribute("hotel");
  List<Chambre> chambres = (List<Chambre>) request.getAttribute("chambres");
  String alert = (String) request.getAttribute("alert");
  if (alert == null)
    alert = "false";
%>
<!DOCTYPE html>
<html>
<head>
  <title><%=hotel.getNom()%> - Chambres</title>
  <link href="https://cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.min.css" rel="stylesheet">
  <style>
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
    }

    .hotel-info {
      text-align: center;
      margin-bottom: 30px;
    }

    .stars {
      font-size: 1.2em;
    }

    .rooms-container {
      display: grid;
      grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
      gap: 20px;
      padding: 20px;
    }

    .room-card {
      background: var(--card-background);
      border-radius: 8px;
      padding: 20px;
      box-shadow: var(--shadow);
      transition: transform 0.2s ease, box-shadow 0.2s ease;
    }

    .room-card:hover {
      transform: translateY(-5px);
      box-shadow: 0 4px 8px rgba(0,0,0,0.2);
    }

    .room-info {
      margin-bottom: 15px;
    }

    .room-id {
      font-weight: bold;
      color: #333;
    }

    .capacity {
      color: #666;
    }

    .status {
      display: inline-block;
      padding: 5px 10px;
      border-radius: 15px;
      font-size: 0.9em;
    }

    .available {
      background: #e6ffe6;
      color: var(--success-color);
    }

    .unavailable {
      background: #ffe6e6;
      color: var(--error-color);
    }

    .price {
      font-size: 1.2em;
      font-weight: bold;
      color: var(--primary-color);
    }

    .reserve-btn, .cancel {
      width: 100%;
      padding: 10px;
      border: none;
      border-radius: 5px;
      cursor: pointer;
      transition: background-color 0.3s ease;
    }

    .reserve-btn {
      background: var(--primary-color);
      color: white;
    }

    .reserve-btn:disabled {
      background: #cccccc;
      cursor: not-allowed;
    }

    .cancel {
      background: var(--danger-color);
      color: white;
      margin-top: 10px;
    }

    .cancel:hover {
      background: #b91c1c;
    }

    .reservation-form {
      position: fixed;
      top: 20px;
      right: 20px;
      width: 250px;
      padding: 15px;
      background: var(--card-background);
      border-radius: 8px;
      box-shadow: var(--shadow);
      animation: slideIn 0.5s ease-out;
      z-index: 100;
    }

    @keyframes slideIn {
      from {
        transform: translateX(100%);
        opacity: 0;
      }
      to {
        transform: translateX(0);
        opacity: 1;
      }
    }

    .form-input {
      width: 100%;
      padding: 8px;
      margin-bottom: 8px;
      border: 1px solid #ddd;
      border-radius: 4px;
      font-size: 0.9em;
    }

    .form-submit {
      width: 100%;
      padding: 8px;
      background: var(--primary-color);
      color: white;
      border: none;
      border-radius: 4px;
      cursor: pointer;
      font-size: 0.9em;
      transition: background-color 0.3s ease;
    }

    .form-submit:hover {
      background: #1d4ed8;
    }

    @media (max-width: 768px) {
      .rooms-container {
        grid-template-columns: 1fr;
      }
    }
  </style>
</head>
<body>
<header class="hotel-info">
  <h1><%=hotel.getNom()%></h1>
  <p>📍 <%=hotel.getVille()%></p>
  <p class="stars">
    <% for(int i = 0; i < hotel.getEtoilles(); i++) { %>
    ⭐
    <% } %>
  </p>
</header>

<div class="reservation-form">
  <form action="Reservation_Plusieurs" method="GET" onsubmit="return validateReservation()">
    <input type="number" name="nombre_chambre" min="1" required class="form-input" placeholder="Nombre de chambres">
    <input type="hidden" name="id_hotel" value="<%=hotel.getId()%>">
    <input type="submit" name="action" value="Réserver plusieurs" class="form-submit">
  </form>
</div>

<main class="rooms-container">
  <% for(Chambre c : chambres) { %>
  <article class="room-card">
    <div class="room-info">
      <p class="room-id">Chambre #<%=c.getId()%></p>
      <p class="capacity">👥 <%=c.getNombre_personnes()%> personnes</p>
      <p class="status <%=c.isDisponible() ? "available" : "unavailable"%>">
        <%=c.isDisponible() ? "Disponible" : "Non disponible"%>
      </p>
      <p class="price"><%=c.getTarif().getTarif()%> €</p>
      <p class="description"><%=c.getTarif().getDescription()%></p>
    </div>
    <form action="Reservation" method="GET">
      <input type="hidden" name="id_chambre" value="<%=c.getId()%>">
      <button type="submit" name="action" value="Reserver" class="reserve-btn" <%=!c.isDisponible() ? "disabled" : ""%>>
        Réserver
      </button>
      <button type="submit" name="action" value="Annuler" class="cancel">
        Annuler
      </button>
    </form>
  </article>
  <% } %>
</main>

<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script>
  function validateReservation() {
    const nombreChambres = document.querySelector('input[name="nombre_chambre"]').value;
    if (nombreChambres < 1) {
      Swal.fire({
        title: 'Erreur',
        text: 'Veuillez entrer un nombre valide de chambres.',
        icon: 'error',
        confirmButtonText: 'OK'
      });
      return false;
    }
    return true;
  }

  <% if ("true".equals(alert)) { %>
  Swal.fire({
    title: 'Notification',
    text: 'Il y a un problème',
    icon: 'error',
    confirmButtonText: 'OK'
  });
  <% } %>
</script>
</body>
</html>