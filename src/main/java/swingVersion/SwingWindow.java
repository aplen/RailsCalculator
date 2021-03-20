package swingVersion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class SwingWindow extends JFrame implements FocusListener {

    Cut cut = new Cut();
    RailsCutter railsCutter = new RailsCutter(cut);

    private JTextField jRailLength, jSegmentLength, jSegmentCount, jSawWidth;
    private JLabel title, jRailLengthDesc, jSegmentLengthDesc, jSegmentCountDesc, jSawWidthDesc;
    private JButton solveButton, exitButton;
    private JPanel titlePart, centerPart, bottomPart;

    SwingWindow() {// konstruktor okna
        super("SOLARSPOT - Kalkulator odcinków szyn");
        setBounds(200, 10, 700, 600);


        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        title = new JLabel("Wprowadź dane:");

        jRailLength = new JTextField("300.00", 10);
        jRailLengthDesc = new JLabel("Długość szyny w cm:");

        jSawWidth = new JTextField("0.20", 10);
        jSawWidthDesc = new JLabel("Grubość ostrza piły w cm:");

        jSegmentLength = new JTextField("", 10);
        jSegmentLengthDesc = new JLabel("Długość odcinka w cm:");

        jSegmentCount = new JTextField("", 10);
        jSegmentCountDesc = new JLabel("Ilość potrzebnych odcinków:");
        JTextArea solutionDesc = new JTextArea("Wynik:", 10, 2);
        solutionDesc.setEditable(false);
        solutionDesc.setOpaque(false);


        solveButton = new JButton("Oblicz (ENTER)");
        solveButton.setPreferredSize(new Dimension(50, 20));
        exitButton = new JButton("Wyjście (ESC)");
        exitButton.setPreferredSize(new Dimension(50, 20));

        jRailLength.addFocusListener(this);
        jSegmentLength.addFocusListener(this);
        jSegmentCount.addFocusListener(this);
        jSawWidth.addFocusListener(this);

        jSegmentLength.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent ke) {
                if(ke.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    System.exit(0);
                }
            }
        });

        jSegmentCount.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent ke) {
                if(ke.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    System.exit(0);
                }
            }
        });
        jRailLength.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent ke) {
                if(ke.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    System.exit(0);
                }
            }
        });
        jSawWidth.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent ke) {
                if(ke.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    System.exit(0);
                }
            }
        });
        ActionListener listen = (ActionEvent e) -> {
            Object source = e.getSource();
            if (source == solveButton) {

                parseData();
                if (railsCutter.isSegmentShorterThanRailAndNoZeroValues()) {
                    solutionDesc.setText(railsCutter.returnAnswer());
                    title.setText("Wykonano obliczenia. Wprowadź nowe dane:");
                } else {
                    title.setText("Wprowadzone dane są błędne");

                }

            }
            if (source == exitButton) {
                System.exit(0);
            }

        };
        solveButton.addActionListener(listen);
        exitButton.addActionListener(listen);


        titlePart = new JPanel(); // rozmieszcznie elementów w trzech grupach w okreslonej kolejności
        //titlePart.add(title1);
        titlePart.add(title);

        centerPart = new JPanel();
        GridLayout centerGrid = new GridLayout(4, 2, 1, 10);
        centerPart.setLayout(centerGrid);
        centerPart.add(jRailLengthDesc);
        centerPart.add(jRailLength);
        centerPart.add(jSawWidthDesc);
        centerPart.add(jSawWidth);
        centerPart.add(jSegmentCountDesc);
        centerPart.add(jSegmentCount);
        centerPart.add(jSegmentLengthDesc);
        centerPart.add(jSegmentLength);

        bottomPart = new JPanel();
        GridLayout bottomGrid = new GridLayout(3, 1, 1, 10);
        bottomPart.setLayout(bottomGrid);
        bottomPart.add(solveButton);
        JScrollPane textWithScroll = new JScrollPane(solutionDesc);
        bottomPart.add(textWithScroll);
        bottomPart.add(exitButton);

        this.getRootPane().setDefaultButton(solveButton);

        setLayout(new BorderLayout(5, 5));
        add(titlePart, BorderLayout.NORTH);
        add(centerPart, BorderLayout.CENTER);
        centerPart.setPreferredSize(new Dimension(100, 100));
        add(bottomPart, BorderLayout.SOUTH);
        bottomPart.setPreferredSize(new Dimension(100, 300));
        setVisible(true);
    }


    private void parseData() {// zamiana danych wprowadzonych przez usera na wartosci double + obsluga formatu
        title.setText(" ");
        try {
            cut.setRailLength(Double.parseDouble(jRailLength.getText().replace(",", ".")));

        } catch (IllegalArgumentException e) {
            title.setText("Błędny format danych! Wprowadź ponownie:");
            cut.setRailLength(0.00);
            // jRailLength.setText("0.00");
        }
        try {
            cut.setSawWidth(Double.parseDouble(jSawWidth.getText().replace(",", ".")));
        } catch (IllegalArgumentException e) {
            title.setText("Błędny format danych! Wprowadź ponownie:");
            cut.setSawWidth(0.00);
            // jSawWidth.setText("0.00");
        }
        try {
            cut.setSegmentCount(Double.parseDouble(jSegmentCount.getText().replace(",", ".")));
        } catch (IllegalArgumentException e) {
            title.setText("Błędny format danych! Wprowadź ponownie:");
            cut.setSegmentCount(0.00);
            // jSegmentCount.setText("0.00");
        }
        try {
            cut.setSegmentLength(Double.parseDouble(jSegmentLength.getText().replace(",", ".")));
        } catch (IllegalArgumentException e) {
            title.setText("Błędny format danych! Wprowadź ponownie:");
            cut.setSegmentLength(0.00);
            // jSegmentLength.setText("0.00");
        }


    }

    @Override
    public Insets getInsets() {// ramka okna
        return new Insets(30, 15, 15, 15);
    }

    @Override
    public void focusGained(FocusEvent e1e) {
    }// reakcja na aktywacje elementu

    @Override
    public void focusLost(FocusEvent e1e) {// reakcja na wyjscie z elementu
        //parseData();
    }



}