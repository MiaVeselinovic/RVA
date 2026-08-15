import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator,MatPaginatorModule } from '@angular/material/paginator';
import { MatSort,MatSortModule } from '@angular/material/sort';
import { HotelService } from '../services/hotel-service';
import { MatDialog,MatDialogModule } from '@angular/material/dialog';
import { Hotel } from '../model/Hotel';
import { MatTableDataSource,MatTableModule } from '@angular/material/table';
import { Destination } from '../model/Destination';
import { HotelFormComponent } from '../hotel-form/hotel-form.component';
import { MatIconModule } from '@angular/material/icon';
import { MatToolbarModule } from '@angular/material/toolbar';


@Component({
  selector: 'app-hotel',
  imports: [MatTableModule, MatIconModule, MatToolbarModule, MatDialogModule, MatSortModule, MatPaginatorModule],
  templateUrl: './hotel.component.html',
  styleUrls: ['./hotel.component.css']
})
export class HotelComponent implements OnInit {

  displayedColumns = ['id', 'name', 'numberOfStars', 'description', 'destination', 'actions'];
  dataSource!: MatTableDataSource<Hotel>;


  @ViewChild(MatSort, { static: false }) sort!: MatSort;
  @ViewChild(MatPaginator, { static: false }) paginator!: MatPaginator;

  constructor(
    private hotelService: HotelService,
    private dialog: MatDialog
  ) {}

  ngOnInit(): void {
    this.loadData();
  }

  public loadData(): void {
    this.hotelService.getAllHotels().subscribe({
      next: (data) => {
        console.log(data);
        this.dataSource = new MatTableDataSource<Hotel>(data);
        this.dataSource.sortingDataAccessor = (item: Hotel, property: string): any => {
          switch (property) {
            case 'destination':
              return item.destination?.place ?? '';
            default:
              return (item as any)[property];
          }
        };
        this.dataSource.sort = this.sort;
        this.dataSource.paginator = this.paginator;
      },
      error: (error) => {
        console.log(error.message);
      },
    });
  }

  public openDialog(
    flag: number,
    id?: number,
    name?: String,
    numberOfStars?: number,
    description?: String,
    destination?: Destination
  ): void {
    const ref = this.dialog.open(HotelFormComponent, {
      data: { id, name, numberOfStars, description, destination },
    });
    ref.componentInstance.flag = flag;
    ref.afterClosed().subscribe((response) => {
      if (response === 1) {
        this.loadData();
      }
    });
  }

}
