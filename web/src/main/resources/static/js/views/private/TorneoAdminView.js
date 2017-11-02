define([
	'jquery',
	'backbone',
	'bootstrap',
	'selecter',
	'core/BaseView',
	'collections/LigasCollection',
	'collections/TorneosCollection',
	'views/private/LigaDetailView',
	'views/private/TorneoDetailView',
	'views/private/TorneoEditView',
	'views/private/util/ModalGenericView',
	'text!templates/private/tplTorneoAdmin.html'
], function($, Backbone, bootstrap, selecter, BaseView, LigasCollection,
            TorneosCollection, LigaDetailView, TorneoDetailView, TorneoEditView,
            ModalGenericView, tplTorneoAdmin){

	var TorneoAdminView = BaseView.extend({
        template: _.template(tplTorneoAdmin),

        events: {
            'change #select-liga'       : 'changeLiga',
            'change #select-torneo'     : 'changeTorneo',
            'click #torneo-nuevo'       : 'newTorneo',
            'click #torneo-editar'      : 'editTorneo',
            'click #torneo-borrar'      : 'deleteTorneo'
        },

        initialize: function() {
            this.ligas = new LigasCollection();
            this.listenTo(this.ligas, 'sync', this.agregarLiga);
            this.listenTo(this.ligas, 'sync', this.syncLigas);

            app.torneos = new TorneosCollection();
            this.listenTo(app.torneos, 'sync', this.agregarTorneo);
            this.listenTo(app.torneos, 'sync', this.syncTorneos);

            this.ligas.fetch();
        },

        render: function() {
            this.$el.html(this.template());
            // this.$el.find(".selecter_liga").select();
            return this;
        },

        changeLiga: function(event) {
          var modelo = this.ligas.get($(event.target).val());

          if (typeof modelo != 'undefined') {
              this.ligaDetailView = new LigaDetailView({model: modelo});
              $('#liga-detail').html(this.ligaDetailView.render().$el);
              app.torneos.setLiga(modelo);
              app.torneos.fetch();
              $('#torneo-editar').removeAttr("disabled");
              $('#torneo-borrar').removeAttr("disabled");
          } else {
              $('#torneo-editar').attr("disabled", true);
              $('#torneo-borrar').attr("disabled", true);
          }
        },

        changeTorneo: function(event) {
          var modelo = app.torneos.get($(event.target).val());
          if (typeof modelo != 'undefined') {
              this.torneoDetailView = new TorneoDetailView({model: modelo});
              $('#torneo-detail').html(this.torneoDetailView.render().$el);
              $('#torneo-editar').removeAttr("disabled");
              $('#torneo-borrar').removeAttr("disabled");
          } else {
              if (typeof this.torneoDetailView !== 'undefined') {
                  this.torneoDetailView.destroyView();
              }
              $('#torneo-editar').attr("disabled", true);
              $('#torneo-borrar').attr("disabled", true);
          }
        },

        agregarLiga: function(collection) {
          $('#select-liga').empty();
          for(var i=0; i<collection.length; i++) {
            myModel = collection.models[i];
            $('#select-liga').append($('<option>', {
                value: collection.models[i].get('id'),
                text : collection.models[i].get('nombre')
            }));
          }
        },

        syncLigas: function() {
            $('#select-liga').change();
        },

        agregarTorneo: function(collection) {
          $('#select-torneo').empty();
          for(var i=0; i<collection.length; i++) {
            myModel = collection.models[i];
            $('#select-torneo').append($('<option>', {
                value: collection.models[i].get('id'),
                text : collection.models[i].get('nombre')
            }));
          }
        },


        syncTorneos: function() {
          $('#select-torneo').change();
        },

        newTorneo: function() {
            this.disabledAction(true);
            var ligaId = this.ligas.get($("#select-liga").val()).get('id');
            var torneoEditView = new TorneoEditView({tipo: 'new', idLiga: ligaId});
            $('#torneo-edit').html(torneoEditView.render().$el);
        },

        editTorneo: function() {
            this.disabledAction(true);
            var modelo = app.torneos.get($("#select-torneo").val());
            var torneoEditView = new TorneoEditView({tipo: 'edit', modelo: modelo});
            $('#torneo-edit').html(torneoEditView.render().$el);
        },

        deleteTorneo: function() {
            var modelo = app.torneos.get($("#select-torneo").val());
            modelo.destroy({
                wait:true,
                success: function(model, response) {
                    new ModalGenericView({message: response.message});
                    if(response.result){
                        $("#select-torneo option:selected").remove();
                        $('#select-torneo').change();
                    }
                },
                error: function(model, error) {
                    new ModalGenericView({message: error.responseJSON.message});
                }
            });
        }
	});

	return TorneoAdminView;

});
