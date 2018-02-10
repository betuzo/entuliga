define([
  'jquery',
  'backbone',
  'marionette',
  'bootstrap',
  'text!templates/private/tplRootAdmin.html',
  'views/private/MainAdminNavView',
  'views/public/MainView'
], function($, Backbone, Mn, bootstrap, tplRootAdmin, MainAdminNavView, MainView){

  var RootAdminView = Mn.View.extend({

    template: _.template(tplRootAdmin),

    regions: {
      headerWrap: '#header-region',
      containerWrap: '#container-body',
      footerWrap: '#footer-region'
    },

    onRender : function () {
      this.showChildView('headerWrap', new MainAdminNavView());
      this.showChildView('containerWrap', new MainView());
    },

  });

  return RootAdminView;

});
