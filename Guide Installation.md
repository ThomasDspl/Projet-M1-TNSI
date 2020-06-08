# Ce guide permet d'installer le back + le front sur une machine linux

# Prérequis
Pour faire fonctionner ce projet, il vous faudra :
- une machine sous Linux (ubuntu, etc.)
- un serveur Web installé (Apache2) **en écoute sur un autre port que 8080** !
- un serveur mysql avec un utilisateur test avec comme mdp test 
- Tomcat 9 installé et en écoute sur le port 8080
- Python 3.7 ou 3.8
- Tensorflow et Pillow d'installés (```pip3 install Pillow``` et ```pip3 install Tensorflow```)

# Etape 1 : la base de donnée
- Il vous faut créer une base de donnée nommé bdd_projet avec les tables conrespondante : user, image, statistique (voir schéma de la base). (_Un .sql est fournit avec quelques utilisateurs déjà présent._)

# Etape 2 : le site web
- Si vous avez installé Apache2, récupérer le contenue du dossier **DIST** du projet (ou construisez le à partir du dossier **site-classification** avec la commande ```npm run-script build``` quand vous êtes dans le dossier) puis le mettre dans le dossier ***www/html** d'apache2 (sur Ubuntu ```/var/www/html```). 

- Ensuite, donner les droits d'accèes en lecture/écriture/exécution à tous le dossier avec la commande ```sudo chmod -u+rwx /var/www/html/*``` (sous Ubuntu). 

# Etape 3 : le back
- Pour cela, il faut telécharger le fichier **API.war** est le dézipper dans un dossier API que vous aurez créer au préalable. 
- Il vous faut aussi télécharger le dossier **Modele_IA** et créer un autre dossier **Images**.
- Ensuite, il faut modifier le fichier **conf.json** dans ```API/WEB-INF/classes```. Il vous faut mettre les chemins 
    - de votre dossier **Modele_IA** (sous la forme ```/path/to/Modele_IA/```, le dernier **/** est **important**), 
    - de votre dossier **Image** (sous la forme ```/path/to/Images/```, le dernier **/** est **important**)
    - de votre installation python3 (ex: ```/usr/bin/python3``` sous Ubuntu)
- Il faut alors déplacer le dossier **API** dans le dossier **webapps** de Tomcat9.

# Etape 4 : Mise en route
- Lancer le service mysql
- Lancer le service Apache2
- Lancer la commande ```sudo ./catalina.sh run``` (le fichier se trouve dans le dossier **bin** de Tomcat9)
- Aller sur http://localhost:[PORT_D'ECOUTE_APACHE2]/
- Tester
