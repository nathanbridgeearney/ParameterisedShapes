// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP-102-112 - 2021T1, Assignment 3
 * Name:
 * Username:
 * ID:
 */

import ecs100.*;
import java.awt.Color;
import javax.swing.JColorChooser;

/** Paramterised Shapes: draw tricolour flags and game boards */
public class ParameterisedShapes{

    //Constants for CORE  (three strip flags)
    public static final double FLAG_WIDTH = 200;
    public static final double FLAG_HEIGHT = 133;

    //Constants for COMPLETION
    public static final double BOARD_LEFT = 15;  // Left side of each row
    public static final double BOARD_TOP = 15;   // Top of the first row
    public static final double ROW_SIZE = 40;    // Height of each row.
    public static final double DISH_WIDTH = ROW_SIZE-4;      // Size of the dishes
    public static final double DISH_HEIGHT = DISH_WIDTH-10;  
    public static final double PEBBLE_DIAM = 10; // Size of the
    int redPos = 0;
    int bluePos= 5;
    int numDish = 0;
    boolean gameWon = false;
    boolean redWon=false;
    boolean blueWon = false;
    /**   CORE
     * asks user for a position and three colours, then calls the
     * drawTriColorFlag method, passing the appropriate arguments
     */
    public void doCore(){
        double left = UI.askDouble("Left of flag");
        double top = UI.askDouble("Top of flag");
        boolean horiz = UI.askBoolean("Are the stripes horizontal?");
        UI.println("Now choose the colours");
        Color stripe1 = JColorChooser.showDialog(null, "First Stripe", Color.white);
        Color stripe2 = JColorChooser.showDialog(null, "Second Stripe", Color.white);
        Color stripe3 = JColorChooser.showDialog(null, "Third Stripe", Color.white);
        this.drawThreeStripeFlag(left,top,horiz,stripe1,stripe2,stripe3);
    }

    /**   CORE
     * draws a three colour flag at the given position consisting of
     * three equal size stripes of the given colors
     * The stripes are horizontal or vertical
     * The size of the flag is specified by the constants FLAG_WIDTH and FLAG_HEIGHT
     */
    public void drawThreeStripeFlag(double left, double top, boolean horiz, Color stripe1, Color stripe2, Color stripe3){
        /*# YOUR CODE HERE */
        if (!horiz) {
            UI.setColor(stripe1);
            UI.fillRect(left, top, FLAG_WIDTH / 3, FLAG_HEIGHT);
            UI.setColor(stripe2);
            UI.fillRect(left + FLAG_WIDTH/3, top, FLAG_WIDTH / 3, FLAG_HEIGHT);
            UI.setColor(stripe3);
            UI.fillRect(left + 2*(FLAG_WIDTH/3), top, FLAG_WIDTH / 3, FLAG_HEIGHT);
        }
        else{
            UI.setColor(stripe1);
            UI.fillRect(left, top, FLAG_WIDTH, FLAG_HEIGHT/3);
            UI.setColor(stripe2);
            UI.fillRect(left, top + FLAG_HEIGHT/3, FLAG_WIDTH, FLAG_HEIGHT/3);
            UI.setColor(stripe3);
            UI.fillRect(left, top + 2 * (FLAG_HEIGHT/3), FLAG_WIDTH, FLAG_HEIGHT/3);
        }
        UI.setColor(Color.black);
        UI.drawRect(left,top, FLAG_WIDTH, FLAG_HEIGHT);
    }

    /**   COMPLETION
     * Draws a pebble game board with five rows of increasing size
     *   The first row has 6 dishes, the second has 7 dishes, the third has 8, etc.
     *   The positions of the red and blue pebbles are shown in this table:
     *   (where the |'s separate the dishes)
     *     |   | r |   |   |   | b |
     *     |   | b | r |   |   |   |   |
     *     |   |   |   |   |   | r |   | b |
     *     | b |   |   | r |   |   |   |   |   |
     *     |   |   | b |   |   |   |   |   |   | r |
     *
     *  It uses the drawPebbleRow method which draws one row and the two pebbles in it.
     */
    public void doCompletion(){
        UI.clearGraphics();
        this.drawPebbleRow(1,6,1,5);
        this.drawPebbleRow(2,7,3,1);
        this.drawPebbleRow(3,8,5,7 );
        this.drawPebbleRow(4,9,0,3);
        this.drawPebbleRow(5,10,2,9);
    }

