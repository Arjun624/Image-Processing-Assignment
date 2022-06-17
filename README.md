_Image Program created by Arjun and Noam!_

# Image Processing Overview:

This program is an application that allows a user to load a file, preform some basic operations on the image, and save the image to a new file.

**The following image types are supported:**
- .ppm
- .tif
- .jpg 
- .tiff 
- .bmp 
- .gif 
- .png 
- .jpeg

**The following image operations are supported:**
*You must select an image to **load** before you can preform any operations on it.*
- **Grayscale**: Converts the image to grayscale.
- **Vertical Flip**: Flips the image vertically.
- **Horizontal Flip**: Flips the image horizontally.
- **Adjust Brightness**: Adjusts the brightness of the image.
- **Red Greyscale**: Converts the image to red grayscale.
- **Green Greyscale**: Converts the image to green grayscale.
- **Blue Greyscale**: Converts the image to blue grayscale.
- **Intensity Greyscale**: Converts the image to intensity grayscale.
- **Value Greyscale**: Converts the image to value grayscale.
- **Luma Greyscale**: Converts the image to luma grayscale.
- **Sepia**: Converts the image to sepia.
- **Blur**: Blurs the image.
- **Sharpen**: Sharps the image.
- **Save**: Saves the image to a new file.


## DESIGN:

**MVC Design:**
- We used a model, view, and controller design to create the application.
- MODEL:
  - The model is the ImageModel class, which is responsible for loading the image, and performing the operations on the image.
  - The model implements the ImageEditor interface, which contains the methods responsible for performing the operations on the image.
  - We use a hashmap to store the images, with the key being the string name of the image and the value being a 2D array of pixels repressing the image.
- VIEW:
  - The view is the ImageDisplay class, which is responsible for displaying informational text during the program. 
  - The display renders messages to the specified appendable.
  - The ImageDisplay class implements the ImageView interface, which contains the methods responsible for displaying information.
- CONTROLLER:
  - The controller is the ImageControllerImpl class, which is responsible for handling the user input. The ImageControllerImpl class is the bridge between the model and the view. 
  - The controller implements the ImageController interface, which contains the methods responsible for handling the user input. 
  - The controller uses a hashmap of valid commands to perform the specified operations on the image.
  - The controller is also responsible for loading the image, and saving the image.

**Command Design:**
  - We also decided to use the command design pattern, so it's easy to add extra commands to our application. 
  - The command pattern is used to create a command object, which is responsible for performing a single specified the operation on the image. 
  - The command object implements the ImageCommands interface, which contains the methods responsible for executing the operation.
  - Each command is a separate class, which implements the ImageCommands interface. 
  - We add all the valid commands to the hashmap, and then we can use the user input to execute the chosen command.

## INTERFACES:

- **ImageEditor:** This interface contains the methods responsible for editing an image.
- **ImageView:** This interface contains the methods responsible for displaying helpful information to the user during the program.
- **ImageController:** This interface contains the methods responsible for handling the user input.
- **ImageCommands:** This interface contains the methods responsible for executing the editing operation on the image based on the user input.

## CLASSES:
- **ImageModel:** This class is responsible for loading the image, saving the image, and performing the operations on the image.
- **Pixel:** This class represents a singular pixel which is used to create a 2D array of pixels. A pixel is represented by a red, green, and blue value.
- **ImageDisplay:** This class is responsible for displaying helpful information to the user during the program.
- **ImageControllerImpl:** This class is responsible for handling the user input and connecting the model and view to successfully run the program.
- **ImageProgram:** This is the main class of the program, which contains the main method.

#### ACTION COMMAND CLASSES
- **AdjustBrightness:** This class is responsible for performing the brightness operation on the image based on the inputted increment. The input is negative if the image is to be dimmed, and positive if the image is to be brightened.
- **BlueGreyscale:** This class is responsible for creating a blue greyscale image.
- **GreenGreyscale:** This class is responsible for creating a green greyscale image.
- **HorizontalFlip:** This class is responsible for performing the horizontal flip operation on the image.
- **IntensityGreyscale:** This class is responsible for creating an intensity greyscale image.
- **LoadImage:** This class is responsible for loading an image from the local machine to preform operations on.
- **LumaGreyscale:** This class is responsible for creating a luma greyscale image.
- **Quit:** This class is responsible for quitting the program.
- **RedGreyscale:** This class is responsible for creating a red greyscale image.
- **SaveImage:** This class is responsible for saving the image to the local machine.
- **ValueGreyscale:** This class is responsible for creating a value greyscale image.
- **VerticalFlip:** This class is responsible for performing the vertical flip operation on the image.
- **Sepia:** This class is responsible for creating a sepia image, with a yellowish tone.
- **Blur:** This class is responsible for creating a blurred image.
- **Sharpen:** This class is responsible for creating a sharpened image, with more contrast.
- **Grayscale:** This class is responsible for creating a general grayscale image.

## EXCEPTIONS AND ERRORS:

**All exceptions are handled by the program, and will not cause the program to crash.**
*The program will print out the error message that caused the exception, and then ask the user to re-enter the command.*

 - If you give an invalid command, the program will print an error message.
 - If you try to load an image that does not exist, the program will print an error message.
 - If you try to save an image that does not exist, the program will print an error message.
 - If you try to edit an image that hasn't been loaded, the program will print an error message.

*If you try to save an image that already exists, the program will **OVERWRITE** the old image with the new image.*

## IMAGE CITATION:

We created our own 3x3 .ppm image for testing purposes.

