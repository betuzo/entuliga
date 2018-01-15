define([
  'jquery',
  'backbone',
  'marionette',
  'bootstrap',
  'selecter',
  'core/BaseView',
  'collections/LigasCollection',
  'views/private/LigaDetailView',
  'views/private/LigaEditView',
  'views/private/util/ModalGenericView',
  'text!templates/private/tplLigaAdmin.html',
  'text!templates/private/dashboard/tplLigasList.html'

], function($, Backbone, Mn, bootstrap, selecter, BaseView, LigasCollection,
  LigaDetailView, LigaEditView, ModalGenericView, tplLigaAdmin, tplLigasList) {

  var LigasListView = Mn.View.extend({
    template: _.template(tplLigasList),

    regions: {
      listLigas: '#list-ligas',
    },

    initialize: function() {
      this.ligas = new LigasCollection();
      this.listenTo(this.ligas, 'add', this.agregarLiga);
      this.listenTo(this.ligas, 'sync', this.syncLigas);
      this.ligas.fetch();
    },

    agregarLiga: function(modelo) {
    },

    syncLigas: function(collection) {
      for(var i = 0; i < collection.length; i++) {
        $('.add-cl').append(collection.models[i].get('nombre'));
      }
    },

    onBeforeDestroy: function() {
      console.log('before:destroy');
    },
    onDestroy: function(){
      console.log("destroy");
    },

    onDomRemove: function() {
      console.log('dom:remove');
    },

    onBeforeDetach: function() {
      console.log('before:detach');
    },

    onBeforeRender: function() {
      console.log('before:render');
    },

    onRender: function() {
      console.log('render');
    },

    onDetach: function() {
      console.log('detach');
    },




  });

  return LigasListView;

});
