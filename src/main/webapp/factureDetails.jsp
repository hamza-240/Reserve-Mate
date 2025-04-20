<%@ page import="java.util.Map" %>
<%@ page import="com.pfe.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    // Récupérer le client depuis la session
    Client client = (Client) session.getAttribute("client");
    if (client == null) {
        // Rediriger vers la page d'accueil si le client n'est pas connecté
        response.sendRedirect("index.jsp");
        return;
    }

    // Récupérer les attributs de la requête
    Facture facture = (Facture) request.getAttribute("facture");
    Reservation reservation = (Reservation) request.getAttribute("reservation");
    Hotel hotel = (Hotel) request.getAttribute("hotel");
    Map<Chambre, Tarif> mapChambreTarif = (Map<Chambre, Tarif>) request.getAttribute("chambre");
%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Détails de la Facture - HôtelApp</title>
    <link rel="stylesheet" href="Front/dashbordClient.css">
    <style>
        :root {
            --primary: #6D28D9; /* Violet */
            --secondary: #DB2777; /* Rose */
            --success: #10B981; /* Vert */
            --background: #F3F4F6; /* Gris clair */
            --card: #FFFFFF; /* Blanc */
            --text: #1F2937; /* Gris foncé */
            --text-secondary: #6B7280; /* Gris moyen */
            --border-color: #E2E8F0; /* Gris de bordure */
            --radius: 8px; /* Rayon des bordures */
            --shadow: 0 4px 6px -1px rgba(0, 0, 0, 0.1), 0 2px 4px -1px rgba(0, 0, 0, 0.06); /* Ombre */
            --transition: all 0.3s ease; /* Transition */
        }

        body {
            background-color: var(--background);
            color: var(--text);
        }

        .navbar {
            background-color: var(--card);
            box-shadow: var(--shadow);
        }

        .navbar-container {
            max-width: 1200px;
            margin: 0 auto;
            padding: 1rem;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        .navbar-logo {
            font-size: 1.5rem;
            font-weight: bold;
            color: var(--primary);
            text-decoration: none;
        }

        .navbar-logo:hover {
            color: var(--secondary);
        }

        .navbar-menu {
            display: flex;
            gap: 1.5rem;
            list-style: none;
            margin: 0;
            padding: 0;
        }

        .navbar-menu a {
            color: var(--text-secondary);
            text-decoration: none;
            transition: var(--transition);
        }

        .navbar-menu a:hover {
            color: var(--primary);
        }

        .invoice-details-container {
            max-width: 1200px;
            margin: 2rem auto;
            padding: 0 1rem;
            animation: fadeIn 0.4s ease-out;
        }

        .invoice-details-header {
            margin-bottom: 2rem;
        }

        .invoice-details-header h2 {
            font-size: 1.8rem;
            color: var(--text);
            margin-bottom: 0.5rem;
        }

        .invoice-details-header p {
            color: var(--text-secondary);
            font-size: 1.1rem;
            background: none;
            box-shadow: none;
            padding: 0;
        }

        .invoice-details-card {
            background-color: var(--card);
            border-radius: var(--radius);
            box-shadow: var(--shadow);
            padding: 2rem;
        }

        .invoice-details-table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 1.5rem;
        }

        .invoice-details-table th {
            background-color: var(--background);
            padding: 1rem;
            text-align: left;
            font-weight: 600;
            color: var(--text);
            border-bottom: 2px solid var(--border-color);
        }

        .invoice-details-table td {
            padding: 1rem;
            border-bottom: 1px solid var(--border-color);
            color: var(--text-secondary);
        }

        .invoice-details-table tr:last-child td {
            border-bottom: none;
        }

        .invoice-details-table tr:hover {
            background-color: var(--background);
            transition: var(--transition);
        }

        .status-paid {
            color: var(--success);
            font-weight: 500;
        }

        .status-pending {
            color: var(--secondary);
            font-weight: 500;
        }

        .amount {
            font-family: monospace;
            font-weight: 600;
            color: var(--text);
        }

        .invoice-actions {
            display: flex;
            gap: 0.5rem;
            margin-top: 1.5rem;
        }

        .btn-back {
            padding: 0.5rem 1rem;
            background-color: var(--primary);
            color: white;
            border: none;
            border-radius: var(--radius);
            cursor: pointer;
            transition: var(--transition);
            text-decoration: none;
            font-size: 0.875rem;
        }

        .btn-back:hover {
            background-color: var(--secondary);
        }

        @media (max-width: 768px) {
            .invoice-details-container {
                padding: 1rem;
            }

            .invoice-details-card {
                padding: 1rem;
            }

            .navbar-menu {
                gap: 1rem;
            }
        }
    </style>
