<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.pfe.model.Client" %>
<%@ page import="java.util.List" %>
<%@ page import="com.pfe.model.Reservation" %>
<%@ page import="com.pfe.dao.GetReservation" %>
<%
    Client client = (Client) session.getAttribute("client");
    if (client == null) {
        response.sendRedirect("login.jsp");
    }
%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Mon Espace Client</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-50 font-sans min-h-screen">
<!-- Navbar moderne -->
<nav class="bg-white shadow-md">
    <div class="container mx-auto px-6 py-4 flex justify-between items-center">
        <a href="#" class="text-2xl font-bold text-blue-600 hover:text-blue-700 transition duration-300">🏨 HôtelApp</a>
        <ul class="flex space-x-6">
            <li><a href="dashboard.jsp" class="text-gray-700 hover:text-blue-600 transition duration-300">Accueil</a></li>
            <li><a href="rechercheHotel.jsp" class="text-gray-700 hover:text-blue-600 transition duration-300">Rechercher</a></li>
            <li><a href="mesFactures" class="text-gray-700 hover:text-blue-600 transition duration-300">Factures</a></li>
            <li><a href="profile.jsp" class="text-gray-700 hover:text-blue-600 transition duration-300">Profil</a></li>
            <li><a href="logout.jsp" class="text-gray-700 hover:text-blue-600 transition duration-300">Déconnexion</a></li>
        </ul>
    </div>
</nav>

<!-- Contenu principal -->
<main class="container mx-auto px-4 py-8">
    <!-- En-tête avec carte de bienvenue -->
    <div class="bg-white rounded-xl shadow-lg p-6 mb-8">
        <div class="flex items-center justify-between">
            <div>
                <h1 class="text-3xl font-bold text-gray-800">Bienvenue, <%= client.getNom() %></h1>
                <p class="text-gray-600 mt-2">Gérez vos réservations et profitez de nos services</p>
            </div>
            <div class="hidden md:block">
                <img src="https://via.placeholder.com/100" alt="decoration" class="h-24 w-24">
            </div>
        </div>
    </div>

    <!-- Section des réservations -->
    <div class="bg-white rounded-xl shadow-lg p-6">
        <h2 class="text-2xl font-bold text-gray-800 mb-6">Vos réservations</h2>

        <%
            int id = client.getId();
            List<Reservation> reservations = GetReservation.getAllReservationByIdClient(id);
            if (reservations != null && !reservations.isEmpty()) {
        %>
        <div class="overflow-x-auto">
            <table class="w-full">
                <thead>
                <tr class="bg-gray-50 border-b border-gray-200">
                    <th class="px-6 py-3 text-left text-gray-500 text-sm font-medium">Réservation ID</th>
                    <th class="px-6 py-3 text-left text-gray-500 text-sm font-medium">Arrivée</th>
                    <th class="px-6 py-3 text-left text-gray-500 text-sm font-medium">Départ</th>
                    <th class="px-6 py-3 text-left text-gray-500 text-sm font-medium">Statut</th>
                    <th class="px-6 py-3 text-left text-gray-500 text-sm font-medium">Actions</th>
                </tr>
                </thead>
                <tbody class="divide-y divide-gray-200">
                <%
                    for (Reservation reservation : reservations) {
                        String confirme = reservation.isConfirmed() ? "Confirmée" : "En attente";
                        String statusClass = reservation.isConfirmed() ?
                                "bg-green-100 text-green-800 border-green-200" :
                                "bg-yellow-100 text-yellow-800 border-yellow-200";
                %>
                <tr class="hover:bg-gray-50 transition duration-150">
                    <td class="px-6 py-4 text-sm text-gray-900">#<%= reservation.getId() %></td>
                    <td class="px-6 py-4 text-sm text-gray-900"><%= reservation.getDate_arrive() %></td>
                    <td class="px-6 py-4 text-sm text-gray-900"><%= reservation.getDate_depart() %></td>
                    <td class="px-6 py-4">
                                <span class="px-3 py-1 border rounded-full text-xs font-medium <%= statusClass %>">
                                    <%= confirme %>
                                </span>
                    </td>
                    <td class="px-6 py-4">
                        <button class="text-blue-600 hover:text-blue-800 text-sm font-medium">
                            Voir détails
                        </button>
                    </td>
                </tr>
                <% } %>
                </tbody>
            </table>
        </div>
        <% } else { %>
        <div class="text-center py-12 px-4">
            <div class="mb-4">
                <img src="path_to_empty_state.svg" alt="Aucune réservation" class="mx-auto h-32 w-32">
            </div>
            <h3 class="text-xl font-medium text-gray-900 mb-2">Aucune réservation trouvée</h3>
            <p class="text-gray-600 mb-6">Commencez par rechercher un hôtel pour votre prochain séjour</p>
            <a href="rechercheHotel.jsp" class="inline-flex items-center px-4 py-2 bg-blue-600 text-white rounded-lg hover:bg-blue-700 transition duration-300">
                Rechercher un hôtel
            </a>
        </div>
        <% } %>
    </div>
</main>

<!-- Footer -->
<footer class="bg-white border-t border-gray-200 mt-12">
    <div class="container mx-auto px-6 py-4">
        <p class="text-center text-gray-600 text-sm">
            © 2024 HôtelApp. Tous droits réservés.
        </p>
    </div>
</footer>
</body>
</html>