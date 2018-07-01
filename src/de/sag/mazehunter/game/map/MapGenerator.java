/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.sag.mazehunter.game.map;

import java.util.Random;

/**
 *
 * @author Karl Huber
 */
public class MapGenerator {
    
    private final Random r = new Random();
    private final int snakeMaxRange = 4;
    
    public Block[][] generateBlocklist(int size) {
        Block[][] b = generateInner(generateWalls(size));
        snake(b, r.nextInt(b.length), r.nextInt(b.length), 0);
        snake(b, r.nextInt(b.length), r.nextInt(b.length), 0);
        snake(b, r.nextInt(b.length), r.nextInt(b.length), 0);
        openClosedBlocks(b);
        return b;
    }
    
    private void openClosedBlocks(Block[][] b) {
        for (int x = 0; x < b.length; x++) {
            for (int y = 0; y < b.length; y++) {
                if (!(b[x][y].up || b[x][y].right || b[x][y].down || b[x][y].left)) {
                    openBlock(b, x, y);
                }
            }
        }
    }
    
    private void openHalfOpenBlocks(Block[][] b) {
        for (int x = 0; x < b.length; x++) {
            for (int y = 0; y < b.length; y++) {
                if (b[x][y].up || b[x][y].right || b[x][y].down || b[x][y].left) {
                    if (r.nextBoolean()) {
                        closeBlock(b, x, y);
                    }
                }
            }
        }
    }
    
    private void closeBlock(Block[][] b, int x, int y) {
        int dir = r.nextInt(4) + 1;
        if (!checkPosition(x, y, dir, b.length)) {
            closeBlock(b, x, y);
            return;
        }
        
        switch (dir) {
            case 1:
                    b[x][y+1].down = false;
                    b[x][y].up = false;
                    break;
            case 2:
                    b[x+1][y].left = false;
                    b[x][y].right = false;
                    break;
            case 3:
                    b[x][y-1].up = false;
                    b[x][y].down = false;
                    break;
            case 4:
                    b[x-1][y].right = false;
                    b[x][y].left = false; 
                    break;
            default:
                throw new RuntimeException("?????");
        }
    }
    
    private void openBlock(Block[][] b, int x, int y) {
        int dir = r.nextInt(4) + 1;
        if (!checkPosition(x, y, dir, b.length)) {
            closeBlock(b, x, y);
            return;
        }
        
        switch (dir) {
            case 1:
                    openN(b, x, y);
                    break;
            case 2:
                    openO(b, x, y);
                    break;
            case 3:
                    openS(b, x, y);
                    break;
            case 4:
                    openW(b, x, y);
                break;
            default:
                throw new RuntimeException("?????");
        }
    }
    
    private Block[][] snake(Block[][] b, int x, int y, int i) {
        if (i >= snakeMaxRange) {
            return b;
        }
        int dir = r.nextInt(4) + 1;
        if (!checkPosition(x, y, dir, b.length)) {
            return snake(b, x, y, i);
        }
        switch (dir) {
            case 1:
                    openN(b, x, y);
                    y += 1;
                    break;
            case 2:
                    openO(b, x, y);
                    x += 1;
                    break;
            case 3:
                    openS(b, x, y);
                    y -= 1;
                     break;
            case 4:
                    openW(b, x, y);
                    x -= 1;
                    break;
            default:
                break;
        }
        System.out.println("SnakePosition: x = " + x + ", y = " + y + ", dir = " + dir);
        i++;
        return snake(b, x, y, i);
    }
    
    private void openN(Block[][] b, int x, int y) {
        b[x][y+1].down = true;
        b[x][y].up = true;
    }
    
    private void openO(Block[][] b, int x, int y) {
        b[x+1][y].left = true;
        b[x][y].right = true;
    }
    
    private void openS(Block[][] b, int x, int y) {
        b[x][y-1].up = true;
        b[x][y].down = true;
    }
    
    private void openW(Block[][] b, int x, int y) {
        b[x-1][y].right = true;
        b[x][y].left = true;    
    }
    
    private boolean checkPosition(int x, int y, int dir, int max) {
        if (dir == 2) {return x+1 < max;} 
        if (dir == 4) {return x-1 >= 0;}
        if (dir == 1) {return y+1 < max;} 
        if (dir == 3) {return y-1 >= 0;}
        throw new RuntimeException("invalid direction");
    }
    
    private Block[][] generateWalls(int size) {
        
        Block[][] b = new Block[size][size];
        int maxIndex = size - 1;
        
        b[0][0] = new Block(r.nextBoolean(), r.nextBoolean(), false, false, 0, 0);
        b[0][maxIndex] = new Block(false, r.nextBoolean(), r.nextBoolean(), false, 0, maxIndex);
        for (int x = 1; x < size; x++) {
            b[x][0] = new Block(r.nextBoolean(), r.nextBoolean(), false, b[x-1][0].left, x, 0);
            b[x][maxIndex] = new Block(false, r.nextBoolean(), r.nextBoolean(), b[x-1][maxIndex].left, x, maxIndex);
        }
        for (int y = 1; y < maxIndex; y++) {
            if (y < maxIndex - 1) {
                b[0][y] = new Block(r.nextBoolean(), r.nextBoolean(), b[0][y-1].up, false, 0, y);
                b[maxIndex][y] = new Block(r.nextBoolean(), false, b[maxIndex][y-1].up, r.nextBoolean(), maxIndex, y);
            } else {
                b[0][y] = new Block(b[0][y+1].down, r.nextBoolean(), b[0][y-1].up, false, 0, y);
                b[maxIndex][y] = new Block(b[maxIndex][y+1].down, false, b[maxIndex][y-1].up, r.nextBoolean(), maxIndex, y);
            }
        }
        return b;
    }
    
    private Block[][] generateInner(Block[][] b) {
        int size = b.length;
        for (int x = 1; x < size-1; x++) {
            for (int y = 1; y < size-1; y++) {
                if (b[x][y+1] == null && b[x+1][y] == null) {
                    b[x][y] = new Block(r.nextBoolean(), r.nextBoolean(), b[x][y-1].up, b[x-1][y].right, x, y);
                } else if (b[x][y+1] != null && b[x+1][y] == null) {
                    b[x][y] = new Block(b[x][y+1].down, r.nextBoolean(), b[x][y-1].up, b[x-1][y].right, x, y);
                } else if (b[x][y+1] == null && b[x+1][y] != null) {
                    b[x][y] = new Block(r.nextBoolean(), b[x+1][y].left, b[x][y-1].up, b[x-1][y].right, x, y);
                } else if (b[x][y+1] != null && b[x+1][y] != null) {
                    b[x][y] = new Block(b[x][y-1].down, b[x+1][y].left, b[x][y-1].up, b[x-1][y].right, x, y);
                }
            }
        }
        return b;
    }
    
    /**
     * converts a blocklist from {@link Map} to a boolean array that you can then send to Client.
     * 
     * @param b blocklist.
     * @return Booleanlist (same format as b... from {@link Map#makeMap}).
     */
    public boolean[] convertBlocksToBoolean(Block[][] b) {
        boolean[] booleanList = new boolean[b.length*b.length*4];
        int i = 0;
        for (int y = 0; y < b.length; y++) {
            for (Block[] b1 : b) {
                booleanList[i] = b1[y].up;
                booleanList[i+1] = b1[y].right;
                booleanList[i+2] = b1[y].down;
                booleanList[i+3] = b1[y].left;
                i += 4;
            }
        }
        return booleanList;
    }
}
