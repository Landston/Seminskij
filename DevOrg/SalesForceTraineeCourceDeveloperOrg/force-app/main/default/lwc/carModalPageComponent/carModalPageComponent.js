import { LightningElement, api} from 'lwc';
import logo from '@salesforce/resourceUrl/Cars';

export default class CarModalPageComponent extends LightningElement {

    carImgUrl = logo+"/cars/McLaren.png";
    @api carId;
    @api isClosed;

    handleCloseClick(){
        this.dispatchEvent(new CustomEvent("close"));
    }
}