</head>
<body>
<!-- Barre de navigation -->
<nav class="navbar">
    <div class="navbar-container">
        <a href="#" class="navbar-logo">HôtelApp</a>
        <ul class="navbar-menu">
            <li><a href="dashboard.jsp">Accueil</a></li>
            <li><a href="rechercheHotel.jsp">Rechercher</a></li>
            <li><a href="reservations.jsp">Réservations</a></li>
            <li><a href="profil.jsp">Mon profil</a></li>
            <li><a href="logout.jsp">Déconnexion</a></li>
        </ul>
    </div>
</nav>

<!-- Contenu des détails de la facture -->
<div class="invoice-details-container">
    <div class="invoice-details-header">
        <h2>Détails de la Facture</h2>
        <p>Informations détaillées sur la facture #<%= facture.getId() %></p>
    </div>

    <div class="invoice-details-card">
        <table class="invoice-details-table">
            <thead>
            <tr>
                <th>Champ</th>
                <th>Valeur</th>
            </tr>
            </thead>
            <tbody>
            <!-- Informations de la facture -->
            <tr>
                <td>N° Facture</td>
                <td>#<%= facture.getId() %></td>
            </tr>
            <tr>
                <td>Date de la facture</td>
                <td><%= facture.getDate_facture() %></td>
            </tr>
            <tr>
                <td>Montant total</td>
                <td class="amount"><%= String.format("%.2f €", facture.getMontant()) %></td>
            </tr>
            <tr>
                <td>État de la facture</td>
                <td class="<%= facture.getEtat().equalsIgnoreCase("payée") ? "status-paid" : "status-pending" %>">
                    <%= facture.getEtat() %>
                </td>
            </tr>

            <!-- Informations de l'hôtel -->
            <tr>
                <td>Nom de l'hôtel</td>
                <td><%= hotel.getNom() %></td>
            </tr>
            <tr>
                <td>Ville</td>
                <td><%= hotel.getVille() %></td>
            </tr>
            <tr>
                <td>Nombre d'étoiles</td>
                <td><%= hotel.getEtoilles() %></td>
            </tr>

            <!-- Informations de la réservation -->
            <tr>
                <td>Prix total</td>
                <td><%= reservation.getPrix() %></td>
            </tr>
            <tr>
                <td>Montant restant</td>
                <td><%= reservation.getReste() %></td>
            </tr>
            <tr>
                <td>Avance payée</td>
                <td><%= reservation.getPrix()-reservation.getReste() %></td>
            </tr>
            <tr>
                <td>Nombre de personnes</td>
                <td><%= reservation.getNombre_person() %></td>
            </tr>
            <tr>
                <td>Date d'arrivée</td>
                <td><%= reservation.getDate_arrive() %></td>
            </tr>
            <tr>
                <td>Date de départ</td>
                <td><%= reservation.getDate_depart() %></td>
            </tr>
            <tr>
                <td>Date de réservation</td>
                <td><%= reservation.getDate_reserved() %></td>
            </tr>

            <!-- Détails des chambres et tarifs -->
            <% for (Map.Entry<Chambre, Tarif> entry : mapChambreTarif.entrySet()) {
                Chambre chambre = entry.getKey();
                Tarif tarif = entry.getValue();
            %>
            <tr>
                <td>Chambre #<%= chambre.getId() %></td>
                <td>
                    Capacité : <%= chambre.getNombre_personnes() %> personnes<br>
                    Tarif : <%= tarif.getTarif() %> €
                </td>
            </tr>
            <% } %>
            </tbody>
        </table>

        <!-- Bouton de retour -->
        <div class="invoice-actions">
            <a href="mesFactures.jsp" class="btn-back">Retour aux factures</a>
        </div>
    </div>
</div>
</body>
</html>