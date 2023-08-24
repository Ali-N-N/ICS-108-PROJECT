package com.example.thegame;

import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import javafx.animation.KeyValue;
import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.*;
import javafx.scene.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;


public class TheGame extends Application {
    // Define the speed of the falling objects, the current score, the current round, and the number of clicks
    private double ballSpeed = 10;
    private int points = 0;
    private int roundCounter = 1;
    private int clickCount;
    private int birdsCaught = 0;

    // Create an ArrayList to store the falling objects
    private ArrayList<ImageView> objects = new ArrayList<>();

    // Create UI elements for the game, including text, buttons, and a pane to hold everything
    Text Scores = new Text("Score :" + points);
    Text Round = new Text("Current Round:" + roundCounter);
    StackPane pane = new StackPane();
    Scene scene = new Scene(pane, 500, 500);
    Text gameOver = new Text("!! Round Lost !! ");
    Button retryButton = new Button("Oops!! Try Again ?");
    Button startGame;
    Button showScore = new Button("Show Score");
    Button exit = new Button("Exit the Game ");
    Button nextRound = new Button("Next Round ?");
    Button playAgain = new Button("Want to Play Again ?");
    Text roundFinished = new Text("!! Round Won !!");
    Color white = Color.WHITE;
    Font font = Font.font("Arial", FontWeight.BOLD, 20);
    Font font1 = Font.font("Arial", FontWeight.BOLD, 15);

    // Define an array of filenames for the falling objects
    String[] fallingObject = {"O1.jpg", "O2.jpg", "O3.png","O1.jpg", "O2.jpg", "O3.png","O1.jpg", "O2.jpg", "O3.png","O1.jpg", "O2.jpg", "O3.png","O1.jpg", "O2.jpg", "O3.png","O1.jpg", "O2.jpg", "O3.png","O1.jpg", "O2.jpg", "O3.png","O1.jpg", "O2.jpg", "O3.png","O1.jpg", "O2.jpg", "O3.png","O1.jpg", "O2.jpg", "O3.png"};
    // Create UI elements for end of game scenarios
    Text gameEnd = new Text("!! Game Ended !! ");
    private int noOfObjects = fallingObject.length;
    private int clickLeft = 30;
    Text remain = new Text("Birds Left: " + (clickLeft));
    Text clicked = new Text("Birds Caught: " + (birdsCaught));

    // Create an ArrayList to store the player's scores
    private List<Integer> scoresList = new ArrayList<>();

