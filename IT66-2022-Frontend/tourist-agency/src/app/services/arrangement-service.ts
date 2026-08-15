import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Arrangement } from '../model/Arrangement';


@Injectable({
  providedIn: 'root'
})
export class ArrangementService {
  constructor(private httpClient: HttpClient) {}

  public getAllArrangements(): Observable<any> {
    return this.httpClient.get('http://localhost:8080/arrangement');
  }

  public createArrangement(arrangement: Arrangement): Observable<any> {
    return this.httpClient.post('http://localhost:8080/arrangement', arrangement);
  }

  public updateArrangement(arrangement: Arrangement): Observable<any> {
    return this.httpClient.put(`http://localhost:8080/arrangement/${arrangement.id}`, arrangement);
  }

  public deleteArrangement(id: number): Observable<any> {
    return this.httpClient.delete(`http://localhost:8080/arrangement/${id}`, { responseType: 'text' });
  }

  
  public getArrangementsByHotel(hotelId: number): Observable<any> {
    return this.httpClient.get(`http://localhost:8080/arrangement/hotel/${hotelId}`);
  }

  public getArrangementsByAgency(agencyId: number): Observable<any> {
    return this.httpClient.get(`http://localhost:8080/arrangement/agency/${agencyId}`);
  }

  public getArrangementsByPayed(payed: boolean): Observable<any> {
    return this.httpClient.get(`http://localhost:8080/arrangement/payed/${payed}`);
  }
}
