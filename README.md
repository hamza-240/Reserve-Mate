[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)


# 🏡 ReserveMate - La nouvelle génération de réservation d'hébergements

**Plateforme communautaire développée en Java EE avec architecture MVC**  
*Conçue pour les voyageurs en quête d'authenticité et les hôtes exigeants*

![Java EE](https://img.shields.io/badge/Java_EE-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![MVC](https://img.shields.io/badge/Architecture-MVC-2C8EBB?style=for-the-badge)
![JDBC](https://img.shields.io/badge/Data-JDBC-00758F?style=for-the-badge&logo=mysql&logoColor=white)
![Tailwind](https://img.shields.io/badge/Styling-Tailwind_CSS-38B2AC?style=for-the-badge&logo=tailwind-css&logoColor=white)

## 🌟 Ce qui nous distingue

### Pour les voyageurs
- 🔍 **Moteur de recherche intelligent** (JSP + Servlets)
- 📅 **Calendrier de disponibilité** (JDBC optimisé)
- 🏷 **Système de tags avancé** (Modèle Java EE)


### Pour les hôtes
- 📊 **Dashboard MVC complet** (Servlet Controller)
- 📈 **Analytics temps réel** (JDBC + MySQL Procedures)


## 🛠 Stack Technique

```mermaid
graph TB
    A[JSP] -->|Tailwind CSS| B(Vue)
    B --> C[Servlet]
    C --> D[Service Layer]
    D --> E[DAO JDBC]
    E --> F[(MySQL)]
```

**Notre architecture MVC robuste :**
- **Modèle** : Beans Java + DAO JDBC haute performance
- **Vue** : JSP dynamiques avec Tailwind CSS pour un design responsive
- **Contrôleur** : Servlets optimisés avec routage intelligent

**Points forts techniques :**
- Pool de connexions JDBC configuré pour haute disponibilité
- Transactions ACID pour la gestion des réservations
- Système de cache applicatif (Level 2)
- Templates JSP modulaires

## 🚀 Déploiement

1. **Prérequis :**
   ```bash
   Java EE 8+
   Tomcat 9+
   MySQL 5.7+
   ```

2. **Installation :**
   ```bash
   git clone https://github.com/hamza-240/ReserveMate.git
   mvn clean package
   ```