    @Override
    public void start(Stage stage) throws IOException {
        // Set the font and color for various UI elements
        Scores.setFont(font);
        gameOver.setFont(new Font(30));
        Round.setFont(font);
        roundFinished.setFill(white);
        roundFinished.setFont(new Font(30));
        remain.setFont(font);
        clicked.setFont(font);
        remain.setFill(white);
        clicked.setFill(white);
        Scores.setFill(white);
        gameOver.setFill(white);
        Round.setFill(white);

        // Set the style for the retry, exit, show score, next round, and play again buttons
        retryButton.setStyle("-fx-background-color: white; -fx-background-radius: 20px; -fx-background-insets: 0px; -fx-text-fill: black; -fx-font-size: 16px; -fx-padding: 10px 20px;");
        exit.setStyle("-fx-background-color: white; -fx-background-radius: 20px; -fx-background-insets: 0px; -fx-text-fill: black; -fx-font-size: 16px; -fx-padding: 10px 20px;");
        showScore.setStyle("-fx-background-color: white; -fx-background-radius: 20px; -fx-background-insets: 0px; -fx-text-fill: black; -fx-font-size: 16px; -fx-padding: 10px 20px;");
        nextRound.setStyle("-fx-background-color: white; -fx-background-radius: 20px; -fx-background-insets: 0px; -fx-text-fill: black; -fx-font-size: 16px; -fx-padding: 10px 20px;");
        playAgain.setStyle("-fx-background-color: white; -fx-background-radius: 20px; -fx-background-insets: 0px; -fx-text-fill: black; -fx-font-size: 16px; -fx-padding: 10px 20px;");

        // Set the font for the retry, exit, next round, show score, and play again buttons
        retryButton.setFont(font);
        exit.setFont(font);
        nextRound.setFont(font);
        showScore.setFont(font);
        playAgain.setFont(font);


// Create a drop shadow effect to apply to the buttons
        DropShadow dropShadow = new DropShadow();
        dropShadow.setOffsetX(2.0);
        dropShadow.setOffsetY(2.0);
        dropShadow.setColor(Color.color(0.4, 0.5, 0.5));

// Create a glow effect to apply when the buttons are hovered over
        Glow glow = new Glow();
        glow.setLevel(0.7);

// Add the effects to the buttons
        exit.setEffect(dropShadow);
        retryButton.setEffect(dropShadow);
        playAgain.setEffect(dropShadow);
        showScore.setEffect(dropShadow);
        nextRound.setEffect(dropShadow);

// Add event handlers to the buttons to apply  click effects
        exit.setOnMouseEntered(event -> {
            exit.setEffect(glow);
        });
        exit.setOnMouseExited(event -> {
            exit.setEffect(dropShadow);
        });

        retryButton.setOnMouseEntered(event -> {
            retryButton.setEffect(glow);
        });
        retryButton.setOnMouseExited(event -> {
            retryButton.setEffect(dropShadow);
        });

        playAgain.setOnMouseEntered(event -> {
            playAgain.setEffect(glow);
        });
        playAgain.setOnMouseExited(event -> {
            playAgain.setEffect(dropShadow);
        });

        showScore.setOnMouseEntered(event -> {
            showScore.setEffect(glow);
        });
        showScore.setOnMouseExited(event -> {
            showScore.setEffect(dropShadow);
        });

        nextRound.setOnMouseEntered(event -> {
            nextRound.setEffect(glow);
        });
        nextRound.setOnMouseExited(event -> {
            nextRound.setEffect(dropShadow);
        });


        // Create the "start game" button and add it to the pane
        Image BackGroundPlayGame = getImage("mainMenu.jpg");
        Text text1 = new Text("Red bird has 2 points");
        Text text2 = new Text("Pink bird has 3 points");
        Text text3 = new Text("Green pig has 5 points");

        text1.setFill(Color.RED);
        text1.setFont(font1);

        text2.setFill(Color.PINK);
        text2.setFont(font1);

        text3.setFill(Color.YELLOWGREEN);
        text3.setFont(font1);



        ImageView startPlay = new ImageView(BackGroundPlayGame);
        startPlay.setFitWidth(500);
        startPlay.setFitHeight(500);
        startGame = new Button("", startPlay);
        pane.getChildren().addAll(startGame);
        pane.getChildren().addAll(text1,text2,text3);
        pane.setAlignment(text1,Pos.BOTTOM_CENTER);
        pane.setAlignment(text2,Pos.BOTTOM_LEFT);
        pane.setAlignment(text3,Pos.BOTTOM_RIGHT);

        // Add event listener for when the "start game" button is clicked
        startGame.setOnMouseClicked(event -> {
            startGame();
        });

        // Set up the stage and display it
        stage.setTitle("Speed Click Game!");
        stage.setScene(scene);
        stage.initStyle(StageStyle.UTILITY);
        stage.show();
    }

