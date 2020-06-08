import tensorflow as tf
from tensorflow import keras
from PIL import Image
import json
import numpy as np
import argparse

#import du modele depuis modele.py
from model import creation_modele


#Definition nom des classes (ici 0= bouteille en plastique par ex)
CLASS_NAME = ['bouteille en plastique', 'sac en plastique', 'canette']

my_devices = tf.config.experimental.list_physical_devices(device_type='CPU')
tf.config.experimental.set_visible_devices(devices= my_devices, device_type='CPU')

def classifier_image(path_image):
    model = creation_modele()
    model.load_weights(args.path_poids)
    probability_model = tf.keras.Sequential([model, 
                                             tf.keras.layers.Softmax()])
    test_images = Image.open(path_image)
    test_images = test_images.resize((200,200))
    test_images = np.array(test_images)
    test_images = test_images / 255.0
    test_images = (np.expand_dims(test_images,0))
    predictions = probability_model.predict(test_images)
    #on met en forme les informations reçu
    jsonObject = {
        'image' : path_image, 
        'prediction': str(np.argmax(predictions)), 
        'prediction_propabilite' : {
            'c_0': str(round(predictions[0][0], 2)),
            'c_1': str(round(predictions[0][1], 2)),
            'c_2': str(round(predictions[0][2], 2))
        }
    }
    # print("Prediction: " + str(predictions))
    # print("Classe: " + CLASS_NAME[np.argmax(predictions)])
    return jsonObject


if (__name__ == '__main__'):
    #creation du parser pour les arguments
    parser = argparse.ArgumentParser()

    #ajout d'arguments au parser
    parser.add_argument('path_image', help="Chemin de l'image à tester")
    parser.add_argument('path_poids')
    parser.add_argument('path_json')

    #parser les argument
    args = parser.parse_args()
    #classifier_image('test_image_prediction/c.jpg')
    jsonO = classifier_image(args.path_image)
    #création du fichier json
    with open(args.path_json+"/prediction.json", "w") as file:
        json.dump(jsonO, file)