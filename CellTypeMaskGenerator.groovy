/**
 * CellTypeMaskGenerator.groovy
 *
 * Description:
 * This script generates RGB masks from annotated cell types in whole slide images (WSIs)
 * using QuPath. Annotations for different cell types are encoded in the red channel of an RGB mask.
 *
 * Author: Mohamed Omar, MD
 * Contact: mao4005@med.cornell.edu
 * GitHub: https://github.com/MohamedOmar2020
 * Created on: January 22, 2024
 *
 * Usage:
 * 1. Open your annotated WSI in QuPath.
 * 2. Open the script editor in QuPath (Automate -> Show script editor).
 * 3. Copy and paste this script into the script editor.
 * 4. Run the script to generate the mask. The mask will be saved in the specified output directory.
 */

import qupath.lib.objects.PathAnnotationObject
import qupath.lib.regions.RegionRequest
import qupath.lib.gui.scripting.QPEx
import java.awt.Color
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

// Get current image data and server
def imageData = QPEx.getCurrentImageData()
def server = imageData.getServer()

// Define the path classes and corresponding pixel values
def pathClasses = ['Background', 'Megakaryocytes', 'Neutrophils', 'RBCs'] // Add other classes as needed
def pixelValues = [0, 1, 2, 3] // Corresponding pixel values for classes

// Create an empty RGB mask image
def maskImage = new BufferedImage(server.getWidth(), server.getHeight(), BufferedImage.TYPE_INT_RGB)

// Process each annotation
for (PathAnnotationObject pathObject : imageData.getHierarchy().getAnnotationObjects()) {
    def roi = pathObject.getROI()
    def pathClass = pathObject.getPathClass()
    def pixelValue = pixelValues[pathClasses.indexOf(pathClass)]

    // Create graphics to draw on the mask
    def g2d = maskImage.createGraphics()
    g2d.setColor(new Color(pixelValue, 0, 0, 255)) // Set only the red channel
    g2d.fill(roi.getShape())
    g2d.dispose()
}

// Define the output path and file
def outputPath = buildFilePath(PROJECT_BASE_DIR, "masks")
new File(outputPath).mkdirs() // Create the directory if it doesn't exist
def outputFile = new File(outputPath, server.getMetadata().getName() + "_mask.png")

// Save the mask image using ImageIO
if (!ImageIO.write(maskImage, "PNG", outputFile)) {
    println("Error: Unable to write mask image to file.")
}
