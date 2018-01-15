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
      // var vm = new ViewManager();

      // this.viewMana.showView(new MainAdminView().render());
      // console.log("after dashboard route");
    },

    configDashboard: function(){
      // console.log("config dashboard");
      this.viewMana.showView(new LigasListView());
      // this.viewMana.showView(new MainAdminView().render());


    },

    initialize: function() {
      this.viewMana = ViewManager;
    },


  });

  return DashBoardRoute;
});
