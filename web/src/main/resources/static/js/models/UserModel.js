define([
  'backbone'
], function(Backbone) {

  var UserModel = Backbone.Model.extend({

    urlRoot: 'user',

    defaults: {

      username: '',
      roles: '',
      password: '',
      passwordConfirm: '',
      name: '',
      email: '',
      is_admin: false
    },

    initialize: function() {},


    validation: {
      username: [{
        required: true,
        maxLength: 50,
        msg: 'El campo es requerido'
      }, {
        pattern: 'email',
        msg: 'Por favor especifique un email correcto.....'
      }],
      password: {
        required: true,
        pattern: /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{4,8}$/,
        msg: 'El password debe tener por lo menos una letra mayuscula, una minuscula y un numero'
      },
      passwordConfirm: {
        required: true,
        equalTo: 'password',
        msg: 'El password no coincide'

      }
    }

  });

  return UserModel;
});
