define([
  'jquery',
  'core/BaseView',
  'backbone',
  'backboneValidation',
  'jquerySerializeObject',
  'models/UserModel',
  'text!templates/tplLogin.html',
  'jscookie',
  'views/RootAdminView'
], function($, BaseView, Backbone, backboneValidation, jquerySerializeObject, UserModel, tplLogin, Cookies, RootAdminView) {

  var LoginView = BaseView.extend({
    template: _.template(tplLogin),

    events: {
      'click .btn.btn-lg.btn-primary.btn-block': 'login'
    },

    initialize: function() {
      this.layoutMaster = Backbone.Radio.channel('app').request('appMn');
      this.$el.find('.tip').tooltip();
      this.model = new UserModel();
      Backbone.Validation.bind(this, {
        valid: function(view, attr, selector) {
          var $el = view.$('[name=' + attr + ']'),
            $group = $el.closest('.form-group');
          if(view.model.preValidate(attr, $el.val())) {
            return;
          }
          $group.removeClass('has-error');
          $group.addClass('has-success');
        },

        invalid: function(view, attr, error, selector) {
          var $el = view.$('[name=' + attr + ']'),
            $group = $el.closest('.form-group');
          $el.popover({
            content: error,
            placement: 'left'
          });
          $el.popover('show');
          $group.addClass('has-error');
        }

      });
    },


    render: function() {
      this.$el.html(this.template(this.model.toJSON()));
      return this;
    },

    remove: function() {
      // Remove the validation binding
      // See: http://thedersen.com/projects/backbone-validation/#using-form-model-validation/unbinding
      Backbone.Validation.unbind(this);
      return Backbone.View.prototype.remove.apply(this, arguments);
    },

    login: function() {
      var that = this;

      var data = this.$el.find("#form-login").serializeObject();

      this.model.set(data);
      this.model.set({ passwordConfirm: this.model.get('password') });

      if(this.model.isValid(true)) {
        var user = this.model.get('username');
        var pass = this.model.get('password');
        var remember = $("#remember").is(":checked");
        var rootLayout = Backbone.Radio.channel('app').request('rootLayout');

        Backbone.Radio.channel('app').request('session').login({
          username: user,
          password: pass
        }, {
          success: function(res) {
            Cookies.set('auth_token', JSON.stringify({ username: res.username, token: res.token }), { expires: 365 });

            $.ajaxSetup({
              headers: {
                "X-Auth-Token": res.token
              }
            });
            //Render master view
            //TODO verificar los roles en esta parte y al iniciar se redireccionara a su dashboard
            var regionMaster = that.layoutMaster.getRegion();
            regionMaster.show(new RootAdminView());
            Backbone.history.navigate('', { trigger: true });
          },
          error: function(err) {
            //TODO Redireccionar de nuevo a login o mandar mensaje de error.
            console.log("Error on login");
          },
          complete: function() {
            console.log("Complete login");
          }
        });
      }

    }

  });

  return LoginView;

});
