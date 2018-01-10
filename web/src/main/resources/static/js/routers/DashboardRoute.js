define([
  'jquery',
  'backbone',
  'marionette',
  'subroute'
], function($, Backbone, Mn, Subroute) {

  var DashBoardRoute = Backbone.SubRoute.extend({
    routes : {
      '' : 'dashboard',
      'config': 'configDashboard'
    },

    dashboard : function (churchId) {
      console.log("index dashboard");
    },

    configDashboard: function(){
      console.log("config dashboard");
    },

    initialize: function() {},


  });

  return DashBoardRoute;
});
