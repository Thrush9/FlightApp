export interface Booking {
  id: number;
  flightId: number
  name: string;
  email: string;
  pnr: string;
  totalSeats: number;
  meal: boolean;
  status: boolean;
  passengers: any;
  seats: any;
}