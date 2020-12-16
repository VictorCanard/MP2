Mode multi-joueur: par défaut le mode multijoueur est activé,
ceci s'enlève avec la modification du boolean isMultiplayer dans
la classe SuperPacman.java. Les deux joueurs s'entraident et ont
chacun une barre de vie et un score ( le score affiché à la fin du
jeu et la somme de ces deux scores ); ils peuvent traverser les
portes ensemble ou séparément. L'un se contrôle avec les touches
WSAD, l'autre avec les flèches du clavier.

Pause et fin de jeu: le jeu se met en pause avec l'appui de la touche tab
qui en fait stoppe les super.update de la classe Superpacman.java;
quand un des joueurs n'a plus de vie, le jeu s'arrête et le score total s'affiche.
Un écran d'accueil a été élaboré mais à cause d'un problème de boucle infinie
la ligne initialisant cet écran a été mise en commentaire
( elle se trouve dans la methode begin() de SuperPacman.java)