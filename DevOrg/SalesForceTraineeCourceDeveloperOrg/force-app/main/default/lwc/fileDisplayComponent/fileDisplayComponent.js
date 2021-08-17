import { LightningElement, api } from 'lwc';
import getContentVersionId from '@salesforce/apex/sObjectFileService.getContentVersionId';

export default class FileDisplayComponent extends LightningElement {

    @api sObjectId;
    imgUrl;

    connectedCallback(){
        getContentVersionId({ sobjectId : this.sObjectId}) 
            .then( result => {
                this.imgUrl = '/sfc/servlet.shepherd/version/download/' + result;
            });
        
    }
}