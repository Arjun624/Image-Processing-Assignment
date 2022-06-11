Image Program created by Arjun and Noam!

OVERVIEW:
This program is an application that allows a user to load a ppm file preform some basic operations on the image.
The application can vertically flip, horizontally flip, brighten, dim, and create a red, green, blue, luma, value, and intensity greyscale.
The application can also save the edited image to a new ppm file on your machine.

DESIGN:
We used a model, view, and controller to create the application. The model is the ImageModel class, which is responsible for loading the image, and performing the operations on the image.
The model implements the ImageEditor interface, which contains the methods responsible for performing the operations on the image.
We use a hashmap to store the images, with the key being the string name of the image and the value being a 2D array of pixels repressing the image.
The view is the ImageDisplay class, which is responsible for displaying informational text during the program. The display renders messages to the specified appendable.
The ImageDisplay class implements the ImageView interface, which contains the methods responsible for displaying information.
The controller is the ImageControllerImpl class, which is responsible for handling the user input. The ImageControllerImpl class is the bridge
between the model and the view. The controller implements the ImageController interface,which contains the methods responsible for handling the user input. The controller uses a hashmap of commands to perform the operations on the image.
We also decided to use the command design pattern, so it's easy to add extra commands to our application. The command pattern is used to create a command object,
which is responsible for performing a single specified the operation on the image. The command object implements the ImageCommands interface, which contains the methods responsible for executing the operation.
Each command is a separate class, which implements the ImageCommands interface. We add all the valid commands to the hashmap,
and then we can use the user input to execute the chosen command.

INTERFACES:
-ImageEditor: This interface contains the methods responsible for editing an image.
-ImageView: This interface contains the methods responsible for displaying helpful information to the user during the program.
-ImageController: This interface contains the methods responsible for handling the user input.
-ImageCommands: This interface contains the methods responsible for executing the editing operation on the image based on the user input.

CLASSES:
-ImageModel: This class is responsible for loading the image, saving the image, and performing the operations on the image.
-Pixel: This class represents a singular pixel which is used to create a 2D array of pixels. A pixel is represented by a red, green, and blue value.
-ImageDisplay: This class is responsible for displaying helpful information to the user during the program.
-ImageControllerImpl: This class is responsible for handling the user input and connecting the model and view to successfully run the program.
-ImageProgram: This is the main class of the program, which contains the main method.
**ACTION COMMAND CLASSES**
-AdjustBrightness: This class is responsible for performing the brightness operation on the image based on the inputted increment. The input is negative if the image is to be dimmed, and positive if the image is to be brightened.
-BlueGreyscale: This class is responsible for creating a blue greyscale image.
-GreenGreyscale: This class is responsible for creating a green greyscale image.
-HorizontalFlip: This class is responsible for performing the horizontal flip operation on the image.
-IntensityGreyscale: This class is responsible for creating an intensity greyscale image.
-LoadImage: This class is responsible for loading an image from the local machine to preform operations on.
-LumaGreyscale: This class is responsible for creating a luma greyscale image.
-Quit: This class is responsible for quitting the program.
-RedGreyscale: This class is responsible for creating a red greyscale image.
-SaveImage: This class is responsible for saving the image to the local machine.
-ValueGreyscale: This class is responsible for creating a value greyscale image.
-VerticalFlip: This class is responsible for performing the vertical flip operation on the image.

RUNNING THE PROGRAM AND VALID INPUT:
-To run the program, go to the ImageProgram class and run the main method.
-No command line arguments are required to run the program.

**ALL INPUTS ARE CASE SENSITIVE**
Ex: 'Load' is not the same as load. If you give an image the name 'example' and another image the
name 'Example', the program recognize the two images as different.

Valid inputs:
(1) 'load' <filepath> <filename>
    -This command loads an image from the local machine.
    -<filepath> is the path to the file.
    -<filename> is the name that you want to use to refer to the image in the program.

    Example: 'load /home/user/example.ppm ex'
    -Loads the image example.ppm from the local machine, which can be referred to as 'ex' in the program.


(2) 'save' <filepath> <filename>
    -This command saves the image to the local machine.
    -<filepath> is the path to the new file that is going to be created.
    -<filename> is the name of the file that you want to save.

    Example: 'save /home/user/newExample.ppm ex'
    -Saves the image ex to the local machine, which can is called newExample.ppm on the local machine.

