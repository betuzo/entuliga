define([
  'jquery',
  'backbone',
  'marionette',
  'subroute'
], function($, Backbone, Mn, Subroute) {

  var DashBoardController = Mn.Object.extend({
    initialize: function() {
      console.log("initialize controller Dashboard");
    },

    showuser: function() {
      
    },

  });

  return DashBoardController;
});
