define([
  'jquery',
  'core/BaseView',
  'text!templates/private/tplMainAdminNav.html',
  'Session'
], function($, BaseView, tplMainAdminNav, Session) {

  var MainAdminNavView = BaseView.extend({

    template: _.template(tplMainAdminNav),

    events: {
      'click #logout': 'logout',
      'click #home': 'homeAdmin',
      'click .nav-item-entuliga': 'activeMenu'
    },

    initialize: function() {
      this.render();
    },

    render: function() {
      this.$el.html(this.template());
      return this;
    },

    logout: function() {
      Session.logout(function(response) {
        Backbone.history.navigate('', { trigger: true });
      });
    },

    homeAdmin: function() {
      Backbone.history.navigate('admin', { trigger: true });
    },

    activeMenu: function(event) {
      $('.nav-item-entuliga').removeClass('active');
      $(event.target).parent().addClass('active');
    },
  });

  return MainAdminNavView;

});
