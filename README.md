# SPRING

# Exercice Spring C

## Partie A :

Réaliser une application Spring MVC offrant la visualisation d'une collection d'objets Java de classe Contact (avec au moins 3 champs) et ayant les routes suivantes :
- /: Page d'accueil
- /contacts: Le listing des contacts
- /contacts/{contactId}: Les détails d'un contact
- /contacts/add: L'ajout d'un contact

Le visuel devra également être travaillé, idéalement via l'utilisation de la librairie Bootstrap incluse dans les différentes pages via l'utilisations des fragments Thymeleaf.

Les couches de l'application seront découpées en packages, et les erreurs devront aussi être gérées via des pages dédiés.

## Partie B :

Etendre l'application pour permettre de réaliser un CRUD complet, soit avec une API *ou* une interface web.

## Partie C :

Ajouter à l'application la couche de données via l'utilisation de Spring Data JPA ainsi que d'une base de données H2. Le mapping des DTO vers les entités sera réalisé via 
MapStruct. 

L'application devra désormais comprendre également, via un paramètre de requête optionnel, la capacité à faire une recherche par valeur textuelle débutant par XXX
