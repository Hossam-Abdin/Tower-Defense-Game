package game;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;

public class GameGui {
    private JFrame frame;
    private GameEngine engine;
    private JPanel topPanel;
    protected static JLabel timeLabel;
    protected static JLabel coinsLabel;
    protected static JLabel healthLabel;
    public static final String HEART_EMOJI = new String(Character.toChars(023544));
    Font font = new Font("SansSerif", Font.BOLD, 18);
    public static final Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();

    public GameGui() {
        frame = new JFrame();
        topPanel = new JPanel();
        timeLabel = new JLabel("Next Wave: 00:10", JLabel.CENTER);
        coinsLabel = new JLabel("$: 100", JLabel.LEFT);
        healthLabel = new JLabel(HEART_EMOJI + " :" + "100", JLabel.RIGHT);

        // Setting up the font size
        timeLabel.setFont(font);
        coinsLabel.setFont(font);
        healthLabel.setFont(font);

        // Basic settings for the frame
        setupFrameSettings(frame);

        // Setting the top panel
        topPanel.setPreferredSize(new Dimension(frame.getWidth(), frame.getHeight() / 20));
        topPanel.setLayout(new FlowLayout(70, SCREEN_SIZE.width / 7, 10));
        topPanel.add(coinsLabel);
        topPanel.add(timeLabel);
        topPanel.add(healthLabel);

        //Should be here to avoid the not appearing bug
        setupFrameAppearance(frame);
        // Setting up the game engine
        try {
            engine = new GameEngine(24);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        engine.setPreferredSize(new Dimension(frame.getWidth(), frame.getHeight()));
        // Adding the different components to the main frame
        frame.getContentPane().add(BorderLayout.NORTH, topPanel);
        frame.getContentPane().add(BorderLayout.CENTER, engine);
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
}