    public void startGame() {
        // Clear the pane and reset variables
        pane.getChildren().clear();
        clickCount = 0;
        points = 0;
        Scores.setText("Score :" + points);
        remain.setText("Birds Left: " + clickLeft);
        clicked.setText("Birds Caught: " + birdsCaught);
        Round.setText("Current Round:" + roundCounter);

        // Create the first falling object
        ImageView firstObject = new ImageView(getImage(fallingObject[(int) (Math.random() * noOfObjects)]));
        Image image = getImage("giphy.gif"); // the background
        ImageView imageViewer = new ImageView(image);
        firstObject.setFitHeight(50);
        firstObject.setFitWidth(50);

        // Create a circular clip for the falling object
        Circle clip = new Circle(25, 25, 25);
        firstObject.setClip(clip);

        // Add the necessary elements to the pane
        imageViewer.setFitWidth(500);
        imageViewer.setFitHeight(500);
        pane.getChildren().addAll(imageViewer, firstObject, Scores, Round, remain, clicked);
        pane.setAlignment(firstObject, Pos.TOP_CENTER);
        pane.setAlignment(Scores, Pos.TOP_LEFT);
        pane.setAlignment(Round, Pos.TOP_RIGHT);
        pane.setAlignment(remain, Pos.BOTTOM_LEFT);
        pane.setAlignment(clicked, Pos.BOTTOM_RIGHT);

        // Create the animation for the falling object
        TranslateTransition transition = new TranslateTransition();
        transition.setNode(firstObject);
        transition.setDuration(Duration.seconds(3+Math.random()*8));
        transition.setByY(500); // Move the object down by 500 pixels

        // Set the starting x coordinate for the falling object randomly
        int num = (int) (Math.random() * 2);
        if (num == 0)
            transition.setFromX(-(int) (10 + Math.random() * 200));
        else
            transition.setFromX((int) (10 + Math.random() * 200));

        transition.play();

        // Calculate the y coordinate of the falling object while the animation is playing
        transition.currentTimeProperty().addListener((observable, oldValue, newValue) -> {
            double progress = newValue.toMillis() / transition.getDuration().toMillis();
            double currentY = firstObject.getLayoutY() + progress * transition.getByY();
            firstObject.setLayoutY(currentY);
        });

        // Add event listener for when the falling object is clicked
        firstObject.setOnMouseClicked(mouseEvent -> {
            clickCount++;
            clickLeft--;
            birdsCaught++;

            // Create a new animation to fade out the object
            FadeTransition fadeOut = new FadeTransition(Duration.millis(150), firstObject);
            fadeOut.setFromValue(1.0);
            fadeOut.setToValue(0.0);
            fadeOut.setOnFinished(event -> {
                firstObject.setVisible(false);
                pane.getChildren().remove(firstObject); // Remove the circle
            });

            // Start the fade out animation
            fadeOut.play();

            transition.setOnFinished(null); // Remove the animation when the object is clicked

            if(firstObject.getImage().getUrl().equals("file:/C:/Users/humai/IdeaProjects/TheGame/target/classes/O1.jpg")){ //red bird
                points = points+2;
            }
            else if(firstObject.getImage().getUrl().equals("file:/C:/Users/humai/IdeaProjects/TheGame/target/classes/O2.jpg")){ // pink bird
                points = points+3;
            }
            else if(firstObject.getImage().getUrl().equals("file:/C:/Users/humai/IdeaProjects/TheGame/target/classes/O3.png")){ // green pig
                points = points+5;
            }

            Scores.setText("Score :" + points);
            remain.setText("Birds Left: " + clickLeft);
            clicked.setText("Birds Caught: " + birdsCaught);
            roundFinished.setText("Current Round:" + roundCounter);

            // Create and animate 5 new falling objects
            for (int i = 0; i < 5; i++) {
                ImageView temp = createCircularObject();
                objects.add(0, temp);
                animateTheObject(objects.get(0));
            }

            transition.stop();
        });

// Add event listener for when the animation is finished
        transition.setOnFinished(actionEvent -> {
            if (firstObject.isVisible() && (firstObject.getTranslateY() + firstObject.getLayoutY()) >= 500) { // Check if the object has reached the bottom of the screen
                roundCounter++;
                showExit(); // End the round and display the "game over" screen
            }
        });
    }

    private void showExit() {
        // Remove all objects from the pane and make them invisible
        objects.forEach(imageView -> {
            pane.getChildren().remove(imageView);
            imageView.setVisible(false);
        });
        // Clear all objects from the pane
        pane.getChildren().clear();

        // Display a "game over" image
        Image BackGroundEndRound = getImage("S2.gif");
        ImageView playEnd = new ImageView(BackGroundEndRound);
        playEnd.setFitWidth(500);
        playEnd.setFitHeight(500);
        pane.getChildren().add(playEnd);

        // Add the exit button, "game over" text, and retry button to the pane
        pane.getChildren().add(exit);
        pane.getChildren().add(gameOver);
        pane.getChildren().add(retryButton);

        // Set the alignment of the "game over" text, retry button, and exit button
        pane.setAlignment(gameOver, Pos.TOP_CENTER);
        pane.setAlignment(retryButton, Pos.BOTTOM_LEFT);
        pane.setAlignment(exit, Pos.BOTTOM_RIGHT);

        // Add the player's score to the list of scores
        scoresList.add(points);

        // Add event listeners for the exit and retry buttons
        exit.setOnAction(event3 -> {
            exitButtonClicked();
        });
        retryButton.setOnMouseClicked(event1 -> { // when retry button is clicked
            // Clear the pane and start a new game
            pane.getChildren().clear();
            startGame();
        });
    }

    // This method takes a path to an image file and returns an Image object.
    public Image getImage(String path) {

            // Try to create an Image object using the given path.
            return new Image(path);

    }


