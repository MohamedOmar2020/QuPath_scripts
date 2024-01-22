/**
 * CSVtoAnnotations.groovy
 *
 * Description:
 * This script reads coordinates from a CSV file and creates annotations in QuPath at the specified locations.
 * The script assumes the CSV file contains coordinates in its second and third columns.
 *
 * Author: Mohamed Omar, MD
 * Contact: mao4005@med.cornell.edu
 * GitHub: https://github.com/MohamedOmar2020
 * Created on: January 22, 2024
 *
 * Usage:
 * 1. Open a whole slide image in QuPath.
 * 2. Run this script.
 * 3. Select the CSV file with the coordinates when prompted.
 * 4. Annotations will be created on the image based on the coordinates in the CSV file.
 */


import java.io.BufferedReader;
import java.io.FileReader;
import qupath.lib.objects.PathAnnotationObject;
import qupath.lib.roi.PointsROI;
import qupath.lib.gui.dialogs.Dialogs

def imageData = getCurrentImageData();

// Get location of csv
def file = Dialogs.promptForFile(null)


// Create BufferedReader
def csvReader = new BufferedReader(new FileReader(file));


int sizePixels = 1000
row = csvReader.readLine() // first row (header)

// Loop through all the rows of the CSV file.
while ((row = csvReader.readLine()) != null) {
    def rowContent = row.split(",")
    double cx = rowContent[1] as double;
    double cy = rowContent[2] as double;

    // Create annotation
    def roi = new PointsROI(cx-4120,cy-2945);
    def annotation = new PathAnnotationObject(roi, PathClassFactory.getPathClass("Region"));
    imageData.getHierarchy().addObject(annotation, true);
}
