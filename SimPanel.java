/*
 * Author : Shantanu Mandpe
 * Date : 17/06/2022
 * Lab : IE-SEL
 * Class is the observer for the distributed observer pattern, the GUI part of the project and the game thread is implemented here. 
 * It is also used to set the new values of the parameters (variables)
 * */

//import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.Graphics;
import java.awt.Graphics2D;


public class SimPanel extends JPanel implements Observer, ActionListener , Runnable{

    private static IRobotSystemDatabase irsd = null;
    private static IHardwareController ihc = null;
    private static AppDisplay app = null;
    
    public static void registerStub(IRobotSystemDatabase robot, SimPanel panel) throws RemoteException{
        Observer stub = (Observer) UnicastRemoteObject.exportObject(new SimPanel(irsd, ihc,userCommand), 0);
        robot.addObserver(stub);
    }

    
    JPanel panel1 = new JPanel();
    JPanel panel2 = new JPanel();
    JPanel panel3 = new JPanel();

    JFrame frame = new JFrame();
    JFrame frame1 = new JFrame();

    // buttons
	JButton displayButton = new JButton("DISPLAY");
	JButton startButton = new JButton("START");
	JButton stopButton = new JButton("STOP");

	// labels
	JLabel batteryLabel = new JLabel("Battery Percentage = ");
	JLabel currentLabel = new JLabel("Current Location = ");
	JLabel dockLabel = new JLabel("Dock Location = ");

    // SCREEN SETTINGS
    final int originalTileSize = 16;
    final int scale = 3;

    final int tileSize = originalTileSize * scale;
    final int maxScreenCol = 18;
    final int maxScreenRow = 16;
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;

    // Game time
    Thread gameThread;

    // players default position and movement
    private int playerX = 100;
    private int playerY = 100;
    private int dockX = 600;
    private int dockY = 600;
    private int battery = 100;
    int playerSpeed = 4;

    // FPS and Time
    int FPS = 30;
    int time = 0;

    static int  userCommand = 1;

    public SimPanel(IRobotSystemDatabase irsd, IHardwareController ihc, int userCommand) throws RemoteException{
        SimPanel.irsd = irsd;
        SimPanel.ihc = ihc;
        SimPanel.userCommand = userCommand;
    }
    
