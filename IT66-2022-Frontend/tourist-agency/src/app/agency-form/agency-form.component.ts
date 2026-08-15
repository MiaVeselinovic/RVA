import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { AgencyService } from '../services/agency-service';
import { Agency } from '../model/Agency';

@Component({
  selector: 'app-agency-form',
  imports: [MatDialogModule, MatFormFieldModule, MatButtonModule, MatInputModule,FormsModule, CommonModule],
  templateUrl: './agency-form.component.html',
  styleUrls: ['./agency-form.component.css']
})
export class AgencyFormComponent {

  flag!:number;

  constructor(
    private snackBar:MatSnackBar,
    private dialogRef: MatDialogRef<AgencyFormComponent>,
    private agencyService:AgencyService,
    @Inject(MAT_DIALOG_DATA) public data: Agency 
  ){}

  public add():void{
    this.agencyService.createAgency(this.data).subscribe(
      {
        next: (data)=> {
          this.dialogRef.close(1);
          this.snackBar.open(`Tourist agency: ${data.name} sucsesfull created!`, 'Ok', {duration:2500});
        },
        error:error => {
          this.snackBar.open('Error during tour agency create', 'Ok', {duration:2500});
          console.log(error.message);
        }
      }
    )
  }

   public update():void{
    this.agencyService.updateAgency(this.data).subscribe(
      {
        next: (data)=> {
          this.dialogRef.close(1);
          this.snackBar.open(`Tour agency: ${data.name} updated!`, 'Ok', {duration:2500});
        },
        error:error => {
          this.snackBar.open('Error during agecny update', 'Ok', {duration:2500});
          console.log(error.message);
        }
      }
    )
  }

  public delete():void{
    this.agencyService.deleteAgency(this.data.id).subscribe(
      {
        next: (response)=> {
          this.dialogRef.close(1);
          this.snackBar.open(response, 'Ok', {duration:2500});
        },
        error:error => {
          this.snackBar.open('Error during delete agency', 'Ok', {duration:2500});
          console.log(error.message);
        }
      }
    )
  }

}
