let controller = require("../controllers/users.controller.js")
let auth =  require ("../controllers/auth.controller.js")

module.exports = function(app) {
    // app.use("/api/usuarios/", controller.checar);

    //ROTA PARA OBTER USUARIO POR ID
    app.get("/api/usuarios/:id", controller.getUserById);

    //ROTA PARA ATUALIZAR USUÁRIO
    app.put("/api/usuarios/:id", controller.updateUserById);

    // ROTA PARA DELETAR USUÁRIO
    app.delete("/api/usuarios/:id", controller.deleteUserById);
}