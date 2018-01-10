define([
  'jquery',
  'core/BaseView',
  'text!templates/private/tplMainAdminNav.html',
  'Session'
], function($, BaseView, tplMainAdminNav, Session) {

  var MainAdminNavView = BaseView.extend({
    // el: $("#hotel-nav"),

    template: _.template(tplMainAdminNav),

    events: {
      'click #logout': 'logout',
      'click #home': 'homeAdmin',
      'click li.nav-item-entuliga': 'activeMenu'
    },

    initialize: function() {
      // this.listenTo("reset", this.updateView);
      // this.listenTo('destroy', this.remove);
      // this.bind("reset", this.remove());
      this.render();
    },

    render: function() {
      this.$el.html(this.template());
      // this.remove();
      return this;
    },

    logout: function() {
      Session.logout(function(response) {
        Backbone.history.navigate('', { trigger: true });
        window.location.reload();
      });
    },

    homeAdmin: function() {
      Backbone.history.navigate('admin', { trigger: true });
    },

    activeMenu: function(event) {
      $(event.target).parent().addClass('active');
      // $('.nav-item-entuliga').removeClass('active');
    },

    destroy_view: function() {
      // COMPLETELY UNBIND THE VIEW
      this.undelegateEvents();

      this.$el.removeData().unbind();

      // Remove view from DOM
      this.remove();
      Backbone.View.prototype.remove.call(this);
    }
  });

  return MainAdminNavView;

});