    /**   COMPLETION
     * Draws a row of a pebble game. Parameters must be sufficient to specify
     * the position and size of the row, and which dishes the red and blue pebbles are in.
     * Hint: use the drawRowOutline, drawDish and drawPebble methods!
     */
    public void drawPebbleRow(int rowNum, int numDishes, int redPos, int bluePos){
        /*# YOUR CODE HERE */
        drawRowOutline(rowNum,numDishes);
        for(int i = 0; i < numDishes; i++ ){
            drawDish(rowNum,i);
            drawPebble(rowNum,bluePos,Color.blue);
            drawPebble(rowNum,redPos,Color.red);
            UI.setColor(Color.black);
        }

    }

    /**
     * Draws the outline of the specified row with the specified number of dishes.
     * (rows numbered from 0)
     */
    public void drawRowOutline(int rowNum, int numDishes){
        UI.drawRect(BOARD_LEFT, BOARD_TOP + rowNum * ROW_SIZE, ROW_SIZE*numDishes, ROW_SIZE);
    }

    /**
     * Draw the specified dish in the specified row
     * (rows and dishes are numbered from 0)
     */
    public void drawDish(int rowNum, int dishNum){
        double dishLeft = BOARD_LEFT+dishNum*ROW_SIZE + ROW_SIZE/2 - DISH_WIDTH/2 ;
        double dishTop = BOARD_TOP + rowNum * ROW_SIZE + ROW_SIZE/2 - DISH_HEIGHT/2;
        UI.setColor(new Color(230, 230, 230));   // very light grey
        UI.fillOval(dishLeft, dishTop, DISH_WIDTH, DISH_HEIGHT);
        UI.setColor(Color.black);
        UI.drawOval(dishLeft, dishTop, DISH_WIDTH, DISH_HEIGHT);
    }

    /**
     * Draw a pebble in specified dish in the specified row with the specified color.
     * (rows and dishes are numbered from 0)
     */
    public void drawPebble(int rowNum, int dishNum, Color pebbleColor){
        double pebbleTop = BOARD_TOP + rowNum * ROW_SIZE + ROW_SIZE/2 - PEBBLE_DIAM/2;
        double pebbleLeft = BOARD_LEFT+dishNum*ROW_SIZE + ROW_SIZE/2 - PEBBLE_DIAM/2;
        UI.setColor(pebbleColor);
        UI.fillOval(pebbleLeft, pebbleTop, PEBBLE_DIAM, PEBBLE_DIAM);
    }
    public void challenge(){
        UI.clearGraphics();
        numDish = UI.askInt("How many dishes do you want?");
        bluePos = numDish - 1;
        this.drawPebbleRow(1,numDish,redPos,bluePos);
        while(!gameWon){
          if(!blueWon) {
              UI.sleep(500);
              if (bluePos == redPos + 1) {
                UI.sleep(500);
                bluePos = numDish - 1;
                redPos = redPos + 2;
                UI.sleep(500);
                UI.clearGraphics();
                this.drawPebbleRow(1,numDish,redPos,bluePos);
                winCheck();
              }
              else{
                  UI.sleep(500);
                  UI.clearGraphics();
                  redPos = redPos + 1;
                  this.drawPebbleRow(1, numDish, redPos, bluePos);
                  winCheck();
                  UI.sleep(500);
              }
          }
          if (!redWon){
              UI.sleep(500);
              if (redPos == bluePos - 1) {
                  UI.sleep(500);
                  redPos = 0;
                  bluePos = bluePos - 2;
                  UI.sleep(500);
                  UI.clearGraphics();
                  this.drawPebbleRow(1,numDish,redPos,bluePos);
                  winCheck();
              }
              else{
                  UI.sleep(500);
                  UI.clearGraphics();
                  bluePos = bluePos - 1;
                  this.drawPebbleRow(1, numDish, redPos, bluePos);
                  winCheck();
                  UI.sleep(500);
              }
          }
        }
        if (redWon = true){
            UI.print("Red Wins");
        }
        else if (blueWon = true){
            UI.print("Blue Wins");
        }
    }

    public void winCheck(){
            if (bluePos == 0) {
                blueWon = true;
                gameWon = true;

            } else if (redPos == numDish - 1 ) {
                redWon = true;
                gameWon = true;
            }
    }


    public void setupGUI(){
        UI.initialise();
        UI.addButton("Clear", UI::clearPanes );
        UI.addButton("Core", this::doCore );
        UI.addButton("Completion", this::doCompletion );
        UI.addButton("Challenge", this::challenge);
        UI.addButton("Quit", UI::quit );

    }

    public static void main(String[] args){
        ParameterisedShapes ps = new ParameterisedShapes ();
        ps.setupGUI();
    }

}
