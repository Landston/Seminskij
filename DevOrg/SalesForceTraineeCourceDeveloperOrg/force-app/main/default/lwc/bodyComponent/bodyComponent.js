import { LightningElement, api, track, wire } from 'lwc';
import getProductsIdList  from '@salesforce/apex/ProductService.getProductsIdList';
import getProductByName  from '@salesforce/apex/ProductService.getProductByName';
import getProductByPrice from '@salesforce/apex/ProductService.getProductByPrice';

const DELAY = 350;

export default class BodyComponent extends LightningElement {
    products;
    searchKey;
    modalPageCarId;
    @track isModalOpen ;
    @track error;
    val = 50000;

    connectedCallback(){
        this.isModalOpen = false;
        getProductsIdList()
        .then(result =>{
            this.products = result;

            console.log(this.products);
        })
    }

    handleFilterReset(){
        getProductsIdList()
        .then(result =>{
            this.products = result;
        })

        this.val = 50000;
        this.searchKey = "";
    }

    handleSearchChange(event){;
        const key =  event.target.value;
        console.log(key);

        getProductByName({name: key})
            .then(result =>{
                this.products = result;

                this.error = undefined;
            })
            .catch(error => {
                this.error = error;
                this.products = undefined;
            })
    }

    handleClose(){
        this.isModalOpen = false;
    }
    
    handleOpen(event){
        const eventDetail = event.detail; 

        this.modalPageCarId = eventDetail;
        this.isModalOpen = true;
    }

    handleSliderChange(event){
        this.val = event.target.value;

        getProductByPrice({price : this.val})
            .then(result =>{
                this.products = result;
            })

    }

    

}