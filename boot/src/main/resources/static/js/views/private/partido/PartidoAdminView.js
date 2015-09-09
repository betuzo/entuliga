define([
	'jquery',
	'backbone',
	'bootstrap',
	'selecter',
	'core/BaseView',
	'models/TorneoPartidoModel',
	'views/private/partido/PartidoLocalView',
	'views/private/partido/PartidoVisitaView',
	'views/private/partido/PartidoArbitrosView',
	'views/private/partido/PartidoEditView',
	'views/private/estadistica/EstadisticaResumenView',
	'views/private/estadistica/EstadisticaPuntosView',
	'views/private/estadistica/PuntoCreateView',
	'text!templates/private/partido/tplPartidoAdmin.html'
], function($, Backbone, bootstrap, selecter, BaseView, TorneoPartidoModel,
            PartidoLocalView, PartidoVisitaView, PartidoArbitrosView,
            PartidoEditView, EstadisticaResumenView, EstadisticaPuntosView,
            PuntoCreateView, tplPartidoAdmin){

	var PartidoAdminView = BaseView.extend({
        template: _.template(tplPartidoAdmin),

        events: {
            'click #partido-editar'             : 'partidoEditar',
            'click #agregar-arbitro'            : 'agregarArbitro',
            'click #partido-comenzar'           : 'partidoComenzar',
            'click #partido-cancelar'           : 'partidoCancelar',
            'click #partido-finalizar'          : 'partidoFinalizar',

            'click #partido-puntos-local'       : 'partidoPuntosLocal',
            'click #partido-faltas-local'       : 'partidoFaltasLocal',
            'click #partido-cambios-local'      : 'partidoCambiosLocal',
            'click #partido-asistencias-local'  : 'partidoAsistenciasLocal',
            'click #partido-bloqueos-local'     : 'partidoBloqueosLocal',
            'click #partido-rebotes-local'      : 'partidoRebotesLocal',
            'click #partido-robos-local'        : 'partidoRobosLocal',

            'click #partido-puntos-visita'      : 'partidoPuntosVisita',
            'click #partido-faltas-visita'      : 'partidoFaltasVisita',
            'click #partido-cambios-visita'     : 'partidoCambiosVisita',
            'click #partido-asistencias-visita' : 'partidoAsistenciasVisita',
            'click #partido-bloqueos-visita'    : 'partidoBloqueosVisita',
            'click #partido-rebotes-visita'     : 'partidoRebotesVisita',
            'click #partido-robos-visita'       : 'partidoRobosVisita'
        },

        initialize: function(idPartido) {
            this.model = new TorneoPartidoModel();
            this.listenTo(this.model, 'sync', this.syncPartido);
        },

        render: function() {
            return this;
        },

        syncPartido: function() {
            this.$el.html(this.template(this.model.toJSON()));
            $('#estadistica-tab a').click(function (e) {
              e.preventDefault()
              $(this).tab('show')
            })

            new PartidoLocalView(this.model);
            new PartidoVisitaView(this.model);
            new PartidoArbitrosView(this.model);
            new EstadisticaPuntosView(this.model);
            new EstadisticaResumenView(this.model);
        },

        setIdPartido: function(idPartido) {
            this.model.set('id', idPartido);
            this.model.fetch();
        },

        partidoEditar: function() {
            new PartidoEditView({modelo: this.model, callbackAceptar: this.successSavePartido});
        },

        successSavePartido: function(partido) {

        },

        agregarArbitro: function() {

        },

        partidoPuntosLocal: function() {
            new PuntoCreateView({modelo: this.model, callbackAceptar: this.successAddPunto,
                                 origen: 'LOCAL', parent: this});
        },

        partidoPuntosVisita: function() {
            new PuntoCreateView({modelo: this.model, callbackAceptar: this.successAddPunto,
                                 origen: 'VISITA', parent: this});
        },

        successAddPunto: function(punto) {
            if (punto.get('origen') == 'LOCAL') {
                var puntoLocal = this.parent.model.get('localPuntos') + punto.get('tipoValor');
                this.parent.model.set({localPuntos: puntoLocal});
            } else {
                var puntoVisita = this.parent.model.get('visitaPuntos') + punto.get('tipoValor');
                this.parent.model.set({visitaPuntos: puntoVisita});
            }
            $('#section-estadisticas-resumen').html('');
            new EstadisticaResumenView(this.parent.model);
            app.puntosPartido.add(punto);
            this.destroyView();
        },

        partidoFaltasLocal: function() {

        },

        partidoCambiosLocal: function() {

        },

        partidoAsistenciasLocal: function() {

        },

        partidoBloqueosLocal: function() {

        },

        partidoRebotesLocal: function() {

        },

        partidoRobosLocal: function() {

        },

        partidoFaltasVisita: function() {

        },

        partidoCambiosVisita: function() {

        },

        partidoAsistenciasVisita: function() {

        },

        partidoBloqueosVisita: function() {

        },

        partidoRebotesVisita: function() {

        },

        partidoRobosVisita: function() {

        },

        partidoComenzar: function() {
            this.model.set({statusPartido: 'ENPROCESO'});
            this.saveModel();
        },

        partidoCancelar: function() {
            this.model.set({statusPartido: 'CANCELADO'});
            this.saveModel();
        },

        partidoFinalizar: function() {
            this.model.set({statusPartido: 'FINALIZADO'});
            this.saveModel();
        },

        saveModel: function() {
            that = this;
            if(this.model.isValid(true)){
                this.model.save({}, {
                    wait:true,
                    success:function(model, response) {
                        console.log('Successfully saved!');
                        alert('Great Success!');
                    },
                    error: function(model, error) {
                        console.log(model.toJSON());
                        console.log('error.responseText');
                    }
                });
            }
        }
	});

	return PartidoAdminView;

});