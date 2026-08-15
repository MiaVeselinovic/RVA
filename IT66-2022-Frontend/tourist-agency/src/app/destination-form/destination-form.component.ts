import { Component,Inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatDialogModule, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Destination } from '../model/Destination';
import { DestinationService } from '../services/destination-service';

@Component({
  selector: 'app-destination-form',
  imports: [MatDialogModule, MatFormFieldModule, MatButtonModule, MatInputModule,FormsModule, CommonModule],
  templateUrl: './destination-form.component.html',
  styleUrls: ['./destination-form.component.css']
})

export class DestinationFormComponent {

  flag!:number;

  constructor(
    private snackBar:MatSnackBar,
    private dialogRef: MatDialogRef<DestinationFormComponent>,
    private destinationService: DestinationService,
    @Inject(MAT_DIALOG_DATA) public data: Destination 
  ){}

  public add():void{
    this.destinationService.createDestination(this.data).subscribe(
      {
        next: (data)=> {
          this.dialogRef.close(1);
          this.snackBar.open(`Destination for places: ${data.mesto} succesfull created!`, 'Ok', {duration:2500});
        },
        error:error => {
          this.snackBar.open('Error during destination create', 'Ok', {duration:2500});
          console.log(error.message);
        }
      }
    )
  }

  public update():void{
    this.destinationService.updateDestination(this.data).subscribe(
      {
        next: (data)=> {
          this.dialogRef.close(1);
          this.snackBar.open(`Destination for place: ${data.place} is succsesfull changed!`, 'Ok', {duration:2500});
        },
        error:error => {
          this.snackBar.open('Error during update destination', 'Ok', {duration:2500});
          console.log(error.message);
        }
      }
    )
  }

  public delete():void{
    this.destinationService.deleteDestination(this.data.id).subscribe(
      {
        next: (response)=> {
          this.dialogRef.close(1);
          this.snackBar.open(response, 'Ok', {duration:2500});
        },
        error:error => {
          this.snackBar.open('Error during destination delete', 'Ok', {duration:2500});
          console.log(error.message);
        }
      }
    )
  }

  public cancel():void{
    this.dialogRef.close();
    this.snackBar.open('You rejected changes', 'Okay', {duration:2500});
  }
}
