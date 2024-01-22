/**
 * AnnotationColorMapper.groovy
 *
 * Description:
 * This script applies specific colors to annotations in QuPath based on their names.
 * It is designed to work with different types of tissue or cell annotations, assigning each type a unique color.
 *
 * Author: Mohamed Omar, MD
 * Contact: mao4005@med.cornell.edu
 * GitHub: https://github.com/MohamedOmar2020
 * Created on: January 22, 2024
 *
 * Usage:
 * 1. Open a whole slide image with annotations imported as geojson in QuPath.
 * 2. Run this script.
 * 3. The script will update the colors of annotations based on their names and predefined color mapping.
 */

import qupath.lib.gui.QuPathGUI
import qupath.lib.objects.PathAnnotationObject
import qupath.lib.objects.PathObject
import qupath.lib.objects.PathObjects
import qupath.lib.objects.TMACoreObject
import qupath.lib.roi.interfaces.ROI
import javafx.scene.paint.Color

def viewer = QuPathGUI.getInstance().getViewer()

def imageData = viewer.getImageData()
if (imageData == null) {
    print("No image data!")
    return
}

def hierarchy = imageData.getHierarchy()
def objects = hierarchy.getFlattenedObjectList(null)

// Define type-to-color mapping
def type_info = [
    "neoplastic" : Color.RED,
    "inflammatory" : Color.GREEN,
    "connective" : Color.BLUE,
    "necrotic" : Color.YELLOW,
    "non-neoplastic" : Color.ORANGE,
    "nolabel" : Color.BLACK
]

// Helper function to convert JavaFX Color to RGB integer
def colorToRGB(Color color) {
    int r = (int)(color.getRed() * 255) & 0xFF;
    int g = (int)(color.getGreen() * 255) & 0xFF;
    int b = (int)(color.getBlue() * 255) & 0xFF;
    return (r << 16) + (g << 8) + b;
}

objects.each { PathObject pathObject -> 
    if (pathObject.isAnnotation()) {
        def name = pathObject.getName()
        if (name && type_info.containsKey(name)) {
            pathObject.setColor(colorToRGB(type_info[name]))
        }
    }
}

viewer.repaint()
