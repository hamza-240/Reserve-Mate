<%@ page import="com.pfe.model.Client" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Client client = (Client) session.getAttribute("client");
    if (client==null ){
        response.sendRedirect("index.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Mon Profil - <%= client.getNom() %></title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gradient-to-r from-blue-100 to-purple-100 min-h-screen">
<!-- Navbar moderne -->
<nav class="bg-white border-b border-gray-200">
    <div class="container mx-auto">
        <div class="flex justify-between items-center py-4 px-6">
            <div class="flex items-center space-x-8">
                <a href="#" class="text-2xl font-extrabold bg-clip-text text-transparent bg-gradient-to-r from-blue-600 to-purple-600">
                    HôtelApp
                </a>
                <!-- Menu principal -->
                <div class="hidden md:flex space-x-6">
                    <a href="dashboard.jsp" class="text-gray-600 hover:text-blue-600 transition duration-300">Accueil</a>
                    <a href="rechercheHotel.jsp" class="text-gray-600 hover:text-blue-600 transition duration-300">Rechercher</a>
                    <a href="reservations.jsp" class="text-gray-600 hover:text-blue-600 transition duration-300">Réservations</a>
                </div>
            </div>
            <a href="logout.jsp" class="bg-red-500 hover:bg-red-600 text-white px-4 py-2 rounded-lg transition duration-300">
                Déconnexion
            </a>
        </div>
    </div>
</nav>

<!-- Contenu principal -->
<main class="container mx-auto px-4 py-8">
    <!-- En-tête du profil avec carte de bienvenue -->
    <div class="bg-white rounded-xl shadow-lg p-8 text-center mb-8">
        <div class="h-24 w-24 bg-gradient-to-r from-blue-600 to-purple-600 rounded-full mx-auto mb-6 flex items-center justify-center text-white text-3xl font-bold hover:scale-105 transition duration-300">
            <%= client.getNom().substring(0, 1).toUpperCase() %>
        </div>
        <h1 class="text-3xl font-bold text-gray-800 mb-2">
            <%= client.getNom() %> <%= client.getPrenom() %>
        </h1>
        <p class="text-gray-600 text-lg">
            <%= client.getEmail() %>
        </p>
    </div>

    <!-- Informations détaillées du profil -->
    <div class="bg-white rounded-xl shadow-lg p-6">
        <div class="grid grid-cols-1 md:grid-cols-2 gap-8 mb-8">
            <div class="space-y-4">
                <div>
                    <label class="text-sm font-medium text-gray-500 block mb-1">Nom</label>
                    <p class="text-gray-900 font-medium"><%= client.getNom() %></p>
                </div>

                <div>
                    <label class="text-sm font-medium text-gray-500 block mb-1">Prénom</label>
                    <p class="text-gray-900 font-medium"><%= client.getPrenom() %></p>
                </div>

                <div>
                    <label class="text-sm font-medium text-gray-500 block mb-1">Email</label>
                    <p class="text-gray-900 font-medium"><%= client.getEmail() %></p>
                </div>
            </div>

            <div class="space-y-4">
                <div>
                    <label class="text-sm font-medium text-gray-500 block mb-1">Ville</label>
                    <p class="text-gray-900 font-medium">
                        <%= client.getVille() != null ? client.getVille() : "Non renseigné" %>
                    </p>
                </div>

                <div>
                    <label class="text-sm font-medium text-gray-500 block mb-1">Adresse</label>
                    <p class="text-gray-900 font-medium">
                        <%= client.getAdresse() != null ? client.getAdresse() : "Non renseigné" %>
                    </p>
                </div>

                <div>
                    <label class="text-sm font-medium text-gray-500 block mb-1">Date de naissance</label>
                    <p class="text-gray-900 font-medium">
                        <%= client.getDate_naissance() != null ? client.getDate_naissance() : "Non renseigné" %>
                    </p>
                </div>
            </div>
        </div>

        <div class="flex justify-center">
            <button onclick="location.href='/editProfile'"
                    class="px-6 py-3 bg-gradient-to-r from-blue-600 to-purple-600 text-white font-medium rounded-lg
                               hover:from-blue-700 hover:to-purple-700 transition duration-300
                               focus:ring-4 focus:ring-blue-200">
                Modifier mon profil
            </button>
        </div>
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