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
    createContract(params:any): Promise<any>{

      var url = Liferay.PortletURL.createURL(Liferay.currentURL);
		  url.setLifecycle(Liferay.PortletURL.RESOURCE_PHASE);
		  url.setPortletId(params.portletNamespace.slice(1, -1));
		  url.setResourceId("addContract");
         
      return new Promise((resolve,reject) =>{
        Liferay.Service(
          url.toString(),
          { },
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