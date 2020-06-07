#importation de tensorflow va nous servir à construire le modèle
import tensorflow as tf
from tensorflow import keras
from PIL import Image
import random
import numpy as np

def creation_modele():
    """   Création du modèle avec extraction des features de l'image: 
      D'abord une couche extraction des features composé d'une succesion de:
      une couche de Convolution 2D (kernel 3x3, un stride de 1 avec une activation 'relu')
      une couche de MaxPool2D pour associer les informations de la Convolution
      un Dropout (pas obligatoire), il va permettre de réduire le Surapprentissage (Overfitting), surtout ici avec peu d'images pour l'entrainement 
      Ensuite la couche de classification: On applatie les résulats (on va avoir un seul tableau au lieu d'un tableau en 2 dim)
      On le fait passer dans une couche de 512 nodes.
      On le fait passer dans le dernière couche de nodes: 3 nodes car 3 type de class """
    model = keras.Sequential([
        keras.layers.Conv2D(16,padding='same', kernel_size=(3,3), strides=(1,1), input_shape=(200, 200 ,3),activation='relu'),
        keras.layers.MaxPool2D(),
        keras.layers.Dropout(0.2),
        keras.layers.Conv2D(32, padding='same',kernel_size=(3,3), strides=(1,1), activation='relu'),
        keras.layers.MaxPool2D(),
        keras.layers.Conv2D(64, padding='same',kernel_size=(3,3), strides=(1,1), activation='relu'),
        keras.layers.MaxPool2D(),
        keras.layers.Dropout(0.2),
        keras.layers.Flatten(),
        keras.layers.Dense(512, activation='relu'),
        keras.layers.Dense(3)
    ])
    
    #On configure le modèle avec une fonction d'optimisation et une fonction de loss
    model.compile(optimizer='adam',
                loss=tf.keras.losses.SparseCategoricalCrossentropy(from_logits=True),
                metrics=['accuracy'])

    return model


#importation des donneer : les images sont dans le dossier BANK_images, il y a aussi un fichier .cvs qui associe chaque image à sa classe
def importation_et_traitement_images(path_donnees_cvs):
    
    donnees = open(path_donnees_cvs, 'r')
    images = []
    labels = []
    if donnees:
        for ligne in donnees:
            donnee_ligne = ligne.split(";")
            img = np.array(Image.open('./BANK_images/' +donnee_ligne[0]))
            if(img.shape != (200,200,3)):
                print(donnee_ligne[0])
            images.append(img)
            labels.append(int(donnee_ligne[1]))

    #on va mélanger les images(et les labels associé) pour pouvoir après les diviser en un set d'entrainement et en un set de test
    temp = list(zip(images, labels))
    random.shuffle(temp)
    images, labels = zip(*temp)
    images = list(images)
    labels = list(labels)
    
    #normalisation des images (0, 255) entier => (0,1) réel
    for i in range(len(images)):
        images[i] = images[i] / 255.0

    return images, labels

#definition des groupes d'images servant pour l'entrainement du modèle et pour tester le modèle
def creation_train_et_test_images(pourcenatge_train = 70):
    #on prends 60% des données pour l'entrainement
    train_images = images[0: (len(images)*pourcenatge_train)//100]
    train_labels = labels[0: (len(labels)*pourcenatge_train)//100]

    #On prends les 40% restant pour tester le modèle après l'entrainement
    test_images = images[(len(images)*pourcenatge_train)//100:]
    test_labels = labels[(len(labels)*pourcenatge_train)//100:]

    return train_images, train_labels, test_images, test_labels


def train_model(model, train_images, train_labels, nb_epochs=10):
    #On converti la list d'images et la liste de labels en np.array pour pouvoir l'envoyer dans le modèle
    if(type(train_images) == list):
        train_images = np.array(train_images)
    if(type(train_labels) == list):
        train_labels = np.array(train_labels)
    #entrainement du modèle, ici, on  fait 30 epochs
    model.fit(train_images, train_labels, epochs=nb_epochs)

def test_model(model, test_images, test_labels):
    if(type(test_images) == list):
        test_images = np.array(test_images)
    if(type(test_labels) == list):
        test_labels = np.array(test_labels)
    #on calcule le loss et la précision du modèle qui vient d'être entrainer avec les donner de test
    loss, acc = model.evaluate(test_images, test_labels, verbose=2)
    return loss, acc

def sauvegarde_poids_model(model, chemin_fichier):
    #Sauvegade des poids du modèle pour pouvoir le réutiliser sans devoir le réentrainer
    model.save_weights(chemin_fichier)

def save(model, chemin):
    model.save(chemin)
    #tf.saved_model.save(model, chemin)

if(__name__ == '__main__'):
    images, labels = importation_et_traitement_images('./BANK_images/donnees.cvs')
    model = creation_modele()
    train_images, train_labels, test_images, test_labels = creation_train_et_test_images()
    train_model(model, train_images, train_labels, 30)
    loss, acc = test_model(model, test_images, test_labels)
    #Affichage de la précision
    print('\nTest accuracy:', acc)
    sauvegarde_poids_model(model, './poids_modele2/poids')










