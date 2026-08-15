import { Component, Input, OnChanges, OnInit, SimpleChanges, ViewChild } from '@angular/core';
import { Arrangement } from '../model/Arrangement';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { Agency } from '../model/Agency';
import { ArrangementService } from '../services/arrangement-service';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import { MatPaginator, MatPaginatorModule } from '@angular/material/paginator';
import { Hotel } from '../model/Hotel';
import { ArrangementFormComponent } from '../arrangement-form/arrangement-form.component';
import { DatePipe } from '@angular/common';
import { MatSort, MatSortModule } from '@angular/material/sort';
import { MatIconModule } from '@angular/material/icon';
import { MatToolbarModule } from '@angular/material/toolbar';



@Component({
  selector: 'app-arrangement',
  imports: [MatTableModule, MatIconModule, MatToolbarModule, MatDialogModule, DatePipe, MatSortModule, MatPaginatorModule],
  templateUrl: './arrangement.component.html',
  styleUrls: ['./arrangement.component.css']
})
export class ArrangementComponent implements OnChanges, OnInit {
  displayedColumns = ['id', 'total_cost', 'payed', 'realization_date', 'hotel', 'actions'];
  dataSource!: MatTableDataSource<Arrangement>;

  @Input()
  childSelectedAgency?: Agency;

  constructor(
    private arrangementService: ArrangementService,
    private dialog: MatDialog
  ) {}

  @ViewChild(MatSort, { static: false }) sort!: MatSort;
  @ViewChild(MatPaginator, { static: false }) paginator!: MatPaginator;

  ngOnChanges(changes: SimpleChanges): void {
    this.loadData();
  }

  ngOnInit(): void {
    this.loadData();
  }

  public loadData(): void {
    if (this.childSelectedAgency && this.childSelectedAgency.id != null) {
      this.arrangementService
        .getArrangementsByAgency(this.childSelectedAgency.id)
        .subscribe({
          next: (data) => {
            console.log(data);
            this.dataSource = new MatTableDataSource<Arrangement>(data);
            this.dataSource.sortingDataAccessor = (item: Arrangement, property: string): any => {
              switch (property) {
                case 'hotel':
                  return item.hotel?.name ?? '';
                case 'realization_date':
                  return item.realization_date ? new Date(item.realization_date).getTime() : 0;
                case 'payed':
                  return item.payed ? 1 : 0;
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
    } else {
      this.arrangementService.getAllArrangements().subscribe({
        next: (data) => {
          console.log(data);
          this.dataSource = new MatTableDataSource<Arrangement>(data);
          this.dataSource.sortingDataAccessor = (item: Arrangement, property: string): any => {
            switch (property) {
              case 'hotel':
                return item.hotel?.name ?? '';
              case 'realization_date':
                return item.realization_date ? new Date(item.realization_date).getTime() : 0;
              case 'payed':
                return item.payed ? 1 : 0;
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
  }

  public openDialog(
    flag: number,
    id?: number,
    total_cost?: number,
    payed?: boolean,
    realization_date?: Date,
    hotel?: Hotel

  ): void {
    const ref = this.dialog.open(ArrangementFormComponent, {
      data: { id, total_cost, payed, realization_date, hotel },
    });
    ref.componentInstance.flag = flag;
    if (this.childSelectedAgency) {
      ref.componentInstance.data.agency = this.childSelectedAgency;
    }
    ref.afterClosed().subscribe((response) => {
      if (response === 1) {
        this.loadData();
      }
    });
  }
}
