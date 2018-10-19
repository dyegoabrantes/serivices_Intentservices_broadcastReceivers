var mongoose = require ('mongoose');

module.exports = function () {
    mongoose.connect("mongodb://dyegoabrantes:cachalote89@ds123753.mlab.com:23753/perere")
    mongoose.connection.on('connected', function () {
        console.log('Mongoose! Conectado em ' + 'Pererê server');
    });
    mongoose.connection.on('disconnected',
        function () {
            console.log('Mongoose! Desconectado de ' + 'Pererê server');
        });
    mongoose.connection.on('error', function (erro) {
        console.log('Mongoose! Erro na conexão: '+'Pererê server');
    });
    mongoose.set('debug', true);
}
