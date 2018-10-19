let mongoose = require('mongoose');

module.exports = function(){
        
    let schema = mongoose.Schema({
        email:{
            type:String,
            required:true,
            index:{
                unique: true
            }
        },
        senha:{
            type:String,
            required:true
        }
    });
    return mongoose.model('usuarios', schema);
} ();