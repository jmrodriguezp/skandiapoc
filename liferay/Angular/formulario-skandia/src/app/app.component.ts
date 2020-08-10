import { Component, OnInit } from '@angular/core';
import { FormsModule, FormGroup, FormControl, FormBuilder, Validators } from '@angular/forms';

import LiferayParams from '../types/LiferayParams'
import { Service } from './service';

declare const Liferay: any;

@Component({
	templateUrl: 
		Liferay.ThemeDisplay.getPathContext() + 
		'/o/formulario-skandia/app/app.component.html'
})
export class AppComponent implements OnInit{
	params: LiferayParams;
	labels: any;
	firstName: any;
	lastName: any;
	

	affiliationForm: FormGroup;

	constructor(private fb: FormBuilder, private service: Service) {
		/* this.labels = {        
			
			configuration: Liferay.Language.get('configuration'),
			
			portletNamespace: Liferay.Language.get('portlet-namespace'),
        	contextPath: Liferay.Language.get('context-path'),
			portletElementId: Liferay.Language.get('portlet-element-id'),
		} */
		this.service.getUser().then(response => {
			this.firstName = response.firstName;
			this.lastName = response.lastName;
		})
	}

	onSubmit() {
		if (this.affiliationForm.invalid) {
		  alert("invalid form pt");
		} 
		}
	  
	ngOnInit() {
		this.affiliationForm = this.fb.group({
			productType: ['', [Validators.required]],
			planType: ['', [Validators.required]],
			agentType: ['', [Validators.required]],
			tocs: ['', [Validators.required]]
		})
	}

	// Choose city using select dropdown
	changeProductType(e:any) {
		console.log(e.value)
		this.productType.setValue(e.target.value, {
		  onlySelf: true
		})
	  }

	  changeplanType(e:any) {
		console.log(e.value)
		this.planType.setValue(e.target.value, {
		  onlySelf: true
		})

	  }
	  changeagentType(e:any) {
		console.log(e.value)
		this.agentType.setValue(e.target.value, {
		  onlySelf: true
		})
	  }
	
	  // Getter method to access formcontrols
	  get productType() {
		return this.affiliationForm.get('productType');
	  }
	  get planType() {
		return this.affiliationForm.get('planType');
	  }
	  get agentType() {
		return this.affiliationForm.get('agentType');
	  }
	get configurationJSON() {
		return JSON.stringify(this.params.configuration, null, 2);
	}
}
