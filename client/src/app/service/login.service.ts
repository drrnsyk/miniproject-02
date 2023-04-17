import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { firstValueFrom } from "rxjs";
import { ChangePassword, LoginRequest, LoginResp, Status } from "../components/model/model";

@Injectable({
    providedIn: 'root'
})
export class LoginService {

    constructor(private http: HttpClient) {}

    login(loginReq: LoginRequest): Promise<LoginResp> {
        const headers = new HttpHeaders()
            .set('Content-Type', 'application/json')
            .set('Accept', 'application/json')

        return firstValueFrom(
            this.http.post<LoginResp>('/auth/authenticate', loginReq, { headers: headers })
        )
    }

    changePassword(changePass: ChangePassword): Promise<Status> {
        const headers = new HttpHeaders()
            .set('Content-Type', 'application/json')
            .set('Accept', 'application/json')

        return firstValueFrom(
            this.http.post<Status>('/auth/changePassword', changePass, { headers: headers })
        )
    }

}