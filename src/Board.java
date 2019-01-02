import java.awt.*;

public class Board {

    public static final int BOARD_SIZE = 20;

    public static final int EMPTY = 0;
    public static final int SNAKE = 1;
    public static final int FOOD = 2;
    public static final int NORTH = 3;
    public static final int EAST = 4;
    public static final int SOUTH = 5;
    public static final int WEST = 6;

    int randA, randB; //guess to place food

    int repeat = 0; //adds apple every 20 steps

    int[][] board;
    int snakeDirection; //direction snake is moving
    int snakeLength; //length of snake

    Snake snake;

    public Board() {
        snake = new Snake();

        board = new int[BOARD_SIZE][BOARD_SIZE];

        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = EMPTY;
            }
        }
        //fill board with empty, add a random starting snake and food location
        //board[(int)(Math.random()*BOARD_SIZE)][(int)(Math.random()*BOARD_SIZE)] = SNAKE;
        snake.body.add(0, new Point((int)(Math.random()*(BOARD_SIZE-1)),(int)(Math.random()*(BOARD_SIZE-1))));
        snakeToBoard();
        board[(int)(Math.random()*(BOARD_SIZE-1))][(int)(Math.random()*(BOARD_SIZE-1))] = FOOD;

        snakeDirection = (int)(Math.random()*3) + 3; //random direction
        snakeLength = snake.body.size();
    }

    public boolean isAlive() {
        return snake.isAlive;
    }

    public void snakeToBoard() {
        //adds snake data to board array
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (board[i][j] != FOOD) board[i][j] = EMPTY;
            }
        }
        for (Point p : snake.body) {
            board[p.y][p.x] = SNAKE;
        }
    }

    public void step() {
        //step of game, moves snake and redraws etc.
        move();
        if (repeat % 20 == 0) {
            board[(int)(Math.random()*(BOARD_SIZE-1))][(int)(Math.random()*(BOARD_SIZE-1))] = FOOD;
        }
        repeat++;
        snakeToBoard();

    }

    public void move() {
        switch (snakeDirection) {
            case NORTH:
                if (snake.body.get(0).y - 1 >= 0) { //in board range
                    if (board[snake.body.get(0).y - 1][snake.body.get(0).x] == EMPTY) {
                        snake.body.add(0, new Point(snake.body.get(0).x, snake.body.get(0).y - 1)); //move front of snake forward
                        snake.body.remove(snake.body.size() - 1); //remove last piece, move forward
                    } else if (board[snake.body.get(0).y - 1][snake.body.get(0).x] == FOOD) {
                        snake.body.add(0, new Point(snake.body.get(0).x, snake.body.get(0).y - 1)); //move front of snake forward
                        //don't remove last piece, as snake grows!
                        do {
                            randA = (int)(Math.random()*(BOARD_SIZE-1));
                            randB= (int)(Math.random()*(BOARD_SIZE-1));
                        } while(board[randA][randB] != EMPTY);
                        board[randA][randB] = FOOD; //randomly adds an apple

                    } else if (board[snake.body.get(0).y - 1][snake.body.get(0).x] == SNAKE) {
                        snake.isAlive = false; //snake dies if intersects self
                        snake.body.add(0, new Point(snake.body.get(0).x, snake.body.get(0).y - 1)); //move front of snake forward
                        snake.body.remove(snake.body.size() - 1); //remove last piece, move forward
                    }
                } else {
                    //cut to bottom of board
                    if (board[BOARD_SIZE - 1][snake.body.get(0).x] == EMPTY) {
                        snake.body.add(0, new Point(snake.body.get(0).x, BOARD_SIZE - 1)); //move front of snake forward
                        snake.body.remove(snake.body.size() - 1); //remove last piece, move forward
                    } else if (board[BOARD_SIZE - 1][snake.body.get(0).x] == FOOD) {
                        snake.body.add(0, new Point(snake.body.get(0).x, BOARD_SIZE - 1)); //move front of snake forward
                        //don't remove last piece, as snake grows!
                        do {
                            randA = (int)(Math.random()*(BOARD_SIZE-1));
                            randB= (int)(Math.random()*(BOARD_SIZE-1));
                        } while(board[randA][randB] != EMPTY);
                        board[randA][randB] = FOOD; //randomly adds an apple
                    } else if (board[BOARD_SIZE - 1][snake.body.get(0).x] == SNAKE) {
                        snake.isAlive = false; //snake dies if intersects self
                        snake.body.add(0, new Point(snake.body.get(0).x, BOARD_SIZE - 1)); //move front of snake forward
                        snake.body.remove(snake.body.size() - 1); //remove last piece, move forward
                    }
                }
                break;
            case EAST:
                if (snake.body.get(0).x + 1 < BOARD_SIZE) { //in board range
                    if (board[snake.body.get(0).y][snake.body.get(0).x + 1] == EMPTY) {
                        snake.body.add(0, new Point(snake.body.get(0).x + 1, snake.body.get(0).y)); //move front of snake forward
                        snake.body.remove(snake.body.size() - 1); //remove last piece, move forward
                    } else if (board[snake.body.get(0).y][snake.body.get(0).x + 1] == FOOD) {
                        snake.body.add(0, new Point(snake.body.get(0).x + 1, snake.body.get(0).y)); //move front of snake forward
                        //don't remove last piece, as snake grows!
                        do {
                            randA = (int)(Math.random()*(BOARD_SIZE-1));
                            randB= (int)(Math.random()*(BOARD_SIZE-1));
                        } while(board[randA][randB] != EMPTY);
                        board[randA][randB] = FOOD; //randomly adds an apple
                    } else if (board[snake.body.get(0).y][snake.body.get(0).x + 1] == SNAKE) {
                        snake.isAlive = false; //snake dies if intersects self
                        snake.body.add(0, new Point(snake.body.get(0).x + 1, snake.body.get(0).y)); //move front of snake forward
                        snake.body.remove(snake.body.size() - 1); //remove last piece, move forward
                    }
                } else {
                    //cut to left of board
                    if (board[snake.body.get(0).y][0] == EMPTY) {
                        snake.body.add(0, new Point(0, snake.body.get(0).y)); //move front of snake forward
                        snake.body.remove(snake.body.size() - 1); //remove last piece, move forward
                    } else if (board[snake.body.get(0).y][0] == FOOD) {
                        snake.body.add(0, new Point(0, snake.body.get(0).y)); //move front of snake forward
                        //don't remove last piece, as snake grows!
                        do {
                            randA = (int)(Math.random()*(BOARD_SIZE-1));
                            randB= (int)(Math.random()*(BOARD_SIZE-1));
                        } while(board[randA][randB] != EMPTY);
                        board[randA][randB] = FOOD; //randomly adds an apple
                    } else if (board[snake.body.get(0).y][0] == SNAKE) {
                        snake.isAlive = false; //snake dies if intersects self
                        snake.body.add(0, new Point(0, snake.body.get(0).y)); //move front of snake forward
                        snake.body.remove(snake.body.size() - 1); //remove last piece, move forward
                    }
                }
                break;
            case SOUTH:
                if (snake.body.get(0).y + 1 < BOARD_SIZE) { //in board range
                    if (board[snake.body.get(0).y + 1][snake.body.get(0).x] == EMPTY) {
                        snake.body.add(0, new Point(snake.body.get(0).x, snake.body.get(0).y + 1)); //move front of snake forward
                        snake.body.remove(snake.body.size() - 1); //remove last piece, move forward
                    } else if (board[snake.body.get(0).y + 1][snake.body.get(0).x] == FOOD) {
                        snake.body.add(0, new Point(snake.body.get(0).x, snake.body.get(0).y + 1)); //move front of snake forward
                        //don't remove last piece, as snake grows!
                        do {
                            randA = (int)(Math.random()*(BOARD_SIZE-1));
                            randB= (int)(Math.random()*(BOARD_SIZE-1));
                        } while(board[randA][randB] != EMPTY);
                        board[randA][randB] = FOOD; //randomly adds an apple
                    } else if (board[snake.body.get(0).y + 1][snake.body.get(0).x] == SNAKE) {
                        snake.isAlive = false; //snake dies if intersects self
                        snake.body.add(0, new Point(snake.body.get(0).x, snake.body.get(0).y + 1)); //move front of snake forward
                        snake.body.remove(snake.body.size() - 1); //remove last piece, move forward
                    }
                } else {
                    //cut to top of board
                    if (board[0][snake.body.get(0).x] == EMPTY) {
                        snake.body.add(0, new Point(snake.body.get(0).x, 0)); //move front of snake forward
                        snake.body.remove(snake.body.size() - 1); //remove last piece, move forward
                    } else if (board[0][snake.body.get(0).x] == FOOD) {
                        snake.body.add(0, new Point(snake.body.get(0).x, 0)); //move front of snake forward
                        //don't remove last piece, as snake grows!
                        do {
                            randA = (int)(Math.random()*(BOARD_SIZE-1));
                            randB= (int)(Math.random()*(BOARD_SIZE-1));
                        } while(board[randA][randB] != EMPTY);
                        board[randA][randB] = FOOD; //randomly adds an apple
                    } else if (board[0][snake.body.get(0).x] == SNAKE) {
                        snake.isAlive = false; //snake dies if intersects self
                        snake.body.add(0, new Point(snake.body.get(0).x, 0)); //move front of snake forward
                        snake.body.remove(snake.body.size() - 1); //remove last piece, move forward
                    }
                }
                break;
            case WEST:
                if (snake.body.get(0).x - 1 >= 0) { //in board range
                    if (board[snake.body.get(0).y][snake.body.get(0).x - 1] == EMPTY) {
                        snake.body.add(0, new Point(snake.body.get(0).x - 1, snake.body.get(0).y)); //move front of snake forward
                        snake.body.remove(snake.body.size() - 1); //remove last piece, move forward
                    } else if (board[snake.body.get(0).y][snake.body.get(0).x - 1] == FOOD) {
                        snake.body.add(0, new Point(snake.body.get(0).x - 1, snake.body.get(0).y)); //move front of snake forward
                        //don't remove last piece, as snake grows!
                        do {
                            randA = (int)(Math.random()*(BOARD_SIZE-1));
                            randB= (int)(Math.random()*(BOARD_SIZE-1));
                        } while(board[randA][randB] != EMPTY);
                        board[randA][randB] = FOOD; //randomly adds an apple
                    } else if (board[snake.body.get(0).y][snake.body.get(0).x - 1] == SNAKE) {
                        snake.isAlive = false; //snake dies if intersects self
                        snake.body.add(0, new Point(snake.body.get(0).x - 1, snake.body.get(0).y)); //move front of snake forward
                        snake.body.remove(snake.body.size() - 1); //remove last piece, move forward
                    }
                } else {
                    //cut to right of board
                    if (board[snake.body.get(0).y][BOARD_SIZE - 1] == EMPTY) {
                        snake.body.add(0, new Point(BOARD_SIZE - 1, snake.body.get(0).y)); //move front of snake forward
                        snake.body.remove(snake.body.size() - 1); //remove last piece, move forward
                    } else if (board[snake.body.get(0).y][BOARD_SIZE - 1] == FOOD) {
                        snake.body.add(0, new Point(BOARD_SIZE - 1, snake.body.get(0).y)); //move front of snake forward
                        //don't remove last piece, as snake grows!
                        do {
                            randA = (int)(Math.random()*(BOARD_SIZE-1));
                            randB= (int)(Math.random()*(BOARD_SIZE-1));
                        } while(board[randA][randB] != EMPTY);
                        board[randA][randB] = FOOD; //randomly adds an apple
                    } else if (board[snake.body.get(0).y][BOARD_SIZE - 1] == SNAKE) {
                        snake.isAlive = false; //snake dies if intersects self
                        snake.body.add(0, new Point(BOARD_SIZE - 1, snake.body.get(0).y)); //move front of snake forward
                        snake.body.remove(snake.body.size() - 1); //remove last piece, move forward
                    }
                }
                break;
        }
    }

}
