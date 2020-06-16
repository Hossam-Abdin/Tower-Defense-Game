package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import static game.GameGui.SCREEN_SIZE;

public class GameStarterGui {

    public GameStarterGui() throws FileNotFoundException {
        // initialization
        JFrame frame = new JFrame();
        JButton startButton = new JButton("Start");
        JButton exitButton = new JButton("Exit");
        JPanel buttonPanel = new JPanel();
        JLabel gameNameLabel = new JLabel();
        JLabel gamePicLabel = new JLabel();

        // Setting up th Button appearance
        setupButtonAppearance(startButton);
        setupButtonAppearance(exitButton);

        // Setting up the pic label appearance
        gamePicLabel.setIcon(new ImageIcon(new ImageIcon("images/Castle.png").getImage().
                getScaledInstance(350, 350, Image.SCALE_DEFAULT)));
        gamePicLabel.setHorizontalAlignment(JLabel.CENTER);

        // Setting up the name label appearance
        gameNameLabel.setText("The Fortress");
        gameNameLabel.setFont(new Font("Serif", Font.PLAIN, 55));
        gameNameLabel.setHorizontalAlignment(JLabel.CENTER);

        // Setting up the start frame
        buttonPanel.add(BorderLayout.NORTH, startButton);
        buttonPanel.add(BorderLayout.CENTER, exitButton);

        // Adding the button panel
        frame.getContentPane().add(gamePicLabel, BorderLayout.NORTH);
        frame.getContentPane().add(gameNameLabel, BorderLayout.CENTER);
        frame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        // Setting up the buttons action listerners
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new GameGui();
                frame.dispose();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        });

        // Basic settings for the frame
        setupFrameSettings(frame);

        //Should be here to avoid the not appearing bug
        setupFrameAppearance(frame);
    }

    /**
     * Setting the basic settings for the frame
     * @param f
     */
    private void setupFrameSettings(JFrame f){
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setPreferredSize(new Dimension(SCREEN_SIZE.width, SCREEN_SIZE.height));
        f.pack();
    }


    /**
     * Setting the general appearance for the frame
     * @param f
     */
    private void setupFrameAppearance(JFrame f){
        f.setVisible(true);
        f.setResizable(false);
        f.setSize(new Dimension(1300, 800));
    }

    /**
     * Setting up buttons appearance
     * @param b
     */
    private void setupButtonAppearance(JButton b){
        b.setPreferredSize(new Dimension(120, 100));
        b.setBackground(new Color(0, 0, 0));
        b.setForeground(Color.WHITE);
        b.setFocusPainted(false);
        b.setFont(new Font("Tahoma", Font.BOLD, 16));
    }
}
