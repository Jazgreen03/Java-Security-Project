package ArtistTheaterSystem;

public class TheaterSystem {

    class Ticket {
        private String date;
        private String time;
        private int numberOfSeats;
        private int[] seatNumbers;
        private boolean[] SetAvailability;

        public Ticket(String date, String time) {
            this.date = date;
            this.time = time;
            this.numberOfSeats = 30; // Predetermined 
            initializeSeats();
        }

        private void initializeSeats() {
            seatNumbers = new int[numberOfSeats];
            SetAvailability = new boolean[numberOfSeats];
            for (int i = 0; i < numberOfSeats; i++) {
                seatNumbers[i] = i + 1; // Seat numbers start from 1
                SetAvailability[i] = true; // All seats are available at first
            }
        }
    }
    private Ticket ticket; 
    public boolean ArtistTheaterFull(int seatNumber) {
        if (seatNumber < 1 || seatNumber > ticket.numberOfSeats) {
            System.out.println("Invalid Seat Number (this will be a message displayed)");
            return false;
        }
        int track = seatNumber - 1;
        return ticket.SetAvailability[track];
    }
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
