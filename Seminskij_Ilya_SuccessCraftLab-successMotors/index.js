const { response, request } = require('express');
const express = require('express');
const bodyParser = require("body-parser");
const path = require('path');
const app = express();
const PORT = process.env.PORT || 5000;

// Define Routers
const leadRouter = require('./router/leadRouter.js');

app.use("/lead", leadRouter);
app.use(bodyParser.urlencoded({ extended: false }));

app
  .use(express.static(path.join(__dirname, "/public")))
  .set("views", path.join(__dirname, "views"))
  .set("view engine", "ejs")
  .listen(PORT, () => console.log(`Listening on ${ PORT }`));

  
