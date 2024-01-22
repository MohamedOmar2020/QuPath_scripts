# QuPath Helper Scripts

## Overview
This repository contains a collection of Groovy scripts designed to enhance and automate various tasks within QuPath, a powerful open-source software for bioimage analysis, particularly in digital pathology. These scripts are intended to help pathologists and researchers efficiently process and analyze whole slide images (WSIs).

## Scripts Included
- `CSVtoAnnotations.groovy`: Reads coordinates from a CSV file and creates annotations in QuPath at those locations.
- `AnnotationColorMapper.groovy`: Applies specific colors to annotations based on their names.
- `CellTypeMaskGenerator.groovy`: Generates RGB masks from annotated cell types in WSIs, encoding them in the red channel of an RGB mask.
- (Additional script descriptions as needed)

## Prerequisites
- [QuPath](https://qupath.github.io/) (Version 0.4.3)

## Installation
Clone or download this repository to your local machine.

## Usage
Each script in this repository is designed to be run within QuPath's script editor. To use a script:
1. Open QuPath and load a WSI.
2. Open the script editor in QuPath (Automate -> Show script editor).
3. Copy and paste the desired script from this repository into the script editor.
4. Execute the script.

## Contributing
Contributions to these scripts, including improvements, bug fixes, or additional utility scripts, are welcome. Please feel free to submit pull requests or open issues for discussion.

## License
[MIT License](LICENSE) - Feel free to use, modify, and distribute these scripts as needed.

## Contact
- Author: Mohamed Omar, MD
- Email: mao4005@med.cornell.edu
- GitHub: https://github.com/MohamedOmar2020
