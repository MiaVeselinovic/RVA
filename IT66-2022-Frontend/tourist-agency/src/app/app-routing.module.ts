import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AgencyComponent } from './agency/agency.component';
import { DestinationComponent } from './destination/destination.component';
import { ArrangementComponent } from './arrangement/arrangement.component';
import { HotelComponent } from './hotel/hotel.component';

const routes: Routes = [
  {path: 'agency', component:AgencyComponent},
  {path: 'destination', component: DestinationComponent},
  {path: 'arrangement', component:ArrangementComponent},
  {path: 'hotel', component:HotelComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
