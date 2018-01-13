define([
  'jquery',
  'backbone',
  'marionette',
  'backboneValidation',
  'jquerySerializeObject',
  'router',
  'Session',
  'models/UserTokenModel',
  'views/public/MainNavView',
  'views/RootView',
  'views/RootAdminView',
  'controllers/DashBoardController',
  'jscookie'
], function($, Backbone, Mn, backboneValidation, jquerySerializeObject, Router, Session, UserTokenModel, MainNavView, RootView, RootAdminView, DashBoardController, Cookie) {

  var pleaseWaitDiv = $('<div class="modal fade" data-keyboard="false" tabindex="-1"><div class="modal-base"><img src="img/basket.gif" height="150px" width="150px" style="display: block; margin: auto;"/></div></div>');
  var callServers = 0;

  var ApplicationModel = Mn.Application.extend({

    region: '#app',


    initialize: function() {
      console.log('initialize marionette');
      this.controllers = {
        dashboard: new DashBoardController(),
      };
      this.modeltoken = new UserTokenModel();
    },

    onBeforeStart: function() {
      console.log('onBeforeStart marionette');
    },

    onStart: function() {
      console.log('onStart marionette');
      var that = this;

      if(Cookie.get('auth_token') !== undefined) {
        this.showView(new RootAdminView());
        var user = JSON.parse(Cookie.get('auth_token'));
        if(user != null) {
          //TODO Se tiene que verificar el token que contiene la cookie con el servidor
          // var resToken = this.modeltoken.checkAuth(user.token, function(){
          //   console.log("callback");
          // });

          //Si es valido el token lo ponemos en el encabezado
          $.ajaxSetup({
            headers: {
              "X-Auth-Token": user.token
            }
          });
          Session.set('authenticated', true);
          Session.set('username', user.username);
        }
      }else{
        $.ajaxSettings.headers = [];
        Session.set('authenticated', false);
        this.showView(new RootView());
      }

      var router = new Router();
      Backbone.history.start();


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
            // Redirec the to the login page.
            Session.set('authenticated', false);
            Session.set('username', '');
            Cookie.remove('auth_token');
            if($.ajaxSettings.headers["X-Auth-Token"] !== 'undefined') {
              delete $.ajaxSettings.headers["X-Auth-Token"];
            }
            Backbone.history.navigate('login', { trigger: true });
            window.location.reload();

          },
          403: function() {
            // 403 -- Access denied
            console.log("ERROR 403");
            //
            // Session.set('authenticated', false);
            // Session.set('username', '');
            // $.cookie("auth_token", null, { path: '/' });
            // if($.ajaxSettings.headers["X-Auth-Token"] !== 'undefined') {
            //   delete $.ajaxSettings.headers["X-Auth-Token"];
            // }
            // Backbone.history.navigate('login', { trigger: true });
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
          this.url = 'http://localhost:8090/' + this.url;
        };
        return sync.call(this, method, model, options);
      };
    }

  });



  return ApplicationModel;
});
