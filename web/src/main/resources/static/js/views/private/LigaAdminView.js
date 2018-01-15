define([
  'jquery',
  'backbone',
  'bootstrap',
  'selecter',
  'core/BaseView',
  'collections/LigasCollection',
  'views/private/LigaDetailView',
  'views/private/LigaEditView',
  'views/private/util/ModalGenericView',
  'text!templates/private/tplLigaAdmin.html'
], function($, Backbone, bootstrap, selecter, BaseView, LigasCollection, LigaDetailView, LigaEditView, ModalGenericView, tplLigaAdmin) {

  var LigaAdminView = BaseView.extend({
    template: _.template(tplLigaAdmin),

    events: {
      'change #select-liga': 'changeLiga',
      'click #liga-nuevo': 'newLiga',
      'click #liga-editar': 'editLiga',
      'click #liga-borrar': 'deleteLiga'
    },

    initialize: function() {
      app.ligas = new LigasCollection();
      this.listenTo(app.ligas, 'add', this.agregarLiga);
      this.listenTo(app.ligas, 'sync', this.syncLigas);

      app.ligas.fetch();
    },

    render: function() {
      this.$el.html(this.template());
      this.$el.find(".selecter_liga").select();
      return this;
    },

    changeLiga: function(event) {
      var modelo = app.ligas.get($(event.target).val());
      if(typeof modelo != 'undefined') {
        this.ligaDetailView = new LigaDetailView({ model: modelo });
        $('#liga-detail').html(this.ligaDetailView.render().$el);
        $('#liga-editar').removeAttr("disabled");
        $('#liga-borrar').removeAttr("disabled");
      } else {
        if(typeof this.ligaDetailView !== 'undefined') {
          this.ligaDetailView.destroyView();
        }
        $('#liga-editar').attr("disabled", true);
        $('#liga-borrar').attr("disabled", true);
      }
    },

    newLiga: function() {
      this.disabledAction(true);
      var ligaEditView = new LigaEditView({ tipo: 'new', modelo: null });
      $('#liga-edit').html(ligaEditView.render().$el);
    },

    editLiga: function() {
      this.disabledAction(true);
      var modelo = app.ligas.get($("#select-liga").val());
      var ligaEditView = new LigaEditView({ tipo: 'edit', modelo: modelo });
      $('#liga-edit').html(ligaEditView.render().$el);
    },

    deleteLiga: function() {
      var modelo = app.ligas.get($("#select-liga").val());
      modelo.destroy({
        wait: true,
        success: function(model, response) {
          new ModalGenericView({ message: response.message });
          if(response.result) {
            $("#select-liga option:selected").remove();
            $('#select-liga').change();
          }
        },
        error: function(model, error) {
          new ModalGenericView({ message: error.responseJSON.message });
        }
      });
    },

    agregarLiga: function(modelo) {
      $('#select-liga').append($('<option>', {
        value: modelo.get('id'),
        text: modelo.get('nombre')
      }));
    },

    syncLigas: function() {
      $('#select-liga').change();
    }
  });

  return LigaAdminView;

});
