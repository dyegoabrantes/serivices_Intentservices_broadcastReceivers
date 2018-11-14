let http = require ('http');
let app = require('./config/express.js')();
let db = require ('./config/database.js');




http.createServer(app)
    .listen(app.get('port'), function() {
        console.log('Express Server escutando na porta'+ app.get('port'));
    });

db("mongodb://dyegoabrantes:cachalote89@ds123753.mlab.com:23753/perere");