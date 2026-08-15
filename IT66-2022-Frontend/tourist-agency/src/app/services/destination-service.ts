import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Destination } from '../model/Destination';

@Injectable({
  providedIn: 'root'
})
export class DestinationService {
  constructor(private httpClient: HttpClient) {}

  public getAllDestinations(): Observable<any> {
    return this.httpClient.get('http://localhost:8080/destination');
  }

  public createDestination(destination:Destination): Observable<any> {
    return this.httpClient.post('http://localhost:8080/destination', destination);
  }

  public updateDestination(destination: Destination): Observable<any> {
    return this.httpClient.put(`http://localhost:8080/destination/${destination.id}`, destination);
  }

  public deleteDestination(id: number): Observable<any> {
    return this.httpClient.delete(`http://localhost:8080/destination/${id}`, { responseType: 'text' });
  }
}
