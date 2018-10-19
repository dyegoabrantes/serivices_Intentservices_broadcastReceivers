let express = require ('express');
let bodyParser = require('body-parser');
let path = require('path');

let usersRouter = require('../app/routes/users.routes');
let authRouter = require('../app/routes/auth.routes');


module.exports = function () {
    let app = express();
    app.set("port", 3000);
    app.use(bodyParser.json());
    app.use(bodyParser.urlencoded({extended: true}))
    usersRouter(app);
    authRouter(app);

    return app;
}