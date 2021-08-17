import { LightningElement, api, wire } from 'lwc';

export default class ProductCard extends LightningElement {

    @api firstProductId;

    handleCardClick(){

        console.log("card click handler ");
        this.dispatchEvent(new CustomEvent("open",{detail: this.firstProductId}))

        
    }
}