    // This method creates a new circular object and returns it as an ImageView object.
    public ImageView createCircularObject() {
        // Get a random image from the "fallingObject" array and create a new ImageView object with it.
        ImageView newObject = new ImageView(getImage(fallingObject[(int) (Math.random() * noOfObjects)]));
        // Set the height and width of the ImageView to 50 pixels.
        newObject.setFitHeight(50);
        newObject.setFitWidth(50);

        // Create a circular clip to mask the ImageView
        Circle clip = new Circle(25, 25, 25);
        newObject.setClip(clip);


        // Add the newObject to the pane and return it.
        pane.getChildren().addAll(newObject);
        return newObject;
    }


    public void animateTheObject(ImageView object) {// a method for animating the circular object.
        // Align the object to the top center of the pane
        pane.setAlignment(object, Pos.TOP_CENTER);

        // Create a TranslateTransition to animate the object
        TranslateTransition transition = new TranslateTransition();
        transition.setNode(object);

        // Set a random duration for the transition between 2 and 8 seconds
        transition.setDuration(Duration.seconds(2+Math.random()*6));

        // Move the object downwards by 500 pixels
        transition.setByY(500);

        // Generate a random number between 0 and 1
        int number = (int) (Math.random() * 2);

        // If the number is 0, move the object from a random position on the left edge of the pane
        if (number == 0)
            transition.setFromX(-(int) (10 + Math.random() * 220));
            // If the number is 1, move the object from a random position on the right edge of the pane
        else
            transition.setFromX((int) (10 + Math.random() * 220));

        // Play the transition animation
        transition.play();

        // If the round is not over and clicks are left
        if (roundCounter < 7 && clickLeft > 0) {
            // Add an event listener for mouse clicks on the object
            object.setOnMouseClicked(mouseEvent -> {
                // Increment the click count and decrement the clicks left
                clickCount++;
                clickLeft--;
                birdsCaught++;

                // Create a new animation to simulate a fade out effect
                FadeTransition fadeOut = new FadeTransition(Duration.millis(150), object);
                fadeOut.setFromValue(1.0);
                fadeOut.setToValue(0.0);
                fadeOut.setCycleCount(1);
                fadeOut.setAutoReverse(false);
                fadeOut.setOnFinished(event -> {
                    object.setVisible(false);
                    pane.getChildren().remove(object); // Remove the circle
                });

                // Start the fade out animation
                fadeOut.play();

                // Remove the transition animation for the object
                transition.setOnFinished(null);

                // Add a random number of points to the score

                if(object.getImage().getUrl().equals("file:/C:/Users/humai/IdeaProjects/TheGame/target/classes/O1.jpg")){ //red bird
                    points = points+2;
                }
                else if(object.getImage().getUrl().equals("file:/C:/Users/humai/IdeaProjects/TheGame/target/classes/O2.jpg")){ // pink bird
                    points = points+3;
                }
                else if(object.getImage().getUrl().equals("file:/C:/Users/humai/IdeaProjects/TheGame/target/classes/O3.png")){ // green pig
                    points = points+5;
                }


                // Update the score, remaining clicks, and clicks caught labels
                Scores.setText("Score :" + points);
                remain.setText("Birds Left: " + clickLeft);
                clicked.setText("Birds Caught: " + birdsCaught);

                // If the required number of clicks have been caught, end the round
                if (clickCount == 6) {
                    roundCounter++;
                    ballSpeed = 10;
                    // Add the current score to the score list
                    scoresList.add(points);
                    endRound();
                }
            });

// Add an event listener for when the transition animation finishes
            transition.setOnFinished(actionEvent -> {
                // If the object is still visible and has reached y position of 500 or more
                if (object.isVisible() && (object.getTranslateY() + object.getLayoutY()) >= 500) {
                    // Increment the round counter
                    roundCounter++;
                    // If the last round has been completed, exit the game
                    if(roundCounter ==7){
                        // Remove all objects from the pane and hide them
                        objects.forEach(imageView -> {
                            pane.getChildren().remove(imageView);
                            imageView.setVisible(false);
                        });
                        // Add the current score to the score list
                        scoresList.add(points);
                        exitButtonClicked();
                    }
                    // If the last round has not been completed, show the exit button
                    else {
                        showExit();
                    }
                }
            });
        }
        // If the round is over or there are no clicks left, exit the game
        else if (clickLeft ==0) {
            exitGame();
        } else {
            exitGame();
        }
    }