(3) 'adjust-brightness' <increment> <filename> <new filename>
    -This command adjusts the brightness of the image.
    -<increment> is the amount of brightness you want to adjust the image by. Inputted as a negative number if you want to dim the image, and as a positive number if you want to brighten the image.
    -<filename> is the name of the image that you want to adjust the brightness of.
    -<new filename> is the name of the new image that was brightened or dimmed.

    Example: 'adjust-brightness -50 ex dimmedEx'
    -Adjusts the brightness of the image ex by -50, and names the image as dimmedEx.

(4) 'vertical-flip' <filename> <new filename>
    -This command flips the image vertically.
    -<filename> is the name of the image that you want to flip vertically.
    -<new filename> is the name of the new image that was flipped vertically.

    Example: 'vertical-flip ex vFlippedEx'
    -Flips the image ex vertically, and names the image as vFlippedEx.

(5) 'horizontal-flip' <filename> <new filename>
    -This command flips the image horizontally.
    -<filename> is the name of the image that you want to flip horizontally.
    -<new filename> is the name of the new image that was flipped horizontally.

    Example: 'horizontal-flip ex hFlippedEx'
    -Flips the image ex horizontally, and names the image as hFlippedEx.

(6) 'greyscale-red' <filename> <new filename>
    -This command creates a red greyscale image.
    -<filename> is the name of the image that you want to create a red greyscale image of.
    -<new filename> is the name of the new image that was created as a red greyscale image.

    Example: 'greyscale-red ex redGreyscaleEx'
    -Creates a red greyscale image of the image ex, and names the image as redGreyscaleEx.

(7) 'greyscale-green' <filename> <new filename>
    -This command creates a green greyscale image.
    -<filename> is the name of the image that you want to create a green greyscale image of.
    -<new filename> is the name of the new image that was created as a green greyscale image.

    Example: 'greyscale-green ex greenGreyscaleEx'
    -Creates a green greyscale image of the image ex, and names the image as greenGreyscaleEx.

(8) 'greyscale-blue' <filename> <new filename>
    -This command creates a blue greyscale image.
    -<filename> is the name of the image that you want to create a blue greyscale image of.
    -<new filename> is the name of the new image that was created as a blue greyscale image.

    Example: 'greyscale-blue ex blueGreyscaleEx'
    -Creates a blue greyscale image of the image ex, and names the image as blueGreyscaleEx.

(9) 'luma' <filename> <new filename>
    -This command creates a luma greyscale image.
    -<filename> is the name of the image that you want to create a luma greyscale image of.
    -<new filename> is the name of the new image that was created as a luma greyscale image.

    Example: 'luma ex lumaGreyscaleEx'
    -Creates a luma greyscale image of the image ex, and names the image as lumaGreyscaleEx.

(10) 'intensity' <filename> <new filename>
    -This command creates an intensity greyscale image.
    -<filename> is the name of the image that you want to create an intensity greyscale image of.
    -<new filename> is the name of the new image that was created as an intensity greyscale image.

    Example: 'intensity ex intensityGreyscaleEx'
    -Creates an intensity greyscale image of the image ex, and names the image as intensityGreyscaleEx.

(11) 'value' <filename> <new filename>
    -This command creates a value greyscale image.
    -<filename> is the name of the image that you want to create a value greyscale image of.
    -<new filename> is the name of the new image that was created as a value greyscale image.

    Example: 'value ex valueGreyscaleEx'
    -Creates a value greyscale image of the image ex, and names the image as valueGreyscaleEx.

(12) 'q' to quit
    -This command quits the program.
    -You cannot quit in the middle of a command, and can only quit before or after executing a command.

Inputs can be separated by spaces or by new lines:

For example: (1) 'load /home/user/example.ppm ex'
             (2) 'load'
                 '/home/user/example.ppm'
                 'ex'
(1) and (2) are both valid inputs and are allowed.


EXCEPTIONS AND ERRORS:

**All exceptions are handled by the program, and will not cause the program to crash.**
The program will print out the error message that caused the exception, and then ask the user to re-enter the command.

-If you give an invalid command, the program will print an error message.
-If you try to load an image that does not exist, the program will print an error message.
-If you try to save an image that does not exist, the program will print an error message.
-If you try to edit an image that hasn't been loaded, the program will print an error message.

**If you try to save an image that already exists, the program will OVERWRITE the old image with the new image.**

SCRIPT:

Vertically flip an image and save it:
load res/colors.ppm colors
vertical-flip colors vFlippedColors
save res/colors-vertical.ppm vFlippedColors

Refer to the full script found in the Script.txt file located in the res directory.


