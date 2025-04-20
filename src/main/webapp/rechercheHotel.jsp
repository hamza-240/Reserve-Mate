<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.pfe.model.Client" %>
<%
  Client client = (Client) session.getAttribute("client");
  if (client == null) {
    response.sendRedirect("login.jsp");
    return;
  }
  String dateDebut = (String) session.getAttribute("debutDate");
  String dateFin  = (String) session.getAttribute("finDate");
  if (dateDebut != null || dateFin != null) {
    session.removeAttribute("debutDate");
    session.removeAttribute("finDate");
  }
%>
<!DOCTYPE html>
<html lang="fr">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Recherche d'hôtel | HôtelApp</title>
  <!-- Intégration de Tailwind CSS via CDN -->
  <script src="https://cdn.tailwindcss.com"></script>
  <style>
    /* Animation d'entrée */
    @keyframes slideIn {
      from {
        opacity: 0;
        transform: translateY(20px);
      }
      to {
        opacity: 1;
        transform: translateY(0);
      }
    }

    .search-card {
      animation: slideIn 0.3s ease-out;
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
      <li><a href="rechercheHotel" class="text-blue-600 font-semibold">Rechercher un hôtel</a></li>
      <li><a href="reservations.jsp" class="text-gray-700 hover:text-blue-600 transition duration-300">Mes réservations</a></li>
      <li><a href="profil.jsp" class="text-gray-700 hover:text-blue-600 transition duration-300">Mon profil</a></li>
      <li><a href="logout.jsp" class="text-gray-700 hover:text-blue-600 transition duration-300">Déconnexion</a></li>
    </ul>
  </div>
</nav>

<!-- Contenu principal -->
<div class="container mx-auto px-4 py-8">
  <div class="text-center mb-8">
    <h2 class="text-3xl font-bold text-gray-800">Rechercher un hôtel</h2>
    <p class="text-gray-600 mt-2">Trouvez l'hôtel parfait pour votre séjour</p>
  </div>

  <!-- Carte de recherche -->
  <div class="bg-white rounded-xl shadow-lg p-8 max-w-3xl mx-auto search-card">
    <form action="RechercheHotel" method="POST" class="space-y-6">
      <!-- Nom de l'hôtel -->
      <div>
        <label for="nom" class="block text-sm font-medium text-gray-700">Nom de l'hôtel</label>
        <input type="text"
               id="nom"
               name="nom"
               placeholder="Ex: Grand Hôtel Palace"
               class="mt-1 block w-full px-4 py-2 border border-gray-300 rounded-lg shadow-sm focus:ring-blue-500 focus:border-blue-500">
      </div>

      <!-- Ville -->
      <div>
        <label for="ville" class="block text-sm font-medium text-gray-700">Ville</label>
        <input type="text"
               id="ville"
               name="ville"
               placeholder="Ex: Paris"
               class="mt-1 block w-full px-4 py-2 border border-gray-300 rounded-lg shadow-sm focus:ring-blue-500 focus:border-blue-500">
      </div>

      <!-- Nombre d'étoiles -->
      <div>
        <label for="etoiles" class="block text-sm font-medium text-gray-700">Nombre d'étoiles</label>
        <select id="etoiles"
                name="etoiles"
                class="mt-1 block w-full px-4 py-2 border border-gray-300 rounded-lg shadow-sm focus:ring-blue-500 focus:border-blue-500">
          <option value="">Toutes les catégories</option>
          <option value="1">⭐ 1 étoile</option>
          <option value="2">⭐⭐ 2 étoiles</option>
          <option value="3">⭐⭐⭐ 3 étoiles</option>
          <option value="4">⭐⭐⭐⭐ 4 étoiles</option>
          <option value="5">⭐⭐⭐⭐⭐ 5 étoiles</option>
        </select>
      </div>

      <!-- Dates d'arrivée et de départ -->
      <div class="grid grid-cols-1 md:grid-cols-2 gap-6">
        <div>
          <label for="date_arrivee" class="block text-sm font-medium text-gray-700">Date d'arrivée</label>
          <input type="date"
                 id="date_arrivee"
                 name="date_arrivee"
                 class="mt-1 block w-full px-4 py-2 border border-gray-300 rounded-lg shadow-sm focus:ring-blue-500 focus:border-blue-500">
        </div>
        <div>
          <label for="date_depart" class="block text-sm font-medium text-gray-700">Date de départ</label>
          <input type="date"
                 id="date_depart"
                 name="date_depart"
                 class="mt-1 block w-full px-4 py-2 border border-gray-300 rounded-lg shadow-sm focus:ring-blue-500 focus:border-blue-500">
        </div>
      </div>

      <!-- Actions du formulaire -->
      <div class="flex justify-end space-x-4">
        <button type="reset"
                class="px-6 py-2 bg-gray-200 text-gray-700 rounded-lg hover:bg-gray-300 transition duration-300">
          Réinitialiser
        </button>
        <button type="submit"
                class="px-6 py-2 bg-blue-600 text-white rounded-lg hover:bg-blue-700 transition duration-300">
          Rechercher
        </button>
      </div>
    </form>
  </div>
</div>
</body>
</html>