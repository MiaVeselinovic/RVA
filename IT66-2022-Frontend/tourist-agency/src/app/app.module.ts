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
    AgencyComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NoopAnimationsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
