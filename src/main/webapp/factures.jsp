<%@ page import="com.pfe.model.Client" %>
<%@ page import="com.pfe.model.Facture" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  Client client = (Client) session.getAttribute("client");
  if (client == null) {
    response.sendRedirect("index.jsp");
    return;
  }
  List<Facture> factureList = (List<Facture>) request.getAttribute("factures");
%>
<!DOCTYPE html>
<html lang="fr">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Mes Factures - HôtelApp</title>
  <!-- Intégration de Tailwind CSS via CDN -->
  <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-50 font-sans">
<!-- Barre de navigation -->
<nav class="bg-white shadow-md">
  <div class="container mx-auto px-6 py-4 flex justify-between items-center">
    <a href="#" class="text-2xl font-bold text-blue-600 hover:text-blue-700 transition duration-300">🏨 HôtelApp</a>
    <ul class="flex space-x-6">
      <li><a href="dashboard.jsp" class="text-gray-700 hover:text-blue-600 transition duration-300">Accueil</a></li>
      <li><a href="rechercheHotel.jsp" class="text-gray-700 hover:text-blue-600 transition duration-300">Rechercher</a></li>
      <li><a href="reservations.jsp" class="text-gray-700 hover:text-blue-600 transition duration-300">Réservations</a></li>
      <li><a href="profil.jsp" class="text-gray-700 hover:text-blue-600 transition duration-300">Mon profil</a></li>
      <li><a href="logout.jsp" class="text-gray-700 hover:text-blue-600 transition duration-300">Déconnexion</a></li>
    </ul>
  </div>
</nav>

<!-- Contenu des factures -->
<div class="container mx-auto px-4 py-8">
  <div class="bg-white rounded-xl shadow-lg p-6">
    <div class="mb-8">
      <h2 class="text-2xl font-bold text-gray-800">Mes Factures</h2>
      <p class="text-gray-600 mt-2">Consultez l'historique de vos factures</p>
    </div>

    <% if (factureList != null && !factureList.isEmpty()) { %>
    <div class="overflow-x-auto">
      <table class="w-full">
        <thead>
        <tr class="bg-gray-50 border-b border-gray-200">
          <th class="px-6 py-3 text-left text-gray-500 text-sm font-medium">N° Facture</th>
          <th class="px-6 py-3 text-left text-gray-500 text-sm font-medium">Date</th>
          <th class="px-6 py-3 text-left text-gray-500 text-sm font-medium">Montant</th>
          <th class="px-6 py-3 text-left text-gray-500 text-sm font-medium">État</th>
          <th class="px-6 py-3 text-left text-gray-500 text-sm font-medium">Actions</th>
        </tr>
        </thead>
        <tbody class="divide-y divide-gray-200">
        <% for (Facture f : factureList) { %>
        <tr class="hover:bg-gray-50 transition duration-150">
          <td class="px-6 py-4 text-sm text-gray-900">#<%= f.getId() %></td>
          <td class="px-6 py-4 text-sm text-gray-900"><%= f.getDate_facture() %></td>
          <td class="px-6 py-4 text-sm text-gray-900"><%= String.format("%.2f €", f.getMontant()) %></td>
          <td class="px-6 py-4">
                    <span class="px-3 py-1 border rounded-full text-xs font-medium <%= f.getEtat().equalsIgnoreCase("payée") ? "bg-green-100 text-green-800 border-green-200" : "bg-yellow-100 text-yellow-800 border-yellow-200" %>">
                      <%= f.getEtat() %>
                    </span>
          </td>
          <td class="px-6 py-4">
            <a href="viewFacture?id=<%= f.getId() %>" class="text-blue-600 hover:text-blue-800 text-sm font-medium">
              Voir détails
            </a>
          </td>
        </tr>
        <% } %>
        </tbody>
      </table>
    </div>
    <% } else { %>
    <div class="text-center py-12 px-4">
      <div class="mb-4">
        <img src="https://via.placeholder.com/150" alt="Aucune facture" class="mx-auto h-32 w-32">
      </div>
      <h3 class="text-xl font-medium text-gray-900 mb-2">Aucune facture trouvée</h3>
      <p class="text-gray-600 mb-6">Vous n'avez pas encore de factures</p>
    </div>
    <% } %>
  </div>
</div>
</body>
</html>