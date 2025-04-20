<%@ page import="com.pfe.model.Client" %>
<%@ page import="com.pfe.model.Hotel" %>
<%@ page import="com.pfe.model.Chambre" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Client client = (Client) session.getAttribute("client");
    if (client == null) {
        response.sendRedirect("login.jsp");
        return;
    }
    Hotel hotel = (Hotel) session.getAttribute("hotel");
    String action = (String) request.getAttribute("action");
    Chambre chambre = null;
    List<Chambre> chambres = null;
    float sum = 0;
    if ("Reserver".equals(action)) {
        chambre = (Chambre) request.getAttribute("chambre");
        sum += chambre.getTarif().getTarif();
    } else if ("ReserverPlusieursChambre".equals(action)) {
        chambres = (List<Chambre>) request.getAttribute("chambreList");
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Confirmation de réservation</title>
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
            max-width: 800px;
            margin: 0 auto;
            padding: 20px;
            background: var(--card-background);
            border-radius: 8px;
            box-shadow: var(--shadow);
            animation: fadeIn 0.8s ease-out;
        }

        /* Informations sur l'hôtel avec animation */
        .hotel-info {
            text-align: center;
            margin-bottom: 30px;
            animation: fadeIn 1s ease-out;
        }

        .hotel-info h1 {
            color: #333;
            font-size: 2em;
            margin-bottom: 10px;
        }

        .hotel-info p {
            color: #666;
            font-size: 1.1em;
        }

        .stars {
            font-size: 1.2em;
            color: #ffcc00;
        }

        /* Informations sur le client avec animation */
        .client-info {
            margin-bottom: 20px;
            text-align: center;
            animation: fadeIn 1.2s ease-out;
        }

        .client-info h2 {
            color: #333;
            font-size: 1.5em;
            margin-bottom: 10px;
        }

        /* Informations sur la chambre ou les chambres avec animation */
        .chambre-info {
            margin-bottom: 20px;
            animation: fadeIn 1.4s ease-out;
        }

        .chambre-info h3 {
            color: #333;
            font-size: 1.2em;
            margin-bottom: 10px;
        }

        .chambre-info p {
            color: #666;
            font-size: 1em;
            margin-bottom: 5px;
        }

        /* Boutons de confirmation avec animation */
        .confirmation-buttons {
            display: flex;
            justify-content: center;
            gap: 10px;
            margin-top: 20px;
            animation: fadeIn 1.6s ease-out;
        }

        .confirmation-buttons button {
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 1em;
            transition: background-color 0.3s ease, transform 0.3s ease;
        }

        .confirmation-buttons button.ok {
            background: var(--primary-color);
            color: white;
        }

        .confirmation-buttons button.ok:hover {
            background: #1d4ed8;
            transform: scale(1.05);
        }

        .confirmation-buttons button.no {
            background: var(--danger-color);
            color: white;
        }

        .confirmation-buttons button.no:hover {
            background: #b91c1c;
            transform: scale(1.05);
        }

        /* Styles pour les listes de chambres avec animation */
        .chambre-list {
            display: grid;
            gap: 15px;
            margin-top: 20px;
        }

        .chambre-item {
            padding: 15px;
            background: #f9f9f9;
            border-radius: 8px;
            box-shadow: var(--shadow);
            animation: fadeIn 0.8s ease-out;
        }

        .chambre-item p {
            margin: 5px 0;
            color: #666;
        }

        /* Animation de survol pour les cartes de chambres */
        .chambre-item:hover {
            transform: translateY(-5px);
            transition: transform 0.3s ease;
        }

        /* Animation de pulsation pour les boutons */
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

        .confirmation-buttons button.pulse {
            animation: pulse 1.5s infinite;
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

        .montant-total {
            font-size: 1.2em;
            font-weight: bold;
            color: var(--primary-color);
            margin-top: 20px;
            text-align: center;
        }
    </style>
</head>
<body>
<div class="reservation-container">
    <!-- Informations sur l'hôtel -->
    <div class="hotel-info">
        <h1><%=hotel.getNom()%></h1>
        <p>📍 <%=hotel.getVille()%></p>
        <p class="stars">
            <% for (int i = 0; i < hotel.getEtoilles(); i++) { %>
            ⭐
            <% } %>
        </p>
    </div>

    <!-- Informations sur le client -->
    <div class="client-info">
        <h2>Mr/Mme : <%=client.getNom()%></h2>
    </div>

    <!-- Formulaire de réservation -->
    <form action="ReservationServlet" method="post">
        <!-- Champs de formulaire pour les dates et l'avance -->
        <div class="form-group">
            <label for="dateDepart">Date de départ</label>
            <input type="date" id="dateDepart" name="dateDepart" value="<%=session.getAttribute("debutDate")%>" required>
        </div>
        <div class="form-group">
            <label for="dateArrivee">Date d'arrivée</label>
            <input type="date" id="dateArrivee" name="dateArrivee" value="<%=session.getAttribute("finDate")%>" required>
        </div>
        <div class="form-group">
            <label for="avance">Avance</label>
            <input type="number" id="avance" name="avance" required>
        </div>
        <div class="form-group">
            <label for="nombrePersonnes">Nombre de personnes</label>
            <input type="number" id="nombrePersonnes" name="nombre_de_personne" required>
        </div>

        <!-- Champs cachés pour transmettre les données -->
        <input type="hidden" name="hotelId" value="<%=hotel.getId()%>">
        <input type="hidden" name="clientId" value="<%=client.getId()%>">
        <input type="hidden" name="action" value="<%=action%>">

        <!-- Informations sur la chambre ou les chambres -->
        <div class="chambre-info">
            <% if ("Reserver".equals(action)) { %>
            <h3>Détails de la chambre</h3>
            <p>Capacité : <%=chambre.getNombre_personnes()%> personnes</p>
            <p>Tarif : <%=chambre.getTarif().getTarif()%> €</p>
            <input type="hidden" name="chambreId" value="<%=chambre.getId()%>">
            <% } else if ("ReserverPlusieursChambre".equals(action)) { %>
            <h3>Détails des chambres</h3>
            <div class="chambre-list">
                <% int i = 0; for (Chambre c : chambres) {  %>
                <div class="chambre-item">
                    <p>Capacité : <%=c.getNombre_personnes()%> personnes</p>
                    <p>Tarif : <%=c.getTarif().getTarif()%> €</p>
                    <input type="hidden" name="chambreId<%=i++%>" value="<%=c.getId()%>">
                </div>
                <% sum += c.getTarif().getTarif(); } %>
                <div class="montant-total">Montant total : <%=sum%> €</div>
                <input type="hidden" name="nombre" value="<%=i%>">
            </div>
            <% } %>
            <input type="hidden" name="prix_total" value="<%=sum%>">
        </div>

        <!-- Boutons de confirmation -->
        <div class="confirmation-buttons">
            <button type="submit" class="ok pulse">Confirmer la réservation</button>
            <button type="button" class="no" onclick="window.location.href='dashboardClient.jsp'">Annuler</button>
        </div>
    </form>
</div>
</body>
</html>