    private void exitButtonClicked() {
        // Clear all objects from the pane
        pane.getChildren().clear();

        // If the game has ended due to running out of clicks or completing all rounds, display a game over image
        if(clickLeft == 0 || roundCounter == 7) {
            Image BackGroundEndGame = getImage("S6.gif");
            ImageView gameFinished = new ImageView(BackGroundEndGame);
            gameFinished.setFitWidth(500);
            gameFinished.setFitHeight(500);
            pane.getChildren().addAll(gameFinished);
        }
        // If the game has not ended, display a game over image with a different animation
        else {
            Image BackGroundEndGame = getImage("O6.gif");
            ImageView gameFinished = new ImageView(BackGroundEndGame);
            gameFinished.setFitWidth(500);
            gameFinished.setFitHeight(500);
            pane.getChildren().addAll(gameFinished);
        }

        // Add the show score label and the game end label to the pane
        pane.getChildren().addAll(showScore, gameEnd);

        // Set the text color, font size, and alignment for the show score label
        showScore.setTextFill(Color.BLACK);
        showScore.setFont(new Font(25));
        pane.setAlignment(showScore, Pos.BOTTOM_CENTER);

        // Set the text color, font size, and alignment for the game end label
        gameEnd.setFill(white);
        gameEnd.setFont(new Font(25));
        pane.setAlignment(gameEnd, Pos.TOP_CENTER);

        // Add an event listener for mouse clicks on the show score label that exits the game
        showScore.setOnMouseClicked(e -> {
            exitGame();
        });
    }


    public void exitGame() {
        // Clear all objects from the pane
        pane.getChildren().clear();

        // Sort the list of scores in descending order
        Collections.sort(scoresList);

        // Display a game over image
        Image gameDoneBackGround = getImage("S4.gif");
        ImageView gameDone = new ImageView(gameDoneBackGround);
        gameDone.setFitWidth(500);
        gameDone.setFitHeight(500);
        pane.getChildren().addAll(gameDone);

        // Define an array of strings for the top score labels
        String[] names = {"First Score,amazing!  = ", "Second Score,excellent!  = ", "Third Score,good! = ", "Fourth Score,nice try!  = ", "Fifth Score,good try! = "};

        // Add a label for the top scores to the pane
        Text topScores = new Text("Top Scores:");
        topScores.setFill(white);
        topScores.setFont(Font.font("Arial", FontWeight.BOLD, 35));
        pane.getChildren().add(topScores);
        pane.setAlignment(topScores, Pos.TOP_CENTER);

        // Create a timeline with a delay of 1 second between each score
        Timeline timeline = new Timeline();

        // If the game has ended due to running out of clicks or completing all rounds
        if (roundCounter == 7) {
            // Display the top 5 scores
            for (int i = 0; i < 5 ; i++) {
                try {
                    // Create a label for the score and add it to the pane
                    Text score = new Text(names[i] + scoresList.get(scoresList.size() - 1 - i) + " Points");
                    score.setFont(font);
                    score.setFill(white);
                    score.setVisible(false); // Set the visible property to false initially
                    pane.getChildren().add(score);
                    pane.setAlignment(score, Pos.CENTER);
                    score.setTranslateY((i * 20) - 40);
                    // Add a keyframe to the timeline to display the score after a delay
                    KeyFrame keyFrame = new KeyFrame(Duration.seconds(i + 1), event -> score.setVisible(true));
                    timeline.getKeyFrames().add(keyFrame);
                } catch (Exception e) {
                }
            }

            // Display the number of missed balls
            Text missed = new Text("You missed "+clickLeft+" birds ");
            missed.setFont(font);
            missed.setVisible(false);
            missed.setFill(white);
            pane.getChildren().add(missed);
            pane.setAlignment(missed, Pos.BOTTOM_CENTER);
            KeyFrame keyFrame = new KeyFrame(Duration.seconds(6), event -> missed.setVisible(true));
            timeline.getKeyFrames().add(keyFrame);
            timeline.play();

            // Hide the play again button during the score display
            playAgain.setVisible(false);
            PauseTransition p = new PauseTransition(Duration.seconds(scoresList.size()+5));
            // Show the play again button after the score display is complete
            p.setOnFinished(actionEvent -> {playAgain.setVisible(true) ; pane.getChildren().remove(missed);});
            p.play();
            pane.getChildren().add(playAgain);
            pane.setAlignment(playAgain,Pos.BOTTOM_CENTER);
        }
        // If the game has not ended due to running out of clicks or completing all rounds
        else {
            // Display all scores
            for (int i = 0; i < scoresList.size(); i++) {
                try {
                    // Create a label for the score and add it to the pane
                    Text score = new Text(names[i] + scoresList.get(scoresList.size() - 1 - i) + " Points");
                    score.setFont(font);
                    score.setFill(white);
                    score.setVisible(false); // Set the visible property to false initially
                    pane.getChildren().add(score);
                    pane.setAlignment(score, Pos.CENTER);
                    score.setTranslateY((i * 20) - 40);
                    // Add a keyframe to the timeline to display the score after a delay
                    KeyFrame keyFrame = new KeyFrame(Duration.seconds(i + 1), event -> score.setVisible(true));
                    timeline.getKeyFrames().add(keyFrame);
                } catch (Exception e) {
                }
            }

            // Display the number of missed balls
            Text missed = new Text("You missed "+clickLeft+" birds ");
            missed.setFont(font);
            missed.setVisible(false);
            missed.setFill(white);
            pane.getChildren().add(missed);
            pane.setAlignment(missed, Pos.BOTTOM_CENTER);
            KeyFrame keyFrame = new KeyFrame(Duration.seconds(scoresList.size()+1), event -> missed.setVisible(true));
            timeline.getKeyFrames().add(keyFrame);
            timeline.play();

            // Hide the play again button during the score display
            playAgain.setVisible(false);
            PauseTransition p = new PauseTransition(Duration.seconds(scoresList.size()+5));
            // Show the play again button after the score display is complete
            p.setOnFinished(actionEvent -> {playAgain.setVisible(true) ; pane.getChildren().remove(missed);});
            p.play();
            pane.getChildren().add(playAgain);
            pane.setAlignment(playAgain,Pos.BOTTOM_CENTER);
        }

        // Add an event listener for mouse clicks on the play again button that resets the game
        playAgain.setOnMouseClicked(event ->{
            pane.getChildren().clear();
            clickLeft = 30;
            roundCounter = 1;
            points = 0;
            clickCount = 0;
            scoresList.clear();
            Image BackGroundPlayGame = getImage("mainMenu.jpg");
            ImageView startPlay = new ImageView(BackGroundPlayGame);
            startPlay.setFitWidth(500);
            startPlay.setFitHeight(500);
            startGame = new Button("", startPlay);
            pane.getChildren().addAll(startGame);
            Text text1 = new Text("Red bird has 2 points");
            Text text2 = new Text("Pink bird has 3 points");
            Text text3 = new Text("Green pig has 5 points");

            text1.setFill(Color.RED);
            text1.setFont(font1);

            text2.setFill(Color.PINK);
            text2.setFont(font1);

            text3.setFill(Color.GREEN);
            text3.setFont(font1);

            pane.getChildren().addAll(text1,text2,text3);
            pane.setAlignment(text1,Pos.BOTTOM_CENTER);
            pane.setAlignment(text2,Pos.BOTTOM_LEFT);
            pane.setAlignment(text3,Pos.BOTTOM_RIGHT);

            startGame.setOnMouseClicked(event1 -> {
                startGame();
            });
        });
    }

