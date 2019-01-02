import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

class Visual extends JPanel {
    //will draw the game

    Board b;

    public Visual(Board b) {
        super();
        this.b = b;
    }

    private void doDrawing(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        //draw grid
        Color outline = new Color(212,187,255);
        Color empty = new Color(102, 102, 153);
        Color food = new Color(153, 17, 23);
        Color snake = new Color(54, 153, 38);
        for (int x = 0; x < 400; x += 20) {
            for (int y = 0; y < 400; y += 20) {
                if (b.board[y/20][x/20] == Board.EMPTY) {
                    g2d.setColor(outline);
                    g2d.drawRect(x, y, 20, 20);
                    g2d.setColor(empty);
                    g2d.fillRect(x, y, 20, 20);
                } else if (b.board[y/20][x/20] == Board.FOOD) {
                    g2d.setColor(outline);
                    g2d.drawRect(x, y, 20, 20);
                    g2d.setColor(food);
                    g2d.fillRect(x, y, 20, 20);
                } else if (b.board[y/20][x/20] == Board.SNAKE) {
                    g2d.setColor(outline);
                    g2d.drawRect(x, y, 20, 20);
                    g2d.setColor(snake);
                    g2d.fillRect(x, y, 20, 20);
                }
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }
}

public class Window extends JFrame {
    //draw and make move cycles

    public static final int cycleSpeed = 250; //rate game cycles occur in milliseconds (i.e. 2 sec)


    public Window(Board b) {
        initUI(b);
    }

    private void initUI(Board b) {

        Visual visual = new Visual(b);
        add(visual);

        setResizable(false);
        setSize(400,400);
        setTitle("Snake");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {

        Board board = new Board();

        Window ex = new Window(board);
        ex.setVisible(true);

        //adds direction controls
        ex.addKeyListener(new KeyListener() {
                              @Override
                              public void keyTyped(KeyEvent keyEvent) {

                              }

                              @Override
                              public void keyPressed(KeyEvent x) {
                                  switch (x.getKeyCode()) {
                                      case KeyEvent.VK_UP:
                                          board.snakeDirection = Board.NORTH;
                                          break;
                                      case KeyEvent.VK_RIGHT:
                                          board.snakeDirection = Board.EAST;
                                          break;
                                      case KeyEvent.VK_DOWN:
                                          board.snakeDirection = Board.SOUTH;
                                          break;
                                      case KeyEvent.VK_LEFT:
                                          board.snakeDirection = Board.WEST;
                                          break;

                                  }
                              }

                              @Override
                              public void keyReleased(KeyEvent keyEvent) {

                              }
                          }
        );
/*
        ex.addKeyListener(new KeyListener() {
                              @Override
                              public void keyTyped(KeyEvent keyEvent) {

                              }

                              @Override
                              public void keyPressed(KeyEvent d) {
                                  board.snakeDirection = Board.EAST;
                              }

                              @Override
                              public void keyReleased(KeyEvent keyEvent) {

                              }
                          }
        );

        ex.addKeyListener(new KeyListener() {
                              @Override
                              public void keyTyped(KeyEvent keyEvent) {

                              }

                              @Override
                              public void keyPressed(KeyEvent s) {
                                  board.snakeDirection = Board.SOUTH;
                              }

                              @Override
                              public void keyReleased(KeyEvent keyEvent) {

                              }
                          }
        );

        ex.addKeyListener(new KeyListener() {
                              @Override
                              public void keyTyped(KeyEvent keyEvent) {

                              }

                              @Override
                              public void keyPressed(KeyEvent a) {
                                  board.snakeDirection = Board.WEST;
                              }

                              @Override
                              public void keyReleased(KeyEvent keyEvent) {

                              }
                          }
        );
*/
        Timer timer = new Timer(cycleSpeed, null);
        timer.setRepeats(true);
        timer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                //run cycle of game
                if (board.isAlive()) {
                    board.step();
                } else {
                    timer.stop();
                    System.out.println("Game over, score: " + board.snake.body.size());
                }
                ex.repaint(); //updates the gui
            }
        });
        timer.start();

    }
/*
    public static void main(String[] args) {
        Board board = new Board();

        Timer timer = new Timer(cycleSpeed, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                //run cycle of game
                if (board.isAlive()) {
                    board.step();
                } else {
                    //timer.stop();
                }
            }
        });
        timer.setRepeats(true);
        timer.start();

    }
*/
}
