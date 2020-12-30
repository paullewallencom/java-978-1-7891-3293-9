package com.loonycorn;


/**
 * Created by jananiravi on 11/19/15.
 */
public class PaintFill {

    private static int SCREEN_SIZE = 15;

    public static void main(String[] args) {
        Pixel[][] displayScreen = new Pixel[SCREEN_SIZE][SCREEN_SIZE];
        for (int i = 0; i < SCREEN_SIZE; i++) {
            for (int j = 0; j < SCREEN_SIZE; j++) {
                displayScreen[i][j] = new Pixel("X");
            }
        }

        setColor(displayScreen, 4, 4, 6, "B");
        setColor(displayScreen, 6, 1, 7, "I");
        printScreen(displayScreen);

        paintFill(displayScreen, 6, 6, "I", "O");
        paintFill(displayScreen, 4, 5, "B", "O");
        paintFill(displayScreen, 2, 2, "X", "Z");
        printScreen(displayScreen);
    }

    public static void printScreen(Pixel[][] displayScreen) {
        for (int i = 0; i < SCREEN_SIZE; i++) {
            for (int j = 0; j < SCREEN_SIZE; j++) {
                System.out.print(displayScreen[i][j].getColor() + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void setColor(Pixel[][] displayScreen, int x, int y, int size, String color) {
        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                displayScreen[j][i].setColor(color);
            }
        }
    }

    public static void paintFill(Pixel[][] displayScreen, int x, int y,
                                 String originalColor, String newColor) {
        if (x < 0 || y < 0 || x >= displayScreen[0].length || y >= displayScreen.length) {
            return;
        }
        if (displayScreen[y][x].getColor() != originalColor) {
            return;
        }

        if (displayScreen[y][x].getColor() == originalColor) {
            displayScreen[y][x].setColor(newColor);

            // Pixel on the left
            paintFill(displayScreen, x - 1, y, originalColor, newColor);
            // Pixel on top
            paintFill(displayScreen, x, y - 1, originalColor, newColor);
            // Pixel on the right
            paintFill(displayScreen, x + 1, y, originalColor, newColor);
            // Pixel on the bottom
            paintFill(displayScreen, x, y + 1, originalColor, newColor);
        }

    }

    public static class Pixel {
        private String color;

        public Pixel(String color) {
            this.color = color;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }
    }
}
