import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ArrangementFormComponent } from './arrangement-form/arrangement-form.component';
import { DestinationFormComponent } from './destination-form/destination-form.component';
import { HotelFormComponent } from './hotel-form/hotel-form.component';
import { AgencyFormComponent } from './agency-form/agency-form.component';
import { NoopAnimationsModule } from '@angular/platform-browser/animations';
import { ArrangementComponent } from './arrangement/arrangement.component';
import { DestinationComponent } from './destination/destination.component';
import { HotelComponent } from './hotel/hotel.component';
import { AgencyComponent } from './agency/agency.component';
import { MatIconModule } from '@angular/material/icon';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatTableModule } from '@angular/material/table';
import { MatToolbarModule } from '@angular/material/toolbar';
import { CommonModule } from '@angular/common';
import { MatSortModule } from '@angular/material/sort';
import { FormsModule } from '@angular/forms';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { MatDialogModule } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatButtonModule } from '@angular/material/button';
import { HomeComponent } from './home/home.component';
import {MatCardModule} from '@angular/material/card';import { HttpClientModule } from '@angular/common/http';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
;

@NgModule({
  declarations: [
    AppComponent,
    ArrangementFormComponent,
    DestinationFormComponent,
    HotelFormComponent,
    AgencyFormComponent,
    ArrangementComponent,
    DestinationComponent,
    HotelComponent,
    AgencyComponent,
    HomeComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NoopAnimationsModule,
    MatIconModule,
    MatPaginatorModule,
    MatTableModule,
    MatToolbarModule,
    CommonModule,
    MatSortModule,
    FormsModule,
    MatInputModule,
    MatSelectModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatDialogModule,
    MatFormFieldModule,
    MatButtonModule,
    MatCardModule,
    HttpClientModule,
    MatSnackBarModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
