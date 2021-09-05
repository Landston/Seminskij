const { request, response } = require('express');
const express = require('express');
const leadRouter = express.Router();
const leadController = require('../controller/leadController.js');

leadRouter.use('/', leadController.createLead);

module.exports = leadRouter;

