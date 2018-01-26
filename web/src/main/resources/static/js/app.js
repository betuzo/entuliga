define([
  'jquery',
  'backbone',
  'marionette',
  'backboneValidation',
  'jquerySerializeObject',
  'router',
  'views/public/MainNavView',
  'views/RootView',
  'views/RootAdminView',
  'jscookie',
  'core/SessionModel',
  'backbone.radio',
  'ControllerRoute',
  'core/CoreRouterMn'
], function($, Backbone, Mn, backboneValidation, jquerySerializeObject, Router, MainNavView, RootView, RootAdminView, Cookie, SessionModel, Radio, ControllerRoute, CoreRouterMn) {

  var pleaseWaitDiv = $('<div class="modal fade" data-keyboard="false" tabindex="-1"><div class="modal-base"><img src="img/basket.gif" height="150px" width="150px" style="display: block; margin: auto;"/></div></div>');
  var callServers = 0;

  var ApplicationModel = Mn.Application.extend({

    region: '#app',

    urlRoot: 'http://localhost:8090/', //CORS mientras

    initialize: function() {},

    onBeforeStart: function() {
      // console.log('onBeforeStart marionette');
      var that = this;

      $.ajaxSetup({
        cache: false,
        beforeSend: function() {
          pleaseWaitDiv.modal('show');
          callServers = callServers + 1;
        },
        complete: function() {
          if(callServers <= 1) {
            pleaseWaitDiv.modal('hide');
          }
          callServers = callServers - 1;
        },
        statusCode: {
          401: function() {
            console.log("ERROR 401");

          },
          403: function() {
            console.log("ERROR 403");
          }

        }
      });

      // Extend the callbacks to work with Bootstrap, as used in this example
      // See: http://thedersen.com/projects/backbone-validation/#configuration/callbacks
      _.extend(Backbone.Validation.callbacks, {
        valid: function(view, attr, selector) {
          var $el = view.$('[name=' + attr + ']'),
            $group = $el.closest('.form-group');

          $group.removeClass('has-error');
          $group.find('.help-block').html('').addClass('hidden');
        },
        invalid: function(view, attr, error, selector) {
          var $el = view.$('[name=' + attr + ']'),
            $group = $el.closest('.form-group');

          $group.addClass('has-error');
          $group.find('.help-block').html(error).removeClass('hidden');
        }
      });

      $.fn.serializeObject = function() {
        "use strict";
        var a = {},
          b = function(b, c) {
            var d = a[c.name];
            "undefined" != typeof d && d !== null ? $.isArray(d) ? d.push(c.value) : a[c.name] = [d, c.value] : a[c.name] = c.value
          };
        return $.each(this.serializeArray(), b), a
      };

      //REST URL
      var sync = Backbone.sync;
      Backbone.sync = function(method, model, options) {
        options.beforeSend = function() {
          this.url = that.urlRoot + this.url;
        };
        return sync.call(this, method, model, options);
      };

      this.rootLayout = new RootView();
      this.session = new SessionModel();
      this.controlllerRoute = new ControllerRoute({ that: this });

      this.channel = Backbone.Radio.channel('app');
      this.channel.reply('session', this.session);
      this.channel.reply('configval', { urlRoot: this.urlRoot });
      this.channel.reply('rootLayout', this.rootLayout);
      this.channel.reply('controllerRoute', this.controlllerRoute);
      this.channel.reply('appMn', this);
    },

    onStart: function() {
      // console.log('onStart marionette');
      var that = this;
      if(Cookie.get('auth_token') !== undefined) {
        var user = JSON.parse(Cookie.get('auth_token'));

        if(user != null) {
          this.session.set('username', user.username);
          this.session.checkAuth({ token: user.token }, {
            //la respuesta al verificar el token deben venir el username, roles, token y los roles, por que se esta recuperando del la cookie
            // Start the backbone routing once we have captured a user's auth status
            success: function(mod, res) {
              that.rootLayout = new RootAdminView();
              that.showView(that.rootLayout);
              $.ajaxSetup({
                headers: {
                  "X-Auth-Token": user.token
                }
              });
            },
            error: function(mod, res) {
              console.log("Error al verificar el token");
            },
            complete: function() {
              // console.log("complete checkaut");
            }
          });
        }
      } else {
        this.showView(this.rootLayout);
      }

      // var router = new Router();
      // Backbone.history.start();
      this.controlllerRoute.router = new CoreRouterMn({
        controller: this.controlllerRoute
      });

      this.controlllerRoute.start();
      Backbone.history.start();


    }
  });
  return ApplicationModel;
});
