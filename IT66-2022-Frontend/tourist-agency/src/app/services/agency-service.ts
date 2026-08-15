import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Agency } from '../model/Agency';

@Injectable({
  providedIn: 'root'
})
export class AgencyService {
  constructor(private httpClient: HttpClient) {}

  public getAllAgencies(): Observable<any> {
    return this.httpClient.get('http://localhost:8080/agencies');
  }

  public createAgency(agency: Agency): Observable<any> {
    return this.httpClient.post('http://localhost:8080/agency', agency);
  }

  public updateAgency(agency: Agency): Observable<any>{
    return this.httpClient.put(`http://localhost:8080/agency/${agency.id}`, agency);
  }

  public deleteAgency(id:number): Observable<any>{
    return this.httpClient.delete(`http://localhost:8080/agency/${id}`, {responseType: 'text'});
  }

}
