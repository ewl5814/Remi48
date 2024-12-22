package model;

//import java.util.ArrayList;

public class Board {
    private final int[][] grid;
    private static int max = 0;
    private int score;

    public Board() {
        score = 0;
        grid = new int[4][4];
        for (int j = 0; j < 4; j++) {
            for (int i  = 0; i < 4; i++) {
                grid[j][i] =0;
            }
        }
    }
    public int[][] getGrid() {
        return grid;
    }
    public boolean isSolution() {
        for (int[] x : this.grid) {
            for (int i  = 0; i < 4; i++) {
                if (x[i] != 0) {
                    if (x[i] == 8192) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean gameOver() {
        int[][] test = new int[4][4];
        int temp = score;
        int tempmax = max;
        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 4; i++) {
                test[j][i] = grid[i][j];
                if (grid[j][i] == 0) {
                    return false;
                }
            }
        }
        shiftNorth(test);
        shiftSouth(test);
        shiftEast(test);
        shiftWest(test);
        score = temp;
        max = tempmax;
        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 4; i++) {
                if (test[j][i] != grid[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
    void addTile() {
        int x;
        int y;
        do {
            x = (int) (Math.random() * 4);
            y = (int) (Math.random() * 4);
        } while (grid[x][y] != 0);
        grid[x][y] = 2;
    }
    void shiftNorth(int[][] temp) {
        int[][] north = new int[temp.length][temp[0].length];
        for (int i = 0; i < temp.length; i++) {
            System.arraycopy(temp[i], 0, north[i], 0, temp[0].length);
        }
        for (int i = 0; i < north.length; i++) {
            for (int j = 0; j < north[0].length; j++) {
                if (i > 0) {
                    if (north[i][j] != 0) {
                        int num = north[i][j];
                        for (int tilt = 1; tilt < i + 1; tilt++) {
                            if (north[i - tilt][j] == 0) {
                                int t = north[i - (tilt - 1)][j];
                                north[i - (tilt - 1)][j] = 0;
                                north[i - tilt][j] = t;
                            }
                            else if (north[i - tilt][j] == num) {
                                north[i - tilt][j] = 2*num;
                                north[i - (tilt - 1)][j] = 0;
                                score += 2*north[i][j];
                                if (2*north[i][j] > max) {
                                    max = 2*north[i][j];
                                }
                                tilt = i + 1;
                            }
                            else {
                                tilt = i + 1;
                            }
                        }
                    }
                }
            }
        }
        for (int i = 0; i < 4; i++) {;
            System.arraycopy(north[i], 0, temp[i], 0, 4);
        }
        addTile();
    }

    void shiftSouth(int[][] temp) {
        int[][] south = new int[temp.length][temp[0].length];
        for (int i = 0; i < temp.length; i++) {
            System.arraycopy(temp[i], 0, south[i], 0, temp[0].length);
        }
        for (int i = south.length - 1; i >= 0; i--) {
            for (int j = 0; j < south[0].length; j++) {
                if (i < south.length - 1) {
                    if (south[i][j] != 0) {
                        int num = south[i][j];
                        for (int tilt = 1; tilt < south.length - i; tilt++) {
                            if (south[i + tilt][j] == 0) {
                                int t = south[i + (tilt - 1)][j];
                                south[i + (tilt - 1)][j] = 0;
                                south[i + tilt][j] = t;
                            } else if (south[i + tilt][j] == num) {
                                south[i + tilt][j] = 2*num;
                                south[i + (tilt - 1)][j] = 0;
                                score += 2*south[i][j];
                                if (2*south[i][j] > max) {
                                    max = 2*south[i][j];
                                }
                                tilt = south.length - (i+1);
                            }
                            else {
                                tilt = south.length - (i+1);
                            }
                        }
                    }
                }
            }
        }
        for (int i = 0; i < 4; i++) {
            System.arraycopy(south[i], 0, temp[i], 0, 4);
        }
        addTile();
    }

    void shiftWest(int[][] temp) {
        int[][] west = new int[temp.length][temp[0].length];
        for (int i = 0; i < temp.length; i++) {
            System.arraycopy(temp[i], 0, west[i], 0, temp[0].length);
        }
        for (int i = 0; i < west.length; i++) {
            for (int j = 0; j < west[0].length; j++) {
                if (j > 0) {
                    if (west[i][j] != 0) {
                        int num = west[i][j];
                        for (int tilt = 1; tilt < j + 1; tilt++) {
                            if (west[i][j - tilt] == 0) {
                                int t = west[i][j - (tilt - 1)];
                                west[i][j - (tilt - 1)] = 0;
                                west[i][j - tilt] = t;
                            } else if (west[i][j - tilt] == num) {
                                west[i][j - tilt] = 2*num;
                                west[i][j - (tilt - 1)] = 0;
                                score += 2*west[i][j];
                                if (2*west[i][j] > max) {
                                    max = 2*west[i][j];
                                }
                                tilt = j + 1;
                            }
                            else {
                                tilt = j + 1;
                            }
                        }
                    }
                }
            }
        }
        for (int i = 0; i < 4; i++) {
            System.arraycopy(west[i], 0, temp[i], 0, 4);
        }
        addTile();
    }

    void shiftEast(int[][] temp) {
        int[][] east = new int[temp.length][temp[0].length];
        for (int i = 0; i < temp.length; i++) {
            System.arraycopy(temp[i], 0, east[i], 0, temp[0].length);
        }
        for (int i = 0; i < east.length; i++) {
            for (int j = east[0].length - 1; j >= 0; j--) {
                if (j < east[0].length - 1) {
                    if (east[i][j] != 0) {
                        int num = east[i][j];
                        for (int tilt = 1; tilt < east[0].length - j; tilt++) {
                            if (east[i][j + tilt] == 0) {
                                int t = east[i][j + (tilt - 1)];
                                east[i][j + (tilt - 1)] = 0;
                                east[i][j + tilt] = t;
                            } else if (east[i][j + tilt] == num) {
                                east[i][j + tilt] = 2*num;
                                east[i][j + (tilt - 1)] = 0;
                                score += 2*east[i][j];
                                if (2*east[i][j] > max) {
                                    max = 2*east[i][j];
                                }
                                tilt = east[0].length - j;
                            }
                            else {
                                tilt = east[0].length - j;
                            }
                        }
                    }
                }
            }
        }
        for (int i = 0; i < 4; i++) {
            System.arraycopy(east[i], 0, temp[i], 0, 4);
        }
        addTile();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int row=0; row<this.grid.length; ++row) {
            for (int col=0; col<this.grid[0].length; ++col) {
                result.append(this.grid[row][col]).append(" ");
            }
            if (row != this.grid.length-1) {
                result.append(System.lineSeparator());
            }
        }
        return result.toString();
    }
}
