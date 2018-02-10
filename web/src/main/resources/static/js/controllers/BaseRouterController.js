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

    defaultAdminList: function() {
      return [];
    },

    defaultAnonUserUrl: function() {
      return [];
    },




    filterurl: function(filter) {
      filter = filter || '#';
      let options = {};

      var arrayAdmin = this.defaultAdminUrl();
      var arrayUser = this.defaultAnonUserUrl();


      if(_.where(this.defaultAdminUrl(), { 'url': filter }).length) {
        options = _.extend(options, { requiresAuth: true });
      }

      if(_.where(this.defaultAnonUserUrl(), { 'url': filter }).length) {
        options = _.extend(options, { preventAccessWhenAuth: true });
      }

      if(options.requiresAuth) {
        var urlAdmin = arrayAdmin.filter(function(obj) {
          return obj.url == filter;
        });

        if(urlAdmin.length > 0) {
          this.checkAccess(urlAdmin[0].method, options, () => this[urlAdmin[0].method](options)); //llamar a esta linea solo cuando alla alguna cookie

        } else {
          Backbone.history.navigate('', { trigger: true });
          console.log("algo no esta definido en array admin");
        }

      } else if(options.preventAccessWhenAuth) {
        var urlUser = arrayUser.filter(function(obj) {
          return obj.url == filter;
        });

        if(urlUser.length > 0) {
          this.checkAccess(urlUser[0].method, options, () => this[urlUser[0].method](options)); //llamar a esta linea solo cuando alla alguna cookie
        } else {
          //se podria llamar a alguna pagina de error
          Backbone.history.navigate('', { trigger: true });
          console.log("algo no esta definido en array user");
        }

      } else {
        Backbone.history.navigate('', { trigger: true });
        console.log("no hay match de esta url");
      }
    },


    checkAccess: function(filter, opt = {}, callback) {
      var that = this;
      // Need to be authenticated before rendering view.
      // For cases like a user's settings page where we need to double check against the server.

      //verificar en la session principal
      if(opt.preventAccessWhenAuth) {

        // console.log("hay que verificar si esta logueado ");
        if(Cookie.get('auth_token') !== undefined) {
          var user = JSON.parse(Cookie.get('auth_token'));

          this.session.checkAuth({ token: user.token }, {
            success: (res => {

              callback();
            }),
            error: (res => {
              console.log("error app session");
              Cookies.remove('auth_token');
              Backbone.history.navigate('login', { trigger: true });
              // callback();
            })
          });

        } else {
          callback(); //dejamos que pase el route
        }
      } else if(opt.requiresAuth) {
        //requiere autenticacion
        if(Cookie.get('auth_token') !== undefined) {
          var user = JSON.parse(Cookie.get('auth_token'));

          this.session.checkAuth({ token: user.token }, {
            success: (res => {
              callback(); //dejamos pasar la url
            }),
            error: (res => {
              //regresamos al login
              console.log("error en base route");
              Backbone.history.navigate('login', { trigger: true });
            })
          });

        } else {
          //no esta definido en los arrays
          Backbone.history.navigate('', { trigger: true });
          // callback(); //dejamos que pase el route
        }

      } else {
        console.log("no entra en filter");
        callback(); //dejamos pasar el callback
      }

    },

    changeView: function(view) {
      function setView(view) {
        if(this.currentView) {
          if(typeof this.currentView.close === "function") {
            this.currentView.close();
          } else {
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
