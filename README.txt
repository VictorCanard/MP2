MultiplayerMode: By default the multiplayer mode is activated; this can be changed by affecting the value false to the boolean isMultiplayer.
The two players help each other, can pass doors together or alone and both have a status GUI.
The final score displayed at the end is the sum of both of the players's scores.
One is controlled with WSAD keys while the other uses the keyboard's arrows.

Start of Game was attempted but the while loop lasts forever as it can't detect the player pressing enter to start the game.
Pause of game is toggled through the use of the TAB key; this stops the super.update(deltaTime) and effectively freezes the game.
End of Game when one of the players has no more life; total score is displayed as well as gameover.

Pacman moves quicker when invulnerable and even quicker when he persists in a given direction (same key pressed continuously).
The ghosts move quicker as well when they are afraid.

Teleporters are located in some corners of Level1 and Level2 and they simply bring the player who walks onto them to another corner of the map.
