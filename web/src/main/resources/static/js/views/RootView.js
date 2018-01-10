define([
  'jquery',
  'backbone',
  'marionette',
  'bootstrap',
  'text!templates/tplRoot.html',
  'views/public/MainNavView'
], function($, Backbone, Mn, bootstrap, tplRoot, MainNavView){

  var RootView = Mn.View.extend({

    template: _.template(tplRoot),

    regions: {
      headerWrap: '#header-region',
      containerWrap: '#container-region',
      footerWrap: '#footer-region'
    },

    onRender : function () {
      this.showChildView('headerWrap', new MainNavView());
    },

  });

  return RootView;

});
