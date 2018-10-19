let controller = require("../controllers/auth.controller.js")
let auth =  require ("../controllers/auth.controller.js")

module.exports = function(app) {
    // ROTA DE LOGIN
    app.post("/api/login", controller.efetuaLogin)

    //ROTA PARA ADICIONAR NOVO USUÁRIO
    app.post("/api/usuarios", controller.newUser);
}