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
	'views/private/estadistica/EstadisticaFaltasView',
	'views/private/estadistica/EstadisticaMovimientosView',
	'views/private/estadistica/EstadisticaAsistenciasView',
	'views/private/estadistica/EstadisticaBloqueosView',
	'views/private/estadistica/EstadisticaRebotesView',
	'views/private/estadistica/EstadisticaRobosView',
	'views/private/estadistica/PuntoCreateView',
	'views/private/estadistica/FaltaCreateView',
	'views/private/estadistica/MovimientoCreateView',
	'views/private/estadistica/AsistenciaCreateView',
	'views/private/estadistica/BloqueoCreateView',
	'views/private/estadistica/ReboteCreateView',
	'views/private/estadistica/RoboCreateView',
	'text!templates/private/partido/tplPartidoAdmin.html'
], function($, Backbone, bootstrap, selecter, BaseView, TorneoPartidoModel,
            PartidoLocalView, PartidoVisitaView, PartidoArbitrosView,
            PartidoEditView, EstadisticaResumenView, EstadisticaPuntosView,
            EstadisticaFaltasView, EstadisticaMovimientosView, EstadisticaAsistenciasView,
            EstadisticaBloqueosView, EstadisticaRebotesView, EstadisticaRobosView,
            PuntoCreateView, FaltaCreateView, MovimientoCreateView, AsistenciaCreateView,
            BloqueoCreateView, ReboteCreateView, RoboCreateView, tplPartidoAdmin){

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
            new EstadisticaPuntosView({modelo: this.model, parent: this});
            new EstadisticaFaltasView({modelo: this.model, parent: this});
            new EstadisticaMovimientosView({modelo: this.model, parent: this});
            new EstadisticaAsistenciasView({modelo: this.model, parent: this});
            new EstadisticaBloqueosView({modelo: this.model, parent: this});
            new EstadisticaRebotesView({modelo: this.model, parent: this});
            new EstadisticaRobosView({modelo: this.model, parent: this});
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

        successRemovePunto: function(punto) {
            if (punto.get('origen') == 'LOCAL') {
                var puntoLocal = this.model.get('localPuntos') - punto.get('tipoValor');
                this.model.set({localPuntos: puntoLocal});
            } else {
                var puntoVisita = this.model.get('visitaPuntos') - punto.get('tipoValor');
                this.model.set({visitaPuntos: puntoVisita});
            }
            $('#section-estadisticas-resumen').html('');
            new EstadisticaResumenView(this.model);
            app.puntosPartido.remove(punto);
        },

        partidoFaltasLocal: function() {
            new FaltaCreateView({modelo: this.model, callbackAceptar: this.successAddFalta,
                                             origen: 'LOCAL', parent: this});
        },

        partidoFaltasVisita: function() {
            new FaltaCreateView({modelo: this.model, callbackAceptar: this.successAddFalta,
                                             origen: 'VISITA', parent: this});
        },

        successAddFalta: function(falta) {
            app.faltasPartido.add(falta);
            this.destroyView();
        },

        successRemoveFalta: function(falta) {
            app.faltasPartido.remove(falta);
        },

        partidoCambiosLocal: function() {
            new MovimientoCreateView({modelo: this.model, callbackAceptar: this.successAddMovimiento,
                                              origen: 'LOCAL', parent: this});
        },

        partidoCambiosVisita: function() {
            new MovimientoCreateView({modelo: this.model, callbackAceptar: this.successAddMovimiento,
                                              origen: 'VISITA', parent: this});
        },

        successAddMovimiento: function(movimiento) {
            app.movimientosPartido.add(movimiento);
            this.destroyView();
        },

        successRemoveMovimiento: function(movimiento) {
            app.movimientosPartido.remove(movimiento);
        },

        partidoAsistenciasLocal: function() {
            new AsistenciaCreateView({modelo: this.model, callbackAceptar: this.successAddAsistencia,
                                        origen: 'LOCAL', parent: this});
        },

        partidoAsistenciasVisita: function() {
            new AsistenciaCreateView({modelo: this.model, callbackAceptar: this.successAddAsistencia,
                                        origen: 'VISITA', parent: this});
        },

        successAddAsistencia: function(asistencia) {
            app.asistenciasPartido.add(asistencia);
            this.destroyView();
        },

        successRemoveAsistencia: function(asistencia) {
            app.asistenciasPartido.remove(asistencia);
        },

        partidoBloqueosLocal: function() {
            new BloqueoCreateView({modelo: this.model, callbackAceptar: this.successAddBloqueo,
                                        origen: 'LOCAL', parent: this});

        },

        partidoBloqueosVisita: function() {
            new BloqueoCreateView({modelo: this.model, callbackAceptar: this.successAddBloqueo,
                                        origen: 'VISITA', parent: this});
        },

        successAddBloqueo: function(bloqueo) {
            app.bloqueosPartido.add(bloqueo);
            this.destroyView();
        },

        successRemoveBloqueo: function(bloqueo) {
            app.bloqueosPartido.remove(bloqueo);
        },

        partidoRebotesLocal: function() {
            new ReboteCreateView({modelo: this.model, callbackAceptar: this.successAddRebote,
                                        origen: 'LOCAL', parent: this});
        },

        partidoRebotesVisita: function() {
            new ReboteCreateView({modelo: this.model, callbackAceptar: this.successAddRebote,
                                        origen: 'VISITA', parent: this});
        },

        successAddRebote: function(rebote) {
            app.rebotesPartido.add(rebote);
            this.destroyView();
        },

        successRemoveRebote: function(rebote) {
            app.rebotesPartido.remove(rebote);
        },

        partidoRobosLocal: function() {
            new RoboCreateView({modelo: this.model, callbackAceptar: this.successAddRobo,
                                        origen: 'LOCAL', parent: this});
        },

        partidoRobosVisita: function() {
            new RoboCreateView({modelo: this.model, callbackAceptar: this.successAddRobo,
                                        origen: 'VISITA', parent: this});
        },

        successAddRobo: function(robo) {
            app.robosPartido.add(robo);
            this.destroyView();
        },

        successRemoveRobo: function(robo) {
            app.robosPartido.remove(robo);
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
                        new ModalGenericView({message: 'Partido registrado correctamente'});
                    },
                    error: function(model, error) {
                        new ModalGenericView({message: 'Se presento un error al registrar el partido'});
                    }
                });
            }
        }
	});

	return PartidoAdminView;

});