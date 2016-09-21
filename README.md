# Fourmiliere
Simulation d'une fourmiliere - Java - Conception orientée objet

### Nous allons modéliser une version très simplifiée de fourmilière. Dans le but de pouvoir faire évoluer le modèle, certaines fonctionnalités resterons floues. 

 ![Simulation de 2 fourmilieres](https://github.com/crambille/Fourmiliere/blob/master/Capture.PNG)

##L'idée est la suivante :

* Une reine pond une grande quantité de fourmis soldats et a (au moins)une fourmi­chef.
* La reine donne le signal d'éclosion par phéromones.
* Les fourmis soldats parcourent le terrain à la recherche de nourriture,et la ramènent à la fourmi­chef.
* Les fourmis soldats ont une durée de vie limitée.
* Lorsque toutes les fourmis soldats sont mortes, leur fourmi chef meurt et la reine peut aller fonder une autre fourmilière.
* Chaque fois qu'une fourmi rencontre un autre combattant, un objet Combat est créé et c'est une méthode de la classe combat qui définira les règles du combat.Tout combattant doit implémenter l'interface Combattant. cette interface definira les différentes méthodes nécessaire à la mise en œuvre du combat.
* Chaque case du terrain qui pourra contenir différentes informations :nourriture, phéromone, présence d'un combattant,.




**Une classe FourmiReine**
* Elle pourra "contacter" les fourmis soldats par phéromone
* Elle a un lien avec le terrain

**Une classe FourmiChef**
* Une fourmi­chef devra connaitre sa reine, et
être capable de savoir quelle quantité de nourriture a été récoltée. De
plus elle devra savoir à tout moment le nombre de soldats morts et
vivants.

**Une classe FourmiSoldat**
* Une fourmi­soldat a une durée de vie (pas
forcément la même pour chaque fourmi), connait le terrain sur lequel
elle évolue et connait son chef. De plus une fourmi­soldat se déplace
selon des règles que l'on définira ( cela peut être aléatoirement pour le
moment). Toutes les fourmis se déplacent en même temps.

**Une classe Main pour lancer le simulateur**







**Voici la nourriture**


![](https://github.com/crambille/Fourmiliere/blob/master/src/ressources/bigmac.png) 

**Voici une fourmiliere**


![](https://github.com/crambille/Fourmiliere/blob/master/src/ressources/fourmielliere2.jpg)
