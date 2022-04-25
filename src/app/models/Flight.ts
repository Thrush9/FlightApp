export interface Flight {

  id: number;
  flightNo: string;
  fromPlace: string;
  toPlace: string;
  startDate: Date;
  endDate: Date;
  availability: string;
  instrument: string;
  businessSeats: number;
  nonBusinessSeats: number;
  cost: number;
  totalRows: number;
  meal: Boolean;
  veg: Boolean;
}