import { Component, OnInit, ViewChild } from '@angular/core';
import { MatTableDataSource,MatTableModule } from '@angular/material/table';
import { Destination } from '../model/Destination';
import { DestinationService } from '../services/destination-service';
import { MatDialog,MatDialogModule } from '@angular/material/dialog';
import { MatPaginator, MatPaginatorModule } from '@angular/material/paginator';
import { MatSort,MatSortModule } from '@angular/material/sort';
import { DestinationFormComponent } from '../destination-form/destination-form.component';
import { MatIconModule } from '@angular/material/icon';
import { MatToolbarModule } from '@angular/material/toolbar';

@Component({
  selector: 'app-destination',
  templateUrl: './destination.component.html',
  styleUrls: ['./destination.component.css']
})
export class DestinationComponent implements OnInit{

  displayedColumns: string[] = ['id', 'place', 'country', 'description', 'actions'];
  dataSource!: MatTableDataSource<Destination>;

  @ViewChild(MatSort, { static: false }) sort!: MatSort;
  @ViewChild(MatPaginator, { static: false }) paginator!: MatPaginator;

  constructor(private destinationService: DestinationService, private dialog:MatDialog) {}

  ngOnInit(): void {
    this.loadData();
  }

  public loadData(): void {
    this.destinationService.getAllDestinations().subscribe({
      next: data => {
        console.log(data);
        this.dataSource = new MatTableDataSource<Destination>(data);
        this.dataSource.sort = this.sort;
        this.dataSource.paginator = this.paginator;
      },
      error: error => {
        console.log(error.message);
      }
    });
  }
  public openDialog(flag:number, id?:number, place?:String, country?:String, description?:String):void{
      const ref = this.dialog.open(DestinationFormComponent, {data: {id, place, country, description }});
      ref.componentInstance.flag = flag;
      ref.afterClosed().subscribe(
        response => {
          if(response === 1){ 
            this.ngOnInit();
          }
        }
      )
    }
}
