<%@ page import="com.pfe.model.Client" %>
<%@ page import="java.util.List" %>
<%@ page import="com.pfe.model.Hotel" %>
<%@ page import="com.pfe.dao.GetHotel" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
  Client client = (Client) session.getAttribute("client");
  if (client == null) {
    response.sendRedirect("index.jsp");
    return;
  }
  List<Hotel> hotels = (List<Hotel>) request.getAttribute("hotels");
%>
<!DOCTYPE html>
<html lang="fr">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Résultats de recherche | HôtelApp</title>
  <!-- Intégration de Tailwind CSS via CDN -->
  <script src="https://cdn.tailwindcss.com"></script>
  <style>
    /* Animation d'entrée pour les cartes d'hôtel */
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

    .hotel-card {
      animation: fadeIn 0.5s ease-out;
    }
  </style>
</head>
<body class="bg-gray-50 font-sans">
<!-- Barre de navigation -->
<nav class="bg-white shadow-md">
  <div class="container mx-auto px-6 py-4 flex justify-between items-center">
    <a href="#" class="text-2xl font-bold text-blue-600 hover:text-blue-700 transition duration-300">🏨 HôtelApp</a>
    <ul class="flex space-x-6">
      <li><a href="dashboard.jsp" class="text-gray-700 hover:text-blue-600 transition duration-300">Accueil</a></li>
      <li><a href="rechercheHotel.jsp" class="text-gray-700 hover:text-blue-600 transition duration-300">Rechercher</a></li>
      <li><a href="reservations.jsp" class="text-gray-700 hover:text-blue-600 transition duration-300">Mes réservations</a></li>
      <li><a href="profil.jsp" class="text-gray-700 hover:text-blue-600 transition duration-300">Mon profil</a></li>
      <li><a href="logout.jsp" class="text-gray-700 hover:text-blue-600 transition duration-300">Déconnexion</a></li>
    </ul>
  </div>
</nav>

<!-- Contenu principal -->
<div class="container mx-auto px-4 py-8">
  <div class="bg-white rounded-xl shadow-lg p-6 mb-8">
    <div class="mb-6">
      <h2 class="text-2xl font-bold text-gray-800">Résultats de recherche</h2>
      <p class="text-gray-600 mt-2"><%= hotels != null ? hotels.size() : 0 %> hôtels trouvés</p>
    </div>

    <% if (hotels != null && !hotels.isEmpty()) { %>
    <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
      <% for (Hotel tmp : hotels) { %>
      <div class="hotel-card bg-white rounded-xl shadow-md overflow-hidden">
        <div class="relative">
          <!-- Utilisation de Picsum.photos pour des images aléatoires -->
          <img src="https://picsum.photos/300/200?random=<%= tmp.getId() %>" alt="<%= tmp.getNom() %>" class="w-full h-48 object-cover">
          <div class="absolute bottom-2 right-2 bg-white bg-opacity-75 px-2 py-1 rounded-full text-sm">
            <% for (int i = 1; i <= tmp.getEtoilles(); i++) { %>
            ⭐
            <% } %>
          </div>
        </div>
        <div class="p-4">
          <h3 class="text-xl font-bold text-gray-800"><%= tmp.getNom() %></h3>
          <p class="text-gray-600 mt-2">📍 <%= tmp.getVille() %></p>
          <div class="flex space-x-4 mt-4">
            <a href="#" class="text-blue-600 hover:text-blue-800 text-sm font-medium">Détails</a>
            <a href="ReservationChambre?id=<%= tmp.getId() %>" class="text-blue-600 hover:text-blue-800 text-sm font-medium">Réserver</a>
          </div>
        </div>
      </div>
      <% } %>
    </div>
    <% } else { %>
    <div class="text-center py-12 px-4">
      <div class="mb-4">
        <img src="https://via.placeholder.com/150" alt="Aucun hôtel trouvé" class="mx-auto h-32 w-32">
      </div>
      <h3 class="text-xl font-medium text-gray-900 mb-2">Aucun hôtel trouvé</h3>
      <p class="text-gray-600 mb-6">Essayez de modifier vos critères de recherche</p>
    </div>
    <% } %>
  </div>

  <!-- Pagination -->
  <div class="flex justify-center items-center space-x-4 mt-8">
    <button class="px-4 py-2 bg-gray-200 text-gray-700 rounded-lg hover:bg-gray-300 transition duration-300">Précédent</button>
    <span class="text-gray-600">Page 1 sur 3</span>
    <button class="px-4 py-2 bg-gray-200 text-gray-700 rounded-lg hover:bg-gray-300 transition duration-300">Suivant</button>
  </div>
</div>
</body>
</html>