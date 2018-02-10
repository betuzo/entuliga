define([
  'jquery',
  'backbone',
  'views/private/util/ModalGenericView',
  'jscookie',
  'models/UserModel'
], function($, Backbone, ModalGenericView, Cookies, UserModel) {

  var SessionModel = Backbone.Model.extend({
    url: 'session',

    defaults: {
      username: '',
      password: '',
      logged_in: false,
      user_id: ''

    },

    initialize() {
      // Singleton user object
      // Access or listen on this throughout any module with app.session.user
      this.user = new UserModel();
    },

    // Fxn to update user attributes after recieving API response
    updateSessionUser(userData) {
      this.user.set(_.pick(userData, _.keys(this.user.attributes)));
    },

    /*
     * Check for session from API
     * The API will parse client cookies using its secret token
     * and return a user object if authenticated
     */
    checkAuth(opts,callback, args) {
      var token = opts.token
      var self = this;
      // this.set('id', token);
      this.url = 'session/valid/' + token;
      this.fetch({
        success: function(mod, res) {
          if(res.token) {
            self.set({ logged_in: true });
            if('success' in callback) callback.success(mod, res);
          } else {
            self.set({ logged_in: false });
            if('error' in callback) callback.error(mod, res);
          }
        },
        error: function(mod, res) {
          self.set({ logged_in: false });
          if('error' in callback) callback.error(mod, res);
        }
      }).done(function(mod, res) {
        if('complete' in callback) callback.complete();
      });
    },

    /*
     * Abstracted fxn to make a POST request to the auth endpoint
     * This takes care of the CSRF header for security, as well as
     * updating the user and session after receiving an API response
     */
    postAuth(opts, callback, args) {
      this.url = 'session'
      var self = this;
      var postData = _.omit(opts, 'method');
      var urlroot = Backbone.Radio.channel('app').request('configval').urlRoot
      $.ajax({
        url: urlroot + this.url + '/' + opts.method,
        contentType: 'application/json',
        dataType: 'json',
        type: 'POST',
        beforeSend: function(xhr) {
          // Set the CSRF Token in the header for security
          // var token = $('meta[name="csrf-token"]').attr('content');
          // if(token) xhr.setRequestHeader('X-CSRF-Token', token);
        },
        data: JSON.stringify(_.omit(opts, 'method')),
        success: function(res) {
          if(!res.error) {
            if(_.indexOf(['login', 'signup'], opts.method) !== -1) {
              self.updateSessionUser(res || {});
              self.set({ user_id: res.id, username: res.username, logged_in: true });
            } else {
              self.set({ logged_in: false });
            }

            if(callback && 'success' in callback) callback.success(res);
          } else {
            if(callback && 'error' in callback) callback.error(res);
          }
        },
        error: function(mod, res) {
          if(callback && 'error' in callback) callback.error(res);
        }
      }).done(function() {
        if(callback && 'complete' in callback) callback.complete();
      });
    },

    login(opts, callback, args) {
      this.postAuth(_.extend(opts, { method: 'login' }), callback);
    },

    logout(opts, callback, args) {
      this.postAuth(_.extend(opts, { method: 'logout' }), callback);
    },

    signup(opts, callback, args) {
      this.postAuth(_.extend(opts, { method: 'signup' }), callback);
    },

    removeAccount(opts, callback, args) {
      this.postAuth(_.extend(opts, { method: 'remove_account' }), callback);
    },

  });

  return SessionModel;
});