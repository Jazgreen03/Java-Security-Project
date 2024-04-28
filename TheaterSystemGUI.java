package ArtistTheaterSystem;

import javax.swing.*;

import ArtistTheaterSystem.TheaterSystem.NoSeatAvailableException;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

public class TheaterSystemGUI extends JFrame {
	private static final long serialVersionUID = 1L;
    private TheaterSystem theaterSystem;
    private JTextField dateField;
    private JTextField timeField;
    private JTextField seatNumberField;
    private JTextArea outputArea;
    private JComboBox<String> seatNumberComboBox;

    public TheaterSystemGUI() {
        setLayout(new FlowLayout());

        add(new JLabel("Date:"));
        dateField = new JTextField(14);
        add(dateField);

       
        JButton DisplayShows = new JButton("Display Shows Available");
        DisplayShows.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String date = dateField.getText();
                String time = timeField.getText();
                theaterSystem = new TheaterSystem(date, time);
                if (date.equals("04-20-2024")) {
                    outputArea.append("Show Available on 04-20:\nShow time: 1:00 PM\n");
                } else if (date.equals("04-28-2024")) {
                    outputArea.append("Show Available on 04-28:\nShow times: 8:00 PM\n");
                } else {
                    outputArea.append("No show Available! \n Choose Another Date \n");
                }
                
               
            }
        });
        add(DisplayShows);
        
        add(new JLabel("Time:"));
        timeField = new JTextField(10);
        add(timeField);
        
        
        
        
        add(new JLabel("Seat Number:"));
        seatNumberComboBox = new JComboBox<>();
        for (int i = 1; i <= 30; i++) {
            seatNumberComboBox.addItem(String.valueOf(i));
            
        }
        add(seatNumberComboBox);
        
        add(seatNumberComboBox);
        JButton createShowButton = new JButton("Request Show");
        createShowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String date = dateField.getText();
                String time = timeField.getText();
                theaterSystem = new TheaterSystem(date, time);
                String selectedSeat = (String) seatNumberComboBox.getSelectedItem();
                int seatNumber = Integer.parseInt(selectedSeat);
                try {
					theaterSystem.requestseat(seatNumber);
				} catch (NoSeatAvailableException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                boolean isFull = theaterSystem.isArtistTheaterFull();
                if (isFull) {
                    outputArea.append("The theater seat is NOT available..\n");
                } else {
                    outputArea.append("The theater seat is available.\n");
                }
            
                if (date.equals("04-20-2024") && time.equalsIgnoreCase("1:00PM")) {
                    outputArea.append("Show Confirmed at Date: " + date + "\n Time: " + time + "\n Buy Ticket Now!\n");
                
                } else if (date.equals("04-28-2024") && time.equalsIgnoreCase("8:00PM")) {
                    outputArea.append("Show Confirmed at Date: " + date + "\n Time: " + time + "\n Buy Ticket Now!\n");
             
                } else {
                    outputArea.append("No Show Available!\n");
                }
            }
        });
        add(createShowButton);
        add(Box.createVerticalStrut(20));
        
      
        
       
        //add(new JLabel("Seat Number:"));
        //seatNumberField = new JTextField(10);
        //add(seatNumberField);
        

        JButton buyTicketButton = new JButton("Buy Ticket");
        buyTicketButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedSeat = (String) seatNumberComboBox.getSelectedItem();
                int seatNumber = Integer.parseInt(selectedSeat);
                try {
                    theaterSystem.buyTicket(seatNumber);
                    String date = dateField.getText();
                    String time = timeField.getText();
                    outputArea.append("Show Confirmed! \n " + date + ":" + time +"\n Seat Number = " + seatNumber + "\n");
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
        
        
        JButton nextPurchaseButton = new JButton("Continue to Next Purchase");
        nextPurchaseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Clear the date and time fields
                dateField.setText("");
                timeField.setText("");
                
                // Clear the output area
                outputArea.setText("");
               
                // Display the last purchased seat number
                Set<Integer> purchasedSeats = theaterSystem.getPurchasedSeats();
                updatePurchasedSeatsDisplay(purchasedSeats);
                
                
                // Add any other initialization or setup logic here
            }
        });
        add(nextPurchaseButton);


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
    public void updatePurchasedSeatsDisplay(Set<Integer> purchasedSeats) {
        outputArea.append("Purchased Seats:\n");
        for (int seat : purchasedSeats) {
            outputArea.append(seat + "\n");
        }
    }
}
