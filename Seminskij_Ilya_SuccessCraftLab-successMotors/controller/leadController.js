const cars = require('../model/car.js');

exports.createLead = function (request, response) {
    
    response.render('pages/web-to-lead.ejs', { cars :  cars.getCars() });
};

exports.getHello = function(request, response) {

    console.log('getHello func');
    response.render('pages/hello.ejs');
};