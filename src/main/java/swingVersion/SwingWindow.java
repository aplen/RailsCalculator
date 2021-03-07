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
    private JLabel title, title1, jRailLengthDesc, jSegmentLengthDesc, jSegmentCountDesc, jSawWidthDesc, solutionDesc;
    private JButton solveButton, exitButton;
    private JPanel titlePart, centerPart, bottomPart;

    SwingWindow() {// konstruktor okna
        super("Kalkulator zużycia szyn trapezowych SMT-60 ");
        setBounds(200, 10, 640, 480);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        title = new JLabel("Wprowadź dane:");
        //title1 = new JLabel("tytul1");

        jRailLength = new JTextField("300",10);
        jRailLengthDesc = new JLabel("Długość szyny w cm:");

        jSawWidth = new JTextField("0.2", 10);
        jSawWidthDesc = new JLabel("grubość ostrza piły w cm:");

        jSegmentLength = new JTextField("0", 10);
        jSegmentLengthDesc = new JLabel("Długość odcinka w cm:");

        jSegmentCount = new JTextField("0", 10);
        jSegmentCountDesc = new JLabel("Ilość potrzebnych odcinków:");

        JTextArea solutionDesc = new JTextArea();
        solutionDesc.setEditable(false);
        solutionDesc.setOpaque(false);


        //solutionDesc = new JLabel(
          //      );
        solveButton = new JButton("Oblicz");
        solveButton.setPreferredSize(new Dimension(100, 60));
        exitButton = new JButton("Wyjście");
        exitButton.setPreferredSize(new Dimension(100, 60));
      // jAnswer.setEditable(false);

        jRailLength.addFocusListener(this); // tworzenie reakcji na klikanie oraz aktywację/deaktywację pól
        jSegmentLength.addFocusListener(this);
        jSegmentCount.addFocusListener(this);
        jSawWidth.addFocusListener(this);
        //title.setText(" ");
        //title1.setText("");
        ActionListener listen = (ActionEvent e) -> {
            //title.setText(" ");
            //title1.setText("");
            Object source = e.getSource();
            if (source == solveButton) {

                parseData();
                railsCutter.oblicz(cut);
                solutionDesc.setText(railsCutter.getAnswer());
                title.setText("Wykonano obliczenia. Wprowadz nowe dane:");
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
        GridLayout bottomGrid = new GridLayout(2,1,1,10);
        bottomPart.setLayout(bottomGrid);
        bottomPart.add(solveButton);
        bottomPart.add(exitButton);
        bottomPart.add(solutionDesc);

        setLayout(new BorderLayout(10, 10));
        add(titlePart, BorderLayout.NORTH);
        add(centerPart, BorderLayout.CENTER);
        add(bottomPart, BorderLayout.SOUTH);
        bottomPart.setPreferredSize(new Dimension(100, 200));

        setVisible(true);
    }


    private void parseData() {// zamiana danych wprowadzonych przez usera na wartosci double + obsluga formatu
//        title1.setText("");
        title.setText(" ");
        try {
            cut.setRailLength(Double.parseDouble(jRailLength.getText().replace(",",".")));

        } catch (IllegalArgumentException e) {
            title.setText("Błędny format danych! Wprowadź ponownie:");
            jRailLength.setText("0.00");
        }
        try {
            cut.setSawWidth(Double.parseDouble(jSawWidth.getText().replace(",",".")));
        } catch (IllegalArgumentException e) {
            title.setText("Błędny format danych! Wprowadź ponownie:");
            jSawWidth.setText("0.00");
        }
        try {
            cut.setSegmentCount(Double.parseDouble(jSegmentCount.getText().replace(",",".")));
        } catch (IllegalArgumentException e) {
            title.setText("Błędny format danych! Wprowadź ponownie:");
            jSegmentCount.setText("0.00");
        }
        try {
            cut.setSegmentLength(Double.parseDouble(jSegmentLength.getText().replace(",",".")));
        } catch (IllegalArgumentException e) {
            title.setText("Błędny format danych! Wprowadź ponownie:");
            jSegmentLength.setText("0.00");
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
        parseData();
    }

}