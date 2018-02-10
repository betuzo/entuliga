define([
  'jquery',
  'backbone',
  'marionette',
  'subroute',
  'core/ViewManager',
  'views/private/MainAdminView',
  'views/private/LigaAdminView',
  'views/private/dashboard/LigasListView'
], function($, Backbone, Mn, Subroute, ViewManager, MainAdminView, LigaAdminView, LigasListView) {

  var DashBoardRoute = Backbone.SubRoute.extend({
    routes : {
      '' : 'dashboard',
      'config': 'configDashboard'
    },

    dashboard : function (opt) {

      this.viewMana.showView(new LigaAdminView());
    },

    configDashboard: function(){
      this.viewMana.showView(new LigasListView());
    },

    initialize: function() {
      this.viewMana = new ViewManager();
    },
  });

  return DashBoardRoute;
});
