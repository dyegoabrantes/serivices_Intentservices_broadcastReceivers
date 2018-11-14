var mongoose = require('mongoose');

module.exports = function() {
  var schema = mongoose.Schema({
    topic:{
      type: String,
      required: true
    },
    messageId:{
      type: String,
      required: false
    },
    payload:{
      // temperatura:{
      //   type: Number,
      //   required: false
      // },
      // umidade:{
      //   type: Number,
      //   required: false
      // }
      state:{
        type: String,
        required: false
      }
    }
  }, {
    timestamps: { createdAt: 'created', updatedAt: 'updated' }
  });
  return mongoose.model('MQTTMessage', schema);
}();