define([
  'jquery',
  'backbone',
  'marionette',
  'bootstrap',
  'text!templates/private/tplRootAdmin.html',
  'views/private/MainAdminNavView'
], function($, Backbone, Mn, bootstrap, tplRootAdmin, MainAdminNavView){

  var RootAdminView = Mn.View.extend({

    template: _.template(tplRootAdmin),

    regions: {
      headerWrap: '#header-region',
      containerWrap: '#container-region',
      footerWrap: '#footer-region'
    },

    onRender : function () {
      this.showChildView('headerWrap', new MainAdminNavView());
    },

  });

  return RootAdminView;

});
