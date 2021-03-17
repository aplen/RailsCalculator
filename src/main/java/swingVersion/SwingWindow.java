package swingVersion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;


public class SwingWindow extends JFrame implements FocusListener {

    Cut cut = new Cut();
    RailsCutter railsCutter = new RailsCutter(cut);

    private JTextField jRailLength, jSegmentLength, jSegmentCount, jSawWidth;
    private JLabel title, jRailLengthDesc, jSegmentLengthDesc, jSegmentCountDesc, jSawWidthDesc;
    private JButton solveButton, exitButton;
    private JPanel titlePart, centerPart, bottomPart;

    SwingWindow() {// konstruktor okna
        super("Kalkulator zużycia szyn trapezowych SMT-60 ");
        setBounds(200, 10, 700, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        title = new JLabel("Wprowadź dane:");

        jRailLength = new JTextField("300.00", 10);
        jRailLengthDesc = new JLabel("Długość szyny w cm:");

        jSawWidth = new JTextField("0.2", 10);
        jSawWidthDesc = new JLabel("Grubość ostrza piły w cm:");

        jSegmentLength = new JTextField("0", 10);
        jSegmentLengthDesc = new JLabel("Długość odcinka w cm:");

        jSegmentCount = new JTextField("0", 10);
        jSegmentCountDesc = new JLabel("Ilość potrzebnych odcinków:");

        JTextArea solutionDesc = new JTextArea("Wynik:", 10, 2);
        solutionDesc.setEditable(false);
        solutionDesc.setOpaque(false);


        solveButton = new JButton("Oblicz");
        solveButton.setPreferredSize(new Dimension(50, 20));
        exitButton = new JButton("Wyjście");
        exitButton.setPreferredSize(new Dimension(50, 20));
        // jAnswer.setEditable(false);

        jRailLength.addFocusListener(this); // tworzenie reakcji na klikanie oraz aktywację/deaktywację pól
        jSegmentLength.addFocusListener(this);
        jSegmentCount.addFocusListener(this);
        jSawWidth.addFocusListener(this);
        ActionListener listen = (ActionEvent e) -> {
            Object source = e.getSource();
            if (source == solveButton) {

                parseData();
                if (railsCutter.isSegmentShorterThanRailAndNoZeroValues()) {
                    solutionDesc.setText(railsCutter.returnAnswer());
                    title.setText("Wykonano obliczenia. Wprowadz nowe dane:");
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
        return new Insets(40, 20, 20, 20);
    }

    @Override
    public void focusGained(FocusEvent e1e) {
    }// reakcja na aktywacje elementu

    @Override
    public void focusLost(FocusEvent e1e) {// reakcja na wyjscie z elementu
        //parseData();
    }

}