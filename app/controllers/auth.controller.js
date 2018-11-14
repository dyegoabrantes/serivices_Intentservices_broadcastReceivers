let bcrypt = require('bcrypt');
let Usuario = require('../models/user.model');
let jwt = require('jsonwebtoken');



module.exports.efetuaLogin = function(req,res){
    let pass = req.body.senha;
    let email = req.body.email;

    let promise = Usuario.findOne({'email':email}).exec();

    promise.then(
        (data) =>{ 
            if (data){
                if (bcrypt.compareSync(pass, data.senha)) {
                    data.senha="";
                    let token = jwt.sign({data:Usuario}, 'd0e4e97c82638d6d6fe0153649762fd6f');
                    res.status(200).send({
                        message:"Logado",
                        token: token,
                        data
                    });
                }else{
                    res.status(401).send('Verifique sua senha');
                }
            }else{
                res.status(401).send('Email não cadastrado');
            }
        },
        (erro) => {
            res.status(500).json(erro);
        }
    );
}

module.exports.newUser = function(req,res) {
    console.log(req.body);
    let user = new  Usuario({
        email: req.body.email,
        senha: bcrypt.hashSync (req.body.senha, 10)
    });

    let promise = Usuario.create(user);
    promise.then(
        (user) =>{
            res.status(201).json({
                email: user.email
            }
            );
        },
        (erro) => {
            res.status(500).json(erro);
        }
    )
}