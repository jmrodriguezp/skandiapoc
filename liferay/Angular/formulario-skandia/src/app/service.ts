import { Injectable } from "@angular/core";
declare const Liferay: any;

@Injectable({
    providedIn:'root'
})

export class Service {
    constructor(){
 
    }
    getUser(): Promise<any>{
        return new Promise((resolve,reject) =>{
          Liferay.Service(
            '/user/get-user-by-id',
            {
              userId: Liferay.ThemeDisplay.getUserId()
            },
            (obj: any) => {
              resolve(obj);
            },
            (error: any)=>{
                reject(error);
            }
          );
        })
    }
}