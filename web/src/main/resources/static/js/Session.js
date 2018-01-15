define([
  'jquery',
  'backbone',
  'views/private/util/ModalGenericView',
  'jscookie'
], function($, Backbone, ModalGenericView, Cookies) {

  var SessionModel = Backbone.Model.extend({
    url: 'session/login',

    defaults: {
      username: '',
      password: '',
      authenticated: false,
      user_id: ''
    },

    initialize: function() {
    },

    validation: {
      username: {
        required: true,
        pattern: 'email',
        msg: 'Por favor especifique un email correcto'
      },
      password: {
        required: true,
        pattern: /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{4,8}$/,
        msg: 'El password debe tener por lo menos una letra mayuscula, una minuscula y un numero'
      }
    },



    login: function(callback, user, pass, remember) {
      var thatCallback = callback;
      var self = this;

      this.save({ username: user, password: pass }, {
        wait: true,

        success: function(model, response) {
          Cookies.set('auth_token', JSON.stringify({ username: user, token: model.get('token') }), { expires: 365 });

          self.set('authenticated', true);
          self.set('username', user)

          $.ajaxSetup({
            headers: {
              "X-Auth-Token": model.get('token')
            }
          });
          console.log('Successfully saved!');
          thatCallback();
        },
        error: function(model, error) {
          console.log("Error autenthication");
          // la parte de session esta mal Session
          Session.set('authenticated', false);
          Session.set('username', '');
          Cookies.remove('auth_token')
          new ModalGenericView({
            message: 'Usuario y/o contraseña incorrecta'
          });
        }
      });
    },

    logout: function(callback) {
      var thisSession = this;
      var that = callback;
      var Session = new SessionModel();
      Cookies.remove('auth_token');



      Session.save({ username: this.get('username'), logout: 'logout' }, {
        wait: true,
        success: function(model, response) {

          thisSession.clear();
          $.ajaxSetup({
            headers: {
              "X-Auth-Token": ''
            }
          });
          Session.set('username', '');
          Cookies.remove('auth_token');
          console.log('Successfully Remove cookie!');
          that();
        },
        error: function(model, error) {
          console.log(model.toJSON());
          console.log('error.responseText');
          window.location.reload();

        }
      });
    }
  });

  return new SessionModel();
});
