# Projet-M1-TNSI DetriClass

Ce projet a pour but de fournir un front et un back pour classifier des objet (de type ordure). Il permettera d'avoir des statistiques sur le nombre de détritut classé.

Ce projet se compose de 3 grandes parties : le **Front**, le **Back** et le **Modele de classification d'image**

___

# Front
Cette partie est composé de 2 application : un site web en [Vue.js](https://vuejs.org/index.html) et une application Android.

## Site Web:
![gif site_web](https://nsa40.casimages.com/img/2020/06/08/200608040737965074.gif)

## Application mobile:
![gif app](https://nsa40.casimages.com/img/2020/06/09/2006091228521269.gif)

(_version pre-alpha_)

___

# Back

Le back ici est une application JEE qui va proposer une API REST qui va permettre de se connecter, de récupérer les informations etc. Elle permet aussi de récupérer une image pour l'analyser avec le modèle.

___

# Modèle
Le modèle est créer à partie de [Tensorflow](http://tensorflow.org/). C'est un modèle avec d'abord une couche d'extraction des caractéristique de l'objet avant de faire la classification.
![image_modele](https://nsa40.casimages.com/img/2020/06/08/200608042048189473.png) 
