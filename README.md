# -Gestion_Billetterie_threads
# Système de Gestion de Billetterie

## Description
Ce projet est un système de gestion de billetterie pour un théâtre, développé en utilisant Spring Boot pour le backend et Angular pour le frontend. Le système permet aux utilisateurs de réserver et d'annuler des billets, avec une synchronisation pour gérer l'accès concurrentiel.

## Fonctionnalités
- **Réservation de Billets** : Permet à plusieurs utilisateurs (threads) de réserver des billets simultanément.
- **Annulation de Billets** : Permet aux utilisateurs d'annuler des billets, rendant les billets à nouveau disponibles.
- **Affichage de l'État des Réservations** : Interface graphique montrant les sièges réservés, libres, etc.

## Choix de Synchronisation
Pour assurer la synchronisation des données lors des opérations concurrentes de réservation et d'annulation, nous utilisons des mécanismes de synchronisation tels que les blocs `synchronized` en Java. Ces mécanismes garantissent que les opérations critiques sont exécutées de manière atomique, évitant ainsi les conflits d'accès aux données.

## Installation
1. Clonez le dépôt :
    ```bash
    git clone https://github.com/votreutilisateur/gestion-billetterie.git
    cd gestion-billetterie
    ```
2. Exécutez le backend Spring Boot :
    ```bash
    ./mvnw spring-boot:run
    ```
3. Exécutez le frontend Angular :
    ```bash
    cd frontend
    ng serve
    ```

## Utilisation
- Accédez à l'interface utilisateur à `http://localhost:4200`.
- Utilisez les boutons pour réserver et annuler des billets.
- Vérifiez l'état des réservations en temps réel via l'interface graphique.

## Contribuer
Les contributions sont les bienvenues ! Veuillez ouvrir une issue ou soumettre une pull request pour toute suggestion ou amélioration.

## Licence
Ce projet est sous licence MIT. Voir le fichier [LICENSE](LICENSE) pour plus de détails.
