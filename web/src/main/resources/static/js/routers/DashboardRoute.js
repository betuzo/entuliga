define([
  'jquery',
  'backbone',
  'marionette',
  'subroute',
  'core/ViewManager',
  'views/private/MainAdminView'
], function($, Backbone, Mn, Subroute, ViewManager, MainAdminView) {

  var DashBoardRoute = Backbone.SubRoute.extend({
    routes : {
      '' : 'dashboard',
      'config': 'configDashboard'
    },


    dashboard : function (opt) {
      console.log("index dashboard");
      console.log('opt');
      console.log(opt);

      // var vm = new ViewManager();

      this.viewMana.showView(new MainAdminView().render());
      console.log("after dashboard route");
    },

    configDashboard: function(){

      console.log("config dashboard");
      this.viewMana.showView(new MainAdminView().render());


    },

    initialize: function() {
      this.viewMana = new ViewManager();
    },


  });

  return DashBoardRoute;
});
