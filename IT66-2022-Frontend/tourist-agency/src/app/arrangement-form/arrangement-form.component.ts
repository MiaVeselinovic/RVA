import { Component,Inject } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatButtonModule } from '@angular/material/button';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { Hotel } from '../model/Hotel';
import { Arrangement } from '../model/Arrangement';
import { Agency } from '../model/Agency';
import { ArrangementService } from '../services/arrangement-service';
import { HotelService } from '../services/hotel-service';


@Component({
  selector: 'app-arrangement-form',
  imports: [
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatDatepickerModule,
    MatNativeDateModule,
    CommonModule,
    MatDialogModule,
    MatButtonModule,
    FormsModule,
  ],
  templateUrl: './arrangement-form.component.html',
  styleUrls: ['./arrangement-form.component.css']
})
export class ArrangementFormComponent {
  flag!: number;
  hotels!: Hotel[];

   constructor(
    private snackBar: MatSnackBar,
    private dialogRef: MatDialogRef<ArrangementFormComponent>,
    private arrangementService: ArrangementService,
    private hotelService: HotelService,
    @Inject(MAT_DIALOG_DATA) public data: Arrangement
  ) {}

  ngOnInit(): void {
    this.hotelService.getAllHotels().subscribe({
      next: (data) => (this.hotels = data),
      error: (e) => console.log(e.message)
    });
  }

  public add(): void {
    this.arrangementService.createArrangement(this.data).subscribe({
      next: (created) => {
        this.dialogRef.close(1);
        this.snackBar.open(`Arrangement #${created.id}  successfull created!`, 'OK', { duration: 2500 });
      },
      error: (error) => {
        this.snackBar.open('Error druing arrangement creation', 'OK', { duration: 2500 });
        console.log(error.message);
      },
    });
  }
  public update(): void {
    this.arrangementService.updateArrangement(this.data).subscribe({
      next: (updated) => {
        this.dialogRef.close(1);
        this.snackBar.open(`Arrangement #${updated.id} succsesfull updated!`, 'OK', { duration: 2500 });
      },
      error: (error) => {
        this.snackBar.open('Error during update arrangement', 'OK', { duration: 2500 });
        console.log(error.message);
      },
    });
  }


  public delete(): void {
    this.arrangementService.deleteArrangement(this.data.id).subscribe({
      next: (response) => {
        this.dialogRef.close(1);
        this.snackBar.open(response, 'OK', { duration: 2500 });
      },
      error: (error) => {
        this.snackBar.open('Error during deletion arrangement', 'OK', { duration: 2500 });
        console.log(error.message);
      },
    });
  }

  public cancel(): void {
    this.dialogRef.close();
    this.snackBar.open('You rejected changes', 'OK', { duration: 2500 });
  }

  public compareHotel(a: Hotel, b: Hotel) {
    return a && b ? a.id === b.id : a === b;
  }

  public compareAgencija(a: Agency, b: Agency) {
    return a && b ? a.id === b.id : a === b;
  }
}
