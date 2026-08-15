import { Component, Inject } from '@angular/core';
import { Destination } from '../model/Destination';
import { DestinationService } from '../services/destination-service';
import { HotelService } from '../services/hotel-service';
import { Hotel } from '../model/Hotel';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-hotel-form',
  templateUrl: './hotel-form.component.html',
  styleUrls: ['./hotel-form.component.css']
})
export class HotelFormComponent {

  flag!: number;
  destinations!: Destination[];

  constructor(
    private snackBar: MatSnackBar,
    private dialogRef: MatDialogRef<HotelFormComponent>,
    private hotelService: HotelService,
    private destinationService: DestinationService,
    @Inject(MAT_DIALOG_DATA) public data: Hotel
  ) {}

   ngOnInit(): void {
    this.destinationService.getAllDestinations().subscribe({
      next: (data) => (this.destinations = data),
      error: (e) => console.log(e.message)
    });
  }

  public add(): void {
    this.hotelService.createHotel(this.data).subscribe({
      next: (created) => {
        this.dialogRef.close(1);
        this.snackBar.open(`Hotel "${created.naziv}" succesfull created!`, 'OK', { duration: 2500 });
      },
      error: (error) => {
        this.snackBar.open('Error during hotel creation', 'OK', { duration: 2500 });
        console.log(error.message);
      }
    });
  }

  public update(): void {
    this.hotelService.updateHotel(this.data).subscribe({
      next: (updated) => {
        this.dialogRef.close(1);
        this.snackBar.open(`Hotel "${updated.naziv}" successfull updated`, 'OK', { duration: 2500 });
      },
      error: (error) => {
        this.snackBar.open('Error during hotel update', 'OK', { duration: 2500 });
        console.log(error.message);
      }
    });
  }

  public delete(): void {
    this.hotelService.deleteHotel(this.data.id).subscribe({
      next: (response) => {
        this.dialogRef.close(1);
        this.snackBar.open(response, 'OK', { duration: 2500 });
      },
      error: (error) => {
        this.snackBar.open('Error during hotel delete', 'OK', { duration: 2500 });
        console.log(error.message);
      }
    });
  }

  public cancel(): void {
    this.dialogRef.close();
    this.snackBar.open('You rejected changes', 'OK', { duration: 2500 });
  }

  public compareDestinacija(a: Destination, b: Destination) {
    return a && b ? a.id === b.id : a === b;
  }

}
