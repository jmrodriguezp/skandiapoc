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
	respuesta: any;

	affiliationForm: FormGroup;

	constructor(private fb: FormBuilder, private service: Service) {
		this.service.getUser().then(response => {
			this.firstName = response.firstName;
			this.lastName = response.lastName;
		})
		this.respuesta="";
	}

	onSubmit() {
		if (this.affiliationForm.invalid) {
		 // alert("invalid form pt");
		}else{
			this.service.createContract(this.params).then(response => {
				this.respuesta=response;
				console.log(response);
			})
		} 

	}
	  
	ngOnInit() {
		this.affiliationForm = this.fb.group({
			productType: ['', [Validators.required]],
			planType: ['', [Validators.required]],
			agentType: ['', [Validators.required]],
			tocs: [false, [Validators.requiredTrue]]
		});
		
	}

	changeProductType(e:any) {
		console.log(this.params);
		this.productType.setValue(e.target.value, {
		  onlySelf: true
		})
	  }

	  changeplanType(e:any) {
		this.planType.setValue(e.target.value, {
		  onlySelf: true
		})

	  }
	  changeagentType(e:any) {
		this.agentType.setValue(e.target.value, {
		  onlySelf: true
		})
	  }

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
