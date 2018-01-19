define([
  'jquery',
  'backbone',
  'marionette',
  'jscookie',
], function($, Backbone, Mn, Cookie) {

  var BaseRouterController = Mn.Object.extend({

    initialize: function(opts) {
      //debemos de recuperar el username
      // console.log(opts.that.session.get('username'));
      this.session = opts.that.session;
    },
    // Action that need authentication and if user is not authenticated
    // gets redirect to login page
    requresAuth: function() {
      return [];
    },

    // Routes that should not be accessible if user is authenticated
    // for example, login, register, forgetpasword ...
    preventAccessWhenAuth: function() {
      return [];
    },

    filter: function(filter) {
      filter = filter || '#';
      let options = {};

      if(this[filter] && typeof this[filter] === 'function') {

        if(~this.requresAuth().indexOf(filter)) {
          options = _.extend(options, { requiresAuth: true });
        }

        if(~this.preventAccessWhenAuth().indexOf(filter)) {
          options = _.extend(options, { preventAccessWhenAuth: true });
        }

        this.checkAccess(filter, options, () => this[filter](options));
      }

    },


    checkAccess: function(filter, opt = {}, callback) {
      var that = this;
      // Need to be authenticated before rendering view.
      // For cases like a user's settings page where we need to double check against the server.
      if(Cookie.get('auth_token') !== undefined) {
        var user = JSON.parse(Cookie.get('auth_token'));

        this.session.checkAuth({token: user.token },{
          success: (res => {
            if(opt.preventAccessWhenAuth) {
              Backbone.history.navigate('', { trigger: true });
            } else {
              callback();
            }
          }),
          error: (res => {
            console.log("error app session");
            if(opt.requiresAuth) {
              Backbone.history.navigate('login', { trigger: true });
            } else {
              callback();
            }
          })
        });
      }else{
        Backbone.history.navigate('', { trigger: true });
        console.log("no existe cokkie y lo enviamos al indexdefault");
      }


      if(opt.requiresAuth || opt.preventAccessWhenAuth) {



      } else {
        callback();
      }
    },

    // // if we change route, then to need change current view
    // changeView: function(view) {
    //   console.log("change view");
    //   // Close and unbind any existing page view
    //   if(this.currentView && _.isFunction(this.currentView.close)) {
    //     this.currentView.close();
    //   }
    //
    //   // Establish the requested view into scope
    //   this.currentView = view;
    //
    //   // Re-delegate events (unbound when closed)
    //   //this.currentView.delegateEvents(this.currentView.events)
    //
    //   //En esta parte recuperar el layout principal que va en el rror de marionette js
    //   // Backbone.Radio.channel('app').request('layout').showChildView('main', view);
    // }

    changeView: function(view) {
      function setView(view) {
        if(this.currentView) {
          if(typeof this.currentView.close === "function") {
            this.currentView.close();
          }else{
            if(typeof this.currentView.destroy === "function") {
              this.currentView.destroy(); //cerrar vistas de marionette
            }
          }
        }
        this.currentView = view;
        $('#container-body').html(view.render().$el);
      }
      setView(view);
    },


  });

  return BaseRouterController;
});
