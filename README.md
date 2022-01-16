# Jeu de Space invaders

## Lancement du jeu

Pour lancer le jeu, il faut commencer par construire le projet (Build project) puis
executer la classe HelloApplication. Une fois fait, vous devriez voir une fenêtre 
apparaître. Elle vous invitera à commencer une partie.

## Structure de l'application

### Package java.com.example.demo2

Ce package contient toute la partie logique de l'application. Dedans, on y a trouve les 2
classes HelloApplication et Game, qui respectivement font office de page d'accueil et 
de plateforme de jeu. Également, il est constitué des répertoires : 

 - "models" qui contient les composants du jeu principalement le vaisseau et les cubes.
 - "thread" contenant tous les threads utiles pour vérifier la position des cubes ou encore
les éventuelles collisions des missiles lancés.


### Répertoire resources

Ce dossier contient toutes les vues FXML, les images notamment celle du vaisseau ainsi 
qu'une feuille de styles CSS.
 