    public void endRound() {
        // Remove all objects from the pane and make them invisible
        objects.forEach(imageView -> {
            pane.getChildren().remove(imageView);
            imageView.setVisible(false);
        });
        // Clear all objects from the pane
        pane.getChildren().clear();
        // Reset click count
        clickCount = 0;

        // If the current round is the last round, end the game
        if (roundCounter == 7) {
            exitButtonClicked();
        }
        // If the current round is not the last round, prepare for the next round
        else {
            // Reset points
            points = 0;

            // Display a "next round" image
            Image nextRoundBackGround = getImage("S3.gif");
            ImageView nextRound2 = new ImageView(nextRoundBackGround);
            nextRound2.setFitWidth(500);
            nextRound2.setFitHeight(500);

            pane.getChildren().add(nextRound2);
            pane.getChildren().add(roundFinished);
            pane.getChildren().add(exit);
            pane.getChildren().add(nextRound);

            pane.setAlignment(exit, Pos.BOTTOM_RIGHT);
            pane.setAlignment(nextRound, Pos.BOTTOM_LEFT);
            pane.setAlignment(roundFinished, Pos.TOP_CENTER);

            // Add event listeners for the exit and next round buttons
            exit.setOnMouseClicked(event -> {
                exitButtonClicked();
            });
            nextRound.setOnMouseClicked(mouseEvent -> {
                // Clear the pane and start the next round
                pane.getChildren().clear();
                startGame();
            });
        }
    }

    public static void main(String[] args) {
        launch();
    }
}