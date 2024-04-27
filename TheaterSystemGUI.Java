package ArtistTheaterSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TheaterSystemGUI extends JFrame {
	private static final long serialVersionUID = 1L;
    private TheaterSystem theaterSystem;
    private JTextField dateField;
    private JTextField timeField;
    private JTextField seatNumberField;
    private JTextArea outputArea;

    public TheaterSystemGUI() {
        setLayout(new FlowLayout());

        add(new JLabel("Date:"));
        dateField = new JTextField(10);
        add(dateField);

        add(new JLabel("Time:"));
        timeField = new JTextField(10);
        add(timeField);

        JButton createShowButton = new JButton("Create Show");
        createShowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String date = dateField.getText();
                String time = timeField.getText();
                theaterSystem = new TheaterSystem(date, time);
                outputArea.append("Show created for date " + date + ", time " + time + "\n");
            }
        });
        add(createShowButton);

        add(new JLabel("Seat Number:"));
        seatNumberField = new JTextField(10);
        add(seatNumberField);

        JButton buyTicketButton = new JButton("Buy Ticket");
        buyTicketButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int seatNumber = Integer.parseInt(seatNumberField.getText());
                try {
                    theaterSystem.buyTicket(seatNumber);
                    outputArea.append("Ticket bought for seat number " + seatNumber + "\n");
                } catch (TheaterSystem.NoSeatAvailableException ex) {
                    outputArea.append(ex.getMessage() + "\n");
                }
            }
        });
        add(buyTicketButton);

        JButton returnTicketButton = new JButton("Return Ticket");
        returnTicketButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int seatNumber = Integer.parseInt(seatNumberField.getText());
                theaterSystem.returnTicket(seatNumber);
                outputArea.append("Ticket returned for seat number " + seatNumber + "\n");
            }
        });
        add(returnTicketButton);

        outputArea = new JTextArea(10, 30);
        add(new JScrollPane(outputArea));

        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TheaterSystemGUI();
            }
        });
    }
}