    public SimPanel()  throws RemoteException{
        app = new AppDisplay(irsd);

        this.setBackground(Color.BLACK);
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        

        panel3.setLayout(new GridLayout(1, 4));
        panel3.add(displayButton);
        panel3.add(startButton);
        panel3.add(stopButton);
        panel3.setBackground(Color.BLACK);

        panel2.setBackground(Color.white);
        panel2.setLayout(new GridLayout(3, 1));
        panel2.add(batteryLabel);
        panel2.add(currentLabel);
        panel2.add(dockLabel);

        // setting up the buttons
        displayButton.addActionListener(this);
        displayButton.setBackground(Color.WHITE);
        startButton.addActionListener(this);
        startButton.setBackground(Color.WHITE);
        stopButton.addActionListener(this);
        stopButton.setBackground(Color.WHITE);

        // setting up the frames
        frame.add(panel3, BorderLayout.PAGE_END);
        frame.add(this, BorderLayout.CENTER);
        frame.setResizable(false);
        frame.setTitle("App Display 1");
        frame.setBounds(0, 0, screenWidth, screenHeight);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        frame1.add(panel2);
        frame1.setResizable(false);
        frame1.setTitle("App Display 2");
        frame1.setBounds(0, 0,  screenWidth/ 3,  screenHeight/ 3);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.setLocationRelativeTo(null);
        frame1.setVisible(false);
        
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    // stopping the game thread
    @SuppressWarnings("deprecation")
    public void stopGameThread() {
        gameThread.stop();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000 / FPS; // 0,016666 seconds
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;

        while (gameThread != null) {
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                try {
                    update();
                    repaint();
                } catch (RemoteException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                //panel1.repaint();
                delta--;
                time++;
            }

            if (timer >= 1000000000) {
                // updating the position of the of the robot and the dock
                System.out.println("Time : " + time/FPS);
                app.display();
                // updating the battery percentage
                if (playerX != dockX && playerY != dockY) {
                    battery = battery - 4;
                    try {
                        set();
                    } catch (RemoteException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                } else if (playerX == dockX && playerY == dockY && battery <= 100) {
                    battery = battery + 4;
                    try {
                        set();
                    } catch (RemoteException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                timer = 0;
            }
        }
    }

    @SuppressWarnings("deprecation")
    // updating the position during the simulation
    public void update() throws RemoteException  {
        if (battery < 80) {
            if (playerX != dockX && playerY != dockY) {
                    playerX = ihc.controller(playerX);
                    playerY = ihc.controller(playerY);
            } else {
                playerX = dockX;
                playerY = dockY;
            }
        }

        if (battery > 100) {
            System.out.println("Simulation Stopped");
            gameThread.stop();
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        if (battery >= 60) {
            g2.setColor(Color.CYAN);
            g2.fillRect(playerX, playerY, tileSize / 4, tileSize / 4);
        } else if (battery >= 30) {
            g2.setColor(Color.ORANGE);
            g2.fillRect(playerX, playerY, tileSize / 4, tileSize / 4);
        } else {
            g2.setColor(Color.RED);
            g2.fillRect(playerX, playerY, tileSize / 4, tileSize / 4);
        }

        g2.setColor(Color.WHITE);
        g2.fillRect(dockX, dockY, tileSize, tileSize);
        g2.setColor(getBackground());
        g2.fillRect(dockX, dockY, tileSize / 4, tileSize / 4);

        g2.dispose();
    }

    @Override
    public void update(IObservable o, Object arg) throws RemoteException {
        // TODO Auto-generated method stub
        if (o == irsd) {
            // System.out.println("from Panel ov : " + arg + irsd.getValue());
            playerX = irsd.getCurrentLocationX();
            playerY = irsd.getCurrentLocationY();
            dockX = irsd.getDockLocationX();
            dockY = irsd.getDockLocationY();
            battery = irsd.getBatteryPercentage();
        } else // if (obs == ov)
        {
            // System.err.println(tmp); // checking stub reference
            // int val = tmp.getValue();
            playerX = ((IRobotSystemDatabase) o).getCurrentLocationX();
            playerY = ((IRobotSystemDatabase) o).getCurrentLocationY();
            dockX = ((IRobotSystemDatabase) o).getDockLocationX();
            dockY = ((IRobotSystemDatabase) o).getDockLocationY();
            battery = ((IRobotSystemDatabase) o).getBatteryPercentage();
            // System.out.println("from Panel ov : " + arg + irsd.getValue());
        }
    }

    public void set() throws RemoteException {
        irsd.setCurrentLocationX(playerX);
        irsd.setCurrentLocationY(playerY);
        irsd.setDockLocationX(dockX);
        irsd.setDockLocationY(dockY);
        irsd.setBatteryPercentage(battery);
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        // TODO Auto-generated method stub
        if (e.getSource() == displayButton) {
            frame1.setVisible(true);
            // panel1.app1.display(); //to display in the console
            try {
                currentLabel.setText("Current Location is : " + app.getRsd().getCurrentLocationX() + ", "
                        + app.getRsd().getCurrentLocationY());
            } catch (RemoteException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            try {
                dockLabel.setText("Dock Location is : " + app.getRsd().getDockLocationX() + ", "
                        + app.getRsd().getDockLocationY());
            } catch (RemoteException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            try {
                if (app.getRsd().getBatteryPercentage() >= 100) {
                    batteryLabel.setText("Battery Percentage is : " + 100);
                } else {
                    batteryLabel.setText("Battery Percentage is : " + app.getRsd().getBatteryPercentage());
                }
            } catch (RemoteException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }

        } else if (e.getSource() == startButton) { // starting the simulation if start button is pressed and printing a
                                                   // statement depending on the algorithm
            startGameThread();
            System.out.println("ROBOT SOFTWARE SYSTEM");
            if (userCommand == 1) {
                System.out.println("Task 1 is starting");
            } else if (userCommand == 2) {
                System.out.println("Task 2 is starting");
            } else if (userCommand == 3) {
                System.out.println("Task 3 is starting");
            }
            System.out.println("StartButton pressed ");
        } else if (e.getSource() == stopButton) { // stoping the simulation if stop button is pressed and printing a
                                                  // statement
            stopGameThread();
            System.out.println("StopButton pressed ");
        }
    }
}




    

