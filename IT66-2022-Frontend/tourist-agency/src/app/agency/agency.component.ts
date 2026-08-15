import { Component, OnInit, ViewChild } from '@angular/core';
import { MatTableDataSource,MatTableModule } from '@angular/material/table';
import { Agency } from '../model/Agency';
import { MatSort, MatSortModule } from '@angular/material/sort';
import { MatPaginator, MatPaginatorModule } from '@angular/material/paginator';
import { AgencyService } from '../services/agency-service';
import { MatDialog } from '@angular/material/dialog';
import { AgencyFormComponent } from '../agency-form/agency-form.component';
import { MatIconModule } from '@angular/material/icon';
import { CommonModule } from '@angular/common';
import { MatToolbarModule } from '@angular/material/toolbar';
import { ArrangementComponent } from '../arrangement/arrangement.component';

@Component({
  selector: 'app-agency',
  imports: [MatTableModule, MatIconModule, MatToolbarModule, ArrangementComponent, CommonModule, MatSortModule, MatPaginatorModule],
  templateUrl: './agency.component.html',
  styleUrls: ['./agency.component.css']
})
export class AgencyComponent implements OnInit {

  displayedColumns: string[] = ['id', 'name', 'address', 'contact', 'actions'];
  dataSource!: MatTableDataSource<Agency>;
  parentSelectedArrangement!: Agency;

  @ViewChild(MatSort, { static: false }) sort!: MatSort;
  @ViewChild(MatPaginator, { static: false }) paginator!: MatPaginator;

   constructor(private agencyService: AgencyService, private dialog:MatDialog) {}

  ngOnInit(): void {
    this.loadData();
  }

  public loadData(): void {
    this.agencyService.getAllAgencies().subscribe({
      next: data => {
        console.log(data);
        this.dataSource = new MatTableDataSource<Agency>(data);
        this.dataSource.sort = this.sort;
        this.dataSource.paginator = this.paginator;
      },
      error: error => {
        console.log(error.message);
      }
      
    })
  }

   public openDialog(flag:number, id?:number, name?:String, address?:String, contact?:String):void{
    const ref = this.dialog.open(AgencyFormComponent, {data: {id,name, address, contact}});
    ref.componentInstance.flag = flag;
    ref.afterClosed().subscribe(
      response => {
        if(response === 1){ 
          this.ngOnInit();
        }
      }
    )
  }

   public selectRow(row: Agency): void {
    this.parentSelectedArrangement = row;
    //console.log(row);
  }


}
