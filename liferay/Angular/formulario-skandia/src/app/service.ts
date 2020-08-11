import { Injectable } from "@angular/core";
import { HttpClient } from '@angular/common/http';
declare const Liferay: any;

@Injectable({
    providedIn:'root'
})

export class Service {

  public contractInfo: any = null;
    constructor(private httpClient: HttpClient){
 
    }
    getUser(): Promise<any>{
        return new Promise((resolve,reject) =>{
          Liferay.Service(
            '/user/get-user-by-id',
            {
              userId: Liferay.ThemeDisplay.getUserId()
            },
            (obj: any) => {
              console.log(obj);
              resolve(obj);
            },
            (error: any)=>{
                reject(error);
            }
          );
        })
    }
    createContract(params:any): Promise<any>{
      var url = Liferay.PortletURL.createURL(Liferay.currentURL);
      url.setLifecycle(Liferay.PortletURL.RESOURCE_PHASE);
      url.setPortletId(params.portletNamespace.slice(1, -1));
      url.setResourceId("addContract");
      console.log("urlToString "+url.toString())
      console.log("url "+url)
      return new Promise((resolve,reject) =>   
        this.httpClient
        .get(url.toString())
        .subscribe(
          contractInfo=>{
            console.log(contractInfo);
            resolve(contractInfo);
          }
        )
      )
    }
  }