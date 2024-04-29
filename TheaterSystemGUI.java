package ArtistTheaterSystem;

import javax.swing.*;
import javax.swing.JButton;

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
    //private JTextField seatNumberField;
    private JTextArea outputArea;
    private JComboBox<String> seatNumberComboBox;

    public TheaterSystemGUI() {
    	theaterSystem = new TheaterSystem("April 20", "1:00 PM");
        setLayout(new FlowLayout());

        add(new JLabel("Date:"));
        dateField = new JTextField(14);
        add(dateField);

        JButton DisplayShows = new JButton("Display Shows Available");
        DisplayShows.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String date = dateField.getText();
                if (date.equals("04-20-2024")) {
                    outputArea.append("Kung Fu Panda 4\n Available on 04-20:\nShow time: 1:00 PM\n");
                } else if (date.equals("04-28-2024")) {
                    outputArea.append("Kung Fu Panda 4\n Available on 04-28:\nShow times: 8:00 PM\n");
                } else {
                    outputArea.append("No Shows Available! \n Choose Another Date \n");
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
        
       // add(seatNumberComboBox);
        JButton createShowButton = new JButton("Request Show");
        createShowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String date = dateField.getText();
                String time = timeField.getText();
                String selectedSeat = (String) seatNumberComboBox.getSelectedItem();
                int seatNumber = Integer.parseInt(selectedSeat);
                try {
					theaterSystem.requestseat(seatNumber);
				} catch (NoSeatAvailableException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                
                if (date.equals("04-20-2024") && (time.equalsIgnoreCase("1:00PM") || time.equalsIgnoreCase("1:00 PM"))) {
                    outputArea.append("Show requested at Date: " + date + "\n Time: " + time + "\n Buy Ticket Now!\n");
                
                } else if (date.equals("04-28-2024") && (time.equalsIgnoreCase("8:00PM") || time.equalsIgnoreCase("8:00 PM"))) {
                    outputArea.append("Show requested at Date: " + date + "\n Time: " + time + "\n Buy Ticket Now!\n");
             
                } else {
                    outputArea.append("No Show Available!\n");
                }
            }
        });
        add(createShowButton);
        add(Box.createVerticalStrut(20));
        
        JButton buyTicketButton = new JButton("Buy Ticket");
        buyTicketButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedSeat = (String) seatNumberComboBox.getSelectedItem();
                int seatNumber = Integer.parseInt(selectedSeat);
                boolean isFull = theaterSystem.isArtistTheaterFull();
                if (isFull) {
                    outputArea.append("Theater is Full! Select Another Date\n");
                    return; // Exit the actionPerformed method if the theater is full
                }
                try {
                    theaterSystem.buyTicket(seatNumber);
                    String date = dateField.getText();
                    String time = timeField.getText();
                    outputArea.append("Show Confirmed! \n " + date + ":" + time +"\n Seat Number = " + seatNumber + "\n");
                } catch (TheaterSystem.NoSeatAvailableException ex) {
                	outputArea.append("Seat unavailable for the requested show on " + dateField.getText() + " at " + timeField.getText() + ".\n Please choose a different seat.\n");
                }
            }
        });
        add(buyTicketButton);
        
        JButton returnTicketButton = new JButton("Return Ticket");
        returnTicketButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	String Date = dateField.getText();
                String Time = timeField.getText();
            	String selectedSeat = (String) seatNumberComboBox.getSelectedItem();
                int seatNumber = Integer.parseInt(selectedSeat);
                    theaterSystem.returnTicket(seatNumber);
                    outputArea.append("Ticket returned for " + Date + " : " + Time + "\n Seat Number =" + seatNumber + "\n");
                // Update the GUI to display the current state of purchasedSeats
                updatePurchasedSeatsDisplay();
            }
        });
        add(returnTicketButton);
        
        
        JButton nextPurchaseButton = new JButton("Continue to Next Purchase");
        nextPurchaseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	String savedDate = dateField.getText();
                String savedTime = timeField.getText();
                outputArea.append("Showing on: " + savedDate +" " + savedTime);
                // Clear the date and time fields
                dateField.setText("");
                timeField.setText("");
                
                // Clear the output area
                outputArea.setText("");
                // Update the GUI to display the current state of purchasedSeats
                updatePurchasedSeatsDisplay();
               
            }
        });
        add(nextPurchaseButton);
        outputArea = new JTextArea(10, 30);
        add(new JScrollPane(outputArea));
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    public void updatePurchasedSeatsDisplay() {
    	
        Set<Integer> purchasedSeats = theaterSystem.getPurchasedSeats();
        System.out.println("Updating purchased seats display: " + purchasedSeats); // Debug statement
        outputArea.append("Purchased Seats:\n");
        for (int seat : purchasedSeats) {
            outputArea.append(seat + "\n");
        }
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
