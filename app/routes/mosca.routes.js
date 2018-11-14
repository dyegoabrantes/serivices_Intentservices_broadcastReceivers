let controller = require("../controllers/mosca.controller.js")

module.exports = function(app) {

    //ROTA PARA OBTER TEMPERATURA E UMIDADE
    app.post("/api/mosca/getumtp", controller.getUmTp);

    //ROTA PARA OBTER ESTADO DE OPERAÇÃO
    app.post("/api/mosca/getstate", controller.getEstados);

    //ROTA PARA LUZES
    app.put("/api/mosca/:state", controller.setLuz);
    
    //ROTA PARA REGA
    app.put("/api/mosca/:state", controller.setRega);
    
    //ROTA PARA TROCA DE AR
    app.put("/api/mosca/:state", controller.setTrocaAr);
    
    //ROTA PARA VENTILAÇÃO
    app.put("/api/mosca/:state", controller.setVentila);

}