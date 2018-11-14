let MQTTMessage = require('./../models/mqttMessage.model');
let MQTTEvent = require('./../models/mqttEvent.model');

const mosca = require('mosca');

let port = 1883;
let state = 0;
let mosca_server = new mosca.Server({port:port});
  
mosca_server.on('ready', function() {
  console.log(`[Mosca Server] Running on port ${port}`);
});

mosca_server.on('clientConnected', function(client) {
  let mqttEvent = new MQTTEvent({
    clientId: client,
    event: 'connected'
  });
  MQTTEvent.create(mqttEvent).then(
    function(event){
			console.log('Event stored');
		},
		function(error){
      console.log('FAIL: Event not stored');
		}
  );
});

mosca_server.on('clientDisconnected', function(client) {
  let mqttEvent = new MQTTEvent({
    clientId: client,
    event: 'disconnected'
  });
  MQTTEvent.create(mqttEvent).then(
    function(event){
			console.log('Event stored');
		},
		function(error){
      console.log('FAIL: Event not stored');
		}
  );
});

mosca_server.on('published', function(packet, client) {
  if (!packet.topic.startsWith('$')){
    console.log(packet);
    try{
      let message = new MQTTMessage({
        topic: packet.topic.substring(packet.topic.indexOf("/") + 1),
        messageId: packet.messageId,
        payload: {
          state: packet.payload
        }
      });
      MQTTMessage.create(message).then(
        function(message){
                console.log('Message stored '+message);
            },
            function(error){
          console.log('FAIL: Message not stored');
            }
      );
    }catch(err){
        console.log("JSON incompreensível");
    }
  }
});

mosca_server.on('subscribed', function(topic, client) {
  console.log('subscribed: ' + client.id);
});

mosca_server.on('unsubscribed', function(topic, client) {
  console.log('unsubscribed: ' + client.id);    
});


module.exports.getUmTp = function() {
  MQTTMessage.findOne({}, {}, { sort: { 'created' : -1 } }, function(err, message) {
  });
}

module.exports.getEstados = function(req,res) {
  console.log(state);
  res.status(202).json({state:state});
}

module.exports.setLuz = function(req,res) {
  state = req.params.state;
  mosca_server.publish({topic: 'inTopic', payload: state, qos:2});
  res.status(200).send('publicado')
}

module.exports.setRega = function(req,res) {

}

module.exports.setTrocaAr = function(req,res) {

}

module.exports.setVentila = function(req,res) {

}