const fs = require('fs');
const cars = [ { "name" : "Renault Logan" , "code" : "R001C1" } , 
{ "name" : "Renault Logan Stepway" , "code" : "R002C2" } , 
{ "name" : "Renault Sandero" ,       "code" : "R003C3" } , 
{ "name" : "Renault Sandero Stepway" , "code" : "R004C4" } , 
{ "name" : "K7M" , "code" : "R005E1" } , 
{ "name" : "K4M" , "code" : "R006E2" } , 
{ "name" : "H4M" , "code" : "R007E3" } , 
{ "name" : "Kia Rio" ,  "code" : "HK001C1" } , 
{ "name" : "Kia Rio X-Line" , "code" : "HK002C2" } , 
{ "name" : "Hyundai Accent" , "code" : "HK003C3" } , 
{ "name" : "Hyundai Solaris" , "code" : "HK004C4" } , 
{ "name" : "G4LC" , "code" : "HK005E1" } , 
{ "name" : "G4FG" , "code" : "HK006E2" } , 
{ "name" : "Volkswagen Polo Sedan" , "code" : "VAG001C1" } , 
{ "name" : "Volkswagen Polo 2021" ,  "code" : "VAG002C2" } , 
{ "name" : "Skoda Rapid" , "code" : "VAG003C3" } , 
{ "name" : "CZCA" , "code" : "VAG004E1" } , 
{ "name" : "CFW" , "code" : "VAG005E2" } , 
{ "name" : "CWVA" , "code" : "VAG006E3" } , 
{ "name" : "CFNA" , "code" : "VAG007E4" } , 
{ "name" : "CFNB" , "code" : "VAG008E5" } , 
{ "name" : "Lada Vesta" , "code" : "L001C1" } , 
{ "name" : "Lada Xray" , "code" : "L002C2" } , 
{ "name" : "VAZ-21179" , "code" : "L003E1" } , 
{ "name" : "VAZ-21129" , "code" : "L004E2" } , 
{ "name" : "Carpets Renault" , "code" : "R008A1" } , 
{ "name" : "Carpets Hyundai" , "code" : "HK007A1" } , 
{ "name" : "Carpets Kia" , "code" : "HK008A2" } , 
{ "name" : "Carpets Volkswagen" , "code" : "VAG009A1" } , 
{ "name" : "Carpets Skoda" , "code" : "VAG010A2" } , 
{ "name" : "Carpets Lada" , "code" : "L005A1" } , 
{ "name" : "Phone holder Xiaomi" ,  "code" : "MI001A1" } 
    ] ;


module.exports = class User {

    constructor(name, code){
        this.name = name;
        this.code = code;
    }

    static getCars(){

        console.log('Cars pre load  ' + cars.length) ;
        return cars;
        
    }
}


